package com.example.fetchrewardsexercise.data;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/*
Repository pattern  contract interface for interacting with http server
 */
public interface ItemRepository {
    @GET("hiring.json")
    Call<List<Item>> listItems();
}
