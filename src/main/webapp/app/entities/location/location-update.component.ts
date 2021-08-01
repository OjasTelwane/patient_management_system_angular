import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILocation, Location } from 'app/shared/model/location.model';
import { LocationService } from './location.service';

@Component({
  selector: 'jhi-location-update',
  templateUrl: './location-update.component.html',
})
export class LocationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    country: [],
    countryCode: [],
    region: [],
    conState: [],
    stateCode: [null, [Validators.maxLength(14)]],
    district: [],
    city: [],
    area: [],
    pincode: [null, [Validators.maxLength(14)]],
  });

  constructor(protected locationService: LocationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ location }) => {
      this.updateForm(location);
    });
  }

  updateForm(location: ILocation): void {
    this.editForm.patchValue({
      id: location.id,
      country: location.country,
      countryCode: location.countryCode,
      region: location.region,
      conState: location.conState,
      stateCode: location.stateCode,
      district: location.district,
      city: location.city,
      area: location.area,
      pincode: location.pincode,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const location = this.createFromForm();
    if (location.id !== undefined) {
      this.subscribeToSaveResponse(this.locationService.update(location));
    } else {
      this.subscribeToSaveResponse(this.locationService.create(location));
    }
  }

  private createFromForm(): ILocation {
    return {
      ...new Location(),
      id: this.editForm.get(['id'])!.value,
      country: this.editForm.get(['country'])!.value,
      countryCode: this.editForm.get(['countryCode'])!.value,
      region: this.editForm.get(['region'])!.value,
      conState: this.editForm.get(['conState'])!.value,
      stateCode: this.editForm.get(['stateCode'])!.value,
      district: this.editForm.get(['district'])!.value,
      city: this.editForm.get(['city'])!.value,
      area: this.editForm.get(['area'])!.value,
      pincode: this.editForm.get(['pincode'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocation>>): void {
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
