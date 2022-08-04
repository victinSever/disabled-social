<template>
	<view>
		<!-- 附近的社交盒子滑动 -->
		<scroll-view scroll-y="true" class="social-close" v-if="getInfo">
			<message-box :data="item" v-for="(item, i) in acitveData" :key="i" @openPopu="openPopu"></message-box>
		</scroll-view>
	</view>
</template>

<script>
	import around from '../../apis/around.js'
	export default {
		name: "social-close",
		data() {
			return {
				getInfo: false,
				acitveData: []
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
			},
			// 获取附近用户信息
			getAroundInfo() {
				let _this = this;
				uni.showLoading({
					title: '加载中'
				});
				around.getRecomment({
					page: 1,
					size: 10
				}).then(res => {
					_this.acitveData = res.data
					_this.getInfo = true;
					// console.log(_this.acitveData);
					uni.hideLoading()
				})
			}
		},
		mounted() {
			this.getAroundInfo()
		}

	}
</script>

<style lang="scss">
	// 单个信息盒子
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
