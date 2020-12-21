package android.wawakidss.test_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
}
