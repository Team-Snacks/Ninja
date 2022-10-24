import { useState } from 'react'
import GridLayout, { ItemCallback, Layout } from 'react-grid-layout'

export const WidgetGrid = () => {
  const maxRow = 3
  const dummy = [
    { i: 'a', x: 0, y: 0, w: 1, h: 1 },
    { i: 'b', x: 1, y: 0, w: 2, h: 1 },
    { i: 'c', x: 0, y: 1, w: 1, h: 1 },
    { i: 'd', x: 1, y: 1, w: 1, h: 1 },
  ]
  const [currentLayout, setCurrentLayout] = useState<Layout[]>(dummy)

  const saveCurrentLayout: ItemCallback = layout => {
    setCurrentLayout(layout)
  }

  const checkChangedLayout: ItemCallback = layout => {
    layout.forEach((ele, index) => {
      if (ele.y + ele.h > maxRow) {
        console.log('변경')
      }
    })
  }
  return (
    <GridLayout
      className='layout'
      layout={currentLayout}
      rowHeight={(window.innerHeight * 0.9) / 3}
      width={window.innerWidth * 0.99}
      cols={5}
      resizeHandles={['se']}
      style={{ border: 'solid black 1px' }}
      compactType={null}
      autoSize={true}
      maxRows={maxRow}
      onResizeStart={saveCurrentLayout}
      onResizeStop={checkChangedLayout}
      onDragStart={saveCurrentLayout}
      onDragStop={checkChangedLayout}
    >
      <div key='a' style={{ border: 'solid black 1px' }}>
        요소1
      </div>
      <div key='b' style={{ border: 'solid black 1px' }}>
        요소2
      </div>
      <div key='c' style={{ border: 'solid black 1px' }}>
        요소3
      </div>
      <div key='d' style={{ border: 'solid black 1px' }}>
        요소4
      </div>
    </GridLayout>
  )
}
