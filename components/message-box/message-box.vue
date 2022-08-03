<template>
	<view class="item-box">
		<view class="left-img">
			<img :src="data.userData.imgpath" alt="">
		</view>
		<view class="right-box">
			<view class="title-info">
				<h2 :style="{'margin-left':data.lastTime ? '0px' : '10px'}">{{data.userData.username}}</h2>
				<p v-if="data.lastTime">
					<text>{{data.lastTime}}{{data.lastUnit}}前</text>
					<text>·</text>
					<text>{{data.distance}}km</text>
				</p>
				<template v-else>
					<p style="font-size: 15px;margin: 5px 0;">
						{{data.message}}
					</p>
					<view class="advertise" style="position: absolute;top: 0; right: 20px;">
						<uni-tag  text="广告"></uni-tag>
					</view>
				</template>
			</view>
			<view class="content-info" @click="gotoComment">
				<p v-if="data.lastTime">{{data.message}}</p>
				<view class="cont-info-img" v-for="(item, i) in data.activeImages" :key="i">
					<img :src="item.imgpath" alt="" model="widthFix">
				</view>
			</view>
			<view class="btn-info">
				<template v-if="data.lastTime">
					<view class="btn-info-left">
						<uni-icons type="more-filled" color="#d0d2d4" size="20" @click="openPopu"></uni-icons>
					</view>
					<view class="btn-info-right">
						<view class="like">
							<uni-icons type="heart" size="25"></uni-icons>
							<text>124</text>
						</view>
						<view class="comment" @click="gotoComment">
							<uni-icons type="chatbubble" size="25"></uni-icons>
							<text>45</text>
						</view>
					</view>
				</template>
				<template v-else>
					<view class="btn-info-left" style="display: flex;color: #a5a5a5;font-size: 16px;">
						<uni-icons type="paperclip" color="#d0d2d4" size="20"></uni-icons>
						<text style="margin-left: 5px;">查看详情</text>
					</view>
					<view class="btn-info-right" style="margin-right: 15px;">
						<uni-icons type="more" color="#d0d2d4" size="20" @click="openPopu"></uni-icons>
					</view>
				</template>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: "message-box",
		props: ['data'],
		data() {
			return {

			};
		},
		methods: {
			openPopu() {
				this.$emit('openPopu', true)
			},
			gotoComment() {
				uni.navigateTo({
					url: '/subpkg/comment/comment'
				})
			}
		}
	}
</script>

<style lang="scss">
	.item-box {
		display: flex;
		background-color: #fff;
		position: relative;
		margin-bottom: 20px;

		&::after {
			content: '';
			position: absolute;
			bottom: 0;
			left: 50%;
			transform: translateX(-50%);
			width: 90%;
			height: 1px;
			background-color: #f0eef1;
		}

		.left-img {

			img {
				margin: 0 8px;
				width: 40px;
				height: 40px;
				border-radius: 50%;
			}
		}

		.right-box {
			width: calc(100vw - 56px);

			.title-info {
				// height: 140px;
				position: relative;

				h2 {
					font-size: 14px;
					height: 25px;
					line-height: 25px;
				}

				p {
					font-size: 12px;
					height: 15px;
					line-height: 15px;
					color: #a5a395;

					text:nth-child(2) {
						margin: 0 2px;
					}
				}


			}

			.content-info {
				margin-top: 10px;

				p {
					font-size: 14px;
					word-wrap: break-word;
				}

				.cont-info-img {
					margin-top: 10px;

					img {
						width: 130px;
						height: 130px;
						border-radius: 10px;
					}
				}
			}

			.btn-info {
				margin-top: 10px;
				margin-bottom: 10px;
				display: flex;
				justify-content: space-between;

				.btn-info-right {
					display: flex;

					&>view {
						display: flex;
						align-items: center;

						text {
							font-size: 12px;
							margin-right: 8px;
							color: #a5a395;
						}
					}


				}
			}
		}
	}
</style>
