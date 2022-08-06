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
					<textarea placeholder="分享我的时刻..." v-model="value"></textarea>
				</view>
				<view class="share-pic">
					<!-- <uni-section title="只选择图片" type="line"> -->
					<view class="example-body">
						<!-- <uni-file-picker limit="9" title="最多选择9张图片"></uni-file-picker> -->
					</view>
					<!-- </uni-section> -->
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
	export default {
		data() {
			return {
				value: '',
				// 是否可发布
				isSubmit: false,
				isfocus: false,
				url: ''
			}
		},
		methods: {
			chooseImg() {
                let _that=this;
				uni.chooseImage({
					count: 6, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: function(res) {
                        
                        res.tempFilePaths.forEach((item)=>{
                            uni.uploadFile({
                            	// 需要上传的地址
                            	url: _that.vuex_uploadAction,
                            	// filePath  需要上传的文件
                            	filePath: item,
                            	name: 'file',
                            	header: {
                            		token: ""		// 挂载请求头为用户的 token
                            	},
                            	success:function (arr) {
                            		let data = JSON.parse(arr.data);
                                    
                                    // data就是上传的成功的路径
                            	}
                            });
                        })

                        
					}
				});
			},
			chooseVideo(){
				uni.showLoading({
					mask: true,
					title: '上传中...'
				})
				// uploadFile 存储需要上传的文件
				let uploadFile = '';
				let _that = this;
				// 1.选择要上传的视频
	           uni.chooseVideo({
					maxDuration: 10,		// 拍摄视频最长拍摄时间，单位秒。最长支持 60 秒。
					sourceType: ['album'],		// album 从相册选视频，camera 使用相机拍摄，默认为：['album', 'camera']
					success:function (res) {
						uploadFile = res.tempFilePath;
						// console.log(uploadFile);
						// 2.上传所选视频到服务器
						uni.uploadFile({
							// 需要上传的地址
							url: _that.vuex_uploadAction,
							// filePath  需要上传的文件
							filePath: uploadFile,
							name: 'file',
							header: {
								token: ""		// 挂载请求头为用户的 token
							},
							success:function (arr) {
								let data = JSON.parse(arr.data);
                                
                                // data就是上传的成功的路径
							}
						});

					}
				});

			},
			goBack() {
				uni.navigateBack()
			},
			gotoShare() {
				if (!this.isSubmit) return uni.$showMsg('你的编辑不完整！')
				this.gotoBack()
				uni.$showMsg('发布成功！')
			}
		},
		watch: {
			value() {
				if (this.value !== '') this.isSubmit = true
				else this.isSubmit = false
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
			height: calc(100vh - 150px);
			width: 100%;
			display: flex;
			flex-direction: column;

			.share-text {
				flex: 1;
				width: 100%;
				background-color: yellow;
			}

			.share-pic {
				flex: 1;
				width: 100%;
				background-color: #fcdfd3;
			}

		}

		.share-more {
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
