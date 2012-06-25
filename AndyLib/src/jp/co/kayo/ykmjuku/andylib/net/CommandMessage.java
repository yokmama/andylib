package jp.co.kayo.ykmjuku.andylib.net;

public interface CommandMessage {
    public byte getCommand();
    public byte[] getBuffer();
}
