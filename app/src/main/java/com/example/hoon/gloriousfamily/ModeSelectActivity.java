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
    Glide.with(getApplicationContext()).load(R.drawable.button_a_selected).into(buttonASelected);
    Glide.with(getApplicationContext()).load(R.drawable.button_b_selected).into(buttonBSelected);
    Glide.with(getApplicationContext()).load(R.drawable.button_c_selected).into(buttonCSelected);
    Glide.with(getApplicationContext()).load(R.drawable.button_d_selected).into(buttonDSelected);
    Glide.with(getApplicationContext()).load(R.drawable.button_a_unselected).into(buttonAUnselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_b_unselected).into(buttonBUnselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_c_unselected).into(buttonCUnselected);
    Glide.with(getApplicationContext()).load(R.drawable.button_d_unselected).into(buttonDUnselected);
  }

  @Click(R.id.button_a_unselected)
  protected void buttonAUnselectedClicked() {
    mode = "A";

    buttonASelected.setVisibility(View.VISIBLE);
    buttonBSelected.setVisibility(View.INVISIBLE);
    buttonCSelected.setVisibility(View.INVISIBLE);
    buttonDSelected.setVisibility(View.INVISIBLE);

    buttonAUnselected.setVisibility(View.INVISIBLE);
    buttonBUnselected.setVisibility(View.VISIBLE);
    buttonCUnselected.setVisibility(View.VISIBLE);
    buttonDUnselected.setVisibility(View.VISIBLE);
  }

  @Click(R.id.button_b_unselected)
  protected void buttonBUnselectedClicked() {
    mode = "B";

    buttonBSelected.setVisibility(View.VISIBLE);
    buttonASelected.setVisibility(View.INVISIBLE);
    buttonCSelected.setVisibility(View.INVISIBLE);
    buttonDSelected.setVisibility(View.INVISIBLE);

    buttonBUnselected.setVisibility(View.INVISIBLE);
    buttonAUnselected.setVisibility(View.VISIBLE);
    buttonCUnselected.setVisibility(View.VISIBLE);
    buttonDUnselected.setVisibility(View.VISIBLE);
  }

  @Click(R.id.button_c_unselected)
  protected void buttonCUnselectedClicked() {
    mode = "C";

    buttonCSelected.setVisibility(View.VISIBLE);
    buttonASelected.setVisibility(View.INVISIBLE);
    buttonBSelected.setVisibility(View.INVISIBLE);
    buttonDSelected.setVisibility(View.INVISIBLE);

    buttonCUnselected.setVisibility(View.INVISIBLE);
    buttonAUnselected.setVisibility(View.VISIBLE);
    buttonBUnselected.setVisibility(View.VISIBLE);
    buttonDUnselected.setVisibility(View.VISIBLE);
  }

  @Click(R.id.button_d_unselected)
  protected void buttonDUnselectedClicked() {
    mode = "D";

    buttonDSelected.setVisibility(View.VISIBLE);
    buttonASelected.setVisibility(View.INVISIBLE);
    buttonBSelected.setVisibility(View.INVISIBLE);
    buttonCSelected.setVisibility(View.INVISIBLE);

    buttonDUnselected.setVisibility(View.INVISIBLE);
    buttonAUnselected.setVisibility(View.VISIBLE);
    buttonBUnselected.setVisibility(View.VISIBLE);
    buttonCUnselected.setVisibility(View.VISIBLE);
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
