<template>
	<view>
		<!-- tip -->
		<view class="tip">
			<p>各位用户，个人资料内容需要遵守相关法律法规和社区规定，需要审核后才能生效，请严格遵守规定填写资料</p>
		</view>
		<!-- images -->
		<view class="image-active">
			<view class="image-list">
				<view class="image-item" v-for="(item, i) in userImages" :key="i">
					<view class="box" v-if="item.show">
						<image :src="item.imagePath" alt=""></image>
						<span class="close">
							<uni-icons type="closeempty" size="16" color="darkorange"></uni-icons>
						</span>
						<span v-if="i === 0" class="host">
							<text>主头像</text>
						</span>
					</view>
					<view class="" v-else>
						<uni-icons type="auth" size="50" color="#eee"></uni-icons>
					</view>
				</view>

			</view>
			<view class="image-text">
				<text>图片秀仅限6张图片哦！</text>
			</view>
		</view>

		<view class="detail">

			<view class="section">
				<text class="label">头像</text>
				<view class="content" @click="chooseimage">
					<view v-if="!data.userImage" class="camera">
						<text>点击上传</text>
						<uni-icons class="icon-camera" type="camera-filled" size="30" color="#ffb795"></uni-icons>
					</view>
					<text v-else>已上传成功！</text>
				</view>
			</view>

			<view class="section">
				<text class="label">生日</text>
				<view class="content">
					<picker mode="date" :value="data.date" :start="startDate" @change="bindDateChange" :end="endDate">
						<view v-if="data.date" class="uni-input">{{data.date}}</view>
						<view v-else>请选择</view>
					</picker>
				</view>
			</view>

			<view class="section">
				<text class="label">身高</text>
				<view class="content">
					<input type="text" class="input" placeholder="请输入" v-model="data.height">
				</view>
			</view>
			
			<view class="section">
				<text class="label">体重</text>
				<view class="content">
					<input type="text" class="input" placeholder="请输入" v-model="data.weight">
				</view>
			</view>
			
			<view class="section">
				<text class="label">工作地区</text>
				<view class="content">
					<picker mode="multiSelector" @change="bindWorkPlaceChange" :range="area" :value="data.workplace">
						<view v-if="data.workplace" class="uni-input">
						{{area[0][data.workplace[0]]}} {{area[1][data.workplace[1]]}}
						</view>
						<view v-else>请选择</view>
					</picker>
				</view>
			</view>
			
			<view class="section">
				<text class="label">户籍情况</text>
				<view class="content">
					<picker mode="multiSelector" @change="bindDomicileeChange" :range="domicile" :value="data.domicile">
						<view v-if="data.domicile" class="uni-input">
						{{domicile[0][data.domicile[0]]}} {{domicile[1][data.domicile[1]]}}
						</view>
						<view v-else>请选择</view>
					</picker>
				</view>
			</view>
			
			<view class="section section-bottom">
				<text class="label">婚姻情况</text>
				<view class="content">
					<picker @change="bindMarraryChange" :range="marrary" :value="data.marrary">
						<view v-if="data.marrary" class="uni-input">
						{{marrary[data.marrary]}}
						</view>
						<view v-else>请选择</view>
					</picker>
				</view>
			</view>
			
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			const area = [['南岸区','巴南区'], ['红光大道','渝中达到']]
			const domicile = [['重庆','四川'],['南岸','渝北','渝中','巴南']]
			const marrary = ['请选择','未婚','二婚','已婚']
			return {
				area,
				domicile,
				marrary,
				data: {
					userImage: '',
					date: '',
					// age: 18,
					// sex: 1,
					height: '',
					weight: '',
					workplace: '',//工作地
					domicile: '',//户籍
					marrary: '',//婚姻情况
				},
				userImages: [{
					imagePath: '/static/images/home/img1.png',
					show: true
				}, {
					imagePath: '/static/images/home/img2.png',
					show: true
				}, {
					imagePath: '',
					show: false
				}, {
					imagePath: '',
					show: false
				}, {
					imagePath: '',
					show: false
				}, {
					imagePath: '',
					show: false
				}],
				progress: 0,//完成度
			};
		},
		watch: {
			data: {
				deep: true,
				handler(val) {
					let sum = 0, finish = 0				
					for(let key in val){
						sum++
						if(val[key] != '' && val[key] != 0) finish++
						console.log(val[key]);
					}
					this.$emit('changeProgress', (finish/sum).toFixed(2))
				}
			}
		},
		computed: {
			startDate() {
				return this.getDate('start');
			},
			endDate() {
				return this.getDate('end');
			}
		},
		methods: {
			// 选择婚姻情况
			bindMarraryChange(e){
				this.data.marrary = e.detail.value
			},
			// 选择户籍地区
			bindDomicileeChange(e){
				this.data.domicile = e.detail.value
			},
			// 选择工作地区
			bindWorkPlaceChange(e){
				this.data.workplace = e.detail.value
			},
			// 选择生日
			bindDateChange(e) {
				this.data.date = e.detail.value
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();

				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			// 拍照
			chooseimage() {
				var that = this;
				wx.showActionSheet({
					itemList: ['相册', '拍照'],
					itemColor: "darkorange",
					success: function(res) {
						if (!res.cancel) {
							if (res.tapIndex == 0) {
								that.chooseWxImage('album')
							} else if (res.tapIndex == 1) {
								that.chooseWxImage('camera')
							}
						}
					}
				})
			},
			// 调用微信图片
			chooseWxImage(type) {
				var that = this;
				wx.chooseImage({
					sizeType: ['original', 'compressed'],
					sourceType: [type],
					success: function(res) {
						console.log(res);
						// tempFilePath可以作为img标签的src属性显示图片
						that.data.userImage = res.tempFilePaths[0]
					}
				})
			},
			// 跳转到编辑详情页面
			gotoEditDetail(value) {
				uni.navigateTo({
					url: '/subpkg/editDetail/editDetail?id=' + value
				})
			},

		}
	}
