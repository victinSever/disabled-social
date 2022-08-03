<template>
	<view>
		<view class="section">
			<text class="label">是否残疾</text>
			<view class="content">
				<picker @change="bindDisableChange" :range="isDisable" :value="data.isDisable">
					<view v-if="data.isDisable" class="uni-input">
					{{isDisable[data.isDisable]}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">残疾类别</text>
			<view class="content">
				<picker @change="bindDisableTypeChange" :range="disableType" :value="data.disableType">
					<view v-if="data.disableType" class="uni-input">
					{{disableType[data.disableType]}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">残疾等级</text>
			<view class="content">
				<picker @change="bindDisbaleLevelChange" :range="disbaleLevel" :value="data.disbaleLevel">
					<view v-if="data.disbaleLevel" class="uni-input">
					{{disbaleLevel[data.disbaleLevel]}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">是否自理</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.selfCheck">
			</view>
		</view>
		
		<view class="section">
			<text class="label">健康状况原因</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.jiankang">
			</view>
		</view>	
		
		<view class="section">
			<text class="label">子女是否遗传</text>
			<view class="content">
				<picker @change="bindyichuanlChange" :range="yichuan" :value="data.yichuan">
					<view v-if="data.yichuan" class="uni-input">
					{{yichuan[data.yichuan]}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">父母状况</text>
			<view class="content">
				<picker @change="bindparentsChange" :range="parents" :value="data.parents">
					<view v-if="data.parents" class="uni-input">
					{{parents[data.parents]}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">子女是否遗传</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.yichuan">
			</view>
		</view>
	</view>
</template>

<script>
	import { returnRate } from '@/apis/tools.js'
	export default {
		data() {
			const isDisable = ['请选择','正常人','残疾人']
			const disableType = ['请选择','手臂','腿部','耳部','眼睛','脑瘫','其他']
			const disbaleLevel = ['请选择','手臂','腿部','耳部','眼睛','脑瘫','其他']
			const parents = ['请选择','父母健在','单亲','离异']
			const yichuan = ['请选择','不遗传','遗传','不清楚']
			return {
				isDisable,
				disableType,
				disbaleLevel,
				parents,
				yichuan,
				data: {
					isDisable: '',
					disableType: '',
					disbaleLevel: '',
					parents: '',
					yichuan: ''
				}
			};
		},
		watch: {
			data: {
				immediate: true,
				deep: true,
				handler(val) {
					this.$emit('changeProgress', returnRate(val))
				}
			}
		},
		methods: {
			//父母状况
			bindparentsChange(e){
				this.data.parents = e.detail.value
			},
			//是否遗传
			bindyichuanlChange(e){
				this.data.yichuan = e.detail.value
			},
			//残疾等级
			bindDisbaleLevelChange(e){
				this.data.disbaleLevel = e.detail.value
			},
			//残疾类型
			bindDisableTypeChange(e){
				this.data.disableType = e.detail.value
			},
			// 是否残疾
			bindDisableChange(e){
				this.data.isDisable = e.detail.value
			},
		}
	}
</script>

<style lang="scss">


		.section {
			height: 100rpx;
			display: flex;
			justify-content: space-between;
			align-items: center;

			.label {
				font-weight: bold;
			}

			.content {
				color: #989898;

				.camera {
					border: 2rpx solid #ffb795;
					display: flex;
					align-items: center;
					height: 50rpx;
					padding: 0 20rpx;
					border-radius: 50rpx;

					text {
						color: #ffb795;
					}

					.icon-camera {
						margin-left: 20rpx;
					}
				}
				
				.input{
					font-size: 14px;
					text-align: right;
				}
			}

		}

		.section:not(:last-child) {
			border-bottom: 2rpx solid #eee;
		}
		
		.section-bottom{
			border-bottom: 8rpx solid #eee;
		}
	
	.btn-next{
		margin: 30rpx 0;
		
		button{
			border-radius: 20rpx;
			color: #fff;
			background: linear-gradient(#ffaa7f, #ff557f);
		}
	}
</style>
