import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import { ChangePasswordDto } from '../Models/ChangePasswordDto';
import { ResetPassword } from '../Models/ResetPassword';
import { UpdateProfileDto } from '../Models/UpdateProfileDto';
import { User } from '../Models/User';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AccountService {
  host = environment.host;

  constructor(private http: HttpClient) {
  }

  public forgotPassword(emailAddress: string): Observable<string> {
    const params = new HttpParams().set('emailAddress', emailAddress);
    return this.http.post<string>(this.host + "account/forgot-password",
      null, {
        params,
        responseType: 'text' as 'json'
      });
  }

  public resetPassword(data: ResetPassword): Observable<string> {
    return this.http.post<string>(this.host + "account/reset-password",
      data, {responseType: 'text' as 'json'});
  }

  public listUserAccounts(): Observable<User[]> {
    return this.http.get<User[]>(this.host + "account/list-user-accounts");
  }

  public enableAccount(userId:number):Observable<string>{
    const params = new HttpParams().set('userId', userId);
    return this.http.post<string>(this.host+"account/enable-account",null,{
      params,
      responseType:'text' as 'json'
    })
  }

  public disableAccount(userId:number):Observable<string>{
    const params = new HttpParams().set('userId', userId);
    return this.http.post<string>(this.host+"account/disable-account",null,{
      params,
      responseType:'text' as 'json'
    })
  }

  public getProfile():Observable<User>{
    return this.http.get<User>(this.host+"account/profile")
  }

  public changePassword(changePassword:ChangePasswordDto):Observable<string>{
    return this.http.post<string>(this.host+"account/change-password", changePassword,{
      responseType:'text' as 'json'
    });
  }

  public updateProfile(updateProfile:UpdateProfileDto):Observable<string>{
    return this.http.post<string>(this.host+"account/update-profile", updateProfile,{
      responseType:'text' as 'json'
    });
  }

  public activateAccount(confirmationKey: string): Observable<string> {
    const params = new HttpParams().set('confirmationKey', confirmationKey);
    return this.http.post<string>(this.host + "account/activate-account",
      null, {
        params,
        responseType: 'text' as 'json'
      });
  }
}
