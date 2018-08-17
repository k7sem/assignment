package com.k7.android.moment.net;

import com.k7.android.moment.obj.Url;
import com.k7.android.moment.obj.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 *
 * @author Kevin
 * @since 08/17/2018
 *
 *
 */

public interface RequestService {
    /*
     Get User object by JSON format
     */
    @GET("getusers")
    Call<List<User>> requestUserData();

    /*
      Get image urls by user id
     */
    @GET("getimg")
    Call<List<Url>> requestUrlData(@Query("uid") String uid);

}
