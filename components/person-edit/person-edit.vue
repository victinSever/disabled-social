<template>
	<view>
		<!-- tip -->
		<view class="tip">
			<p>各位用户，个人资料内容需要遵守相关法律法规和社区规定，需要审核后才能生效，请严格遵守规定填写资料</p>
		</view>

		<!-- images -->
		<view class="image-list">
			<view class="image-item" v-for="(item, i) in personData.userImages" :key="i">
				<view class="box" v-if="item.show">
					<image :src="item.imagePath" alt=""></image>
					<span class="close">
						<uni-icons type="closeempty" size="16" color="darkorange"></uni-icons>
					</span>
					<span v-if="i === 0" class="host">
						<text>主头像</text>
					</span>
				</view>
				<view class="" v-else>
					<uni-icons type="auth" size="50" color="#eee"></uni-icons>
				</view>
			</view>
		</view>

		<view class="detail">
			<!-- 自我介绍 -->
			<view class="self-introduce">
				<view class="section-title">
					<span class="left-title">自我介绍</span>
					<span v-if="personData.other.userIntroduce !== ''">
						<uni-icons type="paperplane" size="25" color="#777"></uni-icons>
					</span>
				</view>
				<view class="section-content">
					<p v-if="personData.other.userIntroduce !== ''">{{personData.other.userIntroduce}}</p>
					<view class="" v-else>
						<span>添加自我介绍</span>
						<span class="addSpan">
							<uni-icons type="plusempty" size="25" color="#fff" @click="gotoEditDetail('selfIntroduce')">
							</uni-icons>
						</span>
					</view>
				</view>
			</view>

			<!-- 基本信息 -->
			<view class="base-introduce section">
				<view class="section-title">
					<span class="left-title">基本信息</span>
				</view>

				<view class="section-content">
					<span>性别</span>
					<span class="content">
						<text>{{personData.base.sex == '0' ? '男' : '女'}}</text>
					</span>
				</view>

				<view class="section-content">
					<span>年龄</span>
					<span class="content">
						<text>{{personData.base.age}}</text>
					</span>
				</view>

				<view class="section-content">
					<span>身高</span>
					<span class="addSpan" v-if="personData.base.weight === ''">
						<uni-icons type="plusempty" size="25" color="#fff" @click="gotoEditDetail('height')"></uni-icons>
					</span>
					<span v-else class="content">
						<text>{{personData.base.height}}</text>
						<uni-icons type="forward" size="18" color="#777"></uni-icons>
					</span>
				</view>

				<view class="section-content">
					<span>体重</span>
					<span class="addSpan" v-if="personData.base.height === ''">
						<uni-icons type="plusempty" size="25" color="#fff" @click="gotoEditDetail('weight')"></uni-icons>
					</span>
					<span v-else class="content">
						<text>{{personData.base.weight}}</text>
						<uni-icons type="forward" size="18" color="#777"></uni-icons>
					</span>
				</view>

			</view>


			<!-- 其他信息 -->
			<view class="other-introduce section">
				<view class="section-title">
					<span class="left-title">其他信息</span>
				</view>
				<view class="section-content">
					<span>职业</span>
					<span class="addSpan" v-if="personData.other.occupation === ''">
						<uni-icons type="plusempty" size="25" color="#fff" @click="gotoEditDetail('occupation')"></uni-icons>
					</span>
					<span v-else class="content">
						<text>{{personData.other.occupation}}</text>
						<uni-icons type="forward" size="18" color="#777"></uni-icons>
					</span>
				</view>

				<view class="section-content">
					<span>公司</span>
					<span class="addSpan" v-if="personData.other.company === ''">
						<uni-icons type="plusempty" size="25" color="#fff"></uni-icons>
					</span>
					<span v-else class="content">
						<text>{{personData.other.company}}</text>
						<uni-icons type="forward" size="18" color="#777"></uni-icons>
					</span>
				</view>

				<view class="section-content">
					<span>学校</span>
					<span class="addSpan" v-if="personData.other.school === ''">
						<uni-icons type="plusempty" size="25" color="#fff"></uni-icons>
					</span>
					<span v-else class="content">
						<text>{{personData.other.school}}</text>
						<uni-icons type="forward" size="18" color="#777"></uni-icons>
					</span>
				</view>

				<view class="section-content">
					<span>学历</span>
					<span class="addSpan" v-if="personData.other.degree === ''">
						<uni-icons type="plusempty" size="25" color="#fff"></uni-icons>
					</span>
					<span v-else class="content">
						<text>{{personData.other.degree}}</text>
						<uni-icons type="forward" size="18" color="#777"></uni-icons>
					</span>
				</view>

				<view class="section-content">
					<span>来历</span>
					<span class="addSpan" v-if="personData.other.address === ''">
						<uni-icons type="plusempty" size="25" color="#fff"></uni-icons>
					</span>
					<span v-else class="content">
						<text>{{personData.other.address}}</text>
						<uni-icons type="forward" size="18" color="#777"></uni-icons>
					</span>
				</view>
			</view>
		</view>

	</view>

	</view>
