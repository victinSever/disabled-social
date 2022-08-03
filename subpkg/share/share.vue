<template>
	<view>
		<mover></mover>
		<!-- 头部按钮 -->
		<view class="share-header">
			<view class="share-header-left">
				<uni-icons type="closeempty" size="25" @click="gotoBack"></uni-icons>
			</view>
			<view class="share-header-right" :style="'background-color: ' + (isSubmit ? 'darkorange': '#ddd')" >
				<span class="btnSend" @click="gotoShare">发布</span>
				<uni-icons type="forward" size="20" color="#fff"></uni-icons>
			</view>
		</view>

		<!-- 分享内容填写富文本 -->
		<scroll-view scroll-y="true" >
			<view class="share-text">
				<textarea placeholder="分享我的时刻..." v-model="value"></textarea>
			</view>
		</scroll-view>

		<!-- 信息添加 -->
		<view class="share-more">
			<view class="share-top">
				<view class="share-tag">
					<span>#加话题</span>
					<span>#我的夏日</span>
					<span>#残疾日</span>
				</view>
				<view class="share-public">
						<span>公开</span>
						<uni-icons type="right" size="25"></uni-icons>
				</view>
			</view>
			<view class="share-button">
					<span>
						<img src="../../static/icon/active/picture.png" alt="">
						<!-- <uni-icons type="image" size="30"></uni-icons> -->
					</span>
					<span>
						<img src="../../static/icon/active/photo.png" alt="">
						<!-- <uni-icons type="camera-filled" size="30"></uni-icons> -->
					</span>
					<span>
						<img src="../../static/icon/active/voice.png" alt="">
						<!-- <uni-icons type="mic-filled" size="30"></uni-icons> -->
					</span>
					<span>
						<img src="../../static/icon/active/position.png" alt="">
						<!-- <uni-icons type="map-pin-ellipse" size="30"></uni-icons> -->
					</span>				
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				value: '',
				// 是否可发布
				isSubmit: false,
				isfocus: false
			};
		},
		watch:{
			value(){
				if(this.value !== '') this.isSubmit = true
				else this.isSubmit = false
			}
		},
		methods: {
			gotoBack() {
				uni.navigateBack()
			},
			gotoShare() {
				if(!this.isSubmit) return uni.$showMsg('你的编辑不完整！')
				this.gotoBack()
				uni.$showMsg('发布成功！')
			}
		},
		onLoad(){
			this.isfocus = true
		}
	}
</script>

<style lang="scss" scoped>
	.share-header {
		position: sticky;
		top: 70rpx;
		z-index: 5;
		height: 50px;
		display: flex;
		align-items: center;
		justify-content: space-between;
		background-color: #fff;
		padding: 0 30rpx;
		

		.share-header-left {

			span {
				margin-right: 15px;
				color: gray;
				font-size: 18px;
			}

		}

		.share-header-right {
			display: flex;
			align-items: center;
			height: 50rpx;
			border-radius: 20rpx;
			padding: 10rpx 15rpx 10rpx 30rpx;	
			border-radius: 50rpx;

			.btnSend {
				display: inline-block;
				text-align: center;
				font-size: 16px;			
				color: #fff;								
			}
		}
	}

	.share-text {
		padding: 30rpx;

		textarea {
			width: 100%;
			line-height: 60rpx;
			min-height: calc(100vh - 100rpx);
		}
	}

	.share-more {
		background-color: #fff;
		position: sticky;
		bottom: 0;
		z-index: 2;
		padding: 0 30rpx;

		.share-top {
			display: flex;
			justify-content: space-between;
			
			.share-tag {
				height: 60rpx;
				margin-left: 10px;
			
				span {
					font-size: 13px;
					padding: 10rpx 20rpx;
					margin-right: 20rpx;
					background-color: #fddfd0;
					color: #d5836a;
					border-radius: 30rpx;		
				}
			}
			
			.share-public{
				display: flex;
				align-items: center;
			}
		}
		
		.share-button {		
			padding: 0 10px;
			display: flex;
			height: 100rpx;
			justify-content: space-between;
			align-items: center;
			
			img{
				height: 60rpx;
			}
		}
	}
</style>
