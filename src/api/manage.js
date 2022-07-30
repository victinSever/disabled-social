import fetch from './fetch'

// 
const sendAudit = data => fetch('/', data, 'post') 

// 业务模块

const getAdvertisingList = data => fetch('/business/getAdvertisingList', data, 'get') //获取广告列表

const addAdvertising = data => fetch('/business/addAdvertising', data, 'post') //添加单个广告

const deleteAdvertsing = data => fetch('/business/deleteAdvertsing', data, 'delete') //删除单个广告


const getAdvertiseplaceList = data => fetch('/business/getAdvertiseplaceList', data, 'get') //获取广告位列表


const getActivityList = data => fetch('/business/getActivityList', data, 'get') //获取活动列表

const deleteActivity = data => fetch('/business/deleteActivity', data, 'delete') //删除单个活动


export {
    getAdvertisingList,
    addAdvertising,
    deleteAdvertsing,

    getAdvertiseplaceList,

    getActivityList,
    deleteActivity,
}