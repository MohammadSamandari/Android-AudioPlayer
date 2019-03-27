package com.mohammadsamandari.audioplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    SeekBar sbVolume, sbPlayBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.music);

        sbVolume=findViewById(R.id.sbVolume);
        sbPlayBack=findViewById(R.id.sbPlayBack);

        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int maxVolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        sbVolume.setMax(maxVolume);
        sbVolume.setProgress(curVolume);

        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        int musicLength=mediaPlayer.getDuration();
        int curPosition=mediaPlayer.getCurrentPosition();

        sbPlayBack.setMax(musicLength);
        sbPlayBack.setProgress(curPosition);

        sbPlayBack.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sbPlayBack.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,2000);






    }

    public void play(View view) {

        mediaPlayer.start();

    }

    public void pause(View view) {
        mediaPlayer.pause();
    }

    public void stop(View view) {
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
    }
}
