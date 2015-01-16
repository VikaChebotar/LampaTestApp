package example.com.lampatestapp;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by viktoria on 15.01.15.
 */

/*
second page, shows ViewPager with images and descriptions from PhotoTask. Set task description as title of action bar.
 */
public class GalleryFragment extends Fragment {
    View rootView;
    ActionBar actionBar;
    Task task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.gallery, container, false);
        task = getArguments().getParcelable("task");
        ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
        GalleryPagerAdapter adapter = new GalleryPagerAdapter(getActivity(), task.getPhotos());
        pager.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        actionBar.setDisplayHomeAsUpEnabled(true); //this is to enable up navigation
        actionBar.setTitle(task.getTitle());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("task", task);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        actionBar = (getActivity()).getActionBar();
        if (savedInstanceState != null) {
            task = savedInstanceState.getParcelable("task");
        }
    }
}


