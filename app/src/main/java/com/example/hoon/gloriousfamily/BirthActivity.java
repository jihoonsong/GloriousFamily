package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.AfterViews;

@EActivity(R.layout.activity_birth)
public class BirthActivity extends Activity {
  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
  }
}
