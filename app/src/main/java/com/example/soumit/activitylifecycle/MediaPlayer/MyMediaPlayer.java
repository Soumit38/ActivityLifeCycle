package com.example.soumit.activitylifecycle.MediaPlayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.soumit.activitylifecycle.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyMediaPlayer extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MyMediaPlayer";

    private MediaPlayer mediaPlayer;
    private ImageView artistImage;
    private TextView leftTime;
    private TextView rightTime;
    private Button previusBtn;
    private Button nextBtn;
    private Button playButton;
    private SeekBar seekBar;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        setUpUiComponents();

        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    int duration = mediaPlayer.getDuration();
                    String leftValue = dateFormat.format(new Date(currentPosition));
                    String rightValue = dateFormat.format(new Date(duration-currentPosition));
                    leftTime.setText(leftValue);
                    rightTime.setText(rightValue);

                    Log.d(TAG, "onProgressChanged: " + "\n" +
                    "CurrnetPos : " + currentPosition/1000 + " Duration : " + duration/1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void setUpUiComponents() {
        mediaPlayer = MediaPlayer.create(MyMediaPlayer.this, R.raw.ringtone);

        seekBar = (SeekBar) findViewById(R.id.seekbar_id);
        artistImage = (ImageView) findViewById(R.id.music_image);
        leftTime = (TextView) findViewById(R.id.leftTime);
        rightTime = (TextView) findViewById(R.id.rightTime);
        previusBtn = (Button) findViewById(R.id.btn_previous);
        nextBtn = (Button) findViewById(R.id.btn_next);
        playButton = (Button) findViewById(R.id.btn_middle);

        previusBtn.setOnClickListener(this);
        playButton.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_previous:
                backMusic();
                break;
            case R.id.btn_middle:
                if (mediaPlayer.isPlaying())
                    pauseMusic();
                else
                    startMusic();
                break;
            case R.id.btn_next:
                nextMusic();
                break;
        }
    }

    public void pauseMusic(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
            playButton.setBackgroundResource(android.R.drawable.ic_media_play);
        }
    }

    public void startMusic(){
        if (mediaPlayer!=null){
            mediaPlayer.start();
            updateThread();
            playButton.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    public void backMusic(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(0);
        }
    }

    public void nextMusic(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(mediaPlayer.getDuration());
        }
    }

    public void updateThread(){
        thread = new Thread(){
            @Override
            public void run() {
                try {
                    while (mediaPlayer!=null && mediaPlayer.isPlaying()) {
                        Thread.sleep(50);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int newPosition = mediaPlayer.getCurrentPosition();
                                int newMax = mediaPlayer.getDuration();
                                seekBar.setMax(newMax);
                                seekBar.setProgress(newPosition);

                                SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                                leftTime.setText(dateFormat.format(new Date(mediaPlayer.getCurrentPosition())));
                                rightTime.setText(dateFormat.format(new Date(mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition())));

                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        thread.interrupt();
        thread = null;
        super.onDestroy();
    }
}






































