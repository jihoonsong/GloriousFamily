package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_profile)
public class ProfileActivity extends Activity {
    @ViewById ImageView background_profile;
    @ViewById ImageView button_getstarted;

    @AfterViews
    protected void init() {
        // Lock rotation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Picasso.with(getApplicationContext()).load(R.drawable.background_profile).into(background_profile);
        Picasso.with(getApplicationContext()).load(R.drawable.button_getstarted).into(button_getstarted);
    }

    @Click(R.id.button_getstarted)
    void buttonGetStartedClicked() {
        CharacterSelectActivity_.intent(this).start();
        // TODO :
        //finish();
        //onStop();
    }
}
