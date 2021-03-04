package com.example.tpbook.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpbook.databinding.ActivityLoginBinding;
import com.example.tpbook.model.request.LoginRequest;
import com.example.tpbook.model.response.TokenResponse;
import com.example.tpbook.model.viewmodel.loginViewModel;
import com.example.tpbook.utils.Commons;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    loginViewModel mloginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mloginViewModel = new ViewModelProvider(this).get(loginViewModel.class);

        LoginRequest request = new LoginRequest("admin", "admin", false);
        mloginViewModel.postLogin(request).observe(this, new Observer<TokenResponse>() {
            @Override
            public void onChanged(TokenResponse tokenResponse) {
                Commons.auth ="Bearer " +tokenResponse.getIdToken();
               // startActivity(new Intent(LoginActivity.this, MainPageActivity.class));
            }
        });

    }



}