import { Component, OnInit } from '@angular/core';
import { IndicatorService } from '../indicator.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Indicator } from '../model/indicator';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-indicator-list',
  templateUrl: './indicator-list.component.html',
  styleUrls: ['./indicator-list.component.css']
})
export class IndicatorListComponent implements OnInit {

  id: string | undefined;
  indicators: Indicator[] = [];
  totalElements: number = 0;
  indicatorAtual?:Indicator;

  constructor(
    public indicatorService: IndicatorService,
    private route: ActivatedRoute
   ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.getIndicators(this.id, {pageNum: "1", pageSize: "5" });
  }
  
  private getIndicators(id, request) {
    this.indicatorService.findIndicatorByCountry(id, request)
    .subscribe(data => {
        this.indicators = data['content'];
        this.totalElements = data['total'];
        this.indicatorAtual = this.indicators[0];
    });
  }

  nextPage(event: PageEvent) {
    const request = {};
    request['pageNum'] = event.pageIndex +1;
    request['pageSize'] = event.pageSize.toString();
    this.getIndicators(this.id, request);
  }

}
