import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
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

  form: FormGroup;

  materiasData: IMateria[] = []
  bancasData: IBanca[] = []
  loadingBancas: boolean = true
  loadingMaterias: boolean = true

  get bancasFormArray() {
    return this.form.controls['bancas'] as FormArray;
  }
  get materiasFormArray() {
    return this.form.controls['materias'] as FormArray;
  }

  constructor(private bancaService: BancasService, private materiaService: MateriasService, private simuladoService: SimuladoService, private formBuilder: FormBuilder, private toastr: ToastrService, private router: Router) {
    this.form = this.formBuilder.group(
      {
        bancas: new FormArray([], [minSelectedCheckboxes(1)]),
        materias: new FormArray([], [minSelectedCheckboxes(1)]),
        numPerguntas: new FormControl(10, [Validators.min(5), Validators.max(90), Validators.required])
      }
    )
  }

  public isLoading() {
    return !this.loadingBancas && !this.loadingMaterias
  }

  private addCheckboxesBanca() {
    this.bancasData.forEach(() => this.bancasFormArray.push(new FormControl(false)));
  }
  private addCheckboxesMateria() {
    this.materiasData.forEach(() => this.materiasFormArray.push(new FormControl(false)));
  }

  private getBancasSelecionadas() {

    return this.form.value.bancas
      .map((v: boolean, i: number) => v ? this.bancasData[i] : null)
      .filter((v: IBanca | null) => v !== null)
  }

  private getMateriasSelecionadas() {
    return this.form.value.materias
      .map((v: boolean, i: number) => v ? this.materiasData[i] : null)
      .filter((v: IMateria | null) => v !== null)
  }

  consultarBancas() {
    this.bancaService.consultar().subscribe(
      {
        next: bancas => {
          this.bancasData = bancas
          this.addCheckboxesBanca()
        },
        error: error => {
          console.log(error)
          if (error.status != 401) {
            this.toastr.error("Não foi possível consultar as bancas.", "Erro")
            this.router.navigate(['app', 'home'])
          }
        }
      }).add(() => this.loadingBancas = false)

  }

  consultarMaterias() {
    this.materiaService.consultar().subscribe({
      next: materias => {
        this.materiasData = materias
        this.addCheckboxesMateria()
      },
      error: error => {
        console.log(error)
        if (error.status != 401) {

          this.toastr.error("Não foi possível consultar as matérias.", "Erro")
          this.router.navigate(['app', 'home'])
        }
      }
    }).add(() => this.loadingMaterias = false)
  }

  ngOnInit(): void {
    this.consultarBancas()
    this.consultarMaterias()
  }

  enviar(): void {
    this.simuladoService.gerar({ numeroPerguntas: this.form.value.numPerguntas, materias: this.getMateriasSelecionadas(), bancas: this.getBancasSelecionadas() })
      .subscribe(
        {
          next: (gerado) => {
            this.toastr.success("Simulado gerado com sucesso!")
            this.router.navigate(['app', 'simulados', 'realizar', gerado.id])
          }
          ,
          error: (erro) => {
            this.toastr.error(erro.error.message)
          }
        }
      )
  }

  bancaSelected: any[] = []
  verificaMaterias(idBanca: any) {
    this.loadingMaterias = true
    this.materiasFormArray.clear()
    let index = this.bancaSelected.indexOf(idBanca)
    if (index == -1)
      this.bancaSelected.push(idBanca)
    else
      this.bancaSelected.splice(index, 1)

    if (this.bancaSelected.length == 0)
      this.consultarMaterias()
    else
      this.getMaterias(this.bancaSelected)
  }

  getMaterias(idBancas: any[]) {
    this.materiaService.consultarPorBanca(idBancas).subscribe({
      next: (materias: IMateria[]) => {
        this.materiasData = materias
        this.addCheckboxesMateria()
      },
      error: (error: { status: number; }) => {
        console.log(error)
        if (error.status != 401) {

          this.toastr.error("Não foi possível consultar as matérias.", "Erro")
        }
      }
    }).add(() => this.loadingMaterias = false)
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
