package com.example.tpbook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tpbook.databinding.ActivityReportBinding;
import com.example.tpbook.databinding.ActivityTopicBinding;
import com.example.tpbook.databinding.MainPageBinding;
import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.viewmodel.TopicViewModel;
import com.example.tpbook.model.viewmodel.reportViewModel;
import com.example.tpbook.utils.Commons;
import com.example.tpbook.view.MainPage.MainPageActivity;

public class reportActivity extends AppCompatActivity {
    ActivityReportBinding binding;
    reportViewModel reportViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reportViewModel = new ViewModelProvider(reportActivity.this).get(reportViewModel.class);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.customToolbar.toolbarTitle.setText("Báo cáo");
        binding.customToolbar.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(reportActivity.this, MainPageActivity.class));
            }
        });

        setupData();
        updateReport();

    }

    private void updateReport() {


        binding.btnUpdtae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commons.report.setStatus(binding.txtStatus.getText().toString());
                Commons.report.setProcess(binding.txtProcess.getText().toString());
                reportViewModel.saveReport(Commons.report).observe(reportActivity.this, new Observer<Report>() {
                    @Override
                    public void onChanged(Report report) {
                        Toast.makeText(reportActivity.this, "UPdate thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void setupData() {
        binding.txtNameReport.setText("Tên báo cáo: "+Commons.report.getNameReport());
        binding.txtDeadline.setText("Hạn chót"+Commons.report.getDeadline());
        binding.txtMissition.setText("Nhiệm vụ: "+Commons.report.getMission());
        binding.txtProcess.setText(Commons.report.getProcess());
        binding.txtStatus.setText(Commons.report.getStatus());

    }
}