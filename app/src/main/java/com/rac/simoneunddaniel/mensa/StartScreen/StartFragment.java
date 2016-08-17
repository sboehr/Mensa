package com.rac.simoneunddaniel.mensa.StartScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rac.simoneunddaniel.mensa.R;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class StartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_startpage, container, false);

        Shimmer shimmer = new Shimmer();
        ShimmerTextView shimmerTextView = (ShimmerTextView) rootView.findViewById(R.id.shimmer_tv);
        shimmer.setDuration(1000)
                .setStartDelay(300)
                .setDirection(Shimmer.ANIMATION_DIRECTION_RTL);
//                .setAnimatorListener(new Animator.AnimatorListener(){});
        shimmer.start(shimmerTextView);


        return rootView;
    }
}