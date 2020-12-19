package android.wawakidss.test_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {

    private static String TAG = "MenuActivity";
    private EditText sportId;
    private EditText matchId;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Log.d(TAG, "onCreate was called");

        sportId = (EditText)findViewById(R.id.input_sport_id);
        matchId = (EditText)findViewById(R.id.input_match_id);

        searchButton = (Button)findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MatchActivity.class);
                intent.putExtra("_p_sport", sportId.getText().toString());
                Log.d(TAG, sportId.getText().toString() + " put in intent as sport _p_sport");
                intent.putExtra("_p_match_id", matchId.getText().toString());
                Log.d(TAG, matchId.getText().toString() + " put in intent as sport _p_match_id");
                startActivity(intent);
            }
        });
    }
}