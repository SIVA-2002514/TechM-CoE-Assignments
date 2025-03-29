import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WeatherService {
  constructor() {}

  getWeather(city: string): Observable<any> {
    if (!city) return of(null);

    const randomTemperature = (Math.random() * (40 - 15) + 15).toFixed(1);
    const randomHumidity = Math.floor(Math.random() * (90 - 30) + 30);
    const randomWindSpeed = (Math.random() * (10 - 1) + 1).toFixed(1);

    const weatherDescriptions = ['Sunny', 'Cloudy', 'Rainy', 'Stormy', 'Windy'];
    const randomWeather = weatherDescriptions[Math.floor(Math.random() * weatherDescriptions.length)];

    const weatherData = {
      name: city,
      main: { temp: randomTemperature, humidity: randomHumidity },
      wind: { speed: randomWindSpeed },
      weather: [{ description: randomWeather }]
    };

    return of(weatherData);
  }
}
