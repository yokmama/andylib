package jp.co.kayo.ykmjuku.andylib.component;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MotionController extends Fragment implements SensorEventListener {
    public interface OnMotionListener{
        public void onMotinChanged(float yaw, float pitch, float roll);
    }
    
    private static final int MATRIX_SIZE = 16;
    /* 回転行列 */
    float[] inR = new float[MATRIX_SIZE];
    float[] outR = new float[MATRIX_SIZE];
    float[] I = new float[MATRIX_SIZE];
    
    /* センサーの値 */
    float[] orientationValues = new float[3];
    float[] magneticValues = new float[3];
    float[] accelerometerValues = new float[3];
    
    private float mYaw; //Z軸方向,yaw 向いている方向
    private float mPitch; //X軸方向,pitch 前後に回転する角度
    private float mRoll; //Y軸方向,roll 左右に回転する角度
    
    private SensorManager mSensorManager;
    
    private OnMotionListener mOnMotionListener;

    private boolean mIsMagSensor;
    private boolean mIsAccSensor;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        mSensorManager = (SensorManager) activity
                .getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
        return new View(getActivity());
    }
    
    @Override
    public void onPause() {
        super.onPause();
        // センサーマネージャのリスナ登録破棄
        if(mSensorManager!=null){
            if (mIsMagSensor || mIsAccSensor) {
                mSensorManager.unregisterListener(this);
                mIsMagSensor = false;
                mIsAccSensor = false;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mSensorManager!=null){
            List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
    
            // センサマネージャへリスナーを登録(implements SensorEventListenerにより、thisで登録する)
            for (Sensor sensor : sensors) {
                if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                    mSensorManager.registerListener(this, sensor,
                            SensorManager.SENSOR_DELAY_UI);
                    mIsMagSensor = true;
                }
    
                if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    mSensorManager.registerListener(this, sensor,
                            SensorManager.SENSOR_DELAY_UI);
                    mIsAccSensor = true;
                }
            }
        }
    }
    
    public void setOnMotionListener(OnMotionListener onMotionListener) {
        mOnMotionListener = onMotionListener;
    }
    
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) return;

        switch (event.sensor.getType()) {
        case Sensor.TYPE_MAGNETIC_FIELD:
            magneticValues = event.values.clone();
            break;
        case Sensor.TYPE_ACCELEROMETER:
            accelerometerValues = event.values.clone();
            break;
        }

        if (magneticValues != null && accelerometerValues != null) {

            SensorManager.getRotationMatrix(inR, I, accelerometerValues, magneticValues);

            //Activityの表示が縦固定の場合。横向きになる場合、修正が必要です
            SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Y, outR);
            SensorManager.getOrientation(outR, orientationValues);

            Log.v("Orientation",
                    String.valueOf( radianToDegree(mYaw) + ", " +
                            String.valueOf( mPitch ) + ", " +
                            String.valueOf( mRoll )));
            mYaw = radianToDegree(orientationValues[0]); //Z軸方向,yaw;
            mPitch = radianToDegree(orientationValues[1]); //X軸方向,pitch
            mRoll = radianToDegree(orientationValues[2]); //Y軸方向,roll

            doControl();
        }
    }
    
    int radianToDegree(float rad){
        return (int) Math.floor( Math.toDegrees(rad) ) ;
    }
    
    public void doControl() {
        // 制御処理呼び出し
        if(mOnMotionListener!=null){
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
                // Hunycomb
                mOnMotionListener.onMotinChanged(mYaw, mPitch, -mRoll);
            } else {
                mOnMotionListener.onMotinChanged(mYaw, mPitch, mRoll);
            }
        }
    }
}
