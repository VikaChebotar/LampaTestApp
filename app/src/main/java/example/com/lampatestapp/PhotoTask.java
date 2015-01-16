package example.com.lampatestapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viktoria on 15.01.15.
 */

/*
Represent one page in viewpager in GalleryFragment
Implements parcel to enable putting PhotoTask objects in bundle to save instanse state or pass it to fragment
 */
public class PhotoTask implements Parcelable{
    private int drawable_id;
    private String description;
    public PhotoTask(int drawable_id, String description){
        this.drawable_id = drawable_id;
        this.description = description;
    }
    private PhotoTask(Parcel parcel) {
        drawable_id = parcel.readInt();
        description = parcel.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(drawable_id);
        parcel.writeString(description);
    }
    public static final Parcelable.Creator<PhotoTask> CREATOR = new Parcelable.Creator<PhotoTask>() {
         // get object from parcel
        public PhotoTask createFromParcel(Parcel in) {
            return new PhotoTask(in);
        }

        public PhotoTask[] newArray(int size) {
            return new PhotoTask[size];
        }
    };

    public int getDrawable_id() {
        return drawable_id;
    }

    public void setDrawable_id(int drawable_id) {
        this.drawable_id = drawable_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
