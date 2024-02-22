import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProdusenModel } from 'src/app/produsen/produsen.model';
import { ProdusenService } from 'src/app/produsen/produsen.service';
import { ProdukModel } from '../produk.model';
import { ProdukService } from '../produk.service';



@Component({
  selector: 'app-form-produk',
  templateUrl: './form-produk.component.html',
  styleUrls: ['./form-produk.component.css']
})
export class FormProdukComponent implements OnInit {

  formProduk!: FormGroup;
  idProduk!: number;
  jenisMenu: string = 'Tambah';
  listProdusen!: ProdusenModel[];

  constructor(private formBuild: FormBuilder, private _produkService: ProdukService, private _produsenService: ProdusenService , private router: Router, private activatedRoute: ActivatedRoute) { 
    this.formProduk = formBuild.group({
      'nama': new FormControl(null, [Validators.required, Validators.minLength(5)]),
      'jenis': new FormControl(null, [Validators.required, Validators.minLength(5)]),
      'harga': new FormControl(null, [Validators.required]),
      'berat': new FormControl(null, [Validators.required]),
      'produsen_id': new FormControl(null, [Validators.required])
    })
  }

  ngOnInit(): void {
    this.getListProdusen();
    this.activatedRoute.params.subscribe(rute =>{
      this.idProduk = rute['id'];
    })

    if(this.idProduk){  
      this._produkService.findId(this.idProduk).subscribe(response => {
        console.log(response);
        this.formProduk.controls['nama'].setValue(response.nama);
        this.formProduk.controls['jenis'].setValue(response.jenis);
        this.formProduk.controls['harga'].setValue(response.harga);
        this.formProduk.controls['berat'].setValue(response.berat);
        this.formProduk.controls['produsen_id'].setValue(response.produsen);
      })

      this.jenisMenu = 'Sunting';
    }
  }

  save() {
    let produk: ProdukModel = new ProdukModel();
    produk = this.formProduk.value;

    if(this.idProduk){
      produk.id = this.idProduk;
      this._produkService.update(produk).subscribe(response => {
        if (response.status === 200) {
          console.log(response.body);
          this.router.navigate(['/', 'produk'])
        } else {
          alert("Gagal Menyimpan Data");
          console.log(response.body)
        }
      })
    }else {
      this._produkService.create(produk).subscribe(response => {
        if (response.status === 200) {
          console.log(response.body);
          this.router.navigate(['/', 'produk'])
        } else {
          alert("Gagal Menyimpan Data");
          console.log(response.body)
        }
      })
    }
  }

  getListProdusen() {
    this._produsenService.list().subscribe({
      next: value => {
        console.log(value);
        this.listProdusen = value;
      },
      error: err => {
        console.log(err)
      },
      complete: () => {
        console.log("Service Telah Dijalankan")
      }
    });
  }


}
