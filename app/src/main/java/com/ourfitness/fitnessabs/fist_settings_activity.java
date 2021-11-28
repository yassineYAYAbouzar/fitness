package com.ourfitness.fitnessabs;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ourfitness.fitnessabs.the_app_adapter.blan_settings_adapters;
import com.ourfitness.fitnessabs.the_app_adapter.ViewItemclicklistener;
import com.ourfitness.fitnessabs.helper.Sett_ings_helper;

import java.util.ArrayList;

public class fist_settings_activity extends AppCompatActivity {
     blan_settings_adapters adapter;
    FrameLayout bannerContainer;

     int count = 0;
     ImageView exitbtn;
     TextView headertitle;
     ArrayList<Sett_ings_helper> settinghelpers = new ArrayList<>();
     RecyclerView settinglist;

    static int access008(fist_settings_activity settingactivity) {
        int i = settingactivity.count;
        settingactivity.count = i + 1;
        return i;
    }

    static int access010(fist_settings_activity settingactivity) {
        int i = settingactivity.count;
        settingactivity.count = i - 1;
        return i;
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_the_setting);


        this.exitbtn = (ImageView) findViewById(R.id.exit_btn);
        this.headertitle = (TextView) findViewById(R.id.header_title);
        this.bannerContainer = (FrameLayout) findViewById(R.id.f2_adplaceholderr);
        this.headertitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/baloo_regular.ttf"));
        this.settinglist = (RecyclerView) findViewById(R.id.setting_list);
        fillsetting();
        this.settinglist.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        this.adapter = new blan_settings_adapters(this, this.settinghelpers);
        this.settinglist.setAdapter(this.adapter);
        this.exitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.adapter.setItemclickListener(new ViewItemclicklistener() {


            @Override
            public void currentprogress(int i, int i2) {
                Log.d("","ssd");
            }

            public void delete(String str, int i) {
                Log.d("", "sd");
            }

            public void save(String str, String str2, String str3) {
                Log.d("", "sd");
            }

            public void update(String str, int i, boolean z, String str2) {
                Log.d("", "sd");
            }

            @Override
            public void viewaction(int i) {
                Log.d("","ssd");
            }


            public void onClick(View view, int i) {
                if (i == 0) {
                    fist_settings_activity.this.startActivity(new Intent(fist_settings_activity.this, Static_first_activity.class));
                } else if (i == 5) {
                    fist_settings_activity.this.ratethisapp();
                }
            }
        });
        Main_application.refreshAd(this.bannerContainer, fist_settings_activity.this);
    }

    public void ratethisapp() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    public void fillsetting() {
        Sett_ings_helper settinghelper = new Sett_ings_helper();
        settinghelper.setMenuicon(R.drawable.ic_baseline_privacy_tip_24);
        settinghelper.setMenuname(getString(R.string.privecy));
        this.settinghelpers.add(settinghelper);

        Sett_ings_helper settinghelper6 = new Sett_ings_helper();
        settinghelper6.setMenuicon(R.drawable.ic_baseline_star_rate_24);
        settinghelper6.setMenuname(getString(R.string.rateapp));
        this.settinghelpers.add(settinghelper6);
    }



}
