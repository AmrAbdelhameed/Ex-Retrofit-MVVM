package com.example.ex_retrofit_mvvm.network;

import com.example.ex_retrofit_mvvm.utils.AppManger;

public class ApiUtils {
    public static APIService getAPIService() {
        return RetrofitClient.getClient(AppManger.BaseURL).create(APIService.class);
    }
}