</template>

<script>
	export default {
		name: "person-edit",
		props: ['isFinish'],
		watch: {
			isFinish() {
				this.$emit('getData', this.personData)
			}
		},
		data() {
			return {
				personData: {
					base: {
						age: '18',
						sex: '1',
						height: '',
						weight: '',
					},
					other: {
						address: '',
						degree: '',
						school: '',
						company: '',
						occupation: '',
						userIntroduce: ''
					},
					marrary: {

					},

					userImages: [{
						imagePath: '/static/images/home/img1.png',
						show: true
					}, {
						imagePath: '/static/images/home/img2.png',
						show: true
					}, {
						imagePath: '',
						show: false
					}, {
						imagePath: '',
						show: false
					}, {
						imagePath: '',
						show: false
					}, {
						imagePath: '',
						show: false
					}],
				}
			};
		},
		methods: {
			// 跳转到编辑详情页面
			gotoEditDetail(value) {
				uni.navigateTo({
					url: '/subpkg/editDetail/editDetail?id=' + value
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.tip {
		color: #777;
		align-items: center;
		margin-bottom: 40rpx;
		font-size: 12px;
	}

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

	.detail {
		margin-top: 40rpx;

		.self-introduce {
			border-top: 6rpx solid #eee;

			&>view {
				height: 100rpx;
				line-height: 100rpx;
				display: flex;
				justify-content: space-between;
				align-items: center;

			}

			.section-title {
				font-size: 13px;
				color: #777;

				span {
					display: flex;
					align-items: center;
				}
			}

			.section-content {
				font-size: 16px;
				font-weight: bold;

				p {
					word-wrap: break-word;
					width: 100%;
					line-height: 50rpx;
				}

				view {
					width: 100%;
					display: flex;
					justify-content: space-between;
					align-items: center;

					.addSpan {
						display: inline-block;
						height: 60rpx;
						width: 60rpx;
						display: flex;
						justify-content: center;
						align-items: center;
						border-radius: 50%;
						background-color: darkorange;
					}
				}
			}
		}

		.section {
			border-top: 6rpx solid #eee;

			&>view {
				height: 100rpx;
				line-height: 100rpx;
			}

			.section-title {
				font-size: 13px;
				color: #777;
			}

			.section-content {
				display: flex;
				justify-content: space-between;
				align-items: center;
				font-size: 16px;
				font-weight: bold;

				.addSpan {
					display: inline-block;
					height: 60rpx;
					width: 60rpx;
					display: flex;
					justify-content: center;
					align-items: center;
					border-radius: 50%;
					background-color: darkorange;
				}

				.content {
					display: flex;
					align-items: center;
					color: #777;

					text {
						margin-right: 20rpx;
					}
				}
			}
		}
	}
</style>
