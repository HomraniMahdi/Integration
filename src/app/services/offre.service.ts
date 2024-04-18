import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject, tap } from 'rxjs';
import { Offre } from '../Models/offre.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OffreService {
  host = environment.host;
  
  getOffreById(id: number): Observable<Offre> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Offre>(url);
  }
  private apiUrl = 'http://localhost:9091/offres';
  private notificationsSubject = new Subject<void>();

  constructor(private http: HttpClient) { }

  getAllOffres(): Observable<Offre[]> {
    return this.http.get<Offre[]>(this.apiUrl);
  }

  addOffre(offre: Offre): Observable<Offre> {
    return this.http.post<Offre>(this.host, offre).pipe(
      tap(() => {
        this.notificationsSubject.next(); // Émettre un événement pour indiquer qu'une offre a été ajoutée
      })
    );
  }

  updateOffre(id: number, offre: Offre): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put<void>(url, offre);
  }
  deleteOffre(idOffre: number): Observable<void> { 
    const url = `${this.apiUrl}/${idOffre}`;
    return this.http.delete<void>(url);
    
  }
  searchOffres(searchText: string): Observable<Offre[]> {
    const url = `${this.apiUrl}?search=${searchText}`; // ajustez l'URL de recherche selon votre backend
    return this.http.get<Offre[]>(url);
  }
}
