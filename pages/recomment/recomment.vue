<template>
	<view class="home-container">
		<mover></mover>
		<!-- 头部 -->
		<view :animation="animationHeader" class="home-header">
			<view class="header-left">
				<span @click="isImages = true">
					<uni-icons v-if="isImages" type="image-filled" size="30" color="darkorange"></uni-icons>
					<uni-icons v-else type="image" size="30" color="#777"></uni-icons>
					<text :style="isImages?'color: darkorange; font-weight: bold':'color: #777; font-weight: normal'">图片秀</text>
				</span>
				<span @click="isImages = false">
					<uni-icons v-if="isImages" type="videocam" size="30" color="#777"></uni-icons>
					<uni-icons v-else type="videocam-filled" size="30" color="darkorange"></uni-icons>
					<text :style="!isImages?'color: darkorange; font-weight: bold':'color: #777; font-weight: normal'">视频秀</text>
				</span>
			</view>
			<view class="header-right">
				<span @click="gotoSearch"><uni-icons type="search" size="30"></uni-icons></span>
				<span @click="openPopup"><uni-icons type="settings-filled" size="30"></uni-icons></span>
			</view>
		</view>
		
		<!-- 主题图片组件 -->
		<view class="home-body">
			<movable-area :animation="animationData" class="movable-area movable-area0">
				<movable-view id="1" :x="x" :y="y" out-of-bounds="true" class="movable-view" show-scrollbar
					direction="all" @change="onChange" :disabled="movedisable">
					<view class="home-swiper">
						<view class="swiper_detail" :style="{display:header==true? 'block' : 'none'}">
							<view class="mask">
							</view>
							<view class="userName">
								小懒猫
							</view>
							<view class="userDetail">
								<span>重庆九龙坡(10km)</span><span @click="checkDetail">↑</span>
							</view>
							<view class="userConstellation">
								<span>肢体残疾（三级）</span>
								<span>26岁</span>
							</view>
							<view class="control">
								<span class="back">
									<i class="iconfont icon-huitui"></i>
								</span>
								<span class="cancel">
									<i class="iconfont icon-quxiao"></i>
								</span>
								<span class="like">
									<i class="iconfont icon-xihuan"></i>
								</span>
								<span class="collection">
									<i class="iconfont icon-shoucang"></i>
								</span>
							</view>
						</view>
						<view class="back-detail" @click="backdetail" :style="{display:header==true? 'none' : 'block'}">
							←
						</view>
						<swiper class="swiper" indicator-dots="true" circular="true">
							<swiper-item>
								<view class="swiper-item">
									<image src="@/static/images/home/img4.png"
										mode="aspectFill" alt="">
								</view>
							</swiper-item>
							<swiper-item>
								<view class="swiper-item">
									<image src="@/static/images/home/img2.png"
										mode="aspectFill" alt="">
								</view>
							</swiper-item>
							<swiper-item>
								<view class="swiper-item">
									<image src="@/static/images/home/img3.png"
										mode="aspectFill" alt="">
								</view>
							</swiper-item>
							<swiper-item>
								<view class="swiper-item">
									<image src="@/static/images/home/img1.png"
										mode="aspectFill" alt="">
								</view>
							</swiper-item>
						</swiper>
						
						<view class="footer" v-show="footer">
							<!-- 姓名 -->
							<view class="footer-header">
								<view class="userName">
									<span>小懒猫</span>
									<span class="focus">关注</span>
								</view>
								<view class="address">
									<span>重庆巴南(3km)</span>
									<span class="point">·</span>
									<span>26分钟前活跃</span>
								</view>
							</view>
							
							<!-- 相册 -->
							<view class="photos">
								<view class="title">
									<span class="pic">动态相册</span>
									<span class="look">查看全部</span>
								</view>
								<view class="">
									<scroll-view class="scroll-view_H" scroll-x="true" scroll-left="120">
										<view id="demo1" class="scroll-view-item_H uni-bg-red">A</view>
										<view id="demo2" class="scroll-view-item_H uni-bg-green">B</view>
										<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
										<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
										<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
										<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
										<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
										<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
										<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
										<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
										<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
										<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
									</scroll-view>
								</view>
							</view>
							
							<!-- MV -->
							<view class="mv">
								<view class="title">
									<span class="pic">个人MV</span>
								</view>
								<view class="">
									暂无
								</view>
							</view>
							
							<homeItem title="关于我" content="美少女战士"></homeItem>
							<homeItem title="我的标签"></homeItem>
							<homeItem title="我的兴趣"></homeItem>
							<homeItem class="my-account" title="我的账号"></homeItem>
						</view>
					</view>
				</movable-view>
			</movable-area>
		</view>
		
		<!-- 设置按钮的弹框 -->
		<uni-popup ref="homeSetting">
			<recomment-setting @closePopup="closePopup"></recomment-setting>
		</uni-popup>
		
		<!-- 提示或者广告按钮的弹框 -->
		<uni-popup ref="recommentAdver" mask-background-color="rgba(0,0,0,0.2)">
			<recomment-adver @closeAdver="closeAdver"></recomment-adver>
		</uni-popup>
		
	</view>
