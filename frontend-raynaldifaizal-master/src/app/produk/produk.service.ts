import { Injectable } from '@angular/core';
import { ProdukModel } from './produk.model';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProdukService {

  constructor(private http: HttpClient) { }


  list(){
    return this.http.get<ProdukModel[]>(`${environment.baseUrl}/produk`);
  }

  findId(id: number){
    return this.http.get<ProdukModel>(`${environment.baseUrl}/produk/${id}`);
  }

  create(value: ProdukModel){
    return this.http.post(`${environment.baseUrl}/produk/create`, value, {observe: "response"});
  }

  update(value: ProdukModel){
    return this.http.put(`${environment.baseUrl}/produk/update`, value, {observe: "response"});
  }

  delete(id: number){
    return this.http.delete(`${environment.baseUrl}/produk/delete/${id}`, {observe: "response"});
  }
}
