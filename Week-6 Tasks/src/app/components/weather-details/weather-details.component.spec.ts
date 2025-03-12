import { ComponentFixture, TestBed } from '@angular/core/testing';

import { weatherDetailsComponent } from './weather-details.component';

describe('weatherDetailsComponent', () => {
  let component: weatherDetailsComponent;
  let fixture: ComponentFixture<weatherDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [weatherDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(weatherDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
