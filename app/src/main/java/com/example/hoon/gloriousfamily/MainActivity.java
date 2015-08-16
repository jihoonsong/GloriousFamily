package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
  @ViewById(R.id.text_loading)
  TextView textLoading;

  @ViewById(R.id.button_start)
  ImageView buttonStart;

  @Pref
  StartFlagsPrefs_ startFlagsPrefs;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // XXX : Global Image Cache.
    // XXX : Must be called at first activity !
    this.cache();

    // Load images.
    Glide.with(getApplicationContext()).load(R.drawable.button_start);

    // Wait 1200 milliseconds and set visibility.
    Handler mHandler = new Handler();
    mHandler.postDelayed(new Runnable() {
      public void run() {
        // Make loading text invisible  and start button visible.
        textLoading.setVisibility(View.INVISIBLE);
        Glide.with(getApplicationContext()).load(R.drawable.button_start).into(buttonStart);
        buttonStart.setVisibility(View.VISIBLE);
      }
    }, 1100);
  }

  @Background
  protected void cache() {
    // TODO : Cache all images ! (Make sure not to omit.)

    // Background images.
    Glide.with(getApplicationContext()).load(R.drawable.background_main);
    Glide.with(getApplicationContext()).load(R.drawable.background_profile);
    Glide.with(getApplicationContext()).load(R.drawable.background_home);

    // Button images.
    Glide.with(getApplicationContext()).load(R.drawable.button_select);
    Glide.with(getApplicationContext()).load(R.drawable.button_start);

    // Layer images.
    Glide.with(getApplicationContext()).load(R.drawable.layer_banner);
  }

  @Click(R.id.button_start)
  protected void buttonStartClicked() {
    if(startFlagsPrefs.isScenario().get()) {
      // Swap to HomeActivity.
      HomeActivity_.intent(this).start();
    }
    else if(startFlagsPrefs.isProfile().get()) {
      // Swap to ScenarioActivity.
      ScenarioActivity_.intent(this).start();
    }
    else if(startFlagsPrefs.isCharacterSelect().get()) {
      // Swap to ProfileActivity.
      ProfileActivity_.intent(this).start();
    }
    else if(startFlagsPrefs.isModeSelect().get()) {
      // Swap to CharacterSelectActivity.
      CharacterSelectActivity_.intent(this).start();
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
