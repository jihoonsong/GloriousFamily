package com.example.hoon.gloriousfamily;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Background;
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
        Glide.with(getApplicationContext()).load(R.drawable.button_facebook).into(buttonFacebook);
        Glide.with(getApplicationContext()).load(R.drawable.layer_facebook);
        Glide.with(getApplicationContext()).load(R.drawable.button_login);

        // XXX : Global Image Cache.
        this.cache();
    }

    @Click(R.id.button_facebook)
    void buttonFacebookClicked() {
        buttonFacebook.setVisibility(View.INVISIBLE);
        Glide.with(getApplicationContext()).load(R.drawable.layer_facebook).into(layerFacebook);
        layerFacebook.setVisibility(View.VISIBLE);
        Glide.with(getApplicationContext()).load(R.drawable.button_login).into(buttonLogin);
        buttonLogin.setVisibility(View.VISIBLE);
        // 값 입력받는 창
    }

    @Click(R.id.button_login)
    void buttonLoginClicked() {
        ProfileActivity_.intent(this).start();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
        System.gc();
    }

    @Background
    void cache(){
        // TODO : [GLOBAL] Replace the background image from png(with alpha) to jpg(without).
        Glide.with(getApplicationContext()).load(R.drawable.background_profile);
        Glide.with(getApplicationContext()).load(R.drawable.button_getstarted);
        Glide.with(getApplicationContext()).load(R.drawable.background_home_tent);
        Glide.with(getApplicationContext()).load(R.drawable.button_findyourmate);
        Glide.with(getApplicationContext()).load(R.drawable.character_body_one);
        Glide.with(getApplicationContext()).load(R.drawable.button_profile);
        Glide.with(getApplicationContext()).load(R.drawable.button_familytree);
        Glide.with(getApplicationContext()).load(R.drawable.layer_banner);
        Glide.with(getApplicationContext()).load(R.drawable.background_charselect);
        Glide.with(getApplicationContext()).load(R.drawable.button_yesitisme);
        Glide.with(getApplicationContext()).load(R.drawable.character_body_one);
        Glide.with(getApplicationContext()).load(R.drawable.character_cloth_one);
        Glide.with(getApplicationContext()).load(R.drawable.character_color_one);
        Glide.with(getApplicationContext()).load(R.drawable.character_face_one);
    }
}
