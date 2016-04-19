package com.lynas.androidresttest.service;

import com.lynas.androidresttest.domain.GitUser;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by LynAs on 19-Apr-16
 */
public interface GitHubService {
    @GET("users/lynas")
    Call<GitUser> getUser();


    class Factory{
        public static GitHubService service;

        public static GitHubService getinstance(){
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://api.github.com/")
                        .build();
                service = retrofit.create(GitHubService.class);
                return service;
            } else {
                return service;
            }

        }
    }
}