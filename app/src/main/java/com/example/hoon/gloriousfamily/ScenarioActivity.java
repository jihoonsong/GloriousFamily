package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_scenario)
public class ScenarioActivity extends Activity {
  @ViewById(R.id.layer_indicator)
  ImageView layerIndicator;

  @ViewById(R.id.text_analysis)
  TextView textAnalysis;

  @ViewById(R.id.layer_circle_blue_left)
  ImageView layerCircleBlueLeft;

  @ViewById(R.id.layer_circle_blue_center)
  ImageView layerCircleBlueCenter;

  @ViewById(R.id.layer_circle_blue_right)
  ImageView layerCircleBlueRight;

  @ViewById(R.id.layer_circle_white_left)
  ImageView layerCircleWhiteLeft;

  @ViewById(R.id.layer_circle_white_center)
  ImageView layerCircleWhiteCenter;

  @ViewById(R.id.layer_circle_white_right)
  ImageView layerCircleWhiteRight;

  @ViewById(R.id.text_find_match)
  TextView textFindMatch;

  @ViewById(R.id.text_match_name)
  TextView textMatchName;

  @ViewById(R.id.character_match)
  ImageView characterMatch;

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

  @ViewById(R.id.layer_scenario)
  ImageView layerScenario;

  @ViewById(R.id.button_prev)
  ImageView buttonPrev;

  @ViewById(R.id.button_next)
  ImageView buttonNext;

  @Pref
  StartFlagsPrefs_ startFlagsPrefs;

  @Pref
  ConfigPrefs_ configPrefs;

  private String mode = "";

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Set fonts types.
    textAnalysis.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
    textFindMatch.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Regular.otf"));
    textMatchName.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));
    textBanner.setTypeface(Typeface.createFromAsset(getAssets(), "NotoSansCJKkr_Medium.otf"));

    // Check mode.
    mode = configPrefs.Mode().get();

    // Set text.
    if (mode.equals("B") || mode.equals("D")) {
      textBanner.setText(configPrefs.UserName().get());
    } else {
      textBanner.setText(configPrefs.CharacterName().get());
    }

    // Load indicator.
    Glide.with(getApplicationContext()).load(R.drawable.layer_indicator).into(layerIndicator);

    // Load circles.
    Glide.with(getApplicationContext()).load(R.drawable.layer_circle_blue).into(layerCircleBlueLeft);
    Glide.with(getApplicationContext()).load(R.drawable.layer_circle_white).into(layerCircleWhiteCenter);
    Glide.with(getApplicationContext()).load(R.drawable.layer_circle_white).into(layerCircleWhiteRight);

    // Load banner.
    Glide.with(getApplicationContext()).load(R.drawable.layer_banner).into(layerBanner);

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

    // Analysis steps count.
    analysisStepsCount();
  }

  private void analysisStepsCount() {
    Handler mHandler = new Handler();
    mHandler.postDelayed(new Runnable() {
      public void run() {
        layerCircleBlueLeft.setVisibility(View.INVISIBLE);
        Glide.with(getApplicationContext()).load(R.drawable.layer_circle_white).into(layerCircleWhiteLeft);
        layerCircleWhiteCenter.setVisibility(View.INVISIBLE);
        Glide.with(getApplicationContext()).load(R.drawable.layer_circle_blue).into(layerCircleBlueCenter);
      }
    }, 1500);

    mHandler.postDelayed(new Runnable() {
      public void run() {
        layerCircleBlueCenter.setVisibility(View.INVISIBLE);
        layerCircleWhiteCenter.setVisibility(View.VISIBLE);
        layerCircleWhiteRight.setVisibility(View.INVISIBLE);
        Glide.with(getApplicationContext()).load(R.drawable.layer_circle_blue).into(layerCircleBlueRight);
      }
    }, 2500);

    mHandler.postDelayed(new Runnable() {
      public void run() {
        layerCircleBlueCenter.setVisibility(View.INVISIBLE);
        layerCircleWhiteCenter.setVisibility(View.VISIBLE);
        layerCircleWhiteRight.setVisibility(View.INVISIBLE);
        layerCircleBlueRight.setVisibility(View.VISIBLE);
      }
    }, 3000);

    mHandler.postDelayed(new Runnable() {
      public void run() {
        layerCircleBlueRight.setVisibility(View.INVISIBLE);
        layerCircleWhiteRight.setVisibility(View.VISIBLE);
        layerCircleWhiteLeft.setVisibility(View.INVISIBLE);
        layerCircleBlueLeft.setVisibility(View.VISIBLE);
      }
    }, 4000);

    mHandler.postDelayed(new Runnable() {
      public void run() {
        layerCircleBlueLeft.setVisibility(View.INVISIBLE);
        layerCircleWhiteLeft.setVisibility(View.VISIBLE);
        layerCircleWhiteCenter.setVisibility(View.INVISIBLE);
        layerCircleBlueCenter.setVisibility(View.VISIBLE);
      }
    }, 5000);

    mHandler.postDelayed(new Runnable() {
      public void run() {
        layerCircleBlueCenter.setVisibility(View.INVISIBLE);
        layerCircleWhiteCenter.setVisibility(View.VISIBLE);
        layerCircleWhiteRight.setVisibility(View.INVISIBLE);
        layerCircleBlueRight.setVisibility(View.VISIBLE);
      }
    }, 6000);

    mHandler.postDelayed(new Runnable() {
      public void run() {
        layerCircleBlueCenter.setVisibility(View.INVISIBLE);
        layerCircleWhiteCenter.setVisibility(View.VISIBLE);
        layerCircleWhiteRight.setVisibility(View.INVISIBLE);
        layerCircleBlueRight.setVisibility(View.VISIBLE);
      }
    }, 7000);

    mHandler.postDelayed(new Runnable() {
      public void run() {
        // Find match.
        findMatch();
      }
    }, 7100);
  }

  private void findMatch() {
    textAnalysis.setVisibility(View.INVISIBLE);
    layerCircleWhiteLeft.setVisibility(View.INVISIBLE);
    layerCircleWhiteCenter.setVisibility(View.INVISIBLE);
    layerCircleBlueRight.setVisibility(View.INVISIBLE);
    textFindMatch.setVisibility(View.VISIBLE);

    Handler mHandler = new Handler();
    mHandler.postDelayed(new Runnable() {
      public void run() {
        // TODO : Defining match's name.
        textMatchName.setText("바둑이");
        textMatchName.setVisibility(View.VISIBLE);
        Glide.with(getApplicationContext()).load(R.drawable.character_match).into(characterMatch);
      }
    }, 1300);

    mHandler.postDelayed(new Runnable() {
      public void run() {
        // TODO : Show scenario.
        Glide.with(getApplicationContext()).load(R.drawable.layer_scenario).into(layerScenario);
        Glide.with(getApplicationContext()).load(R.drawable.button_next).into(buttonNext);
      }
    }, 2300);
  }

  @Click(R.id.button_next)
  protected void buttonNextClicked() {
    Glide.with(getApplicationContext()).load(R.drawable.button_prev).into(buttonPrev);

    // TODO : etc...

    // TODO : Set scenario flag true.
    //startFlagsPrefs.isScenario().put(true);

    // Swap to HomeActivity.
    HomeActivity_.intent(this).start();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
