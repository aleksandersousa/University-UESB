import React from 'react'
import {Link} from 'react-router-dom'
import './Button.css'

export default function Button(props) {
  const { text, onlyText, to, facebook } = props

  return(
    <Link to={to} id="bt" className={
      onlyText ? "onlyText" : facebook ? "facebook" : "button"
    }>{text}</Link>
  )
}
