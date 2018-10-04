package com.example.qaiserpasha.project2017.SQLite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Qaiser on 1/22/2018.
 */
@Entity
public class ClothItem {
    @PrimaryKey
    public int clotheItemID;
    @ColumnInfo(name = "quantity")
    public int quantity;
    @ColumnInfo(name="pic")
    public  String pic;
    @ColumnInfo(name ="Dname")
    public String Dname;

















    public int getClotheItemID() {
        return clotheItemID;
    }

    public void setClotheItemID(int clotheItemID) {
        this.clotheItemID = clotheItemID;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }
}
