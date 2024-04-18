import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import { environment } from 'src/environments/environment';
import { AuthenticationRequest } from '../Models/AuthenticationRequest';
import { AuthenticationResponse } from '../Models/AuthenticationResponse';
import { User } from '../Models/User';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  host = environment.host;

  constructor(private http: HttpClient) {
  }

  public login(data: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(this.host + "authentication/login", data);
  }

  public logout():Observable<string>{
    return this.http.post<string>(this.host + "authentication/logout", null, {responseType: 'text' as 'json'});
  }

  public setToLocalStorage(key: string, value: string) {
    localStorage.setItem(key, value);
  }

  public getFromLocalStorage(key:string){
    return localStorage.getItem(key);
  }

  public register(user:User, role:string):Observable<string>{
    const params = new HttpParams().set('roleName', role);
    return this.http.post<string>(this.host+"authentication/register", user,{
      params,
      responseType: 'text' as 'json'
    })
  }
}
