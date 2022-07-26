<template>
  <div>
    <el-form
      ref="form"
      :model="data"
      label-position="right"
      label-width="100px"
      :rules="rules"
    >
      <el-row>
        <el-form-item label="活动封面" prop="imgPath">
          <div class="block">
              <el-avatar
                shape="square"
                :size="30"
                :src="data.imgPath"
              ></el-avatar>
            </div>
        </el-form-item>
        <el-form-item label="广告位位置" prop="position">
          <el-select v-model="data.type" placeholder="请选择广告位置">
            <el-option
              v-for="item in adverPosition"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="广告位大小" prop="size">
          <el-select v-model="data.size" placeholder="请选择广告位大小">
            <el-option
              v-for="item in adverSize"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="序号" prop="order">
          <el-input-number
            v-model="data.order"
            controls-position="right"
            :min="1"
            :max="10"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="广告类型" prop="type">
          <el-select v-model="data.type" placeholder="请选择广告类型">
            <el-option
              v-for="item in adverType"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
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
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        position:[
          { required: true, message: "请输入广告位置", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        order: [{ required: true, message: "请输入数字", trigger: "change" }],
        status: [{ required: true, message: "请选择状态", trigger: "change" }],
      },

      //   广告位
      adverPosition: [
        {
          value: "1",
          label: "首页弹框",
        },
        {
          value: "2",
          label: "就业模块",
        },
        {
          value: "3",
          label: "社交按钮",
        },
        {
          value: "4",
          label: "活动链接",
        },
        {
          value: "5",
          label: "互动推荐",
        },
      ],

      //   类型
      adverType: [
        {
          value: "1",
          label: "友情广告",
        },
        {
          value: "2",
          label: "七夕之约",
        },
        {
          value: "3",
          label: "情人节礼物",
        },
      ],

      //   型号
      adverSize: [
        {
          value: "1",
          label: "全屏",
        },
        {
          value: "2",
          label: "中屏",
        },
        {
          value: "3",
          label: "右侧按钮",
        },
        {
          value: "4",
          label: "图片",
        },
      ],

    };
  },
  watch:{
    adverData: {
        handler(){
            this.data = this.adverData
        console.log(this.data);
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