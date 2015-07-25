package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.button_familytree:
                startActivity(new Intent(this, FamilyTreeActivity.class));
                break;
            case R.id.button_findyourmate:
                startActivity(new Intent(this, MateSelectActivity.class));
                break;
        }
    }
}
