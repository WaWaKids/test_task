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
    private static final String MATCH_URL = "http://api.instat.tv/test/data";
    private static final String VIDEOS_URL = "https://api.instat.tv/test/video-urls";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Bundle args = getIntent().getExtras();
        int sport = args.getInt("_p_sport");
        int matchId = args.getInt("_p_match_id");

        if (NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
            Log.d(TAG, "internet connection is denied");
            Toast.makeText(MatchActivity.this,
                    "Internet connection is denied by Network Security Policy",
                    Toast.LENGTH_SHORT);
        }

        tournamentNameEng = (TextView)findViewById(R.id.tournament_name_eng);
        tournamentNameRus = (TextView)findViewById(R.id.tournament_name_rus);

        team1NameEng = (TextView)findViewById(R.id.team1_name_eng);
        team1NameRus = (TextView)findViewById(R.id.team1_name_rus);
        team2NameEng = (TextView)findViewById(R.id.team2_name_eng);
        team2NameRus = (TextView)findViewById(R.id.team1_name_rus);

        matchRequest = new MatchRequest("get_match_info", sport, matchId);
        Log.d(TAG, "matchRequest: " + gson.toJson(matchRequest));
        sendMatchPost(matchRequest);

        videosRequest = new VideosRequest(matchId, sport);
        Log.d(TAG, "videoRequest: " + gson.toJson(videosRequest));
        sendVideoPost(videosRequest);
    }

    public void sendMatchPost(MatchRequest matchRequest) {
        Call<MatchResponse> matchResponseCall = apiInterface.getMatchData(matchRequest);
        matchResponseCall.enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "post submitted to API." + response.body().toString());
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
                    setVideoButtonsOnScreen(response.body());
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
        score1.setText(matchResponse.getTeam1().getScore());
        score2.setText(matchResponse.getTeam2().getScore());
    }

    private void setVideoButtonsOnScreen(List<VideosResponse> videosList) {
        LinearLayout ll = (LinearLayout)findViewById(R.id.layout_buttons);
        videosList = (ArrayList<VideosResponse>)videosList;

        for(int i = 0; i < videosList.size(); i++) {
            Button videoButton = new Button(this);
            videoButton.setText(videosList.get(i).getName());

            videoButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            videoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(videosList.get(i).getURL())));
                    Log.i("Video", "Video Playing....");
                }
            });

            ll.addView(videoButton);
        }
    }
}