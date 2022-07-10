import { Component } from '@angular/core';
import { Platform } from '@ionic/angular';

import xApp from 'src/xapp.plugin';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  /** OS ネイティブ実装を呼び出せるかどうか */
  public canCallNative: boolean;


  constructor(
    platform: Platform,
  ) {
    this.canCallNative = platform.is('capacitor');
  }


  public async callNative() {
    if (!this.canCallNative) {
      alert('Unsupported.');
      return;
    }

    await xApp.launch();
  }
}
