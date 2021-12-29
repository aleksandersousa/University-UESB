import React from 'react'
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import './Styles.css'

import Card from '../../components/Card'
import Button from '../../components/Button'
import NavButton from '../../components/NavButton'
import IconButton from '../../components/IconButton'
import InputText from '../../components/InputText'

export default function RegisterClient() {
  return (
    <div className="registerClient">
      <span className="title">Cadastro Cliente</span>
      <Card>
        <BrowserRouter>
          <Switch>
            <Route path="/cadastro/cliente/2">
            <div className="header">
                <NavButton to="/cadastro/cliente/1" text="1" className="active" />
                <NavButton to="/cadastro/cliente/2" text="2" />
              </div>
              <div className="body">
                <InputText className="street" placeHolder="Rua" />
                <InputText className="neighborhood" placeHolder="Bairro" />
                <div className="address">
                  <InputText className="number" placeHolder="Número" />
                  <InputText className="address2" placeHolder="Complemento" />
                </div>
              </div>
              <div className="footer">
                <IconButton back={true} to="/cadastro/cliente/1" />
                <Button to="/cadastro/cliente/2" text="Finalizar" onlyText={true}/>
              </div>
            </Route>
            <Route path="/cadastro/cliente">
              <div className="header">
                <NavButton to="/cadastro/cliente/1" text="1" />
                <NavButton to="/cadastro/cliente/2" text="2" className="active" />
              </div>
              <div className="body">
                <InputText className="email" placeHolder="Email" />
                <div className="nameSurname">
                  <InputText className="name" placeHolder="Nome" />
                  <InputText className="surname" placeHolder="Sobrenome" />
                </div>
                <InputText className="password" placeHolder="Senha" />
                <InputText className="confirmPassword" placeHolder="Confirmar Senha" />
              </div>
              <div className="footer">
                <section></section>
                <IconButton to="/cadastro/cliente/2" />
              </div>
            </Route>
          </Switch>
        </BrowserRouter>
      </Card>
    </div>
  )
}
