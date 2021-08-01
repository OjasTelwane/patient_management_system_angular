import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'patient',
        loadChildren: () => import('./patient/patient.module').then(m => m.PatientPatientModule),
      },
      {
        path: 'lookup',
        loadChildren: () => import('./lookup/lookup.module').then(m => m.PatientLookupModule),
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.PatientLocationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class PatientEntityModule {}
