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
		<view class="message-banner" v-if="recommentData.length>0">
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
							<uni-swipe-action-item :right-options="options" @click="bindClick">
								<view class="item" @click="gotoMessageDetail(item)">
									<view class="left-box">
										<img :src="item.uimg" alt="">
									</view>
									<view class="right-box">
										<view class="right-box-left">
											<text>{{item.nickname}}</text>
											<!-- <p>{{item.message}}</p> -->
										</view>
										<view class="right-box-right">
											<!-- v-if="item.num!=0" -->
											<text class="item-num" v-if="item.num!=0">{{item.num}}</text>
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
				recommentData: [],
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
		onShow() {
			this.getrecommendList();
			this.getMessageList();
			//接受消息数据
			uni.$on("messageData", (data) => {
				if (data.type == 1) {}
			})
		},

		methods: {
			
			//删除聊天人
			bindClick(e){
				if(e.content.text=="删除"){
					this.$tip.confirm('确认删除？').then(()=>{
						let obj =this.messageData[e.index];
							message.deleteuser(obj.userid).then(response => {
								this.getMessageList();
						  	}).catch(error => {
						
						})
					})
	
				}
			},

			//获取推荐人
			getrecommendList() {
				message.recommendList().then(response => {
					this.recommentData = response.data ? response.data : []
				}).catch(error => {

				})
			},

			//获取用户列表
			getMessageList() {
				message.messageList("1").then(response => {
					  this.messageData = response.data ? response.data : [];
					this.messageData.forEach((item,index)=>{
						this.$set(item,'num',0)
						item.num=0
						this.$store.state.webSocket.messageList.forEach((items)=>{
							if(item.userid==items.userid){
							   item.num=items.num
							}
						});
						this.getMessageDetail(item.userid,index)
					})
			     
				}).catch(error => {

				})
			},
			
			//获取消息列表
			getMessageDetail(id,index){
				message.lkuschatmsg({
					reviceuserid: id,
					userid: "1"
				}).then(response => {
					response.data = response.data ? response.data : [];
					let indexs=0;
					response.data.forEach((item)=>{
						if(item.msgstatus==0){
						  indexs++
						}
					})
					this.messageData[index].num=indexs;
				}).catch(error => {
				
				})
			},


			gotoMessageDetail(item) {
				uni.navigateTo({
					url: '/subpkg/information/information?userId=' + item.userid
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
					height: 120rpx;
					width: calc(100vw - 40rpx);
					display: flex;
					margin-bottom: 40rpx;

					.left-box {
						display: flex;
						align-items: center;
						width: 160rpx;

						img {
							height: 104rpx;
							width: 104rpx;
							border-radius: 50%;
						}
					}

					.right-box {
						width: calc(100% - 100rpx);
						display: flex;
						justify-content: space-between;
						align-items: center;
						
						.item-num{
							    position: absolute;
							    left: 61rpx;
							    font-size: 24rpx;
							    background: red;
							    color: #ffffff;
							    padding: 10rpx 20rpx;
							    top: 0;
							    border-radius: 20rpx;
						}

						.right-box-left {
							width: 80%;

							text,
							p {
								height: 50rpx;
								line-height: 50rpx;
								display: inline-block;
							}

							text {
								font-size: 40rpx;
							}
						}

						.right-box-right {
							width: 18%;
							display: flex;
							justify-content: center;
							align-items: center;

						}

					}
				}
			}


		}
	}
</style>
