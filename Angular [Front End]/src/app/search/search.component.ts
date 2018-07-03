import { SearchService } from './../search.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private searchService:SearchService,private router:Router) { }

  private searchQuery: string;
  navigateTo(){
    let link = ['/search-results'];
    console.log("Navigating to Search Result Component",link);
    this.router.navigate(link);
  }

  public search() {
    console.log('searchMovies: ' + this.searchQuery)
    this.searchService.searchMovies(this.searchQuery).subscribe(response => {
      console.log('sharedSearchMovies');
      console.log(response.results);
      this.searchService.sharedSearchResult = response.results;
      this.navigateTo();
    });
  }
  
  ngOnInit() {
    console.log("Search Component invoked.");
  }

}
