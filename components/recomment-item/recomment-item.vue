<template>
	<view class="about" :style="{'border-bottom':title=='我的账号' ? 'none': '6rpx solid e6e6e64f' }">
		<view class="title">
			<text class="me">{{title}}</text>
		</view>
		<text v-if="content.length>0" class="content">
			{{content}}
		</text>
		<view class="content-item">
			<view v-if="title=='我的兴趣'" class="">
				<view class="all run">
					<view class="left">
						<i class="iconfont icon-paobuji"></i>
					</view>
					<view class="right">
						<text>郊游</text>
						<text>郊游23</text>
						<text>郊游</text>
						<text>郊13游</text>
						<text>郊3游</text>
						<text>郊游21</text>
						<text>郊游</text>
					</view>
				</view>
				<view class="all travel">
					<view class="left">
						<i class="iconfont icon-lvyou"></i>
					</view>
					<view class="right">
						<text>郊游</text>
						<text>郊游23</text>
						<text>郊游</text>
						<text>郊13游</text>
						<text>郊3游</text>
						<text>郊游21</text>
						<text>郊游</text>
					</view>
				</view>
				<view class="all popular">
					<view class="left">
						<i class="iconfont icon-yinle"></i>
					</view>
					<view class="right">
						<text>郊游</text>
						<text>郊游23</text>
						<text>郊游</text>
						<text>郊13游</text>
						<text>郊3游</text>
						<text>郊游21</text>
						<text>郊游</text>
					</view>
				</view>
				<view class="all move">
					<view class="left">
						<i class="iconfont icon-yingshi"></i>
					</view>
					<view class="right">
						<text>郊游</text>
						<text>郊游23</text>
						<text>郊游</text>
						<text>郊13游</text>
						<text>郊3游</text>
						<text>郊游21</text>
						<text>郊游</text>
					</view>
				</view>
				<view class="all food">
					<view class="left">
						<i class="iconfont icon-shiwu-"></i>
					</view>
					<view class="right">
						<text>郊游</text>
						<text>郊游23</text>
						<text>郊游</text>
						<text>郊13游</text>
						<text>郊3游</text>
						<text>郊游21</text>
						<text>郊游</text>
					</view>
				</view>
			</view>
			<view v-else-if="title=='我的标签'" class="">
				<view class="all run">
					<view class="left">
						<i class="iconfont icon-biaoqian"></i>
					</view>
					<view class="right">
						<text>郊游</text>
						<text>郊游23</text>
						<text>郊游</text>
						<text>郊13游</text>
						<text>郊3游</text>
						<text>郊游21</text>
						<text>郊游</text>
					</view>
				</view>
			</view>
			<view v-else-if="title=='关于我'" class="aboutMe">
				<text>郊游</text>
				<text>郊游23</text>
				<text>郊游</text>
				<text>郊13游</text>
				<text>郊3游</text>
				<text>郊游21</text>
				<text>郊游</text>
			</view>
			<view v-else class="account">
				<view>13123213</view>
				<view class="report">
					<text >~举报~</text>
					<text class="report-item">小月亮</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			title: {
				type: String,
				default: undefined
			},
			content: {
				type: String,
				default: ''
			}
		},
		data() {
			return {

			}
		}
	}
</script>

<style lang="scss">
	.about {
		width: 100%;
			padding-bottom: 30rpx;
			margin-bottom: 80rpx;
		// border-bottom: 6rpx solid e6e6e64f;
		.title {
			display: flex;

			align-items: center;
			justify-content: space-between;
			margin-bottom: 20rpx;

			.me {
				font-weight: 700;
				font-size: 32rpx;
				font-family: PingFang SC-Bold, PingFang SC;
				font-weight: bold;
				color: #000000;
			}
		}

		.content {
			width: 100%;
			color: #777777;
			display: inline-block;
			white-space: pre-wrap;
			height: auto;
			margin-bottom: 20rpx;
		}

		.content-item {
			display: flex;
			flex-direction: column;

			.all {
				display: flex;

				.left {
					width: 15%;
					i {
						font-size: 70rpx;
					}

					.icon-paobuji {
						font-size: 100rpx;
					}
				}

				.right {
					display: flex;
					flex-wrap: wrap;
					align-content: space-around;
					width: 85%;
					// background-color: blue;

					text {
						margin-bottom: 20rpx;
						margin-left: 30rpx;
						height: 60rpx;
						display: inline-block;
							border-radius: 30rpx;
						border: 4rpx solid #d8d8d833;
						padding: 0 20rpx;
						display: flex;
						align-items: center;
						justify-content: center;
					}
					text.select{
						border: 4rpx solid #F95F81;
					}
					
				}



				// justify-content: space-around;

			}

			.aboutMe {
				display: flex;
				flex-wrap: wrap;
				align-content: space-around;

				text {
					margin-bottom: 20rpx;
					margin-left: 30rpx;
					height: 60rpx;
					display: inline-block;
					border-radius: 12rpx;
					border: 4rpx solid #d8d8d833;
					border-radius: 30rpx;
					padding: 0 20rpx;
					display: flex;
					align-items: center;
					justify-content: center;
				}
				text.select{
					border: 4rpx solid #F95F81;
				}

			}

			.account {
				display: flex;
				flex-direction: column;
				align-items: center;
				justify-content: center;
				position: relative;
				color: #777777;
				&::before{
					content: '用户ID';
					position: absolute;
					top: 0;
					left: 0;
				}
				.report {
					margin-top: 20rpx;
					
					.report-item::before {
						content: ' ';
					}
					.report-item::after {
						content: ' ';
					}
				}
			}

		}
	}
</style>
