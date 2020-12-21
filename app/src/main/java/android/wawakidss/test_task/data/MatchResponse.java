package android.wawakidss.test_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
