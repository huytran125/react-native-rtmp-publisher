package com.reactnativertmppublisher;

import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.reactnativertmppublisher.modules.Publisher;
import com.reactnativertmppublisher.modules.SurfaceHolderHelper;

import java.util.Map;

public class RTMPManager extends SimpleViewManager<SurfaceView> {
  private static final String TAG = "RTMPManager";
  //TODO: "Do not place Android context classes in static fields (static reference to Publisher which has field _surfaceView pointing to SurfaceView); this is a memory leak"
  public static Publisher publisher;
  public final String REACT_CLASS_NAME = "RTMPPublisher";
  SurfaceView surfaceView;
  private ThemedReactContext _reactContext;
  
  public RTMPManager() {
    super();
    Log.d(TAG, "RTMPManager: Constructor called");
  }

  View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() {
    @Override
    public void onLayoutChange(@NonNull View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {

    }
  };

  @NonNull
  @Override
  public String getName() {
    Log.d(TAG, "getName: Returning view name: " + REACT_CLASS_NAME);
    return REACT_CLASS_NAME;
  }

  @NonNull
  @Override
  protected SurfaceView createViewInstance(@NonNull ThemedReactContext reactContext) {
    Log.d(TAG, "createViewInstance: Starting to create view instance");
    try {
      _reactContext = reactContext;
      Log.d(TAG, "createViewInstance: Creating SurfaceView");
      surfaceView = new SurfaceView(_reactContext);
      
      Log.d(TAG, "createViewInstance: Creating Publisher");
      publisher = new Publisher(_reactContext, surfaceView);
      Log.d(TAG, "createViewInstance: Publisher created successfully");
      
      Log.d(TAG, "createViewInstance: Adding layout change listener");
      surfaceView.addOnLayoutChangeListener(onLayoutChangeListener);

      Log.d(TAG, "createViewInstance: Creating SurfaceHolderHelper");
      SurfaceHolderHelper surfaceHolderHelper = new SurfaceHolderHelper(_reactContext, publisher.getRtmpCamera(), surfaceView.getId());
      surfaceView.getHolder().addCallback(surfaceHolderHelper);
      Log.d(TAG, "createViewInstance: SurfaceHolderHelper added");

      Log.d(TAG, "createViewInstance: View created successfully");
      return surfaceView;
    } catch (Exception e) {
      Log.e(TAG, "createViewInstance: Error creating view", e);
      throw e;
    }
  }

  @ReactProp(name = "streamURL")
  public void setStreamURL(SurfaceView surfaceView, @Nullable String url) {
    Log.d(TAG, "setStreamURL: Setting stream URL: " + url);
    try {
      publisher.setStreamUrl(url);
      Log.d(TAG, "setStreamURL: Stream URL set successfully");
    } catch (Exception e) {
      Log.e(TAG, "setStreamURL: Error setting stream URL", e);
    }
  }

  @ReactProp(name = "streamName")
  public void setStreamName(SurfaceView surfaceView, @Nullable String name) {
    Log.d(TAG, "setStreamName: Setting stream name: " + name);
    try {
      publisher.setStreamName(name);
      Log.d(TAG, "setStreamName: Stream name set successfully");
    } catch (Exception e) {
      Log.e(TAG, "setStreamName: Error setting stream name", e);
    }
  }

  @Nullable
  @Override
  public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.<String, Object>builder()
      .put("onDisconnect", MapBuilder.of("registrationName", "onDisconnect"))
      .put("onConnectionFailed", MapBuilder.of("registrationName", "onConnectionFailed"))
      .put("onConnectionStarted", MapBuilder.of("registrationName", "onConnectionStarted"))
      .put("onConnectionSuccess", MapBuilder.of("registrationName", "onConnectionSuccess"))
      .put("onNewBitrateReceived", MapBuilder.of("registrationName", "onNewBitrateReceived"))
      .put("onStreamStateChanged", MapBuilder.of("registrationName", "onStreamStateChanged"))
      .put("onBluetoothDeviceStatusChanged", MapBuilder.of("registrationName", "onBluetoothDeviceStatusChanged"))
      .build();
  }
}
