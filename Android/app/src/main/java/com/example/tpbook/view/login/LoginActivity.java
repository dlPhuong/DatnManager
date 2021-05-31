package com.example.tpbook.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpbook.MainActivity;
import com.example.tpbook.databinding.ActivityLoginBinding;
import com.example.tpbook.databinding.MainPageBinding;
import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.model.request.LoginRequest;
import com.example.tpbook.model.response.TokenResponse;
import com.example.tpbook.model.viewmodel.loginViewModel;
import com.example.tpbook.model.viewmodel.teacherViewModel;
import com.example.tpbook.utils.Commons;
import com.example.tpbook.utils.SharedPref;
import com.example.tpbook.view.MainPage.MainPageActivity;
import com.example.tpbook.view.Register.RegisterActivity;

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
        SharedPref.init(getApplicationContext());

        binding.edtUser.setText(""+SharedPref.read(SharedPref.USER,""));
        binding.edtPass.setText(""+SharedPref.read(SharedPref.PASS,""));

        binding.btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest request = new LoginRequest(binding.edtUser.getText().toString(), binding.edtPass.getText().toString(), false);
                mloginViewModel.postLogin(request).observe(LoginActivity.this, new Observer<TokenResponse>() {
                    @Override
                    public void onChanged(TokenResponse tokenResponse) {
                        if (tokenResponse.getIdToken().length() > 10) {
                            Commons.auth = "Bearer " + tokenResponse.getIdToken();
                            SharedPref.write(SharedPref.USER,binding.edtUser.getText().toString());
                            SharedPref.write(SharedPref.PASS,binding.edtPass.getText().toString());

                            startActivity(new Intent(LoginActivity.this, MainPageActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this, "sai tên đăng nhập hoặc mật khẩu !", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }




}