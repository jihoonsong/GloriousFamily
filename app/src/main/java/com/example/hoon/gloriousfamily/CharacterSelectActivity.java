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
    ImageView buttonYesItIsMe;

    @ViewById(R.id.character_body_one)
    ImageView characterBodyOne;

    @ViewById(R.id.character_color_one)
    ImageView characterColorOne;

    @ViewById(R.id.character_face_one)
    ImageView characterFaceOne;

    @ViewById(R.id.character_cloth_one)
    ImageView characterClothOne;

    @AfterViews
    protected void init() {
        // Lock rotation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Load images.
        Glide.with(getApplicationContext()).load(R.drawable.button_yesitisme).into(buttonYesItIsMe);
        Glide.with(getApplicationContext()).load(R.drawable.character_body_one).into(characterBodyOne);
        Glide.with(getApplicationContext()).load(R.drawable.character_color_one).into(characterColorOne);
        Glide.with(getApplicationContext()).load(R.drawable.character_face_one).into(characterFaceOne);
        Glide.with(getApplicationContext()).load(R.drawable.character_cloth_one).into(characterClothOne);
    }

    @Click(R.id.button_yesitisme)
    void buttonYesItIsMeClicked() {
        HomeActivity_.intent(this).start();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
        System.gc();
    }
}
