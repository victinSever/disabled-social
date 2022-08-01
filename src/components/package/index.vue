<template>
  <div>
    <el-form
      :model="data"
      :rules="rules"
      ref="packageForm"
      size="mini"
      label-width="100px"
    >
      <el-form-item label="套餐名" prop="name">
        <el-input placeholder="输入套餐名称" v-model="data.name"> </el-input>
      </el-form-item>

      <el-form-item label="套餐状态" prop="status">
        <el-switch
          v-model="data.status"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="是否首推" prop="isMain">
        <el-switch
          v-model="data.isMain"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="套餐时长" prop="timeNum">
        <el-input
          placeholder="输入套餐时长"
          type="number"
          v-model="data.timeNum"
        >
          <template slot="append">月</template>
        </el-input>
      </el-form-item>
      <el-form-item label="套餐单价" prop="priceNum">
        <el-input
          placeholder="输入套餐单月价格"
          type="number"
          v-model="data.priceNum"
        >
          <template slot="append">元</template>
        </el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  props: ["itemData"],
  watch: {
    itemData: {
      immediate: true,
      handler(val) {
        this.data = val;
      },
    },
  },
  data() {
    return {
      data: {},
      rules: {
        name: { required: true, message: "请输入套餐名称", trigger: "blur" },
        timeNum: { required: true, message: "请输入套餐时长", trigger: "blur" },
        priceNum: {
          required: true,
          message: "请输入套餐单月价格",
          trigger: "blur",
        },
      },
    };
  },
  methods: {
    returnData() {
      let flag;
      this.$refs["packageForm"].validate((valid) => {
        if (valid) {
          flag = this.data;
        } else {
          this.$message.warning("数据填写有误");
          flag = false;
        }
      });
      return flag;
    },
  },
};
</script>

<style scoped lang='less'>
.el-input {
  width: 60%;
}
</style>