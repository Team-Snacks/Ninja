import { atom } from "jotai"

export interface LayoutItem {
  i: string
  x: number
  y: number
  w: number
  h: number
}
export type Layout = LayoutItem[]

export const layoutAtom = atom<Layout>([
  { i: 'a', x: 0, y: 0, w: 1, h: 1 },
  { i: 'b', x: 1, y: 0, w: 2, h: 1 },
  { i: 'c', x: 0, y: 1, w: 1, h: 1 },
  { i: 'd', x: 1, y: 1, w: 1, h: 1 },
])
