import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FoosRoutingModule } from './foos-routing.module';
import { FoosComponent } from './foos.component';


@NgModule({
  declarations: [
    FoosComponent
  ],
  imports: [
    CommonModule,
    FoosRoutingModule
  ]
})
export class FoosModule { }
