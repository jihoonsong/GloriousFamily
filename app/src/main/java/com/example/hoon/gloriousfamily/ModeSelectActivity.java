package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_mode_select)
public class ModeSelectActivity extends Activity {
  @ViewById(R.id.button_confirm)
  ImageView buttonConfirm;

  @ViewById(R.id.button_a_unselected)
  ImageView buttonAUnselected;

  @ViewById(R.id.button_a_selected)
  ImageView buttonASelected;

  @ViewById(R.id.button_b_unselected)
  ImageView buttonBUnselected;

  @ViewById(R.id.button_b_selected)
  ImageView buttonBSelected;

  @ViewById(R.id.button_c_unselected)
  ImageView buttonCUnselected;

  @ViewById(R.id.button_c_selected)
  ImageView buttonCSelected;

  @ViewById(R.id.button_d_unselected)
  ImageView buttonDUnselected;

  @ViewById(R.id.button_d_selected)
  ImageView buttonDSelected;

  @Pref
  StartFlagsPrefs_ startFlagsPrefs;

  @Pref
  ConfigPrefs_ configPrefs;

  private String mode = "";

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Load images.
    Glide.with(getApplicationContext()).load(R.drawable.button_confirm).into(buttonConfirm);
    Glide.with(getApplicationContext()).load(R.drawable.button_a_unselected).into(buttonAUnselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_a_selected);
    Glide.with(getApplicationContext()).load(R.drawable.button_b_unselected).into(buttonBUnselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_b_selected);
    Glide.with(getApplicationContext()).load(R.drawable.button_c_unselected).into(buttonCUnselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_c_selected);
    Glide.with(getApplicationContext()).load(R.drawable.button_d_unselected).into(buttonDUnselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_d_selected);
  }

  @Click(R.id.button_a_unselected)
  protected void buttonAUnselectedClicked() {
    mode = "A";
    Glide.with(getApplicationContext()).load(R.drawable.button_a_selected).into(buttonASelected);
    buttonASelected.setVisibility(View.VISIBLE);
    buttonBSelected.setVisibility(View.INVISIBLE);
    buttonCSelected.setVisibility(View.INVISIBLE);
    buttonDSelected.setVisibility(View.INVISIBLE);
  }

  @Click(R.id.button_b_unselected)
  protected void buttonBUnselectedClicked() {
    mode = "B";
    Glide.with(getApplicationContext()).load(R.drawable.button_b_selected).into(buttonBSelected);
    buttonBSelected.setVisibility(View.VISIBLE);
    buttonASelected.setVisibility(View.INVISIBLE);
    buttonCSelected.setVisibility(View.INVISIBLE);
    buttonDSelected.setVisibility(View.INVISIBLE);
  }

  @Click(R.id.button_c_unselected)
  protected void buttonCUnselectedClicked() {
    mode = "C";
    Glide.with(getApplicationContext()).load(R.drawable.button_c_selected).into(buttonCSelected);
    buttonCSelected.setVisibility(View.VISIBLE);
    buttonASelected.setVisibility(View.INVISIBLE);
    buttonBSelected.setVisibility(View.INVISIBLE);
    buttonDSelected.setVisibility(View.INVISIBLE);
  }

  @Click(R.id.button_d_unselected)
  protected void buttonDUnselectedClicked() {
    mode = "D";
    Glide.with(getApplicationContext()).load(R.drawable.button_d_selected).into(buttonDSelected);
    buttonDSelected.setVisibility(View.VISIBLE);
    buttonASelected.setVisibility(View.INVISIBLE);
    buttonBSelected.setVisibility(View.INVISIBLE);
    buttonCSelected.setVisibility(View.INVISIBLE);
  }

  @Click(R.id.button_confirm)
  protected void buttonConfirmClicked() {
    // Check input validity.
    if (mode.equals("")) {
      Toast.makeText(this, "Please select your character type.", Toast.LENGTH_SHORT).show();
      return;
    }

    // Store mode.
    configPrefs.Mode().put(mode);

    // Set mode select flag true.
    startFlagsPrefs.isModeSelect().put(true);

    // Swap to ProfileActivity.
    ProfileActivity_.intent(this).start();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
