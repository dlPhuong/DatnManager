package com.example.tpbook.model.viewmodel;

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
    private APITopic apiReport = ServiceGenerator.createService(APITopic.class);

    private APILogin apiLoginWithAuth = ServiceGenerator.createService(APILogin.class, Commons.auth);

    public MutableLiveData<ReportResponse> getAllReport(){
        final MutableLiveData<ReportResponse> newsData = new MutableLiveData<>();
        apiReport.getAllReport().enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
        return newsData;
    }
}