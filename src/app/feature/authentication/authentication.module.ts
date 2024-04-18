import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationRoutingModule } from './authentication-routing.module';
import { LoginComponent } from './login/login.component';
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {InputTextModule} from "primeng/inputtext";
import {DialogModule} from "primeng/dialog";
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';
import { ActivateaccountComponent } from './activateaccount/activateaccount.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    LoginComponent,
    ForgotpasswordComponent,
    ResetpasswordComponent,
    ActivateaccountComponent
  ],
  imports: [
    CommonModule,
    AuthenticationRoutingModule,
    ButtonModule,
    RippleModule,
    ReactiveFormsModule,
    InputTextModule,
    DialogModule
  ]
})
export class AuthenticationModule { }
