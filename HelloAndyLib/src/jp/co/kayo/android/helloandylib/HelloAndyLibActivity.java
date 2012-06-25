package jp.co.kayo.android.helloandylib;

import java.io.IOException;

import jp.co.kayo.ykmjuku.andylib.cmd.AndyCommand;
import jp.co.kayo.ykmjuku.andylib.component.BluetoothComponent;
import jp.co.kayo.ykmjuku.andylib.component.DeviceComponent;
import jp.co.kayo.ykmjuku.andylib.component.DeviceComponent.OnMessageReceiver;
import jp.co.kayo.ykmjuku.andylib.net.CommandMessage;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HelloAndyLibActivity extends FragmentActivity implements OnClickListener, OnMessageReceiver {
    private final String TAG = "HelloAndyLib";
    private DeviceComponent dc;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     
        Button btn1 = (Button)findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.button2);
        Button btn3 = (Button)findViewById(R.id.button3);
        
        dc = (DeviceComponent)getSupportFragmentManager().findFragmentByTag("DC");
        dc.setOnMessageReceiver(this);
        
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dc != null) {
            try {
                sendStop();
                Thread.sleep(500);
            } catch (IOException e) {
                android.util.Log.e(TAG, "send cmd failed.", e);
            } catch (InterruptedException e) {
            }
        }
    }

    public void onClick(View v) {
        try{
            if(v.getId() == R.id.button1){
                sendWalk();
            }
            else if(v.getId() == R.id.button2){
                sendStop();
            }
            else if(v.getId() == R.id.button3){
                sendBack();
            }
        }
        catch(IOException e){
            android.util.Log.e(TAG, "send cmd failed.", e);
        }
    }
    
    public void sendWalk() throws IOException{
        AndyCommand morterfront = new AndyCommand((byte) 0x03, (byte) 0x1B,
                new byte[] { (byte) 127, (byte) 127 }) {
        };
        
        dc.sendCommand(morterfront);
    }
    
    public void sendStop() throws IOException{
        AndyCommand moteroff = new AndyCommand((byte) 0x03, (byte) 0x1B,
                new byte[] { (byte) 0x00, (byte) 0x00 }) {
        };
        
        dc.sendCommand(moteroff);
        
    }
    
    public void sendBack() throws IOException{
        AndyCommand morterback = new AndyCommand((byte) 0x03, (byte) 0x1B,
                new byte[] { (byte) -128, (byte) -128 }) {
        };
        
        dc.sendCommand(morterback);
    }

    public void onReceive(CommandMessage msg) {
        switch (msg.getCommand()) {
            case BluetoothComponent.MESSAGE_DEVICE_CONNECTED: {
                Toast.makeText(this,
                        "Connected to " + new String(msg.getBuffer()),
                        Toast.LENGTH_SHORT).show();
            }
            break;
            case BluetoothComponent.MESSAGE_DEVICE_CONNECT_FAILED: {
                Toast.makeText(this,
                        new String(msg.getBuffer()),
                        Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
}