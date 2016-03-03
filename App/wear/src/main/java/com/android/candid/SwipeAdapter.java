package com.android.candid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.wearable.activity.WearableActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.candid.R;

/**
 * Created by fendyzhou on 3/1/16.
 */
public class SwipeAdapter extends PagerAdapter {
    private int[] image_resources = {R.mipmap.map_logo, R.mipmap.usa_logo};
    private String[] text_resources = {"Current Location", "2012 Presidential Election"};
    private Context ctx;
    private LayoutInflater layoutInflater;
    private WearableActivity w;



    public SwipeAdapter (Context ctx) {
        this.ctx = ctx;
    }
    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.curr_location, container, false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.curr_img);
        TextView textView = (TextView) item_view.findViewById(R.id.curr_text);
        TextView textView1 = (TextView) item_view.findViewById(R.id.tap_curr);
        if (position == 0) {
            textView1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CurrDetails.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
        if (position == 1) {
            textView1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PressResult.class);
                    v.getContext().startActivity(intent);
                }
            });
        }


        imageView.setImageResource(image_resources[position]);
        textView.setText(text_resources[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
