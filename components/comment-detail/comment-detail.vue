<template>
	<view class="comment-container">
		<view class="comment-title">评论</view>
		<view class="comment-content" v-for="(item,index) in userList" :key="index">
			<view class="content-left">
				<img :src="item.user.imgPath" alt="">
			</view>

			<view class="content-right">
				<content-item :user="item.user"></content-item>

				<uni-transition mode-class="slide-bottom" :show="item.user.flag">
					<view v-if="item.user.flag" v-for="(item2,index) in item.comment">
						<content-item :user="item2"></content-item>
					</view>
				</uni-transition>

				<view class="btn-moreInfo" @click="showComment(item.user)">
					<text>—— 展开1510条回复</text>
					<uni-icons :type="item.user.flag == false ? 'bottom' : 'top'" size="20"></uni-icons>
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		name: "comment-detail",
		data() {
			return {
				userList: [{
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
			showComment(item) {
				item.flag = !item.flag
			}
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

				.btn-moreInfo {
					margin-left: 80px;
					display: flex;
				}
			}
		}
	}
</style>
