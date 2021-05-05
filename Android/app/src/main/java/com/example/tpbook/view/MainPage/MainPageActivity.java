package com.example.tpbook.view.MainPage;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpbook.R;
import com.example.tpbook.databinding.MainPageBinding;
import com.example.tpbook.model.data.User;
import com.example.tpbook.model.viewmodel.loginViewModel;
import com.example.tpbook.view.MainPage.fragment.fragment_home;
import com.google.android.material.bottomnavigation.BottomNavigationView;


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
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ;

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    fragment_home home = new fragment_home();
                    loadFragment(home);
                    return true;
                case R.id.nav_report:
                    return true;
                case R.id.nav_user:
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadData() {
        mloginViewModel.getUserData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Toast.makeText(MainPageActivity.this, "" + user.getFirstName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}