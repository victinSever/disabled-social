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
        <el-form-item label="封面图片" prop="name">
          <el-upload
            class="avatar-uploader"
            action=""
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="data.imgPath" :src="data.imgPath" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="广告标题" prop="title">
            <el-input
              v-model="data.title"
              placeholder="输入广告标题"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item label="广告链接" prop="url">
            <el-input v-model="data.url" placeholder="输入链接" clearable
              ><template slot="prepend">Http://</template></el-input
            >
          </el-form-item>
          <el-form-item label="型号" prop="size">
            <el-select v-model="data.size" placeholder="请选择型号">
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
        </el-col>
        <el-col :span="12">
          <el-form-item label="广告类型" prop="type">
            <el-select v-model="data.type" placeholder="请选择广告位">
              <el-option
                v-for="item in adverType"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所属广告位" prop="name">
            <el-select v-model="data.position" placeholder="请选择广告位">
              <el-option
                v-for="item in adverPosition"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="开始时间" prop="startTime">
            <el-date-picker
              v-model="data.startTime"
              type="datetime"
              placeholder="选择开始时间"
              align="right"
              :picker-options="pickerOptions"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束时间" prop="endTime">
            <el-date-picker
              v-model="data.endTime"
              type="datetime"
              placeholder="选择结束时间"
              align="right"
              :picker-options="pickerOptions"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-radio v-model="data.status" label="true">正常</el-radio>
            <el-radio v-model="data.status" label="false">禁用</el-radio>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
export default {
  props: ["adverData"],
  data() {
    return {
      data: {},
      rules: {
        title: [
          { required: true, message: "请输入广告标题", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        type: [
          { required: true, message: "请至少选择活动类型", trigger: "change" },
        ],
        startTime: [
          {
            type: "date",
            required: true,
            message: "请选择开始日期",
            trigger: "change",
          },
        ],
        endTime: [
          {
            type: "date",
            required: true,
            message: "请选择结束时间",
            trigger: "change",
          },
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
          label: "图片",
        },
        {
          value: "2",
          label: "文字",
        },
        {
          value: "3",
          label: "视频",
        },
        {
          value: "4",
          label: "推荐",
        },
      ],

      //   型号
      adverSize: [
        {
          value: "1",
          label: "50*50",
        },
        {
          value: "2",
          label: "30*30",
        },
        {
          value: "3",
          label: "20*20",
        },
        {
          value: "4",
          label: "10*20",
        },
      ],

      // 日期选择器
      pickerOptions: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            },
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            },
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            },
          },
        ],
      },
    };
  },
  watch:{
    adverData(){
        this.data = this.adverData
    }
  },
  methods: {
    // 上传生成
    handleAvatarSuccess(res, file) {
      this.data.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      // if (!isJPG) {
      //   this.$message.error('上传头像图片只能是 JPG 格式!');
      // }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    // 父组件调用
    returnData(){
      return this.data
    }
  },
};
</script>

<style scoped lang='less'>
.avatar-uploader .el-upload {
  border: 1px dashed #eee;
  border-radius: 3px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  width: 50px;
  height: 50px;
  background-color: #ccc;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 18px;
  color: #8c939d;
  width: 50px;
  height: 50px;
  background-color: #ccc;
  text-align: center;
}
.avatar {
  width: 50px;
  height: 50px;
  display: block;
}
</style>