<template>
	<view class="homeSetting">
		<view class="homeSetting-box">
			<!-- 头部按钮 -->
			<view class="homeSetting-header">
				<uni-icons type="closeempty" size="25" @click="closePopup"></uni-icons>
				<span>筛选</span>
				<span class="btnSure" @click="closePopup">确定</span>
			</view>
			<!-- 选项区域 -->
			<scroll-view scroll-y="true" class="homeSetting-main">
				<view class="age-and-distance">
					<view class="box-age">
						<view class="age-top">
							<span>年龄</span>
							<span>18 - 50</span>
						</view>
						<view class="age-bottom">
							<span></span>
						</view>
					</view>
					<view class="box-distance">
						<view class="distance-top">
							<span>距离</span>
							<span>10km</span>
						</view>
						<view class="distance-bottom">
							<span></span>
						</view>
					</view>
				</view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	export default {
		name:"recomment-setting",
		data() {
			return {
				
			};
		},
		methods: {
			closePopup(){
				this.$emit('closePopup', true)
			}
		}
	}
</script>

<style lang="scss">
// 弹出层的盒子设置
	.homeSetting{
		height: 93vh;
		background-color: transparent;
		
		.homeSetting-box{
			height: 100%;
			background-color: #fff;
			border-radius: 40rpx 40rpx 0 0;
			padding: 30rpx 20rpx 0 20rpx;
			
			.homeSetting-header{
				height: 100rpx;
				display: flex;
				align-items: center;
				justify-content: space-between;
				font-size: 16px;
				font-weight: bold;
				letter-spacing: 2rpx;
				border-bottom: 1px solid #eee;
				
				.btnSure{
					color: darkorange;
				}
			}
			
			.homeSetting-main{
				margin-top: 40rpx;
				height: 2000rpx;
				
				.age-and-distance{
					&>view{
						height: 150rpx;
						
						
						&>view:first-child{
							display: flex;
							justify-content: space-between;
							
							span:first-child{
								color: #777;
							}
							span:last-child{
								font-weight: bold;
							}
						}
						
						&>view:last-child{
							width: 100%;
							span{
								display: inline-block;
								height: 4rpx;
								width: 100%;
								background-color: darkorange;
							}
						}
					}
				}
			}
		}
	}
</style>