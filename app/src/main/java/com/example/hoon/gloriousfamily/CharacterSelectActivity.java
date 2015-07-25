package com.example.hoon.gloriousfamily;

import android.support.v4.app.FragmentActivity;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

public class CharacterSelectActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_yesitisme:
                startActivity(new Intent(this, CharacterSelectActivity.class));
                break;
        }
    }
}
