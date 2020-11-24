package com.example.daycom;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {


    @GET("/category/{code}")
    Call<ArrayList<Product>>getProducts(@Path("code") int code);

    @GET("/categorys")
    Call<ArrayList<ProductType>>getCategorys();

    @GET("/repair")
    Call<RepairStatus>getRepairStatus(@Query("Surname") String surname, @Query("Code") int  code);
}
