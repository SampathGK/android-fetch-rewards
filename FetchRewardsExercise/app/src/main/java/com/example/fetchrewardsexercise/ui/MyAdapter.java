package com.example.fetchrewardsexercise.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fetchrewardsexercise.R;
import com.example.fetchrewardsexercise.data.Item;

import java.util.ArrayList;
import java.util.LinkedList;

/*
Custom Adapter for the recycler view
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Item> itemList;
    private Context context;

    public MyAdapter(ArrayList<Item> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText("ID: " +Integer.toString(itemList.get(position).getId()));
        holder.listId.setText("List ID: "+Integer.toString(itemList.get(position).getListId()));
        holder.name.setText("Name: "+itemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /*
        This class creates a wrapper object around a view that contains the layout for
         an individual item in the list. It also implements the onClickListener so each ViewHolder in the list is clickable.
        It's onclick method will call the onClick method of the RVClickListener defined in
        the main activity.
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView id;
        public TextView listId;
        public TextView name;
        private View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.textView);
            listId = (TextView) itemView.findViewById(R.id.textView2);
            name = (TextView) itemView.findViewById(R.id.textView3);
            this.itemView = itemView;
        }
    }
}

