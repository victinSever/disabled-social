<template>
	<view>
		<personage :backShow="false" :personageData="data"></personage>
	</view>
</template>

<script>
	import personage from '@/components/personage/index.vue'
	export default {
		name:"person-template",
		components: {personage},
		data() {
			return {
				data: {
					
				}
			};
		}
	}
</script>

<style lang="scss">

</style>