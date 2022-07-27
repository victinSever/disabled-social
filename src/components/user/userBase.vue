<template>
  <div>
    <el-row>
      <el-form label-position="right" label-width="100px" ref="userBaseForm" :model="data" :rules="rules" size="mini">
        <el-col :span="12">
          <el-form-item label="头像" prop="imgPath">
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
          <el-form-item label="角色" prop="role">
            <el-select v-model="data.role" placeholder="请选择用户权限">
              <el-option
                v-for="item in roles"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="用户名" prop="userName">
            <el-input v-model="data.userName" placeholder="输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="残疾证号" prop="disableNumber">
            <el-input
              v-model="data.disableNumber"
              placeholder="输入残疾证号"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="sex">
            <el-radio v-model="data.sex" label="0">男</el-radio>
            <el-radio v-model="data.sex" label="1">女</el-radio>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input-number
              v-model="data.age"
              controls-position="right"
              :min="18"
              :max="100"
            ></el-input-number>
          </el-form-item>
          <el-form-item label="实名认证" prop="isRight">
            <el-radio v-model="data.isRight" label="0">通过</el-radio>
            <el-radio v-model="data.isRight" label="1">未通过</el-radio>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input
              v-model="data.phone"
              placeholder="输入手机号"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: {
        imgPath: require("@/assets/images/user.jpeg"),
        role: 0,
        userName: this.$uuid().replaceAll("-",""),
        sex: "0",
        age: "",
        phone: "",
        disableNumber: "",
        isVip: 0,
        isRight: '0'
      },

      rules: {
        imgPath: [
          { required: true, message: "请选择一张真人头像", trigger: "change" },
        ],
        role: [
          { required: true, message: "请选择身份", trigger: "change" },
        ],
        userName: [{ required: true, message: "请输入名称", trigger: "blur" }],
        sex: [{ required: true, message: "请选择性别", trigger: "change" }],
        age: [{ required: true, message: "请输入年龄", trigger: "blur" }],
        phone: [{ required: true, message: "请输入手机号", trigger: "blur" }],
        disableNumber: [{ required: true, message: "请输入残疾证号", trigger: "blur" }],
        isRight: [{ required: true, message: "请选择状态", trigger: "change" }],
      },

      // 角色身份
      roles: [
        {
          value: 0,
          label: "普通用户",
        },
        {
          value: 1,
          label: "管理员",
        },
        {
          value: 2,
          label: "审核员",
        },
      ],
    };
  },
  methods: {
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
    // 验证表单
    validateFrom(){
      let flag = false
      this.$refs['userBaseForm'].validate((valid) => {
        if (valid) {
          flag = true
        } else {
          this.$message.warning('数据填写有误');
          flag = false
        }
      });
      return flag
    },
    returnData(){
        if(this.validateFrom()){
          return this.data
        } else{
          return false
        }
    }
  },
};
</script>

<style scoped lang='less'>
.el-input {
  width: 70%;
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