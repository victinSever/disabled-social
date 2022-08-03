<template>
	<view class="page">
		<swiper class="swiper" :circular="circular" :vertical="true" @change="onSwiperChange">
			<swiper-item v-for="(item,index) in videoList" :key="item.id">
				<video @click="videoplay(index)" class="video" :id="item.id" :ref="item.id" 
				:src="item.video_src"
				:controls="false"
				:loop="true" 
				:show-center-play-btn="false" 
				preload="auto" 
				object-fit='fill' 
				x5-playsinline="" 
				playsinline="true"
				webkit-playsinline="true" 
				x-webkit-airplay="allow" 
				x5-video-player-type="h5" 
				x5-video-player-fullscreen=""
				x5-video-orientation="portraint">
				</video>
				<view class="detail-box">
					<view class="name">222</view>
					<view class="detail">21343</view>
				</view>
			</swiper-item>
		</swiper>
		<cover-view>
			<cover-image  :src="avatar_img" class="video-image"></cover-image>
			<cover-view class="video-love" @click="love()">
				<uni-icons type="heart-filled" :color="isactive==true?'#f44336':'#ffffff'" size="55" />
				<view class="video-num">{{love_num}}</view>
			</cover-view>
			<cover-view class="video-comm" @click="comm">
				<uni-icons type="chat-filled" color="#ffffff" size="50" />
				<view class="video-num">{{comm_num}}</view>
			</cover-view>
		</cover-view>
      </view>
</template>
<script>
	export default {
		data() {
			return {
				avatar_img:'',
				love_num:0,
				comm_num:0,
				redo_num:0,
				current_nav:1,
				isactive: false,
				circular: true,
				videoList: [{}],
				videoDataList: []
			}
		},
		mounted() {
		 this.getVideoData();	
		},
		methods: {
			navCurrent(index){
				this.current_nav = index
				index === 1 ? uni.showToast({title: '点击首页'}) : '';
				index === 2 ? uni.showToast({title: '点击朋友'}) : '';
				index === 3 ? uni.showToast({title: '点击消息'}) : '';
				index === 4 ? uni.showToast({title: '点击我的'}) : '';
			},
			navAdd(){
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
				this.avatar_img = this.videoList[this._videoDataIndex].avatar_img
				this.love_num = this.videoList[this._videoDataIndex].love_num
				this.comm_num = this.videoList[this._videoDataIndex].comm_num
				this.redo_num = this.videoList[this._videoDataIndex].redo_num
			},
			videoplay(index) {
				const id = this._videoContextList[index].id
				const video = document.getElementById(`${id}`).firstChild.firstChild
				video.paused ?
					this._videoContextList[index].play() :
					this._videoContextList[index].pause()
			},
			getVideoData() {
				
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
				var id = this._videoContextList[this._videoIndex].id
				const video = document.getElementById(`${id}`)

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
				this.avatar_img = this.videoList[this._videoIndex].avatar_img
				this.love_num = this.videoList[this._videoIndex].love_num
				this.comm_num = this.videoList[this._videoIndex].comm_num
				this.redo_num = this.videoList[this._videoIndex].redo_num
				this.$set(this.videoList[this._videoIndex], 'src', this.videoDataList[this._videoDataIndex].src);
				this.$set(this.videoList[this.getNextIndex(isNext)], 'src', this.videoDataList[this.getNextDataIndex(isNext)].src);
        
				setTimeout(() => {
					// this._videoContextList[this._videoIndex].hideStatusBar(); //隐藏状态栏，仅在iOS全屏下有效
					this._videoContextList[this._videoIndex].play();
				}, 200);
				console.log("v:" + this._videoIndex + " d:" + this._videoDataIndex + "; next v:" + this.getNextIndex(
					isNext) + " next d:" + this.getNextDataIndex(isNext));
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
			redo() {
				uni.showToast({
					title: '点击分享'
				});
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

    .detail-box{
		position: absolute;
		    position: absolute;
		    bottom: 40rpx;
		    z-index: 4;
		    color: #ffffff;
		    left: 20rpx;
			

	}
	.detail-box .name{
		font-size: 32rpx;
		font-family: PingFang SC-Bold, PingFang SC;
		font-weight: bold;
		color: #FFFFFF;
	}
	.detail-box .detail{
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

	.video-love {
		position: fixed;
		bottom: 35vh;
		right: 15px;
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
		right: 37rpx;
		z-index: 100;
	}

	.video-redo {
		position: fixed;
		bottom: 19vh;
		right: 15px;
		z-index: 100;
	}
	
	.nav_b{
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
	.nav_f{
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
