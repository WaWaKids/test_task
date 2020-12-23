package android.wawakidss.test_task;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import android.view.View;
import android.wawakidss.test_task.data.MatchRequest;
import android.wawakidss.test_task.data.MatchResponse;
import android.wawakidss.test_task.data.VideosRequest;
import android.wawakidss.test_task.data.VideosResponse;
import android.wawakidss.test_task.retrofit.InstatAPI;
import android.wawakidss.test_task.retrofit.RetrofitClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchActivity extends AppCompatActivity {

    private static final String TAG = "MatchActivity";
    private InstatAPI apiInterface = RetrofitClient.getClient().create(InstatAPI.class);
    Gson gson = new Gson();
    private TextView tournamentNameEng;
    private TextView tournamentNameRus;
    private TextView team1NameEng;
    private TextView team1NameRus;
    private TextView team2NameEng;
    private TextView team2NameRus;
    private TextView score1;
    private TextView score2;
    private MatchRequest matchRequest;
    private VideosRequest videosRequest;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Bundle args = getIntent().getExtras();
        int sport = args.getInt("_p_sport");
        int matchId = args.getInt("_p_match_id");

        ll = (LinearLayout)findViewById(R.id.layout_buttons);

        tournamentNameEng = (TextView)findViewById(R.id.tournament_name_eng);
        tournamentNameRus = (TextView)findViewById(R.id.tournament_name_rus);

        team1NameEng = (TextView)findViewById(R.id.team1_name_eng);
        team1NameRus = (TextView)findViewById(R.id.team1_name_rus);

        team2NameEng = (TextView)findViewById(R.id.team2_name_eng);
        team2NameRus = (TextView)findViewById(R.id.team2_name_rus);

        score1 = (TextView)findViewById(R.id.score1);
        score2 = (TextView)findViewById(R.id.score2);

        ll = (LinearLayout)findViewById(R.id.layout_buttons);

        matchRequest = new MatchRequest("get_match_info", sport, matchId);
        Log.d(TAG, "matchRequest: " + gson.toJson(matchRequest));
        sendMatchPost(matchRequest);

        videosRequest = new VideosRequest(matchId, sport);
        Log.d(TAG, "videoRequest: " + gson.toJson(videosRequest));
        sendVideoPost(videosRequest);
    }

    public void sendMatchPost(MatchRequest matchRequest) {
        apiInterface.getMatchData(matchRequest).enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "post submitted to API." + gson.toJson(response.body()));
                    setMatchInfoOnScreen(response.body());
                }
            }

            @Override
            public void onFailure(Call<MatchResponse> call, Throwable t) {
                Log.d(TAG, "Unable to submit match post to API");
            }
        });
    }

    public void sendVideoPost(VideosRequest videosRequest) {
        apiInterface.getVideos(videosRequest).enqueue(new Callback<List<VideosResponse>>() {
            @Override
            public void onResponse(Call<List<VideosResponse>> call, Response<List<VideosResponse>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "post submitted to API." + gson.toJson(response.body()));
                    setVideoButtonsOnScreen((ArrayList<VideosResponse>) response.body());
                }
            }

            @Override
            public void onFailure(Call<List<VideosResponse>> call, Throwable t) {
                Log.d(TAG, "Unable to submit video post to API");
            }
        });
    }

    private void setMatchInfoOnScreen(MatchResponse matchResponse) {
        tournamentNameEng.setText(matchResponse.getTournament().getNameEng());
        tournamentNameRus.setText(matchResponse.getTournament().getNameRus());

        team1NameEng.setText(matchResponse.getTeam1().getNameEng());
        team1NameRus.setText(matchResponse.getTeam1().getNameRus());

        team2NameEng.setText(matchResponse.getTeam2().getNameEng());
        team2NameRus.setText(matchResponse.getTeam2().getNameRus());

        score1.setText(Integer.toString(matchResponse.getTeam1().getScore()));
        score2.setText(Integer.toString(matchResponse.getTeam2().getScore()));
    }

    private void setVideoButtonsOnScreen(ArrayList<VideosResponse> videos) {

        final float scale = MatchActivity.this.getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for(VideosResponse video: videos) {
            Button videoButton = new Button(MatchActivity.this);
            videoButton.setText(video.getName());

            videoButton.setWidth((int)(200 * scale));
            videoButton.setHeight((int)(100 * scale));
            videoButton.setLayoutParams(params);

            videoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MatchActivity.this, VideoActivity.class);
                    intent.putExtra("url", video.getUrl());
                    Log.i(TAG, "Video Playing....");
                    startActivity(intent);
                }
            });
            ll.addView(videoButton);
        }
    }
}