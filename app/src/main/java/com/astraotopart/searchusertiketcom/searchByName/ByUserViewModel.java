package com.astraotopart.searchusertiketcom.searchByName;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Kashif on 9/27/2019.
 */
public class ByUserViewModel extends ViewModel {

    private ByUserRepo userRepo;
    private MutableLiveData<ByUserResponse> resBodyMutableLiveData;

    public ByUserViewModel(){
        userRepo = new ByUserRepo();
    }

    public LiveData<ByUserResponse> reqByUser(String url) {
        if(resBodyMutableLiveData==null){
            resBodyMutableLiveData = userRepo.requestByUser(url);
        }
        return resBodyMutableLiveData;
    }


}
