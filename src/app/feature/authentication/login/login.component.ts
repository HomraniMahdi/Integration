import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  display = false;
  message!: string;
  @ViewChild('userNameRef') usernameElementRef!: ElementRef;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService, private router: Router) {
    this.form = this.formBuilder.group({
      userName: this.formBuilder.control(null, [Validators.required]),
      password: this.formBuilder.control(null, [Validators.required])
    })
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.usernameElementRef.nativeElement.focus();
  }

  login() {
    this.authenticationService.login(this.form.value).subscribe({
      next: (authenticationResponse) => {
        this.authenticationService.setToLocalStorage('accessToken', authenticationResponse.accessToken);
        this.authenticationService.setToLocalStorage('refreshToken', authenticationResponse.refreshToken);
        this.authenticationService.setToLocalStorage('role', authenticationResponse.role);
        this.router.navigate(['/home/dashboard']);
      },
      error: (err) => {
        this.message = 'Bad credentials.';
        this.display = true;
      }
    });
  }
}
