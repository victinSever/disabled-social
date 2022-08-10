<template>
	<view class="item-box">
		<view class="left-img">
			<img :src="data.headPicture" alt="">
		</view>
		<view class="right-box">
			<view class="title-info">
				<!-- <h2 :style="{'margin-left':data.flag ? '0px' : '10px'}" v-if="data.diary.diaryUserName">{{data.diary.diaryUserName}}</h2> -->
				<!-- 如果不是广告 -->
				<p v-if="data.flag">
					<text>{{0}}{{data.lastUnit}}前</text>
					<text>·</text>
					<text>{{20}}km</text>
				</p>
				<!-- 如果是广告 -->
				<template v-else>
					<p style="font-size: 15px;margin: 5px 0;">
						<!-- {{data.diary.diaryContent}} -->
					</p>
					<view class="advertise" style="position: absolute;top: 0; right: 20px;">
						<uni-tag text="广告"></uni-tag>
					</view>
				</template>
			</view>
			<view class="content-info" @click="gotoComment">
				<!-- 不是广告 -->
				<p v-if="data.flag">{{data.diary.diaryContent}}</p>
				<view class="cont-info-img">
					<img :src="item" alt="" v-for="(item, i) in data.picture" :key="i" model="widthFix">
				</view>
			</view>
			<view class="btn-info">
				<template v-if="data.flag">
					<view class="btn-info-left">
						<uni-icons type="more-filled" color="#d0d2d4" size="20" @click="openPopu"></uni-icons>
					</view>
					<view class="btn-info-right">
						<!-- 点赞 -->
						<view class="like">
							<uni-icons type="heart" size="25" @click="addLike"
								:style="data.alreadyLike == 1 ? 'color:red' : (code == 1 ? 'color:red' : '')">
							</uni-icons>
							<text>{{data.diary.diaryLove < 100 ? data.diary.diaryLove : '99+' }}</text>
						</view>
						<!-- 跳转评论页面 -->
						<view class="comment" @click="gotoComment">
							<uni-icons type="chatbubble" size="25"></uni-icons>
							<text>{{data.diary.diaryComment < 100 ? data.diary.diaryComment : '99+' }}</text>
						</view>
					</view>
				</template>
				<template v-else>
					<view class="btn-info-left" style="display: flex;color: #a5a5a5;font-size: 16px;">
						<uni-icons type="paperclip" color="#d0d2d4" size="20"></uni-icons>
						<text style="margin-left: 5px;">查看详情</text>
					</view>
					<view class="btn-info-right" style="margin-right: 15px;">
						<uni-icons type="more" color="#d0d2d4" size="20" @click="openPopu"></uni-icons>
					</view>
				</template>
			</view>
		</view>
	</view>
</template>

<script>
	import around from '../../apis/around.js'
	export default {
		name: "message-box",
		props: ['data'],
		data() {
			return {
				code: 0,
                myInfo:{
                    loginName: "",
                    nickName: "",
                    userId:""
                }
			};
		},
		methods: {
			openPopu() {
				this.$emit('openPopu', true)
			},
			gotoComment() {
				uni.navigateTo({
					url: `/subpkg/comment/comment?diaryId=${this.data.diary.diaryId}`  
				})
			},
			// 添加喜欢
			addLike() {
				let _this = this
				this.data.alreadyLike = 0

				this.code == 1 ? uni.$showMsg('取消成功', 1000) : uni.$showMsg('点赞成功', 1000)
				around.addLike({
					userId:this.myInfo.userId,
					diaryId: this.data.diary.diaryId
				}).then(res => {
					_this.data.diary.diaryLove = res.data.map.total
					_this.code = res.data.code
				})
			}
		},
		mounted() {
			this.code = this.data.alreadyLike ;
			console.log(this.data);
            this.myInfo= this.$store.state.user.loginUser?this.$store.state.user.loginUser:{
                loginName: "",
                nickName: "",
                userId:""
            }
		}
	}
</script>

<style lang="scss">
	.item-box {
		display: flex;
		background-color: #fff;
		position: relative;
		margin-bottom: 20px;

		&::after {
			content: '';
			position: absolute;
			bottom: 0;
			left: 50%;
			transform: translateX(-50%);
			width: 90%;
			height: 1px;
			background-color: #f0eef1;
		}

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
			box-sizing: border-box;
			padding-right: 10px;

			.title-info {
				// height: 140px;
				position: relative;

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

					text:nth-child(2) {
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

					// background-color: red;
					img {
						width: 130px;
						margin-right: 5px;
						height: 130px;
						border-radius: 10px;
					}
				}
			}

			.btn-info {
				margin-top: 10px;
				margin-bottom: 10px;
				display: flex;
				justify-content: space-between;

				.btn-info-right {
					display: flex;

					&>view {
						display: flex;
						align-items: center;

						text {
							font-size: 12px;
							margin-right: 3px;
							color: #a5a395;
						}
					}

				}
			}
		}
	}
</style>
