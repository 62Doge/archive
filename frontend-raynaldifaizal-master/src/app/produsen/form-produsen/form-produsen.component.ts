import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProdusenModel } from '../produsen.model';
import { ProdusenService } from '../produsen.service';

@Component({
  selector: 'app-form-produsen',
  templateUrl: './form-produsen.component.html',
  styleUrls: ['./form-produsen.component.css']
})
export class FormProdusenComponent implements OnInit {

  formProdusen!: FormGroup;
  idProdusen!: number;
  jenisMenu: string = 'Tambah'; 

  constructor(private formBuild: FormBuilder, private _produsenService: ProdusenService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.formProdusen = formBuild.group({
      'nama': new FormControl(null, [Validators.required, Validators.minLength(5)]),
      'kode': new FormControl(null, [Validators.required, Validators.minLength(3)]),
      'alamat': new FormControl(null, [Validators.required])
    })

   }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(rute =>{
      this.idProdusen = rute['id'];
    })

    if(this.idProdusen){  
      this._produsenService.findId(this.idProdusen).subscribe(response => {
        console.log(response);
        this.formProdusen.controls['nama'].setValue(response.nama);
        this.formProdusen.controls['kode'].setValue(response.kode);
        this.formProdusen.controls['alamat'].setValue(response.alamat);
      })

      this.jenisMenu = 'Sunting';
    }

  


  }

  save() {
    let produsen: ProdusenModel = new ProdusenModel();
    produsen = this.formProdusen.value;

    if(this.idProdusen){
      produsen.id = this.idProdusen;
      this._produsenService.update(produsen).subscribe(response => {
        if (response.status === 200) {
          console.log(response.body);
          this.router.navigate(['/', 'produsen'])
        } else {
          alert("Gagal Menyimpan Data");
          console.log(response.body)
        }
      })
    }else {
      this._produsenService.create(produsen).subscribe(response => {
        if (response.status === 200) {
          console.log(response.body);
          this.router.navigate(['/', 'produsen'])
        } else {
          alert("Gagal Menyimpan Data");
          console.log(response.body)
        }
      })
    }


  }


}
