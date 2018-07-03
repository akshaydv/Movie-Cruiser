import { async, TestBed, inject } from '@angular/core/testing';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { Http, BaseRequestOptions, 
         Response, ResponseOptions, RequestMethod,
         HttpModule, XHRBackend } from '@angular/http';
import { SearchService } from './search.service';
import { Injectable }     from '@angular/core'; 
import { Observable }     from 'rxjs/Observable';
import { RouterLinkStubDirective } from './router-stubs';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'; 
import { RouterTestingModule } from '@angular/router/testing';
describe('Http-SearchService (mockBackend)', () => {
  beforeEach( async(() => {
    TestBed.configureTestingModule({
      declarations: [RouterLinkStubDirective],
      imports: [ HttpModule,RouterTestingModule ],
      providers: [
        SearchService,
        { provide: XHRBackend, useClass: MockBackend }
      ]
    })
    .compileComponents();
  }));
  it('can instantiate service when inject service',
    inject([SearchService], (service: SearchService) => {
      expect(service instanceof SearchService).toBe(true);
  }));
  it('can instantiate service with "new"', inject([Http], (http: Http) => {
    expect(http).not.toBeNull('http should be provided');
    let service = new SearchService(http);
    expect(service instanceof SearchService).toBe(true, 'new service should be ok');
  }));
  it('can provide the mockBackend as XHRBackend',
    inject([XHRBackend], (backend: MockBackend) => {
      expect(backend).not.toBeNull('backend should be provided');
  }));
  
});