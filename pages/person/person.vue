<template>
	<view class="root">
		<mover></mover>
		<!-- 顶部设置 -->
		<!-- 				<view class="header">
			<uni-badge size="normal" :offset="[3, 3]" :is-dot="true" :text="value" absolute="rightTop" type="error">
				<view class="header-prove" @click="gotoConnern">
					<uni-icons type="checkbox-filled" size="25"></uni-icons>
					<text>认证中心</text>
					<uni-icons type="forward" size="18"></uni-icons>
				</view>
			</uni-badge>
			<uni-badge size="normal" :offset="[3, 3]" :is-dot="true" :text="value" absolute="rightTop" type="error">
				<view class="header-buttons" @click="gotoConnern">
					<uni-icons type="bars" size="25"></uni-icons>
				</view>
			</uni-badge>
		</view> -->
		<view class="head">

		</view>

		<!-- 主信息部分 -->
		<view class="main">
			<view class="main-top">

				<div class="top-left">
					<view class="top-logo">
						<image v-if="baseData.headPicPath" :src="baseData.headPicPath" alt="" mode="aspectFill"/>
						<view class="empty-image" v-else>
							<uni-icons type="person-filled" size="80" color="#eee"></uni-icons>
						</view>
					</view>
					<view class="top-left-right">
						<h1>
							<text>{{baseData.username}}</text>
						</h1>
						<p>
							<text>用户ID：{{baseData.personId}}</text>
							<uni-icons type="compose" size="16" @click="copyText(baseData.personId)"></uni-icons>
						</p>

						<p>
							<text>我的简历</text>
							<uni-icons type="compose" size="16" @click="gotoResume"></uni-icons>
						</p>
					</view>
				</div>
				<view class="top-right">
					<text v-if="!baseData.signIn" @click="makeSignUp">签到</text>
					<text v-else class="isFlaged">已签到</text>
				</view>
			</view>
			<view class="main-bottom">
				<view class="bottom-item" @click="gotoBillBoard(1)">
					<text class="item-num">{{baseData.attentionCount}}</text>
					<text class="item-title">关注</text>
				</view>
				<view class="bottom-item" @click="gotoBillBoard(2)">
					<text class="item-num">{{baseData.fanCount}}</text>
					<text class="item-title">粉丝</text>
				</view>
				<view class="bottom-item" @click="openLikes">
					<text class="item-num">{{baseData.like}}</text>
					<text class="item-title">点赞</text>
				</view>
				<view class="bottom-item" @click="openSorts">
					<text class="item-num">{{baseData.sorts}}</text>
					<text class="item-title">积分</text>
				</view>
			</view>
		</view>

		<!-- 按钮 -->
		<view class="banner">
			<view class="banner-vip section" @click="gotoPrivilege">
				<view class="section-one">
					<text>我的特权</text>
					<uni-icons type="forward" size="20"></uni-icons>
				</view>
				<view class="section-two">
					<text>特权享更多权益·飞速脱单</text>
				</view>
				<view class="section-three">
					<text>开通特权</text>
				</view>
			</view>
			<view class="banner-info section" @click="gotoCheckPerson">
				<view class="section-one">
					<text>完善资料</text>
					<uni-icons type="forward" size="20"></uni-icons>
				</view>
				<view class="section-two">
					<text>完成度越高越容易推荐</text>
				</view>
				<view class="section-three">
					<text>{{InfoRate}}</text>
				</view>
			</view>
		</view>

		<!-- 动态 -->
		<person-active :personActiveData="personActiveData"></person-active>

		<!-- 发表动态按钮 -->
		<view class="active-btn" @click="gotoShare">
			<uni-icons type="camera-filled" size="22" color="#fff"></uni-icons>
			<text>发动态</text>
		</view>

	</view>
</template>

