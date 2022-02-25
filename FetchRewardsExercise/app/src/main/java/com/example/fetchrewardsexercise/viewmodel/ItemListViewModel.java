package com.example.fetchrewardsexercise.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.fetchrewardsexercise.data.Item;
import com.example.fetchrewardsexercise.data.ItemRepositoryImpl;
import java.util.List;

/*
View-Model containing item data to be displayed in the recycler-view
 */
public class ItemListViewModel extends AndroidViewModel {
    public MutableLiveData<List<Item>> items;

    public ItemListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Item>> getItems(){
        if(items == null){
            items = new MutableLiveData<>();
            loadItems();
        }
        return items;
    }

    private void loadItems() {
        ItemRepositoryImpl repo = new ItemRepositoryImpl(getApplication().getApplicationContext());
        items = repo.listItems();
    }
}
