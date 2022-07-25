<template>
	<view class="root">
		<!--  -->
		<view class="cards">
			<view class="card-vip">
				<view class="vip-innerbox">
					<view class="vip-top">
						<span><text>VIP</text> 会员</span>
					</view>
					<view class="vip-bottom">
						<img src="@/static/images/user.jpg" alt="">
						<span>暂未激活会员</span>
					</view>
				</view>
			</view>
		</view>

		<!-- 充值卡片 -->
		<view class="cardChoose">
			<view class="choose-item" v-for="(item, i) in vipPrice" :key="i">
				<span class="one"><span>{{item.time}}</span>个{{item.timeUnit}}</span>
				<span class="two">￥{{item.price}}/{{item.priceUnit}}</span>
				<span class="three">￥{{(item.time * item.price).toFixed(0)}}</span>
			</view>
		</view>

		<!-- 续费 -->
		<view class="choosePro">
			<checkbox :checked="checked" color="darkorange"/>
			<text>
				同意
				<text>《自动续费协议》</text>
				<text>最高可享三倍加速配对</text>
				，到期后以{{vipPrice[choosePrice].allprice}}/{{choosePrice==0?'年':choosePrice==1?'季':'月'}}自动续费，可随时取消
			</text>
		</view>
		
		<!-- 特权 -->
		<view class="privileges">
			<view class="privilege-title">
				<span>VIP特权</span>
			</view>
			<view class="privilege-list">
				<view class="privilege-item" v-for="(item, i) in 5" :key="i">
					<view class="item-left">					
						<uni-icons type="tune" size="30"></uni-icons>
					</view>
					<view class="item-right">
						<h3>查看谁喜欢你</h3>
						<p>可以随时随地查看对你心仪的朋友</p>
					</view>
				</view>
			</view>
		</view>
		
		<!-- 购买 -->
		<view class="buy">
			<view class="buy-top" v-if="isWechat">
				<view class="top-left">
					<img src="@/static/images/wechat.png" alt="">
					<span>微信</span>
				</view>
				<view class="top-right">
					<uni-icons type="forward" size="25" color="#777"></uni-icons>
				</view>
			</view>
			<view class="buy-top" v-else>
				<view class="top-left">
					<img src="static/images/pay.png" alt="">
					<span>微信</span>
				</view>
				<uni-icons type="forward" size="30"></uni-icons>
			</view>
			<view class="buy-btn">
				<button>
					<text class="price">￥{{vipPrice[choosePrice].allprice}}</text>
					<text>立即购买</text>
				</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 是否是微信
				isWechat: true,
				// 勾选同意协议
				checked: true,
				// 选择的vip价格
				choosePrice: 0,
				vipPrice: [{
					time: 12,
					timeUnit: '月',
					price: 7.92,
					priceUnit: '月',
					allprice: 95
				}, {
					time: 3,
					timeUnit: '月',
					price: 8.7,
					priceUnit: '月',
					allprice: 26
				}, {
					time: 1,
					timeUnit: '月',
					price: 9.9,
					priceUnit: '月',
					allprice: 10
				}],
				
			};
		}
	}
</script>

<style lang="scss" scoped>
	.root {
		padding: 0 20rpx;

		.cards {
			.card-vip {
				width: 700rpx;
				height: 220rpx;
				background-color: red;
				display: flex;
				justify-content: center;
				align-items: center;
				border-radius: 30rpx;

				.vip-innerbox {
					width: 98%;
					height: 92%;
					border: 4rpx solid #fc7aa1;
					box-sizing: border-box;
					border-radius: 30rpx;
					padding: 20rpx 0 20rpx 30rpx;
					display: flex;
					flex-direction: column;
					justify-content: space-between;

					.vip-top {
						font-size: 20px;
						color: #fff;
						font-family: 'kaiti';

						text {
							font-weight: bold;
							margin-right: 8rpx;
							letter-spacing: 4rpx;
						}
					}

					.vip-bottom {
						display: flex;
						align-items: center;
						color: #fbfcda;

						img {
							width: 60rpx;
							height: 60rpx;
							border-radius: 50%;
							margin-right: 10rpx;
						}
					}
				}
			}
		}

		.cardChoose {
			margin-top: 60rpx;
			display: flex;
			justify-content: space-around;

			.choose-item {
				width: 200rpx;
				border: 4rpx solid #eee;
				border-radius: 30rpx;
				color: #777;
				display: flex;
				flex-direction: column;
				align-items: center;
				padding: 20rpx 0 0 0;

				span {
					margin: 10rpx 0;

					span {
						font-size: 24px;
					}
				}

				.three {
					font-weight: bold;
				}
			}

			.choose-item:first-child {
				border: 4rpx solid red;
				color: red;
			}
		}
	
		.choosePro{
			margin-top: 40rpx;
			display: flex;
			align-items: center;
			padding: 0 20rpx;
			font-size: 12px;
			
			checkbox{
				transform: scale(0.8);			
			}
			
			text{
				color: #a8a8a8;
				
				text:first-child{
					color: #000;
				}
				
				text:nth-child(2){
					color: darkorange;
				}
			}
		}
	
		.privileges{
			margin-top: 40rpx;
			margin-bottom: 250rpx;
			
			.privilege-title{
				height: 60rpx;
				line-height: 60rpx;
				
				span{
					color: #777;
					font-weight: bold;
				}
			}
			
			.privilege-list{
				.privilege-item{
					height: 100rpx;
					display: flex;
					align-items: center;
					margin: 25rpx 0;
					
					.item-left{
						height: 70rpx;
						width: 70rpx;
						margin-right: 30rpx;
						padding: 10rpx;
						display: flex;
						justify-content: center;
						align-items: center;
						border: 4rpx solid #eee;
						border-radius: 20rpx;
					}
					
					.item-right{
						h3{
							font-size: 14px;
						}
						p{
							margin-top: 10rpx;
							color: #777;
							font-size: 12px;
						}
					}
				}
			}
		}
	
		.buy{
			position: fixed;
			bottom: 0;
			z-index: 6;
			width: 100%;
			background-color: #fff;
			padding: 0 20rpx 20rpx 20rpx;
			border-top: 4rpx solid #eee;
			box-sizing: border-box;
			
			.buy-top{
				height: 100rpx;
				display: flex;
				align-items: center;
				justify-content: space-between;
				font-size: 16px;
				
				.top-left{
					display: flex;
					align-items: center;
					
					img{
						height: 40rpx;
						width: 40rpx;
						margin-right: 30rpx;
					}
				}
				
				.top-right{
					margin-right: 20rpx;
				}
			}
			
			.buy-btn{
				
				
				button{
					width: 90%;
					background-color: darkorange;
					border-radius: 20rpx;
					
					text{
						color: #fff;
						font-weight: bold;
					}
					
					.price{
						margin-right: 20rpx;
					}
				}
			}
		}
	}
</style>
