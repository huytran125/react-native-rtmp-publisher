import type { ViewProps, HostComponent } from 'react-native';
// @ts-ignore - codegenNativeComponent is only available in RN 0.68+
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
// @ts-ignore - DirectEventHandler is only available in RN 0.68+
import type { DirectEventHandler } from 'react-native/Libraries/Types/CodegenTypes';

export interface NativeProps extends ViewProps {
  streamURL: string;
  streamName: string;
  onConnectionFailed?: DirectEventHandler<{ data?: string }>;
  onConnectionStarted?: DirectEventHandler<{ data?: string }>;
  onConnectionSuccess?: DirectEventHandler<{ data?: string }>;
  onDisconnect?: DirectEventHandler<{ data?: string }>;
  onNewBitrateReceived?: DirectEventHandler<{ data?: number }>;
  onStreamStateChanged?: DirectEventHandler<{ data?: string }>;
  onBluetoothDeviceStatusChanged?: DirectEventHandler<{ data?: string }>;
}

// @ts-ignore - codegenNativeComponent is only available in RN 0.68+
export default codegenNativeComponent<NativeProps>(
  'RTMPPublisher'
) as HostComponent<NativeProps>;

