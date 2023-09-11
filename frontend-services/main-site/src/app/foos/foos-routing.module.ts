import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FoosComponent } from './foos.component';

const routes: Routes = [{ path: '', component: FoosComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FoosRoutingModule { }
