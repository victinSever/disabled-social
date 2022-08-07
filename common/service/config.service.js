let BASE_URL = ''


BASE_URL = 'http://192.168.2.24:5211/'

// 正式环境1 120.26.7.136:8082
// 正式环境2（关注模块）120.26.7.136:8083

let staticDomainURL = BASE_URL+ '/sys/common/static';//预留图片路径

const configService = {
	apiUrl: BASE_URL,
	staticDomainURL: staticDomainURL
};

export default configService
