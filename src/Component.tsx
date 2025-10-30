import {
  NativeSyntheticEvent,
  requireNativeComponent,
  ViewStyle,
} from 'react-native';
import type { StreamState, BluetoothDeviceStatuses } from './types';

type RTMPData<T> = { data: T };

export type ConnectionFailedType = NativeSyntheticEvent<RTMPData<string>>;
export type ConnectionStartedType = NativeSyntheticEvent<RTMPData<string>>;
export type ConnectionSuccessType = NativeSyntheticEvent<RTMPData<null>>;
export type DisconnectType = NativeSyntheticEvent<RTMPData<null>>;
export type NewBitrateReceivedType = NativeSyntheticEvent<RTMPData<number>>;
export type StreamStateChangedType = NativeSyntheticEvent<
  RTMPData<StreamState>
>;
export type BluetoothDeviceStatusChangedType = NativeSyntheticEvent<
  RTMPData<BluetoothDeviceStatuses>
>;
export interface NativeRTMPPublisherProps {
  style?: ViewStyle;
  streamURL: string;
  streamName: string;
  onConnectionFailed?: (e: ConnectionFailedType) => void;
  onConnectionStarted?: (e: ConnectionStartedType) => void;
  onConnectionSuccess?: (e: ConnectionSuccessType) => void;
  onDisconnect?: (e: DisconnectType) => void;
  onNewBitrateReceived?: (e: NewBitrateReceivedType) => void;
  onStreamStateChanged?: (e: StreamStateChangedType) => void;
  onBluetoothDeviceStatusChanged?: (
    e: BluetoothDeviceStatusChangedType
  ) => void;
}

// Use Fabric component if available (New Architecture), otherwise fall back to old architecture
let RTMPPublisherComponent: any;
try {
  RTMPPublisherComponent =
    require('./specs/RTMPPublisherViewNativeComponent').default;
} catch (e) {
  RTMPPublisherComponent =
    requireNativeComponent<NativeRTMPPublisherProps>('RTMPPublisher');
}

export default RTMPPublisherComponent;
