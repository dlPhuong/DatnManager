package com.example.tpbook.model.viewmodel;

import android.text.style.AlignmentSpan;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpbook.model.DTO.ManagedUserVM;
import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.model.data.User;
import com.example.tpbook.model.request.LoginRequest;
import com.example.tpbook.model.response.ReportResponse;
import com.example.tpbook.model.response.TokenResponse;
import com.example.tpbook.service.APILogin;
import com.example.tpbook.service.APIReport;
import com.example.tpbook.service.APITopic;
import com.example.tpbook.service.ServiceGenerator;
import com.example.tpbook.utils.Commons;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class reportViewModel extends ViewModel {
    private APIReport apiReport = ServiceGenerator.createService(APIReport.class);

    private APIReport apiLoginWithAuth = ServiceGenerator.createService(APIReport.class, Commons.auth);


    public MutableLiveData<List<Report>> getReport(){
        final MutableLiveData<List<Report>> newsData = new MutableLiveData<>();
        apiLoginWithAuth.getAllReport().enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<Report> saveReport(Report report){
        final MutableLiveData<Report> newsData = new MutableLiveData<>();
        apiLoginWithAuth.saveReport(report).enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<Report> call, Throwable t) {

            }
        });
        return newsData;
    }

}
