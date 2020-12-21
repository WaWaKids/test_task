package android.wawakidss.test_task.data.response.tournament;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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
}
