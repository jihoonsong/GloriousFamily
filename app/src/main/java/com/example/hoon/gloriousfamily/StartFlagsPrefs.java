package com.example.hoon.gloriousfamily;

import org.androidannotations.annotations.sharedpreferences.SharedPref;
import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;

@SharedPref(value=SharedPref.Scope.APPLICATION_DEFAULT)
public interface StartFlagsPrefs {
  @DefaultBoolean(false)
  boolean isScenario();

  @DefaultBoolean(false)
  boolean isProfile();

  @DefaultBoolean(false)
  boolean isCharacterSelect();

  @DefaultBoolean(false)
  boolean isModeSelect();
}
