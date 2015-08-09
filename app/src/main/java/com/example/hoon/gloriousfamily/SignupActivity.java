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
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.fitness.request.DataReadRequest;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    connectGoogleFitnessApiClient();
  }

  @Override
  protected void onStop() {
    // Disconnect connection.
    disconnectGoogleFitnessApiClient();

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
        .addApi(Fitness.RECORDING_API)
        .addApi(Fitness.HISTORY_API)
        .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
        .addScope(new Scope(Scopes.FITNESS_BODY_READ))
        .addScope(new Scope(Scopes.FITNESS_LOCATION_READ))
        .addScope(new Scope(Scopes.FITNESS_NUTRITION_READ))
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .build();
  }

  void connectGoogleFitnessApiClient() {
    Log.d(this.toString(), "Connecting.");
    mGoogleApiClient.connect();
  }

  void disconnectGoogleFitnessApiClient() {
    Log.d(this.toString(), "Disconnecting.");
    if (mGoogleApiClient.isConnected())
      mGoogleApiClient.disconnect();
  }

  @Override
  public void onConnected(Bundle bundle) {
    Log.d(this.toString(), "Connected!");

    // Create subscription consists of 'DataType.TYPE_STEP_COUNT_DELTA'.
    subscribe();

    // Fetch steps count from Google Fitness.
    fetchStepsCount();
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

  private void subscribe() {
    Fitness.RecordingApi.subscribe(mGoogleApiClient, DataType.TYPE_STEP_COUNT_DELTA)
        .setResultCallback(new ResultCallback<Status>() {
          @Override
          public void onResult(Status status) {
            if (status.isSuccess()) {
              if (status.getStatusCode() == FitnessStatusCodes.SUCCESS_ALREADY_SUBSCRIBED) {
                Log.d(this.toString(), "Existing subscription for activity detected.");
              } else {
                Log.d(this.toString(), "Successfully subscribed!");
              }
            } else {
              Log.d(this.toString(), "There was a problem subscribing.");
            }
          }
        });
  }

  @Background
  public void fetchStepsCount() {
    Log.d(this.toString(), "Fitness API invoked.");

    // TODO : Store and set dating started time as a startTime, and set current time as a endTime.
    // Set startTime and endTime.
    Calendar calendar = Calendar.getInstance();
    Date now = new Date();
    calendar.setTime(now);
    long endTime = calendar.getTimeInMillis();
    calendar.add(Calendar.HOUR_OF_DAY, -1);
    long startTime = calendar.getTimeInMillis();

    // Fetch data of steps count from Google Fitness.
    PendingResult<DataReadResult> pendingResult = Fitness.HistoryApi.readData(
        mGoogleApiClient,
        new DataReadRequest.Builder()
            .read(DataType.TYPE_STEP_COUNT_DELTA)
            .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
            .build());
    DataReadResult readDataResult = pendingResult.await();
    DataSet dataSet = readDataResult.getDataSet(DataType.TYPE_STEP_COUNT_DELTA);

    // TODO : Locate Google Fitness API at service so that other activities can access to total steps count.
    // Calculate steps count from the fetch data.
    int stepsCount = 0;
    for (DataPoint dp : dataSet.getDataPoints()) {
      for (Field field : dp.getDataType().getFields()) {
        stepsCount = stepsCount + dp.getValue(field).asInt();
      }
    }
    Log.d(this.toString(), "Total steps count: " + stepsCount);
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
}
