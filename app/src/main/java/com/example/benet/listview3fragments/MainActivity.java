package com.example.benet.listview3fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends FragmentActivity implements  AdapterView.OnItemClickListener {

    public final static String SEND_ITEM="send item";

    private List<ItemModel> data;
    private AdapterListView adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lista=(ListView)findViewById(R.id.list_view);

        rellenar();

        adapter=new AdapterListView(this,data,R.layout.item_layout);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(this);


    }

    public void rellenar(){
        this.data=new ArrayList<ItemModel>();
        String[] titles=getResources().getStringArray(R.array.listaTitulos);
        String[] desc=getResources().getStringArray(R.array.descripciones);
        String[] lugar=getResources().getStringArray(R.array.LugarTitulos);
        String[] fechas=getResources().getStringArray(R.array.fechas);
        String[] img=getResources().getStringArray(R.array.img);

        SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");

        for(int i=0; i<titles.length; i++){

            Date fecha= null;
            try {
                fecha = f.parse(fechas[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            data.add(new ItemModel(titles[i],desc[i],lugar[i],fecha, getResources().getIdentifier(img[i],"mipmap",getPackageName())));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(findViewById(R.id.f_main_detail_land)!=null){//estamos en landscape
            ImageView img=(ImageView)findViewById(R.id.img);
            TextView title=(TextView)findViewById(R.id.txtTitle);
            TextView desc=(TextView)findViewById(R.id.txtDesc);
            TextView lugar=(TextView)findViewById(R.id.txtLugar);
            TextView fecha=(TextView)findViewById(R.id.txtFecha);

            img.setImageResource(data.get(position).getImg());
            title.setText(data.get(position).getTitle());
            desc.setText(data.get(position).getDesc());
            lugar.setText(data.get(position).getDirec());
            fecha.setText(data.get(position).getDate().getDate()+"/"+(data.get(position).getDate().getMonth()+1)+"/"+(data.get(position).getDate().getYear()+1900));

            //no repinta la imagen

        }else {
            ItemModel item_selected = data.get(position);
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(SEND_ITEM, item_selected);
            startActivity(intent);
        }

    }
}
