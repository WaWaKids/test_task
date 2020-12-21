package android.wawakidss.test_task.data.request.tournament;

import android.wawakidss.test_task.data.request.tournament.MatchParams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MatchRequest {

    @SerializedName("proc")
    @Expose
     private String proc;

    @SerializedName("params")
    @Expose
     private MatchParams matchParams;

    public MatchRequest(String proc, int sport, int matchId) {
        this.proc = proc;
        MatchParams matchParams = new MatchParams(sport, matchId);
    }

    public void setProc(String proc) {

        this.proc = proc;
    }

    public void setMatchParams(MatchParams matchParams) {

        this.matchParams = matchParams;
    }

    public String getProc() {
        return proc;
    }

    public MatchParams getMatchParams() {
        return matchParams;
    }
}
