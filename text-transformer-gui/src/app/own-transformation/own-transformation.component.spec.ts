import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnTransformationComponent } from './own-transformation.component';

describe('OwnTransformationComponent', () => {
  let component: OwnTransformationComponent;
  let fixture: ComponentFixture<OwnTransformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnTransformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnTransformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
