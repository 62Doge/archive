import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ParentComponent } from './communication/parent/parent.component';
import { CountComponent } from './count/count.component';
import { ProdukModule } from './produk/produk.module';
import { ProdusenModule } from './produsen/produsen.module';
import { ProfileComponent } from './profile/profile.component';
import { ValidationComponent } from './validation/validation.component';

const routes: Routes = [{

  path: 'profile',
  component: ProfileComponent
},
{
  path: 'count',
  component: CountComponent
},
{
  path: 'validation',
  component: ValidationComponent
},
{
  path: 'communication',
  component: ParentComponent
},
{
  path: 'produsen',
  loadChildren: () => ProdusenModule
},
{
  path: 'produk',
  loadChildren: () => ProdukModule
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
