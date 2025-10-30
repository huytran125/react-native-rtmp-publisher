package com.reactnativertmppublisher.modules;

import androidx.annotation.NonNull;

import com.pedro.common.ConnectChecker;
import com.reactnativertmppublisher.interfaces.ConnectionListener;

import java.util.ArrayList;
import java.util.List;

public class ConnectionChecker implements ConnectChecker {
  private final List<ConnectionListener> listeners = new ArrayList<>();

  public void addListener(ConnectionListener listener) {
    listeners.add(listener);
  }

  @Override
  public void onAuthError() {
    for (ConnectionListener l : listeners) {
      l.onChange("onAuthError", null);
    }
  }

  @Override
  public void onAuthSuccess() {
    for (ConnectionListener l : listeners) {
      l.onChange("onAuthSuccess", null);
    }
  }

  @Override
  public void onConnectionFailed(@NonNull String s) {
    for (ConnectionListener l : listeners) {
      l.onChange("onConnectionFailed", s);
    }
  }

  @Override
  public void onConnectionStarted(@NonNull String s) {
    for (ConnectionListener l : listeners) {
      l.onChange("onConnectionStarted", s);
    }
  }

  @Override
  public void onConnectionSuccess() {
    for (ConnectionListener l : listeners) {
      l.onChange("onConnectionSuccess", null);
    }
  }

  @Override
  public void onDisconnect() {
    for (ConnectionListener l : listeners) {
      l.onChange("onDisconnect", null);
    }
  }

  @Override
  public void onNewBitrate(long b) {
    for (ConnectionListener l : listeners) {
      l.onChange("onNewBitrateReceived", b);
    }
  }
}
