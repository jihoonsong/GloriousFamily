package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Handler;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
  @Pref
  StartFlagsPrefs_ startFlagsPrefs;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Wait 1200 milliseconds and check start flags.
    Handler mHandler = new Handler();
    mHandler.postDelayed(new Runnable() {
      public void run() {
        CheckStartFlags();
      }
    }, 2000);
  }

  private void CheckStartFlags() {
    if(startFlagsPrefs.isScenario().get()) {
      // Swap to HomeActivity.
      HomeActivity_.intent(this).start();
    }
    else if(startFlagsPrefs.isCharacterSelect().get()) {
      // Swap to ScenarioActivity.
      ScenarioActivity_.intent(this).start();
    }
    else if(startFlagsPrefs.isProfile().get()) {
      // Swap to CharacterSelectActivity.
      CharacterSelectActivity_.intent(this).start();
    }
    else if(startFlagsPrefs.isModeSelect().get()) {
      // Swap to ProfileActivity.
      ProfileActivity_.intent(this).start();
    }
    else {
      // Swap to ModeSelectActivity.
      ModeSelectActivity_.intent(this).start();
    }

    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
