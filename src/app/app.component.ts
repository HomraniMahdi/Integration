import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  currentLayout = 'default-layout'; // Par défaut

  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        const route = this.router.config.find(
          (item) => item.path === this.router.url.split('/')[1]
        );
        this.currentLayout = route?.data?.['layout'] || 'default-layout';

      }
    });
  }
}
