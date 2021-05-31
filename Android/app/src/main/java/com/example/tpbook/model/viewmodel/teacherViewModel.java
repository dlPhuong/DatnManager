package com.example.tpbook.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpbook.model.data.Teacher;
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
   // private APITeacher apiReport = ServiceGenerator.createService(APITeacher.class);

    private APITeacher apiTeacher = ServiceGenerator.createService(APITeacher.class, Commons.auth);

    public MutableLiveData<Teacher> getInfoTeacher(Long idTeacher){
        final MutableLiveData<Teacher> newsData = new MutableLiveData<>();
        apiTeacher.getInfoTeacher(idTeacher).enqueue(new Callback<Teacher>() {
            @Override
            public void onResponse(Call<Teacher> call, Response<Teacher> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Teacher> call, Throwable t) {

            }
        });
        return newsData;
    }
}
