package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.graphics.Typeface;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_profile)
public class ProfileActivity extends Activity {
  @ViewById(R.id.button_getstarted)
  ImageView buttonGetStarted;

  @ViewById(R.id.text_getstarted)
  TextView textGetStarted;

  @ViewById(R.id.text_goal)
  TextView textGoal;

  @ViewById(R.id.text_basic_information)
  TextView textBasicInformation;

  @ViewById(R.id.text_height)
  TextView textHeight;

  @ViewById(R.id.edit_height)
  EditText editHeight;

  @ViewById(R.id.text_cm)
  TextView textCm;

  @ViewById(R.id.text_weight)
  TextView textWeight;

  @ViewById(R.id.edit_weight)
  EditText editWeight;

  @ViewById(R.id.text_kg)
  TextView textKg;

  @ViewById(R.id.text_gender)
  TextView textGender;

  @ViewById(R.id.text_notify)
  TextView textNotify;

  @ViewById(R.id.text_push_notification)
  TextView textPushNotification;

  @ViewById(R.id.switch_notification)
  Switch switchNotification;

  @Pref
  PersonalDataPrefs_ personalDataPrefs;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Load image.
    Glide.with(getApplicationContext()).load(R.drawable.button_getstarted).into(buttonGetStarted);

    // Set font type.
    textGetStarted.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_MEDIUM.ttf"));
    textGoal.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_MEDIUM.ttf"));
    textBasicInformation.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_MEDIUM.ttf"));
    textHeight.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_LIGHT.ttf"));
    textCm.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_LIGHT.ttf"));
    textWeight.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_LIGHT.ttf"));
    textKg.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_LIGHT.ttf"));
    textGender.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_LIGHT.ttf"));
    textNotify.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_MEDIUM.ttf"));
    textPushNotification.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_LIGHT.ttf"));
  }

  @Click(R.id.button_getstarted)
  protected void buttonGetStartedClicked() {
    // Store received input.
    personalDataPrefs.height().put(editHeight.getText().toString());
    personalDataPrefs.weight().put(editWeight.getText().toString());
    if(switchNotification.isChecked())
      personalDataPrefs.notification().put("ON");
    else
      personalDataPrefs.notification().put("OFF");

    // Swap activity.
    CharacterSelectActivity_.intent(this).start();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
