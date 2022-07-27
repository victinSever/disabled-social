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
        <el-form-item label="节日名称" prop="festival">
          <el-input
            v-model="data.festival"
            placeholder="输入节日名称"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="主题名称" prop="title">
          <el-input
            v-model="data.title"
            placeholder="输入主题名称"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="主题颜色" prop="theme">
          <el-select v-model="data.theme" placeholder="请选择主题">
            <el-option
              v-for="item in themes"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="开启动画" prop="isAnimation">
          <el-switch
              v-model="data.isAnimation"
              active-color="#13ce66"
              inactive-color="#ff4949"
            >
            </el-switch>
        </el-form-item>
        <el-form-item label="开启主题" prop="status">
          <el-switch
              v-model="data.status"
              active-color="#13ce66"
              inactive-color="#ff4949"
            >
            </el-switch>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
            <el-date-picker
              v-model="data.startTime"
              type="datetime"
              placeholder="选择开始时间"
              align="right"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束时间" prop="endTime">
            <el-date-picker
              v-model="data.endTime"
              type="datetime"
              placeholder="选择结束时间"
              align="right"
            >
            </el-date-picker>
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
        festival: [
          { required: true, message: "请输入节日", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        title: [
          { required: true, message: "请输入主题名称", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        theme: [
          { required: true, message: "请输入主题", trigger: "change" },
        ],
        startTime: [
          { required: true, message: "请选择开始时间", trigger: "change" },
        ],
        endTime: [
          { required: true, message: "请选择结束时间", trigger: "change" },
        ],
      },

      //   广告位
      themes: [
        {
          value: "1",
          label: "浪漫粉",
        },
        {
          value: "2",
          label: "炫酷黑",
        },
        {
          value: "3",
          label: "优雅紫",
        },
        {
          value: "4",
          label: "飘雪白",
        },
        {
          value: "5",
          label: "玛瑙红",
        },
      ],

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