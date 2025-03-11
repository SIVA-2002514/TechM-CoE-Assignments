import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.css']
})
export class AlertsComponent implements OnInit {
  alerts = [
    { type: 'Storm Warning', message: 'Heavy rain expected tomorrow.' },
    { type: 'Heatwave Alert', message: 'High temperatures reaching 40°C' }
  ];

  ngOnInit() {}
}
