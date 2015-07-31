package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.*;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;

@EActivity(R.layout.activity_home)
public class HomeActivity extends Activity {
    @AfterViews
    protected void init() {
        // Lock rotation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
