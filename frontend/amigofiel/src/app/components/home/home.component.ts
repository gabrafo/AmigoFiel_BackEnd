import { Component } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  TextAlimento:string = "Ajude-nos a alimentar nossos cães com doações de ração e outros alimentos nutritivos. Aceitamos qualquer tipo de ração seca ou úmida, assim como petiscos e suplementos alimentares para garantir uma dieta balanceada e saudável para todos os nossos cães."

  TextTransport:string= "Contribua com transporte para levar os cães a consultas veterinárias, eventos de adoção, ou até mesmo para buscar doações e suprimentos. Precisamos de veículos confiáveis e motoristas voluntários que possam ajudar a garantir que nossos cães cheguem em segurança aos seus destinos."

  TextJoy:string="Doe brinquedos para manter nossos cães ativos e felizes. Aceitamos brinquedos de borracha, cordas, bolinhas, e qualquer outro item que possa ajudar a entreter e exercitar nossos cães, proporcionando-lhes uma vida mais alegre e saudável enquanto esperam por um lar definitivo."

  TextMoney:string = "Sua contribuição financeira ajuda a cobrir despesas operacionais, cuidados médicos, manutenção de instalações e muito mais. Toda doação é bem-vinda e pode fazer uma grande diferença na vida dos nossos cães, ajudando-nos a continuar nosso trabalho de resgate e cuidado."

  TextHigiene:string = "Mantenha nossos cães limpos e saudáveis com doações de shampoo, condicionadores, e outros produtos de higiene. Produtos para tratamentos de pele, antipulgas e carrapatos, e itens de limpeza também são essenciais para garantir o bem-estar dos nossos cães."

  TextVitaminas :string = "Ajude-nos a garantir que nossos cães recebam as vitaminas e suplementos necessários para uma vida saudável. Suplementos para articulações, pelagem, e saúde geral são extremamente importantes para a manutenção da saúde dos nossos cães, especialmente os mais velhos."

  TextMedication :string = "Contribua com medicações para tratar e prevenir doenças nos cães. Aceitamos medicamentos para vermifugação, tratamento de infecções, controle de parasitas, e quaisquer outras necessidades veterinárias. Sua doação pode ajudar a salvar vidas e melhorar a saúde de muitos cães."

  TextColeiras :string = "Doe coleiras e guias para os passeios dos nossos cães. Esses itens são essenciais para a segurança e bem-estar dos nossos cães durante caminhadas e atividades ao ar livre. Aceitamos todos os tipos e tamanhos, desde que estejam em boas condições."

  TextCasinhas :string = "Ajude a proporcionar um lugar seguro e confortável para nossos cães dormirem. Aceitamos casinhas, cobertores, camas e outros itens que possam ajudar a criar um ambiente acolhedor para os cães enquanto estão sob nossos cuidados."

  showInfo(title: string, text: string) {
    Swal.fire({
      title: title,
      text: text,
      icon: 'info',
      confirmButtonColor: '#003366'
    });
  }
}
