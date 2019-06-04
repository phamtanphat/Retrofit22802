package khoapham.ptp.phamtanphat.apphoctienganh2802.API;

import java.util.ArrayList;
import java.util.List;

import khoapham.ptp.phamtanphat.apphoctienganh2802.API.model.Tuvung;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APICallback {

    @GET("tuvung.php")
    Call<ArrayList<Tuvung>> getTuvung();
}
