package com.example.tpbook.view.MainPage;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpbook.databinding.MainPageBinding;
import com.example.tpbook.model.data.User;
import com.example.tpbook.model.viewmodel.loginViewModel;


public class MainPageActivity extends AppCompatActivity {
    MainPageBinding binding;
    loginViewModel mloginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mloginViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        loadData();
    }

    public void loadData(){
        mloginViewModel.getUserData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Toast.makeText(MainPageActivity.this, ""+user.getFirstName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}