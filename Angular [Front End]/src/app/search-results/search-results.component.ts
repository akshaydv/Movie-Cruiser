import { Router } from '@angular/router';
import { SearchService } from './../search.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  constructor(private searchService:SearchService,private router:Router) { }
  
  ngOnInit() {
  }
  goToDetail(id:number){  
    console.log("Get to detail",id);
    let link = ['/movie', id];
    this.router.navigate(link);
  }

}
