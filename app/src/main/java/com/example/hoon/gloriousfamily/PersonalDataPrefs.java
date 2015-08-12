package com.example.hoon.gloriousfamily;

import org.androidannotations.annotations.sharedpreferences.SharedPref;
import org.androidannotations.annotations.sharedpreferences.DefaultString;

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
