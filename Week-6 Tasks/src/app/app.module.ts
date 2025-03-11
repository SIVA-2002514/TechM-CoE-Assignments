import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SearchComponent } from './components/search/search.component';
import { FooterComponent } from './components/footer/footer.component';
import { ForecastComponent } from './components/forecast/forecast.component';
import { LocationComponent } from './components/location/location.component';
import { AlertsComponent } from './components/alerts/alerts.component';
import { TemperaturePipe } from './temperature.pipe';
import { WindSpeedPipe } from './wind-speed.pipe';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SearchComponent,
 
    FooterComponent,
    ForecastComponent,
    LocationComponent,
    AlertsComponent,
    TemperaturePipe,
    WindSpeedPipe,
    RegisterComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
