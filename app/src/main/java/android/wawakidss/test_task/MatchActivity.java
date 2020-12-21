package android.wawakidss.test_task;

import android.os.Bundle;
import android.util.Log;
import android.wawakidss.test_task.data.request.tournament.MatchParams;
import android.wawakidss.test_task.data.request.tournament.MatchRequest;
import android.wawakidss.test_task.data.request.video.VideoRequest;
import android.wawakidss.test_task.data.response.tournament.MatchResponse;
import android.wawakidss.test_task.data.response.video.VideoResponse;
import android.wawakidss.test_task.retrofit.InstatAPI;
import android.wawakidss.test_task.retrofit.RetrofitClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchActivity extends AppCompatActivity {

    private static final String TAG = "MatchActivity";
    private static final String MATCH_URL = "http://api.instat.tv/test/data";
    private static final String VIDEOS_URL = "https://api.instat.tv/test/video-urls";
    private InstatAPI apiInterface = RetrofitClient.getClient().create(InstatAPI.class);
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Bundle args = getIntent().getExtras();
        int sport = args.getInt("_p_sport");
        int matchId = args.getInt("_p_match_id");

        MatchRequest matchRequest = new MatchRequest("get_match_info", sport, matchId);

        Call<MatchResponse> matchResponseCall = apiInterface.getMatchData(matchRequest);
        sendMatchPost(matchRequest);
        VideoRequest videoRequest = new VideoRequest(matchId, sport);
    }

    public void sendMatchPost(MatchRequest matchRequest) {
        apiInterface.getMatchData(matchRequest).enqueue(new Callback<MatchResponse>() {
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

    public void sendVideoPost(VideoRequest videoRequest) {
        apiInterface.getVideos(videoRequest).enqueue(new Callback<List<VideoResponse>>() {
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