<script>
	import my from '@/apis/my.js'
	import { getDay, getMonth } from "@/apis/tools"
	import {
		returnRate, formdataify
	} from '@/apis/tools.js'
	import {
		mapMutations
	} from 'vuex'
	
	export default {
		data() {
			return {
				// 是否有角标
				value: 1,
				// 个人信息
				baseData: {
					username: 'xxx',
					personId: '',
					headPicPath: '',
					attentionCount: 0,
					fanCount: 0,
					like: 0, //点赞
					sorts: 0, //积分
				},
				moreData: {},
				// 动态的数据
				personActiveData: [],
				isLoading: false,//节流器
				paramsData: {
					userId: "1",
					page: 1,
					size: 5
				}
				
			}
		},
		mounted() {
			this.getData()
			// this.getActiveData()
		},
		computed: {
			// 资料完善比率
			InfoRate() {
				const obj = {
					...this.moreData.personBasicInfo,
					...this.moreData.personDetailInfo,
					...this.moreData.requirement
				}
				return (returnRate(obj) * 100) + '%'
			}
		},
		// 下拉触底
		onReachBottom(){
			// 节流器
			if(!this.isLoading){
				this.paramsData.page++
				this.getActiveData()
			}					
		},
		methods: {
			...mapMutations('common', ['setBaseInfo', 'setMoreInfo', 'setAlbumInfo']),
			
			// 获取动态信息
			async getActiveData(){
				uni.showLoading({title: '动态加载中',mask:true});
				this.isLoading = true
				const {data: res} = await my.getMyDiary(this.paramsData)
				this.isLoading = false
				setTimeout(function () {uni.hideLoading();}, 100);
				// 根据动态发布时间获取日月
				let arr = res.map(item => {
					item.day = getDay(item.diary.createTime)
					item.month = getMonth(item.diary.createTime)
					return item
				})
				// 合并动态数组
				this.personActiveData = [
					...this.personActiveData,
					...arr
				]			
			},

			// 获取信息
			async getData() {
				uni.showLoading({title: '信息加载中',mask:true});
				// // 系统信息
				const {data: res1 } = await my.getBaseData({
					personId: 1
				})
				// 用户信息
				const {data: res2} = await my.getAllData({
					personId: 1
				})
				console.log(res2.data.requirement);
				const formdata = formdataify(res2.data.requirement)
				console.log(formdata);
				const {data: res4} = await my.changeRequirements({
					formdata
				})
				console.log(res4);
				// 相册
				const {data: res3} = await my.searchAlbumListByUserId({
					userId: 1
				})	
				setTimeout(function () {uni.hideLoading();}, 100);
				this.baseData = res1.data
				
				this.moreData = res2.data
				this.setBaseInfo(res1.data)
				this.setMoreInfo(res2.data)
				this.setAlbumInfo(res3.data)
			},

			// 复制id
			copyText(value) {
				uni.showModal({
					content: value, //模板中提示的内容
					confirmText: '复制内容',
					success: () => { //点击复制内容的后调函数
						//uni.setClipboardData方法就是讲内容复制到粘贴板
						uni.setClipboardData({
							data: value, //要被复制的内容
							success: () => { //复制成功的回调函数
								uni.showToast({ //提示
									title: '复制成功'
								})
							}
						});
					}
				});
			},
			openLikes(){
				uni.$showMsg("你的点赞有" + this.baseData.like + "个！")
			},
			openSorts(){
				uni.$showMsg("下一版本更新积分功能哦！")
			},
			// 打开简历
			gotoResume() {
				uni.$showMsg("下一版本更新简历功能哦！")
			},
			// 查看关注页面
			gotoBillBoard(type) {
				uni.navigateTo({
					url: '/subpkg/billboard/billboard?type=' + type
				})
			},
			// 签到
			async makeSignUp() {
				const {
					data: res
				} = await my.signIn({
					reward: 5,
					loginName: '123456'
				})
				uni.$showMsg(res.message)
				this.baseData.signIn = true
				setTimeout(function() {
					uni.$showMsg('积分 + 5')
				}, 1000)

			},
			// 跳转到vip充值页
			gotoPrivilege() {
				uni.navigateTo({
					url: '/subpkg/privilege/privilege'
				})
			},
			// 跳转到评论页
			gotoShare() {
				uni.navigateTo({
					url: '/subpkg/share/share'
				})
			},
			// 跳转到资料完善页面
			gotoCheckPerson() {
				uni.navigateTo({
					url: '/subpkg/checkPerson/checkPerson'
				})
			},		
		}
	}
