let BASE_URL = ''


BASE_URL = 'http://192.168.2.24:5211/'

let staticDomainURL = BASE_URL+ '/sys/common/static';//预留图片路径

const configService = {
	apiUrl: BASE_URL,
	staticDomainURL: staticDomainURL
};

export default configService
