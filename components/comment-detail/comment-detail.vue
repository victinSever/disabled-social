<template>
	<view class="comment-container">
		<view class="comment-title">评论</view>
		<view class="comment-content" v-for="(item,index) in data" :key="index">
			<view class="content-left">
				<img :src="item.headPicture" alt="" />
			</view>

			<view class="content-right">
				<content-item :user="item.comment"></content-item>

				<uni-transition mode-class="slide-bottom" :show="item.flag">
					<view v-if="item.flag" v-for="(item2,indexs) in commentList" :key="indexs">
						<content-item :user="item2.comment" :replyPic="item2.headPicture"></content-item>
					</view>
				</uni-transition>

				<view class="btn-content" v-if="item.comment.replyAmount > 0">
					<view class="btn-moreInfo" @click="showDown(item)">
						<text>—— 展开{{item.comment.replyAmount}}回复</text>
						<uni-icons type="bottom" size="20"></uni-icons>
					</view>
					<view class="btn-shouqi" v-if="item.flag" @click="showUp(item)">
						<text>收起</text>
						<uni-icons :type="item.flag == false ? 'bottom' : 'top'" size="16"></uni-icons>
					</view>

				</view>
			</view>

		</view>
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
				userList: [{
						user: {
							imgPath: '../../static/images/user.jpg',
							userName: ['柚子'],
							commentContnet: '加油！相信你一定会找到属于你自己的幸福加油！相信你一定会找到属于你自己的幸福。',
							time: '7月12日',
							flag: false
						},
						comment: [{
								userName: ['柚子', '刘哥'],
								commentContnet: '加油！相信你一定会找到属于你自己的幸福加油！相信你一定会找到属于你自己的幸福。',
								time: '7月12日',
								imgPath: 'static/images/user.jpg',
							},
							{
								userName: ['陈小弟', '刘哥'],
								commentContnet: '加油！相信你一定会找到属于你自己的幸福加油！相信你一定会找到属于你自己的幸福。',
								time: '7月12日',
								imgPath: 'static/images/user.jpg',
							},
						]
					},
					{
						user: {
							imgPath: 'static/images/user.jpg',
							userName: ['柚子'],
							commentContnet: '加油！相信你一定会找到属于你自己的幸福加油！相信你一定会找到属于你自己的幸福。',
							time: '7月12日',
							flag: false
						},
						comment: [{
								userName: ['柚子', '刘哥'],
								commentContnet: '加油！相信你一定会找到属于你自己的幸福加油！相信你一定会找到属于你自己的幸福。',
								time: '7月12日',
								imgPath: 'static/images/user.jpg',
							},
							{
								userName: ['陈小弟', '刘哥'],
								commentContnet: '加油！相信你一定会找到属于你自己的幸福加油！相信你一定会找到属于你自己的幸福。',
								time: '7月12日',
								imgPath: 'static/images/user.jpg',
							},
						]
					},
				]
			};
		},
		methods: {
			showDown(item) {
				let _this = this;
				item.flag = true;
				comment.getComments({
					commentId: item.comment.commentId,
					page: 1,
					size: 10
				}).then(res => {
					_this.commentList = res.data
					console.log(res.data);
				})
			},
			showUp(item) {
				item.flag = false
			}
		},
		mounted() {
			console.log(this.data);
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
	}
</style>
