package com.example.tpbook.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpbook.model.data.Student;
import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.service.APIStudent;
import com.example.tpbook.service.APITeacher;
import com.example.tpbook.service.ServiceGenerator;
import com.example.tpbook.utils.Commons;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentViewModel extends ViewModel {
   // private APITeacher apiReport = ServiceGenerator.createService(APITeacher.class);

    private APIStudent apiStudent = ServiceGenerator.createService(APIStudent.class, Commons.auth);

    public MutableLiveData<Student> getInfoStudent(){
        final MutableLiveData<Student> newsData = new MutableLiveData<>();
        apiStudent.getInfoStudent().enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<Student> saveStudent(Student student){
        MutableLiveData<Student> newsData = new MutableLiveData<>();
        apiStudent.saveStudent(student).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

            }
        });
        return newsData;
    }
}
