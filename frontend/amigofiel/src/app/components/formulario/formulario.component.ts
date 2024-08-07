import { Component } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrl: './formulario.component.css'
})
export class FormularioComponent {
  nome: string = '';
  idade: number | null = null;
  endereco: string = '';
  telefone: string = '';
  email: string = '';

  tipoResidencia: string = '';
  residenciaPropria: string = '';
  permissaoAnimais: string = '';
  espacoDisponivel: string = '';
  telasProtecao: string = '';

  numeroPessoas: number | null = null;
  concordancia: string = '';
  alergias: string = '';

  jaTeveAnimais: string = '';
  possuiOutrosAnimais: string = '';

  motivoAdocao: string = '';
  tempoSozinho: string = '';

  visitaRepresentante: string = '';
  termoResponsabilidade: string = '';

  declaracao: string = '';
  termosConcordancia: string = '';

  DadosAdote = {
    nome: this.nome,
    idade: this.idade,
    endereco: this.endereco,
    telefone: this.telefone,
    email: this.email,
    tipoResidencia: this.tipoResidencia,
    residenciaPropria: this.residenciaPropria,
    permissaoAnimais: this.permissaoAnimais,
    espacoDisponivel: this.espacoDisponivel,
    telasProtecao: this.telasProtecao,
    numeroPessoas: this.numeroPessoas,
    concordancia: this.concordancia,
    alergias: this.alergias,
    jaTeveAnimais: this.jaTeveAnimais,
    possuiOutrosAnimais: this.possuiOutrosAnimais,
    motivoAdocao: this.motivoAdocao,
    tempoSozinho: this.tempoSozinho,
    visitaRepresentante: this.visitaRepresentante,
    termoResponsabilidade: this.termoResponsabilidade,
    declaracao: this.declaracao,
    termosConcordancia: this.termosConcordancia
  };

  EnviarForm() {
    if (
      this.nome === '' ||
      this.idade === null ||
      this.endereco === '' ||
      this.telefone === '' ||
      this.email === '' ||
      this.tipoResidencia === '' ||
      this.residenciaPropria === '' ||
      this.permissaoAnimais === '' ||
      this.espacoDisponivel === '' ||
      this.telasProtecao === '' ||
      this.numeroPessoas === null ||
      this.concordancia === '' ||
      this.alergias === '' ||
      this.jaTeveAnimais === '' ||
      this.possuiOutrosAnimais === '' ||
      this.motivoAdocao === '' ||
      this.tempoSozinho === '' ||
      this.visitaRepresentante === '' ||
      this.termoResponsabilidade === '' ||
      this.declaracao === '' ||
      this.termosConcordancia === ''
    ) {
      Swal.fire({
        icon: 'error',
        title: 'Erro!',
        text: 'Por favor, preencha todos os campos.',
      });
    } else {
      Swal.fire({
        icon: 'success',
        title: 'Formulário enviado com sucesso!',
        text: 'Estamos felizes que você decidiu adotar um novo amigo, vamos analisar seu perfil e em breve entraremos em contato!',
      });
      this.DadosAdote = {
        nome: this.nome,
        idade: this.idade,
        endereco: this.endereco,
        telefone: this.telefone,
        email: this.email,
        tipoResidencia: this.tipoResidencia,
        residenciaPropria: this.residenciaPropria,
        permissaoAnimais: this.permissaoAnimais,
        espacoDisponivel: this.espacoDisponivel,
        telasProtecao: this.telasProtecao,
        numeroPessoas: this.numeroPessoas,
        concordancia: this.concordancia,
        alergias: this.alergias,
        jaTeveAnimais: this.jaTeveAnimais,
        possuiOutrosAnimais: this.possuiOutrosAnimais,
        motivoAdocao: this.motivoAdocao,
        tempoSozinho: this.tempoSozinho,
        visitaRepresentante: this.visitaRepresentante,
        termoResponsabilidade: this.termoResponsabilidade,
        declaracao: this.declaracao,
        termosConcordancia: this.termosConcordancia
      };
      localStorage.setItem('FormAdotante', JSON.stringify(this.DadosAdote))
    }
  }

}
