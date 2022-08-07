<template>
	<view>
		<myProgress :data="data"></myProgress>
		
		<!-- tip -->
		<view class="tip">
			<p>各位用户，个人资料内容需要遵守相关法律法规和社区规定，需要审核后才能生效，请严格遵守规定填写资料</p>
		</view>
		
		<!-- images -->
		<view class="image-active">
			<view class="image-list">
				<view class="image-item" v-for="(item, i) in userImages" :key="i" @click="uploadActiveImage(item, i)">
					<view class="box" v-if="item.picPath">
						<image :src="item.picPath" mode="aspectFill" alt=""></image>
						<view class="close" @click="deleteActiveImage(item, i)">
							<uni-icons type="closeempty" size="16" color="darkorange"></uni-icons>
						</view>
						<view v-if="i === 0" class="host">
							<text>主头像</text>
						</view>
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


		<view class="section">
			<text class="label">头像</text>
			<view class="content" @click="chooseimage(2)">
				<view v-if="!data.imagePath" class="camera">
					<text>点击上传</text>
					<uni-icons class="icon-camera" type="camera-filled" size="30" color="#ffb795"></uni-icons>
				</view>
				<text v-else>已上传成功！</text>
			</view>
		</view>
		
		<view class="section">
			<text class="label">用户名</text>
			<view class="content">
				<text>{{data.personName}}</text>
			</view>
		</view>
		
		<view class="section">
			<text class="label">年龄</text>
			<view class="content">
				<text>{{data.age}}</text>
			</view>
		</view>
		
		<view class="section">
			<text class="label">性别</text>
			<view class="content">
				<text>{{data.sex}}</text>
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
		
		<view class="diliver">
			
		</view>

		<view class="section">
			<text class="label">工作地区</text>
			<view class="content">
				<picker mode="multiSelector" @change="bindworkAddrChange" :range="workAddr" :value="data.workAddr">
					<view v-if="data.workAddr" class="uni-input">
						{{workAddr[0][data.workAddr[0]]}} {{workAddr[1][data.workAddr[1]] || data.workAddr}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>

		<view class="section">
			<text class="label">户籍情况</text>
			<view class="content">
				<picker mode="multiSelector" @change="bindhouseholdAddreChange" :range="householdAddr" :value="data.householdAddr">
					<view v-if="data.householdAddr" class="uni-input">
						{{householdAddr[0][data.householdAddr[0]]}} {{householdAddr[1][data.householdAddr[1]] || data.householdAddr}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>

		<view class="section">
			<text class="label">婚姻情况</text>
			<view class="content">
				<picker @change="bindmaritalStatusChange" :range="maritalStatus" :value="data.maritalStatus">
					<view v-if="data.maritalStatus" class="uni-input">
						{{maritalStatus[data.maritalStatus] || data.maritalStatus }}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		

		<view class="section">
			<text class="label">学历</text>
			<view class="content">
				<picker @change="binddegreeChange" :range="degree" :value="data.degree">
					<view v-if="data.degree" class="uni-input">
						{{degree[data.degree] || data.degree}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>

		<view class="section">
			<text class="label">月收入</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.income">
			</view>
		</view>
		
		<view class="diliver">
			
		</view>

		<view class="section">
			<text class="label">职业</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.occupation">
			</view>
		</view>

		<view class="section">
			<text class="label">住房情况</text>
			<view class="content">
				<picker @change="bindhousingStatusChange" :range="housingStatus" :value="data.housingStatus">
					<view v-if="data.housingStatus" class="uni-input">
						{{housingStatus[data.housingStatus] || data.housingStatus}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>

		<view class="section">
			<text class="label">购车情况</text>
			<view class="content">
				<picker @change="bindcarStatusChange" :range="carStatus" :value="data.carStatus">
					<view v-if="data.carStatus" class="uni-input">
						{{carStatus[data.carStatus] || data.carStatus}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		

		<view class="section">
			<text class="label">期待结婚时间</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.expectedMarryTime">
			</view>
		</view>

		<view class="section">
			<text class="label">自我介绍</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.personIntro">
			</view>
		</view>
		
		<view class="section">
			<text class="label">爱情宣言</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.personSign">
			</view>
		</view>
		
		<view class="diliver">
			
		</view>
		
		<view class="section">
			<text class="label">手机</text>
			<view class="content">
				<text>{{data.phone}}</text>
			</view>
		</view>

		<view class="section">
			<text class="label">微信</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.wechat">
			</view>
		</view>

		<view class="section">
			<text class="label">微信二维码</text>
			<view class="content" @click="chooseimage(3)">
				<view v-if="!data.wechatCodeImagesPath" class="camera">
					<text>点击上传</text>
					<uni-icons class="icon-camera" type="camera-filled" size="30" color="#ffb795"></uni-icons>
				</view>
				<text v-else>已上传成功！</text>
			</view>
		</view>

		<view class="section">
			<text class="label">QQ</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.qq">
			</view>
		</view>

		<view class="section">
			<text class="label">邮箱</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.email">
			</view>
		</view>

		<view class="btn-next">
			<button @click="nextPage">下一步</button>
		</view>
	</view>
</template>

<script>
	import myProgress from "@/components/person-information/progress/progress.vue"
	import { mapState } from 'vuex'
	import areaInfo from "./area-data-min.js"
	export default {		
		data() {
			const workAddr = [
				['重庆城区', '重庆郊区'],
				['万州区', '涪陵区','渝中区',"大渡口区","江北区","沙坪坝区","九龙坡区","南岸区","北碚区","綦江区",
				"大足区","渝北区","巴南区","黔江区","长寿区","江津区","合川区","永川区","南川区","璧山区","铜梁区",
				"潼南区","荣昌区","开州区","梁平区","武隆区"]
			]
			const householdAddr = [
			]
			const maritalStatus = ['请选择', '未婚', '二婚', '已婚']
			const degree = ['请选择’,小学', '初中', '高中', '本科', '中专', '职高', '大专', '其他']
			const housingStatus = ['请选择', '已买房', '未买房']
			const carStatus = ['请选择', '已买车', '未买车']
			return {
				workAddr,
				householdAddr,
				maritalStatus,
				degree,
				housingStatus,
				carStatus,
				data: {},
				uploadPostion: 1, //上传类型，1动态，2头像，3微信二维码
				userImages: [],
			};
		},
		components: { 
			myProgress
		},
		computed: {
			...mapState('common', ['moreInfo','albumInfo']),
		},
		watch: {
			userImages: {
				deep: true,
				handler(val){
					this.$emit('changeImagesList', val)
				}
			},
			data: {
				deep: true,
				handler(val){
					this.$emit('changeBase', val)
				}
			}
		},
		created(){
			this.data = this.moreInfo.personBasicInfo
			this.userImages = this.albumInfo
			this.householdAddr = this.workAddr
		},
		methods: {
			// 上传动态图片
			uploadActiveImage(item, i){
				let that = this;
				uni.chooseImage({
					count: 6, //默认9
					sizeType: ['original', 'compressed'],
					sourceType: ['album'], 
					success: function(res) {				
						res.tempFilePaths.forEach((item) => {
							uni.uploadFile({
								url: that.vuex_uploadAction,
								filePath: item,
								name: 'file',
								header: {
									token: "" // 挂载请求头为用户的 token
								},
								success: function(arr) {
									let data = JSON.parse(arr.data);
									that.$set(that.userImages[i], 'picPath', data.data.url)
								}
							});
						})
				
				
					}
				});
			},
			// 删除动态的一张图片
			deleteActiveImage(item, i){
				this.$set( this.userImages[i], 'picPath', "")
			},
			nextPage(){
				this.$emit('nextPage', null)
			},
			//学历
			binddegreeChange(e) {
				this.data.degree = e.detail.value
			},
			//选择买房
			bindhousingStatusChange(e) {
				this.data.housingStatus = e.detail.value
			},
			// 选择买车
			bindcarStatusChange(e) {
				this.data.carStatus = e.detail.value
			},
			// 选择婚姻情况
			bindmaritalStatusChange(e) {
				this.data.maritalStatus = e.detail.value
			},
			// 选择户籍地区
			bindhouseholdAddreChange(e) {
				this.data.householdAddr = e.detail.value
			},
			// 选择工作地区
			bindworkAddrChange(e) {
				this.data.workAddr = e.detail.value
			},

			// 拍照
			chooseimage(uploadPostion) {
				var that = this;
				wx.showActionSheet({
					itemList: ['相册', '拍照'],
					itemColor: "darkorange",
					success: function(res) {
						if (!res.cancel) {
							if (res.tapIndex == 0) {
								that.chooseWxImage('album',uploadPostion)
							} else if (res.tapIndex == 1) {
								that.chooseWxImage('camera',uploadPostion)
							}
						}
					}
				})
			},
			// 调用微信图片
			chooseWxImage(type,uploadPostion) {
				var that = this;
				wx.chooseImage({
					sizeType: ['original', 'compressed'],
					sourceType: [type],
					success: function(res) {
						// tempFilePath可以作为img标签的src属性显示图片
						let url = res.tempFilePaths[0]
						if (uploadPostion === 2) {
							that.$set(that.data, 'imagePath', url)
						} else if (uploadPostion === 3) {
							that.data.wechatCodeImagesPath = url
							that.$set(that.data, 'wechatCodeImagesPath', url)
						}
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


	.section {
		height: 110rpx;
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

			.input {
				font-size: 14px;
				text-align: right;
			}
		}

	}

	.section:not(:last-child) {
		border-bottom: 2rpx solid #eee;
	}

	.diliver{
		width: 100%;
		height: 8rpx;
		background-color: #eee;
	}

	.btn-next {
		margin: 30rpx 0;

		button {
			border-radius: 20rpx;
			color: #fff;
			background: linear-gradient(#ffaa7f, #ff557f);
		}
	}
</style>
