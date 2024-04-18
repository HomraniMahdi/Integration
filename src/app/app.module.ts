import { NgModule } from '@angular/core';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserModule } from '@angular/platform-browser';
import { MatDialogModule } from '@angular/material/dialog';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';  
import { HttpClientModule } from '@angular/common/http'; 
import { MatStepperModule } from '@angular/material/stepper'; 
import { MatIconModule } from '@angular/material/icon';
// import {DataTablesModule} from 'angular-datatables'; 
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { SidebarComponent } from './layouts/sidebar/sidebar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { AsideComponent } from './layouts/aside/aside.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CandidatureComponent } from './pages/candidature/candidature.component';
import { CommunicationComponent } from './pages/communication/communication.component';
import { EntrepriseComponent } from './pages/entreprise/entreprise.component';
import { EvenementComponent } from './pages/evenement/evenement.component';
import { UserprofilComponent } from './pages/userprofil/userprofil.component';
import { EtudiantListComponent } from './pages/etudiant/etudiant-list/etudiant-list.component';
import { EtudiantNewComponent } from './pages/etudiant/etudiant-new/etudiant-new.component';
import { EtudiantUpdateComponent } from './pages/etudiant/etudiant-update/etudiant-update.component';
import { TimelineComponent } from './pages/timeline-component/timeline-component.component';
import { DemandeStageComponent } from './pages/demande-stage/demande-stage.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ConventionComponent } from './pages/convention/convention.component';
import { ConfirmationdialogComponent } from './pages/confirmationdialog/confirmationdialog.component';
import { ConventionFormComponent } from './pages/convention-form/convention-form.component';
import { JournalStageComponent } from './pages/journal-stage/journal-stage.component';
import { CategoryListComponent } from './pages/category/category-list/category-list.component';
import { NewCategoryComponent } from './pages/category/new-category/new-category.component';
import { StageComponent } from './pages/stage/stage.component';
import { CandidatureFronteeComponent } from './pages/candidature-frontee/candidature-frontee.component';
import { ParticiperEventComponent } from './pages/participer-event/participer-event.component';
import { EvenementUserComponent } from './pages/evenement-user/evenement-user.component';
import { EntretienComponent } from './pages/entretien/entretien.component';
import { FormModifyCandComponent } from './pages/form-modify-cand/form-modify-cand.component';
import { EntretienEntrepriseComponent } from './pages/entretien-entreprise/entretien-entreprise.component';
import { ChatComponent } from './pages/chat/chat.component';
import { BlogsComponent } from './pages/blogs/blogs.component';
import { CreatePost1Component } from './pages/create-post1/create-post1.component';
import { SearchByNameComponent } from './pages/search-by-name/search-by-name.component';
import { ViewAllComponent } from './pages/view-all/view-all.component';
import { ViewPostComponent } from './pages/view-post/view-post.component';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatToolbarModule} from '@angular/material/toolbar'; 
import { MatGridListModule } from '@angular/material/grid-list';
import { LayoutModule } from './feature/layout/layout.module';
import { CancelPaymentComponent } from './pages/cancel-payment/cancel-payment.component';
import { SuccessPaymentComponent } from './pages/success-payment/success-payment.component';
import { AllFilesComponent } from './pages/all-files/all-files.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    FooterComponent,
    AsideComponent,
    DashboardComponent,
    CandidatureComponent,
    CommunicationComponent,
    EntrepriseComponent,
    EvenementComponent,
    UserprofilComponent,
    EtudiantListComponent,
    EtudiantNewComponent,
    EtudiantUpdateComponent,
    TimelineComponent,
    DemandeStageComponent,
    ConventionComponent,
    ConfirmationdialogComponent,
    ConventionFormComponent,
    JournalStageComponent,
    CategoryListComponent,
    NewCategoryComponent,
    StageComponent,
    CandidatureFronteeComponent,
    EvenementUserComponent,
    ParticiperEventComponent,
    EntretienComponent,
    FormModifyCandComponent,
    EntretienEntrepriseComponent,
    ChatComponent,
    BlogsComponent,
    CreatePost1Component,
    ViewAllComponent,
    ViewPostComponent,
    SearchByNameComponent,
    CancelPaymentComponent,
    SuccessPaymentComponent,
    AllFilesComponent,
  ],
  imports: [
    MatDialogModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,  
    ReactiveFormsModule,  
    HttpClientModule, BrowserAnimationsModule, 
    MatSnackBarModule,
    MatStepperModule,
    MatIconModule,
    MatCardModule,
    MatChipsModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatGridListModule,
    LayoutModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
