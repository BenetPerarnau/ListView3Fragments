package com.example.benet.listview3fragments;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Benet on 11/02/15.
 */
public class AdapterListView extends BaseAdapter {

    private Activity activity;
    private List<ItemModel> data;
    private int item_layout;
    private LayoutInflater layoutInflater;

    public AdapterListView(Activity activity, List<ItemModel> data, int item_layout) {
        this.activity=activity;
        this.data=data;
        this.item_layout=item_layout;

        this.layoutInflater=(LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){convertView=this.layoutInflater.inflate(R.layout.item_layout,null);}

        ImageView img=(ImageView)convertView.findViewById(R.id.img);
        TextView title=(TextView)convertView.findViewById(R.id.title);
        TextView lugar=(TextView)convertView.findViewById(R.id.lugar);
        TextView fecha=(TextView)convertView.findViewById(R.id.fecha);

        img.setImageResource(data.get(position).getImg());
        title.setText(data.get(position).getTitle());
        lugar.setText(data.get(position).getDirec());
        fecha.setText(data.get(position).getDate().getDate()+"/" +
                ""+(data.get(position).getDate().getMonth()+1)+"/" +
                ""+(data.get(position).getDate().getYear()+1900));

        return convertView;
    }
}
