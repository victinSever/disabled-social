<template>
	<view>
		<mover></mover>
		<!-- 自定义头部导航 -->
		<view class="header" :style="{top:CustomBar+'px'}">
			<view class="header-left">
				<uni-icons type="back" size="30" color="#000" @click="backTo"></uni-icons>
				<image :src="userInfo.userImage" mode="widthFix"></image>
				<view class="info">
					<text>{{userInfo.userName}}</text>
					<text>{{userInfo.lastTime}}</text>
				</view>
			</view>
			<view class="header-right">
				<!-- <uni-icons type="phone-filled" size="30" color="#777" @click="call"></uni-icons> -->
				<uni-icons type="info-filled" size="30" color="#777"></uni-icons>
			</view>
		</view>

		<!-- 聊天区 -->
		<view class="chatting" :style="{'padding-top':CustomBar+'px'}">
			<view class="time">
				<span>07/13 12:55</span>
			</view>
			<scroll-view scroll-y="true">
				<view class="chatting-item" v-for="(item, i) in chattingList" :key="i">
					<view class="left-content content" v-if="item.isMy">
						<image :src="userInfo.userImage"></image>
						<view class="main-content">
							<text>{{item.content.text}}</text>
						</view>
					</view>
					<view class="right-content content" v-else>
						<view class="main-content">
							<text>{{item.content.text}}</text>
						</view>
						<image :src="myInfo.userImage"></image>
					</view>
				</view>
			</scroll-view>
		</view>

		<!-- 输入编辑 -->
		<view class="edit">
			<view class="edit-input">
				<input type="text" placeholder="输入新消息">
				<span @click="sendMessage">发送</span>
			</view>
			<view class="edit-btns">
				<uni-icons class="icons" type="image-filled" size="30" color="#777" @click="chooseImg"></uni-icons>
				<uni-icons class="icons" type="camera-filled" size="30" color="#777" @click="chooseVideo"></uni-icons>
				<!-- <uni-icons type="mic-filled" size="30" color="#777" @click="sendMessage"></uni-icons> -->
<!-- 				<uni-icons type="gift-filled" size="30" color="#777" @click="sendMessage"></uni-icons>
				<uni-icons type="folder-add-filled" size="30" color="#777" @click="sendMessage"></uni-icons> -->
			</view>
		</view>

	</view>
</template>

<script>
	import webSocket from "@/common/util/webSocket.js"
	export default {
		data() {
			return {
                 CustomBar: this.CustomBar,
				// 对方的信息
				userInfo: {},
				// 我的信息
				myInfo: {},
				// 聊天内容
				chattingList: [],
				messageObj:{
					senduserid:"",
					reciveuserid:"",
					sendtext:"",
				}
			};
		},
		onLoad(option) {
			//获取对方用户信息
			getUserInfo(option.userId)
		},
		methods: {
			getUserInfo(id){
				
			},
			
			chooseImg() {
                let _that=this;
				uni.chooseImage({
					count: 6, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album','camera'], //从相册选择
					success: function(res) {
                        
                        res.tempFilePaths.forEach((item)=>{
                            uni.uploadFile({
                            	// 需要上传的地址
                            	url: _that.vuex_uploadAction,
                            	// filePath  需要上传的文件
                            	filePath: item,
                            	name: 'file',
                            	header: {
                            		token: ""		// 挂载请求头为用户的 token
                            	},
                            	success:function (arr) {
                            		let data = JSON.parse(arr.data);
									let str=`<image src="${data}" style="width:200rpx;height:300rpx"  mode="widthFix" />`
                            	    webSocket.sendData({
										
									})
								}
                            });
                        })

                        
					}
				});
			},
			chooseVideo(){
				uni.showLoading({
					mask: true,
					title: '上传中...'
				})
				// uploadFile 存储需要上传的文件
				let uploadFile = '';
				let _that = this;
				// 1.选择要上传的视频
	           uni.chooseVideo({
					maxDuration: 10,		// 拍摄视频最长拍摄时间，单位秒。最长支持 60 秒。
					sourceType: ['album','camera'],		// album 从相册选视频，camera 使用相机拍摄，默认为：['album', 'camera']
					success:function (res) {
						uploadFile = res.tempFilePath;
						// console.log(uploadFile);
						// 2.上传所选视频到服务器
						uni.uploadFile({
							// 需要上传的地址
							url: _that.vuex_uploadAction,
							// filePath  需要上传的文件
							filePath: uploadFile,
							name: 'file',
							header: {
								token: ""		// 挂载请求头为用户的 token
							},
							success:function (arr) {
								let data = JSON.parse(arr.data);
                                let str=`<video object-fit='fill' src="${data}" style="width:200rpx;height:300rpx" />`
                                webSocket.sendData({
                                	
                                })
                                // data就是上传的成功的路径
							}
						});

					}
				});

			},
			
			// 打电话
			call() {
				uni.$showMsg()
			},
			makeSetting() {
				uni.$showMsg()
			},
			// 返回上一个界面
			backTo() {
				uni.navigateBack()
			}
		}
	}
</script>

<style lang="scss" scoped>
	.header {
		position: fixed;
		top: 70rpx;
        width: 100%;
		z-index: 2;
		background-color: #fff;
		height: 120rpx;
		border-bottom: 2rpx solid #eee;
		box-shadow: 1px 1px 2px #eee;
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 0 20rpx;

		.header-left {
			display: flex;
			align-items: center;

			image {
				width: 70rpx;
				height: 70rpx;
				border-radius: 50%;
				margin: 0 20rpx;
			}

			.info {
				display: flex;
				flex-direction: column;

				text:first-child {
					font-weight: bold;
				}

				text:last-child {
					font-size: 12px;
					color: #777;
				}
			}
		}

		.header-right {
			width: 150rpx;
			display: flex;
			align-items: center;
			justify-content: space-around;
		}
	}


	.chatting {
		padding: 0 20rpx;

		.time {
			display: flex;
			justify-content: center;
			margin-top: 20rpx;
			font-size: 12px;

			span {
				color: #777;
			}
		}

		.chatting-item {
			margin: 40rpx 0;

			.content {
				display: flex;

				image {
					width: 70rpx;
					height: 70rpx;
					border-radius: 50%;
					margin-top: 10rpx;
				}

				.main-content {
					max-width: 500rpx;
					background-color: #f2f2f2;
					border-radius: 20rpx;
					padding: 25rpx;
					margin: 0 20rpx;

					text {
						font-size: 16px;
					}
				}
			}



			.right-content {
				justify-content: flex-end;

			}
		}
	}


	.edit {
		position: sticky;
		bottom: 0;
		width: calc(100vw - 40rpx);
		height: 220rpx;
		padding: 0 20rpx;
		padding-top: 10rpx;
		background-color: #fff;

		.edit-input {
			width: 100%;
			display: flex;
			align-items: center;
			justify-content: space-between;
			height: 100rpx;
			background-color: #f2f2f2;
			border-radius: 30rpx;
			font-size: 16px;

			input {
				padding-left: 30rpx;
				font-size: 16px;
				width: 500rpx;
			}

			input::placeholder {
				font-size: 16px;
			}


			span {
				margin-right: 30rpx;
				color: #777;
			}
		}

		.edit-btns {
			padding: 0 10rpx;
			height: 100rpx;
			display: flex;
			align-items: center;

            .icons{
                margin-right: 40rpx;
            }
		}
	}
</style>
