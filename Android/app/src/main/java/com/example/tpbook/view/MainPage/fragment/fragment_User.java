package com.example.tpbook.view.MainPage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.tpbook.databinding.FragmentHomeBinding;
import com.example.tpbook.databinding.FragmentUserBinding;
import com.example.tpbook.model.data.Student;
import com.example.tpbook.model.viewmodel.StudentViewModel;
import com.example.tpbook.model.viewmodel.TopicViewModel;
import com.example.tpbook.model.viewmodel.loginViewModel;
import com.example.tpbook.model.viewmodel.teacherViewModel;
import com.example.tpbook.utils.Commons;

public class fragment_User  extends Fragment {
    FragmentUserBinding binding;
    StudentViewModel studentViewModel;
    public fragment_User() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        studentViewModel = new ViewModelProvider(getActivity()).get(StudentViewModel.class);
        binding.include.toolbarTitle.setText("User");
        binding.include.imgback.setVisibility(View.GONE);
        setupData();
        saveUser();
        return binding.getRoot();
    }

    private void saveUser() {
        binding.btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commons.student.setName(binding.edtname.getText().toString());
                Commons.student.setAddress(binding.edtaddress.getText().toString());
                Commons.student.setPhone(binding.edtphonenumber.getText().toString());
                Commons.student.setLinkGithub(binding.edtlinkgithub.getText().toString());
                studentViewModel.saveStudent(Commons.student).observe(getActivity(), new Observer<Student>() {
                    @Override
                    public void onChanged(Student student) {
                        if(student!=null){
                            Toast.makeText(getActivity(), "update thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void setupData() {
        binding.edtname.setText(Commons.student.getName());
        binding.edtaddress.setText(Commons.student.getAddress());
        binding.edtemail.setText(Commons.user.getEmail());

        binding.edtphonenumber.setText(Commons.student.getPhone());
        binding.edtlinkgithub.setText(Commons.student.getLinkGithub());
        Glide.with(getContext())
                .load(Commons.API_BASE_URL+"images/"+Commons.student.getImage())
                .into(binding.imgProfile);
    }
}
