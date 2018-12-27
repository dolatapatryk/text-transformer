import { TestBed } from '@angular/core/testing';

import { OwnTransformationService } from './own-transformation.service';

describe('OwnTransformationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OwnTransformationService = TestBed.get(OwnTransformationService);
    expect(service).toBeTruthy();
  });
});
