package com.example.hoon.gloriousfamily;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by HooN on 2015. 8. 11..
 */

@SharedPref
public interface PersonalDataPrefs {
  // TODO : Goal.

  @DefaultString("")
  String height();

  @DefaultString("")
  String weight();

  // TODO : Gender.

  @DefaultString("")
  String notification();
}
