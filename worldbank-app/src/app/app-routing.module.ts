import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CountryListComponent } from './country-list/country-list.component';
import { IndicatorListComponent } from './indicator-list/indicator-list.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'countries', component: CountryListComponent},
  { path: 'indicators/:id', component: IndicatorListComponent },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
