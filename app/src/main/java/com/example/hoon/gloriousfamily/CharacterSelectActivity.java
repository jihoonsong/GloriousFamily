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

@EActivity(R.layout.activity_character_select)
public class CharacterSelectActivity extends Activity{
    @ViewById(R.id.button_yesitisme)
    ImageView button_yesitisme;

    @ViewById(R.id.character_body_one)
    ImageView character_body_one;

    @ViewById(R.id.character_color_one)
    ImageView character_color_one;

    @ViewById(R.id.character_face_one)
    ImageView character_face_one;

    @ViewById(R.id.character_cloth_one)
    ImageView character_cloth_one;

    @AfterViews
    protected void init() {
        // Lock rotation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Load images.
        Glide.with(getApplicationContext()).load(R.drawable.button_yesitisme).into(button_yesitisme);
        Glide.with(getApplicationContext()).load(R.drawable.character_body_one).into(character_body_one);
        Glide.with(getApplicationContext()).load(R.drawable.character_color_one).into(character_color_one);
        Glide.with(getApplicationContext()).load(R.drawable.character_face_one).into(character_face_one);
        Glide.with(getApplicationContext()).load(R.drawable.character_cloth_one).into(character_cloth_one);
    }

    @Click(R.id.button_yesitisme)
    void buttonYesItIsMeClicked() {
        HomeActivity_.intent(this).start();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
        System.gc();
    }
}
