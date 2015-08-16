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
  @Pref
  StartFlagsPrefs_ startFlagsPrefs;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // XXX : Global Image Cache.
    // XXX : Must be called at first activity !
    this.cache();

    // Wait 1200 milliseconds and check start flags.
    Handler mHandler = new Handler();
    mHandler.postDelayed(new Runnable() {
      public void run() {
        CheckStartFlags();
      }
    }, 2300);
  }

  @Background
  protected void cache() {
    // TODO : Cache all images ! (Make sure not to omit.)

    // Background images.
    Glide.with(getApplicationContext()).load(R.drawable.background_main);
    Glide.with(getApplicationContext()).load(R.drawable.background_profile);
    Glide.with(getApplicationContext()).load(R.drawable.background_home);
    Glide.with(getApplicationContext()).load(R.drawable.background_mode_select);

    // Button images.
    Glide.with(getApplicationContext()).load(R.drawable.button_confirm);
    Glide.with(getApplicationContext()).load(R.drawable.button_a_unselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_a_selected);
    Glide.with(getApplicationContext()).load(R.drawable.button_b_unselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_b_selected);
    Glide.with(getApplicationContext()).load(R.drawable.button_c_unselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_c_selected);
    Glide.with(getApplicationContext()).load(R.drawable.button_d_unselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_d_selected);

    // Layer images.
    Glide.with(getApplicationContext()).load(R.drawable.layer_banner);
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
