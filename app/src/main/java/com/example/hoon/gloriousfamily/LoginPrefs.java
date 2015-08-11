package com.example.hoon.gloriousfamily;

import org.androidannotations.annotations.sharedpreferences.SharedPref;
import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;

/**
 * Created by HooN on 2015. 8. 11..
 */

@SharedPref
public interface LoginPrefs {
  @DefaultString("")
  String email();

  @DefaultString("")
  String password();
}
