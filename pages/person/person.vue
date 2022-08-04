<template>
	<view class="root">
		<mover></mover>
		<!-- 顶部设置 -->
<!-- 		<view class="header">
			<uni-badge size="normal" :offset="[3, 3]" :is-dot="true" :text="value" absolute="rightTop" type="error">
				<view class="header-prove" @click="gotoConnern">
					<uni-icons type="checkbox-filled" size="25"></uni-icons>
					<text>认证中心</text>
					<uni-icons type="forward" size="18"></uni-icons>
				</view>
			</uni-badge>
			<uni-badge size="normal" :offset="[3, 3]" :is-dot="true" :text="value" absolute="rightTop" type="error">
				<view class="header-buttons" @click="gotoConnern">
					<uni-icons type="bars" size="25"></uni-icons>
				</view>
			</uni-badge>
		</view> -->

		<!-- 主信息部分 -->
		<view class="main">
			<view class="main-top">

				<div class="top-left">
					<view class="top-logo">
						<image v-if="personData.headPicPath" :src="personData.headPicPath" alt="" />					
						<view class="empty-image" v-else>
							<uni-icons type="person-filled" size="80" color="#eee"></uni-icons>
						</view>
					</view>
					<view class="top-left-right">
						<h1>
							<text>{{personData.username}}</text>
						</h1>
						<p>
							<text>用户ID：{{personData.personId}}</text>
							<uni-icons type="compose" size="16" @click="copyText"></uni-icons>
						</p>

						<p>
							<text>我的简历</text>
							<uni-icons type="compose" size="16" @click="gotoResume"></uni-icons>
						</p>
					</view>
				</div>
				<view class="top-right">
					<text v-if="!isSignUp" @click="makeSignUp">签到</text>
					<text v-else class="isFlaged">已签到</text>
				</view>
			</view>
			<view class="main-bottom">
				<view class="bottom-item" @click="gotoBillBoard(1)">
					<text class="item-num">{{personData.attentionCount}}</text>
					<text class="item-title">关注</text>
				</view>
				<view class="bottom-item" @click="gotoBillBoard(2)">
					<text class="item-num">{{personData.fanCount}}</text>
					<text class="item-title">粉丝</text>
				</view>
				<view class="bottom-item">
					<text class="item-num">{{personData.like}}</text>
					<text class="item-title">点赞</text>
				</view>
				<view class="bottom-item">
					<text class="item-num">{{personData.sorts}}</text>
					<text class="item-title">积分</text>
				</view>
			</view>
		</view>

		<!-- 按钮 -->
		<view class="banner">
			<view class="banner-vip section" @click="gotoPrivilege">
				<view class="section-one">
					<text>我的特权</text>
					<uni-icons type="forward" size="20"></uni-icons>
				</view>
				<view class="section-two">
					<text>特权享更多权益·飞速脱单</text>
				</view>
				<view class="section-three">
					<text>开通特权</text>
				</view>
			</view>
			<view class="banner-info section" @click="gotoCheckPerson">
				<view class="section-one">
					<text>完善资料</text>
					<uni-icons type="forward" size="20"></uni-icons>
				</view>
				<view class="section-two">
					<text>完成度越高越容易推荐</text>
				</view>
				<view class="section-three">
					<text>20%</text>
				</view>
			</view>
		</view>

		<!-- 动态 -->
		<view class="active">
			<view class="active-title">
				<text>我的动态</text>
			</view>
			<view class="active-list" v-if="personActiveData.length !== 0">
				<view class="active-item" v-for="(item, i) in personActiveData" :key="i">
					<view class="item-left">
						<text class="day">11</text>
						<text class="mouth">07月</text>
					</view>
					<view class="item-right" @click="gotoComment">
						<p class="text">{{item.text}}</p>
						<view class="image" v-if="item.image">
							<image :src="item.image" alt="">
						</view>
						<view class="btns">
							<view class="btn-info-left">
								<uni-icons type="more" size="30"></uni-icons>
							</view>
							<view class="btn-info-right">
								<view class="like">
									<image class="icon" src="../../static/icon/active/like.png" alt="">
									<text class="num">{{item.likes}}</text>
								</view>
								<view class="comment">									
									<image class="icon" src="../../static/icon/active/comment.png" alt="">
									<text class="num" v-if="item.contents !== 0">{{item.contents}}</text>
									<text class="num" v-else>去评论</text>
								</view>
							</view>
						</view>
					</view>
				</view>
				<view class="active-list-empty">
					<p>没有更多了</p>
				</view>
			</view>
			<view class="active-empty" v-else>
				<text class="empty-icon">
					<uni-icons type="compose" size="60" color="#ccc">
					</uni-icons>
				</text>
				<text>发布一条动态，让大家看到你</text>
			</view>
		</view>

		<!-- 发表动态按钮 -->
		<view class="active-btn" @click="gotoShare">
			<uni-icons type="camera-filled" size="22" color="#fff"></uni-icons>
			<text>发动态</text>
		</view>

	</view>
