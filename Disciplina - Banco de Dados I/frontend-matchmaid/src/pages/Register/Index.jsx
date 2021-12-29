import React from 'react'
import './Styles.css'

import Navigation from '../../components/Navigation'
import Card from '../../components/Card'
import Button from '../../components/Button'
import blonde from '../../assets/images/blonde.jpg'
import maid from '../../assets/images/maid.jpg'

export default function Register() {
  return (
    <div className="register">
      <Navigation />
      <span className="title">Escolha Seu Perfil</span>
      <div className="body">
        <div className="marginLeft"></div>
        <Card>
          <img src={blonde} alt="blonde" className="blonde" />
          <Button to="/cadastro/cliente" text="Cliente"/>
        </Card>
        <Card>
          <img src={maid} alt="blonde" className="maid" />
          <Button to="/cadastro/maid" text="Maid"/>
        </Card>
        <div className="marginRight"></div>
      </div>
    </div>
  )
}
