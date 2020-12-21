package android.wawakidss.test_task.data.request.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VideoRequest {

    @SerializedName("match_id")
    @Expose
    private int matchId;

    @SerializedName("sport_id")
    @Expose
    private int sportId;

    public VideoRequest(int matchId, int sportId) {
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
