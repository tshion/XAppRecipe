import { Component } from '@angular/core';
import { Platform } from '@ionic/angular';

import XAppRecipe from '../../xapprecipe.plugin';

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
    if (!this.platform.is('capacitor')) {
      return;
    } else if (this.platform.is('android')) {
      XAppRecipe.launch();
    } else if (this.platform.is('ios')) {
      XAppRecipe.launch();
    }
  }
}
