package com.example.tpbook.view.MainPage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tpbook.MainActivity;
import com.example.tpbook.databinding.FragmentHomeBinding;
import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.model.data.Topic;
import com.example.tpbook.model.response.ReportResponse;
import com.example.tpbook.model.response.TeacherResponse;
import com.example.tpbook.model.viewmodel.TopicViewModel;
import com.example.tpbook.model.viewmodel.loginViewModel;
import com.example.tpbook.model.viewmodel.reportViewModel;
import com.example.tpbook.model.viewmodel.teacherViewModel;
import com.example.tpbook.utils.Commons;
import com.example.tpbook.view.TopicActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_home extends Fragment implements onEventTopicAdapter {
    FragmentHomeBinding binding;

    TopicViewModel topicViewModel;
    teacherViewModel teacherViewModel;

    List<Topic> topics = new ArrayList<>();

    adapterTopic adapterTopic;

    public fragment_home() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        topicViewModel = new ViewModelProvider(getActivity()).get(TopicViewModel.class);
        teacherViewModel = new ViewModelProvider(getActivity()).get(teacherViewModel.class);
        binding.include.toolbarTitle.setText("Home");
        binding.include.imgback.setVisibility(View.GONE);
        laodDataRV();
        return binding.getRoot();
    }

    private void laodDataRV() {
        topicViewModel.getTopic().observe(getActivity(), new Observer<List<Topic>>() {
            @Override
            public void onChanged(List<Topic> topicList) {
                topics = topicList;
                Commons.topicList = topicList;
                setupRV();
                //adapterTopic.notifyDataSetChanged();
            }
        });
    }

    private void setupRV() {
        adapterTopic = new adapterTopic(topics, getContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvTopic.setAdapter(adapterTopic);
        binding.rvTopic.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onClickItem(Topic topic, int position) {
        Commons.topic = topic;
        startActivity(new Intent(getContext(),TopicActivity.class));
        Toast.makeText(getContext(), "hihi " + position, Toast.LENGTH_SHORT).show();
    }
}
