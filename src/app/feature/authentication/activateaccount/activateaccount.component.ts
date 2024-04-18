import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { AccountService } from 'src/app/services/account.service';


@Component({
  selector: 'app-activateaccount',
  templateUrl: './activateaccount.component.html',
  styleUrls: ['./activateaccount.component.scss']
})
export class ActivateaccountComponent implements OnInit {
  form: FormGroup;
  display = false;
  message!: string;
  constructor(private formBuilder: FormBuilder, private accountService: AccountService) {
    this.form = this.formBuilder.group({
      confirmationKey: this.formBuilder.control(null, [Validators.required]),
    })
  }

  ngOnInit(): void {
  }

  activateAccount() {
    this.accountService.activateAccount(this.form.value.confirmationKey).subscribe({
      next:(response) => {
        this.message = response;
        this.display = true;
        this.form.reset();
      },error:(err)=> {
        const errObject = JSON.parse(err.error);
        this.message = errObject.message;
        this.display = true;
      }
    });
  }
}
