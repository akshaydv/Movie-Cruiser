import { Router } from '@angular/router';
import { SearchService } from './../search.service'; 
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private searchService:SearchService,
              private router:Router) { }

  goToDetail(id:number){  
    console.log("Get to detail",id);
    let link = ['/movie', id];
    this.router.navigate(link);
  }

  goToDetailTV(id:number){  
    console.log("Get to detailTV",id);
    let link = ['/tv', id];
    this.router.navigate(link);
  }
  nowPlayingMovies : Array<Object> = [];
  nowPlayingTV : Array<Object> = [];
  ngOnInit() {
    this.searchService.getNowPlayingMovies().subscribe(response => {
      console.log('nowPlayingResults');
      console.log(response.results);
      this.nowPlayingMovies = response.results.splice(0,3); 
    });;

    this.searchService.getNowPlayingTV().subscribe(response => {
      console.log('nowPlayingResultsTV');
      console.log(response.results);
      this.nowPlayingTV = response.results; 
    });;
  }

}