</script>

<style lang="scss">
	.tip {
		color: #777;
		align-items: center;
		margin-bottom: 40rpx;
		font-size: 12px;
	}

	.image-active {
		.image-list {
			height: 600rpx;
			display: flex;
			flex-wrap: wrap;
			justify-content: space-between;

			.image-item {
				height: calc((100% - 40rpx) / 2);
				width: calc((100% - 80rpx) / 3);

				margin: 10rpx 0;
				border: 4rpx dotted #ccc;
				border-radius: 30rpx;
				display: flex;
				justify-content: center;
				align-items: center;

				.box {
					position: relative;
					width: 100%;
					height: 100%;

					image {
						width: 100%;
						height: 100%;
						border-radius: 30rpx;
					}

					.close {
						position: absolute;
						bottom: 20rpx;
						right: 20rpx;
						height: 50rpx;
						width: 50rpx;
						border-radius: 50%;
						display: flex;
						align-items: center;
						justify-content: center;
						background-color: #fff;
					}

					.host {
						position: absolute;
						top: 20rpx;
						left: 20rpx;
						width: 100rpx;
						height: 60rpx;
						border-radius: 20rpx;
						font-size: 12px;
						color: #fff;
						background-color: rgba(0, 0, 0, 0.3);
						display: flex;
						align-items: center;
						justify-content: center;
					}
				}
			}
		}

		.image-text {
			margin: 50rpx 0;
			text-align: center;
			color: #ddd;
		}
	}

	.detail {
		margin-top: 40rpx;

		.section {
			height: 100rpx;
			display: flex;
			justify-content: space-between;
			align-items: center;

			.label {
				font-weight: bold;
			}

			.content {
				color: #989898;

				.camera {
					border: 2rpx solid #ffb795;
					display: flex;
					align-items: center;
					height: 50rpx;
					padding: 0 20rpx;
					border-radius: 50rpx;

					text {
						color: #ffb795;
					}

					.icon-camera {
						margin-left: 20rpx;
					}
				}
				
				.input{
					width: 100rpx;
					font-size: 14px;
					text-align: right;
				}
			}

		}

		.section:not(:last-child) {
			border-bottom: 2rpx solid #eee;
		}
		
		.section-bottom{
			border-bottom: 6rpx solid #eee;
		}
	}
</style>
