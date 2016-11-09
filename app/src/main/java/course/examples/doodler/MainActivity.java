package course.examples.doodler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DoodleView mDoodleView;
    private SeekBar mColorSeekBar;
    private SeekBar mBrushSizeSeekBar;
    private SeekBar mOpacitySeekBar;
    private SeekBar mSaturationSeekBar;
    private Button mClearButton;
    private Button mFillButton;
    private Button mEraserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDoodleView = (DoodleView) findViewById(R.id.doodleView);
        mColorSeekBar = (SeekBar) findViewById(R.id.colorSeekBar);
        mBrushSizeSeekBar = (SeekBar) findViewById(R.id.brushSizeSeekBar);
        mOpacitySeekBar = (SeekBar) findViewById(R.id.opacitySeekBar);
        mSaturationSeekBar = (SeekBar) findViewById(R.id.saturationSeekBar);
        mClearButton = (Button) findViewById(R.id.clearButton);
        mFillButton = (Button) findViewById(R.id.fillButton);
        mEraserButton = (Button) findViewById(R.id.eraserButton);

        mColorSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mDoodleView.setColor(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Hue changed to " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        mBrushSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mDoodleView.setBrushSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Brush size changed to " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        mClearButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                mDoodleView.clear();
                Toast.makeText(getApplicationContext(), "Canvas cleared!", Toast.LENGTH_SHORT).show();
            }
        });

        mOpacitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mDoodleView.setOpacity(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Opacity changed to " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        mSaturationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mDoodleView.setSaturation(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Float f = new Float(seekBar.getProgress());
                Toast.makeText(getApplicationContext(), "Saturation changed to " + f/100, Toast.LENGTH_SHORT).show();
            }
        });

        mFillButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                mDoodleView.fill();
            }
        });

        mEraserButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                mDoodleView.setEraser();
                Toast.makeText(getApplicationContext(), "Eraser chosen!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
