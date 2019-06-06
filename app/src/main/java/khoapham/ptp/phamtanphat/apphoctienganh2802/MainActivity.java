package khoapham.ptp.phamtanphat.apphoctienganh2802;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

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

    ListView lvTuvung;
    ArrayList<Tuvung> tuvungsfilter = new ArrayList<>();
    TuvungAdapter tuvungAdapter,tuvungAdapterFilter;
    Button btnForm,btnAddWord,btnCancel;
    RelativeLayout relativeFormtrue,relativeFormfalse;
    Spinner spinner;
    String[] mangoption = {"Show_All","Show_Forgot","Show_Memorized"};
    ArrayAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTuvung = findViewById(R.id.listviewWord);
        btnForm = findViewById(R.id.buttonForm);
        btnAddWord = findViewById(R.id.buttonAddword);
        btnCancel = findViewById(R.id.buttonCancel);
        relativeFormfalse = findViewById(R.id.relativeFormfalse);
        relativeFormtrue = findViewById(R.id.relativeFormtrue);
        spinner = findViewById(R.id.spinner);

        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mangoption);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


    }
    private void getWord(){
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
    private void toggleMemorized(){
        APICallback dataapi = Responsedata.getData();
        Call<String> callbackTuvung = dataapi.toggleMemorized("5","false");
        callbackTuvung.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();
                if (ketqua == null){
                    Toast.makeText(MainActivity.this, "Khong tồn tại từ khóa", Toast.LENGTH_SHORT).show();
                }else{
                    if (ketqua.equals("true")){
                        Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    private void deleteWord(){
        APICallback dataapi = Responsedata.getData();
        Call<String> callbackTuvung = dataapi.delete("1");
        callbackTuvung.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();
                if (ketqua == null){
                    Toast.makeText(MainActivity.this, "Khong tồn tại từ khóa", Toast.LENGTH_SHORT).show();
                }else{
                    if (ketqua.equals("true")){
                        Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}
