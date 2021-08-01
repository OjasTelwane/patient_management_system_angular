import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IPatient, Patient } from 'app/shared/model/patient.model';
import { PatientService } from './patient.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-patient-update',
  templateUrl: './patient-update.component.html',
})
export class PatientUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    formNumber: [null, [Validators.required]],
    centerId: [null, [Validators.required]],
    centerName: [null, [Validators.required]],
    firstName: [null, [Validators.required, Validators.maxLength(20)]],
    middleName: [null, [Validators.maxLength(20)]],
    lastName: [null, [Validators.required, Validators.maxLength(20)]],
    displayName: [null, [Validators.maxLength(64)]],
    dateOfBirth: [],
    gender: [],
    age: [],
    address: [null, [Validators.maxLength(255)]],
    area: [],
    city: [],
    district: [],
    conState: [],
    stateCode: [null, [Validators.maxLength(14)]],
    region: [],
    country: [],
    countryCode: [],
    pincode: [null, [Validators.maxLength(14)]],
    photo1: [],
    photo1ContentType: [],
    photo2: [],
    photo2ContentType: [],
    mobileNumber: [null, [Validators.required, Validators.maxLength(15)]],
    contactNo: [null, [Validators.maxLength(20)]],
    education: [null, [Validators.maxLength(50)]],
    bloodGroup: [null, [Validators.maxLength(50)]],
    email: [],
    regDate: [],
    profession: [null, [Validators.maxLength(50)]],
    marriageStatus: [null, [Validators.maxLength(20)]],
    expired: [],
    languageName: [null, [Validators.maxLength(20)]],
    vegNonVeg: [],
    referedBy: [null, [Validators.maxLength(255)]],
    referdByName: [null, [Validators.maxLength(255)]],
    exerciseRegularly: [],
    addiction: [],
    hoursSleep: [],
    wakeRested: [],
    waterIntake: [],
    hoursWork: [],
    shiftWork: [],
    levelOfStress: [null, [Validators.maxLength(20)]],
    patientCondition: [null, [Validators.maxLength(4096)]],
    currentMedication: [null, [Validators.maxLength(4096)]],
    chronologicalHistory: [null, [Validators.maxLength(4096)]],
    symptomaticChanges: [null, [Validators.maxLength(4096)]],
    levelOfDiscomfort: [],
    feesDiscount: [],
    feesDiscountGivenBy: [],
    discountType: [],
    discount: [],
    allowEmail: [],
    allowSMS: [],
    allowWhatsApp: [],
    allowPromotions: [],
    iAgree: [],
    healingLevel: [],
    allowLogin: [],
    password: [],
    voided: [],
    userId: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected patientService: PatientService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patient }) => {
      if (!patient.id) {
        const today = moment().startOf('day');
        patient.dateOfBirth = today;
        patient.regDate = today;
      }

      this.updateForm(patient);
    });
  }

  updateForm(patient: IPatient): void {
    this.editForm.patchValue({
      id: patient.id,
      formNumber: patient.formNumber,
      centerId: patient.centerId,
      centerName: patient.centerName,
      firstName: patient.firstName,
      middleName: patient.middleName,
      lastName: patient.lastName,
      displayName: patient.displayName,
      dateOfBirth: patient.dateOfBirth ? patient.dateOfBirth.format(DATE_TIME_FORMAT) : null,
      gender: patient.gender,
      age: patient.age,
      address: patient.address,
      area: patient.area,
      city: patient.city,
      district: patient.district,
      conState: patient.conState,
      stateCode: patient.stateCode,
      region: patient.region,
      country: patient.country,
      countryCode: patient.countryCode,
      pincode: patient.pincode,
      photo1: patient.photo1,
      photo1ContentType: patient.photo1ContentType,
      photo2: patient.photo2,
      photo2ContentType: patient.photo2ContentType,
      mobileNumber: patient.mobileNumber,
      contactNo: patient.contactNo,
      education: patient.education,
      bloodGroup: patient.bloodGroup,
      email: patient.email,
      regDate: patient.regDate ? patient.regDate.format(DATE_TIME_FORMAT) : null,
      profession: patient.profession,
      marriageStatus: patient.marriageStatus,
      expired: patient.expired,
      languageName: patient.languageName,
      vegNonVeg: patient.vegNonVeg,
      referedBy: patient.referedBy,
      referdByName: patient.referdByName,
      exerciseRegularly: patient.exerciseRegularly,
      addiction: patient.addiction,
      hoursSleep: patient.hoursSleep,
      wakeRested: patient.wakeRested,
      waterIntake: patient.waterIntake,
      hoursWork: patient.hoursWork,
      shiftWork: patient.shiftWork,
      levelOfStress: patient.levelOfStress,
      patientCondition: patient.patientCondition,
      currentMedication: patient.currentMedication,
      chronologicalHistory: patient.chronologicalHistory,
      symptomaticChanges: patient.symptomaticChanges,
      levelOfDiscomfort: patient.levelOfDiscomfort,
      feesDiscount: patient.feesDiscount,
      feesDiscountGivenBy: patient.feesDiscountGivenBy,
      discountType: patient.discountType,
      discount: patient.discount,
      allowEmail: patient.allowEmail,
      allowSMS: patient.allowSMS,
      allowWhatsApp: patient.allowWhatsApp,
      allowPromotions: patient.allowPromotions,
      iAgree: patient.iAgree,
      healingLevel: patient.healingLevel,
      allowLogin: patient.allowLogin,
      password: patient.password,
      voided: patient.voided,
      userId: patient.userId,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: any, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('patientApp.error', { message: err.message })
      );
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const patient = this.createFromForm();
    if (patient.id !== undefined) {
      this.subscribeToSaveResponse(this.patientService.update(patient));
    } else {
      this.subscribeToSaveResponse(this.patientService.create(patient));
    }
  }

  private createFromForm(): IPatient {
    return {
      ...new Patient(),
      id: this.editForm.get(['id'])!.value,
      formNumber: this.editForm.get(['formNumber'])!.value,
      centerId: this.editForm.get(['centerId'])!.value,
      centerName: this.editForm.get(['centerName'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      middleName: this.editForm.get(['middleName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      displayName: this.editForm.get(['displayName'])!.value,
      dateOfBirth: this.editForm.get(['dateOfBirth'])!.value
        ? moment(this.editForm.get(['dateOfBirth'])!.value, DATE_TIME_FORMAT)
        : undefined,
      gender: this.editForm.get(['gender'])!.value,
      age: this.editForm.get(['age'])!.value,
      address: this.editForm.get(['address'])!.value,
      area: this.editForm.get(['area'])!.value,
      city: this.editForm.get(['city'])!.value,
      district: this.editForm.get(['district'])!.value,
      conState: this.editForm.get(['conState'])!.value,
      stateCode: this.editForm.get(['stateCode'])!.value,
      region: this.editForm.get(['region'])!.value,
      country: this.editForm.get(['country'])!.value,
      countryCode: this.editForm.get(['countryCode'])!.value,
      pincode: this.editForm.get(['pincode'])!.value,
      photo1ContentType: this.editForm.get(['photo1ContentType'])!.value,
      photo1: this.editForm.get(['photo1'])!.value,
      photo2ContentType: this.editForm.get(['photo2ContentType'])!.value,
      photo2: this.editForm.get(['photo2'])!.value,
      mobileNumber: this.editForm.get(['mobileNumber'])!.value,
      contactNo: this.editForm.get(['contactNo'])!.value,
      education: this.editForm.get(['education'])!.value,
      bloodGroup: this.editForm.get(['bloodGroup'])!.value,
      email: this.editForm.get(['email'])!.value,
      regDate: this.editForm.get(['regDate'])!.value ? moment(this.editForm.get(['regDate'])!.value, DATE_TIME_FORMAT) : undefined,
      profession: this.editForm.get(['profession'])!.value,
      marriageStatus: this.editForm.get(['marriageStatus'])!.value,
      expired: this.editForm.get(['expired'])!.value,
      languageName: this.editForm.get(['languageName'])!.value,
      vegNonVeg: this.editForm.get(['vegNonVeg'])!.value,
      referedBy: this.editForm.get(['referedBy'])!.value,
      referdByName: this.editForm.get(['referdByName'])!.value,
      exerciseRegularly: this.editForm.get(['exerciseRegularly'])!.value,
      addiction: this.editForm.get(['addiction'])!.value,
      hoursSleep: this.editForm.get(['hoursSleep'])!.value,
      wakeRested: this.editForm.get(['wakeRested'])!.value,
      waterIntake: this.editForm.get(['waterIntake'])!.value,
      hoursWork: this.editForm.get(['hoursWork'])!.value,
      shiftWork: this.editForm.get(['shiftWork'])!.value,
      levelOfStress: this.editForm.get(['levelOfStress'])!.value,
      patientCondition: this.editForm.get(['patientCondition'])!.value,
      currentMedication: this.editForm.get(['currentMedication'])!.value,
      chronologicalHistory: this.editForm.get(['chronologicalHistory'])!.value,
      symptomaticChanges: this.editForm.get(['symptomaticChanges'])!.value,
      levelOfDiscomfort: this.editForm.get(['levelOfDiscomfort'])!.value,
      feesDiscount: this.editForm.get(['feesDiscount'])!.value,
      feesDiscountGivenBy: this.editForm.get(['feesDiscountGivenBy'])!.value,
      discountType: this.editForm.get(['discountType'])!.value,
      discount: this.editForm.get(['discount'])!.value,
      allowEmail: this.editForm.get(['allowEmail'])!.value,
      allowSMS: this.editForm.get(['allowSMS'])!.value,
      allowWhatsApp: this.editForm.get(['allowWhatsApp'])!.value,
      allowPromotions: this.editForm.get(['allowPromotions'])!.value,
      iAgree: this.editForm.get(['iAgree'])!.value,
      healingLevel: this.editForm.get(['healingLevel'])!.value,
      allowLogin: this.editForm.get(['allowLogin'])!.value,
      password: this.editForm.get(['password'])!.value,
      voided: this.editForm.get(['voided'])!.value,
      userId: this.editForm.get(['userId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatient>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
