import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LauncherPage } from './launcher.page';

@NgModule({
  declarations: [
    LauncherPage,
  ],
  imports: [
    RouterModule.forChild([
      {
        path: '',
        component: LauncherPage,
      }
    ]),
  ],
})
export class LauncherPageModule { }
