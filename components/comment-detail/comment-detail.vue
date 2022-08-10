<template>
	<view class="comment-container">
		<view class="comment-title">评论</view>

		<template v-if="data">
			<view class="comment-content" v-for="(item,index) in data" :key="index">
				<view class="content-left">
					<img :src="item.headPicture" alt="" />
				</view>
				<view class="content-right">
					<content-item :user="item.comment" :alreadyItem="item"></content-item>

					<uni-transition mode-class="slide-bottom" :show="item.flag">
						<view v-if="item.flag" v-for="(item2,indexs) in commentList" :key="indexs">
							<content-item  :user="item2.comment" :alreadyItem="item2" :replyPic="item2.headPicture">
							</content-item>
						</view>
					</uni-transition>

					<view class="btn-content">
						<view class="btn-moreInfo" v-if="item.replyAmountTemp > 0" @click="showDown(item)">
							<text @click="commentReply">—— 展开{{item.flag == false ? item.comment.replyAmount : item.replyAmountTemp}}回复</text>
							<uni-icons type="bottom" size="20"></uni-icons>
						</view>
						<view class="btn-shouqi" v-if="item.flag" @click="showUp(item)">
							<text>收起</text>
							<uni-icons :type="item.flag == false ? 'bottom' : 'top'" size="16"></uni-icons>
						</view>

					</view>
				</view>

			</view>
		</template>

		<template v-else>
			<view class="no-comment">
				<text>~暂无评论~</text>
			</view>
		</template>

	</view>
</template>

<script>
	import comment from '../../apis/comment.js'
	export default {
		name: "comment-detail",
		props: ['data'],
		data() {
			return {
				commentList: [],
			};
		},
		methods: {
			showDown(item) {
				let _this = this;
				item.flag = true;
				if (item.Flag == false) {
					uni.$showMsg("获取中...");
					comment.getComments({
						commentId: item.comment.commentId,
						page: item.page,
						size: 10
					}).then(res => {
						if (res.data.data) {
							_this.commentList = _this.commentList.concat(res.data.data)
							item.replyAmountTemp -= 10
							item.page++
						}
						uni.hideToast()
					})
				} else {
					item.Flag = false
					item.replyAmountTemp -= (item.page - 1) * 10
				}

			},
			showUp(item) {
				item.flag = false
				item.Flag = true
				item.replyAmountTemp = item.comment.replyAmount
			},
			commentReply(){
				
			}
		},
		mounted() {
			// console.log(this.data);
		}
	}
</script>

<style lang="scss">
	.comment-container {
		border-top: 1px solid #f5f5f5;
		padding-top: 15px;
		height: 200px;

		.comment-title {
			font-weight: bold;
			margin-bottom: 10px;
		}

		.comment-content {
			width: 100%;
			display: flex;

			.content-left {
				width: 60px;

				box-sizing: border-box;
				display: flex;
				justify-content: center;

				img {
					width: 45px;
					height: 45px;
					border-radius: 50%;
				}
			}

			.content-right {
				width: calc(100vw - 60px);
				height: 100%;


				.btn-content {
					// background-color: red;
					display: flex;
					align-items: center;
					justify-content: space-around;
					box-sizing: border-box;
					padding: 0 30px;

					.btn-shouqi {
						display: flex;
					}

					.btn-moreInfo {
						// margin-left: 80px;
						display: flex;
					}
				}

			}
		}


		.no-comment {
			width: 100%;
			height: 150px;
			display: flex;
			align-items: center;
			justify-content: center;
			font-size: 18px;
			text-align: center;
			color: #a5a395;
		}
	}
</style>
