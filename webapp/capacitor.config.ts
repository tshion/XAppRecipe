import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.github.tshion.xapprecipe.webapp',
  appName: 'webapp',
  bundledWebRuntime: false,
  webDir: 'www',

  android: {
    path: '../android',
  }
};

export default config;
