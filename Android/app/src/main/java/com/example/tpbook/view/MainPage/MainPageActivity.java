package com.example.tpbook.view.MainPage;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpbook.R;
import com.example.tpbook.databinding.MainPageBinding;
import com.example.tpbook.model.data.Student;
import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.model.data.User;
import com.example.tpbook.model.viewmodel.StudentViewModel;
import com.example.tpbook.model.viewmodel.loginViewModel;
import com.example.tpbook.model.viewmodel.teacherViewModel;
import com.example.tpbook.utils.BottomNavigationBehavior;
import com.example.tpbook.utils.Commons;
import com.example.tpbook.view.MainPage.fragment.fragment_home;
import com.example.tpbook.view.MainPage.fragment.fragment_User;
import com.example.tpbook.view.MainPage.fragment.fragment_report;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainPageActivity extends AppCompatActivity {
    MainPageBinding binding;
    loginViewModel mloginViewModel;
    teacherViewModel teacherViewModel;
    StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mloginViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        teacherViewModel = new ViewModelProvider(this).get(teacherViewModel.class);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        loadData();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    fragment_home home = new fragment_home();
//                    binding.searchBar.searchBar.setVisibility(View.VISIBLE);
                    loadFragment(home);
                    return true;
                case R.id.nav_report:
                    fragment_report report = new fragment_report();
//                    binding.searchBar.searchBar.setVisibility(View.VISIBLE);
                    loadFragment(report);
                    return true;
                case R.id.nav_user:
                    fragment_User user = new fragment_User();
//                    binding.searchBar.searchBar.setVisibility(View.INVISIBLE);
                    loadFragment(user);
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
                Commons.user = user;
                loadInfo(user.getAuthorities().get(0));
            }
        });
    }

    private void loadInfo(String role) {
        if (role.equals(Commons.USER)) {
            // load thông tin student theo id
            studentViewModel.getInfoStudent().observe(this, new Observer<Student>() {
                @Override
                public void onChanged(Student student) {
                    Commons.student = student;

                    binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    binding.navigation.setSelectedItemId(R.id.nav_home);
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) binding.navigation.getLayoutParams();
                    layoutParams.setBehavior(new BottomNavigationBehavior());
                }
            });
        } else if (role.equals(Commons.TEACHER)) {
            // load thông tin trong bảng teacher
            teacherViewModel.getInfoTeacher(Long.parseLong(Commons.user.getId())).observe(this, new Observer<Teacher>() {
                @Override
                public void onChanged(Teacher teacher) {
                    Commons.teacher = teacher;
                }
            });
        }
    }


}