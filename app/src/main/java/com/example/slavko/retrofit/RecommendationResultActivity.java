package com.example.slavko.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.*;
import android.widget.Button;

import java.io.IOException;

public class RecommendationResultActivity extends Activity {

    private static final String TAG = RecommendationResultActivity.class.getCanonicalName();

    private Button btnPlayRecommended;
    private boolean playingRecommended = false;
    private MediaPlayer mediaPlayerRecommended;
    private boolean intialStageRecommended = true;

    private Button btnPlayRequested;
    private boolean playingRequested = false;
    private MediaPlayer mediaPlayerRequested;
    private boolean intialStageRequested = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_result);

        btnPlayRecommended = (Button) findViewById(R.id.buttonPreviewRecommended);
        mediaPlayerRecommended = new MediaPlayer();
        mediaPlayerRecommended.setAudioStreamType(AudioManager.STREAM_MUSIC);
        btnPlayRecommended.setBackgroundResource(R.drawable.button_play);
        btnPlayRecommended.setOnClickListener(pausePlayRecommended);

        btnPlayRequested = (Button) findViewById(R.id.buttonPreviewRequested);
        mediaPlayerRequested = new MediaPlayer();
        mediaPlayerRequested.setAudioStreamType(AudioManager.STREAM_MUSIC);
        btnPlayRequested.setBackgroundResource(R.drawable.button_play);
        btnPlayRequested.setOnClickListener(pausePlayRequested);



    }

    private OnClickListener pausePlayRecommended = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!playingRecommended) {
                btnPlayRecommended.setBackgroundResource(R.drawable.button_pause);
                if (intialStageRecommended)
                    new PlayerRecommended()
                            .execute(MainActivity.recommendation.getSuggested_song().getPreviewUrl());
                else {
                    if (!mediaPlayerRecommended.isPlaying())
                        mediaPlayerRecommended.start();
                }
                playingRecommended = true;
            } else {
                btnPlayRecommended.setBackgroundResource(R.drawable.button_play);
                if (mediaPlayerRecommended.isPlaying())
                    mediaPlayerRecommended.pause();
                playingRecommended = false;
            }
        }
    };

    private OnClickListener pausePlayRequested = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!playingRequested) {
                btnPlayRequested.setBackgroundResource(R.drawable.button_pause);
                if (intialStageRequested)
                    new PlayerRequested()
                            .execute(MainActivity.recommendation.getRequest_song().getPreviewUrl());
                else {
                    if (!mediaPlayerRequested.isPlaying())
                        mediaPlayerRequested.start();
                }
                playingRequested = true;
            } else {
                btnPlayRequested.setBackgroundResource(R.drawable.button_play);
                if (mediaPlayerRequested.isPlaying())
                    mediaPlayerRequested.pause();
                playingRequested = false;
            }
        }
    };

    /**
     * preparing mediaplayer will take sometime to buffer the content so prepare it inside the background thread and starting it on UI thread.
     *
     * @author piyush
     */

    class PlayerRecommended extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progress;

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean prepared;
            try {

                mediaPlayerRecommended.setDataSource(params[0]);

                mediaPlayerRecommended.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        intialStageRecommended = true;
                        playingRecommended = false;
                        btnPlayRecommended.setBackgroundResource(R.drawable.button_play);
                        mediaPlayerRecommended.stop();
                        mediaPlayerRecommended.reset();
                    }
                });
                mediaPlayerRecommended.prepare();
                prepared = true;
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                Log.d("IllegarArgument", e.getMessage());
                prepared = false;
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (progress.isShowing()) {
                progress.cancel();
            }
            Log.d("Prepared", "//" + result);
            mediaPlayerRecommended.start();

            intialStageRecommended = false;
        }

        public PlayerRecommended() {
            progress = new ProgressDialog(RecommendationResultActivity.this);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            this.progress.setMessage("Buffering...");
            this.progress.show();

        }
    }

    class PlayerRequested extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progress;

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean prepared;
            try {

                mediaPlayerRequested.setDataSource(params[0]);

                mediaPlayerRequested.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        intialStageRequested = true;
                        playingRequested = false;
                        btnPlayRequested.setBackgroundResource(R.drawable.button_play);
                        mediaPlayerRequested.stop();
                        mediaPlayerRequested.reset();
                    }
                });
                mediaPlayerRequested.prepare();
                prepared = true;
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                Log.d("IllegarArgument", e.getMessage());
                prepared = false;
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (progress.isShowing()) {
                progress.cancel();
            }
            Log.d("Prepared", "//" + result);
            mediaPlayerRequested.start();

            intialStageRequested = false;
        }

        public PlayerRequested() {
            progress = new ProgressDialog(RecommendationResultActivity.this);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            this.progress.setMessage("Buffering...");
            this.progress.show();

        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (mediaPlayerRecommended != null) {
            mediaPlayerRecommended.reset();
            mediaPlayerRecommended.release();
            mediaPlayerRecommended = null;
        }
        if (mediaPlayerRequested != null) {
            mediaPlayerRequested.reset();
            mediaPlayerRequested.release();
            mediaPlayerRequested = null;
        }
    }

}