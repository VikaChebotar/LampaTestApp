package example.com.lampatestapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viktoria on 15.01.15.
 */

/*
Represent one line in list in TaskListFragment.
Implements parcel to enable putting Task objects in bundle to save instanse state or pass it to fragment
 */
public class Task implements Parcelable {
    private String title;
    private String description;
    private PhotoTask[] photos = new PhotoTask[5];

    public Task(String title, String description, PhotoTask[] photos) {
        this.title = title;
        this.description = description;
        this.photos = photos;
    }

    private Task(Parcel parcel) {
        title = parcel.readString();
        description = parcel.readString();
       parcel.readTypedArray(photos, PhotoTask.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeTypedArray(photos, flags);
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        // get object from parcel
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoTask[] getPhotos() {

        return photos;
    }

    public void setPhotos(PhotoTask[] photos) {
        this.photos = photos;
    }
}
