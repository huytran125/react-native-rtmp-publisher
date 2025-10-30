package com.reactnativertmppublisher;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RTMPPackage extends TurboReactPackage {
  private static final String TAG = "RTMPPackage";
  
  @Nullable
  @Override
  public NativeModule getModule(@NonNull String name, @NonNull ReactApplicationContext reactContext) {
    Log.d(TAG, "getModule: Requested module name: " + name);
    try {
      if (name.equals(RTMPModule.NAME)) {
        Log.d(TAG, "getModule: Creating RTMPModule");
        RTMPModule module = new RTMPModule(reactContext);
        Log.d(TAG, "getModule: RTMPModule created successfully");
        return module;
      } else {
        Log.d(TAG, "getModule: Module not found: " + name);
        return null;
      }
    } catch (Exception e) {
      Log.e(TAG, "getModule: Error creating module " + name, e);
      throw e;
    }
  }

  @Override
  public ReactModuleInfoProvider getReactModuleInfoProvider() {
    Log.d(TAG, "getReactModuleInfoProvider: Called");
    return () -> {
      try {
        final Map<String, ReactModuleInfo> moduleInfos = new HashMap<>();
        boolean isTurboModule = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
        Log.d(TAG, "getReactModuleInfoProvider: IS_NEW_ARCHITECTURE_ENABLED = " + isTurboModule);
        
        moduleInfos.put(
          RTMPModule.NAME,
          new ReactModuleInfo(
            RTMPModule.NAME,
            RTMPModule.NAME,
            false, // canOverrideExistingModule
            false, // needsEagerInit
            true, // hasConstants
            false, // isCxxModule
            isTurboModule // isTurboModule
          )
        );
        Log.d(TAG, "getReactModuleInfoProvider: Module info created successfully");
        return moduleInfos;
      } catch (Exception e) {
        Log.e(TAG, "getReactModuleInfoProvider: Error", e);
        throw e;
      }
    };
  }

  @NonNull
  @Override
  public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
    Log.d(TAG, "createViewManagers: Starting to create view managers");
    try {
      Log.d(TAG, "createViewManagers: Creating RTMPManager");
      RTMPManager manager = new RTMPManager();
      List<ViewManager> managers = Collections.singletonList(manager);
      Log.d(TAG, "createViewManagers: RTMPManager created successfully");
      return managers;
    } catch (Exception e) {
      Log.e(TAG, "createViewManagers: Error creating view managers", e);
      throw e;
    }
  }
}


