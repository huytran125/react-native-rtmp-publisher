# Copy Instructions - Updated Library

This document provides exact commands to copy the updated react-native-rtmp-publisher library with:
- ✅ React Native New Architecture Support (TurboModules + Fabric)
- ✅ RTMPS Support (RootEncoder 2.6.5)
- ✅ Backward compatibility with old architecture
- ✅ All TypeScript and build errors fixed

## Quick Copy Command

### Copy Entire Library
```bash
# Replace YOUR_PROJECT_PATH with your actual React Native project path
cp -r "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher" /YOUR_PROJECT_PATH/node_modules/

# Then rebuild
cd /YOUR_PROJECT_PATH
cd android
./gradlew clean
cd ..
npm start -- --reset-cache
npx react-native run-android
```

## Or Copy Individual Directories

### Essential Files for Android + JavaScript
```bash
PROJECT_PATH="/YOUR_PROJECT_PATH/node_modules/react-native-rtmp-publisher"

# Android native code (Updated for RootEncoder 2.6.5)
cp -r "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/android" "$PROJECT_PATH/"

# JavaScript/TypeScript source
cp -r "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/src" "$PROJECT_PATH/"

# Configuration files
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/package.json" "$PROJECT_PATH/"
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/tsconfig.json" "$PROJECT_PATH/"
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/tsconfig.build.json" "$PROJECT_PATH/"

# Documentation (Optional)
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/README.md" "$PROJECT_PATH/"
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/NEW_ARCHITECTURE.md" "$PROJECT_PATH/"
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/CHANGELOG_NEW_ARCH.md" "$PROJECT_PATH/"
```

## Files Changed Summary

### New Files Created
- ✅ `src/specs/NativeRTMPPublisher.ts` - TurboModule specification
- ✅ `src/specs/RTMPPublisherViewNativeComponent.ts` - Fabric component specification
- ✅ `NEW_ARCHITECTURE.md` - Complete guide
- ✅ `CHANGELOG_NEW_ARCH.md` - Detailed changelog
- ✅ `COPY_INSTRUCTIONS.md` - This file

### Modified Files

#### JavaScript/TypeScript
- ✅ `src/Component.tsx` - Auto-detects Fabric vs Bridge
- ✅ `src/RTMPPublisher.tsx` - Auto-detects TurboModule vs Bridge
- ✅ `package.json` - Added codegenConfig
- ✅ `tsconfig.json` - Updated for compatibility
- ✅ `tsconfig.build.json` - Added skipLibCheck

#### Android
- ✅ `android/build.gradle` - New Architecture support, RootEncoder 2.6.5
- ✅ `android/src/main/java/com/reactnativertmppublisher/RTMPPackage.java` - TurboReactPackage
- ✅ `android/src/main/java/com/reactnativertmppublisher/RTMPModule.java` - Added NAME constant
- ✅ `android/src/main/java/com/reactnativertmppublisher/modules/ConnectionChecker.java` - New ConnectChecker interface
- ✅ `android/src/main/java/com/reactnativertmppublisher/modules/Publisher.java` - Updated imports and API
- ✅ `android/src/main/java/com/reactnativertmppublisher/modules/SurfaceHolderHelper.java` - Updated imports

#### Documentation
- ✅ `README.md` - Added New Architecture and RTMPS sections

## After Copying

### 1. Clean and Rebuild Android
```bash
cd YOUR_PROJECT_PATH/android
./gradlew clean
cd ..
```

### 2. Clear Metro Cache
```bash
npm start -- --reset-cache
# or
yarn start --reset-cache
```

### 3. Rebuild the App
```bash
npx react-native run-android
```

## Enable New Architecture (Optional)

To enable New Architecture in your project:

### Android
Add to `android/gradle.properties`:
```properties
newArchEnabled=true
```

Then rebuild as shown above.

## Verify Installation

### Check RootEncoder Version
```bash
cd YOUR_PROJECT_PATH
grep "RootEncoder" node_modules/react-native-rtmp-publisher/android/build.gradle
```

