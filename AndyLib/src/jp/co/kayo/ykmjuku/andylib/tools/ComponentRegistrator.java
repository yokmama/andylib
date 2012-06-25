package jp.co.kayo.ykmjuku.andylib.tools;

import jp.co.kayo.ykmjuku.andylib.component.ADKComponent;
import jp.co.kayo.ykmjuku.andylib.component.DTalkerComponent;
import jp.co.kayo.ykmjuku.andylib.component.TTSComponent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ComponentRegistrator {

    public static boolean registerADK(FragmentManager m, FragmentTransaction t, String tag){
        if(SystemCheck.isSupportADK()){
            Fragment f = m.findFragmentByTag(tag);
            if(f == null){
                f = new ADKComponent();
                t.add(f, tag);
            }
            return true;
        }
        return false;
    }
    
    public static boolean registerADK(FragmentManager m, String tag){
        FragmentTransaction t =m.beginTransaction();
        try{
            return registerADK(m, t, tag);
        }
        finally{
            t.commit();
        }
    }
    
    public boolean registerTTS(FragmentManager m, FragmentTransaction t, String tag){
        Fragment f = m.findFragmentByTag(tag);
        if(f == null){
            f = new TTSComponent();
            t.add(f, tag);
        }
        return true;
    }
    
    public boolean registerTTS(FragmentManager m, String tag){
        FragmentTransaction t =m.beginTransaction();
        try{
            return registerTTS(m, t, tag);
        }
        finally{
            t.commit();
        }
    }
}
