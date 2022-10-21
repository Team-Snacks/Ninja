import { Button, Stack, TextInput, Title } from '@mantine/core'

export const Login = () => {
  return (
    <Stack>
      <Title>로그인</Title>
      <TextInput placeholder="example@gmail.com" label="이메일" withAsterisk />
      <TextInput placeholder="****" label="비밀번호" withAsterisk />
      <Button>로그인</Button>
      <Button>회원가입</Button>
    </Stack>
  )
}

export const CreateAccount = () => {
  return (
    <Stack>
      <Title>회원가입</Title>
      <TextInput placeholder="example@gmail.com" label="이메일" withAsterisk />
      <TextInput placeholder="****" label="비밀번호" withAsterisk />
      <Button>회원가입</Button>
    </Stack>
  )
}
