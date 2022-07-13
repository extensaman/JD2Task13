import { Component, OnInit } from '@angular/core';
import {AppSetting} from "../../setting/app-setting";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  backendUrl: string = AppSetting.backendUrl;

  constructor() { }

  ngOnInit(): void {
  }

}
