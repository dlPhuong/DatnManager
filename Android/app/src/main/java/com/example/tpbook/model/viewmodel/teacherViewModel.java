package com.example.tpbook.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpbook.model.response.ReportResponse;
import com.example.tpbook.model.response.TeacherResponse;
import com.example.tpbook.service.APILogin;
import com.example.tpbook.service.APIReport;
import com.example.tpbook.service.APITeacher;
import com.example.tpbook.service.ServiceGenerator;
import com.example.tpbook.utils.Commons;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teacherViewModel extends ViewModel {
    private APITeacher apiReport = ServiceGenerator.createService(APITeacher.class);

   // private APILogin apiLoginWithAuth = ServiceGenerator.createService(APILogin.class, Commons.auth);

    public MutableLiveData<TeacherResponse> getAllTeacher(){
        final MutableLiveData<TeacherResponse> newsData = new MutableLiveData<>();
        apiReport.getAllteacher().enqueue(new Callback<TeacherResponse>() {
            @Override
            public void onResponse(Call<TeacherResponse> call, Response<TeacherResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TeacherResponse> call, Throwable t) {

            }
        });
        return newsData;
    }
}
