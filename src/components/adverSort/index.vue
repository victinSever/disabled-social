<template>
  <div>
    <el-form
      ref="form"
      :model="data"
      label-position="right"
      label-width="100px"
      :rules="rules"
      size="mini"
    >
      <el-row>
        <el-form-item label="广告位名称" prop="title">
          <el-input
            v-model="data.title"
            placeholder="输入广告位名称"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="广告位描述" prop="description">
            <el-input
            v-model="data.title"
            placeholder="输入广告位描述"
            clearable
          ></el-input>
        </el-form-item>

        <el-form-item label="序号" prop="order">
          <el-input-number
            v-model="data.order"
            controls-position="right"
            :min="1"
            :max="10"
          ></el-input-number>
        </el-form-item>
      </el-row>
    </el-form>
  </div>
</template>

<script>
export default {
  props: ['adverData'],
  data() {
    return {
      data: {},
      rules: {
        title: [
          { required: true, message: "请输入广告标题", trigger: "blur" },
        ],
        description:[
          { required: true, message: "请输入广告位置描述", trigger: "blur" },
        ],
        status: [{ required: true, message: "请选择状态", trigger: "change" }],
      },

    };
  },
  watch: {
    adverData: {
        handler(){
            this.data = this.adverData
        },
        immediate: true
    }
  },
  methods: {
    // 父组件调用
    returnData() {
      return this.data;
    },
  },
};
</script>

<style>
</style>