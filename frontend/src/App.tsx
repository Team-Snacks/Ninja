import { Grid, NavLink } from '@mantine/core'
import 'react-grid-layout/css/styles.css'
import 'react-resizable/css/styles.css'
import {
  BrowserRouter,
  Link,
  Route,
  Routes,
  useLocation,
} from 'react-router-dom'
import { CreateAccount, Login } from './Login'
import { WidgetGrid } from './WidgetGrid'

export const Root = () => {
  const location = useLocation()

  return (
    <>
      <NavLink
        label="로그인"
        component={Link}
        to="/user/login"
        active={location.pathname === '/user/login'}
      />
      <NavLink
        label="회원가입"
        component={Link}
        to="/user/login"
        active={location.pathname === '/user'}
      />
      <WidgetGrid />
    </>
  )
}

export const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Root />} />
        <Route path="/user" element={<CreateAccount />} />
        <Route path="/user/login" element={<Login />} />
      </Routes>
    </BrowserRouter>
  )
}
