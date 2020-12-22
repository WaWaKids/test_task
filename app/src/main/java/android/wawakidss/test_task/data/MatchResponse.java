package android.wawakidss.test_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchResponse {

    @SerializedName("tournament")
    @Expose
    private Tournament tournament;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("team1")
    @Expose
    private Team team1;

    @SerializedName("team2")
    @Expose
    private Team team2;

    @SerializedName("stream_status")
    @Expose
    private int streamStatus;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getStreamStatus() {
        return streamStatus;
    }

    public void setStreamStatus(int streamStatus) {
        this.streamStatus = streamStatus;
    }

    public MatchResponse(Tournament tournament, String date, Team team1, Team team2, int streamStatus) {
        this.tournament = tournament;
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.streamStatus = streamStatus;
    }
}
