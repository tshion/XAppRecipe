import { Component } from '@angular/core';
import { Plugins } from '@capacitor/core';
import { Platform } from '@ionic/angular';

const { XAppRecipePlugin } = Plugins;

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  constructor(
    private platform: Platform
  ) {
  }


  callNative() {
    const isCapacitor = this.platform.is('capacitor');
    if (isCapacitor && this.platform.is('android')) {
      XAppRecipePlugin.launch();
    }
  }
}
