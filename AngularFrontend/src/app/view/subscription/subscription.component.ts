import { Component, OnInit } from '@angular/core';
import {SubscriptionService} from "../../service/subscription.service";
import {Subscription} from "../../entity/subscription";

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent implements OnInit {

  subscriptions:Subscription[];

  constructor(private subscriptionService:SubscriptionService) { }

  ngOnInit(): void {
    this.subscriptionService.findAll().subscribe(data => this.subscriptions = data);
  }

}
