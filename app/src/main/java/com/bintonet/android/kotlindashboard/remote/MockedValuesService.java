package com.bintonet.android.kotlindashboard.remote;

import com.bintonet.android.kotlindashboard.model.Dashboard;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by darren.w.wright on 15/01/2018.
 */

public class MockedValuesService {

    private static String BASE_URL =  "https://5lfoiyb0b3.execute-api.us-west-2.amazonaws.com/";

    public interface  MockedValuesAPI{
        @GET("/prod/mockcredit/values")
        Call<Dashboard> getCreditReportInfo();
    }

    public MockedValuesAPI getAPI(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MockedValuesAPI.class);
    }

}
