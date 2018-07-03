import { Router } from '@angular/router';
import { SearchService } from './../search.service';
import { Component, OnInit } from '@angular/core';
import { Movie } from './../movie';
import {Observable} from 'rxjs/Rx';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  constructor(private searchService: SearchService,
              private router: Router) { }
  watchList: Array<any>; 

  ngOnInit() { 
    this.searchService.getWatchList().then((res)=>{
      if(res){
        this.watchList = res;
        console.log(res); 
      } 
    }) 

  }
  refreshMovies(){
    this.searchService.getWatchList().then((res)=>{
      if(res){
        this.watchList = res;
        console.log(res); 
      } 
    }) 
  } 
  updateComments(id: number,comments: string){
    console.log(comments);
    this.searchService.updateMovieComments(id,comments);
  }
  goToDetail(id: number) {
    console.log('Get to detail', id);
    const link = ['/movie', id];
    this.router.navigate(link);
  }

  removeFromWatchList(id:number){
    this.searchService.removeMovieFromWatchList(id).then(() => this.refreshMovies());
  }

}
