package com.example.hoon.gloriousfamily;

import android.support.v4.app.FragmentActivity;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

public class SignupActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
