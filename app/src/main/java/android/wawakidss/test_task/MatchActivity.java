package android.wawakidss.test_task;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
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

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("proc", "get_match_info");
            jsonObject.put("params", jsonMatchParams.toString());
            Log.d(TAG, "jsonObject: " + jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject match_response = foo(MATCH_URL, jsonObject);
        Log.d(TAG, "response: " + match_response.toString());

    }

    public static JSONObject foo(String url, JSONObject json) {
        JSONObject jsonObjectResp = null;

        try {

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();

            okhttp3.RequestBody body = RequestBody.create(JSON, json.toString());
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            okhttp3.Response response = client.newCall(request).execute();

            String networkResp = response.body().string();
            if (!networkResp.isEmpty()) {
                jsonObjectResp = parseJSONStringToJSONObject(networkResp);
            }
        } catch (Exception ex) {
            String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", ex.getMessage());
            jsonObjectResp = parseJSONStringToJSONObject(err);
        }

        return jsonObjectResp;
    }

    private static JSONObject parseJSONStringToJSONObject(final String strr) {

        JSONObject response = null;
        try {
            response = new JSONObject(strr);
        } catch (Exception ex) {
            //  Log.e("Could not parse malformed JSON: \"" + json + "\"");
            try {
                response = new JSONObject();
                response.put("result", "failed");
                response.put("data", strr);
                response.put("error", ex.getMessage());
            } catch (Exception exx) {
            }
        }
        return response;
    }
}
