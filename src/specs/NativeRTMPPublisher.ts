import type { TurboModule } from 'react-native';
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

export default TurboModuleRegistry.getEnforcing<Spec>('RTMPPublisher');

