import { SearchService } from './../search.service';
import { Movie } from './../movie';
import { Component, OnInit, Input } from '@angular/core';
import { Location } from '@angular/common';
import 'rxjs/add/operator/switchMap';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {

  movie: Movie;
  recommended: Array<Object> = [];
  cast: Array<Object> = [];
  inWatchList: boolean = true;
  constructor(
    private router: Router,
    private searchService: SearchService,
    private route: ActivatedRoute,
    private location: Location) { }

  goToDetail(id: number) {
    console.log('Get to detail', id);
    const link = ['/movie', id];
    this.router.navigate(link);
  }

  addToWatchList(id: number, name: string, comments: string, poster_path: string, release_date : Date, vote_average: number , vote_count: number) {
    console.log('Add to watchlist', id);
    this.searchService.addToWatchListMovie(id, name, comments, poster_path, release_date, vote_average, vote_count)
    this.inWatchList = true;
  }

  updateComments(id: number,comments: string){
    this.searchService.updateMovieComments(id,comments);
  }

  ngOnInit() {
    this.route.paramMap
    .switchMap((params: ParamMap) => this.searchService.getMovieById(+params.get('id')))
    .subscribe(movie => this.movie = movie);

    this.route.params.map(params => params['id'])
    .switchMap(id => this.searchService.getRecommendedMovies(id))
    .subscribe(response => this.recommended = response.results.splice(0, 6));

    this.route.params.map(params => params['id'])
    .switchMap(id => this.searchService.getCast(id))
    .subscribe(response => this.cast = response.cast.splice(0, 3));

    this.route.params.map(params => params['id'])
    .switchMap(id => this.searchService.checkWatchList(id))
    .subscribe(response => {
      if(response === undefined) this.inWatchList = false;
      console.log("responses ",response);
    });
  }

  removeFromWatchList(id:number){
    this.searchService.removeMovieFromWatchList(id).then(() => this.inWatchList = false);
  }
}
