import { useAtom } from 'jotai'
import GridLayout, { ItemCallback, Layout } from 'react-grid-layout'
import { layoutAtom } from './atom'

export const WidgetGrid = () => {
  const maxRow = 3
  const [layout, setLayout] = useAtom(layoutAtom)

  const saveCurrentLayout: ItemCallback = layout => {
    setLayout(layout)
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
      layout={layout}
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
      {layout.map(elem => (
        <div key={elem.i} style={{ border: 'solid black 1px' }}>
          {elem.i}
        </div>
      ))}
    </GridLayout>
  )
}
