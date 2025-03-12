import { Component } from '@angular/core';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent {
  city: string = '';
  locationData: any = null;

  getLocationweather() {
    if (!this.city.trim()) {
      alert('Please enter a city name!');
      return;
    }

    // Simulated Data with Random Values
    this.locationData = {
      city: this.city,
      temp: (Math.random() * (35 - 15) + 15).toFixed(1), // Random temperature between 15°C - 35°C
      condition: this.getRandomCondition()
    };
  }

  getRandomCondition(): string {
    const conditions = ['Sunny ☀️', 'Cloudy ☁️', 'Rainy 🌧️', 'Stormy ⛈️', 'Snowy ❄️', 'Windy 🌬️'];
    return conditions[Math.floor(Math.random() * conditions.length)];
  }
}
