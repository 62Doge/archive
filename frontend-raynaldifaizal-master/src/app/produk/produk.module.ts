import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdukRoutingModule } from './produk-routing.module';
import { ListProdukComponent } from './list-produk/list-produk.component';
import { FormProdukComponent } from './form-produk/form-produk.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ListProdukComponent,
    FormProdukComponent
  ],
  imports: [
    CommonModule,
    ProdukRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ]
})
export class ProdukModule { }
