<template>
	<view class="root">
		<mover></mover>
		<!-- 头部区域 -->
		<template v-if="showOneInfo">
			<view class="comment-header">
				<view class="comment-header-left">
					<uni-icons @click="gotoBack" type="back" size="25"></uni-icons>
					<view class="comment-header-left-info">
						<view class="info-left">
							<image :src="userData.headPicture" alt="" style="height: 40px;width: 40px;"></image>
						</view>
						<view class="info-right">
							<h2>DAisss <text>√</text></h2>
							<p>
								<text>{{dateDiff(userData.diary.createTime)}}</text>
								<text>·</text>
								<text>20km</text>
							</p>
						</view>
					</view>
				</view>

				<view class="comment-header-right">
					<text class="btnConcern" @click="sendAttention()">{{userData.attention==0?"关注":"已关注"}}</text>
				</view>
			</view>

			<!-- 消息区 -->
			<view class="comment-personmessage">
				<view class="personmessage-main">
					<p>{{userData.diary.diaryContent}}</p>
					<view class="personmessage-images">
						<image :src="item" alt="" v-for="(item, i) in userData.picture" :key="i"></image>
					</view>
				</view>
				<view class="personmessage-btn">
					<view class="btn-info-left">
						<uni-icons type="more" size="25" @click="toggle"></uni-icons>
					</view>
					<view class="btn-info-right">
						<view class="like" @click="addLike()">
							<!-- 点赞 -->
							<uni-icons type="heart"
								:style="userData.alreadyLike == 1 ? 'color:red' : (code == 1 ? 'color:red' : '')"
								size="25">
							</uni-icons>
							<text>{{userData.diary.diaryLove < 100 ? userData.diary.diaryLove : '99+'}}</text>
						</view>
						<view class="comment" @click="showHuifu">
							<uni-icons type="chatbubble" size="25" color="#777"></uni-icons>
							<text>打招呼</text>
						</view>
					</view>
				</view>
			</view>

		</template>

		<!-- 评论区 -->
		<uni-transition mode-class="slide-right" :show="true">
			<view v-if="showComments">
				<mescroll-uni :style="{height:wh+'px'}" ref="mescrollRef" @init="init" @down="downCallback"
					:down="downOption" :up="upOption" @up="upCallback" :fixed="true">
					<comment-detail :data="data"></comment-detail>
				</mescroll-uni>
			</view>
		</uni-transition>



		<uni-popup ref="huifu" background-color="#fff">
			<comment-replay @closeHuifu="closeHuifu"></comment-replay>
		</uni-popup>

	</view>
</template>

