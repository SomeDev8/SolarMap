package com.rorysoft.solarmap.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.rorysoft.solarmap.Constants;
import com.rorysoft.solarmap.R;

public class ObjFragment extends Fragment {

    private static String name, diameter, mass, distance, description, webLinks;
    private Animation compileAnim;

    // Create new Fragment instance and input each column value into instance and
    // return to call

    public static ObjFragment newInstance(String[] values, int imagePath, String url) {
        ObjFragment objFragment = new ObjFragment();
        Bundle args = new Bundle();

        name = values[0];
        diameter = values[1];
        mass = values[2];
        distance = values[3];
        description = values[4];
        args.putString(Constants.NAME_KEY, name);
        args.putString(Constants.DIAMETER_KEY, diameter);
        args.putString(Constants.MASS_KEY, mass);
        args.putString(Constants.DISTANCE_KEY, distance);
        args.putString(Constants.DESCRIPTION_KEY, description);
        args.putInt(Constants.IMAGE_KEY, imagePath);
        args.putString(Constants.WEB_LINK, url);

        objFragment.setArguments(args);
        return objFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_objdetail, container, false);

        final TextView objectName = (TextView) view.findViewById(R.id.objectName);
        final TextView objectDiameter = (TextView) view.findViewById(R.id.objectDiameter);
        final TextView objectMass = (TextView) view.findViewById(R.id.objectMass);
        final TextView objectDistance = (TextView) view.findViewById(R.id.objectDistance);
        final TextView objectDescription = (TextView) view.findViewById(R.id.objectDescription);
        final TextView distanceLabel = (TextView) view.findViewById(R.id.distanceLabel);
        final TextView webLinks = (TextView) view.findViewById(R.id.webLinks);
        final ImageView imagePath = (ImageView) view.findViewById(R.id.objectImage);
        final ImageView closeButton = (ImageView) view.findViewById(R.id.button);

        final Bundle args = getArguments();


        // button will animate and close the fragment by popping it from the backstack

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compileAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade);
                compileAnim.setDuration(Constants.ANIMATION_CLOSE_DURATION);

                compileAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        getActivity().onBackPressed();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                closeButton.startAnimation(compileAnim);
            }
        });

        // All data is retrieved and set to views. The distance label is changed for the Moon
        // or removed for the Sun, but it remains default for all other objects in the database.

        String url = args.getString(Constants.WEB_LINK);
        objectName.setText(args.getString(Constants.NAME_KEY));
        objectMass.setText(args.getString(Constants.MASS_KEY));
        objectDistance.setText(args.getString(Constants.DISTANCE_KEY));
        objectDiameter.setText(args.getString(Constants.DIAMETER_KEY));
        imagePath.setImageResource(args.getInt(Constants.IMAGE_KEY));

        if (objectName.getText().toString().equals("Moon")) {
            distanceLabel.setText(getString(R.string.moon_distance));
        }

        if (objectName.getText().toString().equals("Sun")) {
            distanceLabel.setVisibility(View.GONE);

        } else {
            distanceLabel.setVisibility(View.VISIBLE);
        }

        objectDescription.setText(args.getString(Constants.DESCRIPTION_KEY));

        webLinks.setText(Html.fromHtml(url));
        webLinks.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}

