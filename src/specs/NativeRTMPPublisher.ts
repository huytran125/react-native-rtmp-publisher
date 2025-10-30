// @ts-ignore - TurboModule types are only available in RN 0.68+
import type { TurboModule } from 'react-native';
// @ts-ignore - TurboModuleRegistry is only available in RN 0.68+
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  startStream(): Promise<void>;
  stopStream(): Promise<void>;
  isStreaming(): Promise<boolean>;
  isCameraOnPreview(): Promise<boolean>;
  getPublishURL(): Promise<string>;
  hasCongestion(): Promise<boolean>;
  isAudioPrepared(): Promise<boolean>;
  isVideoPrepared(): Promise<boolean>;
  isMuted(): Promise<boolean>;
  mute(): Promise<void>;
  unmute(): Promise<void>;
  switchCamera(): Promise<void>;
  toggleFlash(): Promise<void>;
  setAudioInput(audioInput: number): Promise<void>;
}

// @ts-ignore - TurboModuleRegistry is only available in RN 0.68+
export default TurboModuleRegistry.getEnforcing<Spec>('RTMPPublisher');

