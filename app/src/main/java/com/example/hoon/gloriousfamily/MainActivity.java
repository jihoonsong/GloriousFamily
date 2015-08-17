package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.ConnectionResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements ConnectionCallbacks,
                                                      OnConnectionFailedListener {
  // XXX : Must be called at first activity !
  private GoogleApiClient mGoogleApiClient = null;
  private static final int REQUEST_RESOLVE_ERROR = 1;

  @Pref
  StartFlagsPrefs_ startFlagsPrefs;

  @AfterViews
  protected void init() {
    // Lock rotation.
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
    if(mGoogleApiClient.isConnected())
      mGoogleApiClient.disconnect();

    super.onStop();
  }

  void buildGoogleFitnessApiClient() {
    mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addApi(Fitness.HISTORY_API)
        .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .build();
  }

  void connectGooglefitnessApiClient() {
    Log.d(this.toString(), "Connecting.");
    mGoogleApiClient.connect();
  }

  public void onConnected(Bundle bundle) {
    Log.d(this.toString(), "Connected!");

    // Wait 1200 milliseconds and check start flags.
    Handler mHandler = new Handler();
    mHandler.postDelayed(new Runnable() {
      public void run() {
        CheckStartFlags();
      }
    }, 2000);

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

    if(result.hasResolution()) {
      try {
        // Attempting to resolve an error.
        result.startResolutionForResult(MainActivity.this, REQUEST_RESOLVE_ERROR);
      } catch(IntentSender.SendIntentException e) {
        // There was an error with the resolution intent. Try again.
        mGoogleApiClient.connect();
      }
    }
    else {
      // Show dialog using GooglePlayServicesUtil.getErrorDialog().
      GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), MainActivity.this, 0).show();
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

  private void CheckStartFlags() {
    if(startFlagsPrefs.isScenario().get()) {
      // Swap to HomeActivity.
      HomeActivity_.intent(this).start();
    }
    else if(startFlagsPrefs.isCharacterSelect().get()) {
      // Swap to ScenarioActivity.
      ScenarioActivity_.intent(this).start();
    }
    else if(startFlagsPrefs.isProfile().get()) {
      // Swap to CharacterSelectActivity.
      CharacterSelectActivity_.intent(this).start();
    }
    else if(startFlagsPrefs.isModeSelect().get()) {
      // Swap to ProfileActivity.
      ProfileActivity_.intent(this).start();
    }
    else {
      // Swap to ModeSelectActivity.
      ModeSelectActivity_.intent(this).start();
    }

    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
    System.gc();
  }
}
