package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.view.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_signup)
public class SignupActivity extends Activity {
  @ViewById(R.id.button_facebook)
  ImageView buttonFacebook;

  @ViewById(R.id.layer_facebook)
  ImageView layerFacebook;

  @ViewById(R.id.button_login)
  ImageView buttonLogin;

  @ViewById(R.id.text_email)
  TextView textEmail;

  @ViewById(R.id.edit_email)
  EditText editEmail;

  @ViewById(R.id.text_password)
  TextView textPassword;

  @ViewById(R.id.edit_password)
  EditText editPassword;

  @Pref
  LoginPrefs_ loginPrefs;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Load images.
    Glide.with(getApplicationContext()).load(R.drawable.button_facebook).into(buttonFacebook);
    Glide.with(getApplicationContext()).load(R.drawable.layer_facebook);
    Glide.with(getApplicationContext()).load(R.drawable.button_login);

    // Set fonts type.
    textEmail.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_MEDIUM.ttf"));
    textPassword.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_MEDIUM.ttf"));
  }

  @Click(R.id.button_facebook)
  protected void buttonFacebookClicked() {
    // Set sign up element invisible.
    buttonFacebook.setVisibility(View.INVISIBLE);

    // Set login elements visible.
    Glide.with(getApplicationContext()).load(R.drawable.layer_facebook).into(layerFacebook);
    layerFacebook.setVisibility(View.VISIBLE);
    Glide.with(getApplicationContext()).load(R.drawable.button_login).into(buttonLogin);
    buttonLogin.setVisibility(View.VISIBLE);

    editEmail.setVisibility(View.VISIBLE);
    textEmail.setVisibility(View.VISIBLE);
    editPassword.setVisibility(View.VISIBLE);
    textPassword.setVisibility(View.VISIBLE);
  }

  @Click(R.id.button_login)
  protected void buttonLoginClicked() {
    // TODO : Login to facebook.

    // Store received input.
    loginPrefs.email().put(editEmail.getText().toString());
    loginPrefs.password().put(editPassword.getText().toString());

    // Swap activity.
    ProfileActivity_.intent(this).start();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
