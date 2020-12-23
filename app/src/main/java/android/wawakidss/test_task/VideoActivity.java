package android.wawakidss.test_task;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private static final String TAG = "VideoActivity";
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Log.d(TAG, "VideoActivity was launched");

        Bundle args = getIntent().getExtras();

        videoView = (VideoView)findViewById(R.id.video_view);
        MediaController mediaController = new MediaController(VideoActivity.this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(args.getString("url")));
        videoView.start();
    }
}
