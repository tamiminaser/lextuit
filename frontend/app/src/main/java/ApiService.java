import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/interact")
    Call<VideoResponse> reportInteraction(@Body VideoInteraction interaction);
}
