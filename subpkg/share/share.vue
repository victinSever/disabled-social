<template>
	<view>
		<mover></mover>
		<view class="share-container">
			<view class="share-header">
				<view class="share-header-left">
					<uni-icons type="closeempty" size="25" @click="gotoBack"></uni-icons>
				</view>
				<view class="share-header-right" :style="'background-color: ' + (isSubmit ? 'darkorange': '#ddd')">
					<text class="btnSend" @click="gotoShare">发布</text>
					<uni-icons type="paperplane-filled" size="16" color="#fff"></uni-icons>
				</view>
			</view>


			<!-- 分享内容填写富文本 -->
			<view class="share-content">
				<view class="share-text">
					<textarea placeholder="分享我的时刻..." maxlength="235" v-model="content"></textarea>
				</view>
				<view class="share-picAndMedia">
					<view class="share-pic">
						<view class="pic-header">图片选择区域</view>
						<view class="pic-content">
							<view class="" v-for="(item,index) in userImages" :key="index">
								<image :src="item.imagePath" mode=""></image>
								<!-- 删除 -->
								<view class="pic-delte" @click="deleteImg(index)">
									<uni-icons type="closeempty" color="#ffaa7f" size="14"></uni-icons>
								</view>
							</view>

							<view class="add-pic" @click="chooseImg"
								v-if="userImages.length > 0 &&  userImages.length<6">
								<uni-transition custom-class="add-pic" mode-class="fade"
									:show="userImages.length > 0 &&  userImages.length<6">
									<view class="add-pic">
										<uni-icons type="plusempty" color="white" size="55"></uni-icons>
									</view>
								</uni-transition>
							</view>
						</view>

					</view>
					<view class="share-media">
						<view class="media-header">视频选择区域</view>
						<view class="media-content">
							<view class="" v-for="(item,index) in userMedia" :key="index">
								<video id="myVideo" :src="item.imagePath" @error="videoErrorCallback" controls></video>

								<!-- 删除 -->
								<view class="media-delte" @click="deleteMedia(index)">
									<uni-icons type="closeempty" color="#ffaa7f" size="14"></uni-icons>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>

			<!-- 信息添加 -->
			<view class="share-more">
				<view class="share-top">
					<view class="share-tag">
						<text>#加话题</text>
						<text>#我的夏日</text>
						<text>#残疾日</text>
					</view>
					<view class="share-public">
						<text>公开</text>
						<uni-icons type="right" size="14"></uni-icons>
					</view>
				</view>
				<view class="share-button">
					<view class="" @click="chooseImg">
						<image src="../../static/icon/active/picture.png" mode=""></image>
					</view>
					<view class="" @click="chooseVideo">
						<image src="../../static/icon/active/photo.png" mode=""></image>
					</view>
					<view class="">
						<image src="../../static/icon/active/voice.png" mode=""></image>
					</view>
					<view class="">
						<image src="../../static/icon/active/position.png" mode=""></image>
					</view>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import share from '../../apis/share.js'
	export default {
		data() {
			return {

				// 测试
				// 是否可发布
				isSubmit: false,
				isfocus: false,
				userImages: [],
				userMedia: [],
				useImageRes: [],
				useMediaRes: [],
				userImageServer: [],
				userMediaServer: [],
				content: ''
			}
		},
		mounted() {
			// #ifndef MP-ALIPAY
			this.videoContext = uni.createVideoContext('myVideo')
			// #endif
		},
		computed: {
			getImgPaths() {
				return this.useImageRes.map(res => res.data.data.url)
			},
			getMediaPaths() {
				return this.useMediaRes.map(res => res.data.data.url)
			},
		},
		methods: {

			videoErrorCallback: function(e) {
				uni.showModal({
					content: e.target.errMsg,
					showCancel: false
				})
			},
			begin() {
				console.log(1);
			},
			// 测试
			chooseImg() {
				let _that = this;
				uni.chooseImage({
					count: 6,
					sizeType: ['original', 'compressed'],
					sourceType: ['album'],
					success: function(res) {
						_that.userImages.push({
							imagePath: res.tempFilePaths[0]
						})
						_that.useImageRes.push(res)
					}
				});
			},
			chooseVideo() {
				let _that = this;
				uni.chooseVideo({
					maxDuration: 10,
					sourceType: ['album'],
					success: function(res) {
						_that.userMedia.push({
							imagePath: res.tempFilePath
						})
						_that.useMediaRes.push(res);
					}
				});

			},
			deleteImg(index) {
				this.userImages.splice(index, 1);
				this.useImageRes.splice(index, 1)
			},
			deleteMedia(index) {
				this.userMedia.splice(index, 1);
				this.useMediaRes.splice(index, 1)
			},
			gotoBack() {
				uni.navigateBack()
			},
			filePush() {
				let _that = this
				uni.showLoading({
					mask: true,
					title: '上传中...'
				})
				Promise.all([].concat.apply([], this.useImageRes.map(res => res.tempFilePaths.map((item) =>
					new Promise((resolve, reject) => {
						uni.uploadFile({
							url: _that.vuex_uploadAction,
							filePath: item,
							name: 'file',
							header: {
								token: "1"
							},
							success: function(arr) {
								let data = JSON.parse(arr.data);
								console.log(data.data.url);
								_that.userImageServer.push(data.data.url)
								resolve()
							},
							fail: function(err) {
								reject(err)
							}
						})
					})
				)))).then(res => {
					let uploadFile = ''
					return Promise.all(this.useMediaRes.map(res => new Promise((resolve, reject) => {
						uploadFile = res.tempFilePath;
						uni.uploadFile({
							url: _that.vuex_uploadAction,
							filePath: uploadFile,
							name: 'file',
							header: {
								token: ""
							},
							success: function(arr) {
								let data = JSON.parse(arr.data);
								_that.userMediaServer.push(data.data.url)
								resolve()
							},
							fail: function(err) {
								reject(err)
							}
						});
					})))
				}).then(res => {
					return share.postPerson({
						picPaths: Array.from(_that.userImageServer),
						videoPaths: Array.from(_that.userMediaServer),
					}, {
						diaryUserId: 1,
						diaryContent: _that.content,
						diaryTitle: 1,
						diaryStatus: 1,
						enableComment: 1,
						enableLook: 1,
					})
				}).then(res => {
					uni.hideLoading()
					uni.$showMsg('发布成功！')
					_that.gotoBack()
				}).catch(error => {
					uni.hideLoading()
					uni.$showMsg('发布失败！')
				})
				_that.userImageServer = []
				_that.userMediaServer = []
			},
			gotoShare() {
				if (!this.isSubmit) {
					return uni.$showMsg('你的编辑不完整！')
				}
				this.filePush()
			}
		},
		watch: {
			content() {
				if (this.content !== '') {
					this.isSubmit = true
				} else {
					this.isSubmit = false
				}
			}
		},
	}