</template>

<script>
	import homeItem from '@/components/recomment-item/recomment-item.vue'
	export default {
		components: {
			homeItem
		},
		data() {
			return {
				// 是否展示图片组件
				isImages: true,
				// 主题，默认白色
				theme: {
					backgroundColor: '#fff',
					backgroundImage: '',
				},
				movedisable: false,
				footer: false,
				animationDetail: {},
				animationHeader: {},
				header: true,
				animationData: {},
				nowid: 3,
				x: 0,
				y: 0,
				old: {
					x: 0,
					y: 0
				}
			}
		},
		mounted(){
			// if(!localStorage.getItem('isFirstAdver')){
				this.loadAdver()
			// }
		},
		methods: {
			// 关闭提示广告
			closeAdver(){
				this.$refs.recommentAdver.close()
				// localStorage.setItem('isFirstAdver', true)
			},
			// 弹出广告
			loadAdver(){
				let $this = this
				setTimeout(function(){
					$this.$refs.recommentAdver.open('center')
				}, 1000)
			},
			// 关闭筛选弹出层
			closePopup(){
				this.$refs.homeSetting.close()
			},
			// 弹出筛选弹出层
			openPopup(){
				this.$refs.homeSetting.open('bottom')			
			},
			
			// 筛选
			shaixuan(){
				uni.$showMsg('筛选功能未开放')
			},
			// 跳转到搜索页面
			gotoSearch(){
				uni.navigateTo({
					url: '/subpkg/search/search'
				})
			},
			
			// 详情页返回
			backdetail(){
				this.header = true;
				this.footer = false;
				this.movedisable = false;
				var animation = uni.createAnimation({
					duration: 1000,
					timingFunction: 'ease',
				})
				this.animation = animation
				this.animation.translateY(0).width(95 + "%").height(98 + "%").top(0 + "rpx").step()
				this.animationData = this.animation.export()
			},
			
			// 拖动的
			tap(e) {
				this.x = this.old.x
				this.y = this.old.y
				this.$nextTick(function() {
					this.x = 30
					this.y = 30
				})
			},
			
			// 进入详情页
			checkDetail() {
				this.footer = true;
				this.header = false;
				this.movedisable = true;
				this.animationData = {};
				var animation = uni.createAnimation({
					duration: 1000,
					timingFunction: 'ease',
				})
				this.animation = animation
				this.animation.translateY(-50).width(100 + 'vw').height(400).top(-10 + "rpx").step()
				this.animationData = this.animation.export()
			},
			
			clear() {
				this.animation.rotate(0).translateY(0).translateX(0).step()
			},
			
			// 四种动画方式
			moveOutlb() {
				this.animation.translateY(230).rotate(-10).translateX(-500).step()
				this.clear()
				this.animationData = this.animation.export()
			},
			
			moveOutlt() {
				this.animation.translateY(-230).rotate(-10).translateX(-500).step()
				this.clear()
				this.animationData = this.animation.export()
			},
			
			moveOutrb(){
				this.animation.translateY(230).rotate(10).translateX(500).step()
				this.clear()
				this.animationData = this.animation.export()
			},
			
			moveOutrt() {
				this.animation.translateY(-230).rotate(10).translateX(500).step()
				this.clear()
				this.animationData = this.animation.export()
			},
			
			// 控制动画的播放
			onChange(e) {
				this.animationData = {};
				let x = e.detail.x
				let y = e.detail.y
				let distance = 5 // 控制拖动长度
				
				if (Math.abs(x) >= distance && Math.abs(y) >= distance) {
					// this.moveOutlb((e.detail.x,e.detail.y))
					var animation = uni.createAnimation({
						duration: 500,
						timingFunction: 'ease-in-out',
					})
					this.animation = animation
					if (x < 0 && y > 0)
						this.moveOutlb()
					else if (x < 0 && y < 0)
						this.moveOutlt()
					else if (x > 0 && y < 0)
						this.moveOutrt()
					else
						this.moveOutrb()
				}

				this.nowid = e.target.id
			}
		},
	}
</script>

