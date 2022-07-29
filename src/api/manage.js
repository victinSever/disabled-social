import fetch from './fetch'

// 
const sendAudit = data => fetch('/', data, 'post') 


export default {
    sendAudit,
}