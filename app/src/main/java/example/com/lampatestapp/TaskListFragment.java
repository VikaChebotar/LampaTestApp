package example.com.lampatestapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by viktoria on 15.01.15.
 */

/*
first page, shows list of tasks that activity pass to it in arguments
 */
public class TaskListFragment extends ListFragment {
    private ArrayList<Task> taskItems;
    OnTaskClickListener mCallback;
    ActionBar actionBar;

    // interface to communicate with other fragment through activity, fragment shouldn't know about parent activity
    public interface OnTaskClickListener {
        public void onTaskClick(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // this makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnTaskClickListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTaskClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        taskItems = getArguments().getParcelableArrayList("tasks");
        setListAdapter(new TaskListAdapter(getActivity(), R.layout.task_item, taskItems));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEmptyText(getResources().getString(R.string.no_tasks)); //this is to set message if taskItems.size==0
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onTaskClick(position);
    }

    @Override
    public void onResume() {
        super.onResume();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(((Activity) mCallback).getResources().getString(R.string.task_list_fragment_title));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        actionBar = ((Activity) mCallback).getActionBar();
        if(savedInstanceState!=null) {
            taskItems = savedInstanceState.getParcelableArrayList("tasks");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("tasks", taskItems);
    }
}
