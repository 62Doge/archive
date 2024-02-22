import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-validation',
  templateUrl: './validation.component.html',
  styleUrls: ['./validation.component.css']
})
export class ValidationComponent implements OnInit {

  formDataDiri!: FormGroup;

  constructor(private formBuild:FormBuilder) {
    this.formDataDiri = formBuild.group({
      'nama': new FormControl(null, [Validators.required]),
      'umur': new FormControl(null, [Validators.required, Validators.min(18), Validators.max(30)]),
      'notelp': new FormControl(null, [Validators.required, Validators.minLength(12)]),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'alamat': new FormControl(null, [Validators.required, Validators.minLength(10)])
    })

   }

  ngOnInit(): void {
  }

}
