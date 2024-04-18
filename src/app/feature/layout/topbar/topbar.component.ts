import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

import {Router} from "@angular/router";
import { AuthenticationService } from 'src/app/services/authentication.service';
import { LayoutService } from 'src/app/services/layout.service';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent implements OnInit {


  @ViewChild('menubutton') menuButton!: ElementRef;

  @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

  @ViewChild('topbarmenu') menu!: ElementRef;

  constructor(public layoutService: LayoutService, private authenticationService: AuthenticationService, private router:Router) {

  }

  ngOnInit(): void {
  }

  logout() {
    this.authenticationService.logout().subscribe({
      next:(response) => {
          localStorage.clear();
          this.router.navigate(['']);
      },
      error:(err) => {
        localStorage.clear();
        this.router.navigate(['']);
      }
    });
  }
}
