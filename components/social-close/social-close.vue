<template>
	<view>
		
		
		<!-- 附近的社交盒子滑动 -->
		<scroll-view scroll-y="true" class="social-close">			
			<message-box :data="item" v-for="(item, i) in acitveData" :key="i" @openPopu="openPopu"></message-box>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		name: "social-close",
		data() {
			return {
				acitveData: [{
					userData: {
						username: "雨末微凉",
						imgpath: "../../static/images/user.jpg",
					},					
					message: "虽然手有点问题，但待人我是专心的",
					activeImages: [{
						imgpath: "../../static/images/content2.jpg",
					}],
					lastTime: 1,
					lastUnit: '分钟',
					distance: 2
				},
				{
					userData: {
						username: "XXX服务中心",
						imgpath: "../../static/images/user2.jpg",
					},					
					message: "国家认证中心",
					activeImages: [{
						imgpath: "../../static/images/content.jpg",
					}],
					
				},
				{
					userData: {
						username: "苏苏啊",
						imgpath: "../../static/images/user2.jpg",
					},					
					message: "快来找我呀",
					activeImages: [{
						imgpath: "../../static/images/content.jpg",
					}],
					lastTime: 5,
					lastUnit: '分钟',
					distance: 24
				},{
					userData: {
						username: "才子佳人",
						imgpath: "../../static/images/home/img1.png",
					},					
					message: "有没有文案的",
					activeImages: [{
						imgpath: "../../static/images/content.jpg",
					}],
					lastTime: 5,
					lastUnit: '分钟',
					distance: 24
				},{
					userData: {
						username: "苏苏啊",
						imgpath: "../../static/images/user2.jpg",
					},					
					message: "快来找我呀",
					activeImages: [{
						imgpath: "../../static/images/content.jpg",
					}],
					lastTime: 5,
					lastUnit: '分钟',
					distance: 24
				}]
			};
		},
		methods: {
			openPopu(){
				this.$emit('openPopu', true)
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
