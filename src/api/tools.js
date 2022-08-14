/**********************************************
 * 工具函数
 * @param {*} num 
 * @returns 
 */

// 格式化大整数字，添加逗号
export const formBigNum = function (num) {
  let str = "" + num;
  let str1 = "";
  for (let i = 0; i < str.length; i++) {
    if (i % 4 == 0 && i != 0) {
      str1 += ",";
    }
    str1 += str.charAt(i);
  }
  return str1;
}

/**
 * 返回当前时刻的格式化时间xx:xx:xx
 * @returns 
 */
export const formatDateTime = function () {
  var date = new Date();
  var h = date.getHours();
  h = h < 10 ? ('0' + h) : h;
  var minute = date.getMinutes();
  minute = minute < 10 ? ('0' + minute) : minute;
  var second = date.getSeconds();
  second = second < 10 ? ('0' + second) : second;
  return h + ':' + minute + ':' + second;
}




