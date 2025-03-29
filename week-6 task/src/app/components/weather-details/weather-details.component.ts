import { Component, OnInit } from '@angular/core';
import { weatherService } from '../../weather.service';


@Component({
  selector: 'app-weather-details',
  templateUrl: './weather-details.component.html',
  styleUrls: ['./weather-details.component.css']
})
export class weatherDetailsComponent implements OnInit {
  weather: any;

  constructor(private weatherService: weatherService) {}

  ngOnInit() {
    this.weatherService.getweatherData().subscribe(data => {
      this.weather = data;
    });
  }
}
