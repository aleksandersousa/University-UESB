import React from 'react'
import './Card.css'

export default function Card(props) {
  const{ children } = props
  return(
    <div id="cardId" className="card">{children}</div>
  )
}
