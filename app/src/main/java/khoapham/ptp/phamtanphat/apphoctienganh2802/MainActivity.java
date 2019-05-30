package khoapham.ptp.phamtanphat.apphoctienganh2802;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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



    }
    private void getDatademo1(){
        // tao ra connection voi api
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://khoapham.vn/KhoaPhamTraining/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // gui metho len cho link baseURL
        APIcallback apIcallback = retrofit.create(APIcallback.class);


        //tao ra callback hung du lieu tra ve
        Call<Demo1> callbackdemo1 = apIcallback.demo1api();
        callbackdemo1.enqueue(new Callback<Demo1>() {
            @Override
            public void onResponse(Call<Demo1> call, Response<Demo1> response) {
                Demo1 demo1 = response.body();

                Log.d("BBB",demo1.getLogo() );
                Log.d("BBB",demo1.getFanpage() );
                Log.d("BBB",demo1.getMonhoc() );
            }

            @Override
            public void onFailure(Call<Demo1> call, Throwable t) {
                Log.d("BBB", t.getMessage());
            }
        });
    }
}
