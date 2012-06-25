package jp.co.kayo.ykmjuku.andylib.tools;

public class SystemCheck {
    public static boolean isSupportADK(){
        Class c = null;
        try {
            c = Class.forName("com.android.future.usb.UsbManager");
            return true;
        } catch (ClassNotFoundException e) {
        }
        return false;
    }
}
