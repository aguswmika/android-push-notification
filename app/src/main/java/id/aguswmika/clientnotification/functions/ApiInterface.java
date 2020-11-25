package id.aguswmika.clientnotification.functions;

import id.aguswmika.clientnotification.models.TokenResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/token.php")
    @FormUrlEncoded
    Call<TokenResult> token(@Field("token") String token);
}