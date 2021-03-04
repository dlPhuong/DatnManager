package com.example.tpbook.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpbook.model.data.User;
import com.example.tpbook.model.request.LoginRequest;
import com.example.tpbook.model.response.TokenResponse;
import com.example.tpbook.service.APILogin;
import com.example.tpbook.service.ServiceGenerator;
import com.example.tpbook.utils.Commons;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginViewModel extends ViewModel {
    private APILogin apiLogin = ServiceGenerator.createService(APILogin.class);
    private APILogin apiLoginWithAuth = ServiceGenerator.createService(APILogin.class, Commons.auth);

    public MutableLiveData<TokenResponse> postLogin(LoginRequest loginRequest){
        final MutableLiveData<TokenResponse> newsData = new MutableLiveData<>();
        apiLogin.authentication(loginRequest).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {

            }
        });
        return newsData;
    }


    public MutableLiveData<User> getUserData(){
        final MutableLiveData<User> newsData = new MutableLiveData<>();
       apiLoginWithAuth.account().enqueue(new Callback<User>() {
           @Override
           public void onResponse(Call<User> call, Response<User> response) {
               if(response.isSuccessful()){
                   newsData.setValue(response.body());
               }
           }

           @Override
           public void onFailure(Call<User> call, Throwable t) {

           }
       });
        return newsData;
    }
}
