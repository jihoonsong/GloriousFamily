package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

public class ProfileActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lock rotation.
        setContentView(R.layout.activity_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_getstarted:
                startActivity(new Intent(this, CharacterSelectActivity.class));
                break;
        }
    }
}
