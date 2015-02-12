package com.example.benet.listview3fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends FragmentActivity implements  AdapterView.OnItemClickListener, AbsListView.MultiChoiceModeListener {

    public final static String SEND_ITEM="send item";

    private List<ItemModel> data;
    private AdapterListView adapter;
    private ListView lista;
    private static int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista=(ListView)findViewById(R.id.list_view);
        trabajarLista(0);

    }

    public void trabajarLista(int op){

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
        adapter=new AdapterListView(this,data,R.layout.item_layout);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(this);

        lista.setChoiceMode(lista.CHOICE_MODE_MULTIPLE_MODAL);
        lista.setMultiChoiceModeListener(this);

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

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

        mode.setTitle(data.get(position).getTitle().toString());
        this.position=position;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {

        mode.getMenuInflater().inflate(R.menu.menu_contextual,menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

        switch(item.getItemId()){
            case R.id.edit:
                Toast.makeText(this,"EDIT TITLE "+mode.getTitle(),Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:
                Toast.makeText(this,"DELETE TITLE "+mode.getTitle(),Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }

    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
