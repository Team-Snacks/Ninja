import { Button } from '@mantine/core'
import axios from 'axios'
import { useAtom } from 'jotai'
import { useEffect, useState } from 'react'
import { layoutAtom } from './atom'

interface Widget {
  name: string
}

function Market() {
  //마켓 목록 get
  const [marketList, setMarketList] = useState<Widget[]>([])
  const getWidgets = async () => {
    try {
      const data = await axios.get(`${import.meta.env.VITE_ENDPOINT}/market`)
      setMarketList(data.data)
    } catch (e) {
      console.log(e)
    }
  }
  useEffect(() => {
    getWidgets()
  }, [])

  //마켓 추가
  const [widgetName, setWidgetName] = useState<string>('')
  const widgetRegist = async () => {
    const postWidget: Widget = { name: widgetName }
    try {
      const data = await axios.post(`${import.meta.env.VITE_ENDPOINT}/market`, {
        postWidget,
      })
      console.log(data)
    } catch (e) {
      console.log(e)
    }
  }
  //위젯 그리드에 추가
  const [layout, setLayout] = useAtom(layoutAtom)
  const addWidget = (widgetName: string) => {
    //layout 다 돌면서 검사해서 비어있는 위치 찾는 알고리즘 필요
    layout.push({
      i: widgetName,
      x: 2,
      y: 2,
      w: 1,
      h: 1,
    })
    console.log(layout)
    setLayout(layout)
  }

  return (
    <div>
      <div>마켓</div>
      {marketList?.map((ele, index) => {
        return (
          <Button onClick={() => addWidget(ele.name)} key={index}>
            {ele.name}
          </Button>
        )
      })}

      <div>
        <input
          id='widgetInput'
          placeholder='위젯 이름'
          onChange={e => setWidgetName(e.target.value)}
        />
        <button onClick={widgetRegist}>위젯 등록</button>
      </div>
    </div>
  )
}

export default Market
