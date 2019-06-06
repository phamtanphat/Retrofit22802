package khoapham.ptp.phamtanphat.apphoctienganh2802.API;

import java.util.ArrayList;
import java.util.List;

import khoapham.ptp.phamtanphat.apphoctienganh2802.API.model.Tuvung;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APICallback {

    @GET("tuvung.php")
    Call<ArrayList<Tuvung>> getTuvung();

    @GET("updatetuvung.php")
    Call<String> toggleMemorized(@Query("id") String id ,@Query("isMemorized") String isMemorized );

    @GET("deletetuvung.php")
    Call<String> delete(@Query("id") String id);

    @FormUrlEncoded
    @POST("inserttuvung.php")
    Call<String> insert(@Field("en") String en , @Field("vn") String vn);
}
