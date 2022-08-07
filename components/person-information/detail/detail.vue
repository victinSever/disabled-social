<template>
	<view>
		<myProgress :data="data"></myProgress>
		
		<view class="section">
			<text class="label">是否残疾</text>
			<view class="content">
				<picker @change="bindDisableChange" :range="isDisable" :value="data.isDisable">
					<view v-if="data.isDisable" class="uni-input">
						{{isDisable[data.isDisable] || data.isDisable}}
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
						{{disableType[data.disableType] || data.disableType}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>

		<view class="section">
			<text class="label">残疾等级</text>
			<view class="content">
				<picker @change="binddisableLevelChange" :range="disableLevel" :value="data.disableLevel">
					<view v-if="data.disableLevel" class="uni-input">
						{{disableLevel[data.disableLevel] || data.disableLevel}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>

		<view class="section">
			<text class="label">是否自理</text>
			<view class="content">
				<picker @change="bindisProvideChange" :range="disableLevel" :value="data.isProvide">
					<view v-if="data.isProvide" class="uni-input">
						{{isProvide[data.isProvide] || data.isProvide}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="diliver"></view>

		<view class="section">
			<text class="label">健康状况原因</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.jiankang">
			</view>
		</view>

		<view class="section">
			<text class="label">子女是否遗传</text>
			<view class="content">
				<picker @change="bindisGeneticlChange" :range="isGenetic" :value="data.isGenetic">
					<view v-if="data.isGenetic" class="uni-input">
						{{isGenetic[data.isGenetic]}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>

		<view class="section">
			<text class="label">父母状况</text>
			<view class="content">
				<picker @change="bindparentStatusChange" :range="parentStatus" :value="data.parentStatus">
					<view v-if="data.parentStatus" class="uni-input">
						{{parentStatus[data.parentStatus] || data.parentStatus}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>

		<view class="section">
			<text class="label">兄弟姐妹</text>
			<view class="content">
				<picker @change="bindbrotherNumberChange" :range="brotherNumber" :value="data.brotherNumber">
					<view v-if="data.brotherNumber" class="uni-input">
						{{brotherNumber[data.brotherNumber] || data.brotherNumber}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">未来居住意愿</text>
			<view class="content">
				<picker @change="bindlivingWillChange" :range="livingWill" :value="data.livingWill">
					<view v-if="data.livingWill" class="uni-input">
						{{livingWill[data.livingWill]}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="diliver"></view>
		
		<view class="section">
			<text class="label">是否吸烟</text>
			<view class="content">
				<picker @change="bindisSmokingChange" :range="isSmoking" :value="data.isSmoking">
					<view v-if="data.isSmoking" class="uni-input">
						{{isSmoking[data.isSmoking] || data.isSmoking}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">是否饮酒</text>
			<view class="content">
				<picker @change="bindisDrinkingChange" :range="isDrinking" :value="data.isDrinking">
					<view v-if="data.isDrinking" class="uni-input">
						{{isDrinking[data.isDrinking] || data.isDrinking}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">嫁娶形式</text>
			<view class="content">
				<picker @change="bindmarryFormChange" :range="marryForm" :value="data.marryForm">
					<view v-if="data.marryForm" class="uni-input">
						{{marryForm[data.marryForm]}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">生育情况</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.fertilityStatus">
			</view>
		</view>
		
		<view class="section">
			<text class="label">养宠物情况</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.keepingStatus">
			</view>
		</view>
		
		<view class="diliver"></view>
		
		<view class="section">
			<text class="label">兴趣爱好</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.hobby">
			</view>
		</view>
		
		<view class="section">
			<text class="label">血型</text>
			<view class="content">
				<picker @change="bindbloodTypeChange" :range="bloodType" :value="data.bloodType">
					<view v-if="data.bloodType" class="uni-input">
						{{bloodType[data.bloodType] || data.bloodType}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">作息习惯</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.habit">
			</view>
		</view>
		
		<view class="section">
			<text class="label">单位类型</text>
			<view class="content">
				<picker @change="bindcompanyTypeChange" :range="companyType" :value="data.companyType">
					<view v-if="data.companyType" class="uni-input">
						{{companyType[data.companyType]}}
					</view>
					<view v-else>请选择</view>
				</picker>
			</view>
		</view>
		
		<view class="section">
			<text class="label">工作单位</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.companyName">
			</view>
		</view>
		
		<view class="diliver"></view>
		
		<view class="section">
			<text class="label">工作行业</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.workIndustry">
			</view>
		</view>
		
		<view class="section">
			<text class="label">房产位置</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.housingLocation">
			</view>
		</view>
		
		<view class="section">
			<text class="label">我的标签</text>
			<view class="content">
				<input type="text" class="input" placeholder="请输入" v-model="data.personTag">
			</view>
		</view>
		
		<view class="btn-next">
			<button @click="nextPage">下一步</button>
		</view>
	</view>
</template>

<script>
	import myProgress from "@/components/person-information/progress/progress.vue"
	import { mapState } from 'vuex'
	export default {
		data() {
			const isDisable = ['请选择', '正常人', '残疾人']
			const disableType = ['请选择', '视力残疾', '听力残疾', '言语残疾', '肢体残疾', '智力残疾', '精神残疾','多重残疾']
			const disableLevel = ['请选择', '健康', '轻度残疾(残疾四级)', '中度残疾(残疾三级)', '重度残疾(残疾二级)', '极重度残疾(残疾一级)']
			const isProvide = ['请选择', '完全自理，并能照顾对方', '不能自理']
			const parentStatus = ['请选择', '父母健在', '单亲', '离异']
			const isGenetic = ['请选择', '不遗传', '遗传', '不清楚']
			const brotherNumber = ['请选择', '0', '1', '2','3','4','5','6','7','8','9','其他']
			const livingWill = ['请选择', '与自己父母同住', '与Ta父母同住', '两个人同居']
			const isSmoking = ['请选择', '吸烟', '不吸烟']
			const marryForm = ['请选择', '两头顾', '男方家庭为主','女方家庭为主']
			const isDrinking = ['请选择', '经常喝酒', '有时喝一些','不喝酒']
			const bloodType = ['请选择', 'A型', 'B型','AB型','O型']
			const companyType = ['请选择', '经常喝酒', '有时喝一些','不喝酒']
			return {
				isDisable,
				disableType,
				disableLevel,
				isProvide,
				parentStatus,
				isGenetic,
				brotherNumber,
				livingWill,
				isSmoking,
				marryForm,
				isDrinking,
				bloodType,
				companyType,
				data: {
					isDisable: '',
					disableType: '',
					disableLevel: '',
					isProvide: '',
					parentStatus: '',
					isGenetic: '',
					brotherNumber:'',
					livingWill: '',
					isSmoking: '',
					marryForm: '',
					isDrinking:'',
					fertilityStatus: '',
					keepingStatus: '',
					hobby: '',
					bloodType: '',
					habit: '',
					companyType: '',
					companyName: '',
					workIndustry: '',
					housingLocation: '',
					personTag: '',
				}
			};
		},
		components: {
			myProgress
		},
		computed: {
			...mapState('common', ['moreInfo']),
		},
		created(){
			this.data = this.moreInfo.personDetailInfo
		},
		watch: {
			data: {
				deep: true,
				handler(val){
					this.$emit('changeDetail', val)
				}
			}
		},
		methods: {
			nextPage(){
				this.$emit('nextPage', null)
			},
			// 单位类型
			bindcompanyTypeChange(e) {
				this.data.companyType = e.detail.value
			},
			// 血型
			bindbloodTypeChange(e) {
				this.data.bloodType = e.detail.value
			},
			// 是否饮酒
			bindisDrinkingChange(e) {
				this.data.isDrinking = e.detail.value
			},
			// 嫁娶形式
			bindmarryFormChange(e) {
				this.data.marryForm = e.detail.value
			},
			// 是否吸烟
			bindisSmokingChange(e) {
				this.data.isSmoking = e.detail.value
			},
			//未来居住意愿
			bindlivingWillrChange(e) {
				this.data.livingWill = e.detail.value
			},
			//西东弟姐妹
			bindbrotherNumberChange(e) {
				this.data.parentStatus = e.detail.value
			},
			//父母状况
			bindparentStatusChange(e) {
				this.data.parentStatus = e.detail.value
			},
			//是否遗传
			bindisGeneticlChange(e) {
				this.data.isGenetic = e.detail.value
			},
			//是否自理
			bindisProvideChange(e) {
				this.data.isProvide = e.detail.value
			},
			//残疾等级
			binddisableLevelChange(e) {
				this.data.disableLevel = e.detail.value
			},
			//残疾类型
			bindDisableTypeChange(e) {
				this.data.disableType = e.detail.value
			},
			// 是否残疾
			bindDisableChange(e) {
				this.data.isDisable = e.detail.value
			},
		}
	}
</script>

<style lang="scss">
	.section {
		height: 110rpx;
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

			.input {
				font-size: 14px;
				text-align: right;
			}
		}

	}

	.section:not(:last-child) {
		border-bottom: 2rpx solid #eee;
	}

	.section-bottom {
		border-bottom: 8rpx solid #eee;
	}

	.diliver {
		width: 100%;
		height: 8rpx;
		background-color: #eee;
	}

	.btn-next {
		margin: 30rpx 0;

		button {
			border-radius: 20rpx;
			color: #fff;
			background: linear-gradient(#ffaa7f, #ff557f);
		}
	}
</style>
