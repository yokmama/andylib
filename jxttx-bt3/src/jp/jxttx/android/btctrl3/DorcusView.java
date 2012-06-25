package jp.jxttx.android.btctrl3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DorcusView extends SurfaceView implements SurfaceHolder.Callback {

    class TouchPoint {
        public float p;
        public float x;
        public float y;
    }

    private int mWidth;
    private int mHeight;
    
    private Bitmap mBitmap = null;
    private Paint mPaint = new Paint();

    private Hashtable<Integer, TouchPoint> points;
    private MotionEvent mEv;

    private void init() {
        points = new Hashtable<Integer, TouchPoint>();

        getHolder().addCallback(this);
    }

    public DorcusView(Context context) {
        super(context);
        init();
    }

    public DorcusView(Context context, AttributeSet attr) {
        super(context, attr);
        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        try {
            if (mBitmap == null) {
                InputStream is = getResources().getAssets().open("kuwa.png");
                mBitmap = BitmapFactory.decodeStream(is);
            }
            int w = mBitmap.getWidth();
            int h = mBitmap.getHeight();
            // 描画元の矩形イメージ
            Rect src = new Rect(0, 0, w, h);
            // 描画先の矩形イメージ
            Rect dst = new Rect(0, 0, mWidth, mHeight);
            canvas.drawBitmap(mBitmap, src, dst, null);
        } catch (IOException e) {
            /* 例外処理 */
        }

        Set<Integer> keys = points.keySet();
        for (Iterator<Integer> i = keys.iterator(); i.hasNext();) {
            Integer id = (Integer) i.next();
            switch (id) {
            case 0:
                mPaint.setColor(Color.RED);
                break;

            case 1:
                mPaint.setColor(Color.GREEN);
                break;

            default:
                // 二つまで描画
                break;
            }
            TouchPoint p = points.get(id);
            canvas.drawCircle(p.x, p.y, p.p * 240, mPaint);
        }
    }

    private void setPoints(MotionEvent ev) {
        int count = ev.getPointerCount();
        for (int i = 0; i < count; i++) {
            int id = ev.getPointerId(i);
            TouchPoint p = new TouchPoint();
            p.x = ev.getX(i);
            p.y = ev.getY(i);
            p.p = ev.getPressure(i);
            points.put(id, p);
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        mEv = ev;
        int action = ev.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_POINTER_DOWN:
        case MotionEvent.ACTION_MOVE:
            setPoints(ev);
            break;

        case MotionEvent.ACTION_UP:
            points.remove(ev.getPointerId(0));
            break;

        case MotionEvent.ACTION_POINTER_UP:
            setPoints(ev);
            int index = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
            points.remove(ev.getPointerId(index));
            break;
        }

        doDraw();
        return true;
    }

    public void doDraw() {
        Canvas canvas = getHolder().lockCanvas();
        if (canvas != null) {
            onDraw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    // タッチ点数を取得
    public int getTouchPoints() {
        if (mEv == null) {
            return 0;
        }
        return mEv.getPointerCount();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {

        mWidth = width;
        mHeight = height;
        doDraw();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mBitmap = null;
    }
}
