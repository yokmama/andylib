package jp.co.kayo.ykmjuku.andylib.message;

import jp.co.kayo.ykmjuku.andylib.net.CommandMessage;

public class DebugLed implements CommandMessage {

    public DebugLed(byte[] chunk) {
    }

    public byte getCommand() {
        // TODO Auto-generated method stub
        return 0;
    }

    public byte getCategory() {
        // TODO Auto-generated method stub
        return 0;
    }

    public byte[] getBuffer() {
        // TODO Auto-generated method stub
        return null;
    }

}
