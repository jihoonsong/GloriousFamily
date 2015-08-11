package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_character_select)
public class CharacterSelectActivity extends Activity {
  @ViewById(R.id.button_yesitisme)
  ImageView buttonYesItIsMe;

  @ViewById(R.id.text_yesitisme)
  TextView textYesItIsMe;

  @ViewById(R.id.character_body_one)
  ImageView characterBodyOne;

  @ViewById(R.id.character_color_one)
  ImageView characterColorOne;

//  @ViewById(R.id.character_face_one)
//  ImageView characterFaceOne;
//
//  @ViewById(R.id.character_costume_one)
//  ImageView characterCostumeOne;

  @ViewById(R.id.character_color_left_arrow)
  ImageView characterColorLeftArrow;

  @ViewById(R.id.character_color_right_arrow)
  ImageView characterColorRightArrow;

  @ViewById(R.id.character_face_left_arrow)
  ImageView characterFaceLeftArrow;

  @ViewById(R.id.character_face_right_arrow)
  ImageView characterFaceRightArrow;

  @ViewById(R.id.character_costume_left_arrow)
  ImageView characterCostumeLeftArrow;

  @ViewById(R.id.character_costume_right_arrow)
  ImageView characterCostumeRightArrow;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Load images.
    Glide.with(getApplicationContext()).load(R.drawable.button_yesitisme).into(buttonYesItIsMe);
    Glide.with(getApplicationContext()).load(R.drawable.character_body_one).into(characterBodyOne);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_one).into(characterColorOne);
//    Glide.with(getApplicationContext()).load(R.drawable.character_face_one).into(characterFaceOne);
//    Glide.with(getApplicationContext()).load(R.drawable.character_costume_one).into(characterCostumeOne);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_left_arrow).into(characterColorLeftArrow);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_right_arrow).into(characterColorRightArrow);
    Glide.with(getApplicationContext()).load(R.drawable.character_face_left_arrow).into(characterFaceLeftArrow);
    Glide.with(getApplicationContext()).load(R.drawable.character_face_right_arrow).into(characterFaceRightArrow);
    Glide.with(getApplicationContext()).load(R.drawable.character_costume_left_arrow).into(characterCostumeLeftArrow);
    Glide.with(getApplicationContext()).load(R.drawable.character_costume_right_arrow).into(characterCostumeRightArrow);

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
