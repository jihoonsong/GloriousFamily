package com.example.hoon.gloriousfamily;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

public class ProfileActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_getstarted:
                // startActivity(new Intent(this, CharacterSelectActivity.class));
                break;
        }
    }
}
