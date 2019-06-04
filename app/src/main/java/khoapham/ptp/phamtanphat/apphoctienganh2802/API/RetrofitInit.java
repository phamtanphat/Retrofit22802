package khoapham.ptp.phamtanphat.apphoctienganh2802.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {
    //base class
    private static Retrofit retrofit = null;
    public final static Retrofit initRetro(String base_url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                .connectTimeout(10 ,TimeUnit.SECONDS)
                                .writeTimeout(10 , TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true)
                                .readTimeout(10,TimeUnit.SECONDS)
                                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                                .build();
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        return retrofit;
    }
}
