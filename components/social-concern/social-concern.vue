<template>
	<view>
		<!-- 附近的社交盒子滑动 -->
		<!-- <scroll-view scroll-y="true" refresher-enabled show-scrollbar="flase" :refresher-triggered="triggered"
			@refresherrefresh="getFresh" class="social-close" :style="{height:wh+'px'}" v-if="getInfo"> -->
		<!-- <message-box :data="item" v-for="(item, i) in acitveData" :key="i" @openPopu="openPopu"></message-box> -->
		<!-- </scroll-view> -->
		<mescroll-uni ref="mescrollRef" @init="init" @down="downCallback" :down="downOption" :up="upOption"
			@up="upCallback" :style="{height:wh+'px'}" :fixed="true"  >
			<message-box :data="item" v-for="(item, i) in acitveData" :key="i" @openPopu="openPopu"></message-box>
		</mescroll-uni>
	</view>
</template>

<script>
	import care from '../../apis/care.js'
	import MescrollMixin from "@/uni_modules/mescroll-uni/components/mescroll-uni/mescroll-mixins.js";
	export default {
		name: "social-close",
		mixins: [MescrollMixin], // 使用mixin
		data() {
			return {
				downOption: {
					use: true, // 是否启用下拉刷新; 默认true
					auto: false, // 是否在初始化完毕之后自动执行下拉刷新的回调; 默认true
				},
				// 上拉加载的常用配置
				upOption: {
					use: false, // 是否启用上拉加载; 默认true
					auto: false, // 是否在初始化完毕之后自动执行上拉加载的回调; 默认true
					page: {
						num: 0, // 当前页码,默认0,回调之前会加1,即callback(page)会从1开始
						size: 10 // 每页数据的数量,默认10
					},
					noMoreSize: 5, // 配置列表的总数量要大于等于5条才显示'-- END --'的提示
					empty: {
						tip: '暂无相关数据'
					}
				},
				mescroll: null,
				wh: 0,
				getInfo: false,
				acitveData: []
			};
		},
		methods: {
			init(mescroll) {
				this.mescroll = mescroll;
				this.$emit('init', mescroll)
			},
			downCallback(e) {
				// console.log('33333', e)
				// 第2种: 下拉刷新和上拉加载调同样的接口, 那以上请求可删, 直接用mescroll.resetUpScroll()代替
				// this.mescroll.resetUpScroll(); // 重置列表为第一页 (自动执行 page.num=1, 再触发upCallback方法 )
				this.mescroll.endSucess()
				console.log(e);
				
			},
			scroll(e) {
				this.$emit('scrolls', e.scrollTop)
			},
			upCallback(mescroll) {
				this.callback(mescroll)
			},
			// 多层对象合并
			assiginObj(target = {}, sources = {}) {
				let obj = target;
				if (typeof target != 'object' || typeof sources != 'object') {
					return sources; // 如果其中一个不是对象 就返回sources
				}
				for (let key in sources) {
					// 如果target也存在 那就再次合并
					if (target.hasOwnProperty(key)) {
						obj[key] = assiginObj(target[key], sources[key]);
					} else {
						// 不存在就直接添加
						obj[key] = sources[key];
					}
				}
				return obj;
			},

			getFresh() {
				this.triggered = true
			},
			openPopu() {
				this.$emit('openPopu', true)
			},
			gotoComment() {
				uni.navigateTo({
					url: '/subpkg/comment/comment'
				})
			},
			// 获取附近用户信息
			getAroundInfo() {
				let _this = this;
				uni.showLoading({
					title: '加载中'
				});
				care.getCarefor({
					page: 1,
					size: 10,
					userId: 1
				}).then(res => {
					_this.acitveData = []
					_this.acitveData = res.data
					_this.getInfo = true;
					uni.hideLoading()
				})
			},
			backUpdate() {
				this.getAroundInfo()
			}
		},
		mounted() {
			this.getAroundInfo();
			this.$bus.$on('backUpdate', this.backUpdate)
			this.wh = uni.getSystemInfoSync().windowHeight -103
		},

	}
</script>

<style lang="scss">
	// 单个信息盒子

	.social-close {
		background-color: red;
	}

	.item-box {
		display: flex;
		background-color: #fff;

		.left-img {

			img {
				margin: 0 8px;
				width: 40px;
				height: 40px;
				border-radius: 50%;
			}
		}

		.right-box {
			width: calc(100vw - 56px);

			.title-info {
				height: 40px;


				h2 {
					font-size: 14px;
					height: 25px;
					line-height: 25px;
				}

				p {
					font-size: 12px;
					height: 15px;
					line-height: 15px;
					color: #a5a395;

					span:nth-child(2) {
						margin: 0 2px;
					}
				}
			}

			.content-info {
				margin-top: 10px;

				p {
					font-size: 14px;
					word-wrap: break-word;
				}

				.cont-info-img {
					margin-top: 10px;

					img {
						width: 130px;
						height: 130px;
						border-radius: 10px;
					}
				}
			}

			.btn-info {
				margin-top: 10px;
				margin-bottom: 20px;
				display: flex;
				justify-content: space-between;

				.btn-info-right {
					display: flex;

					&>view {
						display: flex;
						align-items: center;

						span {
							font-size: 12px;
							margin-right: 10px;
							color: #a5a395;
						}
					}
				}
			}
		}
	}
</style>
