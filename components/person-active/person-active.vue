<template>
	<view>
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
					<view class="item-right" @click="gotoComment(item.diaryId)">
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
	</view>
</template>

<script>
	import my from "@/apis/my"
	export default {
		name:"person-active",
		data() {
			return {
				personActiveData: [{
					diaryId: "1",
					publishDate: '2022-8-2 1:59:01',
					text: '今天的天空格外好看！',
					image: '../../static/images/sky.jpg',
					views: 231,
					likes: 1023,
					contents: 52,
					isAudit: false, //被举报
				}],
				paramsData: {
					userId: "1",
					page: 1,
					size: 5
				}
			};
		},
		mounted(){
			this.getData()
		},
		methods: {
			async getData(){
				// 相册
				try{
					const {data: res} = await my.getMyDiary(this.paramsData)
					console.log(res);
				}catch(err){
					console.log(err);
				}
			},
			// 去网评论页面
			gotoComment(diaryId) {
				uni.navigateTo({
					url: '/subpkg/comment/comment?diaryId=' + diaryId
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
/* 动态 */
	.active {
		margin-top: 20rpx;

		.active-title {
			font-weight: bold;
			margin-bottom: 10rpx;
		}

		.active-list {
			.active-item {
				display: flex;
				justify-content: space-between;
				margin-bottom: 20px;

				.item-left {
					padding-top: 30rpx;
					width: 100rpx;
					display: flex;
					flex-direction: column;
					align-items: center;
					font-weight: bold;
					color: #ddd;
					margin-right: 20rpx;


					.day {
						font-size: 22px;
					}

					.mouth {
						font-size: 16px;
					}
				}

				.item-right {
					width: calc(100% - 100rpx);

					.text {
						line-height: 80rpx;
						font-weight: bold;
					}

					.image {

						image {
							width: calc(100% - 50rpx);
							border-radius: 30rpx;
						}
					}

					.btns {
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

								.icon {
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

			.active-list-empty {
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
</style>