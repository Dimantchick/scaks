import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: 'foos',
    loadChildren: () =>
      import('./foos/foos.module').then(
        (m) => m.FoosModule
      ),
  },
  {
    path: 'actuator/info',
    loadChildren: () =>
      import('./actuator/actuator.module').then(
        (m) => m.ActuatorModule
      ),
  },
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
