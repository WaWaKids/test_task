package android.wawakidss.test_task.retrofit;

import android.wawakidss.test_task.data.MatchRequest;
import android.wawakidss.test_task.data.MatchResponse;
import android.wawakidss.test_task.data.VideosRequest;
import android.wawakidss.test_task.data.VideosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InstatAPI {

    @POST("test/data")
    @Headers({"Content-Type: application/json"})
    Call<MatchResponse> getMatchData(@Body MatchRequest matchRequest);

    @POST("test/videoResponse-urls")
    @Headers({"Content-Type: application/json"})
    Call<List<VideosResponse>> getVideos(@Body VideosRequest videosRequest);
}
