import { axios } from "./base";

//向外暴露fetch函数，选择接收参数url（必须），params，type
export default async(url = '', params = {}, type = 'GET') => {
    type = type.toUpperCase()

    
    return await axios({
        method: type,
        url,
        timeout: 3000,
        params
    })
}