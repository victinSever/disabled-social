//第三方封装库axios
import axios from "axios"


//服务器端口
let baseUrl = 'http://127.0.0.1:8080'

//图片地址
let baseImgPath = 'http://127.0.0.1:8080/'

//本机地址
let baseLocalUrl = 'http://127.0.0.1:4000/'

export {
    baseUrl,
    baseImgPath,
    baseLocalUrl,
    axios,
}