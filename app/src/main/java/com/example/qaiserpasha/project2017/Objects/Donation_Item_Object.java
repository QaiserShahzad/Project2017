package com.example.qaiserpasha.project2017.Objects;

/**
 * Created by Qaiser on 1/15/2018.
 */

public class Donation_Item_Object {
    String BagId;
    String Items;
    String datte;
    String Addr;
    String Pic;



    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }



    public String getBagId() {
        return BagId;
    }

    public void setBagId(String bagId) {
        BagId = bagId;
    }

    public String getItems() {
        return Items;
    }

    public void setItems(String items) {
        Items = items;
    }

    public String getDatte() {
        return datte;
    }

    public void setDatte(String datte) {
        this.datte = datte;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }
}
