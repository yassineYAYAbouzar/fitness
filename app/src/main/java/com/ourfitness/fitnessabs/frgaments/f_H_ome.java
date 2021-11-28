package com.ourfitness.fitnessabs.frgaments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.ourfitness.fitnessabs.Perform_excrise_activity;
import com.ourfitness.fitnessabs.R;
import com.ourfitness.fitnessabs.helper.fitness_helpers;

import java.util.ArrayList;
import java.util.Random;

public class f_H_ome extends Fragment implements View.OnClickListener {

    ImageView tvletsstart;
    ArrayList<fitness_helpers> yougahelpers = new ArrayList<>();

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
        View inflate = layoutInflater.inflate(R.layout.fragment_home_, viewGroup, false);
        this.tvletsstart = (ImageView) inflate.findViewById(R.id.tv_lets_start);


        this.tvletsstart.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.tv_lets_start) {
            ArrayList arrayList = new ArrayList();
            do {
                boolean z = true;
                int i = getnumber(0, this.yougahelpers.size() - 1);
                int i2 = 0;
                while (true) {
                    if (i2 >= arrayList.size()) {
                        z = false;
                        break;
                    } else if (((Integer) arrayList.get(i2)).intValue() == i) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!z) {
                    arrayList.add(Integer.valueOf(i));
                }
            } while (arrayList.size() != 10);
            ArrayList arrayList2 = new ArrayList();
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                Log.d("TAG", "Randomise Numbers: " + arrayList.get(i3) + "");
                arrayList2.add(this.yougahelpers.get(i3));
            }
            Intent intent = new Intent(getActivity(), Perform_excrise_activity.class);
            intent.putExtra("excerise_name", ((fitness_helpers) arrayList2.get(0)).getYouganame());
            intent.putExtra("excerise_image", ((fitness_helpers) arrayList2.get(0)).getYougaimage());
            intent.putExtra("youga_helpers", arrayList2);
            intent.putExtra("current_position", 0);
            startActivity(intent);
        }
    }

    public int getnumber(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }
}
