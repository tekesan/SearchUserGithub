package com.astraotopart.searchusertiketcom.searchUser;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Kashif on 9/27/2019.
 */
public class UserViewModel extends ViewModel {

    private UserRepo userRepo;
    private MutableLiveData<List<UserResponse>> resBodyMutableLiveData;

    public UserViewModel(){
        userRepo = new UserRepo();
    }

    public LiveData<List<UserResponse>> reqAllUsers() {
        if(resBodyMutableLiveData==null){
            resBodyMutableLiveData = userRepo.requestAllUser();
        }
        return resBodyMutableLiveData;
    }


}
