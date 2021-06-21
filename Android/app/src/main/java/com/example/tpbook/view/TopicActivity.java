package com.example.tpbook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tpbook.databinding.ActivityRegisterBinding;
import com.example.tpbook.databinding.ActivityTopicBinding;
import com.example.tpbook.model.data.Topic;
import com.example.tpbook.model.viewmodel.TopicViewModel;
import com.example.tpbook.model.viewmodel.reportViewModel;
import com.example.tpbook.utils.Commons;
import com.example.tpbook.view.MainPage.MainPageActivity;

import java.util.ConcurrentModificationException;

public class TopicActivity extends AppCompatActivity {
        ActivityTopicBinding binding;
        TopicViewModel topicViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topicViewModel = new ViewModelProvider(TopicActivity.this).get(TopicViewModel.class);
        binding = ActivityTopicBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.customToolbar.toolbarTitle.setText("Đề tài");
        binding.customToolbar.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TopicActivity.this, MainPageActivity.class));
            }
        });

        binding.btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(Commons.teacher);
                Commons.topic.setIdStudent(""+Commons.student.getId());
                topicViewModel.saveTopicStudent(Commons.topic).observe(TopicActivity.this, new Observer<Topic>() {
                    @Override
                    public void onChanged(Topic topic) {
                        if(topic!=null){
                            binding.btnChoose.setVisibility(View.GONE);
                            Toast.makeText(TopicActivity.this, "đã chọn đề tài ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TopicActivity.this, "Bạn đã đăng ký đề tài rồi !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        setupData();
    }

    private void setupData() {
        System.out.println(Commons.teacher); // null
        binding.txtnameTopic.setText("Tên đề tài: "+Commons.topic.getTopicName());
        binding.txtDescription.setText("Mô tả: "+Commons.topic.getDescription());
        binding.txtstatus.setText("Trạng thái: "+Commons.topic.getStatus());

        if(Commons.student.getIdTopic()!=null){
            binding.btnChoose.setVisibility(View.GONE);
        }
    }
}