package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_character_select)
public class CharacterSelectActivity extends Activity {
  @ViewById(R.id.button_settings)
  ImageView buttonSettings;

  @ViewById(R.id.costume_kinder)
  ImageView costumeKinder;

  @ViewById(R.id.costume_intern)
  ImageView costumeIntern;

  @ViewById(R.id.costume_bokhak)
  ImageView costumeBokhak;

  @ViewById(R.id.character_blue)
  ImageView characterBlue;

  @ViewById(R.id.character_red)
  ImageView characterRed;

  @ViewById(R.id.character_green)
  ImageView characterGreen;

  @ViewById(R.id.button_yes_its_me)
  ImageView buttonYesItsMe;

  @ViewById(R.id.button_yes_its_my_character)
  ImageView buttonYesItsMyCharacter;

  @Pref
  StartFlagsPrefs_ startFlagsPrefs;

  @Pref
  ConfigPrefs_ configPrefs;

  private String mode = "";
  private String costume = "INTERN";
  private String color = "RED";

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Load images.
    Glide.with(getApplicationContext()).load(R.drawable.button_settings).into(buttonSettings);
    Glide.with(getApplicationContext()).load(R.drawable.character_blue);
    Glide.with(getApplicationContext()).load(R.drawable.character_red).into(characterRed);
    Glide.with(getApplicationContext()).load(R.drawable.character_green);
    Glide.with(getApplicationContext()).load(R.drawable.costume_kinder);
    Glide.with(getApplicationContext()).load(R.drawable.costume_intern).into(costumeIntern);
    Glide.with(getApplicationContext()).load(R.drawable.costume_bokhak);

    // Check mode.
    mode = configPrefs.Mode().get();
    if(mode.equals("B") || mode.equals("D")) {
      Glide.with(getApplicationContext()).load(R.drawable.button_yes_its_me).into(buttonYesItsMe);
    }
    else {
      Glide.with(getApplicationContext()).load(R.drawable.button_yes_its_my_character).into(buttonYesItsMyCharacter);
    }
  }

  @Click(R.id.button_kinder)
  protected void buttonKinderClicked() {
    costume = "KINDER";

    Glide.with(getApplicationContext()).load(R.drawable.costume_kinder).into(costumeKinder);
    costumeKinder.setVisibility(View.VISIBLE);
    costumeIntern.setVisibility(View.INVISIBLE);
    costumeBokhak.setVisibility(View.INVISIBLE);
  }

  @Click(R.id.button_intern)
  protected void buttonInternClicked() {
    costume = "INTERN";

    costumeIntern.setVisibility(View.VISIBLE);
    costumeKinder.setVisibility(View.INVISIBLE);
    costumeBokhak.setVisibility(View.INVISIBLE);
  }

  @Click(R.id.button_bokhak)
  protected void buttonBokhakClicked() {
    costume = "BOKHAK";

    Glide.with(getApplicationContext()).load(R.drawable.costume_bokhak).into(costumeBokhak);
    costumeBokhak.setVisibility(View.VISIBLE);
    costumeIntern.setVisibility(View.INVISIBLE);
    costumeKinder.setVisibility(View.INVISIBLE);
  }

  @Click(R.id.button_blue)
  protected void buttonBlueClicked() {
    color = "BLUE";

    Glide.with(getApplicationContext()).load(R.drawable.character_blue).into(characterBlue);
    characterBlue.setVisibility(View.VISIBLE);
    characterRed.setVisibility(View.INVISIBLE);
    characterGreen.setVisibility(View.INVISIBLE);

  }

  @Click(R.id.button_red)
  protected void buttonRedClicked() {
    color = "RED";

    Glide.with(getApplicationContext()).load(R.drawable.character_red).into(characterRed);
    characterRed.setVisibility(View.VISIBLE);
    characterBlue.setVisibility(View.INVISIBLE);
    characterGreen.setVisibility(View.INVISIBLE);
  }

  @Click(R.id.button_green)
  protected void buttonGreenClicked() {
    color = "GREEN";

    Glide.with(getApplicationContext()).load(R.drawable.character_green).into(characterGreen);
    characterGreen.setVisibility(View.VISIBLE);
    characterBlue.setVisibility(View.INVISIBLE);
    characterRed.setVisibility(View.INVISIBLE);
  }

  @Click(R.id.button_started)
  protected void buttonStartedClicked() {
    // Store inputs.
    configPrefs.Costume().put(costume);
    configPrefs.Color().put(color);

    // Set character select flag true.
    startFlagsPrefs.isCharacterSelect().put(true);

    // Swap to ScenarioActivity.
    ScenarioActivity_.intent(this).start();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
