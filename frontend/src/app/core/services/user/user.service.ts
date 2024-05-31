import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { CreateUserDTO } from '../../../shared/models/createUserDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  public createUser(user: CreateUserDTO): Observable<any> {
    return this.http.post(`${this.apiUrl}/user`, user, {observe: 'response'});
  }
}
