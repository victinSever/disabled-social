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
				<view class="top-logo">
					<img :src="personData.userImage" alt="">
				</view>
				<view class="top-right">
					<view class="top-right-left">
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
					<view class="top-right-right">
						<span v-if="!isFlag" @click="makeFlag">签到</span>
						<span v-else class="isFlaged">已签到</span>
					</view>
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
			<view class="" v-if="personActiveData.length !== 0">
				
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
			<span>
				<uni-icons type="videocam-filled" size="25" color="#fff"></uni-icons>
			</span>
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
					userPraised: 423,//点赞
					userFraction: 6165,//积分
				},
				personActiveData: []
			}
		},
		methods: {
			// 复制id
			copyText(){
				uni.$showMsg("复制失败")
			},
			// 查看关注页面
			gotoConnern(){
				uni.$showMsg()
			},
			// 签到
			makeFlag(){
				uni.$showMsg('签到成功')				
				this.isFlag = true
				setTimeout(function(){
					uni.$showMsg('积分 + 5')
				},1000)
			},
			// 跳转到vip充值页
			gotoPrivilege(){
				uni.navigateTo({
					url:'/subpkg/privilege/privilege'
				})
			},
			// 跳转到评论页
			gotoShare(){
				uni.navigateTo({
					url:'/subpkg/share/share'
				})	
			},
			// 跳转到资料完善页面
			gotoCheckPerson(){
				uni.navigateTo({
					url:'/subpkg/checkPerson/checkPerson'
				})	
			}
		}
	}
</script>

<style lang='scss' scoped>
	.root{
		padding: 0 20rpx;
		background-color: #fcfcfc;
	}
	/* 头部 */
	.header{
		height: 100rpx;
		width: 100%;
		display: flex;
		justify-content: space-between;
		align-items: center;
		border-bottom: 1px solid #eee;
		
		.header-prove{
			display: flex;
			align-items: center;
			background-color: #eee;
			padding: 10rpx 20rpx;
			border-radius: 20rpx;
		}
	}
	
	/* 信息页 */
	.main{	
		margin: 20rpx 0 40rpx 0;
		
		.main-top{
			display: flex;
			justify-content: space-between;
			margin-bottom: 40rpx;
			
			.top-logo{
				width: 180rpx;
				height: 180rpx;
				box-sizing: border-box;
				
				img{
					width: 180rpx;
					height: 172rpx;
					border-radius: 50%;
					border: 4rpx solid #fff;
				}
			}
			
			.top-right{
				width: calc(100% - 220rpx);
				display: flex;
				justify-content: space-between;
				
				.top-right-left{
					h1,p{
						height: 90rpx;
						line-height: 90rpx;
					}
					
					h1{
						font-size: 20px;
					}
					
					p{
						span{
							margin-right: 10rpx;
						}
					}
				}
				
				.top-right-right{
					line-height: 180rpx;
					margin-right: 10rpx;
					
					span{
						padding: 10rpx 30rpx;
						height: 60rpx;
						border-radius: 60rpx;
						color: #fff;
						letter-spacing: 2rpx;
						background: linear-gradient(90deg, #ffaa7f , #ff557f);
					}
					
					.isFlaged{
						background: #ccc;
					}
				}
			}
		}
	
		.main-bottom{
			display: flex;
			justify-content: space-around;
			
			.bottom-item{
				width: calc((100% - 40rpx)/4);
				display: flex;
				align-items: center;
				flex-direction: column;
				background-color: #fff;
				padding: 10rpx 20rpx;
				border-radius: 20rpx;
				
				.item-num{
					font-weight: bold;
					font-size: 16px;
				}
				
				.item-title{
					color: #777;
				}
			}
		}
	}
	
	/* 推荐 */
	.banner{
		display: flex;
		justify-content: space-between;
		
		.section{
			width: calc((100% - 80rpx)/2);
			padding: 20rpx 0 20rpx 20rpx;
			background-color: #fff;
			border-radius: 20rpx;
			
			.section-one{
				display: flex;
				align-items: center;
				justify-content: space-between;
				
				span{
					font-weight: bold;
					font-size: 15px;
				}
			}
			
			.section-two{
				span{
					color: #777;
					font-size: 12px;
				}
			}
			
			.section-three{
				margin-top: 20rpx;
	
				span{
					color: #fff;
					height: 60rpx;
					line-height: 60rpx;
					border-radius: 30rpx;
					padding: 10rpx 50rpx;
					background: linear-gradient(90deg,  #ffaa7f , #ff557f);
				}
			}
		}
		
		.section:last-child{
			margin-right: 20rpx;
		}
		
		.banner-vip{
			.section-three{
				span{
					background: linear-gradient(90deg,  #ffaa7f , darkorange);
				}
			}
		}
	}		

	/* 动态 */
	.active{
		margin-top: 20rpx;
		.active-title{
			font-weight: bold;
		}
		.active-empty{
			height: 400rpx;
			display: flex;
			align-items: center;
			justify-content: center;
			flex-direction: column;
			color: #ccc;
			
			.empty-icon{
				margin-bottom: 50rpx;
			}
		}
	}

	/* 动态按钮 */
	.active-btn{
		position: fixed;
		bottom: 120rpx;
		right: 20rpx;
		background-color: darkorange;
		z-index: 5;
		font-size: 18px;
		color: #fff;
		display: flex;
		align-items: center;
		padding: 20rpx 50rpx;
		border-radius: 20rpx;
		
		span{
			display: flex;
			align-items: center;
		}
	}
</style>