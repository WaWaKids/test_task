package android.wawakidss.test_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideosRequest {

    @SerializedName("match_id")
    @Expose
    private int matchId;

    @SerializedName("sport_id")
    @Expose
    private int sportId;

    public VideosRequest(int matchId, int sportId) {
        this.matchId = matchId;
        this.sportId = sportId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }
}