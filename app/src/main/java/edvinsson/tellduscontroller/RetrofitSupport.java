package edvinsson.tellduscontroller;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSupport {
    public static TelldusService getTelldusService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = createRetrofit(httpClient);
        return retrofit.create(TelldusService.class);
    }

    @NonNull
    private static Retrofit createRetrofit(OkHttpClient.Builder httpClient) {
        return new Retrofit.Builder()
                        .baseUrl("http://192.168.1.2")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();
    }
}
