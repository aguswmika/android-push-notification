package id.aguswmika.clientnotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

import id.aguswmika.clientnotification.functions.ApiClient;
import id.aguswmika.clientnotification.functions.ApiInterface;
import id.aguswmika.clientnotification.models.TokenResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        String token = task.getResult();

                        Log.d("FCM A", token);

                        ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);

                        Call<TokenResult> tokenCall = apiClient.token(token);

                        tokenCall.enqueue(new Callback<TokenResult>() {
                            @Override
                            public void onResponse(Call<TokenResult> call, Response<TokenResult> response) {
                                assert response.body() != null;
                            }

                            @Override
                            public void onFailure(Call<TokenResult> call, Throwable t) {
                                Log.e("Token error", t.getMessage());
                            }
                        });

                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}