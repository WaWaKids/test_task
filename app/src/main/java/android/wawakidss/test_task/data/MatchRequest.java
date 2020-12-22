package android.wawakidss.test_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public MatchParams getMatchParams() {
        return matchParams;
    }

    public void setMatchParams(MatchParams matchParams) {
        this.matchParams = matchParams;
    }

    public String getProc() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }
}
