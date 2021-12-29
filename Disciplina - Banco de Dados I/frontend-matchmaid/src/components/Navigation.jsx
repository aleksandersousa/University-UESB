import React from 'react'
import {Link} from 'react-router-dom'
import './Navigation.css'

import Button from './Button'
import logo from '../assets/images/logo.png'

export default function Navigation() {
  return (
    <div className="navigation">
      <div className="container">  
        <Link to="/" id="img"><img src={logo} alt="matchMaid logo" className="logo"/></Link>
        <Button to="/cadastro/maid" text="Seja uma Maid"/>
        <Button text="Buscar" onlyText={true}/>
        <Button to="/login" text="Entrar" onlyText={true}/>
      </div>
    </div>
  )
}
