import {Component, OnInit} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {KeycloakService} from "keycloak-angular";
import {Observable} from "rxjs";

@Component({
  selector: 'app-foos',
  templateUrl: './foos.component.html',
  styleUrls: ['./foos.component.scss']
})
export class FoosComponent implements OnInit {
  public foo = new Foo(1, 'sample foo');
  private foosUrl = environment.FRONTEND_URL + '/api/resource-server/api/foos/';

  constructor(
    private _http: HttpClient,
    private readonly keycloak: KeycloakService) {
  }

  public async ngOnInit() {
    let isLoggedIn = await this.keycloak.isLoggedIn();
    if (!isLoggedIn) {
      await this.keycloak.login();
    }
  }

  getFoo() {
    this.getResource(this.foosUrl + this.foo.id)
      .subscribe({
        next: (data: any) => {
          this.foo = data;
        },
        error: (err: any) => {
          this.foo.name = 'Error';
        }
      });
  }

  getResource(resourceUrl: string): Observable<any> {
    var headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
    });
    this.keycloak.addTokenToHeader(headers);
    return this._http.get(resourceUrl, {headers: headers});
    // .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }
}

export class Foo {
  constructor(
    public id: number,
    public name: string) {
  }
}
