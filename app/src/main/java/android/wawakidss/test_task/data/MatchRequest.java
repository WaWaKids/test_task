package android.wawakidss.test_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchRequest {

    @SerializedName("params")
    @Expose
     private MatchParams matchParams;

    @SerializedName("proc")
    @Expose
    private String proc;

    public MatchRequest(String proc, int sport, int matchId) {
        this.proc = proc;
        matchParams = new MatchParams(sport, matchId);
    }
}
