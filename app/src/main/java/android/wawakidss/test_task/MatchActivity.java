package android.wawakidss.test_task;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MatchActivity extends AppCompatActivity {

    private static final String TAG = "MatchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Bundle args = getIntent().getExtras();
        String _p_sport = args.get("_p_sport").toString();
        String _p_match_id = args.get("_p_match_id").toString();

        JSONObject jsonMatchParams = new JSONObject();
        try {
            jsonMatchParams.put("_p_sport", _p_sport);
            jsonMatchParams.put("_p_match_id", _p_match_id);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, " failed to parse json");
        }

        Log.d(TAG, jsonMatchParams.toString());

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("proc", "get_match_info");
            jsonObject.put("params", jsonMatchParams.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
