import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { CountryService } from '../country.service';
import { Country } from '../model/country';

@Component({
  selector: 'app-country-list',
  templateUrl: './country-list.component.html',
  styleUrls: ['./country-list.component.css']
})
export class CountryListComponent implements OnInit {
  countries: Country[] = [];
  totalElements: number = 0;

  constructor(private countryService: CountryService) { }

  ngOnInit(): void {
    this.getCountries({ pageNum: "1", pageSize: "5" });
}
  
private getCountries(request) {
    this.countryService.getAll(request)
    .subscribe(data => {
        this.countries = data['content'];
        this.totalElements = data['total'];
    });
}

nextPage(event: PageEvent) {
  const request = {};
  request['pageNum'] = event.pageIndex +1;
  request['pageSize'] = event.pageSize.toString();
  this.getCountries(request);
}

}
