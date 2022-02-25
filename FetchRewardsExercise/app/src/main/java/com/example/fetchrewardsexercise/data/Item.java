package com.example.fetchrewardsexercise.data;

import android.util.Log;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

/*
Entity class which contains data pertaining to an item like id, name, list-id.
 */
@Entity
public class Item implements Comparable<Item>{
    @PrimaryKey(autoGenerate = true)
    public int key;

    @ColumnInfo(name = "nameKey")
    private int nameKey;

    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "listID")
    @SerializedName("listId")
    private int listId;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", listId=" + listId +
                ", name='" + name + '\'' +
                '}';
    }

    public Item(int key, int nameKey, int id, int listId, String name) {
        this.key = key;
        this.id = id;
        this.listId = listId;
        this.name = name;
        this.nameKey = nameKey;
    }

    private void extractNameKey(){
        if(name == "" || name == null)
            this.nameKey = -1;
        else
            try {
                this.nameKey = Integer.parseInt(this.name.replaceAll("[A-Za-z ]", ""));
            }catch(Exception e){
                Log.i("Name Format Exception",name);
            }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getNameKey() {
        extractNameKey();
        return nameKey;
    }

    public void setNameKey(int nameKey) {
        this.nameKey = nameKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    not used*
     */
    @Override
    public int compareTo(Item item) {
        if(this.listId == item.getListId()){
            if(name == null ) name = "";
            if(item.name == null ) item.name = "";
            return name.compareTo(item.getName());
        }
        Integer lid = listId;
        return lid.compareTo(item.getListId());
    }
}
