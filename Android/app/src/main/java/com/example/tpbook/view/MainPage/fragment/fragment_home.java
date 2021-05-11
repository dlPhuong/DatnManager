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

import com.example.tpbook.MainActivity;
import com.example.tpbook.databinding.FragmentHomeBinding;
import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.model.data.Topic;
import com.example.tpbook.model.response.ReportResponse;
import com.example.tpbook.model.response.TeacherResponse;
import com.example.tpbook.model.viewmodel.loginViewModel;
import com.example.tpbook.model.viewmodel.reportViewModel;
import com.example.tpbook.model.viewmodel.teacherViewModel;
import com.example.tpbook.utils.Commons;
import com.example.tpbook.view.TopicActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_home extends Fragment {
    FragmentHomeBinding binding;


    ExpandableListAdapter expandableListAdapter;
    List<Teacher> expandableListTitle;
    HashMap<Teacher, List<Topic>> expandableListDetail;

    reportViewModel reportViewModel;
    teacherViewModel teacherViewModel;

    List<Topic> reportList = new ArrayList<>();
    List<Teacher> teacherList = new ArrayList<>();

    Teacher teacher123 = Teacher.getTeacher();

    public fragment_home() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        reportViewModel = new ViewModelProvider(getActivity()).get(reportViewModel.class);
        teacherViewModel = new ViewModelProvider(getActivity()).get(teacherViewModel.class);
        loadData();
        return binding.getRoot();
    }

    private void loadData() {
        reportViewModel.getAllReport().observe(getActivity(), new Observer<ReportResponse>() {
            @Override
            public void onChanged(ReportResponse reportResponse) {
                reportList.addAll(reportResponse.getList());
                teacherViewModel.getAllTeacher().observe(getActivity(), new Observer<TeacherResponse>() {
                    @Override
                    public void onChanged(TeacherResponse teacherResponse) {
                        teacherList.addAll(teacherResponse.getList());
                        if(teacherList.size()!=0){
                            eventListview();
                        }
                    }
                });

            }
        });



    }

    private void eventListview() {

        expandableListDetail = setupData();
        expandableListTitle = new ArrayList<Teacher>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        binding.expandableListView.setAdapter(expandableListAdapter);

        binding.expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        binding.expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });


        binding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                Commons.topic = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                startActivity(new Intent(getActivity(), TopicActivity.class));
                return false;
            }
        });

    }

    private HashMap<Teacher, List<Topic>> setupData() {
        HashMap<Teacher, List<Topic>> lisresponse = new HashMap<>();
        for (Teacher teacher : teacherList) {
            List<Topic> list = new ArrayList<>();
            for (Topic report : reportList) {
                if (report.getIdTeacher().equalsIgnoreCase(teacher.getId().toString())) {
                    list.add(report);
                }
            }
            lisresponse.put(teacher, list);
        }
        return lisresponse;
    }
}
