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
    ImageView button_findyourmate;

    @ViewById(R.id.layer_banner)
    ImageView layer_banner;

    @ViewById(R.id.button_profile)
    ImageView button_profile;

    @ViewById(R.id.button_familytree)
    ImageView button_familytree;

    @ViewById(R.id.character_body_one)
    ImageView character_body_one;

    @AfterViews
    protected void init() {
        // Lock rotation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Load images.
        Glide.with(getApplicationContext()).load(R.drawable.button_findyourmate).into(button_findyourmate);
        Glide.with(getApplicationContext()).load(R.drawable.layer_banner).into(layer_banner);
        Glide.with(getApplicationContext()).load(R.drawable.button_profile).into(button_profile);
        Glide.with(getApplicationContext()).load(R.drawable.button_familytree).into(button_familytree);
        Glide.with(getApplicationContext()).load(R.drawable.character_body_one).into(character_body_one);
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
