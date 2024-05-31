import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(private htttpClient: HttpClient) {}

  public getProfilesByName(name: string): Observable<any> {
    return this.htttpClient.get<any>(
      'http://localhost:8080/api/v1/profile?name=erickson&page=0'
    );
  }
}
