<template>
	<view class="item-box">
		<view class="left-img">
			<img :src="data.userData.imgpath" alt="">
		</view>
		<view class="right-box">
			<view class="title-info">
				<h2>{{data.userData.username}}</h2>
				<p>
					<span>{{data.lastTime}}{{data.lastUnit}}前</span>
					<span>·</span>
					<span>{{data.distance}}km</span>
				</p>
			</view>
			<view class="content-info" @click="gotoComment">
				<p>{{data.message}}</p>
				<view class="cont-info-img" v-for="(item, i) in data.activeImages" :key="i">
					<img :src="item.imgpath" alt="" model="widthFix">
				</view>
			</view>
			<view class="btn-info">
				<view class="btn-info-left">
					<uni-icons type="more" size="30" @click="openPopu"></uni-icons>
				</view>
				<view class="btn-info-right">
					<view class="like">
						<uni-icons type="heart" size="25"></uni-icons>
						<span>124</span>
					</view>
					<view class="comment" @click="gotoComment">
						<uni-icons type="chatbubble" size="25"></uni-icons>
						<span>45</span>
					</view>
				</view>
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
		}
		,methods: {
			openPopu() {
				this.$emit('openPopu',true)
			},
			gotoComment(){
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
				height: 40px;


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

					span:nth-child(2) {
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
				margin-bottom: 20px;
				display: flex;
				justify-content: space-between;

				.btn-info-right {
					display: flex;

					&>view {
						display: flex;
						align-items: center;

						span {
							font-size: 12px;
							margin-right: 10px;
							color: #a5a395;
						}
					}
				}
			}
		}
	}
</style>
