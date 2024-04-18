// etudiant.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Etudiant } from '../Models/etudiant';


@Injectable({
  providedIn: 'root'
})
export class EtudiantService {
  private baseUrl = 'http://localhost:9091/api/etudiants';

  constructor(private http: HttpClient) {}

  getEtudiantList(): Observable<Etudiant[]> {
    return this.http.get<Etudiant[]>(`${this.baseUrl}/all`);
  }

  createEtudiant(etudiant: Etudiant): Observable<Etudiant> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Etudiant>(`${this.baseUrl}/etudiant`, etudiant, { headers }).pipe(
      catchError((error: any) => {
        console.error('Error occurred:', error);
        return throwError(error); // re-throw the error
      })
    );
  }

  deleteEtudiant(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  getEtudiant(id: number): Observable<Etudiant> {
    return this.http.get<Etudiant>(`${this.baseUrl}/${id}`);
  }

  updateEtudiant(id: number, etudiant: Etudiant): Observable<Etudiant> {
    return this.http.put<Etudiant>(`${this.baseUrl}/update/${id}`, etudiant);
  }
  updateDemandeStage(id: number): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/updateDemandeStage/${id}`, null);
  }

  
}
