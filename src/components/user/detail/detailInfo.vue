<template>
  <div>
    <el-form label-position="right" label-width="100px" :model="data">
      <el-col :span="12">
        <el-form-item label="残疾类别">{{ data.disableType }}</el-form-item>
        <el-form-item label="残疾等级">{{ data.disableLevel }}</el-form-item>
        <el-form-item label="能否自理">{{ data.isProvide }}</el-form-item>
        <el-form-item label="辅助工具">{{ data.auxiliaryTool }}</el-form-item>
        <el-form-item label="健康状况原因">{{ data.cause }}</el-form-item>
        <el-form-item label="子女是否遗传">
          {{ data.isGenetic }}
        </el-form-item>
        <el-form-item label="父母状况">{{ data.parentStatus }}</el-form-item>
        <el-form-item label="兄弟姐妹个数">{{
          brotherNumber
        }}</el-form-item>
        <el-form-item label="未来居住意愿">
          {{data.livingWill}}
        </el-form-item>
        <el-form-item label="是否吸烟">{{ data.isSmoking }}</el-form-item>
        <el-form-item label="嫁娶形式">{{ data.marryForm }}</el-form-item>
        <el-form-item label="是否饮酒">{{ data.isDrinking }}</el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="生育情况">{{ data.fertilityStatus }}</el-form-item>
        <el-form-item label="养宠物情况">{{ data.keepingStatus }}</el-form-item>
        <el-form-item label="兴趣爱好">{{ data.hobby }}</el-form-item>
        <el-form-item label="血型">{{ data.bloodType }}</el-form-item>
        <el-form-item label="作息习惯">{{ data.habit }}</el-form-item>
        <el-form-item label="单位类型">{{ data.companyType }}</el-form-item>
        <el-form-item label="工作单位">{{ data.companyName }}</el-form-item>
        <el-form-item label="工作行业">{{ data.workIndustry }}</el-form-item>
        <el-form-item label="房产位置">{{ data.housingLocation }}</el-form-item>
        <el-form-item label="我的标签">{{ data.personTag }}</el-form-item>

      </el-col>

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