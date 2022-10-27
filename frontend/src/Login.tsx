import { Button, PasswordInput, Stack, Text, TextInput, Title } from '@mantine/core'
import axios from 'axios'
import { useAtom, useAtomValue } from 'jotai'
import { Link } from 'react-router-dom'
import { loginAtom } from './atom'
import { paths } from './paths'

export const LoginInput = () => {
  const [login, setLogin] = useAtom(loginAtom)

  return (
    <>
      <TextInput
        placeholder='example@gmail.com'
        label='이메일'
        withAsterisk
        value={login.email}
        onChange={e => setLogin({ ...login, email: e.currentTarget.value })}
      />
      <PasswordInput
        placeholder='****'
        label='비밀번호'
        withAsterisk
        value={login.password}
        onChange={e => setLogin({ ...login, password: e.currentTarget.value })}
      />
    </>
  )
}

export const Login = () => {
  const login = useAtomValue(loginAtom)

  return (
    <Stack>
      <Title>로그인</Title>
      <LoginInput />
      <Button
        onClick={async () => {
          console.log(login)
          try {
            const { data } = await axios.post(
              `${import.meta.env.VITE_ENDPOINT}/user/login`,
              login
            )
            alert(JSON.stringify(data))
          } catch (e) {
            console.log(e)
          }
        }}
      >
        로그인
      </Button>
      <Link to={paths.register}>
        <Text>회원가입</Text>
      </Link>
    </Stack>
  )
}

export const Register = () => {
  const login = useAtomValue(loginAtom)

  return (
    <Stack>
      <Title>회원가입</Title>
      <LoginInput />
      <Button
        onClick={async () => {
          console.log(login)
          try {
            const { data } = await axios.post(
              `${import.meta.env.VITE_ENDPOINT}/user`,
              login
            )
            alert(JSON.stringify(data))
          } catch (e) {
            console.log(e)
          }
        }}
      >
        회원가입
      </Button>
      <Link to={paths.login}>
        <Text>로그인</Text>
      </Link>
    </Stack>
  )
}
