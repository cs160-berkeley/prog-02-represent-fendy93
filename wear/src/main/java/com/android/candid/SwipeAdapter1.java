package com.android.candid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;

/**
 * Created by fendyzhou on 3/1/16.
 */
public class SwipeAdapter1 extends PagerAdapter {
    private int[] image_resources1 = {R.mipmap.map_logo, R.mipmap.usa_logo};
    private String[] text_resources1 = {"ZIP Code: 76768", "ZIP Code: 94703", "ZIP Code: 55233", "ZIP Code: 99999"};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public SwipeAdapter1(Context ctx) {
        this.ctx = ctx;
    }
    @Override
    public int getCount() {
        return image_resources1.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.zip_location, container, false);
        final ImageView imageView = (ImageView) item_view.findViewById(R.id.zip_img);
        final TextView textView = (TextView) item_view.findViewById(R.id.zip_text);
        final TextView textView1 = (TextView) item_view.findViewById(R.id.zip_tap);
        int p = position;
        if (position == 0) {
            Intent intent = ((Activity) ctx).getIntent();
            final String count = intent.getStringExtra("new zipcode");
            if (!(count.isEmpty())) {
                p = Integer.parseInt(count);
            }
            textView.setText(text_resources1[p]);
            textView1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ZipDetails.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
        if (position == 1) {
            textView.setText("2012 Presidential Election");
            textView.setTextSize(15);
            textView1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PressResult1.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
        imageView.setImageResource(image_resources1[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
