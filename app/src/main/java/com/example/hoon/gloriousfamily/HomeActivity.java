package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class HomeActivity extends Activity {
    @ViewById(R.id.button_findyourmate)
    ImageView buttonFindyourmate;

    @ViewById(R.id.layer_banner)
    ImageView layerBanner;

    @ViewById(R.id.button_profile)
    ImageView buttonProfile;

    @ViewById(R.id.button_familytree)
    ImageView buttonFamilytree;

    @ViewById(R.id.character_body_one)
    ImageView characterBodyOne;

    @AfterViews
    protected void init() {
        // Lock rotation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Load images.
        Glide.with(getApplicationContext()).load(R.drawable.button_findyourmate).into(buttonFindyourmate);
        Glide.with(getApplicationContext()).load(R.drawable.layer_banner).into(layerBanner);
        Glide.with(getApplicationContext()).load(R.drawable.button_profile).into(buttonProfile);
        Glide.with(getApplicationContext()).load(R.drawable.button_familytree).into(buttonFamilytree);
        Glide.with(getApplicationContext()).load(R.drawable.character_body_one).into(characterBodyOne);
    }

    @Click(R.id.button_profile)
    void buttonProfileClicked() {
        ProfileActivity_.intent(this).start();
    }

    @Click(R.id.button_familytree)
    void buttonFamilyTreeClicked() {
        FamilyTreeActivity_.intent(this).start();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Click(R.id.button_findyourmate)
    void buttonFindYourMateClicked() {
        MateSelectActivity_.intent(this).start();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
