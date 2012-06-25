package jp.co.kayo.ykmjuku.andylib.component;

import jp.co.kayo.ykmjuku.andylib.tools.Logger;
import jp.co.kayo.ykmjuku.andylib.tts.DTalkerTTS;
import jp.co.kayo.ykmjuku.andylib.tts.TTS;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DTalkerComponent extends Fragment implements TTS {
    DTalkerTTS  mTTS = null;
    
    @Override
    public void speech(String text){
        if (mTTS!=null){
            mTTS.speak(text);
        }

    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return new View(getActivity());
    }
    
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        if (mTTS!=null) mTTS.release();
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        
        mTTS = new DTalkerTTS(getActivity(), mHandler);
    }

    private Handler mHandler = new Handler(){
        public void dispatchMessage(Message msg){
            if (msg.what==DTalkerTTS.CALLBACK_DTS_FINISHED){
                Logger.d("CALLBACK_DTS_FINISHED");
            }
            else if (msg.what==DTalkerTTS.CALLBACK_DTS_STRING){
                Logger.d("CALLBACK_DTS_STRING:"+ msg.obj);
            }
            else if (msg.what==DTalkerTTS.CALLBACK_DTS_OFFSET){
                Logger.d("CALLBACK_DTS_OFFSET:"+msg.arg1+":"+msg.arg2);
            }
            else if (msg.what==DTalkerTTS.CALLBACK_DTS_POSITION){
                Logger.d("CALLBACK_DTS_POSITION:"+msg.arg1);
            }
            else if (msg.what==DTalkerTTS.CALLBACK_DTS_STARTED){
                Logger.d("CALLBACK_DTS_STARTED:"+msg.arg1);
                mTTS.setVoice(1);
                mTTS.setTone(3);
                mTTS.setKigouYomi(false);
                Logger.d("getVoice="+mTTS.getVoice());
            }
            else{
                super.dispatchMessage(msg);
            }
        }
    };
    
}
