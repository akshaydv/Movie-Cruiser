import { Injectable } from '@angular/core';
import { Headers, Http, Jsonp } from '@angular/http';
import 'rxjs/Rx';
import { Movie } from './movie';
@Injectable()
export class SearchService {

  constructor(private http: Http) { }

  // Variable links
  private searchUrl = 'https://api.themoviedb.org/3/search/movie?api_key=cf8441d05e8376c04c2feb36bd5b492f&language=en-US&page=1&include_adult=false&query=';
  private movieUrl = "https://api.themoviedb.org/3/movie/";
  private TVUrl = "https://api.themoviedb.org/3/tv/";
  public sharedSearchResult: Array<Object> = [];
  private discMovies = "https://api.themoviedb.org/3/discover/movie?api_key=cf8441d05e8376c04c2feb36bd5b492f&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
  private apikey = "?api_key=cf8441d05e8376c04c2feb36bd5b492f&language=en-US";
  private nowPlayingUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=cf8441d05e8376c04c2feb36bd5b492f&language=en-US&page=1";
  private discTV = "https://api.themoviedb.org/3/discover/tv?api_key=cf8441d05e8376c04c2feb36bd5b492f&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1"
  private headers = new Headers({'Content-Type': 'application/json', 'Accept': 'application/json'});
  private SQLUrl = 'http://localhost:8080/v1.0/movieservice/movie/';
  // Movies Methods

  public searchMovies(term: string) {
    const url = `${this.searchUrl}${term}`;
    console.log(url);
    return this.http.get(url)
      .map(result => result.json());
  }
  getRecommendedMovies(id: number) {
    const url = this.movieUrl + id + '/recommendations' + this.apikey;
    console.log('Recommended Url', url);
    return this.http.get(url).map(result => result.json());
  }

  discoverMovies(){
    return this.http.get(this.discMovies).map(result => result.json());
  }

  getNowPlayingMovies() {
    return this.http.get(this.nowPlayingUrl).map(response => response.json());
  }

  getMovieById(id: number) {
    console.log('Get movie by Id', id);
    const url = this.movieUrl + id + this.apikey;
    return this.http.get(url).map(response => response.json());
  }

  getCast(id: number) {
    const url = this.movieUrl + id + '/credits' + this.apikey;
    console.log('Credits Url', url);
    return this.http.get(url).map(result => result.json());
  }

  addToWatchListMovie(id: number, name: string, comments: string, poster_path: string, release_date : Date, vote_average: number , vote_count: number) {
    const url = this.SQLUrl + 'save/';
    const json = JSON.stringify({id: id, name: name, comments: comments, poster_path: poster_path, release_date:release_date, vote_average:vote_average, vote_count:vote_count });
    console.log(json);
    console.log(url)
    return this.http.post(this.SQLUrl, json, {headers: this.headers}).toPromise().catch(this.handleError);
  }

  getWatchList() {
    console.log('Getting watchlist.'); 
    return this.http.get(this.SQLUrl).toPromise().then((res) => res.json(),
    (err) => err.json());
  }

  checkWatchList(id:number){
    console.log('Checking watchlist.'); 
    const url = this.SQLUrl + id;
    console.log(url);
    return this.http.get(url).toPromise().then(res => res ? res.json():null ).catch(this.handleError);
  }

  updateMovieComments(id: number,comments: string){
    const url = this.SQLUrl + id;
    console.log(url, id, comments )
    return this.http.put(url, comments).toPromise().catch(this.handleError);
  }

  removeMovieFromWatchList(id: number){ 
    const uri = `${this.SQLUrl}${id}`;
    console.log("DETLET", uri);
    return this.http.delete(uri, {headers: this.headers}).toPromise().catch(this.handleError);
  }

  private handleError(error: any) {
    console.error('An error occurred', error); // for demo purposes only
  }
  // TV Methods
  public searchTV(term: string) {
    const url = `${this.searchUrl}${term}`;
    console.log(url);
    return this.http.get(url)
      .map(result => result.json());
  }

  getNowPlayingTV() {
    const url = "https://api.themoviedb.org/3/tv/popular?api_key=cf8441d05e8376c04c2feb36bd5b492f&language=en-US&page=1"
    return this.http.get(url).map(response => response.json());
  }

  getTVById(id: number) {
    console.log('Get movie by Id', id);
    const url = this.TVUrl + id + this.apikey;
    return this.http.get(url).map(response => response.json());
  }

  discoverTV() {
    return this.http.get(this.discTV).map(result => result.json());
  }

  getRecommendedTV(id: number) {
    const url = this.TVUrl + id + '/recommendations' + this.apikey;
    console.log('Recommended Url', url);
    return this.http.get(url).map(result => result.json());
  }
  getCastTV(id: number) {
    const url = this.TVUrl + id + '/credits' + this.apikey;
    console.log('Credits Url', url);
    return this.http.get(url).map(result => result.json());
  }

}
