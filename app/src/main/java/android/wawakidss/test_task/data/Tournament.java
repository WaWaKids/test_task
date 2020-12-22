package android.wawakidss.test_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tournament {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name_eng")
    @Expose
    private String nameEng;

    @SerializedName("name_rus")
    @Expose
    private String nameRus;

    @SerializedName("team1")
    @Expose
    private String team1;

    @SerializedName("team2")
    @Expose
    private String team2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public Tournament(int id, String nameEng, String nameRus, String team1, String team2) {
        this.id = id;
        this.nameEng = nameEng;
        this.nameRus = nameRus;
        this.team1 = team1;
        this.team2 = team2;
    }
}
