package com.astraotopart.searchusertiketcom.searchByName;

import android.util.Log;

import com.astraotopart.searchusertiketcom.apiHelper.BaseApiService;
import com.astraotopart.searchusertiketcom.commons.MyApplication;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ByUserRepo {

    private final String TAG = getClass().getSimpleName();

        public MutableLiveData<ByUserResponse> requestByUser(String url) {
        final MutableLiveData<ByUserResponse> resBodyMutableLiveData = new MutableLiveData<>();

        BaseApiService apiService =
                MyApplication.getRetrofitClient().create(BaseApiService.class);

        apiService.getUserByUname("search/users?q="+url).enqueue(new Callback<ByUserResponse>() {
            @Override
            public void onResponse(Call<ByUserResponse> call, Response<ByUserResponse> response) {
                Log.e(TAG, "getCurrencyList response="+response.body());

                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestHolidays response.size="+response.body());
                    resBodyMutableLiveData.setValue(response.body());
                }else{
                    resBodyMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ByUserResponse> call, Throwable t) {
                Log.e(TAG, "postRegister onFailure" + t);
            }
        });

        return resBodyMutableLiveData;
    }

}
