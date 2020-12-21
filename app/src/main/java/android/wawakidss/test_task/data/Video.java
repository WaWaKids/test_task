package android.wawakidss.test_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.namespace.QName;

import lombok.Getter;

@Getter
public class Video {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("match_id")
    @Expose
    private int matchId;

    @SerializedName("period")
    @Expose
    private int period;

    @SerializedName("server_id")
    @Expose
    private int serverId;

    @SerializedName("quality")
    @Expose
    private String quality;

    @SerializedName("folder")
    @Expose
    private String folder;

    @SerializedName("video_type")
    @Expose
    private String videoType;

    @SerializedName("abc")
    @Expose
    private String abc;

    @SerializedName("start_ms")
    @Expose
    private int startMs;

    @SerializedName("checksum")
    @Expose
    private int checksum;

    @SerializedName("size")
    @Expose
    private int size;

    @SerializedName("abc_type")
    @Expose
    private String abcType;

    @SerializedName("duration")
    @Expose
    private int duration;

    @SerializedName("fps")
    @Expose
    private int fps;

    @SerializedName("url_root")
    @Expose
    private String urlRoot;

    @SerializedName("url")
    @Expose
    private String url;
}
