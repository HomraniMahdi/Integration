import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { EntrepriseComponent } from './pages/entreprise/entreprise.component'; 
import { EvenementComponent } from './pages/evenement/evenement.component'; 
import { CommunicationComponent } from './pages/communication/communication.component'; 
import { CandidatureComponent } from './pages/candidature/candidature.component'; 
import { UserprofilComponent } from './pages/userprofil/userprofil.component';
import { EtudiantListComponent } from './pages/etudiant/etudiant-list/etudiant-list.component';
import { EtudiantNewComponent } from './pages/etudiant/etudiant-new/etudiant-new.component';
import { EtudiantUpdateComponent } from './pages/etudiant/etudiant-update/etudiant-update.component';
import { TimelineComponent } from './pages/timeline-component/timeline-component.component';
import { ConventionComponent } from './pages/convention/convention.component';
import { ConventionFormComponent } from './pages/convention-form/convention-form.component';
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
import { LayoutComponent } from './feature/layout/layout/layout.component';
import { CancelPaymentComponent } from './pages/cancel-payment/cancel-payment.component';
import { SuccessPaymentComponent } from './pages/success-payment/success-payment.component';

const routes: Routes = [
  {path:'dashboard', component: DashboardComponent },
  {path:'entreprise', component:EntrepriseComponent},
  {path:'evenement', component:EvenementComponent},
  {path:'communication',component:CommunicationComponent},
  {path:'userprofil',component:UserprofilComponent},
  {path:'etudiant', component:EtudiantListComponent },
  {path: '', redirectTo: 'view-etudiant', pathMatch: 'full' }, 
  {path:'etudiant-new', component: EtudiantNewComponent },
  {path:'etudiant-update/:id', component: EtudiantUpdateComponent },
  {path: 'timeline', component: TimelineComponent },
  {path:'convention', component: ConventionComponent},
  {path:'convention-form', component:ConventionFormComponent},
  {path: 'convention-form/:cin', component: ConventionFormComponent },
  {path:'category', component: CategoryListComponent },
  {path: '', redirectTo: 'view-category', pathMatch: 'full' }, 
  {path:'new-category', component: NewCategoryComponent },
  {path:'stage',component:StageComponent},
  {path:'candidatureFront',component:CandidatureFronteeComponent},
  {path:'candidature',component:CandidatureComponent},
  {path:'EvenementUser',component:EvenementUserComponent },
  {path:'PartciperEvent',component:ParticiperEventComponent },
  {path: 'userprofil', component: UserprofilComponent },
  {path: 'entretien' , component:EntretienComponent},
  {path: 'entretienEntreprise' , component:EntretienEntrepriseComponent},
  {path: 'modify-cand/:id', component: FormModifyCandComponent },
  {path: 'chat/:userId', component: ChatComponent},
  {path:'Blogs',component:BlogsComponent},
  {path:'create-post1',component:CreatePost1Component},
  {path:'view-all',component:ViewAllComponent},
  {path:'view-post/:id',component:ViewPostComponent},
  {path:'search-by-name',component:SearchByNameComponent},
  {path:'', loadChildren:()=> import('./feature/authentication/authentication.module').then(m => m.AuthenticationModule)},
  {path:'home', component: LayoutComponent, children: [
      {path:'dashboard',loadChildren:()=>import('./feature/dashboard/dashboard.module').then(m=>m.DashboardModule)},
      {path:'account',loadChildren:()=>import('./feature/accountmanagement/accountmanagement.module').then(m=>m.AccountmanagementModule)},
    ]},
  { path: 'cancel', component: CancelPaymentComponent },
  { path: 'success', component: SuccessPaymentComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],

})
export class AppRoutingModule { }