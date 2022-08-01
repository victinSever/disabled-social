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
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="imgUrl" :src="imgUrl" class="avatar" />
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
          
          
          <el-form-item label="广告描述" prop="description">
            <el-input
              v-model="data.description"
              placeholder="输入广告描述"
              clearable
            ></el-input>
          </el-form-item>

          <el-form-item label="广告链接" prop="advertisingLinks">
            <el-input v-model="data.advertisingLinks" placeholder="输入链接" clearable
              ><template slot="prepend">Http://</template></el-input
            >
          </el-form-item>

          <el-form-item label="序号" prop="ordinal">
            <el-input-number
              v-model="data.ordinal"
              controls-position="right"
              :min="1"
              :max="10"
            ></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          
          <el-form-item label="所属广告位" prop="name">
            <el-select v-model="data.unit" placeholder="请选择广告位">
              <el-option
                v-for="item in adverPosition"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="广告规模" prop="advertisedModel">
            <el-select v-model="data.advertisedModel" placeholder="请选择规模">
              <el-option
                v-for="item in adverSize"
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
        ],
        description: [
          { required: true, message: "请输入广告描述", trigger: "blur" },
        ],
        startTime: [
          {
            type: "date",
            required: true,
            message: "请选择开始日期",
            trigger: "blur",
          },
        ],
        endTime: [
          {
            type: "date",
            required: true,
            message: "请选择结束时间",
            trigger: "blur",
          },
        ],
        order: [{ required: true, message: "请输入数字", trigger: "change" }],
        status: [{ required: true, message: "请选择状态", trigger: "change" }],
      },
      imgUrl: '',
      appearencePath: '',

      //   广告位
      adverPosition: [
        {
          value: "1",
          label: "首页全屏弹框",
        },
        {
          value: "2",
          label: "广场惊喜多多",
        },
        {
          value: "3",
          label: "推荐动态",
        },
      ],

      //   型号
      adverSize: [
        {
          value: "1",
          label: "链接",
        },
        {
          value: "2",
          label: "图文",
        },
        {
          value: "3",
          label: "视屏",
        },
        {
          value: "4",
          label: "文字",
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
      if(this.adverData === {}){
        this.data = {
          imgPath: ''
        } 
      }else
        this.data = this.adverData
    }
  },
  methods: {
    beforeAvatarUpload(file) {     
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }

      this.appearencePath = file
      this.imgUrl = URL.createObjectURL(file);
      return isLt2M;
    },
    // 父组件调用
    returnData(){   
      return this.data
    }
  },
};
</script>

<style scoped lang='less'>
.avatar-uploader,.el-upload{
  height: 80px;
  width: 80px;
  display: flex;
  justify-content: center;
  align-items: center; 
  border-radius: 5px;
  border: 2px dashed #ddd;

    img{
    width: 80px;
    height: 80px;
    border-radius: inherit;

  }
  
}

.avatar-uploader .el-upload:hover {
  border: 2px dashed #409eff;
}

.avatar {
  display: block;
}
</style>