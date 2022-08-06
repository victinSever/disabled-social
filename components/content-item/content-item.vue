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
		<view class="item-right">
			<uni-icons type="heart" color="#a9a9a9" size="20"></uni-icons>
			<text>12</text>
		</view>
	</view>
</template>

<script>
	export default {
		name: "content-item",
		props: ['user', 'replyPic'],
		data() {
			return {

			};
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
