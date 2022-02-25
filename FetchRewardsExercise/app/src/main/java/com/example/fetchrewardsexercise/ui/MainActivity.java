package com.example.fetchrewardsexercise.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.fetchrewardsexercise.R;
import com.example.fetchrewardsexercise.data.Item;
import com.example.fetchrewardsexercise.viewmodel.ItemListViewModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView itemView = findViewById(R.id.recycler_view);
        ItemListViewModel model = new ViewModelProvider(this).get(ItemListViewModel.class);
        //observe for changes in the view-model containing list of items
        model.getItems().observe(this, items -> {
            // update UI
            MyAdapter adapter = new MyAdapter((ArrayList<Item>) items);
            itemView.setHasFixedSize(true);
            itemView.setAdapter(adapter);
            itemView.setLayoutManager(new LinearLayoutManager(this));
        });
    }
}