package com.example.tpbook.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpbook.databinding.FragmentHomeBinding;
import com.example.tpbook.databinding.FragmentUserBinding;
import com.example.tpbook.model.viewmodel.TopicViewModel;
import com.example.tpbook.model.viewmodel.teacherViewModel;
import com.example.tpbook.utils.Commons;

public class fragment_User  extends Fragment {
    FragmentUserBinding binding;

    public fragment_User() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        setupData();
        saveUser();
        return binding.getRoot();
    }

    private void saveUser() {

    }

    private void setupData() {
        binding.edtname.setText(Commons.student.getName());
        binding.edtaddress.setText(Commons.student.getAddress());
        binding.edtemail.setText(Commons.user.getEmail());
        binding.edtphonenumber.setText(Commons.student.getPhone());
        binding.edtlinkgithub.setText(Commons.student.getLinkGithub());
    }
}
