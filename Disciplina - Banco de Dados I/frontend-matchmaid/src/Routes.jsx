import React from 'react'
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom'

import Home from './pages/Home/Index'
import Login from './pages/Login/Index'
import Register from './pages/Register/Index'
import RegisterClient from './pages/RegisterClient/Index'
import RegisterMaid from './pages/RegisterMaid/Index'

export default function Routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={Home}/>
        <Route exact path="/login" component={Login} />
        <Route exact path="/cadastro" component={Register} />
        <Route exact path="/cadastro/cliente" component={RegisterClient} />
        <Route exact path="/cadastro/maid" component={RegisterMaid} />
        <Redirect from='*' to='/' />
      </Switch>
    </BrowserRouter>  
  )
}
