package android.wawakidss.test_task;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MatchActivity extends AppCompatActivity {

    private static final String TAG = "MatchActivity";
    private static final String MATCH_URL = "http://api.instat.tv/test/data";
    private static final String VIDEOS_URL = "https://api.instat.tv/test/video-urls";
    private TextView text;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);


        Bundle args = getIntent().getExtras();
        int _p_sport = args.getInt("_p_sport");
        int _p_match_id = args.getInt("_p_match_id");

        JSONObject jsonMatchParams = new JSONObject();
        try {
            jsonMatchParams.put("_p_sport", _p_sport);
            jsonMatchParams.put("_p_match_id", _p_match_id);
            Log.d(TAG, "jsonMatchParams: " + jsonMatchParams.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "failed to parse json");
        }

        Log.d(TAG, jsonMatchParams.toString());

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("proc", "get_match_info");
            jsonObject.put("params", jsonMatchParams);
            Log.d(TAG, "jsonObject: " + jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String matchResponse = post(MATCH_URL, jsonObject.toString());
        Log.d(TAG, "response: " + matchResponse);
    }

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}