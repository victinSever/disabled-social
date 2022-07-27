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
          <el-form-item label="活动封面" prop="imgPath">
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
            <el-form-item label="活动主题" prop="title">
              <el-input
                placeholder="请输入主题"
                v-model="data.title"
                autofocus
              ></el-input>
            </el-form-item>
            <el-form-item label="开始时间" prop="title">
              <el-date-picker
                v-model="data.startTime"
                type="datetime"
                placeholder="选择开始时间"
                align="right"
              >
              </el-date-picker>
            </el-form-item>
            <el-form-item label="开启报名" prop="title">
              <el-switch
                v-model="data.isSignUp"
                active-color="#13ce66"
                inactive-color="#ff4949"
              >
              </el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="活动投放位" prop="position">
              <el-select v-model="data.type" placeholder="请选择APP活动展示位置">
                <el-option
                  v-for="item in adverPosition"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="结束时间" prop="title">
              <el-date-picker
                v-model="data.endTime"
                type="datetime"
                placeholder="选择开始时间"
                align="right"
              >
              </el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="addGroup">新建单身群</el-button>
            </el-form-item>
            <el-form-item
              v-for="(item, index) in group"
              :label="'单身群' + index"
              :key="item.key"
              :prop="'item.' + index + '.value'"            
            >
              <el-input v-model="item.value" placeholder="输入群名称，默认容纳量为1000人"></el-input
              ><el-button @click.prevent="removeGroup(item)" type="danger">删除</el-button>
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider></el-divider>
        <el-row>
          <el-col :span="12">
            <el-form-item label="浏览量" prop="views">
              <span>{{ data.views }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="报名数" prop="signUpNum">
              <span>{{ data.signUpNum }}</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider></el-divider>
        <el-row>
          <el-form-item label="活动内容" prop="content">
            <vue-ueditor-wrap
              v-model="contents"
              :config="myConfig"
              style="width: 100%"
            ></vue-ueditor-wrap>
          </el-form-item>
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
        position: [
          { required: true, message: "请输入广告位置", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        order: [{ required: true, message: "请输入数字", trigger: "change" }],
        status: [{ required: true, message: "请选择状态", trigger: "change" }],
      },

      // 群
      group: [],

      //   活动投放位
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


      contents: '',
      myConfig: {
        autoHeightEnabled: false, // 编辑器不自动被内容撑高
        initialFrameHeight: 200, // 初始容器高度
        initialFrameWidth: "100%", // 初始容器宽度
        UEDITOR_HOME_URL: "/UEditor/",
        // 上传文件接口（这个地址是我为了方便各位体验文件上传功能搭建的临时接口，请勿在生产环境使用！！！）
        serverUrl: `/ueditorUploads`,
      },
    };
  },
  watch: {
    adverData: {
      handler() {
        this.data = this.adverData;
      },
      immediate: true,
    },
  },
  methods: {
     // 上传生成
    handleAvatarSuccess(res, file) {
      this.data.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    
    // 移除单身群
    removeGroup(item) {
      var index = this.group.indexOf(item);
      if (index !== -1) {
        this.group.splice(index, 1);
      }
    },
    // 添加单身群
    addGroup() {
      this.group.push({
        value: "",
        key: Date.now(),
      });
    },
    // 父组件调用
    returnData() {
      this.data.group = this.group;
      this.data.contents = this.contents;
      return this.data;
    },
  },
};
</script>

<style scoped lang='less'>
.el-input {
  width: 60%;
  margin-right: 10px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #eee;
  border-radius: 3px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  width: 50px;
  height: 50px;
  background-color: #ccc;
  display: flex;
  justify-content: center;
  align-items: center;
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