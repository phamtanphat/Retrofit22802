package khoapham.ptp.phamtanphat.apphoctienganh2802.API;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;

public class RetrofitInit {

    private static final Retrofit retrofit = null;
    public static Retrofit initRetro(String base_url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                .connectTimeout(10 ,TimeUnit.SECONDS)
                                .writeTimeout(10 , TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true)
                                .readTimeout(10,TimeUnit.SECONDS)
                                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                                .build();



        return retrofit;
    }
}
