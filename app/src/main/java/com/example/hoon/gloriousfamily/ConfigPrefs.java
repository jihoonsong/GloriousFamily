package com.example.hoon.gloriousfamily;

import org.androidannotations.annotations.sharedpreferences.SharedPref;
import org.androidannotations.annotations.sharedpreferences.DefaultString;

@SharedPref(value=SharedPref.Scope.APPLICATION_DEFAULT)
public interface ConfigPrefs {
  @DefaultString("")
  String Mode();

  @DefaultString("")
  String UserName();

  @DefaultString("")
  String UserGender();

  @DefaultString("")
  String CharacterName();

  @DefaultString("")
  String CharacterGender();

  @DefaultString("")
  String Costume();

  @DefaultString("")
  String Color();

  @DefaultString("")
  String PartnerUserName();

  @DefaultString("")
  String PartnerCharacterName();
}
