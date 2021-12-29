import React from 'react'
import './InputText.css'

export default function InputText(props) {
  const { className, placeHolder } = props

  return (
    <input 
      type="text" 
      className={`inputText ${className}`} 
      placeHolder={placeHolder} 
    />
  )
}
