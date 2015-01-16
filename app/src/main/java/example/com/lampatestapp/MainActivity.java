package example.com.lampatestapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends Activity implements TaskListFragment.OnTaskClickListener {
    private ArrayList<Task> taskItems;
    private static final int[] drawables = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5}; //images used in GalleryFragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            taskItems = createTasks();
            TaskListFragment task_list_fr = new TaskListFragment();
            Bundle args = new Bundle();
            args.putParcelableArrayList("tasks", taskItems); //pass tasks to fragment
            task_list_fr.setArguments(args);
            getFragmentManager().beginTransaction().replace(R.id.content_frame, task_list_fr,
                    "task_list_fr").commit(); //set TaskListFragment as visible one
        } else {
            taskItems = savedInstanceState.getParcelableArrayList("taskItems"); //retain tasks from saved state, no need to populate them again
        }
    }

    //populate all data
    private ArrayList<Task> createTasks() {
        ArrayList<Task> taskItems = new ArrayList<Task>();
        String title = getResources().getString(R.string.task_title);
        String description = getResources().getString(R.string.task_description);
        PhotoTask[] photoTasks = new PhotoTask[5];
        for (int i = 0; i < drawables.length; i++) {
            photoTasks[i] = new PhotoTask(drawables[i], getString(R.string.image_description) + " " + (i + 1));
        }
        for (int i = 1; i < 10; i++) {
            taskItems.add(new Task(title + " " + i, description + "" + i, photoTasks));
        }
        return taskItems;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("taskItems", taskItems);
    }

    /*
    method called when row of task clicked
     */
    @Override
    public void onTaskClick(int position) {
        GalleryFragment gallery_fr = new GalleryFragment();
        Bundle args2 = new Bundle();
        args2.putParcelable("task", taskItems.get(position));
        gallery_fr.setArguments(args2);
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.anim1,
                R.animator.anim2, R.animator.anim4, R.animator.anim3).replace(R.id.content_frame, gallery_fr,
                "gallery_fr").addToBackStack(
                "gallery_fr").commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // this is called when the Home (Up) button is pressed in the action bar.
                getFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