<style lang="scss" scoped>
	
	
	wx-swiper .wx-swiper-dot {
		position: relative;
		bottom: 900rpx;
		width: 100rpx;
		height: 3rpx;
		color: #fff;
	}

	.home-container {
		box-sizing: border-box;
		width: 100vw;

		.home-header {
			height: 100rpx;
			transition: all 1s;
			display: flex;
			justify-content: space-between;
			align-items: center;
			
			.header-left{
				margin-left: 20rpx;	
				display: flex;
				height: 100%;
				
				&>span{
					height: 100%;
					margin-right: 20rpx;
					font-size: 18px;					
					color: darkorange;				
					display: flex;
					align-items: center;
				}
				
				&>span:nth-child(1){
					font-weight: 600;
				}
			}
			
			.header-right{
				margin-right: 20rpx;
				
				span{
					margin-left: 30rpx;
				}
			}
		}

		.home-body {
			height: calc(100% - 170rpx);
			width: 100%;
			perspective: 800;
			display: flex;
			bottom: 0rpx;
			position: absolute;
			// top: 30rpx;
			// align-items: center;	
			justify-content: center;

			.movable-area,
				{
				width: 95%;
				height: 98%;
				// background-color: red;
				position: absolute;

				.movable-view {
					box-sizing: border-box;
					height: 100%;
					width: 100%;
				}

				.home-swiper {
					width: 100%;
					height: 100%;
					position: relative;

					.back-detail {
						position: absolute;
						top: 50rpx;
						left: 30rpx;
						z-index: 2;
						color: #fff;
						font-size: 20px;
						font-weight: bold;
						padding: 0 20rpx;
						border-radius: 10rpx;
						border: 1px solid #fff;
						background-color: rgba(255,255,255,0.2);
					}

					.mask {
						position: absolute;
						background: linear-gradient(to top, black 50%, transparent);
						left: 0;
						z-index: -1;
						bottom: 0;
						width: 100%;
						height: 80%;
						border-radius: 20rpx;
					}

					.swiper_detail {
						box-sizing: border-box;
						position: absolute;
						padding: 20rpx;
						left: 0;
						width: 100%;
						height: 35%;
						bottom: 0;
						z-index: 1;
						color: white;
						font-size: 30rpx;
						line-height: 70rpx;

						.userName {
							font-size: 50rpx;

						}

						.userDetail {
							display: flex;
							align-items: center;
							justify-content: space-between;
							
							span:last-child {
								color: #fff;
								font-size: 18px;
								font-weight: bold;
								padding: 0 30rpx;
								border-radius: 10rpx;
								border: 1px solid #fff;
								background-color: rgba(255,255,255,0.2);
							}
						}

						.userConstellation {
							padding: 10rpx;
							// background-color: white;
							span {
								padding: 6rpx 20rpx;
								border-radius: 10rpx;
								font-size: 12px;
								background-color: rgba(255,255,255,0.2);
								margin-right: 20rpx;
							}
						}

						.control {
							display: flex;
							align-items: center;
							justify-content: space-around;
							// background-color: yellow;

							.iconfont {
								font-size: 70rpx;
							}
						}
					}



					.swiper {
						width: 100%;
						height: 100%;
						background-color: yellow;


						.swiper-item {
							background-color: aliceblue;
							width: 100%;
							height: 100%;

							image {
								border-radius: 20rpx;
								width: 100%;
								height: 100%;
							}
						}
					}

					.footer {
						box-sizing: border-box;
						padding: 20rpx;

						.footer-header {
							margin-bottom: 60rpx;
							// background-color: bisque;

							.userName {
								display: flex;
								align-items: center;
								justify-content: space-between;
								font-size: 60rpx;

								.focus {
									background-color: orangered;
									padding: 15rpx;
									border-radius: 26rpx;
									color: white;
									font-size: 30rpx;
								}
							}

							.address {
								margin-top: 10rpx;
								color: #8e8e90;

								.point {
									&:before {
										content: " ";
									}

									&:after {
										content: " ";
									}
								}
							}
						}

						.photos, .mv {
							padding-bottom: 40rpx;
							margin-bottom: 20rpx;
							border-bottom: 6rpx solid #e6e6e6;

							.title {
								display: flex;
								align-items: center;
								justify-content: space-between;
								margin-bottom: 20rpx;

								// .about {
								// 	width: 100%;

								// 	.content {
								// 		width: 100%;
								// 		display: inline-block;
								// 		white-space: pre-wrap;
								// 		height: auto;
								// 	}
								// }

								.pic {
									font-weight: 700;
									font-size: 40rpx;
								}

								.look {
									color: #777777;
								}
							}

							.scroll-view_H {
								white-space: nowrap;
								width: 100%;
							}

							.scroll-view-item_H {

								display: inline-block;
								margin-right: 20rpx;
								width: 150rpx;
								background-color: red;
								height: 150rpx;
								border-radius: 20rpx;
								// line-height: 100rpx;
								text-align: center;
								font-size: 36rpx;
							}
						}
					
					}

				}
			}

			// .movable-area2 {
			// 	transform: translate3d(0rpx, 0rpx, 0rpx);
			// }

			// .movable-area1 {
			// 	transform: translate3d(0, -50rpx, -120rpx);
			// }

			// .movable-area0 {
			// 	transform: translate3d(0, -90rpx, -210rpx);
			// }
		}
	}

	
</style>
