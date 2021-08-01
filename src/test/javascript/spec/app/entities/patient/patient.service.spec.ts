import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PatientService } from 'app/entities/patient/patient.service';
import { IPatient, Patient } from 'app/shared/model/patient.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { DiscountType } from 'app/shared/model/enumerations/discount-type.model';
import { HealingLevel } from 'app/shared/model/enumerations/healing-level.model';

describe('Service Tests', () => {
  describe('Patient Service', () => {
    let injector: TestBed;
    let service: PatientService;
    let httpMock: HttpTestingController;
    let elemDefault: IPatient;
    let expectedResult: IPatient | IPatient[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PatientService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Patient(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        Gender.Male,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        0,
        false,
        0,
        0,
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
        'AAAAAAA',
        DiscountType.NoDiscount,
        0,
        false,
        false,
        false,
        false,
        false,
        HealingLevel.Simple,
        false,
        'AAAAAAA',
        false,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT),
            regDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Patient', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT),
            regDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfBirth: currentDate,
            regDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Patient()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Patient', () => {
        const returnedFromService = Object.assign(
          {
            formNumber: 'BBBBBB',
            centerId: 'BBBBBB',
            centerName: 'BBBBBB',
            firstName: 'BBBBBB',
            middleName: 'BBBBBB',
            lastName: 'BBBBBB',
            displayName: 'BBBBBB',
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT),
            gender: 'BBBBBB',
            age: 1,
            address: 'BBBBBB',
            area: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            conState: 'BBBBBB',
            stateCode: 'BBBBBB',
            region: 'BBBBBB',
            country: 'BBBBBB',
            countryCode: 'BBBBBB',
            pincode: 'BBBBBB',
            photo1: 'BBBBBB',
            photo2: 'BBBBBB',
            mobileNumber: 'BBBBBB',
            contactNo: 'BBBBBB',
            education: 'BBBBBB',
            bloodGroup: 'BBBBBB',
            email: 'BBBBBB',
            regDate: currentDate.format(DATE_TIME_FORMAT),
            profession: 'BBBBBB',
            marriageStatus: 'BBBBBB',
            expired: true,
            languageName: 'BBBBBB',
            vegNonVeg: true,
            referedBy: 'BBBBBB',
            referdByName: 'BBBBBB',
            exerciseRegularly: true,
            addiction: true,
            hoursSleep: 1,
            wakeRested: true,
            waterIntake: 1,
            hoursWork: 1,
            shiftWork: true,
            levelOfStress: 'BBBBBB',
            patientCondition: 'BBBBBB',
            currentMedication: 'BBBBBB',
            chronologicalHistory: 'BBBBBB',
            symptomaticChanges: 'BBBBBB',
            levelOfDiscomfort: 1,
            feesDiscount: true,
            feesDiscountGivenBy: 'BBBBBB',
            discountType: 'BBBBBB',
            discount: 1,
            allowEmail: true,
            allowSMS: true,
            allowWhatsApp: true,
            allowPromotions: true,
            iAgree: true,
            healingLevel: 'BBBBBB',
            allowLogin: true,
            password: 'BBBBBB',
            voided: true,
            userId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfBirth: currentDate,
            regDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Patient', () => {
        const returnedFromService = Object.assign(
          {
            formNumber: 'BBBBBB',
            centerId: 'BBBBBB',
            centerName: 'BBBBBB',
            firstName: 'BBBBBB',
            middleName: 'BBBBBB',
            lastName: 'BBBBBB',
            displayName: 'BBBBBB',
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT),
            gender: 'BBBBBB',
            age: 1,
            address: 'BBBBBB',
            area: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            conState: 'BBBBBB',
            stateCode: 'BBBBBB',
            region: 'BBBBBB',
            country: 'BBBBBB',
            countryCode: 'BBBBBB',
            pincode: 'BBBBBB',
            photo1: 'BBBBBB',
            photo2: 'BBBBBB',
            mobileNumber: 'BBBBBB',
            contactNo: 'BBBBBB',
            education: 'BBBBBB',
            bloodGroup: 'BBBBBB',
            email: 'BBBBBB',
            regDate: currentDate.format(DATE_TIME_FORMAT),
            profession: 'BBBBBB',
            marriageStatus: 'BBBBBB',
            expired: true,
            languageName: 'BBBBBB',
            vegNonVeg: true,
            referedBy: 'BBBBBB',
            referdByName: 'BBBBBB',
            exerciseRegularly: true,
            addiction: true,
            hoursSleep: 1,
            wakeRested: true,
            waterIntake: 1,
            hoursWork: 1,
            shiftWork: true,
            levelOfStress: 'BBBBBB',
            patientCondition: 'BBBBBB',
            currentMedication: 'BBBBBB',
            chronologicalHistory: 'BBBBBB',
            symptomaticChanges: 'BBBBBB',
            levelOfDiscomfort: 1,
            feesDiscount: true,
            feesDiscountGivenBy: 'BBBBBB',
            discountType: 'BBBBBB',
            discount: 1,
            allowEmail: true,
            allowSMS: true,
            allowWhatsApp: true,
            allowPromotions: true,
            iAgree: true,
            healingLevel: 'BBBBBB',
            allowLogin: true,
            password: 'BBBBBB',
            voided: true,
            userId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfBirth: currentDate,
            regDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Patient', () => {
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
