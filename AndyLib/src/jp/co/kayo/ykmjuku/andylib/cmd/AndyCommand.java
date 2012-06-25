package jp.co.kayo.ykmjuku.andylib.cmd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import jp.co.kayo.ykmjuku.andylib.net.CommandMessage;

public class AndyCommand implements CommandMessage {
    private byte cmd;
    private byte addr;
    private byte[] data;
    private final byte[] HEADER = { 0xfffffffa, 0xffffffaf};
    
    public AndyCommand(byte cmd, byte addr){
        this.cmd = cmd;
        this.addr = addr;
    }
    
    public AndyCommand(byte cmd, byte addr, byte[] data){
        this.cmd = cmd;
        this.addr = addr;
        setData(data);
    }
    
    public void setData(byte[] data){
        this.data = new byte[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }
    
    @Override
    public byte getCommand() {
        return cmd;
    }
    
    public byte getAddr(){
        return addr;
    }
    

    @Override
    public byte[] getBuffer() {
        int sum = 0;
        sum ^=cmd;
        sum ^=addr;
        sum ^=data.length;
        for(byte b : data){
            sum ^= b;
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            output.write(HEADER);
            output.write(cmd);
            output.write(addr);
            output.write(data.length);
            output.write(data);
            output.write(sum);
            return output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
