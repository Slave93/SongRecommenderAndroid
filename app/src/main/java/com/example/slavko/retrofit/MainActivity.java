package com.example.slavko.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slavko.retrofit.com.example.slavko.retrofit.model.Track;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String url = "http://10.0.3.2:9090/";
    private static final String TAG = MainActivity.class.getCanonicalName();

    @Bind(R.id.input_song_name)
    EditText songName;
    @Bind(R.id.btn_recommend)
    Button recommendButton;
    ProgressDialog progressDialog;

    public static Track searchedTrack;
    public static Track recommendedTrack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Recommending...");

        recommendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommend();
            }
        });
    }

    public void recommend() {

        if (!validate()) {
            return;
        }

        progressDialog.show();

        performRecommendation(songName.getText().toString());

    }

    public void performRecommendation(String songName){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SongRecomenderApi service = retrofit.create(SongRecomenderApi.class);

        Call<List<Track>> call = service.getTrackByName(songName);
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                List<Track> trackList = response.body();
                if (trackList==null || trackList.isEmpty()) {
                    onRecommendationFailed("Couldn't find simillar song!");
                }else {
                    onRecommendationSuccess(trackList.get(0));
                }
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable throwable) {
                throwable.printStackTrace();
                onRecommendationFailed("Couldn't perform recommendation!");
            }
        });
    }

    public void onRecommendationSuccess(Track track) {
        progressDialog.dismiss();
        searchedTrack = track;
        recommendedTrack = track;
        Log.d(TAG,"found song:" + track.getName());
        Intent i = new Intent(this, RecommendationResultActivity.class);
        startActivity(i);
        //Toast.makeText(getBaseContext(), "Recommendation success! Track popularity: "+track.getPopularity(), Toast.LENGTH_SHORT).show();
    }

    public void onRecommendationFailed(String error) {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), error, Toast.LENGTH_LONG).show();
    }

    public boolean validate() {
        boolean valid = true;

        String songName = this.songName.getText().toString();

        if (songName.isEmpty()) {
            this.songName.setError("enter a song name");
            valid = false;
        } else {
            this.songName.setError(null);
        }

        return valid;
    }


}
