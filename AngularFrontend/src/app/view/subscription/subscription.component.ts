import {Component, OnInit} from '@angular/core';
import {SubscriptionService} from "../../service/subscription.service";
import {Subscription} from "../../entity/subscription";
import {AppSetting} from "../../setting/app-setting";

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent implements OnInit {

  subscriptions: Subscription[];
  imagesUrl: string = AppSetting.imagesUrl;
  groupLessonKey: string = AppSetting.groupLessonKey;
  groupLessonValue: string = AppSetting.groupLessonValue;
  individualLessonKey: string = AppSetting.individualLessonKey;
  individualLessonValue: string = AppSetting.individualLessonValue;

  constructor(private subscriptionService: SubscriptionService) {
  }

  ngOnInit(): void {
    this.subscriptionService.findAll().subscribe(data => this.subscriptions = data);
  }

}
