let BASE_URL = ''


if (process.env.NODE_ENV == 'development') {
    BASE_URL = 'https://stage.du-power.com' // 开发环境
} else {
	BASE_URL = '' // 生产环境
}
let staticDomainURL = BASE_URL+ '/sys/common/static';//预留图片路径

const configService = {
	apiUrl: BASE_URL,
	staticDomainURL: staticDomainURL
};

export default configService
