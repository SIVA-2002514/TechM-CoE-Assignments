import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ForecastComponent } from './components/forecast/forecast.component';
import { SearchComponent } from './components/search/search.component';
import { AlertsComponent } from './components/alerts/alerts.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'forecast', component: ForecastComponent },
  { path: 'search', component: SearchComponent },
  { path: 'alerts', component: AlertsComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' } // Default route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
