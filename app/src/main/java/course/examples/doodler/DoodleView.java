package course.examples.doodler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DoodleView extends View {

    private Paint mPaint;
    private Paint mCanvasPaint;
    private Path mPath = new Path();
    private Canvas mCanvas = new Canvas();
    private Bitmap mBitmap;
    private int mAlpha = 255;
    private int mSat = 255;
    private float[] mHSVColor = {0, 1, 1};

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle){
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvasPaint = new Paint(Paint.DITHER_FLAG);
        mCanvas.drawColor(Color.WHITE);

    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mCanvasPaint);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                mPath.lineTo(touchX, touchY);
                mCanvas.drawPath(mPath, mPaint);
                mPath.reset();
                break;
        }

        invalidate();
        return true;
    }

    public void setColor(int hue) {
        invalidate();

        mHSVColor[0] = hue;
        mPaint.setColor(Color.HSVToColor(mHSVColor));
    }

    public void setBrushSize(int size) {
        Float f = new Float(size);
        mPaint.setStrokeWidth(f);
    }

    public void clear() {
        mCanvas.drawColor(Color.WHITE);
        invalidate();
    }

    public void setOpacity(int opacity) {
        invalidate();
        mAlpha = opacity;
        mPaint.setColor(Color.HSVToColor(mHSVColor));
        mPaint.setAlpha(mAlpha);

    }

    public void setSaturation(float saturation) {
        invalidate();

        mHSVColor[1] = saturation/100;
        mPaint.setColor(Color.HSVToColor(mHSVColor));
    }

    public void fill() {
        mCanvas.drawColor(Color.HSVToColor(mHSVColor));
        invalidate();
    }

    public void setEraser() {
        invalidate();

        mPaint.setColor(Color.WHITE);
    }

}
