package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_profile)
public class ProfileActivity extends Activity {
  @ViewById(R.id.button_get_started)
  ImageView buttonGetStarted;

  @ViewById(R.id.text_user_name)
  TextView textUserName;

  @ViewById(R.id.edit_user_name)
  EditText editUserName;

  @ViewById(R.id.text_user_gender)
  TextView textUserGender;

  @ViewById(R.id.user_male)
  RadioButton userMale;

  @ViewById(R.id.user_female)
  RadioButton userFemale;

  @ViewById(R.id.text_character_name)
  TextView textCharacterName;

  @ViewById(R.id.edit_character_name)
  EditText editCharacterName;

  @ViewById(R.id.text_character_gender)
  TextView textCharacterGender;

  @ViewById(R.id.character_male)
  RadioButton characterMale;

  @ViewById(R.id.character_female)
  RadioButton characterFemale;

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
    Glide.with(getApplicationContext()).load(R.drawable.button_get_started).into(buttonGetStarted);

    // Check mode.
    mode = configPrefs.Mode().get();
    if(mode.equals("B") || mode.equals("D")) {
      textCharacterName.setVisibility(View.INVISIBLE);
      editCharacterName.setVisibility(View.INVISIBLE);
      textCharacterGender.setVisibility(View.INVISIBLE);
      characterMale.setVisibility(View.INVISIBLE);
      characterFemale.setVisibility(View.INVISIBLE);
    }

    // Set fonts types.
    textUserName.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));
    textUserGender.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));
    textCharacterName.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));
    textCharacterGender.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));
    userMale.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
    userFemale.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
    characterMale.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
    characterFemale.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
  }

  @Click(R.id.button_get_started)
  protected void buttonGetStartClicked() {
    // Check input validity.
    String userName = editUserName.getText().toString();
    if(userName.equals("")) {
      Toast.makeText(this, "Please write your name.", Toast.LENGTH_SHORT).show();
      return;
    }

    String characterName = "";
    if(mode.equals("A") || mode.equals("C")) {
      characterName = editCharacterName.getText().toString();
      if(characterName.equals("")) {
        Toast.makeText(this, "Please write your character's name.", Toast.LENGTH_SHORT).show();
        return;
      }
    }

    String userGender = "";
    if(userMale.isChecked()) {
      userGender = "MALE";
    }
    else {
      userGender = "FEMALE";
    }

    String characterGender = "";
    if(characterMale.isChecked()) {
      characterGender = "MALE";
    }
    else {
      characterGender = "FEMALE";
    }

    // Store inputs.
    configPrefs.UserName().put(userName);
    configPrefs.UserGender().put(userGender);
    if(mode.equals("A") || mode.equals("C")) {
      configPrefs.CharacterName().put(characterName);
      configPrefs.CharacterGender().put(characterGender);
    }

    // Set profile flag true.
    startFlagsPrefs.isProfile().put(true);

    // Swap to CharacterSelectActivity.
    CharacterSelectActivity_.intent(this).start();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
