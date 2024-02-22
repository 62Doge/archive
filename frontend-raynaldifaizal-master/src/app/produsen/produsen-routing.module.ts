import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormProdusenComponent } from './form-produsen/form-produsen.component';
import { ListProdusenComponent } from './list-produsen/list-produsen.component';

const routes: Routes = [{

  path: '',
  component: ListProdusenComponent
},
{
  path: 'tambah',
  component: FormProdusenComponent
},
{
  path: 'sunting/:id',
  component: FormProdusenComponent
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProdusenRoutingModule { }
