package com.example.benet.listview3fragments;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Benet on 11/02/15.
 */
public class ItemModel implements Serializable {
    private String title, desc, direc;
    private Date date;
    private int img;

    public ItemModel(String title, String desc, String direc, Date date, int img){

        setTitle(title);
        setDesc(desc);
        setDirec(direc);
        setDate(date);
        setImg(img);
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDirec() {
        return direc;
    }

    public Date getDate() {
        return date;
    }
}
