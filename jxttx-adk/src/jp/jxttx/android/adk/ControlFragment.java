package jp.jxttx.android.adk;

import java.io.IOException;

import jp.co.kayo.ykmjuku.andylib.cmd.AndyCommand;
import jp.co.kayo.ykmjuku.andylib.component.DeviceComponent;
import jp.co.kayo.ykmjuku.andylib.component.DeviceComponent.OnMessageReceiver;
import jp.co.kayo.ykmjuku.andylib.component.MotionController;
import jp.co.kayo.ykmjuku.andylib.component.MotionController.OnMotionListener;
import jp.co.kayo.ykmjuku.andylib.net.CommandMessage;
import jp.co.kayo.ykmjuku.andylib.tools.Logger;
import jp.co.kayo.ykmjuku.andylib.tts.TTS;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ControlFragment extends Fragment implements OnMessageReceiver,
        OnMotionListener {
    PointF left;
    PointF right;
    private DeviceComponent mBt;
    private MotionController mMotionController;
    private TTS mTts;

    private boolean mSwitchOn = false;

    private DorcusView mView = null;

    AndyCommand moteroff = new AndyCommand((byte) 0x03, (byte) 0x1B,
            new byte[] { (byte) 0x00, (byte) 0x00 }) {
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.control, container, false);

        mView = (DorcusView) root.findViewById(R.id.view);

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBt != null) {
            try {
                mBt.sendCommand(moteroff);
                Thread.sleep(500);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

    public void onReceive(CommandMessage msg) {
    }

    @Override
    public void onPause() {
        super.onPause();
        mSwitchOn = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSwitchOn = true;
        if(mBt == null){
            mBt = (DeviceComponent) getActivity().getSupportFragmentManager()
            .findFragmentByTag("BT");
            if (mBt != null) {
                mBt.setOnMessageReceiver(this);
            }
        }
        
        if(mMotionController == null){
            mMotionController = (MotionController)getActivity().getSupportFragmentManager().findFragmentByTag("MC");
            if(mMotionController!=null){
                mMotionController.setOnMotionListener(this);
            }
        }
        
        if(mTts == null){
            mTts = (TTS)getActivity().getSupportFragmentManager().findFragmentByTag("TTS");
        }
    }

    private AndyCommand createCommand(float angle, float rotate) {
        if (!mSwitchOn) {
            return null;
        }

        // 傾斜角 angle は前後、 -45〜45を有効範囲とする
        // 回転角 rotate は左右、
        if (angle > 45.0f) {
            angle = 45.0f;
        }
        if (angle < -45.0f) {
            angle = -45.0f;
        }
        if (rotate > 45.0f) {
            rotate = 45.0f;
        }
        if (rotate < -45.0f) {
            rotate = -45.0f;
        }

        int pw = (int) (angle / 45.0f * 64);
        int rol = (int) (rotate / 45.0f * 64);
        int lm;
        int rm;

        if (pw < 0) {
            lm = pw + rol;
            rm = pw - rol;
        } else {
            lm = pw - rol;
            rm = pw + rol;
        }
        if (lm > 45) {
            lm = 45;
        }
        if (lm < -45) {
            lm = -45;
        }
        if (rm > 45) {
            rm = 45;
        }
        if (rm < -45) {
            rm = -45;
        }

        if (lm < 0) {
            lm = 0x80 + (-lm);
        } else {
            lm = 0x40 + lm;
        }
        if (rm < 0) {
            rm = 0x80 + (-rm);
        } else {
            rm = 0x40 + rm;
        }
        AndyCommand motor_forword = new AndyCommand((byte) 0x03, (byte) 0x1B,
                new byte[] { (byte) lm, (byte) rm }) {
        };
        return motor_forword;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.support.v4.app.Fragment#onInflate(android.app.Activity,
     * android.util.AttributeSet, android.os.Bundle)
     */
    @Override
    public void onInflate(Activity activity, AttributeSet attrs,
            Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        if(mView!=null){
            mView.doDraw();
        }
    }

    @Override
    public void onMotinChanged(float yaw, float pitch, float roll) {
        int points = mView.getTouchPoints();
        if (points < 2) {
            try {
                mBt.sendCommand(moteroff);
            } catch (IOException e) {
                Logger.e("control off sendCommand error", e);
            }
            return;
        }
        
        AndyCommand cmd = createCommand(roll, pitch);
        if (cmd != null) {
            try {
                mBt.sendCommand(cmd);
            } catch (IOException e) {
                Logger.e("control on sendCommand error", e);
            }
        }
    }
}
