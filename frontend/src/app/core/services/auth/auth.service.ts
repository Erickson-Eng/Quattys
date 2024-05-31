import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';
import { DOCUMENT } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl;
  private tokenKey: string = 'quattysToken';
  private authToken: string | null = null;

  constructor(
    private http: HttpClient,
    private jwtHelper: JwtHelperService,
    @Inject(DOCUMENT) private document: Document
  ) {}

  public login(username: string, password: string): Observable<any> {
    const token = btoa(`${username}:${password}`);
    const headers = new HttpHeaders({
      Authorization: `Basic ${token}`,
    });

    return this.http.post(
      `${this.apiUrl}/authenticate`,
      {},
      { headers: headers, observe: 'response' }
    );
  }

  setToken(token: string): void {
    this.authToken = token;
    localStorage.setItem(this.tokenKey, token);
  }

  getToken(): string | null {
    const localStorage =
      typeof window !== 'undefined' && window.localStorage
        ? window.localStorage
        : null;

    if (this.authToken) {
      return this.authToken;
    } else if (localStorage) {
      return localStorage.getItem(this.tokenKey);
    } else {
      return null;
    }
  }

  isLoggedIn(): boolean {
    const accessToken = this.getToken();
    return accessToken ? !this.jwtHelper.isTokenExpired(accessToken) : false;
  }


  logout(): void {
    this.authToken = null;
    localStorage.removeItem(this.tokenKey);
  }
}
