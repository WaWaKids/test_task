package android.wawakidss.test_task.retrofit;

import android.wawakidss.test_task.data.request.tournament.MatchRequest;
import android.wawakidss.test_task.data.request.video.VideoRequest;
import android.wawakidss.test_task.data.response.tournament.MatchResponse;
import android.wawakidss.test_task.data.response.video.VideoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InstatAPI {

    @POST("/test/data")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<MatchResponse> getMatchData(@Body MatchRequest matchRequest);

    @POST("/test/videoResponse-urls")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<List<VideoResponse>> getVideos(@Body VideoRequest videoRequest);
}
