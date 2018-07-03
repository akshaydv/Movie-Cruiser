import { Router } from '@angular/router';
import { SearchService } from './../search.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-discover-movies',
  templateUrl: './discover-movies.component.html',
  styleUrls: ['./discover-movies.component.css']
})
export class DiscoverMoviesComponent implements OnInit {

  constructor(private searchService:SearchService,private router:Router) { }
  discMovies : Array<Object> = [];
  ngOnInit() {

    this.searchService.discoverMovies().subscribe(response => {
      console.log('nowPlayingResults');
      console.log(response.results);
      this.discMovies = response.results; 
    });;

  }
  goToDetail(id:number){  
    console.log("Get to detail",id);
    let link = ['/movie', id];
    this.router.navigate(link);
  }

}
