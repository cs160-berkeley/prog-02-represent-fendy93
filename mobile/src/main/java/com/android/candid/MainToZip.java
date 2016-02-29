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
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainToZip extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.android.candid.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zcode);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String zip_num = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.zipNum);
        textView.setTextSize(19);
        textView.setText(zip_num);

        TabHost tabhost = (TabHost) findViewById(R.id.tabHost_curr);

        tabhost.setup();

        TabHost.TabSpec tabSpec = tabhost.newTabSpec("senator");
        tabSpec.setContent(R.id.tab_senator);
        tabSpec.setIndicator("Senator");
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec("rep");
        tabSpec.setContent(R.id.tab_rep);
        tabSpec.setIndicator("Representative");
        tabhost.addTab(tabSpec);

        ImageView jc_img = (ImageView)findViewById(R.id.jc_img);
        jc_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.john_corney);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToZip.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.jc_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.jc_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        TextView jc_text = (TextView)findViewById(R.id.jc_text);
        jc_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.john_corney);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToZip.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.jc_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.jc_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        ImageView jc_web = (ImageView)findViewById(R.id.jc_web);
        jc_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.cornyn.senate.gov/"));
                startActivity(intent);
            }
        });

        ImageView jc_email = (ImageView)findViewById(R.id.jc_contact);
        jc_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.cornyn.senate.gov/contact"));
                startActivity(intent);
            }
        });




        ImageView tcruz_img = (ImageView)findViewById(R.id.tcruz_img);
        tcruz_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.t_cruz);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToZip.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.tcruz_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.tcruz_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        TextView tcruz_text = (TextView)findViewById(R.id.tcruz_text);
        tcruz_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.t_cruz);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToZip.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.tcruz_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.tcruz_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        ImageView tcruz_web = (ImageView)findViewById(R.id.tcruz_web);
        tcruz_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.cruz.senate.gov/"));
                startActivity(intent);
            }
        });

        ImageView tcruz_email = (ImageView)findViewById(R.id.tcruz_contact);
        tcruz_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.cruz.senate.gov/?p=email_senator"));
                startActivity(intent);
            }
        });





        ImageView lm_img = (ImageView)findViewById(R.id.lm_img);
        lm_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.lsmith);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToZip.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.lm_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.lm_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        TextView lm_text = (TextView)findViewById(R.id.lm_text);
        lm_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.lsmith);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToZip.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.lm_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.lm_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        ImageView lm_web = (ImageView)findViewById(R.id.lm_web);
        lm_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://lamarsmith.house.gov/"));
                startActivity(intent);
            }
        });

        ImageView lm_email = (ImageView)findViewById(R.id.lm_contact);
        lm_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://lamarsmith.house.gov/contact"));
                startActivity(intent);
            }
        });






        ImageView mk_img = (ImageView)findViewById(R.id.mk_img);
        mk_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mkeough);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToZip.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.mk_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.mk_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        TextView mk_text = (TextView)findViewById(R.id.mk_text);
        mk_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mkeough);
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToZip.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                TextView editText = (TextView) findViewById(R.id.mk_name);
                String message = editText.getText().toString();
                intent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.mk_party);
                String message1 = editText1.getText().toString();
                intent.putExtra("party", message1);

                startActivity(intent);
            }
        });

        ImageView mk_web = (ImageView)findViewById(R.id.mk_web);
        mk_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.house.state.tx.us/members/member-page/?district=15"));
                startActivity(intent);
            }
        });

        ImageView mk_email = (ImageView)findViewById(R.id.mk_contact);
        mk_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.house.state.tx.us/members/member-page/email/?district=15&session=84"));
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
