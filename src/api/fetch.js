//向外暴露fetch函数，选择接收参数url（必须），params，type
export default async(url = '', params = {}, type = 'GET') => {
    type = type.toUpperCase()

    let headers = {
        "Content-type": "multipart/from-data"
    }
    
    return await axios({
        method: type,
        url,
        timeout: 3000,
        headers,
        params
    })
}