package android.wawakidss.test_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name_eng")
    @Expose
    private String nameEng;

    @SerializedName("name_rus")
    @Expose
    private String nameRus;

    @SerializedName("score")
    @Expose
    private int score;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Team(int id, String nameEng, String nameRus, int score) {
        this.id = id;
        this.nameEng = nameEng;
        this.nameRus = nameRus;
        this.score = score;
    }
}
