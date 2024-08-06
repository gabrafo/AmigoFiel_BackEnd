import { Component } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-contato',
  templateUrl: './contato.component.html',
  styleUrl: './contato.component.css'
})
export class ContatoComponent {

  NomeInput:string = ""
  EmailInput:string = ""
  NumInput = ""
  MsmInput:string = ""

  DadosContato = {
    nome:this.NomeInput,
    email:this.EmailInput,
    numero:this.NumInput,
    mensagem:this.MsmInput,
  }

  EnviarMsm(){
    if(this.NomeInput == "" || this.EmailInput == "" || this.NumInput == "" || this.MsmInput == ""){
      Swal.fire({
        title: 'Erro',
        text: 'Preencha todo o formulário antes de enviar',
        icon: 'error',
        confirmButtonColor: '#003366'
      });
    }else{
      this.DadosContato.nome = this.NomeInput
      this.DadosContato.email = this.EmailInput
      this.DadosContato.numero= this.NumInput
      this.DadosContato.mensagem = this.MsmInput
      localStorage.setItem('ContatoUser', JSON.stringify(this.DadosContato))
      Swal.fire({
        title: 'Sucesso!',
        text: 'Mensagem enviada com sucesso. Em breve entraremos em contato.',
        icon: 'success',
        confirmButtonColor: '#003366'
      });
    }
  }
}
