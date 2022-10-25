import { Button, Stack, Text, TextInput, Title } from '@mantine/core'
import { Link } from 'react-router-dom'
import { paths } from './paths'

export const Login = () => {
  return (
    <Stack>
      <Title>로그인</Title>
      <TextInput placeholder='example@gmail.com' label='이메일' withAsterisk />
      <TextInput placeholder='****' label='비밀번호' withAsterisk />
      <Button>로그인</Button>
      <Link to={paths.register}>
        <Text>회원가입</Text>
      </Link>
    </Stack>
  )
}

export const Register = () => {
  return (
    <Stack>
      <Title>회원가입</Title>
      <TextInput placeholder='example@gmail.com' label='이메일' withAsterisk />
      <TextInput placeholder='****' label='비밀번호' withAsterisk />
      <Button>회원가입</Button>
      <Link to={paths.login}>
        <Text>로그인</Text>
      </Link>
    </Stack>
  )
}
