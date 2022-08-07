<template>
	<view class="page">
		<swiper class="swiper" :circular="circular" :vertical="true" @change="onSwiperChange">
			<swiper-item v-for="(item,index) in videoList" :key="item.id">
				<video @click="videoplay(index)" class="video" :id="item.id" :ref="item.id" :src="item.video_src"
					:controls="false" :loop="true" :show-center-play-btn="false" preload="auto"
					x5-playsinline="" playsinline="true" webkit-playsinline="true" x-webkit-airplay="allow"
					x5-video-player-type="h5" x5-video-player-fullscreen="" x5-video-orientation="portraint">
				</video>
				<view class="detail-box">
					<view class="name">222</view>
					<view class="detail">21343</view>
				</view>
			</swiper-item>
		</swiper>
		<cover-view>
			<cover-image :src="avatar_img" class="video-image"></cover-image>
			<cover-view class="video-love" @click="love()">
				<image src="../../static/images/home/6.png" class="img2"></image>
				<view class="video-num">{{love_num}}</view>
			</cover-view>
			<cover-view class="video-comm" @click="comm()">
					<image src="../../static/images/home/7.png"  class="img2"></image>
				<view class="video-num">{{comm_num}}</view>
			</cover-view>
			<cover-view class="video-redo" @click="collect()">
				<image src="../../static/images/home/8.png"  class="img2"></image>
				<view class="video-num">{{redo_num}}</view>
			</cover-view>
		</cover-view>
	</view>
