package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.AfterViews;

@EActivity(R.layout.activity_signup)
public class SignupActivity extends Activity {
    @ViewById(R.id.button_facebook)
    ImageView buttonFacebook;

    @ViewById(R.id.layer_facebook)
    ImageView layerFacebook;

    @ViewById(R.id.button_login)
    ImageView buttonLogin;

    @ViewById(R.id.background_signup) ImageView backgroundSignUp;

    @AfterViews
    protected void init() {
        // Lock rotation.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Picasso.with(getApplicationContext()).load(R.drawable.button_facebook).into(buttonFacebook);
        Picasso.with(getApplicationContext()).load(R.drawable.layer_facebook).into(layerFacebook);
        Picasso.with(getApplicationContext()).load(R.drawable.button_login).into(buttonLogin);
        Picasso.with(getApplicationContext()).load(R.drawable.background_signup).into(backgroundSignUp);
    }

    @Click(R.id.button_facebook)
    void buttonFacebookClicked() {
        buttonFacebook.setVisibility(View.INVISIBLE);
        layerFacebook.setVisibility(View.VISIBLE);
        buttonLogin.setVisibility(View.VISIBLE);
        // 값 입력받는 창
    }

    @Click(R.id.button_login)
    void buttonLoginClicked() {
        ProfileActivity_.intent(this).start();
        // TODO :
        //finish();
        //onStop();
    }
}
