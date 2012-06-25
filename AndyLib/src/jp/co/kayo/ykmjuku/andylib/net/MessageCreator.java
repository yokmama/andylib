package jp.co.kayo.ykmjuku.andylib.net;


public interface MessageCreator {
    public RecieveCommandMessage createMessage(byte[] chunk);
}
