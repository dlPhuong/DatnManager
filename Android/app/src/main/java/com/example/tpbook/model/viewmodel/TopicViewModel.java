package com.example.tpbook.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpbook.model.data.Student;
import com.example.tpbook.model.data.Topic;
import com.example.tpbook.service.APIStudent;
import com.example.tpbook.service.APITopic;
import com.example.tpbook.service.ServiceGenerator;
import com.example.tpbook.utils.Commons;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicViewModel extends ViewModel {
   // private APITeacher apiReport = ServiceGenerator.createService(APITeacher.class);

    private APITopic apiStudent = ServiceGenerator.createService(APITopic.class, Commons.auth);

    public MutableLiveData<List<Topic>> getTopic(){
        final MutableLiveData<List<Topic>> newsData = new MutableLiveData<>();
            apiStudent.getAllTopicStudent().enqueue(new Callback<List<Topic>>() {
                @Override
                public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                    if(response.isSuccessful()){
                        newsData.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Topic>> call, Throwable t) {

                }
            });
        return newsData;
    }

    public MutableLiveData<Topic> saveTopicStudent(Topic topic){
        final MutableLiveData<Topic> newsData = new MutableLiveData<>();
        apiStudent.saveTopicStudent(topic).enqueue(new Callback<Topic>() {
            @Override
            public void onResponse(Call<Topic> call, Response<Topic> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Topic> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }

}
