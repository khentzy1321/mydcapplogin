package com.example.mydcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydcapp.api.RequestPlaceHolder;
import com.example.mydcapp.api.RetrofitBuilder;
import com.example.mydcapp.mine.Login;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyActivityLogin extends AppCompatActivity {

    public EditText username, password;
    public MaterialButton loginbtn, signup;
    public TextView signin, forgotpass, create;

    public RetrofitBuilder retrofitBuilder;
    public RequestPlaceHolder requestPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        signup = findViewById(R.id.signup);
        forgotpass = findViewById(R.id.forgotpass);
        signin = findViewById(R.id.signin);
        create = findViewById(R.id.create);

        retrofitBuilder = new RetrofitBuilder();
        requestPlaceHolder = retrofitBuilder.getRetrofit().create(RequestPlaceHolder.class);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Login> loginCall = requestPlaceHolder.login(new Login(null, username.getText().toString(), null, null, password.getText().toString()));
                loginCall.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if(response.isSuccessful()){
                            Log.e("LOGGING IN ERROR", response.message());
                        }else{
                            if(response.code()== 200){
                                Toast.makeText(MyActivityLogin.this, "Logging in Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(MyActivityLogin.this, "There is an error uppon logging the API", Toast.LENGTH_SHORT).show();
                        Log.e("LOGGING IN ERROR", t.getMessage());
                    }
                });
            }
        });
    }
}