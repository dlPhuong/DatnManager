package com.example.tpbook.view.MainPage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpbook.databinding.FragmentReportBinding;
import com.example.tpbook.databinding.FragmentUserBinding;
import com.example.tpbook.model.viewmodel.StudentViewModel;

public class fragment_report extends Fragment {
    FragmentReportBinding binding;

    public fragment_report() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReportBinding.inflate(inflater, container, false);
        //studentViewModel = new ViewModelProvider(getActivity()).get(StudentViewModel.class);
        return binding.getRoot();
    }

}
