import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILookup, Lookup } from 'app/shared/model/lookup.model';
import { LookupService } from './lookup.service';

@Component({
  selector: 'jhi-lookup-update',
  templateUrl: './lookup-update.component.html',
})
export class LookupUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    lookup: [null, [Validators.required]],
    lookupType: [null, [Validators.required]],
    lookupOrder: [],
    lookupNotes: [null, [Validators.maxLength(4096)]],
    voided: [],
  });

  constructor(protected lookupService: LookupService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lookup }) => {
      this.updateForm(lookup);
    });
  }

  updateForm(lookup: ILookup): void {
    this.editForm.patchValue({
      id: lookup.id,
      lookup: lookup.lookup,
      lookupType: lookup.lookupType,
      lookupOrder: lookup.lookupOrder,
      lookupNotes: lookup.lookupNotes,
      voided: lookup.voided,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lookup = this.createFromForm();
    if (lookup.id !== undefined) {
      this.subscribeToSaveResponse(this.lookupService.update(lookup));
    } else {
      this.subscribeToSaveResponse(this.lookupService.create(lookup));
    }
  }

  private createFromForm(): ILookup {
    return {
      ...new Lookup(),
      id: this.editForm.get(['id'])!.value,
      lookup: this.editForm.get(['lookup'])!.value,
      lookupType: this.editForm.get(['lookupType'])!.value,
      lookupOrder: this.editForm.get(['lookupOrder'])!.value,
      lookupNotes: this.editForm.get(['lookupNotes'])!.value,
      voided: this.editForm.get(['voided'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILookup>>): void {
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
