import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EntretienModel } from '../Models/EntretienModel';


@Injectable({
  providedIn: 'root'
})
export class EntretienService {
  private baseUrl = 'http://localhost:9091';

  constructor(private http: HttpClient) { }

  affecterEntretienToCandidature(idCandidature: number, entretien: EntretienModel): Observable<EntretienModel> {
    return this.http.post<EntretienModel>(`${this.baseUrl}/affEntretien/${idCandidature}`, entretien);
  }

  getAllEntretiens(): Observable<EntretienModel[]> {
    return this.http.get<EntretienModel[]>(`${this.baseUrl}/getAllEntre`);
  }

  deleteEntretienById(idEntretien: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deleteEntre/${idEntretien}`);
  }

  sendEmail(entretien: EntretienModel): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/sendEmail`, entretien);
  }
}
