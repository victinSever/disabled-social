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
						<text class="tag-content">回复</text>
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
				<view class="btn-comment" style="color: #797a82;">
					回复
				</view>
			</view>

		</view>
		<view class="item-right" @click="addLike ">
			<uni-icons type="heart" :style="alreadyItem.alreadyLike == 1 ? 'color:red' : (code == 1 ? 'color:red' : '')"
				size="20"></uni-icons>
			<text>{{user.likeAmount < 100 ? user.likeAmount : '99+'}}</text>
		</view>
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
						_this.code = res.data.code
					})
				} else {
					comment.addLike({
						userId: 1,
						commentId: this.user.commentId
					}).then(res => {
						_this.user.likeAmount = res.data.map.total;
						_this.code = res.data.code
					})
				}
			}
		},
		mounted() {
		}
	}
</script>

<style lang="scss">
	.item-container {
		width: 100%;
		display: flex;
		margin-bottom: 10px;

		.item-left {
			width: 85%;

			.user-name {
				display: flex;
				height: 20px;

				.travel-img {
					height: 100%;
					width: 20px;
					margin-right: 10px;

					img {
						height: 100%;
						width: 100%;
						border-radius: 50%;
					}
				}

				.travel-tag {
					height: 100%;
					width: 60px;
					box-sizing: border-box;
					padding: 0 8px;

					.tag-content {
						box-sizing: border-box;
						padding: 1px;
						font-size: 2px;
						margin-right: 2px;
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
				padding-right: 10px;

				.time-detail {
					font-size: 12px;
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
