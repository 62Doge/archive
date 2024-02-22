import { Component, OnInit } from '@angular/core';
import { ProdusenModel } from '../produsen.model';
import { ProdusenService } from '../produsen.service';

@Component({
  selector: 'app-list-produsen',
  templateUrl: './list-produsen.component.html',
  styleUrls: ['./list-produsen.component.css']
})
export class ListProdusenComponent implements OnInit {

  listProdusen!: ProdusenModel[];

  constructor(private _produsenService: ProdusenService) { }

  ngOnInit(): void {
    this.getListProdusen();
  }

  getListProdusen(){
    this._produsenService.list().subscribe({
      next: response => {
        console.log(response);
        this.listProdusen = response;
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
      this._produsenService.delete(id).subscribe(response => {
        if(response.status === 200){
          alert("Apakah anda ingin menghapus ID" + id);
          console.log(response.body);
          this.getListProdusen();
        }else{
          console.log(response.body);
        } 
      })
    }

}
