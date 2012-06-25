package jp.co.kayo.ykmjuku.andylib.component;

import java.util.Locale;

import jp.co.kayo.ykmjuku.andylib.tools.Logger;
import jp.co.kayo.ykmjuku.andylib.tts.TTS;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TTSComponent extends Fragment implements TTS, OnInitListener {
    private TextToSpeech tts;
    
    @Override
    public void speech(String text) {
        if (tts.isSpeaking()) {
            tts.stop();
        }

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return new View(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        tts = new TextToSpeech(getActivity(), this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != tts) {
            tts.shutdown();
        }
    }
    
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale locale = Locale.ENGLISH;
            if (tts.isLanguageAvailable(locale) >=
                TextToSpeech.LANG_AVAILABLE) {
                tts.setLanguage(locale);
                tts.setPitch(1.5f);
                tts.setSpeechRate(1.5f);
                return;
            }
        }
        Logger.d("Failed to initialize");
    }

}
