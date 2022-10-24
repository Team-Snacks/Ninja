import { useAtom } from 'jotai'
import GridLayout, { ItemCallback, Layout } from 'react-grid-layout'
import { layoutAtom } from './atom'

export const WidgetGrid = () => {
  const maxRow = 3
  const [currentLayout, setCurrentLayout] = useAtom(layoutAtom)

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
