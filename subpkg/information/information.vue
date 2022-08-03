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
				<uni-icons class="icons" type="image-filled" size="30" color="#777" @click="sendPic"></uni-icons>
				<uni-icons class="icons" type="camera-filled" size="30" color="#777" @click="sendPhotograph"></uni-icons>
				<!-- <uni-icons type="mic-filled" size="30" color="#777" @click="sendMessage"></uni-icons> -->
<!-- 				<uni-icons type="gift-filled" size="30" color="#777" @click="sendMessage"></uni-icons>
				<uni-icons type="folder-add-filled" size="30" color="#777" @click="sendMessage"></uni-icons> -->
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
                 CustomBar: this.CustomBar,
				// 对方的信息
				userInfo: {
					userImage: '/static/images/home/img1.png',
					userName: '小懒猫',
					lastTime: '11小时前'
				},
				// 我的信息
				myInfo: {
					userImage: '/static/images/user.jpg',
					userName: '微凉',
				},
				// 聊天内容
				chattingList: [{
					isMy: false,
					content: {
						text: '吃饭了吗',
					},

				}, {
					isMy: true,
					content: {
						text: '没呢',
					},
				}, {
					isMy: true,
					content: {
						text: '你呢',
					},
				}, {
					isMy: false,
					content: {
						text: '要不我们去新开的那家店？',
					},
				}, {
					isMy: true,
					content: {
						text: '好的',
					},
				}, {
					isMy: true,
					content: {
						text: '另外，你喜欢什么',
					},
				}, {
					isMy: true,
					content: {
						text: '你结过婚嘛？',
					},
				}, {
					isMy: false,
					content: {
						text: '没呢',
					},
				}, {
					isMy: true,
					content: {
						text: '你怎么残疾了，发生了什么，我想先了解你的过去',
					},
				}, {
					isMy: false,
					content: {
						text: '这...',
					},
				}]
			};
		},
		methods: {
			// 选择图片
			sendPic(){
				uni.chooseImage({
					count: 6, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: function (res) {
						console.log(JSON.stringify(res.tempFilePaths));
					}
				});
			},
            
            //照相机
            sendPhotograph(){
                uni.chooseImage({
                	count: 6, //默认9
                	sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
                	sourceType: ['camera'], //照相机
                	success: function (res) {
                		console.log(JSON.stringify(res.tempFilePaths));
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
