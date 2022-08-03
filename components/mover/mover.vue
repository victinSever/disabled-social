<template>
	<view class="root" :style="[{height:(CustomBar+StatusBar) + 'px'}]">
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
		position: sticky;
		top: 0;
		background-color: #fff;
		z-index: 100;
		height: 70rpx;
		width: 100%;
	}
</style>
