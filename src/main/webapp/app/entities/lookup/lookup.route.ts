import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILookup, Lookup } from 'app/shared/model/lookup.model';
import { LookupService } from './lookup.service';
import { LookupComponent } from './lookup.component';
import { LookupDetailComponent } from './lookup-detail.component';
import { LookupUpdateComponent } from './lookup-update.component';

@Injectable({ providedIn: 'root' })
export class LookupResolve implements Resolve<ILookup> {
  constructor(private service: LookupService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILookup> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((lookup: HttpResponse<Lookup>) => {
          if (lookup.body) {
            return of(lookup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Lookup());
  }
}

export const lookupRoute: Routes = [
  {
    path: '',
    component: LookupComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Lookups',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LookupDetailComponent,
    resolve: {
      lookup: LookupResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Lookups',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LookupUpdateComponent,
    resolve: {
      lookup: LookupResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Lookups',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LookupUpdateComponent,
    resolve: {
      lookup: LookupResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Lookups',
    },
    canActivate: [UserRouteAccessService],
  },
];
