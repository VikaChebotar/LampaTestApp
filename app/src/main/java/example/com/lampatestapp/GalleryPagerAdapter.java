package example.com.lampatestapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by viktoria on 15.01.15.
 */

/*
Used as adapter for ViewPager in GalleryFragment. Each page consist of PhotoTask - image and description
 */
public class GalleryPagerAdapter extends PagerAdapter {
    Context context;
    PhotoTask[] photos;
    LayoutInflater inflater;
    ImageView image;
    TextView description;

    public GalleryPagerAdapter(Context context,
                               PhotoTask[] photos) {
        this.context = context;
        this.photos = photos;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return photos.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

       View itemView = inflater.inflate(R.layout.gallery_page,
                container, false);
        PhotoTask photoTaskItem = photos[position];
        image = (ImageView) itemView.findViewById(R.id.image_view);
        image.setImageDrawable(context.getResources().getDrawable(photoTaskItem.getDrawable_id()));
        description = (TextView) itemView.findViewById(R.id.photo_description);
        description.setText(photoTaskItem.getDescription());
        container.addView(itemView);

        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==(RelativeLayout)o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
