package com.ourfitness.fitnessabs.the_app_adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ourfitness.fitnessabs.R;
import com.ourfitness.fitnessabs.helper.Sett_ings_helper;
import java.util.ArrayList;

public class blan_settings_adapters extends RecyclerView.Adapter<blan_settings_adapters.ViewHolder> {
    private Context context;

     ViewItemclicklistener itemclickListener;
    private ArrayList<Sett_ings_helper> settinghelpers;

    public blan_settings_adapters(Context context2, ArrayList<Sett_ings_helper> arrayList) {
        this.context = context2;
        this.settinghelpers = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activ_view_settin, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.settingname.setText(this.settinghelpers.get(i).getMenuname());
        viewHolder.settingname.setTypeface(Typeface.createFromAsset(this.context.getAssets(), "fonts/baloo_regular.ttf"));
        Glide.with(this.context).asBitmap().load(Integer.valueOf(this.settinghelpers.get(i).getMenuicon())).into(viewHolder.imageicon);
    }

    public void setItemclickListener(ViewItemclicklistener viewItemclickListener) {
        this.itemclickListener = viewItemclickListener;
    }

    public int getItemCount() {
        return this.settinghelpers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

         ImageView imageicon;

         TextView settingname;

        public ViewHolder(View view) {
            super(view);
            this.imageicon = (ImageView) view.findViewById(R.id.image_icon);
            this.settingname = (TextView) view.findViewById(R.id.setting_name);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            blan_settings_adapters.this.itemclickListener.onClick(view, getPosition());
        }

        public boolean onLongClick(View view) {
            blan_settings_adapters.this.itemclickListener.onClick(view, getPosition());
            return true;
        }
    }
}
