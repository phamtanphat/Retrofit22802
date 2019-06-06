package khoapham.ptp.phamtanphat.apphoctienganh2802;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import khoapham.ptp.phamtanphat.apphoctienganh2802.API.APICallback;
import khoapham.ptp.phamtanphat.apphoctienganh2802.API.Responsedata;
import khoapham.ptp.phamtanphat.apphoctienganh2802.API.model.Tuvung;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TuvungAdapter extends ArrayAdapter<Tuvung> {
    List<Tuvung> tuvungs ;
    public TuvungAdapter(Context context, int resource, List<Tuvung> objects) {
        super(context, resource, objects);
        tuvungs = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView = layoutInflater.inflate(R.layout.dong_tuvung_item,null);

        TextView txtEn = convertView.findViewById(R.id.textviewEn);
        TextView txtVn = convertView.findViewById(R.id.textviewVn);
        Button btnToggle = convertView.findViewById(R.id.buttonToggleWord);
        Button btnRemove = convertView.findViewById(R.id.buttonRemoveWord);

        final Tuvung tuvung = getItem(position);
        txtEn.setText(tuvung.getEn());
        txtVn.setText(tuvung.getIsMemorized().equals("true") ? "----" : tuvung.getVn());
        btnToggle.setText(tuvung.getIsMemorized().equals("true") ? "Forgot" : "isMemorized");
        btnToggle.setBackgroundColor(tuvung.getIsMemorized().equals("true") ? Color.rgb(33,136,56) : Color.rgb(200,35,51));

        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tuvung.getIsMemorized().equals("true")){
                    toggleMemorized(tuvung.getId(),"false");
                }else{
                    toggleMemorized(tuvung.getId(),"true");
                }

            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tuvung.getId().equals("")){
                    deleteWord(tuvung.getId());
                }
            }
        });
        return convertView;
    }
    private void toggleMemorized(String id , String ismemorized){
        APICallback dataapi = Responsedata.getData();
        Call<String> callbackTuvung = dataapi.toggleMemorized(id,ismemorized);
        callbackTuvung.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();
                if (ketqua == null){
                    Toast.makeText(getContext(), "Khong tồn tại từ khóa", Toast.LENGTH_SHORT).show();
                }else{
                    if (ketqua.equals("true")){
                        Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                        reload();
                    }else{
                        Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void reload(){
        tuvungs.clear();
        APICallback dataapi = Responsedata.getData();
        Call<ArrayList<Tuvung>> callbackTuvung = dataapi.getTuvung();
        callbackTuvung.enqueue(new Callback<ArrayList<Tuvung>>() {
            @Override
            public void onResponse(Call<ArrayList<Tuvung>> call, Response<ArrayList<Tuvung>> response) {
                ArrayList<Tuvung> mangtuvung = response.body();
                tuvungs.addAll(mangtuvung);
                notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<Tuvung>> call, Throwable t) {

            }
        });
    }
    private void deleteWord(String id){
        APICallback dataapi = Responsedata.getData();
        Call<String> callbackTuvung = dataapi.delete(id);
        callbackTuvung.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();
                if (ketqua == null){
                    Toast.makeText(getContext(), "Khong tồn tại từ khóa", Toast.LENGTH_SHORT).show();
                }else{
                    if (ketqua.equals("true")){
                        Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                        reload();
                    }else{
                        Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
