<template>
	<view class="item-container">
		<view class="item-left">
			<view class="user-name" style="color:#a9a9a9">
				<view class="travel-img" v-if="user.replyUserName">
					<img :src="replyPic" alt="">
				</view>

				<text>{{user.replyUserName ? user.commentUserName: user.commentatorName}}</text>
				<template v-if="user.replyUserName">
					<view class="travel-tag">
						<text class="tag-content" @click="showHuifu">回复</text>
						<uni-icons type="paperplane" size="14"></uni-icons>
					</view>
					<text>{{user.replyUserName}}</text>
				</template>

			</view>
			<view class="user-comment">
				{{user.replyUserName ? user.replyContent : user.commentBody}}
			</view>
			<view class="user-comment-time">
				<view class="time-detail">
					{{user.replyUserName ? user.createTime : user.commentCreateTime}}
				</view>
				<view class="btn-comment" style="color: #797a82;" @click="showHuifu">
					回复
				</view>
			</view>

		</view>
		<view class="item-right" @click="addLike ">
			<uni-icons type="heart" :style="alreadyItem.alreadyLike == 1 ? 'color:red' : (code == 1 ? 'color:red' : '')"
				size="20"></uni-icons>
			<text>{{user.likeAmount < 100 ? user.likeAmount : '99+'}}</text>
		</view>

		<uni-popup ref="huifu" background-color="#fff">
			<comment-replay @closeHuifu="closeHuifu"></comment-replay>
		</uni-popup>
	</view>
</template>

<script>
	import comment from '../../apis/comment.js'
	export default {
		name: "content-item",
		props: ['user', 'replyPic', 'alreadyItem'],
		data() {
			return {
				code: 0
			};
		},
		methods: {
			// 添加喜欢
			showHuifu() {
				this.$refs.huifu.open('bottom');
			},
			closeHuifu(info) {
				console.log(2);
				console.log(info);
				this.$refs.huifu.close();
			},
			addLike() {
				let _this = this
				this.alreadyItem.alreadyLike = false
				this.code == 1 ? uni.$showMsg('取消成功', 1000) : uni.$showMsg('点赞成功', 1000)
				if (this.user.replyUserName) {
					comment.addLikeComment({
						userId: 1,
						commentId: this.user.replyId
					}).then(res => {
						_this.user.likeAmount = res.data.map.total;
					})
					_this.code = _this.code == 0 ? 1 : 0
				} else {
					comment.addLike({
						userId: 1,
						commentId: this.user.commentId
					}).then(res => {
						_this.user.likeAmount = res.data.data.map.total;
					})
					_this.code = _this.code == 0 ? 1 : 0
				}
			},
			show() {
				this.$emit('showHuifu')
			}
		},
		mounted() {}
	}
</script>

<style lang="scss">
	.item-container {
		width: 100%;
		display: flex;
		margin-bottom: 30rpx;

		.item-left {
			width: 85%;

			.user-name {
				display: flex;
				height: 40rpx;

				.travel-img {
					height: 100%;
					width: 40rpx;
					margin-right: 20rpx;

					img {
						height: 100%;
						width: 100%;
						border-radius: 50%;
					}
				}

				.travel-tag {
					height: 100%;
					width: 120rpx;
					box-sizing: border-box;
					padding: 0 16rpx;

					.tag-content {
						box-sizing: border-box;
						padding: 2rpx;
						font-size: 4rpx;
						margin-right: 4rpx;
						background-color: #efefef;
						color: #8e8e8e;
					}



				}
			}

			.user-comment-time {
				display: flex;
				color: #a9a9a9;
				justify-content: space-between;
				box-sizing: border-box;
				padding-right: 20rpx;

				.time-detail {
					font-size: 24rpx;
				}
			}

			.user-comment-time {
				background-color: whitesmoke;

			}



		}

		.item-right {
			display: flex;
			flex-direction: column;
			height: 100%;
			width: 15%;
			color: #a9a9a9;

			// background-color: red;
			text {

				width: 100%;
				text-align: center;
			}
		}
	}
</style>