Should show:
```gradle
implementation "com.github.pedroSG94.RootEncoder:library:2.6.5"
implementation "com.github.pedroSG94.RootEncoder:extra-sources:2.6.5"
```

### Check New Architecture Support
```bash
cd YOUR_PROJECT_PATH
cat node_modules/react-native-rtmp-publisher/package.json | grep -A 6 "codegenConfig"
```

Should show:
```json
"codegenConfig": {
  "name": "RTMPPublisherSpec",
  "type": "all",
  "jsSrcsDir": "src/specs",
  "android": {
    "javaPackageName": "com.reactnativertmppublisher"
  }
}
```

## Usage Examples

### Standard RTMP Streaming
```javascript
import RTMPPublisher from 'react-native-rtmp-publisher';

<RTMPPublisher
  ref={publisherRef}
  streamURL="rtmp://server.com/live"
  streamName="stream-key"
  onConnectionSuccess={() => console.log('Connected!')}
  onConnectionFailed={(error) => console.log('Failed:', error)}
/>
```

### Secure RTMPS Streaming
```javascript
<RTMPPublisher
  ref={publisherRef}
  streamURL="rtmps://secure-server.com/live"
  streamName="stream-key"
  onConnectionSuccess={() => console.log('Securely connected!')}
/>
```

### Tunneled RTMPTS Streaming
```javascript
<RTMPPublisher
  ref={publisherRef}
  streamURL="rtmpts://tunnel-server.com/live"
  streamName="stream-key"
  onConnectionSuccess={() => console.log('Tunneled connection established!')}
/>
```

## Troubleshooting

### Build Errors After Copying

1. **Clean everything**:
```bash
cd YOUR_PROJECT_PATH
cd android
./gradlew clean
./gradlew cleanBuildCache
rm -rf .gradle
cd ..
rm -rf node_modules
npm install
npm start -- --reset-cache
```

2. **Check React Native version**:
   - Minimum: React Native 0.63+
   - For New Architecture: React Native 0.68+

3. **Check Gradle version**:
   - Minimum: Gradle 6.9+
   - Check in `android/gradle/wrapper/gradle-wrapper.properties`

### TypeScript Errors

If you get TypeScript errors about TurboModule:
```bash
cd YOUR_PROJECT_PATH
# Make sure these files are copied:
ls -la node_modules/react-native-rtmp-publisher/src/specs/
# Should show:
# NativeRTMPPublisher.ts
# RTMPPublisherViewNativeComponent.ts
```

### Runtime Errors

If the app crashes at runtime:
1. Check if the library is properly linked (auto-linking should handle it)
2. Verify permissions in `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.INTERNET" />
```

## What's New

### Features
- ✅ React Native New Architecture (TurboModules + Fabric)
- ✅ RTMPS support for secure streaming
- ✅ RTMPT/RTMPTS support for tunneled streaming
- ✅ Backward compatible with old architecture
- ✅ Auto-detection of architecture
- ✅ Updated to RootEncoder 2.6.5

### Performance Improvements (New Architecture)
- ⚡ Faster module loading (lazy loading)
- 🎨 Smoother rendering (synchronous)
- 💾 Lower memory usage
- 📦 Smaller bundle size

### Security Improvements
- 🔐 RTMPS over TLS/SSL (port 443)
- 🔐 RTMPTS tunneled over HTTPS (port 443)

## Support

For issues:
1. Check this file and `NEW_ARCHITECTURE.md`
2. Verify all files are copied correctly
3. Clean and rebuild as shown above
4. Check React Native version compatibility

## License

MIT License - Same as original package

---

**Last Updated:** October 30, 2025  
**Version:** 0.5.0 (New Architecture + RTMPS Support)  
**RootEncoder Version:** 2.6.5  
**React Native Compatibility:** 0.63+ (Old Arch), 0.68+ (New Arch)