</script>

<style lang="scss" scoped>
	.share-container {

		.share-header {
			width: 100%;
			height: 40px;
			// background-color: red;
			display: flex;
			box-sizing: border-box;
			padding: 0 20px;
			align-items: center;
			justify-content: space-between;

			.share-header-right {
				width: 60px;
				padding: 0 5px;
				height: 75%;
				border-radius: 10px;
				box-sizing: border-box;
				display: flex;
				align-items: center;
				justify-content: space-around;
				color: white;
			}
		}

		.share-content {
			// height: calc(100vh - 150px);
			width: 100%;
			display: flex;
			flex-direction: column;

			.share-text {
				height: 220rpx;
				width: 100%;
				box-sizing: border-box;
				padding: 10px;
				// background-color: yellow;

				textarea {
					width: 100%;
					height: 100%;
				}
			}

			.share-picAndMedia {
				// height: 805rpx;
				width: 100%;

				.share-pic {
					width: 100%;
					height: 580rpx;
					display: flex;
					flex-direction: column;

					.pic-header {
						width: 100%;
						box-sizing: border-box;
						padding-left: 10px;
						color: #808080;
						height: 20px;
						background-color: #dddddd;
					}

					.pic-content {
						width: 100%;
						flex: 1;
						box-sizing: border-box;
						// background-color: beige;
						display: flex;
						flex-wrap: wrap;
						padding-top: 15rpx;
						justify-content: flex-start;
						align-content: space-around;
						box-sizing: border-box;
						padding-left: 10px;

						view {
							width: 30%;
							height: 45%;
							position: relative;
							margin-right: 3%;

							image {
								width: 100%;
								height: 100%;
								border-radius: 20rpx;
							}

							.pic-delte {
								position: absolute;
								width: 40rpx;
								height: 40rpx;
								top: 5rpx;
								right: 0px;
								display: flex;
								align-items: center;
								justify-content: center;
								border-radius: 50%;
								border: 2rpx dotted #ccc;
								background-color: rgba(255, 255, 255, .7);
								// background-color: #ffaa00;
							}

							&.add-pic {
								background-color: #dddddd;
								border-radius: 15px;
								display: flex;
								align-items: center;
								justify-content: center;
							}

						}



					}

				}

				.share-media {
					width: 100%;
					margin-bottom: 70px;
					display: flex;
					flex-direction: column;

					.media-header {
						box-sizing: border-box;
						padding-left: 10px;
						width: 100%;
						height: 20px;
						color: #808080;
						background-color: #dddddd;
					}

					.media-content {
						width: 100%;
						// height: 400rpx;
						height: 250rpx;
						box-sizing: border-box;
						// background-color: beige;
						padding: 10px;

						view {
							width: 30%;
							height: 100%;
							position: relative;
							margin-right: 3%;

							video {
								width: 100%;
								height: 100%;
								border-radius: 20rpx;
							}

							.media-delte {
								position: absolute;
								width: 40rpx;
								height: 40rpx;
								top: 5rpx;
								right: 0px;
								display: flex;
								align-items: center;
								justify-content: center;
								border-radius: 50%;
								border: 2rpx dotted #ccc;
								background-color: rgba(255, 255, 255, .7);
								// background-color: #ffaa00;
							}
						}
					}
				}
			}
		}

		.share-more {
			// background-color: yellow;
			position: fixed;
			bottom: 0;
			width: 100%;
			height: 70px;

			.share-top {
				height: 50%;
				width: 100%;
				display: flex;
				align-items: center;
				justify-content: space-between;
				box-sizing: border-box;
				padding: 0 25px 0 10px;

				.share-tag {
					text {
						color: #E17B53;
						font-size: 12px;
						background-color: #fcdfd3;
						border-radius: 10px;
						padding: 3px 5px;
						margin-right: 10px;
					}
				}

				.share-public {
					display: flex;
					align-items: center;
					justify-content: center;
				}

				.share-public {
					font-weight: bold;
				}
			}

			.share-button {
				height: 50%;
				width: 100%;
				display: flex;
				align-items: center;
				justify-content: space-around;

				image {
					height: 30px;
					width: 30px;
				}
			}
		}

	}
</style>
