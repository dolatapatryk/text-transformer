import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnDetailComponent } from './own-detail.component';

describe('OwnDetailComponent', () => {
  let component: OwnDetailComponent;
  let fixture: ComponentFixture<OwnDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
