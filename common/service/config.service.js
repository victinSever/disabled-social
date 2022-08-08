let BASE_URL = ''


BASE_URL = 'http://192.168.2.24:5211/'

// BASE_URL = 'http://120.26.7.136:8082/'
// // #ifdef H5
// // BASE_URL = '/api'  //H5下将地址修改为/api
// // #endif

// >>>>>>> d301d05a74edd41cf0a58f6388d7aaaf5302ef02

// 正式环境1 120.26.7.136:8082
// 测试环境 http://222.177.66.230:8082/

let staticDomainURL = BASE_URL+ '/sys/common/static';//预留图片路径

const configService = {
	apiUrl: BASE_URL,
	staticDomainURL: staticDomainURL
};

export default configService
