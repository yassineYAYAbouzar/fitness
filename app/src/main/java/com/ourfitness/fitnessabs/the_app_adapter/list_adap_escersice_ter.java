package com.ourfitness.fitnessabs.the_app_adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ourfitness.fitnessabs.R;
import com.ourfitness.fitnessabs.helper.fitness_helpers;
import java.util.ArrayList;
import java.util.Random;

public class list_adap_escersice_ter extends RecyclerView.Adapter<list_adap_escersice_ter.ViewHolder> {
    private int[] backgrounds = {R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.drawable.ic_baseline_sentiment_satisfied_alt_24};
    private Context context;

    public ViewItemclicklistener itemclickListener;
    private ArrayList<fitness_helpers> yougahelpers;

    public list_adap_escersice_ter(Context context2, ArrayList<fitness_helpers> arrayList) {
        this.context = context2;
        this.yougahelpers = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.excriese_views_li, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.excerisename.setTypeface(Typeface.createFromAsset(this.context.getAssets(), "fonts/baloo_regular.ttf"));
        viewHolder.excerisename.setText(this.yougahelpers.get(i).getYouganame());
        viewHolder.viewbackground.setImageResource(this.backgrounds[generateRandomNumbers(0, 8)]);
        Glide.with(this.context).asBitmap().load(Uri.parse(this.yougahelpers.get(i).getYougaimage())).into(viewHolder.exceriseimage);
    }

    static int generateRandomNumbers(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    public void setItemclickListener(ViewItemclicklistener viewItemclickListener) {
        this.itemclickListener = viewItemclickListener;
    }

    public int getItemCount() {
        return this.yougahelpers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

         ImageView exceriseimage;

         TextView excerisename;

         ImageView viewbackground;

        public ViewHolder(View view) {
            super(view);
            this.exceriseimage = (ImageView) view.findViewById(R.id.excerise_image);
            this.excerisename = (TextView) view.findViewById(R.id.excerise_name);
            this.viewbackground = (ImageView) view.findViewById(R.id.view_background);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            list_adap_escersice_ter.this.itemclickListener.onClick(view, getPosition());
        }

        public boolean onLongClick(View view) {
            list_adap_escersice_ter.this.itemclickListener.onClick(view, getPosition());
            return true;
        }
    }
}
