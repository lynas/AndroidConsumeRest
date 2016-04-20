package com.lynas.androidresttest.service;

import com.lynas.androidresttest.domain.GitUser;
import com.lynas.androidresttest.domain.json.request.AuthenticationRequest;
import com.lynas.androidresttest.domain.json.response.AuthenticationResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by LynAs on 19-Apr-16
 */
public interface GitHubService {

    String BASE_URL = "http://invmrest-szapp.rhcloud.com/";

    @POST("auth")
    Call<AuthenticationResponse> login(@Body AuthenticationRequest authenticationRequest);


    @GET("users/lynas")
    Call<GitUser> getUser();


    class Factory{
        public static GitHubService service;

        public static GitHubService getinstance(){
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(GitHubService.class);
                return service;
            } else {
                return service;
            }

        }
    }
}