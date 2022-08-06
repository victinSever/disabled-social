<template>
	<view class="">
		<mover></mover>
		<!-- 头部 -->
		<view class="message-header">
			<text>
				<text @click="concern" style="margin-right: 40rpx;">消息</text>
				<text @click="gotoGroup">单身群</text>
			</text>
			<text>
				<uni-icons type="search" size="30"></uni-icons>
			</text>
		</view>

		<!-- 推荐 -->
		<view class="message-banner">
			<view class="banner-title">
				<text>推荐</text>
			</view>
			<view class="">
				<scroll-view scroll-x="true" class="message-banner-inbox" scroll-left="120">
					<view v-for="(item, i) in recommentData" :key="i" class="item">
						<img :src="item.imgpath" alt="">
						<text>{{item.lastTime}}{{item.lastUnit}}前</text>
					</view>
				</scroll-view>
			</view>
		</view>

		<!-- 消息 -->
		<view class="message-mes">
			<view class="mes-inbox">
				<view class="mes-title">
					<text>消息</text>
					<text>
						<i class="iconfont icon-settings" v-if="!isSetting" @click="isSetting = true"></i>
						<i class="iconfont icon-settings1" v-else @click="isSetting = false"></i>
					</text>
				</view>
				<view class="mes-list">
					<uni-swipe-action>
						<block v-for="(item, index) in messageData" :key="index">
							<!-- uni-swipe-action-item 可以为其子节点提供滑动操作的效果。需要通过 options 属性来指定操作按钮的配置信息 -->
							<uni-swipe-action-item :right-options="options">
								<view class="item" @click="gotoMessageDetail(item)">
									<view class="left-box">
										<img :src="item.imgpath" alt="">
									</view>
									<view class="right-box">
										<view class="right-box-left">
											<text>{{item.username}}</text>
											<p>{{item.message}}</p>
										</view>
										<view class="right-box-right">
											<text>{{item.lastTime}}{{item.lastUnit}}前</text>
										</view>
									</view>
								</view>
							</uni-swipe-action-item>
						</block>
					</uni-swipe-action>
				</view>
			</view>
		</view>
	
	</view>
</template>

<script>
	 import message from "@/apis/message.js"
	export default {
		data() {
			return {
				recommentData:[],
				messageData: [],
				options: [{
					text: '删除', // 显示的文本内容
					style: {
						backgroundColor: '#C00000' // 按钮的背景颜色
					}
				}],
				isSetting: false, //是否点击设置
				
			}
		},
		mounted() {
			this.getrecommendList();
			this.getMessageList();
            
            //接受消息数据
            uni.$on("messageData",(data)=>{
                if(data.type==1){
                }
            })
		},
		
		methods: {
            
            
			//获取推荐人
			getrecommendList(){
				message.recommendList().then(response => {
                 
                }).catch(error => {
                    
                }) 
			},
			
			//获取用户列表
			getMessageList(){
				message.messageList().then(response => {
			     
			    }).catch(error => {
			        
			    }) 
			},
            
            //创建聊天用户
			addUserMessage(){
				message.addMessageUser().then(response => {
			     
			    }).catch(error => {
			        
			    }) 
			},
			
			
			gotoMessageDetail(item) {
				uni.navigateTo({
					url: '/subpkg/information/information?username=' + item.username
				})
			},
			gotoGroup() {
				uni.navigateTo({
					url: '/subpkg/group/group'
				})
			},
			concern() {
				uni.$showMsg("未开放")
			},

		}
	}
</script>

<style lang="scss" scoped>
	

	// 头部
	.message-header {
		position: sticky;
		top: 0;
		z-index: 3;
		background-color: #fff;
		display: flex;
		justify-content: space-between;
		height: 80rpx;
		line-height: 80rpx;
		padding: 0 20rpx;

		&>text:first-child {
			font-size: 20px;

			text:first-child {
				font-weight: bold;
				margin-right: 20rpx;
			}
		}
	}

	// 推荐
	.message-banner {
		padding: 20rpx 20rpx 0 20rpx;

		.banner-title {
			display: flex;
			justify-content: space-between;

			&>text {
				font-weight: bold;
			}
		}

		.message-banner-inbox {
			margin-top: 20rpx;
			height: 240rpx;
			white-space: nowrap;
			width: 100%;

			.item {
				display: inline-block;
				width: 180rpx;
				height: 200rpx;
				margin-right: 20rpx;
				border: 4rpx solid rgba(251, 150, 110, 1);
				border-radius: 40rpx;

				img {
					display: block;
					width: 120rpx;
					height: 120rpx;
					border-radius: 50%;
					margin: 0 auto;
					margin-top: 15rpx;
				}

				text {
					display: inline-block;
					width: 100%;
					margin-top: 20rpx;
					font-size: 12px;
					text-align: center;
				}
			}
		}
	}

	// 消息
	.message-mes {
		padding: 0 20rpx;

		.mes-inbox {
			border-top: 1rpx solid rgba(0, 0, 0, 0.1);

			.mes-title {
				height: 80rpx;
				line-height: 80rpx;
				display: flex;
				justify-content: space-between;

				&>text {
					font-weight: bold;

					i {
						font-size: 20px;
						color: #777;
					}
				}
			}

			.mes-list {
				width: 100%;

				.item {
					height: 100rpx;
					width: calc(100vw - 40rpx);
					display: flex;
					margin-bottom: 40rpx;

					.left-box {
						display: flex;
						align-items: center;
						width: 100rpx;
						
						img {
							height: 80rpx;
							width: 80rpx;
							border-radius: 50%;
						}
					}

					.right-box {
						width: calc(100% - 100rpx);
						display: flex;
						justify-content: space-between;
						align-items: center;

						.right-box-left {
							width: 80%;

							text,
							p {
								height: 50rpx;
								line-height: 50rpx;
								display: inline-block;
							}
							
							text{
								font-size: 14px;
							}

							p {	
								width: 100%;
								font-size: 13px;
								color: #777;
								overflow: hidden;
								text-overflow: ellipsis;
							}
						}

						.right-box-right {
							width: 18%;
							display: flex;
							justify-content: center;
							align-items: center;

							text {
								color: #777;
								font-size: 10px;
							}
						}

					}
				}
			}


		}
	}
</style>
