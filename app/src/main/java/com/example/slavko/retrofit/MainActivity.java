package com.example.slavko.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slavko.retrofit.com.example.slavko.retrofit.model.ClusteringResponse;
import com.example.slavko.retrofit.com.example.slavko.retrofit.model.FeatureWeights;
import com.example.slavko.retrofit.com.example.slavko.retrofit.model.Recommendation;
import com.example.slavko.retrofit.com.example.slavko.retrofit.model.RecommendationRequest;


import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    @Bind(R.id.input_song_name)
    EditText inpSongName;
    @Bind(R.id.input_criteria)
    EditText inpCriteria;
    @Bind(R.id.btn_recommend)
    Button recommendButton;
    ProgressDialog progressDialog;

    String[] clusteringAlgorighms;

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

        clusteringAlgorighms = getResources().getStringArray(R.array.listArrayClusteringAlgorithms);

        inpSongName.clearFocus();
        inpCriteria.clearFocus();
        setCriteriaHint();


    }

    @Override
    protected void onResume() {
        super.onResume();
        setCriteriaHint();

    }

    private void setCriteriaHint() {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String criteria = SP.getString("reccomendationCriteria", "1");
        String hint = "";
        switch (criteria) {
            case "1":
                hint = "Genre, eg. Pop Rock";
                break;
            case "2":
                hint = "Year span, eg. 1990-2000";
                break;
        }
        inpCriteria.setHint(hint);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            Intent i = new Intent(this, RecommenderPreferencesActivity.class);
            startActivity(i);


        }
        if (id == R.id.action_clustering) {

            performReclustering();

        }

        return super.onOptionsItemSelected(item);
    }

    public void performReclustering() {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String username = SP.getString("username", null);
        String password = SP.getString("password", null);
        if (username == null || password == null || !username.equals("admin") || !password.equals("admin")) {
            Toast.makeText(this, "Yout do not have admin permissions. Please go to the settings and set admin credentials", Toast.LENGTH_LONG).show();
            return;
        }


        String serverAddress = SP.getString("serverAddress", null);
        int clusteringAlgorithm = Integer.parseInt(SP.getString("clusteringAlgorithm", "1"));
        String chosenClusteringAlgorithm = clusteringAlgorighms[clusteringAlgorithm - 1];

        if (serverAddress == null || serverAddress.isEmpty()) {
            Toast.makeText(this, "Server address not set. Please go to the settings and provide server address", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Sending clustering request");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SongRecomenderApi service = retrofit.create(SongRecomenderApi.class);

        Call<ClusteringResponse> call = service.performClustering(chosenClusteringAlgorithm);

        call.enqueue(new Callback<ClusteringResponse>() {
            @Override
            public void onResponse(Call<ClusteringResponse> call, Response<ClusteringResponse> response) {
                ClusteringResponse clusteringResponse = response.body();
                if (clusteringResponse == null) {
                    Toast.makeText(MainActivity.this, "Server did not respond. Please try again later.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, clusteringResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ClusteringResponse> call, Throwable t) {
                t.printStackTrace();
                //TODO: replace two lines below
                /*Toast.makeText(MainActivity.this, "Couldn't perform clustering. Please try again later.", Toast.LENGTH_LONG).show();*/
                Toast.makeText(MainActivity.this, "Clustering sheduled", Toast.LENGTH_LONG).show();

                progressDialog.dismiss();
            }
        });


    }

    public void recommend() {
        System.out.println("Recommending");
        RecommendationRequest recommendationRequest = new RecommendationRequest();

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String serverAddress = SP.getString("serverAddress", null);

        if (serverAddress == null || serverAddress.isEmpty()) {
            Toast.makeText(this, "Server address not set. Please go to the settings and provide server address", Toast.LENGTH_LONG).show();
            return;
        }

        if (!validateSongNameInput()) {
            return;
        }

        recommendationRequest.setSongName(inpSongName.getText().toString());


        String criteria = SP.getString("reccomendationCriteria", "1");
        switch (criteria) {
            case "1":
                System.out.println("Chosen genre");
                recommendationRequest.setCriteriaKey("genre");
                recommendationRequest.setCriteriaValue(inpCriteria.getText().toString());
                break;
            case "2":
                System.out.println("Chosen year");
                if (!validateCriteriaYear()) {
                    return;
                } else {
                    recommendationRequest.setCriteriaKey("year");
                    recommendationRequest.setCriteriaValue(inpCriteria.getText().toString());
                }
                break;
        }
        recommendationRequest.setFeatureWeights(createFeatureWeights());

        progressDialog.setMessage("Recommending");
        progressDialog.show();

        performRecommendation(recommendationRequest, serverAddress);

    }

    private FeatureWeights createFeatureWeights() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        FeatureWeights featureWeights = new FeatureWeights();
        featureWeights.setYear(sp.getInt("yearWeight", 100));
        featureWeights.setTime_signature(sp.getInt("timeSignatureWeight", 0));
        featureWeights.setTempo(sp.getInt("tempoWeight", 80));
        featureWeights.setValence(sp.getInt("valenceWeight", 100));
        featureWeights.setLiveness(sp.getInt("livenessWeight", 20));
        featureWeights.setInstrumentalness(sp.getInt("instrumentalnessWeight", 30));
        featureWeights.setAcousticness(sp.getInt("acousticnessWeight", 70));
        featureWeights.setSpeechiness(sp.getInt("speechinessWeight", 60));
        featureWeights.setMode(sp.getInt("modeWeight", 50));
        featureWeights.setLoudness(sp.getInt("loudnessWeight", 0));
        featureWeights.setTrack_key(sp.getInt("trackKeyWeight", 0));
        featureWeights.setEnergy(sp.getInt("energyWeight", 60));
        featureWeights.setDanceability(sp.getInt("danceabilityWeight", 80));
        featureWeights.setPopularity(sp.getInt("popularityWeight", 30));
        System.out.println("Feature weights created:" + featureWeights);
        return featureWeights;
    }


    public void performRecommendation(RecommendationRequest recommendationRequest, String serverAddress) {
        System.out.println("Recommendation request will be sent now.Recommendation request:" + recommendationRequest);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SongRecomenderApi service = retrofit.create(SongRecomenderApi.class);
        if (recommendationRequest.getCriteriaValue().isEmpty()) {
            recommendationRequest.setCriteriaValue(null);
        }
        Call<Recommendation> call = service.performRecommendation(recommendationRequest);
        call.enqueue(new Callback<Recommendation>() {
            @Override
            public void onResponse(Call<Recommendation> call, Response<Recommendation> response) {
                recommendation = response.body();
                if (recommendation == null) {
                    onRecommendationFailed("Server did not respond. Please try again later.");
                } else if (recommendation.getSuccess()) {
                    onRecommendationSuccess(recommendation);
                } else {
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

    public void performRecommendationOld(String songName, String genre, String serverAddress) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SongRecomenderApi service = retrofit.create(SongRecomenderApi.class);
        if (genre.isEmpty()) {
            genre = null;
        }
        Call<Recommendation> call = service.suggestSimilar(songName, genre);
        call.enqueue(new Callback<Recommendation>() {
            @Override
            public void onResponse(Call<Recommendation> call, Response<Recommendation> response) {
                recommendation = response.body();
                if (recommendation == null) {
                    onRecommendationFailed("Server did not respond. Please try again later.");
                } else if (recommendation.getSuccess()) {
                    onRecommendationSuccess(recommendation);
                } else {
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
        Log.i(TAG, recommendation.toString());
        Intent i = new Intent(this, RecommendationResultActivity.class);
        startActivity(i);
    }

    public void onRecommendationFailed(String error) {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), error, Toast.LENGTH_LONG).show();
    }

    public boolean validateSongNameInput() {
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

    public boolean validateCriteriaYear() {
        boolean valid = true;

        String yearSpan = this.inpCriteria.getText().toString();
        if (yearSpan!=null && !yearSpan.isEmpty() && !yearSpan.matches("\\d+-\\d+")) {
            this.inpCriteria.setError("year span is not in a valid format");
            valid = false;
        } else {
            this.inpCriteria.setError(null);
        }

        return valid;
    }


}
