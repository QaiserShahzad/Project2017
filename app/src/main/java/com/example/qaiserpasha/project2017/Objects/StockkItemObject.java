package com.example.qaiserpasha.project2017.Objects;

import android.content.ClipData;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Qaiser Pasha on 10/10/2017.
 */

public class StockkItemObject {
    String ClothId;
    String Gender;
    String Age;
    String Season;
    String Type;
    String Color;
    String Size;
    String BagId;
    String Qty;
    String Pic;


    public StockkItemObject()
    {

    }




    public String getPic()
    {
        return Pic;
    }
    public void setPic(String pic)
    {
      this.Pic = pic;
    }
    public String getClothId()
    {
        return ClothId;
    }
    public String getQty()
    {
        return Qty;
    }
    public String getGender()
    {
        return Gender;
    }
    public String getAge()
    {
        return Age;
    }
    public String getSeason()
    {
        return Season;
    }
    public String getType()
    {
        return Type;
    }
    public String getSize()
    {
        return Size;
    }
    public String getColor()
    {
        return Color;
    }
    public String getBagId()
    {
        return BagId;
    }




    public void setClothId(String clothId)
    {
        this.ClothId=clothId;
    }
    public void setQty(String qty)
    {
        this.Qty=qty;
    }
    public void setGender(String gender)
    {
           this.Gender=gender;
    }
    public void setAge(String age)
    {
      this.Age=age;
    }
    public void setSeason(String season)
    {
         this.Season=season;
    }
    public void  setType(String type)
    {
     this.Type=type;
    }
    public void  setSize(String size)
    {
        this.Size=size;
    }
    public void  setColor(String color)
    {
        this.Color=color;
    }
    public void  setBagId(String bagId)
    {
        this.BagId=bagId;
    }

}
