// convention-form.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConventionService {


  private apiUrl = 'http://localhost:9091/api/convention-form';

  constructor(private http: HttpClient) { }

  submitConventionForm(conventionForm: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/submit`, conventionForm);
  }
 // Cette méthode doit récupérer le formulaire de convention pour un étudiant donné
 getConventionForm(cin: number): Observable<any> {
  return this.http.get<any>(`${this.apiUrl}/generate/${cin}`);
}


// Ces méthodes doivent valider ou refuser le formulaire de convention
validerConvention(cin: number): Observable<any> {
  return this.http.put<any>(`${this.apiUrl}/validate/${cin}`, null); // Passer null comme corps
}

rejectConvention(cin: number): Observable<any> {
  return this.http.put<any>(`${this.apiUrl}/reject/${cin}`, null); // Passer null comme corps
}

}

