package com.example.tpbook.view.Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tpbook.databinding.ActivityLoginBinding;
import com.example.tpbook.databinding.ActivityRegisterBinding;
import com.example.tpbook.model.DTO.ManagedUserVM;
import com.example.tpbook.model.viewmodel.loginViewModel;
import com.example.tpbook.utils.SharedPref;
import com.example.tpbook.view.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    loginViewModel mloginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mloginViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        SharedPref.init(getApplicationContext());


    }

    // sự kiện đăng ký
    public void btnSignin(View view) {
        boolean check = checkData();
        binding.loginProgress.setVisibility(View.VISIBLE);
        if (check) {
            ManagedUserVM userVM = new ManagedUserVM();
            userVM.setEmail(binding.edtEmail.getText().toString());
            userVM.setLangKey("en");
            userVM.setLogin(binding.edtUsename.getText().toString());
            userVM.setPassword(binding.edtPass.getText().toString());
            mloginViewModel.signIn(userVM).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Toast.makeText(RegisterActivity.this, "đăng ký thành công hãy đợi Admin xác nhận cho bạn !", Toast.LENGTH_SHORT).show();
                    binding.loginProgress.setVisibility(View.GONE);
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            });
        }
    }

    private boolean checkData() {
        if (binding.edtEmail.getText().toString().isEmpty() ||
                binding.edtPass.getText().toString().isEmpty() ||
                binding.edtUsename.getText().toString().isEmpty() ||
                binding.edtRePass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nhập đầy đủ ", Toast.LENGTH_SHORT).show();
        }else{
            if (!binding.edtRePass.getText().toString().equals(binding.edtPass.getText().toString())) {
                Toast.makeText(this, "pass phải giống nhau", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


}