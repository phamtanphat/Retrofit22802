package khoapham.ptp.phamtanphat.apphoctienganh2802;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import khoapham.ptp.phamtanphat.apphoctienganh2802.API.model.Tuvung;

public class TuvungAdapter extends ArrayAdapter<Tuvung> {
    public TuvungAdapter(Context context, int resource, List<Tuvung> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView = layoutInflater.inflate(R.layout.dong_tuvung_item,null);

        TextView txtEn = convertView.findViewById(R.id.textviewEn);
        TextView txtVn = convertView.findViewById(R.id.textviewVn);
        Button btnToggle = convertView.findViewById(R.id.buttonToggleWord);
        Button btnRemove = convertView.findViewById(R.id.buttonRemoveWord);

        Tuvung tuvung = getItem(position);
        txtEn.setText(tuvung.getEn());
        txtVn.setText(tuvung.getIsMemorized().equals("true") ? "----" : tuvung.getVn());
        btnToggle.setText(tuvung.getIsMemorized().equals("true") ? "Forgot" : "isMemorized");
        btnToggle.setBackgroundColor(tuvung.getIsMemorized().equals("true") ? Color.rgb(33,136,56) : Color.rgb(200,35,51));

        return convertView;
    }
}
