<template>
  <div>
    <el-form :model="adverData" size="mini" label-width="100px">
      <el-form-item label="订单号" prop="id">{{adverData.id}}</el-form-item>
      <el-form-item label="用户ID" prop="userId">{{adverData.userId}}</el-form-item>
      <el-form-item label="订单状态" prop="id">
        <el-tag type="warning" size="mini" v-if="adverData.status == 0">未支付</el-tag>
        <el-tag type="success" size="mini" v-else-if="adverData.status == 1">已支付</el-tag>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  props: ['adverData'],
  mounted(){
    this.data = this.adverData
    console.log(this.data);
  },
  data(){
    return {
      data: {}
    }
  }
}
</script>

<style>

</style>