</script>

<style lang='scss' scoped>
	.root {
		padding: 0 20rpx;
		background-color: #fcfcfc;
	}

	.head {
		border-bottom: 2rpx solid #eee;
	}

	/* 头部 */
	.header {
		height: 100rpx;
		width: 100%;
		display: flex;
		justify-content: space-between;
		align-items: center;
		border-bottom: 1px solid #eee;

		.header-prove {
			display: flex;
			align-items: center;
			background-color: #eee;
			padding: 10rpx 20rpx;
			border-radius: 20rpx;
		}
	}

	/* 信息页 */
	.main {
		margin: 20rpx 0 40rpx 0;

		.main-top {
			display: flex;
			justify-content: space-between;
			margin-bottom: 40rpx;
			height: 200rpx;


			.top-left {
				display: flex;

				.top-logo {
					width: 150rpx;
					height: 200rpx;
					box-sizing: border-box;

					image {
						width: 150rpx;
						height: 200rpx;
						border-radius: 30rpx;
						border: 4rpx solid #fff;
					}

					.empty-image {
						height: 200rpx;
						width: 100%;
						display: flex;
						justify-content: center;
						flex-direction: column;
						align-items: center;
						font-size: 20px;
						border-radius: 30rpx;
						border: 4rpx solid #eee;
					}
				}

				.top-left-right {
					margin-left: 30rpx;
					;

					h1 {
						height: 100rpx;
						line-height: 100rpx;
						font-size: 20px;
					}

					p {
						height: 50rpx;
						line-height: 50rpx;
						display: flex;

						text {
							margin-right: 10rpx;
						}
					}
				}
			}

			.top-right {
				display: flex;
				align-items: flex-end;

				text {
					padding: 0 30rpx;
					height: 60rpx;
					line-height: 60rpx;
					border-radius: 60rpx;
					color: #fff;
					letter-spacing: 2rpx;
					background: darkorange;
				}

				.isFlaged {
					background: #ccc;
				}
			}
		}

		.main-bottom {
			display: flex;
			justify-content: space-around;

			.bottom-item {
				position: relative;
				width: calc((100% - 40rpx)/4);
				display: flex;
				align-items: center;
				flex-direction: column;
				background-color: #fff;
				padding: 10rpx 20rpx;
				border-radius: 20rpx;

				.item-num {
					font-weight: bold;
					font-size: 16px;
				}

				.item-title {
					color: #777;
				}
			}

			.bottom-item:not(:last-child):before {
				content: "";
				height: 30rpx;
				width: 2rpx;
				background-color: #ddd;
				position: absolute;
				right: 0;
				top: 50%;
				transform: translateY(-50%);
				z-index: 20;
			}
		}
	}

	/* 推荐 */
	.banner {
		display: flex;
		justify-content: space-between;

		.section {
			width: calc((100% - 80rpx)/2);
			padding: 20rpx 0 20rpx 20rpx;
			background-color: #fff;
			border-radius: 20rpx;
			border: 4rpx solid darkorange;

			.section-one {
				display: flex;
				align-items: center;
				justify-content: space-between;

				text {
					font-weight: bold;
					font-size: 15px;
				}
			}

			.section-two {
				text {
					color: #777;
					font-size: 12px;
				}
			}

			.section-three {
				margin-top: 20rpx;

				text {
					color: #fff;
					height: 60rpx;
					line-height: 60rpx;
					border-radius: 30rpx;
					padding: 10rpx 50rpx;
					background: linear-gradient(90deg, #ffaa7f, #ff557f);
				}
			}
		}

	}

	

	/* 动态按钮 */
	.active-btn {
		position: fixed;
		bottom: 120rpx;
		right: 20rpx;
		background-color: darkorange;
		z-index: 5;
		font-size: 14px;
		color: #fff;
		display: flex;
		align-items: center;
		padding: 20rpx 30rpx;
		border-radius: 20rpx;

		text {
			margin-left: 10rpx;
		}
	}
</style>
