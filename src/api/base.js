//第三方封装库axios
import axios from "axios"


//服务器环境
let baseUrl = 'http://222.177.66.230:8080'

// 管理员地址
let adminPath = '/api/admin'

//图片地址
let baseImgPath = 'http://222.177.66.230:9898/'

//本机地址
let baseLocalUrl = 'http://127.0.0.1:8080/'

export {
    baseUrl,
    adminPath,
    baseImgPath,
    baseLocalUrl,
    axios,
}