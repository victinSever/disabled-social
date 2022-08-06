/**
 * 工具函数
 */

/**
 * 格式化时间
 */

/**
 * 返回对象中的数据含量
 * @param {对象参数} obj 
 */
export const returnRate = function(obj){
	let sum = 0,
		finish = 0
	for (let key in obj) {
		sum++
		if (obj[key] != '' && obj[key] != 0) finish++
	}
	return (finish / sum).toFixed(2)
}

/**
 * 返回指定月数的时间长度
 * @param {月数} mouth 
 */
export const returnDuringTime = function(allMouth) {
	let str = ''
	let nowDate = new Date()
	let year = nowDate.getFullYear()
	let month = nowDate.getMonth() + 1
	let day = nowDate.getDay()
	str +=  year + '-' + month + '-' + day + '~'
	if(allMouth == 12){
		year++
	}
	else if(month + allMouth > 12){
		year++
		month = (month + allMouth) % 12
	}
	str += year + '-' + month + '-' + day
	return str
}