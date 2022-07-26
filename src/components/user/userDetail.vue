<template>
  <div class="root">
    <el-row class="top">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
      >
        <el-menu-item index="base">基础信息</el-menu-item>
        <el-menu-item index="detail">详细信息</el-menu-item>
        <el-menu-item index="marray">择偶信息</el-menu-item>
      </el-menu>
    </el-row>
    <el-row class="bottom">
      <baseInfo :data="personData.baseData" v-if="activeIndex === 'base'"/>
      <detailInfo :data="personData.detailData" v-else-if="activeIndex === 'detail'"/>
      <marrayInfo :data="personData.marrayData" v-else/>
    </el-row>
    <el-skeleton />
  </div>
</template>

<script>
import baseInfo from "@/components/user/detail/baseInfo.vue";
import detailInfo from "@/components/user/detail/detailInfo.vue";
import marrayInfo from "@/components/user/detail/marrayInfo.vue";
export default {
  props: ["personData"],
  components: {
    baseInfo,
    detailInfo,
    marrayInfo,
  },
  data() {
    return {
      activeIndex: 'base', //菜单活动
    };
  },
  methods: {
    // 菜单跳转
    handleSelect(key, keyPath) {
      this.activeIndex = keyPath + ''
    },
  },
};
</script>

<style lang='less' scoped>
.bottom{
  margin-top: 20px;
}
</style>