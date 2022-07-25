<template>
	<view>
		编辑详细信息
		<span>{{keyword}}</span>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				keyword: ''
			};
		},
		onLoad(value){
			this.keyword = value.id
		},
	}
</script>

<style lang="scss">

</style>
