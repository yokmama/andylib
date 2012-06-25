package jp.co.kayo.ykmjuku.andylib.net;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import jp.co.kayo.ykmjuku.andylib.message.DebugLed;
import jp.co.kayo.ykmjuku.andylib.message.ServoMotor;
import jp.co.kayo.ykmjuku.andylib.tools.Logger;

public class USBConnection implements Runnable {
    private static final int MAX_READSIZE = 16384;
    private volatile boolean _running = false;
    private FileInputStream mInput=null;
    private FileOutputStream mOutput=null;
    private Handler mHandler;
    private ParcelFileDescriptor mPfd;
    
    private static HashMap<Byte, MessageCreator> sCareators = new HashMap<Byte, MessageCreator>();
    
    public static void addCreator(byte cmd, MessageCreator creator){
        synchronized (sCareators) {
            sCareators.put(cmd, creator);
        }
    }
    
    public USBConnection(){
    }
    
    public void start(ParcelFileDescriptor pfd, Handler handler){
        if( ! _running){
            _running = true;
            mHandler = handler;
            mPfd = pfd;
            FileDescriptor fd = pfd.getFileDescriptor();
            mInput = new FileInputStream(fd);
            mOutput = new FileOutputStream(fd);
            Thread t = new Thread(this);
            t.start();
        }
    }
    
    public void stop(){
        try {
            if (mPfd != null) {
                mPfd.close();
            }
        } catch (IOException e) {
            Logger.e("stop", e);
        } finally {
            mPfd = null;
            _running = false;
        }
    }
    
    public boolean sendCommand(CommandMessage msg) throws IOException {
        if(mOutput!=null){
            mOutput.write(msg.getBuffer());
            return true;
        } else {
            Log.e("sendCommand", "mOutput is null");
        }
        return false;
    }
    
    private void parseData(int size, byte[] pack){
        int pos=0;
        byte len;
        do{
            len = (byte)(pack[pos]+1);
            if(len>0 && (pos+len)<=size){
                byte[] chunk = Arrays.copyOfRange(pack, pos, pos+len);
                byte cmd = chunk[1];
                MessageCreator creator = null;
                synchronized (sCareators) {
                   creator = sCareators.get(cmd);
                }
                if(creator!=null){
                    Message m = Message.obtain(mHandler, cmd);
                    m.obj = creator.createMessage(chunk);
                    mHandler.sendMessage(m);
                }
            }
            pos+=len;
        }while(pos<size);
    }
    
    public void run() {
        try{
            Logger.d("fd:"+mPfd.toString());
            
            int size = 0;
            byte[] buffer = new byte[MAX_READSIZE];
            Logger.d("start listen input:"+mInput);
            while ((size = mInput.read(buffer)) >= 0) {
                parseData(size, buffer);
            }
        } catch (IOException e) {
            Logger.e("USBConnection.run", e);
        }
        finally{
            if(mInput!=null){
                try {
                    mInput.close();
                } catch (IOException e) {}
                mInput = null;
            }
            if(mOutput!=null){
                try {
                    mOutput.close();
                } catch (IOException e) {}
                mOutput = null;
            }
        }
    }

}
