import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Entretien } from '../Models/entretien.model';



@Injectable({
  providedIn: 'root'
})
export class EntretientEntrepriseService {
  private apiUrl = 'http://localhost:9091/entretienEntreprise'; // URL de l'API backend

  constructor(private http: HttpClient) {}

  getAllEntretiens(): Observable<Entretien[]> {
    return this.http.get<Entretien[]>(`${this.apiUrl}`);
  }

  addEntretien(entretien: Entretien): Observable<Entretien> {
    return this.http.post<Entretien>(`${this.apiUrl}`, entretien);
  }

  updateEntretien(id: number, entretien: Entretien): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}`, entretien);
  }

  deleteEntretien(idEntretien: number): Observable<void> {
    const url = `${this.apiUrl}/${idEntretien}`;
    return this.http.delete<void>(url);

  }
}
