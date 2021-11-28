package com.ourfitness.fitnessabs;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.load.Key;
import com.ourfitness.fitnessabs.database.Favourite_databse;
import com.ourfitness.fitnessabs.frgaments.theBMya_calculsator_e;
import com.ourfitness.fitnessabs.frgaments.fre_codeExcerise;
import com.ourfitness.fitnessabs.frgaments.f_H_ome;
import com.ourfitness.fitnessabs.helper.fitness_helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class fisthom_dashboar extends AppCompatActivity implements View.OnClickListener {
    LinearLayout bannerContainer;
    LinearLayout bmibtn;

    LinearLayout btnsetting;

    int curretlyloadstack = 0;
    Favourite_databse db;
    LinearLayout excerisebtn;
    LinearLayout homebtn;
    TextView tvcalc;
    TextView tvexcerise;
    TextView tvhome;
    ArrayList<fitness_helpers> yougahelpers = new ArrayList<>();
    FrameLayout frameLayout;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_hom_dash);

        this.db = new Favourite_databse(this);
        this.tvhome = (TextView) findViewById(R.id.tv_home);
        this.bannerContainer = (LinearLayout) findViewById(R.id.bannerContainer);
        this.frameLayout = (FrameLayout) findViewById(R.id.f2_adplaceholderr);
        this.tvexcerise = (TextView) findViewById(R.id.tv_excerise);
        this.tvcalc = (TextView) findViewById(R.id.tv_calc);
        this.homebtn = (LinearLayout) findViewById(R.id.home_btn);
        this.excerisebtn = (LinearLayout) findViewById(R.id.excerise_btn);
        this.bmibtn = (LinearLayout) findViewById(R.id.bmi_btn);

        this.btnsetting = (LinearLayout) findViewById(R.id.btn_setting);


        this.homebtn.setOnClickListener(this);
        this.excerisebtn.setOnClickListener(this);
        this.bmibtn.setOnClickListener(this);
        this.btnsetting.setOnClickListener(this);

        fiilyougahelper();
        this.homebtn.performClick();
        setfonts();

        Main_application.loadBanner(this.bannerContainer, this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bmi_btn:
                replacefragment(new theBMya_calculsator_e());
                this.curretlyloadstack = 2;
                return;
            case R.id.btn_setting:
                startActivity(new Intent(this, fist_settings_activity.class));
                return;


            case R.id.excerise_btn:
                Bundle bundle = new Bundle();
                bundle.putSerializable("yoga_helper", this.yougahelpers);
                fre_codeExcerise exceriseF = new fre_codeExcerise();
                exceriseF.setArguments(bundle);
                replacefragment(exceriseF);
                this.curretlyloadstack = 1;
                return;
            case R.id.home_btn:
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("yoga_helper", this.yougahelpers);
                f_H_ome homeF = new f_H_ome();
                homeF.setArguments(bundle2);
                replacefragment(homeF);
                this.curretlyloadstack = 0;
                return;
            default:
                return;
        }
    }




    public void fiilyougahelper() {
        try {
            JSONArray optJSONArray = new JSONObject(loadJSONFromAsset()).optJSONArray("steps");
            for (int i = 0; i < optJSONArray.length(); i++) {
                fitness_helpers yougahelper = new fitness_helpers();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                String optString = optJSONObject.optString("excerise_name");
                String optString2 = optJSONObject.optString("instruction");
                yougahelper.setYouganame(optString);
                yougahelper.setInstruction(optString2);
                yougahelper.setId(optJSONObject.optString(Favourite_databse.ID));
                yougahelper.setYougaimage("file:///android_asset/" + optJSONObject.optString("image") + ".gif");
                if (!this.db.isinserted(optJSONObject.optString(Favourite_databse.ID))) {
                    this.db.insertdata("0", optJSONObject.optString(Favourite_databse.ID));
                }
                this.yougahelpers.add(yougahelper);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        try {
            InputStream open = getAssets().open("excerices.txt");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, Key.STRING_CHARSET_NAME);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void replacefragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    public void setfonts() {
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "fonts/BubbleBobble.ttf");
        this.tvhome.setTypeface(createFromAsset);
        this.tvexcerise.setTypeface(createFromAsset);
        this.tvcalc.setTypeface(createFromAsset);
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.curretlyloadstack == 0) {
            } else {
                this.homebtn.performClick();
                return false;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }


}
