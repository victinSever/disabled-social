/**
 * axios函数封装
 */

import { axios, baseUrl, adminPath } from "./base";

//向外暴露fetch函数，选择接收参数url（必须），params，type
export default async(url = '', params = {}, type = 'GET') => {
    type = type.toUpperCase()
    
    url = baseUrl + adminPath + url
    console.log(url);
    return await axios({
        method: type,
        url,
        timeout: 5000,
        params
    })
}