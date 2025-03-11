import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'windSpeed'
})
export class WindSpeedPipe implements PipeTransform {
  transform(value: number, unit: string = 'kmh'): string {
    if (!value && value !== 0) return 'N/A';

    let convertedSpeed = value; 
    let unitLabel = 'km/h';

    if (unit === 'mph') {
      convertedSpeed = value * 0.621371; // Convert km/h to mph
      unitLabel = 'mph';
    } else if (unit === 'mps') {
      convertedSpeed = value / 3.6; // Convert km/h to m/s
      unitLabel = 'm/s';
    }

    return `${convertedSpeed.toFixed(1)} ${unitLabel}`;
  }
}
