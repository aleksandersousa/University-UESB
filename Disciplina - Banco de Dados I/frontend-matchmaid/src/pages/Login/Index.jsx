import React from 'react'
import './Styles.css'

import Navigation from '../../components/Navigation'
import Card from '../../components/Card'
import Button from '../../components/Button'
import InputText from '../../components/InputText'

export default function Login() {
  return (
    <div className="login">
      <Navigation />
      <Card>
        <div className="header">
          <span>Já sou cadastrado</span>
          <Button to="/cadastro" text="Fazer cadastro" />
        </div>
        <div className="body">
          <InputText className="email" placeHolder="Email..." />
          <InputText className="senha" placeHolder="Senha..." />
          <Button text="Esqueci minha senha" onlyText={true} />
        </div>
        <div className="footer">
          <Button text="Entrar com o Facebook" facebook={true}/>
          <Button text="Entrar"/>
        </div>
      </Card>
    </div>
  )
}
