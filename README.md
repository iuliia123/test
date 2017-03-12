# test task for Keypr

For running test:
* Appium is required. Follow [guide](https://github.com/appium/appium) for installation 

For running test I used Genymotion android emulator.

Before running tests update environemnt variables in pom.xml:
* PLATFORM
* DEVICEID

Use "android" value as PLATFORM variable to run tests on Android platform, "ios" for running tests in iOS platform.
DEVICEID variable - device id of your device/emulator.

To run tests execute under test/keypr:
```
mvn clean test
```





