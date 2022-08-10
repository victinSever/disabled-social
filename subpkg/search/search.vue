<template>
	<view class="container">
		<mover></mover>
		<view class="search-option">
			<view class="search-input">
				<uni-icons type="back" size="24" @click="goBack"></uni-icons>
				<view class="search-box">
					<uni-search-bar radius="15" placeholder="请输入搜索内容" bgColor="#EEEEEE" @confirm="search"
						cancelButton="false" @input="inputInfo" @cancel="cancel" />
				</view>

			</view>
		</view>
		<uni-transition mode-class="slide-right" :show="true">
			<view class="body-recomment">
				<mescroll-uni ref="mescrollRef" class="test" @init="init" style="width: 100%;
				display: block;" :style="{height:wh+'px'}" @down="downCallback"
					:down="downOption" :up="upOption" @up="upCallback" :fixed="true">
					<view class="recomment-list">
						<view class="recomment-item" v-for="(item, i) in searchList" :key="i">
							<view class="item-img">
								<img :src="item.picShow[0]" alt="">
								<span class="img-prove">已认证</span>
							</view>
							<view class="item-info">
								<p>
									<span class="info-age">{{item.age}}岁</span>
									<span class="info-high">{{item.hight ? '· '+item.hight : undefined}}cm</span>
									<span class="info-rank">{{item.degree ? '· '+item.degree : undefined}}</span>
								</p>
								<p>
									<span class="info-disType">{{item.disableType}}</span>·
									<span class="info-disLevel">{{item.disableLevel}}</span>
								</p>
								<p class="info-imp">
									<span class="info-address">
										<uni-icons type="location" size="15" color="red"></uni-icons>
										<span>{{item.houseAddress}}</span>
									</span>
									<span class="info-opration" v-for="(item2) in item.hobbies">{{item2}}</span>
								</p>
							</view>
						</view>
					</view>
				</mescroll-uni>
			</view>
		</uni-transition>

	</view>
</template>

<script>
	import search from '../../apis/search.js'
	export default {
		name: 'search',
		data() {
			return {
				total: 0,
				wh: 0,
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
				upOption: {
					use: true,
					auto: false,
					page: {
						num: 0,
						size: 10
					},
					textNoMore: '~暂无更多信息~'
				},
				mescroll: null,
				timer: null,
				searchList: [],
				searchMessage: ''
			};
		},
		methods: {
			goBack() {
				uni.navigateBack({

				})
			},
			cancel() {
				this.searchMessage = ''
				this.clear()
			},
			clear() {
				this.total = 0
				this.page = 2
				this.flag == false
			},
			init(mescroll) {
				this.mescroll = mescroll;
				this.$emit('init', mescroll)
			},
			downCallback() {
		     	this.mescroll.resetUpScroll();
			},
			upCallback(e) {
				this.page=e.num;
				this.getMoreInfo()
			},
			getMoreInfo(fn) {
				let _that = this;
					search.getList({
						page: _that.page,
						size: 10,
						keyword: _that.searchMessage,
					}).then(res => {
						if(_that.page==1){
							_that.searchList=res.data.data?res.data.data:[];
						}else{
							_that.searchList=_that.searchList.concat(res.data.data?res.data.data:[])
						}
						_that.mescroll.endByPage(10, parseInt(_that.total / 10));
					})
			},
			inputInfo(e) {
				let _that = this;
				// 清空总数
				this.clear()
				this.searchMessage = e;
				// 如果是空穿直接返回，并置空数组
				if (this.searchMessage === '') {
					this.searchList = []
					return
				}
				clearTimeout(this.timer)
				this.timer = setTimeout(() => {
					search.getList({
						keyword: e,
						page: 1,
						size: 10
					}).then(res => {
						_that.searchList = res.data.data
						// 如果查询到的数据大于0添加总数
						if (res.data.data&&res.data.data.length > 0)
							_that.total = res.data.data[0].total
					})
				}, 500)
			},
			search(e) {
				this.searchMessage = ''
			},
		},
		mounted() {
			this.wh = uni.getSystemInfoSync().windowHeight - 163;
		}
	}
</script>

<style lang="scss" scoped>
	.search-container {
		width: 100%;
		height: 700rpx;
	}

	.search-option {
		padding: 0 20rpx;
		box-sizing: border-box;

		.search-input {
			display: flex;
			align-items: center;

			.search-box {
				height: 100%;
				flex: 1;
			}
		}
	}

	.body-recomment {
		margin-top: 40rpx;
		width: 100%;

		.recomment-list {
			display: flex;
			flex-wrap: wrap;
			justify-content: space-around;

			.recomment-item {
				width: calc((100% - 40rpx - 20rpx)/2);
				margin-bottom: 40rpx;
				border-radius: 10rpx;
				box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.1),
					1px -1px 1px rgba(0, 0, 0, 0.1),
					-1px -1px 1px rgba(0, 0, 0, 0.1);

				.item-img {
					position: relative;

					img {
						border-radius: 10rpx 10rpx 0 0;
						width: 100%;
						height: 60%;
					}

					.img-prove {
						position: absolute;
						top: 0;
						left: 0;
						height: 40rpx;
						line-height: 40rpx;
						font-size: 12px;
						border-radius: 10rpx 20rpx 20rpx 0;
						padding: 0 15rpx 0 10rpx;
						background-color: #00e100;
						color: #fff;
						letter-spacing: 2rpx;
					}
				}

				.item-info {
					p {
						text-align: center;
						line-height: 50rpx;
						font-size: 12px;
						color: #777;

						span {
							margin: 0 6rpx;
						}
					}

					.info-imp {
						color: red;
						font-size: 10px;
						margin-bottom: 20rpx;

						&>span {
							border-radius: 20rpx;
							height: 50rpx;
							padding: 5rpx 10rpx;
							background-color: rgba(255, 0, 0, 0.1);
						}
					}
				}
			}

		}

	}
</style>
