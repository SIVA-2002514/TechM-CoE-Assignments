import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'temperature'
})
export class TemperaturePipe implements PipeTransform {
  transform(value: number, unit: string = 'C'): string {
    if (!value && value !== 0) return 'N/A';

    let convertedTemp = value;
    let unitLabel = '°C';

    if (unit === 'F') {
      convertedTemp = (value * 9/5) + 32; // Convert Celsius to Fahrenheit
      unitLabel = '°F';
    }

    return `${convertedTemp.toFixed(1)} ${unitLabel}`;
  }
}
