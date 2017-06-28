package mx.ipn.escom.retrofit2tuto1.interfaces;

import java.util.List;

import mx.ipn.escom.retrofit2tuto1.data.RetrofitPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Jorge on 28/06/2017.
 */

public interface RetrofitInterface {

    @GET("usersFake")
    Call<List<RetrofitPOJO>> getUsersGet();

    @POST("usersFake")
    Call<List<RetrofitPOJO>> getUsersPost();

}
