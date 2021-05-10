import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produit } from '../produit';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  private baseUrl = 'http://localhost:9191/product';

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Produit[]>{
    return this.http.get<Produit[]>(`${this.baseUrl}`);
  }
}
