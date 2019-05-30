package khoapham.ptp.phamtanphat.apphoctienganh2802;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIcallback {
    // dinh nghia ra method ket noi lay thong tin
    @GET("json/tien/demo1.json")
    Call<Demo1> demo1api();
}
