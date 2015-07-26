package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;

public class SignupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lock rotation.
        setContentView(R.layout.activity_signup);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Set custom font.
        TextView text_family = (TextView)findViewById(R.id.text_family);
        text_family.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_LIGHT.ttf"));
        TextView text_glorious = (TextView)findViewById(R.id.text_glorious);
        text_glorious.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_LIGHT.ttf"));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_facebook:
                ImageView button_facebook = (ImageView)findViewById(R.id.button_facebook);
                button_facebook.setVisibility(View.INVISIBLE);
                ImageView layer_facebook = (ImageView)findViewById(R.id.layer_facebook);
                layer_facebook.setVisibility(View.VISIBLE);
                ImageView button_login = (ImageView)findViewById(R.id.button_login);
                button_login.setVisibility(View.VISIBLE);
                // 값 입력받는 창
                break;
            case R.id.button_login:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
    }
}
