import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserLogin } from '../models/user/UserLogin';
import { UserToken } from '../models/user/UserToken';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  private _baseUrl: string = 'http://localhost:8080'

  constructor(private httpClient: HttpClient) {}

  login(userLogin: UserLogin): Observable<UserToken> {
    return this.httpClient.post<UserToken>(`${this._baseUrl}/login`, userLogin);
  }

  logout() {
    localStorage.removeItem('token');
  }

  isLoggedIn() {
    return localStorage.getItem('token') !== null;
  }

}
