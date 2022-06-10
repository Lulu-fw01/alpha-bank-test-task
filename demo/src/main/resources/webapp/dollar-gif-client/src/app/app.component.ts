import {Component, OnInit} from '@angular/core';
import {Rate} from "./rate/Rate";
import {DollarGifService} from "./dollargif/dollar-gif-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'dollar-gif-client';
  rate: Rate | undefined;

  constructor(private router: Router, private dollarGifService: DollarGifService) {
  }

  getRate(base: string) {
    this.dollarGifService.getRate(base).subscribe(data => {
      this.rate = data;
    });
  }

  ngOnInit(): void {
    this.router.events.subscribe(value => {
      this.getRate("RUB");
    });
  }

  onGetGifClick(base: string) {
    this.getRate(base);
  }
}
