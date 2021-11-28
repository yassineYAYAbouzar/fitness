package com.ourfitness.fitnessabs.frgaments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ourfitness.fitnessabs.Perform_excrise_activity;
import com.ourfitness.fitnessabs.R;
import com.ourfitness.fitnessabs.the_app_adapter.list_adap_escersice_ter;
import com.ourfitness.fitnessabs.the_app_adapter.ViewItemclicklistener;
import com.ourfitness.fitnessabs.helper.fitness_helpers;

import java.util.ArrayList;

public class fre_codeExcerise extends Fragment {
    list_adap_escersice_ter adapter;
    
    RecyclerView listview;

    public ArrayList<fitness_helpers> yougahelpers = new ArrayList<>();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
        this.yougahelpers = (ArrayList) bundle.getSerializable("yoga_helper");
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_excerise_, viewGroup, false);
        this.listview = (RecyclerView) inflate.findViewById(R.id.list_view);
        this.listview.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.listview.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation_fall_down));
        this.adapter = new list_adap_escersice_ter(getActivity(), this.yougahelpers);
        this.listview.setAdapter(this.adapter);
        this.adapter.setItemclickListener(new ViewItemclicklistener() {


            @Override
            public void currentprogress(int i, int i2) {
                Log.d("","ssd");
            }

            public void delete(String str, int i) {
                Log.d("", "dsd");
            }

            public void save(String str, String str2, String str3) {
                Log.d("", "dsd");
            }

            public void update(String str, int i, boolean z, String str2) {
                Log.d("", "dsd");
            }

            @Override
            public void viewaction(int i) {
                Log.d("","ssd");
            }


            public void onClick(View view, int i) {
                Intent intent = new Intent(fre_codeExcerise.this.getActivity(), Perform_excrise_activity.class);
                intent.putExtra("excerise_name", ((fitness_helpers) fre_codeExcerise.this.yougahelpers.get(i)).getYouganame());
                intent.putExtra("excerise_image", ((fitness_helpers) fre_codeExcerise.this.yougahelpers.get(i)).getYougaimage());
                intent.putExtra("youga_helpers", fre_codeExcerise.this.yougahelpers);
                intent.putExtra("current_position", i);
                fre_codeExcerise.this.startActivity(intent);
            }
        });
        return inflate;
    }
}
