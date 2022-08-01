//第三方封装库axios
import axios from "axios"


//服务器端口
let baseUrl = 'http://192.168.103.249:5210/'

//图片地址
let baseImgPath = 'http://222.177.66.230:9898/'

//本机地址
let baseLocalUrl = 'http://127.0.0.1:4000/'

export {
    baseUrl,
    baseImgPath,
    baseLocalUrl,
    axios,
}