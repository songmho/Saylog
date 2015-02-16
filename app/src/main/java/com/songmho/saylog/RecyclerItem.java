package com.songmho.saylog;

/**
 * Created by songmho on 2015-02-17.
 */
public class RecyclerItem {
    public String saying;
    public String source;
    public String date;

    public String getSaying(){
        return this.saying;
    }
    public String getSource(){
        return this.source;
    }
    public String getDate(){
        return this.date;
    }
    public RecyclerItem(String saying,String source,String date){
        this.saying=saying;
        this.source=source;
        this.date=date;
    }
}
