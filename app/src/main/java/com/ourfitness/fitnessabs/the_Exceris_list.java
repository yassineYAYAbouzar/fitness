package com.ourfitness.fitnessabs;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ourfitness.fitnessabs.the_app_adapter.list_adap_escersice_ter;
import com.ourfitness.fitnessabs.the_app_adapter.ViewItemclicklistener;
import com.ourfitness.fitnessabs.helper.fitness_helpers;
import java.util.ArrayList;

public class the_Exceris_list extends AppCompatActivity {
     list_adap_escersice_ter adapter;
     ImageView exitbtn;
     TextView headertitle;
     RecyclerView listview;

     ArrayList<fitness_helpers> yougahelpers = new ArrayList<>();


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_lists_exersi);
        this.yougahelpers = (ArrayList) getIntent().getSerializableExtra("youga_helpers");
        this.exitbtn = (ImageView) findViewById(R.id.exit_btn);
        this.listview = (RecyclerView) findViewById(R.id.list_view);
        this.headertitle = (TextView) findViewById(R.id.header_title);
        this.headertitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/baloo_regular.ttf"));
        this.listview.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new list_adap_escersice_ter(this, this.yougahelpers);
        this.listview.setAdapter(this.adapter);
        this.exitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                the_Exceris_list.this.finish();
            }
        });
        this.adapter.setItemclickListener(new ViewItemclicklistener() {
            public void currentprogress(int i, int i2) {
                Log.d("","ssd");
            }



            public void delete(String str, int i) {
                Log.d("","sd");
            }

            public void save(String str, String str2, String str3) {
                Log.d("","sd");
            }

            public void update(String str, int i, boolean z, String str2) {
                Log.d("","sd");
            }
            public void viewaction(int i) {
                Log.d("","ssd");
            }



            public void onClick(View view, int i) {
                Intent intent = new Intent(the_Exceris_list.this, Perform_excrise_activity.class);
                intent.putExtra("excerise_name", ((fitness_helpers) the_Exceris_list.this.yougahelpers.get(i)).getYouganame());
                intent.putExtra("excerise_image", ((fitness_helpers) the_Exceris_list.this.yougahelpers.get(i)).getYougaimage());
                intent.putExtra("youga_helpers", the_Exceris_list.this.yougahelpers);
                intent.putExtra("current_position", i);
                the_Exceris_list.this.startActivity(intent);
            }
        });
    }
}
