<template>
	<view class="root">
		<mover></mover>
		<!-- 头部区域 -->
		<view class="comment-header">
			<view class="comment-header-left">
				<span @click="gotoBack">
					<uni-icons type="back" size="25" ></uni-icons>
				</span>
				<view class="comment-header-left-info">
					<view class="info-left">
						<img :src="userData.imagePath" alt="" style="height: 40px;width: 40px;">
					</view>
					<view class="info-right">
						<h2>DAisss <span>√</span></h2>
						<p>
							<span>2天前</span>
							<span>·</span>
							<span>29km</span>
						</p>
					</view>
				</view>
			</view>

			<view class="comment-header-right">
				<span class="btnConcern">关注</span>
				<span v-if="false">已关注</span>
			</view>
		</view>

		<!-- 消息区 -->
		<view class="comment-personmessage">
			<view class="personmessage-main">
				<p>{{userData.activeData.text}}</p>
				<view class="personmessage-images">
					<img :src="item.imagePath" alt="" style="height: 100px; width: 120px;" v-for="(item, i) in userData.activeData.images" :key="i">
				</view>
			</view>
			<view class="personmessage-btn">
				<view class="btn-info-left">
					<uni-icons type="more" size="30" @click="toggle"></uni-icons>
				</view>
				<view class="btn-info-right">
					<view class="like">
						<uni-icons type="heart" size="25" color="#777"></uni-icons>
						<span>124</span>
					</view>
					<view class="comment">
						<uni-icons type="chatbubble" size="25" color="#777"></uni-icons>
						<span>45</span>
					</view>
				</view>
			</view>
		</view>
	
		<hr>
		<!-- 评论区 -->
		<view class="comment-box">
			<h2>评论</h2>
			<view class="box-list">
				<view v-for="(item, i) in commentData" :key="i" class="box-item">				
					<view class="left-box">
						<img :src="item.imagePath" alt="">
					</view>
					<view class="right-box">
						<view class="right-box-left">
							<h3>{{item.userName}}</h3>
							<p class="text">{{item.comment}}</p>
							<p class="info">
								<span>{{item.lastTime}}{{item.timeUnit}}前</span>
								<span>回复</span>
							</p>
						</view>
						<view class="right-box-right">
							<uni-icons type="heart" size="20" color="#777"></uni-icons>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userData: {
					imagePath: 'static/images/user.jpg',
					activeData:{
						text: '来给人给我一场轰轰烈烈的恋爱吧',
						images: [{
							imagePath: 'static/images/content.jpg',						
						},{
							imagePath: 'static/images/content.jpg'
						}]
					},					
				},
				commentData: [
					{
						imagePath: 'static/images/user2.jpg',
						userName: '雏菊',
						comment: '哈哈',
						lastTime: 5,
						timeUnit: '分钟'
					},{
						imagePath: 'static/images/user.jpg',
						userName: '萨达',
						comment: '哈哈',
						lastTime: 2,
						timeUnit: '天'
					},{
						imagePath: 'static/images/user.jpg',
						userName: '大萨达',
						comment: '水电费收费',
						lastTime: 50,
						timeUnit: '分钟'
					},{
						imagePath: 'static/images/user2.jpg',
						userName: 'ad胜多负少的范德萨',
						comment: '哈哈',
						lastTime: 20,
						timeUnit: '分钟'
					},{
						imagePath: 'static/images/user.jpg',
						userName: '个梵蒂冈',
						comment: '师傅的说法三分大赛',
						lastTime: 6,
						timeUnit: '小时'
					},{
						imagePath: 'static/images/user2.jpg',
						userName: '个梵蒂冈',
						comment: '师傅的说法三分大赛',
						lastTime: 6,
						timeUnit: '小时'
					},{
						imagePath: 'static/images/user.jpg',
						userName: '个梵蒂冈',
						comment: '师傅的说法三分大赛',
						lastTime: 6,
						timeUnit: '小时'
					},{
						imagePath: 'static/images/user2.jpg',
						userName: '个梵蒂冈',
						comment: '师傅的说法三分大赛',
						lastTime: 6,
						timeUnit: '小时'
					},{
						imagePath: 'static/images/user.jpg',
						userName: '个梵蒂冈',
						comment: '师傅的说法三分大赛',
						lastTime: 6,
						timeUnit: '小时'
					},{
						imagePath: 'static/images/user2.jpg',
						userName: '个梵蒂冈',
						comment: '师傅的说法三分大赛',
						lastTime: 6,
						timeUnit: '小时'
					}
				]
			};
		},
		methods:{
			gotoBack(){
				uni.navigateBack()
			}
		}
	}
</script>

<style lang="scss">
	.root {
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

						span {
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

			span {
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
		.personmessage-main{
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
		
		.personmessage-btn{
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

	.comment-box {
		
		h2 {
			margin-bottom: 10px;
			font-size: 14px;
		}
		
		.box-list{
			
		}
		
		.box-item {
			margin: 25rpx 0;
			display: flex;
			justify-content: space-between;
			
			
			.left-box{
				width: 50px;
				display: flex;
				justify-content: center;

				img{
					width: 70rpx; height: 70rpx; border-radius: 50%;
				}
			}
			
			.right-box {
				width: calc(100vw - 70px);
				display: flex;
				justify-content: space-between;
				
				.right-box-left {
					h3{
						color: #777;	
						line-height: 15px;
						font-size: 15px;
					}
					
					.text{
						font-size: 13px;
						line-height: 25px;
					}
					
					.info{
						font-size: 11px;
						color: #a5a395;
						
						span{
							margin-right: 10px;
						}
					}
				}
				
				.right-box-right{
					margin-right: 10px;
				}
			}
		}
	}
</style>
