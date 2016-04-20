package com.lynas.androidresttest.service;

import com.lynas.androidresttest.domain.Book;
import com.lynas.androidresttest.domain.json.request.AuthenticationRequest;
import com.lynas.androidresttest.domain.json.response.AuthenticationResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.List;

/**
 * Created by LynAs on 19-Apr-16
 */
public interface GitHubService {

    String BASE_URL = "http://invmrest-szapp.rhcloud.com/";

    @POST("auth")
    Call<AuthenticationResponse> login(@Body AuthenticationRequest authenticationRequest);

    /*@GET("")
    Call<List<Book>> getBookList;*/


    @GET("book/organization_id/1")
    Call<List<Book>> getBookList(@Header("X-Auth-Token") String token);


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