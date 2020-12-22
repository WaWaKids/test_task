package android.wawakidss.test_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MatchParams {

    @SerializedName("_p_sport")
    @Expose
    private int sportId;

    @SerializedName("_p_match_id")
    @Expose
    private int matchId;

    public MatchParams (int sportId, int matchId) {
        this.sportId = sportId;
        this.matchId = matchId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}
