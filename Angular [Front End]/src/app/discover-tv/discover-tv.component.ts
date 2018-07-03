import { Router } from '@angular/router';
import { SearchService } from './../search.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-discover-tv',
  templateUrl: './discover-tv.component.html',
  styleUrls: ['./discover-tv.component.css']
})
export class DiscoverTvComponent implements OnInit {

  constructor(private searchService:SearchService,private router:Router) { }
  discTV : Array<Object> = [];
  ngOnInit() {

    this.searchService.discoverTV().subscribe(response => {
      console.log('discoverTVResults');
      console.log(response.results);
      this.discTV = response.results; 
    });;

  }
  goToDetailTV(id:number){  
    console.log("Get to detail",id);
    let link = ['/tv', id];
    this.router.navigate(link);
  }

}
