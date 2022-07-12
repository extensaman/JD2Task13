import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Subscription} from "../entity/subscription";
import {Observable} from "rxjs";
import {backendUrl} from "../const/backend-url";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private subscriptionUri: string = '/subscriptions';

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(backendUrl + this.subscriptionUri);
  }
}
