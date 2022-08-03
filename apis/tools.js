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
		console.log(obj[key]);
	}
	return (finish / sum).toFixed(2)
}