import { Component, OnInit } from '@angular/core';
import { ProdukModel } from '../produk.model';
import { ProdukService } from '../produk.service';

@Component({
  selector: 'app-list-produk',
  templateUrl: './list-produk.component.html',
  styleUrls: ['./list-produk.component.css']
})
export class ListProdukComponent implements OnInit {


  listProduk!: ProdukModel[];

  constructor(private _produkService: ProdukService) { }

  ngOnInit(): void {
    this.getListProduk();
  }

  getListProduk(){
    this._produkService.list().subscribe({
      next: response => {
        console.log(response);
        this.listProduk = response;
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        console.log("Service telah dijalankan");
      }
    })
  }

  delete(id: number){
    this._produkService.delete(id).subscribe(response => {
      if(response.status === 200){
        alert("Apakah anda ingin menghapus ID" + id);
        console.log(response.body);
        this.getListProduk();
      }else{
        console.log(response.body);
      } 
    })
  }

}
