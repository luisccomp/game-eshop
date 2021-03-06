import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../models/user/User";
import { UserUpdate } from "../models/user/UserUpdate";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _baseUrl: string = 'http://localhost:8080/api/users'

  constructor(private httpClient: HttpClient) {}

  profile(): Observable<User> {
    return this.httpClient.get<User>(`${this._baseUrl}/profile`, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });
  }

  update(id: number, userUpdateRequest: UserUpdate): Observable<User> {
    return this.httpClient.patch<User>(`${this._baseUrl}/${id}`, userUpdateRequest, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`
      }
    })
  }

}