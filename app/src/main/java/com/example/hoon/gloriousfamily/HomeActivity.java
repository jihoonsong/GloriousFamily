package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class HomeActivity extends Activity {
    @ViewById ImageView background_home_tent, button_findyourmate, character_body_one, button_profile, button_familytree, layer_banner;

    @AfterViews
    protected void init() {
        // Lock rotation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Glide.with(getApplicationContext()).load(R.drawable.background_home_tent).into(background_home_tent);
        Glide.with(getApplicationContext()).load(R.drawable.button_findyourmate).into(button_findyourmate);
        Glide.with(getApplicationContext()).load(R.drawable.character_body_one).into(character_body_one);
        Glide.with(getApplicationContext()).load(R.drawable.button_profile).into(button_profile);
        Glide.with(getApplicationContext()).load(R.drawable.button_familytree).into(button_familytree);
        Glide.with(getApplicationContext()).load(R.drawable.layer_banner).into(layer_banner);
    }

    @Click(R.id.button_profile)
    void buttonProfileClicked() {
        ProfileActivity_.intent(this).start();
    }

    @Click(R.id.button_familytree)
    void buttonFamilyTreeClicked() {
        FamilyTreeActivity_.intent(this).start();
    }

    @Click(R.id.button_findyourmate)
    void buttonFindYourMateClicked() {
        MateSelectActivity_.intent(this).start();
    }
}
