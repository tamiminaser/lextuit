import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static ApiService getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl("http://your-server-url.com")  // Replace with your backend URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit.create(ApiService.class);
    }
}
