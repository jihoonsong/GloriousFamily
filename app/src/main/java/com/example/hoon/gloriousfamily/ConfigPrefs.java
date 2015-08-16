package com.example.hoon.gloriousfamily;

import org.androidannotations.annotations.sharedpreferences.SharedPref;
import org.androidannotations.annotations.sharedpreferences.DefaultString;

@SharedPref(value=SharedPref.Scope.APPLICATION_DEFAULT)
public interface ConfigPrefs {
  @DefaultString("")
  String Mode();
}
