package com.android.candid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button zip_button;
    Button current_button;
    public final static String EXTRA_MESSAGE = "com.android.candid.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zip_button = (Button) findViewById(R.id.zipcode_button);
        current_button = (Button) findViewById(R.id.current_location_button);

        zip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.zipcode);
                if (editText.getText().length() == 0 || Integer.valueOf(editText.getText().toString()) != 76768){
                    editText.setError("Please enter ZIP Code: 76768");
                } else {
                    Intent intent = new Intent(MainActivity.this, MainToZip.class);
                    String message = editText.getText().toString();
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);
                }
            }
        });

        current_button.setOnClickListener(this);
    }

    private void ZipButton(){
        startActivity(new Intent("com.android.candid.MainToZip"));
    }

    private void CurrentButton(){
        startActivity(new Intent("com.android.candid.MainToCurrent"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zipcode_button:
                ZipButton();
                break;
            case R.id.current_location_button:
                CurrentButton();
                break;
        }
    }

//    public void sendMessage(View view) {
//
//    }

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
