import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import IGeneric from 'src/app/interfaces/IGeneric';

@Component({
  selector: 'app-checbox-group',
  templateUrl: './checbox-group.component.html',
  styleUrls: ['./checbox-group.component.css']
})
export class ChecboxGroupComponent {
  @Input() data : IGeneric[] = []

  @Output() checkChangeEvent = new EventEmitter<number[]>();



  form: FormGroup;
  constructor(private formBuilder: FormBuilder){
    this.form = this.formBuilder.group(
      {
        itens: new FormArray([]),
      }
    )
  }

  ngOnChanges(changes: SimpleChanges){
    if(changes['data'].previousValue != changes['data'].currentValue){
      this.addCheckboxesItens()
    }
  }

  get itensFormArray(){
    return this.form.controls['itens'] as FormArray;
  }

  private addCheckboxesItens() {
    this.data.forEach(() => this.itensFormArray.push(new FormControl(false)));
  }


  private getSelectedItens() {
    return this.form.value.itens
      .map((v: boolean, i: number) => v ? this.data[i] : null)
      .filter((v: IGeneric| null) => v !== null)
      .map((v:IGeneric)=>v.id)
  }

  emitChange(){
    this.checkChangeEvent.emit(this.getSelectedItens())
  }

}
