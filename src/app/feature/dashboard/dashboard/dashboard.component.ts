import { Component, OnInit } from '@angular/core';
import {MessageService} from "primeng/api";
import { UsersStats } from 'src/app/Models/UsersStats';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  providers:[MessageService]
})
export class DashboardComponent implements OnInit {

  usersStats:UsersStats={};
  data: any;
  options:any;
  constructor( private messageService:MessageService) { }

  ngOnInit(): void {
  }

}