<script>
	import comment from '../../apis/comment.js';
	import around from '../../apis/around.js';
	import recomment from "@/apis/recomment.js"
	export default {

		data() {
			return {
				wh: 500,
				page: 2,
				total: 0,
				flag: true,
				downOption: {
					use: true,
					auto: false,
					page: {
						num: 0,
						size: 10
					},
					textLoading: '加载中....',
				},
				// 上拉加载的常用配置
				upOption: {
					use: true,
					auto: false,
					page: {
						num: 0,
						size: 10
					},
					textNoMore: '~暂无更多信息~'
					// empty: {
					// 	tip: '暂无相关数据'
					// }
				},
				showOneInfo: false,
				showComments: false,
				diaryId: 0,
				data: [],
				userData: {},
				code: 0,
				backUpdate: {},
				diaryId: ''
			};
		},
		methods: {
			showHuifu() {
				this.$refs.huifu.open('bottom');
			},
			closeHuifu(info) {
				console.log(1);
				console.log(info);
				this.$refs.huifu.close();
			},
			dateDiff(nowTime) {
				if (!nowTime) return '';
				nowTime = nowTime.replace(/年/g, '/');
				nowTime = nowTime.replace(/月/g, '/');
				nowTime = nowTime.replace(/日/g, '/');
				nowTime = nowTime.replace(/时/g, ':')
				nowTime = nowTime.replace(/分/g, ':');
				nowTime = nowTime.replace(/秒/g, '');
				const arg = [nowTime]
				const now = arg[1] ? arg[1] : new Date().getTime()
				const diffValue = now - new Date(arg[0].replace(/-/g, '/')).getTime()
				let result = ""
				const minute = 1000 * 60
				const hour = minute * 60
				const day = hour * 24
				const halfamonth = day * 15
				const month = day * 30
				const year = month * 12

				const _year = diffValue / year
				const _month = diffValue / month
				const _week = diffValue / (7 * day)
				const _day = diffValue / day
				const _hour = diffValue / hour
				const _min = diffValue / minute
				if (_year >= 1) result = parseInt(_year) + "年前"
				else if (_month >= 1) result = parseInt(_month) + "个月前"
				else if (_week >= 1) result = parseInt(_week) + "周前"
				else if (_day >= 1) result = parseInt(_day) + "天前"
				else if (_hour >= 1) result = parseInt(_hour) + "个小时前"
				else if (_min >= 1) result = parseInt(_min) + "分钟前"
				else result = "刚刚"
				return result
			},
			//关注和取消用户
			sendAttention() {
				recomment.concernUser({
					concernedUserId: this.userData.userId
				}).then((res) => {
					if (this.userData.attention == 1) {
						uni.showToast({
							icon: "none",
							title: "已取消"
						})
						this.userData.attention = 0
					} else {
						uni.showToast({
							icon: "none",
							title: "关注成功"
						})
						this.userData.attention = 1
					}

				}).catch(() => {

				})

			},

			init(mescroll) {
				this.mescroll = mescroll;
				this.$emit('init', mescroll)
			},
			// 返回上一页
			gotoBack() {
				let _this = this
				// uni.switchTab({
				// 	url: '/pages/social/social'
				// });
				uni.navigateBack({
					success() {
						_this.$bus.$emit('backUpdate')
					}
				})
			},
			downCallback(e) {
				this.getMoreInfo('unshift')
			},
			upCallback(e) {
				this.page = e.num;
				this.getMoreInfo('push')
			},
			getMoreInfo(fn) {
				let _this = this;
				if (this.flag == true) {
					comment.getCommentList({
						diaryId: this.diaryId,
						size: 10,
						page: this.page
					}).then(res => {
						if (_this.data.length + res.data.data.length == _this.total) {
							_this.flag = false
							_this.mescroll.optDown.textSuccess = '暂无更多数据'
						}
						if (_that.page == 1) {
							_that.data = res.data.data
						} else {
							_that.data = _that.data.concat(res.data.data)
						}

						_this.mescroll.endByPage(10, parseInt(_this.total / 10));
					})
				} else {
					_this.mescroll.endByPage(10, parseInt(_this.total / 10));
				}
			},
			// 获取新的列表数据
			getComments() {
				let _this = this;
				comment.getCommentList({
					diaryId: this.diaryId,
					page: 1,
					size: 10
				}).then(res => {
					_this.data = res.data.data
					_this.showComments = true
					if (_this.data)
						_this.total = _this.data[0].total
				})
			},
			// 添加喜欢
			addLike() {
				let _this = this
				this.userData.alreadyLike = 0
				this.code == 1 ? uni.$showMsg('取消成功', 1000) : uni.$showMsg('点赞成功', 1000)
				around.addLike({
					userId: 1,
					diaryId: this.userData.diary.diaryId
				}).then(res => {
					_this.userData.diary.diaryLove = res.data.data.map.total
					_this.code = _this.code == 0 ? 1 : 0
				})
			},
			// 获取一条展示数据
			OneInfo() {
				let _this = this;
				comment.getOneInfo({
					diaryId: this.diaryId
				}).then(res => {
					console.log(res);
					_this.userData = res.data.data
					_this.showOneInfo = true
					_this.code = _this.userData.alreadyLike
				})
			}
		},
		onLoad(options) {
			console.log(options.diaryId);
			this.diaryId = options.diaryId
			this.OneInfo()
			this.getComments()
		}
	}
</script>

<style lang="scss">
	.root {
		box-sizing: border-box;
		padding: 10px;


		.comment-header {
			position: relative;
			// top: 70rpx;

			height: 100rpx;
			z-index: 10;
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

						image {
							height: 10px;
							width: 10px;
							border-radius: 50%;
						}
					}

					.info-right {
						h2 {
							font-size: 14px;

							text {
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

				text {
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
			.personmessage-main {
				margin-top: 10px;

				p {
					font-size: 14px;
					word-wrap: break-word;
				}

				.personmessage-images {
					margin-top: 10px;

					// display: flex;
					// flex-wrap: wrap;
					// justify-content: space-around;
					// align-content: flex-end;
					// background-color: red;
					image {
						width: 100px;
						height: 100px;
						border-radius: 10px;
						margin-right: 10px;
					}
				}
			}

			.personmessage-btn {
				margin-top: 10px;
				margin-bottom: 20px;
				display: flex;
				justify-content: space-between;

				.btn-info-left {
					display: flex;
					align-items: center;
				}

				.btn-info-right {
					display: flex;

					&>view {
						display: flex;
						align-items: center;

						text {
							font-size: 12px;
							margin-right: 5px;
							color: #a5a395;
						}
					}
				}
			}
		}


		.comment-scroll {
			height: 500px;
			width: 100%;
		}
	}
</style>
