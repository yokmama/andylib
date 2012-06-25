package jp.co.kayo.ykmjuku.andylib.cmd;

import java.util.Arrays;

import jp.co.kayo.ykmjuku.andylib.net.CommandMessage;

public class BlueToothCommand implements CommandMessage {
    byte cmd;
    byte[] buffer;
    byte state = -1;
    String text;
    
    public BlueToothCommand(byte cmd){
        this.cmd = cmd;
    }
    
    public BlueToothCommand(byte cmd, byte state){
        this.cmd = cmd;
        this.state = state;
    }
    
    public BlueToothCommand(byte cmd, String text){
        this.cmd = cmd;
        this.text = text;
    }

    public BlueToothCommand(byte cmd, byte[] buffer){
        this.cmd = cmd;
        this.buffer = Arrays.copyOf(buffer, buffer.length);
    }

    @Override
    public byte getCommand() {
        return cmd;
    }

    @Override
    public byte[] getBuffer() {
        if(text != null){
            return text.getBytes();
        }
        else if(buffer !=null ){
            return buffer;
        }
        else if(state != -1 ){
            return new byte[]{state};
        }
        else{
            return new byte[0];
        }
    }

}
