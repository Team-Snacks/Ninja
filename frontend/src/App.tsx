import GridLayout from 'react-grid-layout'
import 'react-grid-layout/css/styles.css'
import 'react-resizable/css/styles.css'

function App() {
  const layout = [
    { i: 'a', x: 0, y: 0, w: 1, h: 1 },
    { i: 'b', x: 1, y: 0, w: 2, h: 1 },
    { i: 'c', x: 0, y: 1, w: 1, h: 2 },
    { i: 'd', x: 1, y: 1, w: 2, h: 2 },
  ]

  return (
    <div className='App'>
      <div>메뉴바</div>
      <GridLayout
        className='layout'
        layout={layout}
        rowHeight={(window.innerHeight * 0.9) / 3}
        width={window.innerWidth * 0.99}
        cols={5}
        resizeHandles={['se']}
        style={{ border: 'solid black 1px' }}
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
    </div>
  )
}

export default App
