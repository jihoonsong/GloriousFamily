package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.ConnectionResult;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;

@EActivity(R.layout.activity_signup)
public class SignupActivity extends Activity implements ConnectionCallbacks,
    OnConnectionFailedListener {
  // XXX : Must be called at first activity !
  private GoogleApiClient mGoogleApiClient = null;
  private static final int REQUEST_RESOLVE_ERROR = 1;

  @ViewById(R.id.button_facebook)
  ImageView buttonFacebook;

  @ViewById(R.id.layer_facebook)
  ImageView layerFacebook;

  @ViewById(R.id.button_login)
  ImageView buttonLogin;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // Load images.
    Glide.with(getApplicationContext()).load(R.drawable.button_facebook).into(buttonFacebook);
    Glide.with(getApplicationContext()).load(R.drawable.layer_facebook);
    Glide.with(getApplicationContext()).load(R.drawable.button_login);

    // XXX : Global Image Cache.
    // XXX : Must be called at first activity !
    this.cache();

    // Build Google fit API client.
    // XXX : Must be called at first activity !
    buildGoogleFitnessApiClient();

    // Connect to Google fitness.
    // XXX : Must be called at first activity !
    connectGooglefitnessApiClient();
  }

  @Override
  protected void onStop() {
    // Disconnect connection.
    if (mGoogleApiClient.isConnected())
      mGoogleApiClient.disconnect();

    super.onStop();
  }

  @Background
  void cache() {
    // Background images.
    Glide.with(getApplicationContext()).load(R.drawable.background_profile);
    Glide.with(getApplicationContext()).load(R.drawable.background_charselect);
    Glide.with(getApplicationContext()).load(R.drawable.background_birth);
    Glide.with(getApplicationContext()).load(R.drawable.background_death);
    Glide.with(getApplicationContext()).load(R.drawable.background_family_tree);
    Glide.with(getApplicationContext()).load(R.drawable.background_mate_select);
    Glide.with(getApplicationContext()).load(R.drawable.background_home_tent);

    // Button images.
    Glide.with(getApplicationContext()).load(R.drawable.button_getstarted);
    Glide.with(getApplicationContext()).load(R.drawable.button_findyourmate);
    Glide.with(getApplicationContext()).load(R.drawable.button_profile);
    Glide.with(getApplicationContext()).load(R.drawable.button_familytree);
    Glide.with(getApplicationContext()).load(R.drawable.button_yesitisme);

    // Layer images.
    Glide.with(getApplicationContext()).load(R.drawable.layer_banner);

    // Character images.
    Glide.with(getApplicationContext()).load(R.drawable.character_body_one);
    Glide.with(getApplicationContext()).load(R.drawable.character_color_one);
    Glide.with(getApplicationContext()).load(R.drawable.character_face_one);
    Glide.with(getApplicationContext()).load(R.drawable.character_cloth_one);
  }

  void buildGoogleFitnessApiClient() {
    // TODO : Determine scope types.
    mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addApi(Fitness.HISTORY_API)
        .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
        .addScope(new Scope(Scopes.FITNESS_BODY_READ))
        .addScope(new Scope(Scopes.FITNESS_LOCATION_READ))
        .addScope(new Scope(Scopes.FITNESS_NUTRITION_READ))
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .build();
  }

  void connectGooglefitnessApiClient() {
    Log.d(this.toString(), "Connecting.");
    mGoogleApiClient.connect();
  }

  @Click(R.id.button_facebook)
  void buttonFacebookClicked() {
    buttonFacebook.setVisibility(View.INVISIBLE);
    Glide.with(getApplicationContext()).load(R.drawable.layer_facebook).into(layerFacebook);
    layerFacebook.setVisibility(View.VISIBLE);
    Glide.with(getApplicationContext()).load(R.drawable.button_login).into(buttonLogin);
    buttonLogin.setVisibility(View.VISIBLE);

    // TODO : Get input.
  }

  @Click(R.id.button_login)
  void buttonLoginClicked() {
    ProfileActivity_.intent(this).start();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }

  public void onConnected(Bundle bundle) {
    Log.d(this.toString(), "Connected!");

    // TODO : Fetch necessary data.
  }

  @Override
  public void onConnectionSuspended(int i) {
    if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
      Log.d(this.toString(), "Connection lost. Cause: Network Lost.");
    } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
      Log.d(this.toString(), "Connection lost. Reason: Service Disconnected");
    }
  }

  @Override
  public void onConnectionFailed(ConnectionResult result) {
    Log.d(this.toString(), "Connection failed. Cause: " + result.toString());

    if (result.hasResolution()) {
      try {
        // Attempting to resolve an error.
        result.startResolutionForResult(SignupActivity.this, REQUEST_RESOLVE_ERROR);
      } catch (IntentSender.SendIntentException e) {
        // There was an error with the resolution intent. Try again.
        mGoogleApiClient.connect();
      }
    } else {
      // Show dialog using GooglePlayServicesUtil.getErrorDialog().
      GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), SignupActivity.this, 0).show();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_RESOLVE_ERROR) {
      if (resultCode == RESULT_OK) {
        // Make sure the app is not already connected or attempting to connect.
        if (!mGoogleApiClient.isConnecting() && !mGoogleApiClient.isConnected()) {
          Log.d(this.toString(), "Reconnecting.");
          mGoogleApiClient.connect();
        }
      }
    }
  }

}
