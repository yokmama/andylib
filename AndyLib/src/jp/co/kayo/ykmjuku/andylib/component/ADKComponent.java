package jp.co.kayo.ykmjuku.andylib.component;

import java.io.IOException;

import jp.co.kayo.ykmjuku.andylib.net.CommandMessage;
import jp.co.kayo.ykmjuku.andylib.net.USBConnection;
import jp.co.kayo.ykmjuku.andylib.tools.Logger;

import com.android.future.usb.UsbAccessory;
import com.android.future.usb.UsbManager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ADKComponent extends DeviceComponent {
    private static final String ACTION_USB_PERMISSION = "jp.co.kayo.ykmjuku.andylib.action.USB_PERMISSION";
    
    private boolean mPermissionRequestPending;
    private PendingIntent mPermissionIntent;
    private UsbAccessory mAccessory;
    private UsbManager mUsbManager;
    private USBConnection mUSBCon;
    
    @Override
    public boolean sendCommand(CommandMessage msg) throws IOException {
        return getUSBConnection().sendCommand(msg);
    }
    
    public USBConnection getUSBConnection(){
        return mUSBCon;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return new View(getActivity());
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Logger.d("onCreate");
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d("onActivityCreated: mUsbManager="+mUsbManager + " mUSBCon="+ mUSBCon);
        
        try {
            mUsbManager = UsbManager.getInstance(getActivity());
        } catch (Exception e) {
            Logger.e("<uses-library android:name=\"com.android.future.usb.accessory\" />をマニフェストに追加してますか？", e);
        }
        mPermissionIntent = PendingIntent.getBroadcast(getActivity(), 0, new Intent(
                ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        getActivity().registerReceiver(mUsbReceiver, filter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d("onResume");
        if (mUSBCon == null) {
            mUSBCon = new USBConnection();
            UsbAccessory[] accessories = mUsbManager.getAccessoryList();
            UsbAccessory accessory = (accessories == null ? null : accessories[0]);
            Logger.d("onResume accessory:"+accessory);
            if(accessory!=null){
                if (mUsbManager.hasPermission(accessory)) {
                    Logger.d("onResume openAccessory START");
                    openAccessory(accessory);
                } else {
                    Logger.d("permission ERROR?");
                    synchronized (mUsbManager) {
                        if (!mPermissionRequestPending) {
                            mUsbManager.requestPermission(accessory,
                                    mPermissionIntent);
                            mPermissionRequestPending = true;
                        }
                    }
                }
            }
            else{
                Logger.d("not attachd USB.");
            }
        } else {
            Logger.d("mAccessory is null");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d("onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy");
        closeAccessory();
        getActivity().unregisterReceiver(mUsbReceiver);
    }

    private void openAccessory(UsbAccessory accessory) {
        Logger.d("openAccessory  accessory:"+accessory.toString());
        ParcelFileDescriptor fd = mUsbManager.openAccessory(accessory);
        Logger.d("openAccessory  fd:"+fd);
        if (fd != null) {
            mAccessory = accessory;
            mUSBCon.start(fd, getHandler());
        } else {
            Logger.d("accessory open fail");
        }
    }

    private void closeAccessory() {
        try {
            mUSBCon.stop();
        } finally {
            //mAccessory = null;
        }
    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbAccessory accessory = UsbManager.getAccessory(intent);
                    Logger.d("onReceive accessory:"+accessory);
                    if (intent.getBooleanExtra(
                            UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        openAccessory(accessory);
                    } else {
                        Logger.d("permission denied for accessory " + accessory);
                    }
                    mPermissionRequestPending = false;
                }
            } else if (UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action)) {
                UsbAccessory accessory = UsbManager.getAccessory(intent);
                if (accessory != null && accessory.equals(mAccessory)) {
                    closeAccessory();
                }
            }
        }
    };
}
