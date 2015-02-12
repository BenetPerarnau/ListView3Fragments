package com.example.benet.listview3fragments;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(findViewById(R.id.f_detail_list_land)!=null){//estamos en modo landscape
            MainActivity a=new MainActivity();
            a.trabajarLista(1);
        }

        ImageView img=(ImageView)findViewById(R.id.img);
        TextView title=(TextView)findViewById(R.id.txtTitle);
        TextView desc=(TextView)findViewById(R.id.txtDesc);
        TextView lugar=(TextView)findViewById(R.id.txtLugar);
        TextView fecha=(TextView)findViewById(R.id.txtFecha);

        Intent intent=getIntent();
        ItemModel item_recived=(ItemModel)intent.getSerializableExtra(MainActivity.SEND_ITEM);

        img.setImageResource(item_recived.getImg());
        title.setText(item_recived.getTitle());
        desc.setText(item_recived.getDesc());
        lugar.setText(item_recived.getDirec());
        fecha.setText(item_recived.getDate().getDate()+"/"+(item_recived.getDate().getMonth()+1)+"/"+(item_recived.getDate().getYear()+1900));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
}
