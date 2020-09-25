package com.astraotopart.searchusertiketcom.apiHelper;


import com.astraotopart.searchusertiketcom.searchByName.ByUserResponse;
import com.astraotopart.searchusertiketcom.searchUser.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface BaseApiService {

    @Headers({
            "Accept: application/vnd.github.v3+json",
    })
    @GET("/users")
    Call<List<UserResponse>> getAllUsers();

    @Headers({
            "Accept: application/vnd.github.v3+json",
    })
    @GET
    Call<ByUserResponse> getUserByUname(@Url String url);


}
