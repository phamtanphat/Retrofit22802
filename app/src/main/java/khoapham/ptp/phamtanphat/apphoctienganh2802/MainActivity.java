package khoapham.ptp.phamtanphat.apphoctienganh2802;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import khoapham.ptp.phamtanphat.apphoctienganh2802.API.APICallback;
import khoapham.ptp.phamtanphat.apphoctienganh2802.API.Responsedata;
import khoapham.ptp.phamtanphat.apphoctienganh2802.API.model.Tuvung;
import khoapham.ptp.phamtanphat.apphoctienganh2802.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APICallback dataapi = Responsedata.getData();
        Call<ArrayList<Tuvung>> callbackTuvung = dataapi.getTuvung();
        callbackTuvung.enqueue(new Callback<ArrayList<Tuvung>>() {
            @Override
            public void onResponse(Call<ArrayList<Tuvung>> call, Response<ArrayList<Tuvung>> response) {
                ArrayList<Tuvung> mangtuvung = response.body();
                Log.d("BBB",mangtuvung.size()  + " ");

            }

            @Override
            public void onFailure(Call<ArrayList<Tuvung>> call, Throwable t) {

            }
        });


    }

}
