package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;

@EActivity(R.layout.activity_character_select)
public class CharacterSelectActivity extends Activity {
  @ViewById(R.id.button_yesitisme)
  ImageView buttonYesItIsMe;

  @ViewById(R.id.text_yesitisme)
  TextView textYesItIsMe;

  @ViewById(R.id.character_body)
  ImageView characterBody;

  @ViewById(R.id.character_color)
  ImageView characterColor;

//  @ViewById(R.id.character_face_one)
//  ImageView characterFaceOne;
//
//  @ViewById(R.id.character_costume_one)
//  ImageView characterCostumeOne;

  @ViewById(R.id.character_left_arrow_color)
  ImageView characterLeftArrowColor;

  @ViewById(R.id.character_right_arrow_color)
  ImageView characterRightArrowColor;

  @ViewById(R.id.character_left_arrow_face)
  ImageView characterLeftArrowFace;

  @ViewById(R.id.character_right_arrow_face)
  ImageView characterRightArrowFace;

  @ViewById(R.id.character_left_arrow_costume)
  ImageView characterLeftArrowCostume;

  @ViewById(R.id.character_right_arrow_costume)
  ImageView characterRightArrowCostume;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Load images.
    Glide.with(getApplicationContext()).load(R.drawable.button_yesitisme).into(buttonYesItIsMe);
    Glide.with(getApplicationContext()).load(R.drawable.character_body_red).into(characterBody);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_red).into(characterColor);
//    Glide.with(getApplicationContext()).load(R.drawable.character_face_one).into(characterFaceOne);
//    Glide.with(getApplicationContext()).load(R.drawable.character_costume_one).into(characterCostumeOne);
    Glide.with(getApplicationContext()).load(R.drawable.character_left_arrow_color).into(characterLeftArrowColor);
    Glide.with(getApplicationContext()).load(R.drawable.character_right_arrow_color).into(characterRightArrowColor);
    Glide.with(getApplicationContext()).load(R.drawable.character_left_arrow_face).into(characterLeftArrowFace);
    Glide.with(getApplicationContext()).load(R.drawable.character_right_arrow_face).into(characterRightArrowFace);
    Glide.with(getApplicationContext()).load(R.drawable.character_left_arrow_costume).into(characterLeftArrowCostume);
    Glide.with(getApplicationContext()).load(R.drawable.character_right_arrow_costume).into(characterRightArrowCostume);

    // Set font type.
    textYesItIsMe.setTypeface(Typeface.createFromAsset(getAssets(), "ROBOTO_MEDIUM.ttf"));
  }

  @Click(R.id.button_yesitisme)
  protected void buttonYesItIsMeClicked() {
    // Swap activity.
    HomeActivity_.intent(this).start();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
