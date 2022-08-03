<template>
	<view>
		<swiper class="swiper" indicator-dots="true" circular="true">
			<swiper-item>
				<view class="swiper-item">
					<image mode="aspectFill" src="../../static/images/admin/admin1.jpg" alt="" />
				</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item">
					<image mode="aspectFill" src="../../static/images/admin/admin2.jpg" alt="" />
				</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item">
					<image mode="aspectFill" src="../../static/images/admin/admin3.jpg" alt="" />
				</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item">
					<image mode="aspectFill" src="../../static/images/admin/admin4.jpg" alt="" />
				</view>
			</swiper-item>
		</swiper>

		<view class="footer">
			<!-- 姓名 -->
			<view class="footer-header">
				<view class="userName">
					<text>小懒猫</text>
					<text class="focus" v-if="isTemplate">关注</text>
				</view>
				<view class="address">
					<text>重庆巴南(3km)</text>
					<text class="point">·</text>
					<text style="color: #F95F81;">26分钟前活跃</text>
				</view>
			</view>

			<!-- 相册 -->
			<view class="photos">
				<view class="title">
					<text class="pic">动态相册</text>
					<text class="look">查看全部</text>
				</view>
				<view class="">
					<scroll-view class="scroll-view_H" scroll-x="true" scroll-left="120">
						<view id="demo1" class="scroll-view-item_H uni-bg-red"></view>
					</scroll-view>
				</view>
			</view>

			<!-- MV -->
			<view class="mv">
				<view class="title">
					<text class="pic">个人MV</text>
				</view>
				<view class="">
					暂无
				</view>
			</view>

			<homeItem title="关于我" content="美少女战士"></homeItem>
			<homeItem title="我的标签"></homeItem>
			<homeItem title="我的兴趣"></homeItem>
		</view>
	</view>

</template>

<script>
	import homeItem from '@/components/recomment-item/recomment-item.vue'
	export default {
		name: "personage",
		components: {
			homeItem
		},
		props: {
			backShow: {
				type: Boolean,
				default: false
			},
			isTemplate: {
				type: Boolean,
				default: false
			},
			personageData: {
				type: Object,
				default: {}
			}
		},
		data() {
			return {};
		},
		methods: {
			backdetail() {
				uni.$emit("backdetail");
			},

		}
	}
</script>

<style lang="scss" scoped>
	.swiper {
		width: 100%;
		//       min-height: 800rpx;
		// max-height: 900rpx;
		height: 100%;
		background-color: #fff;

		.swiper-item {
			background-color: aliceblue;
			width: 100%;
			height: 100%;

			image {
				border-radius: 60rpx;
				width: 100%;
				height: 100%;
			}
		}
	}

	.footer {
		box-sizing: border-box;
		padding: 20rpx;

		.footer-header {
			margin-bottom: 60rpx;
			// background-color: bisque;

			.userName {
				display: flex;
				align-items: center;
				justify-content: space-between;
				font-size: 54rpx;

				.focus {
					background-color: #FB966E;
					padding: 12rpx 22rpx;
					border-radius: 26rpx;
					color: white;
					font-size: 26rpx;
				}
			}

			.address {
				margin-top: 16rpx;
				color: #000000;

				.point {
					&:before {
						content: " ";
					}

					&:after {
						content: " ";
					}
				}
			}
		}

		.photos,
		.mv {
			padding-bottom: 40rpx;
			margin-bottom: 20rpx;
			border-bottom: 4rpx solid #e6e6e64f;

			.title {
				display: flex;
				align-items: center;
				justify-content: space-between;
				margin-bottom: 28rpx;


				.pic {
					font-weight: 700;
					font-size: 32rpx;
				}

				.look {
					color: #D8D8D8;
				}
			}

			.scroll-view_H {
				white-space: nowrap;
				width: 100%;
			}

			.scroll-view-item_H {

				display: inline-block;
				margin-right: 20rpx;
				width: 164rpx;
				height: 164rpx;
				border-radius: 14rpx;
				text-align: center;
				font-size: 36rpx;
			}
		}

	}
</style>
