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
    Glide.with(getApplicationContext()).load(R.drawable.background_dating_tent);
    Glide.with(getApplicationContext()).load(R.drawable.background_dating_castle);

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
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart10);
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart20);
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart30);
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart40);
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart50);
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart60);
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart70);
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart80);
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart90);

    // Character images.
    Glide.with(getApplicationContext()).load(R.drawable.character_body_red);
    Glide.with(getApplicationContext()).load(R.drawable.character_body_green);
    Glide.with(getApplicationContext()).load(R.drawable.character_body_blue);
    Glide.with(getApplicationContext()).load(R.drawable.character_body_yellow);
    Glide.with(getApplicationContext()).load(R.drawable.character_body_white);

    Glide.with(getApplicationContext()).load(R.drawable.character_color_red);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_green);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_blue);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_yellow);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_white);

    Glide.with(getApplicationContext()).load(R.drawable.character_face_one);
    Glide.with(getApplicationContext()).load(R.drawable.character_face_two);
    Glide.with(getApplicationContext()).load(R.drawable.character_face_three);
    Glide.with(getApplicationContext()).load(R.drawable.character_face_four);
    Glide.with(getApplicationContext()).load(R.drawable.character_face_five);


    Glide.with(getApplicationContext()).load(R.drawable.character_costume_one);
    Glide.with(getApplicationContext()).load(R.drawable.character_costume_two);
    Glide.with(getApplicationContext()).load(R.drawable.character_costume_three);

    Glide.with(getApplicationContext()).load(R.drawable.character_left_arrow_face);
    Glide.with(getApplicationContext()).load(R.drawable.character_left_arrow_color);
    Glide.with(getApplicationContext()).load(R.drawable.character_left_arrow_costume);
    Glide.with(getApplicationContext()).load(R.drawable.character_right_arrow_face);
    Glide.with(getApplicationContext()).load(R.drawable.character_right_arrow_color);
    Glide.with(getApplicationContext()).load(R.drawable.character_right_arrow_costume);

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
