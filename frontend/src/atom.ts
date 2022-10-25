import { atom } from 'jotai'

export interface LayoutItem {
  i: string
  x: number
  y: number
  w: number
  h: number
}
export type Layout = LayoutItem[]
export interface LayoutItemDTO {
  name: string
  x: number
  y: number
  w: number
  h: number
}
export type LayoutDTO = LayoutItemDTO[]

export const fromDTO = (dto: LayoutDTO) => dto.map(w => ({ ...w, i: w.name }))
export const toDTO = (layout: Layout) => layout.map(w => ({ ...w, name: w.i }))

export const layoutAtom = atom<Layout>([
  { i: 'widget1', x: 0, y: 0, w: 1, h: 1 },
  { i: 'widget2', x: 1, y: 0, w: 2, h: 1 },
  { i: 'widget3', x: 0, y: 1, w: 1, h: 1 },
  { i: 'widget4', x: 1, y: 1, w: 1, h: 1 },
])
