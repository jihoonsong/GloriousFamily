package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.AfterViews;

@EActivity(R.layout.activity_mate_select)
public class MateSelectActivity extends Activity {
  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
  }
}
