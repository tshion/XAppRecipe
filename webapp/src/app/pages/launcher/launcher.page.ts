import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { MathUtil } from 'src/app/utilities/math.util';
import { PromiseUtil } from 'src/app/utilities/promise.util';

/**
 * 起動ページ
 */
@Component({
  selector: 'app-launcher',
  template: '',
})
export class LauncherPage implements OnInit {

  constructor(
    private readonly router: Router,
  ) {
  }


  ngOnInit(): void {
    (async () => {
      await PromiseUtil.delay(1500);
      const random = MathUtil.getRandomInt(100);

      // TODO: 画面の振り分け
      if (random < 10) {
        this.router.navigate(['home']);
      } else if (random < 40) {
        this.router.navigate(['home']);
      } else {
        this.router.navigate(['home']);
      }
    })();
  }
}
