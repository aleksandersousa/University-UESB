import React from 'react'
import { Link } from 'react-router-dom'
import { FaChevronRight } from 'react-icons/fa'
import { FaChevronLeft } from 'react-icons/fa'
import './IconButton.css'

export default function IconButton(props) {
  const { back, to} = props

  return (
    <Link to={to}>
      <div id="icon" className={`iconButton ${back ? "back" : "go"}`}>
        {back ? <FaChevronLeft size={50}/> : <FaChevronRight size={50}/>}
      </div>
    </Link>
  )
}
