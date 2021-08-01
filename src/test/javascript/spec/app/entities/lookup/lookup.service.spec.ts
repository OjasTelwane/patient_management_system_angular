import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LookupService } from 'app/entities/lookup/lookup.service';
import { ILookup, Lookup } from 'app/shared/model/lookup.model';

describe('Service Tests', () => {
  describe('Lookup Service', () => {
    let injector: TestBed;
    let service: LookupService;
    let httpMock: HttpTestingController;
    let elemDefault: ILookup;
    let expectedResult: ILookup | ILookup[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LookupService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Lookup(0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Lookup', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Lookup()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Lookup', () => {
        const returnedFromService = Object.assign(
          {
            lookup: 'BBBBBB',
            lookupType: 'BBBBBB',
            lookupOrder: 1,
            lookupNotes: 'BBBBBB',
            voided: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Lookup', () => {
        const returnedFromService = Object.assign(
          {
            lookup: 'BBBBBB',
            lookupType: 'BBBBBB',
            lookupOrder: 1,
            lookupNotes: 'BBBBBB',
            voided: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Lookup', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
