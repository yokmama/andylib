package jp.co.kayo.ykmjuku.andylib.component;

import java.io.IOException;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import jp.co.kayo.ykmjuku.andylib.net.CommandMessage;

public abstract class DeviceComponent extends Fragment {
    public interface OnMessageReceiver {
        public void onReceive(CommandMessage msg);
    }
    private OnMessageReceiver mMessageReceiver;
    
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(mMessageReceiver!=null){
                mMessageReceiver.onReceive((CommandMessage)msg.obj);
            }
        }
    };
    
    public abstract boolean sendCommand(CommandMessage msg) throws IOException ;
    
    public void setOnMessageReceiver(OnMessageReceiver r){
        mMessageReceiver = r;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMessageReceiver = null;
    }
    
    public Handler getHandler(){
        return mHandler;
    }
}
