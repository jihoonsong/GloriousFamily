package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_loading)
public class LoadingActivity extends Activity {
  @Pref
  LoginPrefs_ loginPrefs;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Check if login data exists.
    checkLoginData();
  }

  private void checkLoginData() {
    if ((loginPrefs.email().get().equals("")) &&
        (loginPrefs.password().get().equals(""))) {
      // User needs to login.
      SignupActivity_.intent(this).start();

    } else {
      // User already logged in.
      HomeActivity_.intent(this).start();
    }

    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
