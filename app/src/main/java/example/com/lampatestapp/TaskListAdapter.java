package example.com.lampatestapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by viktoria on 15.01.15.
 */

/*
Used as adapter for ListView of Tasks in TaskListFragment
 */
public class TaskListAdapter extends ArrayAdapter<Task> {
    Context context;
    List<Task> tasks;
    int layoutResourceId; //layout of row

    public TaskListAdapter(Context context, int layoutResourceId,
                           List<Task> tasks) {
        super(context, layoutResourceId, tasks);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.tasks = tasks;
    }

    //holder pattern used to avoids frequent call of findViewById()
    static class TaskHolder {
        TextView taskTitle;
        TextView taskDescription;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TaskHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new TaskHolder();
            holder.taskTitle = (TextView) row.findViewById(R.id.task_title);
            holder.taskDescription = (TextView) row.findViewById(R.id.task_description);
            row.setTag(holder);
        } else {
            holder = (TaskHolder) row.getTag();
        }
        Task task_item = getItem(position);
        if (task_item != null) {
            holder.taskTitle.setText(task_item.getTitle());
            holder.taskDescription.setText(task_item.getDescription());
        }
        return row;
    }
}
