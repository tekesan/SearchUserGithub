package com.astraotopart.searchusertiketcom.searchUser;

import android.util.Log;

import com.astraotopart.searchusertiketcom.apiHelper.BaseApiService;
import com.astraotopart.searchusertiketcom.commons.MyApplication;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserRepo {

    private final String TAG = getClass().getSimpleName();

        public MutableLiveData<List<UserResponse>> requestAllUser() {
        final MutableLiveData<List<UserResponse>> resBodyMutableLiveData = new MutableLiveData<>();

        BaseApiService apiService =
                MyApplication.getRetrofitClient().create(BaseApiService.class);

        apiService.getAllUsers().enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                Log.e(TAG, "getCurrencyList response="+response.body());

                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestHolidays response.size="+response.body());
                    resBodyMutableLiveData.setValue(response.body());
                }else{
                    resBodyMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.e(TAG, "postRegister onFailure" + t);
            }
        });

        return resBodyMutableLiveData;
    }

}
