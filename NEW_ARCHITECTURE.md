# React Native New Architecture Support

This document explains the New Architecture support added to react-native-rtmp-publisher.

## What's New

The library now supports:
- ✅ **TurboModules** - For improved native module performance
- ✅ **Fabric** - For improved UI component rendering
- ✅ **Backward Compatibility** - Works with both old and new architecture
- ✅ **RTMPS Support** - Secure streaming with RootEncoder 2.6.6

## Architecture Detection

The library automatically detects which architecture your app is using:

### JavaScript Layer
```typescript
// Automatically uses TurboModule if available, falls back to Bridge
import RTMPPublisher from 'react-native-rtmp-publisher';
```

### Android Layer
```java
// BuildConfig.IS_NEW_ARCHITECTURE_ENABLED is set at compile time
boolean isTurboModule = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
```

## Enabling New Architecture

### For Your React Native Project

1. **Android** - Add to `android/gradle.properties`:
```properties
newArchEnabled=true
```

2. **Rebuild your app**:
```bash
cd android
./gradlew clean
cd ..
npx react-native run-android
```

## File Structure

### New Files Added

```
src/
├── specs/
│   ├── NativeRTMPPublisher.ts          # TurboModule spec
│   └── RTMPPublisherViewNativeComponent.ts  # Fabric component spec
├── Component.tsx                        # Updated with Fabric fallback
└── RTMPPublisher.tsx                    # Updated with TurboModule fallback

android/
├── build.gradle                         # Updated with New Arch support
└── src/main/java/com/reactnativertmppublisher/
    ├── RTMPPackage.java                # Now extends TurboReactPackage
    └── RTMPModule.java                 # Added NAME constant

package.json                            # Added codegenConfig
```

## CodeGen Configuration

The `package.json` now includes:

```json
{
  "codegenConfig": {
    "name": "RTMPPublisherSpec",
    "type": "all",
    "jsSrcsDir": "src/specs",
    "android": {
      "javaPackageName": "com.reactnativertmppublisher"
    }
  }
}
```

## How It Works

### 1. TurboModule (Native Module)

**JavaScript:**
```typescript
// src/RTMPPublisher.tsx
let RTMPModule: any;
try {
  // Try to load TurboModule (New Architecture)
  RTMPModule = require('./specs/NativeRTMPPublisher').default;
} catch (e) {
  // Fallback to Bridge (Old Architecture)
  RTMPModule = NativeModules.RTMPPublisher;
}
```

**Android:**
```java
// RTMPPackage.java
public class RTMPPackage extends TurboReactPackage {
  // Automatically creates TurboModule when New Architecture is enabled
  // Falls back to standard Bridge module otherwise
}
```

### 2. Fabric Component (UI Component)

**JavaScript:**
```typescript
// src/Component.tsx
let RTMPPublisherComponent: any;
try {
  // Try to load Fabric component (New Architecture)
  RTMPPublisherComponent = require('./specs/RTMPPublisherViewNativeComponent').default;
} catch (e) {
  // Fallback to requireNativeComponent (Old Architecture)
  RTMPPublisherComponent = requireNativeComponent('RTMPPublisher');
}
```

## Benefits of New Architecture

### TurboModules
- ✅ Lazy loading of native modules
- ✅ Type safety with TypeScript/Flow
- ✅ Better performance
- ✅ Smaller app bundle size

### Fabric
- ✅ Synchronous UI rendering
- ✅ Better rendering performance
- ✅ Concurrent rendering support
- ✅ Reduced memory footprint

## Testing

### Test with Old Architecture (Default)
```bash
# Make sure newArchEnabled is not set or set to false
cd android
./gradlew clean
cd ..
npx react-native run-android
```

### Test with New Architecture
```bash
# Set newArchEnabled=true in android/gradle.properties
cd android
./gradlew clean
cd ..
npx react-native run-android
```

## Troubleshooting

### Build Errors

If you get build errors after enabling New Architecture:

1. **Clean the build**:
```bash
cd android
./gradlew clean
cd ..
npm start -- --reset-cache
```

2. **Check React Native version**:
   - New Architecture requires React Native 0.68+
   - For best results, use React Native 0.71+

3. **Check dependencies**:
```bash
npm install  # or yarn install
cd android
./gradlew clean
```

### Runtime Errors

If the app crashes at runtime:

1. **Check if codegen ran**:
```bash
# The generated files should be in:
# android/build/generated/source/codegen/
```

2. **Verify BuildConfig**:
```java
// Should be automatically set in generated code
BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
```

## Migration Guide

### From Old Version (without New Arch support)

The library is **backward compatible**. No changes needed in your app code!

```javascript
// This code works with both architectures
import RTMPPublisher from 'react-native-rtmp-publisher';

<RTMPPublisher
  ref={publisherRef}
  streamURL="rtmps://your-server.com/live"
  streamName="stream-key"
  onConnectionSuccess={() => console.log('Connected!')}
/>
```

### Enabling New Architecture in Existing Project

1. Update React Native to 0.71+ (recommended)
2. Add `newArchEnabled=true` to `android/gradle.properties`
3. Rebuild: `cd android && ./gradlew clean && cd .. && npx react-native run-android`
4. That's it! The library will automatically use the new architecture.

## Performance Comparison

| Feature | Old Architecture | New Architecture |
|---------|-----------------|------------------|
| Module Load Time | Eager (all at once) | Lazy (on demand) |
| Type Safety | Runtime checks | Compile-time checks |
| Rendering | Async bridge | Sync rendering |
| Memory Usage | Higher | Lower |
| Bundle Size | Larger | Smaller |

## Learn More

- [React Native New Architecture](https://reactnative.dev/docs/the-new-architecture/landing-page)
- [TurboModules](https://reactnative.dev/docs/the-new-architecture/pillars-turbomodules)
- [Fabric Components](https://reactnative.dev/docs/the-new-architecture/pillars-fabric-components)
- [Migration Guide](https://reactnative.dev/docs/new-architecture-intro)

## Support

If you encounter any issues with the New Architecture:

1. First, test with Old Architecture (set `newArchEnabled=false`)
2. If it works with Old Architecture, it's a New Architecture setup issue
3. Check your React Native version (needs 0.68+)
4. Open an issue with:
   - React Native version
   - Architecture mode (old/new)
   - Error logs
   - Steps to reproduce

