package jp.co.kayo.ykmjuku.andylib.net;

public abstract class RecieveCommandMessage implements CommandMessage {
    private byte[] buffer;
    
    public RecieveCommandMessage(byte[] buffer){
        this.buffer = buffer;
    }
    
    @Override
    public byte[] getBuffer() {
        return buffer;
    }
}