</template>
<script>
	import recomment from "@/apis/recomment.js"
	export default {
		data() {
			return {
				avatar_img: '',
				love_num: 0,
				comm_num: 0,
				redo_num: 0,
				current_nav: 1,
				isactive: false,
				circular: true,
				videoList: [{
					video_src: "https://vd2.bdstatic.com/mda-nh4dtsitw2ykrs3m/sc/cae_h264/1659694517752184784/mda-nh4dtsitw2ykrs3m.mp4?v_from_s=hkapp-haokan-nanjing&auth_key=1659756981-0-0-6a6d7c11a8c80e0f13b756cc5e94729d&bcevod_channel=searchbox_feed&pd=1&cd=0&pt=3&logid=0381154670&vid=5182127965963672136&abtest=103525_1-103579_2-103742_2-103890_2&klogid=0381154670",
					id: "video0",
					play:false
				},{
					video_src: "https://vd2.bdstatic.com/mda-nh4dtsitw2ykrs3m/sc/cae_h264/1659694517752184784/mda-nh4dtsitw2ykrs3m.mp4?v_from_s=hkapp-haokan-nanjing&auth_key=1659756981-0-0-6a6d7c11a8c80e0f13b756cc5e94729d&bcevod_channel=searchbox_feed&pd=1&cd=0&pt=3&logid=0381154670&vid=5182127965963672136&abtest=103525_1-103579_2-103742_2-103890_2&klogid=0381154670",
					id: "video1",
					play:false
				}],
				videoDataList: [],
				_videoContextList: [],
				
				videoPage:{
					index:1,
					size:10
				}
			}
		},
		mounted() {
			this.getVideoList();
		},
		methods: {
			
			//获取视频秀
			getVideoList(page){
				let _that = this;
				recomment.getUserVedio(
				{
					page:this.videoPage.index,
					size:this.videoPage.size,
					userId:"1"
				}).then((res)=>{
					console.log(res)
					_that.init()
				}).catch(()=>{
					
				})
			},
			
			
			navCurrent(index) {
				this.current_nav = index
				index === 1 ? uni.showToast({
					title: '点击首页'
				}) : '';
				index === 2 ? uni.showToast({
					title: '点击朋友'
				}) : '';
				index === 3 ? uni.showToast({
					title: '点击消息'
				}) : '';
				index === 4 ? uni.showToast({
					title: '点击我的'
				}) : '';
			},
			navAdd() {
				uni.showToast({
					title: '点击+'
				});
			},
			init() {
				this._videoIndex = 0;
				this._videoContextList = [];
				for (var i = 0; i < this.videoList.length; i++) {
					this._videoContextList.push(uni.createVideoContext('video' + i, this));
				}
				this._videoDataIndex = 0;
				if(this._videoContextList.length>0){
					setTimeout(() => {
						this._videoContextList[this._videoIndex].play();
					}, 200);
				}

			},
			videoplay(index) {
				const id = this.videoList[index].id
				const video = uni.createVideoContext(id, this)
				if (this.videoList[index].play) {
					this._videoContextList[index].pause();
					this.videoList[index].play = false
				} else {
					this._videoContextList[index].play();
					this.videoList[index].play = true
				}


			},
			onSwiperChange(e) {
				this.isactive = false
				let currentIndex = e.detail.current;
				if (currentIndex === this._videoIndex) {
					return;
				}

				let isNext = false;
				if (currentIndex === 0 && this._videoIndex === this.videoList.length - 1) {
					isNext = true;
				} else if (currentIndex === this.videoList.length - 1 && this._videoIndex === 0) {
					isNext = false;
				} else if (currentIndex > this._videoIndex) {
					isNext = true;
				}

				if (isNext) {
					this._videoDataIndex++;
				} else {
					this._videoDataIndex--;
				}

				if (this._videoDataIndex < 0) {
					this._videoDataIndex = this.videoDataList.length - 1;
				} else if (this._videoDataIndex >= this.videoDataList.length) {
					this._videoDataIndex = 0;
				}

				this.circular = (this._videoDataIndex != 0);

				if (this._videoIndex >= 0) {
					this._videoContextList[this._videoIndex].pause();
					this._videoContextList[this._videoIndex].seek(0);
				}

				this._videoIndex = currentIndex;
				var id = this._videoContextList[this._videoIndex].id;

				setTimeout(() => {
					this.updateVideo(isNext);
				}, 200);
			},
			getNextIndex(isNext) {
				let index = this._videoIndex + (isNext ? 1 : -1);
				if (index < 0) {
					return this.videoList.length - 1;
				} else if (index >= this.videoList.length) {
					return 0;
				}
				return index;
			},
			getNextDataIndex(isNext) {
				let index = this._videoDataIndex + (isNext ? 1 : -1);
				if (index < 0) {
					return this.videoDataList.length - 1;
				} else if (index >= this.videoDataList.length) {
					return 0;
				}
				return index;
			},
			updateVideo(isNext) {
				setTimeout(() => {
					this._videoContextList[this._videoIndex].play();
				}, 200);
			},
			love(item) {
				item.love_num += 1
				this.isactive = !this.isactive
				uni.showToast({
					title: '点击点赞'
				});
			},
			comm() {
				uni.showToast({
					title: '点击评论'
				});
			},
			
			//收藏
			collect(item) {
				recomment.collect(
				{
					userId:id,
					type:"1",
					likedId:item.id
					
				}).then((res)=>{
					
				}).catch(()=>{
					
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	/* #ifndef APP-PLUS */
	page {
		width: 100%;
		min-height: 100%;
		display: flex;
	}

	/* #endif */

	.detail-box {
		position: absolute;
		position: absolute;
		bottom: 40rpx;
		z-index: 4;
		color: #ffffff;
		left: 20rpx;


	}

	.detail-box .name {
		font-size: 32rpx;
		font-family: PingFang SC-Bold, PingFang SC;
		font-weight: bold;
		color: #FFFFFF;
	}

	.detail-box .detail {
		width: 500rpx;
		font-size: 26rpx;
		font-family: PingFang SC-Regular, PingFang SC;
		font-weight: 400;
		color: #FFFFFF;
		margin-top: 10rpx;
	}

	.page {
		flex: 1;
		width: 750rpx;
	}

	.swiper {
		height: calc(100vh - 240rpx);
		flex: 1;
		background-color: #007AFF;
	}

	.swiper-item {
		flex: 1;
	}

	.video {
		position: relative;
		flex: 1;
		/* #ifndef APP-PLUS */
		width: 100%;
		/* #endif */
		height: calc(100vh - 240rpx);
		z-index: 0;
	}

	cover-view,
	cover-image {
		display: inline-block;
	}

	.video-image {
		position: fixed;
		bottom: 52vh;
		right: 10px;
		height: 60px;
		width: 60px;
		border-radius: 50%;
		border: 3px solid #fff;
		z-index: 100;
	}
	.img2{
		width: 90rpx;
		height: 80rpx;
		margin-bottom: 20rpx;
	}

	.video-love {
		position: fixed;
		bottom: 35vh;
		right: 56rpx;
		z-index: 100;
	}

	.video-num {
		text-align: center;
		position: relative;
		bottom: 5px;
		font-size: 14px;
		font-weight: bold;
		color: #FFFFFF;
		z-index: 100;
	}

	.video-comm {
		position: fixed;
		bottom: 22vh;
		right: 56rpx;
		z-index: 100;
	}

	.video-redo {
		position: fixed;
		bottom: 9vh;
		right: 56rpx;
		z-index: 100;
	}

	.nav_b {
		border-top: #888888 solid 1px;
		width: 100%;
		position: fixed;
		bottom: 0;
		left: 0;
		display: flex;
		height: 45px;
		line-height: 45px;
		color: #FFFFFF;
		font-size: 16px;
		font-weight: bold;
	}

	.nav_f {
		text-align: center;
		width: 20%;
	}

	.current_nav_f {
		position: fixed;
		bottom: 0;
		width: 10vw;
		margin: 0 5vw;
		border-bottom: #fff solid 3px;
	}
</style>
