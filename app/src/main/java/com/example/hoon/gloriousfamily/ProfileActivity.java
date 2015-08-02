package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_profile)
public class ProfileActivity extends Activity {
    @ViewById(R.id.button_getstarted)
    ImageView buttonGetstarted;

    @AfterViews
    protected void init() {
        // Lock rotation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Load image.
        Glide.with(getApplicationContext()).load(R.drawable.button_getstarted).into(buttonGetstarted);
    }

    @Click(R.id.button_getstarted)
    void buttonGetStartedClicked() {
        CharacterSelectActivity_.intent(this).start();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
        System.gc();
    }
}
