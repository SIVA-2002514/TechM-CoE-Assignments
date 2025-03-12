import { Component } from '@angular/core';
import { WeatherService } from '../../weather.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  city: string = '';
  weatherData: any = null;
  errorMessage: string = '';

  constructor(private weatherService: WeatherService) {}

  searchWeather() {
    if (this.city.trim() === '') {
      this.errorMessage = 'Please enter a city name';
      this.weatherData = null;
      return;
    }

    this.weatherService.getWeather(this.city).subscribe(
      (data) => {
        if (data) {
          this.weatherData = data;
          this.errorMessage = '';
        } else {
          this.weatherData = null;
          this.errorMessage = 'No results found.';
        }
      },
      () => {
        this.weatherData = null;
        this.errorMessage = 'No results found.';
      }
    );
  }
}
