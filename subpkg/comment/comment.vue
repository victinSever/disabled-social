<template>
	<view class="root">
		<mover></mover>
		<!-- 头部区域 -->
		<view class="comment-header">
			<view class="comment-header-left">
				<text @click="gotoBack">
					<uni-icons type="back" size="25"></uni-icons>
				</text>
				<view class="comment-header-left-info">
					<view class="info-left">
						<img :src="userData.imagePath" alt="" style="height: 40px;width: 40px;">
					</view>
					<view class="info-right">
						<h2>DAisss <text>√</text></h2>
						<p>
							<text>2天前</text>
							<text>·</text>
							<text>29km</text>
						</p>
					</view>
				</view>
			</view>

			<view class="comment-header-right">
				<text class="btnConcern">关注</text>
				<text v-if="false">已关注</text>
			</view>
		</view>

		<!-- 消息区 -->
		<view class="comment-personmessage">
			<view class="personmessage-main">
				<p>{{userData.activeData.text}}</p>
				<view class="personmessage-images">
					<img :src="item.imagePath" alt="" style="height: 100px; width: 120px;"
						v-for="(item, i) in userData.activeData.images" :key="i">
				</view>
			</view>
			<view class="personmessage-btn">
				<view class="btn-info-left">
					<uni-icons type="more" size="25" @click="toggle"></uni-icons>
				</view>
				<view class="btn-info-right">
					<view class="like">
						<uni-icons type="heart" size="25" color="#777"></uni-icons>
						<text>124</text>
					</view>
					<view class="comment">
						<uni-icons type="chatbubble" size="25" color="#777"></uni-icons>
						<text>打招呼</text>
					</view>
				</view>
			</view>
		</view>

		<!-- 评论区 -->
		<view class="comment-box" v-if="showComments">
			<comment-detail :data="data"></comment-detail>
		</view>
	</view>
</template>

<script>
	import comment from '../../apis/comment.js'
	export default {
		data() {
			return {
				showComments: false,
				diaryId: 0,
				data: [],
				userData: {
					imagePath: 'static/images/user.jpg',
					activeData: {
						text: '来给人给我一场轰轰烈烈的a恋爱吧',
						images: [{
							imagePath: 'static/images/content.jpg',
						}, {
							imagePath: 'static/images/content.jpg'
						}]
					},
				},
			};
		},
		methods: {
			gotoBack() {
				uni.navigateBack()
			},
			getComments(diaryId) {
				let _this = this;
				comment.getCommentList({
					diaryId,
					page: 1,
					size: 10
				}).then(res => {
					_this.data = res.data
					_this.showComments = true
				})
			}
		},
		onLoad(options) {
			this.getComments(options.diaryId)
		}
	}
</script>

<style lang="scss">
	.root {
		box-sizing: border-box;
		padding: 0 10px;
	}

	.comment-header {
		position: sticky;
		top: 70rpx;
		height: 100rpx;
		background-color: #fff;
		display: flex;
		justify-content: space-between;

		.comment-header-left {
			display: flex;
			align-items: center;

			.comment-header-left-info {
				margin-left: 10px;
				display: flex;
				align-items: center;
				height: 100rpx;

				.info-left {
					margin-right: 10px;
					height: 100%;
					display: flex;
					align-items: center;

					img {
						height: 50rpx;
						width: 50rpx;
						border-radius: 50%;
					}
				}

				.info-right {
					h2 {
						font-size: 14px;

						text {
							margin-left: 5px;
							display: inline-block;
							background: radial-gradient(yellow, orange);
							border-radius: 50%;
							width: 20px;
							height: 20px;
							text-align: center;
							color: red;
						}
					}

					p {
						font-size: 12px;
						color: #777;
					}
				}
			}
		}

		.comment-header-right {
			display: flex;
			align-items: center;

			text {
				display: inline-block;
				padding: 5px 10px;
				background-color: orange;
				border-radius: 10px;
				color: #fff;
				font-size: 14px;
			}
		}
	}


	.comment-personmessage {
		.personmessage-main {
			margin-top: 10px;

			p {
				font-size: 14px;
				word-wrap: break-word;
			}

			.personmessage-images {
				margin-top: 10px;

				img {
					border-radius: 10px;
					margin-right: 10px;
				}
			}
		}

		.personmessage-btn {
			margin-top: 10px;
			margin-bottom: 20px;
			display: flex;
			justify-content: space-between;

			.btn-info-left {
				display: flex;
				align-items: center;
			}

			.btn-info-right {
				display: flex;

				&>view {
					display: flex;
					align-items: center;

					text {
						font-size: 12px;
						margin-right: 5px;
						color: #a5a395;
					}
				}
			}
		}
	}

	.comment-box {}
</style>
