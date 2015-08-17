package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_home)
public class HomeActivity extends Activity {
  @ViewById(R.id.layer_indicator)
  ImageView layerIndicator;

  @ViewById(R.id.text_indicator)
  TextView textIndicator;

  @ViewById(R.id.text_character_partner)
  TextView textCharacterPartner;

  @ViewById(R.id.character_partner)
  ImageView characterPartner;

  @ViewById(R.id.layer_banner)
  ImageView layerBanner;

  @ViewById(R.id.text_banner)
  TextView textBanner;

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

  @Pref
  ConfigPrefs_ configPrefs;

  private String mode = "";

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Set fonts type.
    textIndicator.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
    textCharacterPartner.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));
    textBanner.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));

    // Load indicator.
    Glide.with(getApplicationContext()).load(R.drawable.layer_indicator).into(layerIndicator);

    // Load character partner.
    Glide.with(getApplicationContext()).load(R.drawable.character_partner).into(characterPartner);

    // Load banner.
    Glide.with(getApplicationContext()).load(R.drawable.layer_banner).into(layerBanner);

    // Check mode.
    mode = configPrefs.Mode().get();

    // Set text.
    if (mode.equals("A") || mode.equals("B")) {
      textIndicator.setText("Your cooperator:");
    } else {
      textIndicator.setText("Your competitor:");
    }

    if (mode.equals("B") || mode.equals("D")) {
      textBanner.setText(configPrefs.UserName().get());
    } else {
      textBanner.setText(configPrefs.CharacterName().get());
    }

    if (mode.equals("A") || mode.equals("C")) {
      textCharacterPartner.setText(configPrefs.PartnerCharacterName().get());
    } else {
      textCharacterPartner.setText(configPrefs.PartnerUserName().get());
    }

    // Load character.
    String color = configPrefs.Color().get();
    switch (color) {
      case "BLUE":
        Glide.with(getApplicationContext()).load(R.drawable.character_blue).into(characterBlue);
        break;
      case "RED":
        Glide.with(getApplicationContext()).load(R.drawable.character_red).into(characterRed);
        break;
      case "GREEN":
        Glide.with(getApplicationContext()).load(R.drawable.character_green).into(characterGreen);
        break;
    }

    // Load costume.
    String costume = configPrefs.Costume().get();
    switch (costume) {
      case "KINDER":
        Glide.with(getApplicationContext()).load(R.drawable.costume_kinder).into(costumeKinder);
        break;
      case "INTERN":
        Glide.with(getApplicationContext()).load(R.drawable.costume_intern).into(costumeIntern);
        break;
      case "BOKHAK":
        Glide.with(getApplicationContext()).load(R.drawable.costume_bokhak).into(costumeBokhak);
        break;
    }
  }
}
