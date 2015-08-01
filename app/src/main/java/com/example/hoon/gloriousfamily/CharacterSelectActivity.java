package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.*;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;

@EActivity(R.layout.activity_character_select)
public class CharacterSelectActivity extends Activity{
    @AfterViews
    protected void init() {
        // Lock rotation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Click(R.id.button_yesitisme)
    void buttonYesItIsMeClicked() {
        HomeActivity_.intent(this).start();
        finish();
    }
}
