import React from 'react'
import { NavLink } from 'react-router-dom'
import './NavButton.css'

export default function NavButton(props) {
  const {text, to} = props

  return(
    <NavLink to={to} className="navButton" activeClassName="active">{text}</NavLink>
  )
}
