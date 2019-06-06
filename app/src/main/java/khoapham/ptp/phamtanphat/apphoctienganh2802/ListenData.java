package khoapham.ptp.phamtanphat.apphoctienganh2802;

import java.util.ArrayList;

import khoapham.ptp.phamtanphat.apphoctienganh2802.API.model.Tuvung;

public interface ListenData {
    void onSuccessData(ArrayList<Tuvung> mangtuvungs);
    void onFail(String error);
}
