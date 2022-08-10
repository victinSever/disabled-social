<template>
	<view class="root" :style="[{height:(CustomBar) + 'px'}]">
	</view>
</template>

<script>
	export default {
		name: "mover",
		data() {
			return {
				StatusBar: this.StatusBar,
				CustomBar: this.CustomBar
			};
		}
	}
</script>

<style lang="scss" scoped>
	.root {
	}
</style>
