package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

  @ViewById(R.id.button_user_male_selected)
  ImageView buttonUserMaleSelected;

  @ViewById(R.id.button_user_male_unselected)
  ImageView buttonUserMaleUnselected;

  @ViewById(R.id.button_user_female_selected)
  ImageView buttonUserFemaleSelected;

  @ViewById(R.id.button_user_female_unselected)
  ImageView buttonUserFemaleUnselected;

  @ViewById(R.id.text_user_male)
  TextView textUserMale;

  @ViewById(R.id.text_user_female)
  TextView textUserFemale;

  @ViewById(R.id.text_character_name)
  TextView textCharacterName;

  @ViewById(R.id.edit_character_name)
  EditText editCharacterName;

  @ViewById(R.id.text_character_gender)
  TextView textCharacterGender;

  @ViewById(R.id.button_character_male_selected)
  ImageView buttonCharacterMaleSelected;

  @ViewById(R.id.button_character_male_unselected)
  ImageView buttonCharacterMaleUnselected;

  @ViewById(R.id.button_character_female_selected)
  ImageView buttonCharacterFemaleSelected;

  @ViewById(R.id.button_character_female_unselected)
  ImageView buttonCharacterFemaleUnselected;

  @ViewById(R.id.text_character_male)
  TextView textCharacterMale;

  @ViewById(R.id.text_character_female)
  TextView textCharacterFemale;

  @Pref
  StartFlagsPrefs_ startFlagsPrefs;

  @Pref
  ConfigPrefs_ configPrefs;

  private String mode = "";
  private String userGender = "MALE";
  private String characterGender = "";

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Load images.
    Glide.with(getApplicationContext()).load(R.drawable.button_get_started).into(buttonGetStarted);

    // Check mode.
    mode = configPrefs.Mode().get();
    if(mode.equals("A") || mode.equals("C")) {
      textCharacterName.setVisibility(View.VISIBLE);
      editCharacterName.setVisibility(View.VISIBLE);

      characterGender = "MALE";
      buttonCharacterMaleSelected.setVisibility(View.VISIBLE);
      buttonCharacterFemaleUnselected.setVisibility(View.VISIBLE);
      textCharacterGender.setVisibility(View.VISIBLE);
      textCharacterMale.setVisibility(View.VISIBLE);
      textCharacterFemale.setVisibility(View.VISIBLE);
    }

    // Set fonts types.
    textUserName.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));
    textUserGender.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));
    textUserMale.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
    textUserFemale.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
    textCharacterName.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));
    textCharacterGender.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));
    textCharacterMale.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
    textCharacterFemale.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
  }

  @Click(R.id.button_user_male_unselected)
  protected void buttonUserMaleUnselectedClicked() {
    userGender = "MALE";

    buttonUserFemaleSelected.setVisibility(View.INVISIBLE);
    buttonUserFemaleUnselected.setVisibility(View.VISIBLE);
    buttonUserMaleUnselected.setVisibility(View.INVISIBLE);
    buttonUserMaleSelected.setVisibility(View.VISIBLE);
  }

  @Click(R.id.button_user_female_unselected)
  protected void buttonUserFemaleUnselectedClicked() {
    userGender = "FEMALE";

    buttonUserMaleSelected.setVisibility(View.INVISIBLE);
    buttonUserMaleUnselected.setVisibility(View.VISIBLE);
    buttonUserFemaleUnselected.setVisibility(View.INVISIBLE);
    buttonUserFemaleSelected.setVisibility(View.VISIBLE);
  }

  @Click(R.id.button_character_male_unselected)
  protected void buttonCharacterMaleUnselectedClicked() {
    characterGender = "MALE";

    buttonCharacterFemaleSelected.setVisibility(View.INVISIBLE);
    buttonCharacterFemaleUnselected.setVisibility(View.VISIBLE);
    buttonCharacterMaleUnselected.setVisibility(View.INVISIBLE);
    buttonCharacterMaleSelected.setVisibility(View.VISIBLE);
  }

  @Click(R.id.button_character_female_unselected)
  protected void buttonCharacterFemaleUnselectedClicked() {
    characterGender = "FEMALE";

    buttonCharacterMaleSelected.setVisibility(View.INVISIBLE);
    buttonCharacterMaleUnselected.setVisibility(View.VISIBLE);
    buttonCharacterFemaleUnselected.setVisibility(View.INVISIBLE);
    buttonCharacterFemaleSelected.setVisibility(View.VISIBLE);
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
