import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  searchQuery: string = '';

  onSearch() {
    console.log('Searching for:', this.searchQuery);
    // Add logic to filter weather data if needed
  }
}
