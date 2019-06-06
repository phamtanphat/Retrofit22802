package khoapham.ptp.phamtanphat.apphoctienganh2802;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity implements ListenData{

    ListView lvTuvung;
    ArrayList<Tuvung> tuvungsfilter = new ArrayList<>();
    TuvungAdapter tuvungAdapter,tuvungAdapterFilter;
    Button btnForm,btnAddWord,btnCancel;
    EditText edtEn,edtVn;
    RelativeLayout relativeFormtrue,relativeFormfalse;
    Spinner spinner;
    String[] mangoption = {"Show_All","Show_Forgot","Show_Memorized"};
    ArrayAdapter spinnerAdapter;
    ListenData listenData;
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
        edtEn = findViewById(R.id.edittextEn);
        edtVn = findViewById(R.id.edittextVn);

        listenData = MainActivity.this;

        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mangoption);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeFormtrue.setVisibility(View.VISIBLE);
                relativeFormfalse.setVisibility(View.GONE);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeFormtrue.setVisibility(View.GONE);
                relativeFormfalse.setVisibility(View.VISIBLE);
            }
        });
        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String en = edtEn.getText().toString().trim();
                String vn = edtVn.getText().toString().trim();
                if (en.length() > 0 && vn.length() > 0){
                 insert(en,vn);
                }else {
                    Toast.makeText(MainActivity.this, "Thêm thông tin đầy đủ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        getWord();

    }
    private void insert(String en , String vn){
        APICallback apiCallback = Responsedata.getData();
        Call<String> callbackInsert = apiCallback.insert(en ,vn);
        callbackInsert.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();
                if (ketqua == null){
                    Toast.makeText(MainActivity.this, "Khong tồn tại từ khóa", Toast.LENGTH_SHORT).show();
                }else{
                    if (ketqua.equals("true")){
                        Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                        getWord();
                        btnCancel.performClick();
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
    private void getWord(){
        APICallback dataapi = Responsedata.getData();
        Call<ArrayList<Tuvung>> callbackTuvung = dataapi.getTuvung();
        callbackTuvung.enqueue(new Callback<ArrayList<Tuvung>>() {
            @Override
            public void onResponse(Call<ArrayList<Tuvung>> call, Response<ArrayList<Tuvung>> response) {
                ArrayList<Tuvung> mangtuvung = response.body();
                if (mangtuvung.size() > 0){
                    tuvungAdapter = new TuvungAdapter(MainActivity.this,android.R.layout.simple_list_item_1,mangtuvung);
                    lvTuvung.setAdapter(tuvungAdapter);
                }
                listenData.onSuccessData(mangtuvung);

            }

            @Override
            public void onFailure(Call<ArrayList<Tuvung>> call, Throwable t) {
                listenData.onFail(t.getMessage());
            }
        });
    }
    @Override
    public void onSuccessData(final ArrayList<Tuvung> mangtuvungs) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tuvungsfilter.clear();
                tuvungAdapterFilter = new TuvungAdapter(MainActivity.this,android.R.layout.simple_list_item_1,tuvungsfilter);
                lvTuvung.setAdapter(tuvungAdapterFilter);
                switch (mangoption[position]){
                    case "Show_All" :
                        tuvungsfilter.addAll(mangtuvungs);
                        tuvungAdapterFilter.notifyDataSetChanged();
                        break;
                    case "Show_Forgot" :
                        for (int i = 0 ; i<mangtuvungs.size() ;i++){
                            if (mangtuvungs.get(i).getIsMemorized().equals("true")){
                                tuvungsfilter.add(mangtuvungs.get(i));
                            }
                        }
                        tuvungAdapterFilter.notifyDataSetChanged();
                        break;
                    case "Show_Memorized" :
                        for (int i = 0 ; i<mangtuvungs.size() ;i++){
                            if (mangtuvungs.get(i).getIsMemorized().equals("false")){
                                tuvungsfilter.add(mangtuvungs.get(i));
                            }
                        }
                        tuvungAdapterFilter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onFail(String error) {

    }
}
