package com.rorysoft.solarmap.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rorysoft.solarmap.R;

// The about fragment is inflated and passed to a container in the Map activity layout
// When clicked, the fragment will display general data on the objective and resources used
// there is an button that will pop the fragment from the stack

public class AboutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView general = (TextView) view.findViewById(R.id.general_about);
        TextView credits = (TextView) view.findViewById(R.id.credits);
        ImageView okButton = (ImageView) view.findViewById(R.id.okButton);

        title.setText(R.string.app_name);
        general.setText(R.string.general_info);
        credits.setText(R.string.credits_info);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }
}
