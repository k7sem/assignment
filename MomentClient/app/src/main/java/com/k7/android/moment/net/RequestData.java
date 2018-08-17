package com.k7.android.moment.net;

import android.util.Log;

import com.k7.android.moment.MainActivity;
import com.k7.android.moment.obj.Url;
import com.k7.android.moment.obj.User;
import com.k7.android.moment.obj.UserManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author Kevin
 * @since 08/17/2018
 *
 *
 */

public class RequestData {
    private static RequestData instance = null;

    private RequestCallback callback;

    private RequestData(){}

    public static RequestData getInstance() {
        if (instance == null) {
            instance = new RequestData();
        }
        return instance;
    }

    public void setRequestCallback(RequestCallback cb) {
        callback = cb;
    }

    public void reqData(String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestService request = retrofit.create(RequestService.class);


        Call<List<User>> call = request.requestUserData();


        call.enqueue(new Callback<List<User>>() {


            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
               // Log.d(MainActivity.LOG_FLAG, "" + response.body().size());
                for (User user : response.body()) {
                    UserManager.getInstance().addUser(user);
                    Log.d(MainActivity.LOG_FLAG, "# - " + user.getUsername());
                    Call<List<Url>> callurl = request.requestUrlData(String.valueOf(user.getId()));
                    callurl.enqueue(new Callback<List<Url>>() {
                        @Override
                        public void onResponse(Call<List<Url>> call, Response<List<Url>> response) {
                            for (Url url : response.body()) {
                               Log.d(MainActivity.LOG_FLAG, "# - " + url.getImgurl());
                                UserManager.getInstance().addUrl(url);
                            }
                            if (callback != null) {
                                callback.backUI();
                            }
                        }
                        @Override
                        public void onFailure(Call<List<Url>> call, Throwable throwable) {
                            Log.d(MainActivity.LOG_FLAG, "call req Url data failure - " + throwable.getMessage());
                        }
                    });
                }
            }


            @Override
            public void onFailure(Call<List<User>> call, Throwable throwable) {
                Log.d(MainActivity.LOG_FLAG, "call req User data failure - " + throwable.getMessage());
            }
        });

    }


}
