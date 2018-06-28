/**
 * Created by Sorumi on 2018/6/25.
 */
export const SHOW_TYPE = [
  {
    name: "演唱会",
    icon: 'concert'
  },
  {
    name: "音乐会",
    icon: 'music'
  },
  {
    name: "体育比赛",
    icon: 'sport'
  },
  {
    name: "话剧",
    icon: 'drama'
  },
  {
    name: "戏曲",
    icon: 'theater'
  },
  {
    name: "舞蹈",
    icon: 'dance'
  },
  {
    name: "亲子剧",
    icon: 'paternity'
  },
]

export const CITY = [
  '全部', '北京', '上海', '南京', '成都'
]

export const TYPE = [
  '全部', '演唱会', '音乐会', '体育比赛', '话剧', '戏曲', '舞蹈', '亲子剧'
]

export const ORDER_TYPE = {
  '全部' : {
    name: '全部',
    tag: '',
    color: 'blue'
  },
  '已过期' : {
    name: '已过期',
    tag: 'danger',
    color: 'red'
  },
  '已退款' : {
    name: '已退款',
    tag: 'info',
    color: 'grey'
  },
  '未付款' : {
    name: '未付款',
    tag: 'warning',
    color: 'yellow'
  },
  '已完成' : {
    name: '已完成',
    tag: 'success',
    color: 'green'
  },
}
