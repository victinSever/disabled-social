<template>
	<view class="root">
		<mover></mover>
		<!-- 顶部设置 -->
		<view class="header">
			<!-- 认证按钮设置区 -->
			<uni-badge size="normal" :offset="[3, 3]" :is-dot="true" :text="value" absolute="rightTop" type="error">
				<view class="header-prove" @click="gotoConnern">
					<uni-icons type="checkbox-filled" size="25"></uni-icons>
					<span>认证中心</span>
					<uni-icons type="forward" size="18"></uni-icons>
				</view>
			</uni-badge>
			<uni-badge size="normal" :offset="[3, 3]" :is-dot="true" :text="value" absolute="rightTop" type="error">
				<view class="header-buttons" @click="gotoConnern">
					<uni-icons type="bars" size="25"></uni-icons>
				</view>
			</uni-badge>
		</view>

		<!-- 主信息部分 -->
		<view class="main">
			<view class="main-top">

				<div class="top-left">
					<view class="top-logo">
						<img :src="personData.userImage" alt="">
					</view>
					<view class="top-left-right">
						<h1>
							<span>{{personData.username}}</span>
						</h1>
						<p>
							<span>用户ID：{{personData.userId}}</span>
							<uni-icons type="compose" size="16" @click="copyText"></uni-icons>
						</p>

						<p>
							<span>我的简历</span>
							<uni-icons type="compose" size="16" @click="copyText"></uni-icons>
						</p>
					</view>
				</div>
				<view class="top-right">
					<span v-if="!isFlag" @click="makeFlag">签到</span>
					<span v-else class="isFlaged">已签到</span>
				</view>
			</view>
			<view class="main-bottom">
				<view class="bottom-item" @click="gotoConnern">
					<span class="item-num">{{personData.userConcern}}</span>
					<span class="item-title">关注</span>
				</view>
				<view class="bottom-item" @click="gotoConnern">
					<span class="item-num">{{personData.userConcerned}}</span>
					<span class="item-title">被关注</span>
				</view>
				<view class="bottom-item" @click="gotoConnern">
					<span class="item-num">{{personData.userPraised}}</span>
					<span class="item-title">点赞</span>
				</view>
				<view class="bottom-item" @click="gotoConnern">
					<span class="item-num">{{personData.userFraction}}</span>
					<span class="item-title">积分</span>
				</view>
			</view>
		</view>

		<!-- 按钮 -->
		<view class="banner">
			<view class="banner-vip section" @click="gotoPrivilege">
				<view class="section-one">
					<span>我的特权</span>
					<uni-icons type="forward" size="20"></uni-icons>
				</view>
				<view class="section-two">
					<span>特权享更多权益·飞速脱单</span>
				</view>
				<view class="section-three">
					<span>开通特权</span>
				</view>
			</view>
			<view class="banner-info section" @click="gotoCheckPerson">
				<view class="section-one">
					<span>完善资料</span>
					<uni-icons type="forward" size="20"></uni-icons>
				</view>
				<view class="section-two">
					<span>完成度越高越容易推荐</span>
				</view>
				<view class="section-three">
					<span>20%</span>
				</view>
			</view>
		</view>

		<!-- 动态 -->
		<view class="active">
			<view class="active-title">
				<span>我的动态</span>
			</view>
			<view class="active-list" v-if="personActiveData.length !== 0">
				<view class="active-item" v-for="(item, i) in personActiveData" :key="i">
					<view class="item-left">
						<span class="day">11</span>
						<span class="mouth">07月</span>
					</view>
					<view class="item-right" @click="gotoComment">
						<p class="text">{{item.text}}</p>
						<view class="img" v-if="item.img">
							<img :src="item.img" alt="">
						</view>
						<view class="btns">
							<view class="btn-info-left">
								<uni-icons type="more" size="30"></uni-icons>
							</view>
							<view class="btn-info-right">
								<view class="like">
									<img class="icon" src="../../static/icon/active/like.png" alt="">
									<span class="num">{{item.likes}}</span>
								</view>
								<view class="comment">									
									<img class="icon" src="../../static/icon/active/comment.png" alt="">
									<span class="num" v-if="item.contents !== 0">{{item.contents}}</span>
									<span class="num" v-else>去评论</span>
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
				<span class="empty-icon">
					<uni-icons type="compose" size="60" color="#ccc">
					</uni-icons>
				</span>
				<span>发布一条动态，让大家看到你</span>
			</view>
		</view>

		<!-- 发表动态按钮 -->
		<view class="active-btn" @click="gotoShare">
			<uni-icons type="camera-filled" size="22" color="#fff"></uni-icons>
			<span>发动态</span>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 是否有角标
				value: 1,
				// 是否签到
				isFlag: false,
				// 个人信息
				personData: {
					username: '小懒猫',
					userId: '16515516',
					userImage: '../../static/images/user.jpg',
					userConcern: 0,
					userConcerned: 12,
					userPraised: 423, //点赞
					userFraction: 6165, //积分
				},
				personActiveData: [{
					publishDate: '2022-8-2 1:59:01',
					text: '今天的天空格外好看！',
					img: '../../static/images/sky.jpg',
					views: 231,
					likes: 1023,
					contents: 52,
					isAudit: false,//被举报
				}]
			}
		},
		methods: {
			// 复制id
			copyText() {
				uni.$showMsg("复制失败")
			},
			// 查看关注页面
			gotoConnern() {
				uni.$showMsg()
			},
			// 签到
			makeFlag() {
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

					img {
						width: 150rpx;
						height: 200rpx;
						border-radius: 30rpx;
						border: 4rpx solid #fff;
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
						span {
							margin-right: 10rpx;
						}
					}
				}
			}

			.top-right {
				display: flex;
				align-items: flex-end;

				span {	
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

				span {
					font-weight: bold;
					font-size: 15px;
				}
			}

			.section-two {
				span {
					color: #777;
					font-size: 12px;
				}
			}

			.section-three {
				margin-top: 20rpx;

				span {
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
					.img{
						
						img{
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

		span {
			margin-left: 10rpx;
		}
	}
</style>
