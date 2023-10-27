package com.c21054601.project.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.c21054601.project.R;
import com.c21054601.project.databinding.FragmentMapListBinding;
import com.c21054601.project.databinding.FragmentMapViewerBinding;

public class MapViewer extends Fragment {

    int id;

    public MapViewer(int id){
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentMapViewerBinding viewerBinding = FragmentMapViewerBinding.inflate(inflater, container, false);
        switch (id) {
            case R.id.map_fragment1:
                viewerBinding.imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.map_one, null));
                break;
            case R.id.map_fragment2:
                viewerBinding.imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.map_two, null));
                break;
            case R.id.map_fragment3:
                viewerBinding.imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.map_three, null));
                break;
            case R.id.map_fragment4:
                viewerBinding.imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.map_four, null));
                break;
            case R.id.map_fragment5:
                viewerBinding.imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.map_five, null));
                break;
            case R.id.map_fragment6:
                viewerBinding.imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.map_six, null));
                break;
            case R.id.map_fragment7:
                viewerBinding.imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.map_seven, null));
                break;
            case R.id.map_fragment8:
                viewerBinding.imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.map_eight, null));
                break;
        }

        return viewerBinding.getRoot();
    }

}