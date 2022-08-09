<template>
	<view class="root">
		<mover></mover>
		<!-- 头部区域 -->
		<template v-if="showOneInfo">
			<view class="comment-header">
				<view class="comment-header-left">
					<text @click="gotoBack">
						<uni-icons type="back" size="25"></uni-icons>
					</text>
					<view class="comment-header-left-info">
						<view class="info-left">
							<image :src="userData.headPicture" alt="" style="height: 40px;width: 40px;"></image>
						</view>
						<view class="info-right">
							<h2>DAisss <text>√</text></h2>
							<p>
								<text>2天前</text>
								<text>·</text>
								<text>29km</text>
							</p>
						</view>
					</view>
				</view>

				<view class="comment-header-right">
					<text class="btnConcern">关注</text>
					<text v-if="false">已关注</text>
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
						<view class="comment">
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
	</view>
</template>

<script>
	import comment from '../../apis/comment.js';
	import around from '../../apis/around.js'
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
			init(mescroll) {
				this.mescroll = mescroll;
				this.$emit('init', mescroll)
			},
			// 返回上一页
			gotoBack() {
				let _this = this
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
						if (_this.data.length + res.data.length == _this.total) {
							console.log('进了');
							_this.flag = false
							_this.mescroll.optDown.textSuccess = '暂无更多数据'
						}
						_this.page++
						_this.data[fn](...res.data)
						console.log(res.data);
						console.log(_this.data.length);

						_this.mescroll.endByPage(10, parseInt(_this.total / 10));
					})
				} else {
					console.log('走了');
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
					_this.data = res.data
					console.log(res.data);
					_this.showComments = true
					if (_this.data.length > 0)
						_this.total = _this.data[0].total
					console.log(_this.total);
					console.log(_this.data.length);
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
					_this.userData.diary.diaryLove = res.data.map.total
					_this.code = res.data.code
				})
			},
			// 获取一条展示数据
			OneInfo() {
				let _this = this;
				comment.getOneInfo({
					diaryId: this.diaryId
				}).then(res => {
					_this.userData = res.data
					_this.showOneInfo = true
					_this.code = _this.userData.alreadyLike
				})
			}
		},
		onLoad(options) {
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
