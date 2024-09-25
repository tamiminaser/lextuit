import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ExoPlayer player;
    private String currentVideoId = "initial_video_id";  // This will be updated dynamically
    private String userId = "user123";  // For example purposes
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ExoPlayer
        player = new ExoPlayer.Builder(this).build();
        setUpPlayer("http://initial_video_url.mp4");  // Load the first video

        findViewById(R.id.likeButton).setOnClickListener(v -> reportInteraction("like"));
        findViewById(R.id.dislikeButton).setOnClickListener(v -> reportInteraction("dislike"));
    }

    private void setUpPlayer(String videoUrl) {
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoUrl));
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    private void reportInteraction(String interactionType) {
        ApiService apiService = ApiClient.getClient();
        VideoInteraction interaction = new VideoInteraction(currentVideoId, userId, interactionType);

        Call<VideoResponse> call = apiService.reportInteraction(interaction);
        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (response.isSuccessful()) {
                    String nextVideoUrl = response.body().getNextVideoUrl();
                    if (nextVideoUrl != null) {
                        setUpPlayer(nextVideoUrl);  // Play the next video
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
