package com.reactnativertmppublisher;

import android.util.Log;
import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RTMPPackage implements ReactPackage {
  private static final String TAG = "RTMPPackage";

  @NonNull
  @Override
  public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
    Log.d(TAG, "createNativeModules: Starting to create RTMP modules");
    try {
      List<NativeModule> modules = new ArrayList<>();
      Log.d(TAG, "createNativeModules: Creating RTMPModule");
      modules.add(new RTMPModule(reactContext));
      Log.d(TAG, "createNativeModules: RTMPModule created successfully");
      return modules;
    } catch (Exception e) {
      Log.e(TAG, "createNativeModules: Error creating modules", e);
      throw e;
    }
  }

  @NonNull
  @Override
  public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
    Log.d(TAG, "createViewManagers: Starting to create view managers");
    try {
      Log.d(TAG, "createViewManagers: Creating RTMPManager");
      List<ViewManager> managers = Collections.singletonList(new RTMPManager());
      Log.d(TAG, "createViewManagers: RTMPManager created successfully");
      return managers;
    } catch (Exception e) {
      Log.e(TAG, "createViewManagers: Error creating view managers", e);
      throw e;
    }
  }
}


