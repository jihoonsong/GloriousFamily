package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_dating)
public class DatingActivity extends Activity {
  @ViewById(R.id.layer_banner)
  ImageView layerBanner;

  @ViewById(R.id.button_profile)
  ImageView buttonProfile;

  @ViewById(R.id.button_familytree)
  ImageView buttonFamilyTree;

  @ViewById(R.id.background_dating_left)
  ImageView backgroundDatingLeft;

  @ViewById(R.id.layer_heart_left)
  ImageView layerHeartLeft;

  @ViewById(R.id.character_body_left)
  ImageView characterBodyLeft;

  @ViewById(R.id.background_dating_right)
  ImageView backgroundDatingRight;

  @ViewById(R.id.layer_heart_right)
  ImageView layerHeartRight;

  @ViewById(R.id.character_body_right)
  ImageView characterBodyRight;

  @ViewById(R.id.text_startofthefamily)
  TextView textStartOfTheFamily;

  @ViewById(R.id.text_generation)
  TextView textGeneration;

  @ViewById(R.id.text_d_day)
  TextView textDDay;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Load images.
    Glide.with(getApplicationContext()).load(R.drawable.layer_banner).into(layerBanner);
    Glide.with(getApplicationContext()).load(R.drawable.button_profile).into(buttonProfile);
    Glide.with(getApplicationContext()).load(R.drawable.button_familytree).into(buttonFamilyTree);

    Glide.with(getApplicationContext()).load(R.drawable.background_dating_tent).into(backgroundDatingLeft);
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart90).into(layerHeartLeft);
    Glide.with(getApplicationContext()).load(R.drawable.character_body_red).into(characterBodyLeft);

    Glide.with(getApplicationContext()).load(R.drawable.background_dating_castle).into(backgroundDatingRight);
    Glide.with(getApplicationContext()).load(R.drawable.layer_heart30).into(layerHeartRight);
    Glide.with(getApplicationContext()).load(R.drawable.character_body_blue).into(characterBodyRight);

    // Set fonts type.
    textStartOfTheFamily.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_MEDIUM.ttf"));
    textGeneration.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_MEDIUM.ttf"));
    textDDay.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_MEDIUM.ttf"));
  }
}
