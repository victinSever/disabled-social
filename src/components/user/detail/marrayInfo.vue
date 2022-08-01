<template>
  <div>
    <el-form label-position="right" label-width="100px" :model="data">
      <el-col :span="12">
        <el-form-item label="年龄范围">{{ data.ageRange }}</el-form-item>
        <el-form-item label="身高范围">{{ data.heightRange }}</el-form-item>
        <el-form-item label="婚姻情况">{{ data.marryStatus }}</el-form-item>
        <el-form-item label="收入要求">{{ data.income }}</el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="购房情况">{{ data.housingStatus }}</el-form-item>
        <el-form-item label="购车情况">{{ data.carStatus }}</el-form-item>
        <el-form-item label="其他要求">{{
          data.otherRequirements
        }}</el-form-item></el-col
      >
    </el-form>
  </div>
</template>

<script>
export default {
  props: ["data"],
};
</script>

<style>
</style>