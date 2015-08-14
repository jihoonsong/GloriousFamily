package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Handler;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
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

    // XXX : Global Image Cache.
    // XXX : Must be called at first activity !
    this.cache();

    // Wait 1200 milliseconds and check if login data exists.
    Handler mHandler = new Handler();
    mHandler.postDelayed(new Runnable() {
      public void run() {
        checkLoginData();
      }
    }, 1200);
  }

  @Background
  protected void cache() {
    // Background images.
    Glide.with(getApplicationContext()).load(R.drawable.background_signup);
    Glide.with(getApplicationContext()).load(R.drawable.background_profile);
    Glide.with(getApplicationContext()).load(R.drawable.background_charselect);
    Glide.with(getApplicationContext()).load(R.drawable.background_birth);
    Glide.with(getApplicationContext()).load(R.drawable.background_death);
    Glide.with(getApplicationContext()).load(R.drawable.background_family_tree);
    Glide.with(getApplicationContext()).load(R.drawable.background_mate_select);
    Glide.with(getApplicationContext()).load(R.drawable.background_home_tent);

    // Button images.
    Glide.with(getApplicationContext()).load(R.drawable.button_facebook);
    Glide.with(getApplicationContext()).load(R.drawable.button_login);
    Glide.with(getApplicationContext()).load(R.drawable.button_getstarted);
    Glide.with(getApplicationContext()).load(R.drawable.button_findyourmate);
    Glide.with(getApplicationContext()).load(R.drawable.button_profile);
    Glide.with(getApplicationContext()).load(R.drawable.button_familytree);
    Glide.with(getApplicationContext()).load(R.drawable.button_yesitisme);

    // Layer images.
    Glide.with(getApplicationContext()).load(R.drawable.layer_banner);
    Glide.with(getApplicationContext()).load(R.drawable.layer_facebook);

    // Character images.
    Glide.with(getApplicationContext()).load(R.drawable.character_body_one);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_one);
    Glide.with(getApplicationContext()).load(R.drawable.character_face_one);
    Glide.with(getApplicationContext()).load(R.drawable.character_costume_one);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_left_arrow);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_right_arrow);
    Glide.with(getApplicationContext()).load(R.drawable.character_costume_left_arrow);
    Glide.with(getApplicationContext()).load(R.drawable.character_costume_right_arrow);
    Glide.with(getApplicationContext()).load(R.drawable.character_face_left_arrow);
    Glide.with(getApplicationContext()).load(R.drawable.character_face_right_arrow);

    // TODO : Cache all images ! (Make sure not to omit.)
  }

  @Background
  public void checkLoginData() {
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
