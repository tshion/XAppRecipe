import { Component } from '@angular/core';
import { Platform } from '@ionic/angular';

import xApp from 'src/xapp.plugin';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  constructor(
    private readonly platform: Platform,
  ) {
  }


  public async callNative() {
    if (!this.platform.is('capacitor')) {
      return;
    }

    await xApp.launch();
  }
}
