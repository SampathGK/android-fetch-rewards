package com.example.fetchrewardsexercise.data;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.room.Room;

import com.example.fetchrewardsexercise.data.db.AppDatabase;
import com.example.fetchrewardsexercise.data.db.ItemDao;
import com.example.fetchrewardsexercise.ui.MainActivity;
import com.example.fetchrewardsexercise.viewmodel.ItemListViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
Repository pattern class which provides implementation of api requests using Retrofit library
 */
public class ItemRepositoryImpl{
    private ItemRepository repository;
    private AppDatabase db;
    private Context context;

    public ItemRepositoryImpl(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repository = retrofit.create(ItemRepository.class);

        this.context = context;

        db = Room.databaseBuilder(context, AppDatabase.class, "db").build();
    }

    public MutableLiveData<List<Item>> listItems(){
        Call<List<Item>> listItemsCall = repository.listItems();
        final MutableLiveData<List<Item>>[] items = new MutableLiveData[]{new MutableLiveData<>()};
        listItemsCall.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful()) {
                    Runnable db_access = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ItemDao itemDao = db.itemDao();
                                //check if db already has the data
                                if(itemDao.count() != response.body().size()){
                                    //empty the old data
                                    itemDao.deleteAll();
                                    //insert into db
                                    itemDao.insertAll(response.body());
                                    //return formatted/sorted data from db
                                    items[0].postValue(itemDao.getAll());
                                }
                            }catch (Exception e){
                                Log.i("Model", "Database exception");
                                e.printStackTrace();
                            }
                        }
                    };
                    Thread db_thread = new Thread(db_access);
                    db_thread.start();
                } else{
                    Log.i("Model", "Request denied");
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.i("Model", "Network failure");
            }
        });
        return items[0];
    }
}
