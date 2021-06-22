package com.example.tpbook.view.MainPage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tpbook.databinding.FragmentReportBinding;
import com.example.tpbook.databinding.FragmentUserBinding;
import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.data.Topic;
import com.example.tpbook.model.viewmodel.StudentViewModel;
import com.example.tpbook.model.viewmodel.reportViewModel;
import com.example.tpbook.utils.Commons;
import com.example.tpbook.view.reportActivity;

import java.util.ArrayList;
import java.util.List;

public class fragment_report extends Fragment implements onEventReportAdapter {
    FragmentReportBinding binding;
    reportViewModel reportViewModel;
    List<Report> topics = new ArrayList<>();

    adapterReport adapterTopic;
    public fragment_report() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReportBinding.inflate(inflater, container, false);
        reportViewModel = new ViewModelProvider(getActivity()).get(reportViewModel.class);
        binding.include.toolbarTitle.setText("Report");
        binding.include.imgback.setVisibility(View.GONE);
        reportViewModel.getReport().observe(getActivity(), new Observer<List<Report>>() {
            @Override
            public void onChanged(List<Report> reports) {
                topics = reports;
                setupRV();
            }
        });
        return binding.getRoot();
    }

    private void setupRV() {
        adapterTopic = new adapterReport(topics, getContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvReport.setAdapter(adapterTopic);
        binding.rvReport.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClickItem(Report report, int position) {
        Commons.report = report;
        startActivity(new Intent(getActivity(), reportActivity.class));
    }
}
