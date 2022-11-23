import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray,FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import IBanca from 'src/app/interfaces/IBanca';
import IMateria from 'src/app/interfaces/IMateria';
import { BancasService } from 'src/app/services/bancas.service';
import { MateriasService } from 'src/app/services/materias.service';
import { SimuladoService } from 'src/app/services/simulado.service';

@Component({
  selector: 'app-gerar-simulado',
  templateUrl: './gerar-simulado.component.html',
  styleUrls: ['./gerar-simulado.component.css']
})
export class GerarSimuladoComponent implements OnInit {

  form:FormGroup;


  materiasData: IMateria[] = []
  bancasData: IBanca[]= []

  get bancasFormArray(){
    return this.form.controls['bancas'] as FormArray;
  }
  get materiasFormArray(){
    return this.form.controls['materias'] as FormArray;
  }

  constructor(private bancaService: BancasService,private materiaService:MateriasService,private simuladoService:SimuladoService,private formBuilder:FormBuilder){
    this.form = this.formBuilder.group(
      {
        bancas:new FormArray([],[ minSelectedCheckboxes(1)]),
        materias: new FormArray([],[ minSelectedCheckboxes(1)]),
        numPerguntas:new FormControl(10,[Validators.min(5),Validators.max(90),Validators.required])
      }
    )
  }

  private addCheckboxesBanca(){
    this.bancasData.forEach(()=>this.bancasFormArray.push(new FormControl(false)));
  }
  private addCheckboxesMateria(){
    this.materiasData.forEach(()=>this.materiasFormArray.push(new FormControl(false)));
  }

  private getBancasSelecionadas(){

    return this.form.value.bancas
          .map((v :boolean,i:number)=> v? this.bancasData[i]:null)
          .filter((v : IBanca | null)  => v !==null)
  }

  private getMateriasSelecionadas(){
    return this.form.value.materias
          .map((v :boolean,i:number)=> v? this.materiasData[i]:null)
          .filter((v : IMateria | null)  => v !==null)
  }


  ngOnInit(): void {


    this.bancaService.consultar().subscribe(
      {
      next:(bancas)=>{
        this.bancasData=bancas
        this.addCheckboxesBanca()

      },
      error:(error)=>console.log(error)
      }
    )
    this.materiaService.consultar().subscribe({
      next:(materias)=>{
        this.materiasData=materias
        this.addCheckboxesMateria()

      },
      error:(error)=>console.log(error)
    })
  }

  enviar():void{
    console.log(this.getBancasSelecionadas())
    console.log(this.getMateriasSelecionadas())
  }



}



function minSelectedCheckboxes(min = 1) {
  const validator: ValidatorFn = (formArray: AbstractControl) => {
    if (formArray instanceof FormArray) {
      const totalSelected = formArray.controls
        .map((control) => control.value)
        .reduce((prev, next) => (next ? prev + next : prev), 0);
      return totalSelected >= min ? null : { required: true };
    }

    throw new Error('formArray is not an instance of FormArray');
  };

  return validator;
}
