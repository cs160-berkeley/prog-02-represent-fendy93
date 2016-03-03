package com.android.candid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainToCurrent extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.android.candid.MESSAGE";
    public final static String EXTRA_MESSAGE_2 = "com.android.candid.MESSAGE2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabHost tabhost = (TabHost) findViewById(R.id.tabHost_curr);

        tabhost.setup();

        TabHost.TabSpec tabSpec = tabhost.newTabSpec("senator");
        tabSpec.setContent(R.id.tab_senator_curr);
        tabSpec.setIndicator("Senator");
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec("rep");
        tabSpec.setContent(R.id.tab_rep_curr);
        tabSpec.setIndicator("Representative");
        tabhost.addTab(tabSpec);

        ImageView bm_img = (ImageView)findViewById(R.id.bm_img);
        bm_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bmikulski);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.bm_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.bm_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        TextView bm_text = (TextView)findViewById(R.id.bm_text);
        bm_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bmikulski);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.bm_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.bm_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        ImageView bm_web = (ImageView)findViewById(R.id.bm_web);
        bm_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.mikulski.senate.gov/"));
                startActivity(intent);
            }
        });

        ImageView bm_email = (ImageView)findViewById(R.id.bm_contact);
        bm_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.mikulski.senate.gov/contact"));
                startActivity(intent);
            }
        });


        ImageView bb_img = (ImageView)findViewById(R.id.bb_img);
        bb_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dfeinstein);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.bboxer_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.bboxer_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        TextView bb_text = (TextView)findViewById(R.id.bb_text);
        bb_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dfeinstein);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.bboxer_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.bboxer_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        ImageView bb_web = (ImageView)findViewById(R.id.bb_web);
        bb_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.boxer.senate.gov/"));
                startActivity(intent);
            }
        });

        ImageView bb_email = (ImageView)findViewById(R.id.bb_contact);
        bb_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.boxer.senate.gov/contact"));
                startActivity(intent);
            }
        });


        ImageView blee_img = (ImageView)findViewById(R.id.blee_img);
        blee_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.blee);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.blee_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.blee_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        TextView blee_text = (TextView)findViewById(R.id.blee_text);
        blee_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.blee);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.blee_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.blee_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        ImageView blee_web = (ImageView)findViewById(R.id.blee_web);
        blee_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://lee.house.gov/"));
                startActivity(intent);
            }
        });

        ImageView blee_email = (ImageView)findViewById(R.id.blee_email);
        blee_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://barbaralee.house.gov/contact/"));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.home) {
//            return true;
//        }
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
