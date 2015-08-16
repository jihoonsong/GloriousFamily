package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_profile)
public class ProfileActivity extends Activity {
  @ViewById(R.id.button_start)
  ImageView buttonStart;

  @Pref
  StartFlagsPrefs_ startFlagsPrefs;

  @Pref
  ConfigPrefs_ configPrefs;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Load images.
//    Glide.with(getApplicationContext()).load(R.drawable.button_select).into(buttonStart);
  }

  @Click(R.id.button_start)
  protected void buttonStartClicked() {
    // TODO : Store received input and check it's validity.

    // TODO : If input is valid, do belows.
    // Set profile flag true.
    startFlagsPrefs.isProfile().put(true);

    // Swap to CharacterSelectActivity.
    CharacterSelectActivity_.intent(this).start();

    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
