<template>
	<view>
		<view class="login-container">
			<!-- #ifdef MP-ALIPAY || MP-WEIXIN-->
			<view class="other-login-way">
				<view class="quick-login">
					<text class="quick-text">快捷登录</text>
				</view>
				<!-- @getphonenumber="wxOneLogin"  open-type="getPhoneNumber" -->
				<button style="border: 0; background-color: #FBFBFB;" class="flex-button" @click="btn_login()">
					<image class="icon-wechat" src="@/static/images/wechat.png" mode="scaleToFill"></image>
					一键登录
				</button>
			</view>
			<!-- #endif -->
		</view>
	</view>
</template>

<script>
	import auth from "@/apis/auth.js"
	import {
		ACCESS_TOKEN
	} from '@/common/util/constants.js'
	export default {
		data() {
			return {};
		},
		mounted() {

			//获取授权码
			// #ifdef MP-ALIPAY || MP-WEIXIN
			// this.wxGetUserInfo();
			// #endif
			// #ifdef  APP-PLUS
			this.getPhoneCardDetail()
			// #endif
		},
		methods: {
			//获取当前手机手机卡信息
			getPhoneCardDetail() {
				uni.preLogin({
					provider: 'univerify',
					success: (e) => {
						uni.login({
							provider: 'univerify',
							univerifyStyle: { // 自定义登录框样式
								fullScreen: true
							},
							success: (res) => { // 登录成功
								console.log(res)
								uniCloud.callFunction({
									name: 'getPhoneNumber', // 云函数名称
									data: { //传给云函数的参数
										'access_token': res.authResult
										.access_token, // 客户端一键登录接口返回的access_token
										'openid': res.authResult
											.openid // 客户端一键登录接口返回的openid
									},
									success(callRes) {
										// 此处可获得手机号，调用后端接口
										console.log(callRes.result.data);
										
										uni.redirectTo({
											url: "/pages/recomment/recomment"
										})
									},
									fail(callErr) {
										console.log('调用云函数出错', callErr)
									},
									complete() {
										uni.closeAuthView() //关闭授权登录界面
									}
								})
							}
						})

					},
					fail(res) {
						console.log(res)
						// 预登录失败
						//如果手机没有插入有效的sim卡，或者手机蜂窝数据网络关闭，
						//都有可能造成预登录校验失败。
					}
				})
			},
			wxGetUserInfo() {
				let _that = this;
				uni.login({
					scopes: 'auth_user',
					success: res => {
						var code = res.code;
					}
				})
			},
			btn_login(){
				auth.login({
					tel:"15102340082"
				}).then((res)=>{
					uni.setStorageSync(ACCESS_TOKEN,res.data.token)
					
				}).catch(()=>{
					
				})
			}

		},
	};
</script>
<style scoped lang="scss">
	@import url("./login.css");
	
	.flex-button{
		display: flex;
		align-items: center;
		justify-content: center;
		text-align: center;
	}
	
</style>
