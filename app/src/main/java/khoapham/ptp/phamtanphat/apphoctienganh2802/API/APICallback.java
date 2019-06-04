package khoapham.ptp.phamtanphat.apphoctienganh2802.API;

import java.util.ArrayList;
import java.util.List;

import khoapham.ptp.phamtanphat.apphoctienganh2802.API.model.Tuvung;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICallback {

    @GET("tuvung.php")
    Call<ArrayList<Tuvung>> getTuvung();

    @GET("updatetuvung.php")
    Call<String> toggleMemorized(@Query("id") String id ,@Query("isMemorized") String isMemorized );

    @GET("deletetuvung.php")
    Call<String> delete(@Query("id") String id);

}
