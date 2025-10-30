# Changelog - New Architecture Support

## Version 0.5.0 (New Architecture + RTMPS Support)

### 🚀 Major Features

#### 1. React Native New Architecture Support
- ✅ **TurboModules** - Native module now supports TurboModule architecture
- ✅ **Fabric Components** - UI component now supports Fabric renderer
- ✅ **Backward Compatible** - Automatically falls back to old architecture when needed
- ✅ **Zero Config** - Works out of the box with both architectures

#### 2. RTMPS & Secure Streaming (Android)
- ✅ **RTMPS** (`rtmps://`) - RTMP over TLS/SSL (port 443)
- ✅ **RTMPT** (`rtmpt://`) - RTMP tunneled over HTTP (port 80)
- ✅ **RTMPTS** (`rtmpts://`) - RTMP tunneled over HTTPS (port 443)
- ✅ Updated to **RootEncoder 2.6.6**

### 📦 New Files

#### JavaScript/TypeScript
- `src/specs/NativeRTMPPublisher.ts` - TurboModule specification
- `src/specs/RTMPPublisherViewNativeComponent.ts` - Fabric component specification
- `NEW_ARCHITECTURE.md` - Complete New Architecture documentation

#### Android
- Updated `android/build.gradle` - New Architecture configuration
- Updated `android/src/main/java/com/reactnativertmppublisher/RTMPPackage.java` - TurboReactPackage
- Updated `android/src/main/java/com/reactnativertmppublisher/RTMPModule.java` - Added NAME constant
- Updated `android/src/main/java/com/reactnativertmppublisher/modules/ConnectionChecker.java` - New ConnectChecker interface
- Updated `android/src/main/java/com/reactnativertmppublisher/modules/Publisher.java` - Updated API calls

### 🔧 Modified Files

#### Configuration
```json
// package.json - Added codegenConfig
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

#### JavaScript
- `src/Component.tsx` - Auto-detects Fabric vs Bridge
- `src/RTMPPublisher.tsx` - Auto-detects TurboModule vs Bridge

#### Android
- `android/build.gradle`:
  - Added New Architecture detection
  - Added Prefab support
  - Added codegen source sets
  - Updated Java version to 11
  - Added BuildConfig flag for architecture detection

### 🔄 Breaking Changes

**None!** This is a fully backward-compatible update.

### 📖 Migration Guide

#### For Users (App Developers)

**No changes required!** The library automatically detects which architecture your app uses.

To enable New Architecture in your project:
```properties
# android/gradle.properties
newArchEnabled=true
```

#### For Developers (Library Contributors)

See `NEW_ARCHITECTURE.md` for complete architecture details.

### 🎯 API Changes

**No API changes!** All existing methods work exactly the same:

```javascript
// Still works exactly the same way
const publisherRef = useRef(null);

<RTMPPublisher
  ref={publisherRef}
  streamURL="rtmps://secure-server.com/live"  // Now supports rtmps://!
  streamName="stream-key"
  onConnectionSuccess={() => console.log('Connected!')}
  onConnectionFailed={(error) => console.log('Failed:', error)}
/>

await publisherRef.current.startStream();
await publisherRef.current.stopStream();
```

### 🐛 Bug Fixes

- Fixed `hasCongestion()` method to work with RootEncoder 2.6.6
- Updated connection checker interface to match new API

### 📊 Performance Improvements

With New Architecture enabled:
- ⚡ Faster module initialization (lazy loading)
- 🎨 Smoother UI rendering (synchronous)
- 💾 Lower memory usage
- 📦 Smaller bundle size (tree shaking)

### 🔐 Security Improvements

- Added RTMPS support for secure streaming over TLS/SSL
- Updated to latest RootEncoder with security patches

### 📝 Documentation

- Updated `README.md` with New Architecture section
- Updated `README.md` with RTMPS usage examples
- Created `NEW_ARCHITECTURE.md` comprehensive guide
- Updated Props table to show RTMPS support

### 🧪 Testing

Tested with:
- ✅ React Native 0.71+ with New Architecture enabled
- ✅ React Native 0.63+ with Old Architecture
- ✅ Android API 21-34
- ✅ RTMP, RTMPS, RTMPT, RTMPTS protocols

### 🔮 Future Plans

- iOS New Architecture support (coming soon)
- More streaming protocols
- Enhanced error handling
- Performance metrics

### 📦 Dependencies

#### Updated
- `RootEncoder`: `2.2.2` → `2.6.6`

#### New (Dev Dependencies - Auto-managed)
- React Native Codegen (when New Arch enabled)

### 💡 Usage Examples

#### Standard RTMP Streaming
```javascript
<RTMPPublisher
  streamURL="rtmp://server.com/live"
  streamName="stream-key"
/>
```

#### Secure RTMPS Streaming
```javascript
<RTMPPublisher
  streamURL="rtmps://secure-server.com/live"
  streamName="stream-key"
/>
```

#### Tunneled RTMPTS Streaming
```javascript
<RTMPPublisher
  streamURL="rtmpts://tunnel-server.com/live"
  streamName="stream-key"
/>
```

### 🙏 Credits

- Built with [RootEncoder](https://github.com/pedroSG94/RootEncoder) by pedroSG94
- Inspired by React Native New Architecture examples

### 📄 License

MIT License - Same as before

---

## How to Copy to Your Project

### Option 1: Copy All Files
```bash
# Copy the entire updated library
cp -r "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher" /path/to/your-project/node_modules/
```

### Option 2: Copy Only Updated Parts
```bash
# Essential files for New Architecture
cp -r "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/src/specs" /path/to/your-project/node_modules/react-native-rtmp-publisher/src/
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/src/Component.tsx" /path/to/your-project/node_modules/react-native-rtmp-publisher/src/
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/src/RTMPPublisher.tsx" /path/to/your-project/node_modules/react-native-rtmp-publisher/src/
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/package.json" /path/to/your-project/node_modules/react-native-rtmp-publisher/
cp -r "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/android" /path/to/your-project/node_modules/react-native-rtmp-publisher/
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/README.md" /path/to/your-project/node_modules/react-native-rtmp-publisher/
cp "/Users/huy.tran.q/Documents/livestream lib/react-native-rtmp-publisher/NEW_ARCHITECTURE.md" /path/to/your-project/node_modules/react-native-rtmp-publisher/
```

### After Copying
```bash
cd /path/to/your-project
cd android
./gradlew clean
cd ..
npm start -- --reset-cache
npx react-native run-android
```

---

**Enjoy the New Architecture! 🎉**

