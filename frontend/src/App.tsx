import { AppShell, Navbar, Header, Title, Button, Group } from '@mantine/core'
import axios from 'axios'
import { useAtom, useAtomValue } from 'jotai'
import 'react-grid-layout/css/styles.css'
import 'react-resizable/css/styles.css'
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import {
  fromDTO,
  Layout,
  layoutAtom,
  LayoutDTO,
  loginAtom,
  toDTO,
} from './atom'
import { Login, Register } from './Login'
import Market from './Market'
import { paths } from './paths'
import { WidgetGrid } from './WidgetGrid'

export const Router = () => {
  return (
    <Routes>
      <Route path={paths.home} element={<Home />} />
      <Route path={paths.register} element={<Register />} />
      <Route path={paths.login} element={<Login />} />
    </Routes>
  )
}

export const Home = () => {
  const [layout, setlayout] = useAtom(layoutAtom)
  const { email } = useAtomValue(loginAtom)

  return (
    <>
      <Group>
        <Button
          onClick={async () => {
            const widget = toDTO(layout)
            console.log(JSON.stringify(widget))
            try {
              const { data } = await axios.patch(
                `${import.meta.env.VITE_ENDPOINT}/user/${email}/widgets`,
                widget
              )
              alert(JSON.stringify(data))
            } catch (e) {
              console.log(e)
            }
          }}
        >
          저장하기
        </Button>
        <Button
          onClick={async () => {
            try {
              const { data } = await axios.get<{ dataList: LayoutDTO }>(
                `${import.meta.env.VITE_ENDPOINT}/user/${email}/widgets`
              )
              console.log(data)
              console.log(`불러오기: ${JSON.stringify(data.dataList)}`)
              setlayout(fromDTO(data.dataList))
            } catch (e) {
              console.log(e)
            }
          }}
        >
          불러오기
        </Button>
      </Group>
      <WidgetGrid />
      <Market />
    </>
  )
}
export const App = () => {
  return (
    <BrowserRouter>
      <AppShell
        padding='md'
        navbar={
          <Navbar width={{ base: 300 }} height={500} p='xs'>
            <Link to={paths.home}>홈</Link>
            <Link to={paths.register}>회원가입</Link>
            <Link to={paths.login}>로그인</Link>
          </Navbar>
        }
        header={
          <Header height={60} p='xs'>
            <Title>🍪 Snacks (Ninja)</Title>
          </Header>
        }
      >
        <Router />
      </AppShell>
    </BrowserRouter>
  )
}