</template>

<script>
	import apiService from '@/apis/message.js'
	export default {
		data() {
			return {
				// 是否有角标
				value: 1,
				// 是否签到
				isSignUp: false,
				// 个人信息
				personData: {
					username: 'xxx',
					personId: '',
					headPicPath: '',
					attentionCount: 0,
					fanCount: 0,
					like: 0, //点赞
					sorts: 0, //积分
				},
				personActiveData: [{
					publishDate: '2022-8-2 1:59:01',
					text: '今天的天空格外好看！',
					image: '../../static/images/sky.jpg',
					views: 231,
					likes: 1023,
					contents: 52,
					isAudit: false,//被举报
				}]
			}
		},
		mounted(){
			this.getData()
		},
		methods: {
	
			// 获取信息
			async getData(){
				const { data: res} = await apiService.getBaseData({
					personId: 1
				})
				if(res.resultCode === 200){
					this.personData = res.data
					console.log(res);
				}else{
					uni.$showMsg('获取信息失败！')
				}
			},
			
			// 复制id
			copyText() {
				uni.$showMsg("复制失败")
			},
			// 打开简历
			gotoResume(){
				uni.$showMsg("该功能未开放！")
			},
			// 查看关注页面
			gotoBillBoard(type) {
				uni.navigateTo({
					url: '/subpkg/billboard/billboard?type=' + type
				})
			},
			// 签到
			async makeSignUp() {
				const { data: res } = await apiService.signIn({
					reward: 5,
					loginName: '123456'
				})
				console.log(response);
				uni.$showMsg('签到成功')
				this.isFlag = true
				setTimeout(function() {
					uni.$showMsg('积分 + 5')
				}, 1000)
				
			},
			// 跳转到vip充值页
			gotoPrivilege() {
				uni.navigateTo({
					url: '/subpkg/privilege/privilege'
				})
			},
			// 跳转到评论页
			gotoShare() {
				uni.navigateTo({
					url: '/subpkg/share/share'
				})
			},
			// 跳转到资料完善页面
			gotoCheckPerson() {
				uni.navigateTo({
					url: '/subpkg/checkPerson/checkPerson'
				})
			},
			// 去网评论页面
			gotoComment(){
				uni.navigateTo({
					url: '/subpkg/comment/comment'
				})
			}
		}
	}
</script>

