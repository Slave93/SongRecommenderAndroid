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

import com.example.slavko.retrofit.com.example.slavko.retrofit.model.Recommendation;


import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static String server_address = "http://192.168.0.105:4000";
    private static final String TAG = MainActivity.class.getCanonicalName();

    @Bind(R.id.input_song_name)
    EditText inpSongName;
    @Bind(R.id.input_server_address)
    EditText inpServerAddress;
    @Bind(R.id.btn_recommend)
    Button recommendButton;
    ProgressDialog progressDialog;

    public static Recommendation recommendation;

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

        inpServerAddress.setText(server_address);

        inpSongName.clearFocus();
    }

    public void recommend() {
        if(!inpServerAddress.getText().toString().isEmpty()){
            server_address = inpServerAddress.getText().toString();
        }else{
            inpServerAddress.setText(server_address);
        }


        if (!validate()) {
            return;
        }

        progressDialog.show();

        performRecommendation(inpSongName.getText().toString());

    }

    public void performRecommendation(String songName){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server_address)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SongRecomenderApi service = retrofit.create(SongRecomenderApi.class);

        Call<Recommendation> call = service.getTrackByName(songName);
        call.enqueue(new Callback<Recommendation>() {
            @Override
            public void onResponse(Call<Recommendation> call, Response<Recommendation> response) {
                recommendation = response.body();
                if (recommendation==null) {
                    onRecommendationFailed("Server did not respond. Please try again later.");
                }else if(recommendation.getSuccess()) {
                    onRecommendationSuccess(recommendation);
                }else{
                    onRecommendationFailed(recommendation.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Recommendation> call, Throwable t) {
                t.printStackTrace();
                onRecommendationFailed("Couldn't perform recommendation. Please try again later.");
            }


        });
    }

    public void onRecommendationSuccess(Recommendation recommendation) {
        progressDialog.dismiss();
        Log.i(TAG,recommendation.toString());
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

        String songName = this.inpSongName.getText().toString();

        if (songName.isEmpty()) {
            this.inpSongName.setError("enter a song name");
            valid = false;
        } else {
            this.inpSongName.setError(null);
        }

        return valid;
    }


}
