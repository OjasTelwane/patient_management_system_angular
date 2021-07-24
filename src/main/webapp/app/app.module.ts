import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { PatientSharedModule } from 'app/shared/shared.module';
import { PatientCoreModule } from 'app/core/core.module';
import { PatientAppRoutingModule } from './app-routing.module';
import { PatientHomeModule } from './home/home.module';
import { PatientEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    PatientSharedModule,
    PatientCoreModule,
    PatientHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    PatientEntityModule,
    PatientAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class PatientAppModule {}
