package com.example.tpbook.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpbook.model.response.ProductResponse;
import com.example.tpbook.service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class productViewModel extends ViewModel {
    private APIproduct apIproduct = RetrofitService.cteateService(APIproduct.class);

    public MutableLiveData<ProductResponse> getAllProduct(){
        final MutableLiveData<ProductResponse> newsData = new MutableLiveData<>();
        apIproduct.getAllProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductResponse> getTopProduct(){
        final MutableLiveData<ProductResponse> newsData = new MutableLiveData<>();
        apIproduct.getTopProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductResponse> getProductForU(){
        final MutableLiveData<ProductResponse> newsData = new MutableLiveData<>();
        apIproduct.getTopProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<List<TypeProduct>> getProductType(){
        final MutableLiveData<List<TypeProduct>> newsData = new MutableLiveData<>();
        apIproduct.getProductType().enqueue(new Callback<List<TypeProduct>>() {
            @Override
            public void onResponse(Call<List<TypeProduct>> call, Response<List<TypeProduct>> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TypeProduct>> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductResponse> getProductWithType(int idType){
        final MutableLiveData<ProductResponse> newsData = new MutableLiveData<>();
            apIproduct.getProductWithType(idType).enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if(response.isSuccessful()){

                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {

                }
            });
        return newsData;
    }

    public MutableLiveData<ProductResponse> searchProduct(String name){ // bước 1
        final MutableLiveData<ProductResponse> newsData = new MutableLiveData<>();
        apIproduct.searchProduct(name).enqueue(new Callback<ProductResponse>() {//cấu hình
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){ // kiểm tra tính toàn vẹn của dữ liệu
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

}
