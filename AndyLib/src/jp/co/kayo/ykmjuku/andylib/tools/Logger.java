package jp.co.kayo.ykmjuku.andylib.tools;

import android.util.Log;

public class Logger {
	private static final boolean debug = true;
	private static final String TAG = "ADKProject";
	
	public static int d(String msg){
		if(debug && msg!=null){
            return Log.d(TAG, msg);
		}
		return 0;
	}
	
	public static int v(String msg){
	    if(msg!=null){
	        return Log.v(TAG, msg);
	    }
	    return 0;
	}
	
	public static int e(String msg, Exception e){
        if(msg!=null){
    		return Log.e(TAG, msg, e);
        }
        return 0;
	}
}
