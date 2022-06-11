import {Component, OnInit} from '@angular/core';
import {Rate} from "./rate/Rate";
import {DollarGifService} from "./dollargif/dollar-gif-service.service";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'dollar-gif-client';
  rate?: Rate;
  console = console;
  errorMessage = "";

  constructor(private router: Router, private dollarGifService: DollarGifService) {
  }

  getRate(base: string) {
    this.dollarGifService.getRate(base).subscribe(
      (response: Rate) => {
        console.log(response);
        this.errorMessage = "";
        this.rate = response;
      },
      (error: HttpErrorResponse) => {
        console.error("error was caught: " + error)
        this.errorMessage = "Got error with code " + error.status;
      });
  }

  ngOnInit(): void {
    this.router.events.subscribe(value => {
      this.errorMessage = "";
      this.getRate("RUB");
    });
  }

  onGetGifClick(base: string) {
    console.log("Get gif button was clicked.");
    this.getRate(base);
  }
}
