import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {KeycloakAngularModule, KeycloakEventType, KeycloakService} from "keycloak-angular";
import {HttpClientModule} from "@angular/common/http";

function initializeKeycloak(keycloak: KeycloakService) {
  keycloak.keycloakEvents$.subscribe({
    next(event: any) {
      if (event.type == KeycloakEventType.OnTokenExpired) {
        keycloak.updateToken(20);
      }
    }
  });
  return () =>
    keycloak.init({
      config: {
        realm: 'scaks1',
        url: 'https://scaks.dimantchick.tk/auth',
        clientId: 'frontend'
      },
      initOptions: {
        onLoad: 'check-sso',
        scope: 'openid read',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    }
    );
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    KeycloakAngularModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
