import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PatientTestModule } from '../../../test.module';
import { LookupDetailComponent } from 'app/entities/lookup/lookup-detail.component';
import { Lookup } from 'app/shared/model/lookup.model';

describe('Component Tests', () => {
  describe('Lookup Management Detail Component', () => {
    let comp: LookupDetailComponent;
    let fixture: ComponentFixture<LookupDetailComponent>;
    const route = ({ data: of({ lookup: new Lookup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [PatientTestModule],
        declarations: [LookupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LookupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LookupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load lookup on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.lookup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
