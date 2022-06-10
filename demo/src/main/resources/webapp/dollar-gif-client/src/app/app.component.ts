import {Component, OnInit} from '@angular/core';
import {Rate} from "./rate/Rate";
import {DollarGifService} from "./dollargif/dollar-gif-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements  OnInit{
  title = 'dollar-gif-client';
  rate: Rate | undefined;

  constructor(private router: Router, private dollarGifService: DollarGifService) {
  }

  getRate() {
    this.dollarGifService.getRate("RUB").subscribe(data => {
      this.rate = data;
    });
  }

  /*addBook(): void {
    this.router.navigate(['add-book'])
      .then((e) => {
        if (e) {
          console.log("Navigation is successful!");
        } else {
          console.log("Navigation has failed!");
        }
      });
  };
*/
  ngOnInit(): void {
    this.router.events.subscribe(value => {
      this.getRate();
    });
  }
}
