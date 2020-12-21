package android.wawakidss.test_task;

import android.os.Bundle;
import android.util.Log;
import android.wawakidss.test_task.data.MatchRequest;
import android.wawakidss.test_task.data.VideoRequest;
import android.wawakidss.test_task.data.MatchResponse;
import android.wawakidss.test_task.data.VideoResponse;
import android.wawakidss.test_task.retrofit.InstatAPI;
import android.wawakidss.test_task.retrofit.RetrofitClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

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
    private VideoRequest videoRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Bundle args = getIntent().getExtras();
        int sport = args.getInt("_p_sport");
        int matchId = args.getInt("_p_match_id");

        tournamentNameEng = (TextView)findViewById(R.id.tournament_name_eng);
        tournamentNameRus = (TextView)findViewById(R.id.tournament_name_rus);

        team1NameEng = (TextView)findViewById(R.id.team1_name_eng);
        team1NameRus = (TextView)findViewById(R.id.team1_name_rus);
        team2NameEng = (TextView)findViewById(R.id.team2_name_eng);
        team2NameRus = (TextView)findViewById(R.id.team1_name_rus);

        matchRequest = new MatchRequest("get_match_info", sport, matchId);
        Log.d(TAG, "matchRequest: " + gson.toJson(matchRequest));
        sendMatchPost(matchRequest);

        videoRequest = new VideoRequest(matchId, sport);
        Log.d(TAG, "videoRequest: " + gson.toJson(videoRequest));
        sendVideoPost(sport, matchId);
    }

    public void sendMatchPost(MatchRequest matchRequest) {
        Call<MatchResponse> matchResponseCall = apiInterface.getMatchData(matchRequest);
        matchResponseCall.enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<MatchResponse> call, Throwable t) {
                Log.d(TAG, "Unable to submit match post to API");
            }
        });
    }

    public void sendVideoPost(int matchId, int sportId) {
        apiInterface.getVideos(matchId, sportId).enqueue(new Callback<List<VideoResponse>>() {
            @Override
            public void onResponse(Call<List<VideoResponse>> call, Response<List<VideoResponse>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "post submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<VideoResponse>> call, Throwable t) {
                Log.d(TAG, "Unable to submit video post to API");
            }
        });
    }
}