import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscoverTvComponent } from './discover-tv.component';

describe('DiscoverTvComponent', () => {
  let component: DiscoverTvComponent;
  let fixture: ComponentFixture<DiscoverTvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiscoverTvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiscoverTvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
