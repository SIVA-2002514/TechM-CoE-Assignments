import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-forecast',
  templateUrl: './forecast.component.html',
  styleUrls: ['./forecast.component.css']
})
export class ForecastComponent implements OnInit {
  forecastData = [
    { date: 'Mon', temp: 25, condition: 'Sunny' },
    { date: 'Tue', temp: 27, condition: 'Cloudy' },
    { date: 'Wed', temp: 23, condition: 'Rainy' },
    { date: 'Thu', temp: 26, condition: 'Sunny' },
    { date: 'Fri', temp: 24, condition: 'Windy' }
  ];

  ngOnInit() {}
}
