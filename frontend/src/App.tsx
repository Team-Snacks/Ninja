import { AppShell, Navbar, Header, Grid, NavLink, Title } from '@mantine/core'
import 'react-grid-layout/css/styles.css'
import 'react-resizable/css/styles.css'
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import { Register, Login } from './Login'
import { paths } from './paths'
import { WidgetGrid } from './WidgetGrid'

export const Router = () => {
  return (
    <Routes>
      <Route path={paths.home} element={<WidgetGrid />} />
      <Route path={paths.register} element={<Register />} />
      <Route path={paths.login} element={<Login />} />
    </Routes>
  )
}

export const App = () => {
  return (
    <BrowserRouter>
      <AppShell
        padding="md"
        navbar={
          <Navbar width={{ base: 300 }} height={500} p="xs">
            <Link to={paths.home}>홈</Link>
            <Link to={paths.register}>회원가입</Link>
            <Link to={paths.login}>로그인</Link>
          </Navbar>
        }
        header={
          <Header height={60} p="xs">
            <Title>🍪 Snacks (Ninja)</Title>
          </Header>
        }
      >
        <Router />
      </AppShell>
    </BrowserRouter>
  )
}