<style lang='scss' scoped>
	.root {
		padding: 0 20rpx;
		background-color: #fcfcfc;
	}

	/* 头部 */
	.header {
		height: 100rpx;
		width: 100%;
		display: flex;
		justify-content: space-between;
		align-items: center;
		border-bottom: 1px solid #eee;

		.header-prove {
			display: flex;
			align-items: center;
			background-color: #eee;
			padding: 10rpx 20rpx;
			border-radius: 20rpx;
		}
	}

	/* 信息页 */
	.main {
		margin: 20rpx 0 40rpx 0;

		.main-top {
			display: flex;
			justify-content: space-between;
			margin-bottom: 40rpx;
			height: 200rpx;


			.top-left {
				display: flex;
				
				.top-logo {
					width: 150rpx;
					height: 200rpx;
					box-sizing: border-box;

					image {
						width: 150rpx;
						height: 200rpx;
						border-radius: 30rpx;
						border: 4rpx solid #fff;
					}
					
					.empty-image{
						height: 200rpx;
						width: 100%;
						display: flex;
						justify-content: center;
						flex-direction: column;
						align-items: center;
						font-size: 20px;
						border-radius: 30rpx;
						border: 4rpx solid #eee;
					}
				}

				.top-left-right {
					margin-left: 30rpx;;

					h1 {
						height: 100rpx;
						line-height: 100rpx;
						font-size: 20px;
					}

					p {
						height: 50rpx;
						line-height: 50rpx;
						display: flex;
						text {
							margin-right: 10rpx;
						}
					}
				}
			}

			.top-right {
				display: flex;
				align-items: flex-end;

				text {	
					padding: 0 30rpx;
					height: 60rpx;
					line-height: 60rpx;
					border-radius: 60rpx;
					color: #fff;
					letter-spacing: 2rpx;
					background: darkorange;
				}

				.isFlaged {
					background: #ccc;
				}
			}
		}

		.main-bottom {
			display: flex;
			justify-content: space-around;

			.bottom-item {
				position: relative;
				width: calc((100% - 40rpx)/4);
				display: flex;
				align-items: center;
				flex-direction: column;
				background-color: #fff;
				padding: 10rpx 20rpx;
				border-radius: 20rpx;			

				.item-num {
					font-weight: bold;
					font-size: 16px;
				}

				.item-title {
					color: #777;
				}
			}
			
			.bottom-item:not(:last-child):before{
				content: "";
				height: 30rpx;
				width: 2rpx;
				background-color: #ddd;
				position: absolute;
				right: 0;
				top: 50%;
				transform: translateY(-50%);
				z-index: 20;
			}
		}
	}

	/* 推荐 */
	.banner {
		display: flex;
		justify-content: space-between;

		.section {
			width: calc((100% - 80rpx)/2);
			padding: 20rpx 0 20rpx 20rpx;
			background-color: #fff;
			border-radius: 20rpx;
			border: 4rpx solid darkorange;

			.section-one {
				display: flex;
				align-items: center;
				justify-content: space-between;

				text {
					font-weight: bold;
					font-size: 15px;
				}
			}

			.section-two {
				text {
					color: #777;
					font-size: 12px;
				}
			}

			.section-three {
				margin-top: 20rpx;

				text {
					color: #fff;
					height: 60rpx;
					line-height: 60rpx;
					border-radius: 30rpx;
					padding: 10rpx 50rpx;
					background: linear-gradient(90deg, #ffaa7f, #ff557f);
				}
			}
		}

	}

	/* 动态 */
	.active {
		margin-top: 20rpx;

		.active-title {
			font-weight: bold;
			margin-bottom: 10rpx;
		}
		
		.active-list{
			.active-item{
				display: flex;
				justify-content: space-between;
				margin-bottom: 20px;
				
				.item-left{
					padding-top: 30rpx;
					width: 100rpx;
					display: flex;
					flex-direction: column;
					align-items: center;
					font-weight: bold;
					color: #ddd;
					margin-right: 20rpx;
					
					
					.day{
						font-size: 22px;
					}
					
					.mouth{
						font-size: 16px;
					}
				}
				
				.item-right{
					width: calc(100% - 100rpx);
					
					.text{
						line-height: 80rpx;
						font-weight: bold;
					}
					.image{
						
						image{
							width: calc(100% - 50rpx);
							border-radius: 30rpx;
						}
					}
					
					.btns{
						margin-top: 10px;
						display: flex;
						justify-content: space-between;
						width: calc(100% - 50rpx);
						
						.btn-info-right {
							display: flex;
						
							&>view {
								display: flex;
								align-items: center;
								margin-left: 40rpx;
								
								.icon{
									width: 40rpx;
									height: 40rpx;
								}
						
								.num {
									font-size: 12px;
									margin-left: 10rpx;
									color: #a5a395;
								}
							}
						}
					}
				}
			}
		
			.active-list-empty{
				width: 100%;
				text-align: center;
				margin-bottom: 50rpx;
				color: #c6c6c6;
			}
		}

		
		.active-empty {
			height: 400rpx;
			display: flex;
			align-items: center;
			justify-content: center;
			flex-direction: column;
			color: #ccc;

			.empty-icon {
				margin-bottom: 50rpx;
			}
		}
	}

	/* 动态按钮 */
	.active-btn {
		position: fixed;
		bottom: 120rpx;
		right: 20rpx;
		background-color: darkorange;
		z-index: 5;
		font-size: 14px;
		color: #fff;
		display: flex;
		align-items: center;
		padding: 20rpx 30rpx;
		border-radius: 20rpx;

		text {
			margin-left: 10rpx;
		}
	}
</style>
