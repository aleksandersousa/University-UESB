import React from 'react'
import './Styles.css'

import Navigation from '../../components/Navigation'
import Button from '../../components/Button'
import banner from '../../assets/images/banner.png'
import bunette0 from '../../assets/images/bunette0.jpg'
import bunette1 from '../../assets/images/bunette1.jpg'

export default function Home() {
  return (
    <div className="home">
      <div className="page1">
        <img src={banner} alt="banner" className="banner"/>
        <Navigation />
        <p className="textBody">
          <span>Encontre o melhor e</span>
          <span>mais perto de você,</span>
          <span>para te ajudar nos</span>
          <span>serviços do lar.</span>
        </p>
      </div>
      <div className="page2">
        <div className="left">
          <p className="textBody">
            <span>Procure o serviço </span>
            <span>que quiser </span>
            <span>a hora que quiser!</span>
          </p>
          <Button to="/cadastro/cliente" text="Perfil cliente" />
        </div>
        <div className="right">
          <p className="textBody">
            <span>Monte seu perfil </span>
            <span>mostre seu trabalho e </span>
            <span>seja bem avaliada!</span>
          </p>
          <Button to="/cadastro/maid" text="Perfil maid" />
        </div>
      </div>
      <div className="page3">
        <img src={bunette0} alt="bunette0" className="bunette0"/>
        <p className="textBody">
          <span>Confie:</span>
          <span>As mais bem avaliadas,</span>  
          <span>são as de mais confiança,</span>  
          <span>para que sejam bem</span>  
          <span>avaliadas desde o início é</span>  
          <span>necessário um currículo</span>  
          <span>ou portofólio para ser analisado.</span>  
        </p>
      </div>
      <div className="page4">
        <p className="textBody">
          <span>Dica:</span>
          <span>Acompanhe o trabalho para</span>
          <span>saber avaliar sua colaboradora.</span>
        </p>
        <img src={bunette1} alt="bunette1" className="bunette1"/>
      </div>
      <div className="page5">
        <p className="textBody">
          <span>O premium exclusivo para as/os colaboradores,</span>
          <span>serve para ser mais indicado, aparecer destacado nas buscas</span>
          <span>e manter seu engajamento com clientes anteriores.</span>
          <span>Seja um colaborador, Premium</span>
        </p>
        <Button text="Seja premium" />
      </div>
    </div>
  )
}
