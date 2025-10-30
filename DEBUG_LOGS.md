# Debug Logs Added

I've added detailed logging to all key components. Here's how to debug the crash:

## How to View Logs

### Option 1: Using adb logcat (Recommended)
```bash
# Clear logs and show only RTMP-related logs
adb logcat -c && adb logcat | grep -E "RTMPPackage|RTMPModule|RTMPManager|Publisher"
```

### Option 2: Using Android Studio
1. Open Logcat window
2. Filter by package name or tags: `RTMPPackage`, `RTMPModule`, `RTMPManager`, `Publisher`

### Option 3: Using React Native
```bash
npx react-native log-android | grep -E "RTMPPackage|RTMPModule|RTMPManager|Publisher"
```

## Log Flow - What to Expect

When the app loads the library, you should see logs in this order:

### 1. Package Initialization
```
D/RTMPPackage: getReactModuleInfoProvider: Called
D/RTMPPackage: getReactModuleInfoProvider: IS_NEW_ARCHITECTURE_ENABLED = false
D/RTMPPackage: getReactModuleInfoProvider: Module info created successfully
```

### 2. Module Creation
```
D/RTMPPackage: getModule: Requested module name: RTMPPublisher
D/RTMPPackage: getModule: Creating RTMPModule
D/RTMPModule: RTMPModule: Constructor called
D/RTMPModule: getName: Returning module name: RTMPPublisher
D/RTMPPackage: getModule: RTMPModule created successfully
```

### 3. View Manager Creation
```
D/RTMPPackage: createViewManagers: Starting to create view managers
D/RTMPPackage: createViewManagers: Creating RTMPManager
D/RTMPManager: RTMPManager: Constructor called
D/RTMPPackage: createViewManagers: RTMPManager created successfully
```

### 4. View Creation (When component is rendered)
```
D/RTMPManager: getName: Returning view name: RTMPPublisher
D/RTMPManager: createViewInstance: Starting to create view instance
D/RTMPManager: createViewInstance: Creating SurfaceView
D/RTMPManager: createViewInstance: Creating Publisher
D/Publisher: Publisher: Constructor started
D/Publisher: Publisher: Creating RtmpCamera1
D/Publisher: Publisher: RtmpCamera1 created successfully
D/Publisher: Publisher: Creating BluetoothDeviceConnector
D/Publisher: Publisher: BluetoothDeviceConnector created successfully
D/Publisher: Publisher: Adding listeners
D/Publisher: Publisher: Getting AudioManager
D/Publisher: Publisher: AudioManager retrieved
D/Publisher: Publisher: Setting audio input to SPEAKER
D/Publisher: Publisher: Constructor completed successfully
D/RTMPManager: createViewInstance: Publisher created successfully
D/RTMPManager: createViewInstance: Adding layout change listener
D/RTMPManager: createViewInstance: Creating SurfaceHolderHelper
D/RTMPManager: createViewInstance: SurfaceHolderHelper added
D/RTMPManager: createViewInstance: View created successfully
```

### 5. Props Setting
```
D/RTMPManager: setStreamURL: Setting stream URL: rtmps://...
D/RTMPManager: setStreamURL: Stream URL set successfully
D/RTMPManager: setStreamName: Setting stream name: ...
D/RTMPManager: setStreamName: Stream name set successfully
```

## Finding the Crash

Look for `ERROR` logs:

```bash
# Show only errors
adb logcat | grep -E "E/RTMPPackage|E/RTMPModule|E/RTMPManager|E/Publisher"
```

The error log will show:
1. **Which component** crashed (RTMPPackage, RTMPModule, RTMPManager, or Publisher)
2. **Which method** crashed
3. **The full stack trace**

## Common Crash Points and Solutions

### If crash at "Creating RTMPModule":
```
E/RTMPPackage: getModule: Error creating module RTMPPublisher
```
**Solution:** Check if `RTMPModule.NAME` constant is defined correctly

### If crash at "Creating RtmpCamera1":
```
E/Publisher: Publisher: Error in constructor
Caused by: ... at Publisher.<init>
```
**Solution:** RootEncoder library issue - check if RootEncoder 2.6.5 is installed correctly

### If crash at "Creating BluetoothDeviceConnector":
```
E/Publisher: Publisher: Error in constructor
Caused by: ... BluetoothDeviceConnector
```
**Solution:** Bluetooth permission issue - check AndroidManifest.xml

### If crash at "Getting AudioManager":
```
E/Publisher: Publisher: Error in constructor
Caused by: ... AudioManager
```
**Solution:** Context issue - check if ThemedReactContext is valid

### If crash at "Setting audio input":
```
E/Publisher: Publisher: Error in constructor
Caused by: ... at setAudioInput
```
**Solution:** AudioManager permission or state issue

## Files with Logging

All these files now have comprehensive logging:

1. **RTMPPackage.java**
   - Package initialization
   - Module creation
   - View manager creation

2. **RTMPModule.java**
   - Module initialization
   - All method calls

3. **RTMPManager.java**
   - View manager initialization
   - View creation
   - Props setting

4. **Publisher.java**
   - Publisher initialization
   - RtmpCamera1 creation
   - Bluetooth setup
   - Audio setup

## Example Debug Session

```bash
# Terminal 1: Start the app
cd /your-project
npx react-native run-android

# Terminal 2: Watch logs
adb logcat -c
adb logcat | grep -E "RTMPPackage|RTMPModule|RTMPManager|Publisher"
```

Then navigate to the screen with RTMPPublisher component and watch the logs flow through.

## What to Share

If you still get a crash, share:
1. The **last successful log** (what worked)
2. The **error log** (what crashed)
3. The **full stack trace** from logcat

Example:
```
Last successful: D/Publisher: Publisher: Creating RtmpCamera1
Error: E/Publisher: Publisher: Error in constructor
Stack trace: [paste full trace here]
```

## Clean Logs Command

For a clean log session:
```bash
# Clear logs, restart app, and filter
adb logcat -c && \
npx react-native run-android && \
adb logcat | grep -E "RTMPPackage|RTMPModule|RTMPManager|Publisher"
```

---

Now run your app and check the logs to see exactly where it crashes!

