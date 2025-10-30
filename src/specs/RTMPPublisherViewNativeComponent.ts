import type { ViewProps, HostComponent } from 'react-native';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import type { DirectEventHandler } from 'react-native/Libraries/Types/CodegenTypes';

export interface NativeProps extends ViewProps {
  streamURL: string;
  streamName: string;
  onConnectionFailed?: DirectEventHandler<{ data: string }>;
  onConnectionStarted?: DirectEventHandler<{ data: string }>;
  onConnectionSuccess?: DirectEventHandler<{ data: null }>;
  onDisconnect?: DirectEventHandler<{ data: null }>;
  onNewBitrateReceived?: DirectEventHandler<{ data: number }>;
  onStreamStateChanged?: DirectEventHandler<{ data: string }>;
  onBluetoothDeviceStatusChanged?: DirectEventHandler<{ data: string }>;
}

export default codegenNativeComponent<NativeProps>(
  'RTMPPublisher'
) as HostComponent<NativeProps>;

