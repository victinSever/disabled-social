<template>
  <div class="register">
    <div class="topBox">
      <span>已有账号？ <router-link to="/login">去登陆</router-link></span>
    </div>
    <div class="containerBox">
      <!-- 背景 -->
      <div class="wrap">
        <div class="wall wall-front"></div>
        <div class="wall wall-top"></div>
        <div class="wall wall-bottom"></div>
        <div class="wall wall-left"></div>
        <div class="wall wall-right"></div>
        <div class="wall wall-back"></div>
      </div>

      <div class="form-box2">
        <el-card>
          <el-form
            ref="formRef"
            :rules="formRules"
            status-icon
            :model="formData"
          >
            <!-- 标题 -->
            <h1 class="title">管理员注册</h1>
            <!-- 账号 -->
            <el-form-item prop="account">
              <el-input
                prefix-icon="el-icon-user"
                v-model="formData.account"
                placeholder="登陆账号"
              ></el-input>
            </el-form-item>
            <!-- 用户名 -->
            <el-form-item prop="userName">
              <el-input
                prefix-icon="el-icon-user"
                v-model="formData.userName"
                placeholder="用户名"
              ></el-input>
            </el-form-item>
            <!-- 密码 -->
            <el-form-item prop="password">
              <el-input
                show-password
                prefix-icon="el-icon-suitcase"
                v-model="formData.password"
                placeholder="密码"
              ></el-input>
            </el-form-item>
            <!-- 验证密码 -->
            <el-form-item prop="passwordS">
              <el-input
                show-password
                prefix-icon="el-icon-suitcase"
                v-model="formData.passwordS"
                placeholder="确认密码"
              ></el-input>
            </el-form-item>
            <!-- 4位验证码 -->
            <el-form-item prop="verifyCode">
              <div style="display: flex">
                <el-input
                  v-model="formData.verifyCode"
                  prefix-icon="el-icon-mobile"
                  class="verify_code"
                  placeholder="请输入验证码"
                ></el-input>
                <img
                  :src="imgCode"
                  alt=""
                  class="verify_img"
                  style="
                    margin-left: 10px;
                    cursor: pointer;
                    height: 40px;
                    border-radius: 10px;
                  "
                  @click="uploadeImgCode"
                />
              </div>
            </el-form-item>
            <el-form-item>
              <!-- 部门 -->
              <span style="float: left"
                ><span
                  class="el-icon-mobile"
                  style="color: rgba(255, 255, 255, 0.8); margin: 0 10px"
                ></span>
                <el-select
                  v-model="formData.department"
                  placeholder="部门"
                  style="width: 100px"
                  clearable
                >
                  <el-option
                    v-for="item in departments"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    :disabled="item.disabled"
                  >
                  </el-option> </el-select
              ></span>

              <!-- 管理员等级 -->
              <span style="float: right">
                <span
                  class="el-icon-mobile"
                  style="color: rgba(255, 255, 255, 0.8); margin: 0 10px"
                ></span>
                <el-select
                  v-model="formData.role"
                  placeholder="管理员等级"
                  style="width: 150px"
                  clearable
                >
                  <el-option
                    v-for="item in roles"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    :disabled="item.disabled"
                  >
                  </el-option>
                </el-select>
              </span>
            </el-form-item>
            <!-- 注册按钮 -->
            <el-form-item class="form-commit">
              <el-button
                type="primary"
                icon="el-icon-finished"
                @click="submitFormData"
                >注册</el-button
              >
              <el-button
                icon="el-icon-refresh"
                type="primary"
                @click="resetForm"
                >重置</el-button
              >
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "register",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.formData.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      imgCode: "",
      code: "",
      formData: {
        account: "",
        userName: "",
        password: "",
        passwordS: "",
        verifyCode: "",
        department: "",
        role: "",
      },
      formRules: {
        account: [
          { required: true, message: "请输入账号", trigger: "blur" },
          {
            min: 3,
            max: 11,
            message: "长度在 8 到 11 个字符",
            trigger: "blur",
          },
        ],
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 3,
            max: 11,
            message: "长度在 3 到 11 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 4,
            max: 15,
            message: "长度在 4 到 15 个字符",
            trigger: "blur",
          },
        ],
        passwordS: [
          { required: true, message: "请再次输入", trigger: "blur" },
          {
            min: 4,
            max: 15,
            message: "长度在 4 到 15 个字符",
            trigger: "blur",
          },
          {
            validator: validatePass,
            message: "两次输入密码不正确",
            trigger: "blur",
            required: true,
          },
        ],
        verifyCode: [
          { required: true, message: "请输入验证码", trigger: "blur" },
          { min: 4, max: 4, message: "长度为4个字符", trigger: "blur" },
        ],
        department: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        role: [{ required: true, message: "请输入用户名", trigger: "blur" }],
      },
      departments: [
        { label: "开发部", value: "开发部", disabled: false },
        { label: "运维部", value: "运维部", disabled: false },
        { label: "策划部", value: "策划部", disabled: false },
        { label: "市场部", value: "市场部", disabled: false },
        { label: "管理部", value: "管理部", disabled: false },
        { label: "信息技术支持部", value: "信息技术支持部", disabled: false },
      ], //部门数据
      roles: [
        { label: "管理员", value: "管理员", disabled: false },
        { label: "部门主管", value: "部门主管", disabled: false },
        { label: "超级管理员", value: "超级管理员", disabled: true },
      ], //部门数据
    };
  },
  mounted() {
    this.uploadeImgCode();
  },
  methods: {
    //提交表单
    submitFormData() {
      this.$refs.formRef.validate((valid) => {
        if (!valid) {
          return;
        } else {
          this.sentData();
        }
      });
    },

    //注册信息发送
    async sentData() {
      //判断验证码的正误
      if (this.code !== this.formData.verifyCode.toLowerCase()) {
        this.isShow = false;
        return this.$message.error("验证码出错！");
      }

      const data = {
        account: this.formData.account,
        userName: this.formData.userName,
        password: this.formData.password,
        department: this.formData.department,
        role: this.formData.role,
      };
      try {
        const res = await this.$http.post("user/register", data);
        if (res.status === 200) {
          this.resetForm();
          this.$message.success("注册成功！正返回登陆");
          this.$router.push("/login");
        }
      } catch (err) {
        this.$message.error("注册失败！错误：" + err);
      }
    },

    //加载图形验证码
    async uploadeImgCode() {
      try {
        const res = await this.$http({
          method: "get",
          url: "user/showCode",
          responseType: "arraybuffer",
        });
        if (res.status === 200) {
          let binaryData = [];
          binaryData.push(res.data);
          this.imgCode = window.URL.createObjectURL(new Blob(binaryData)); //获取验证码图片
          this.code = res.headers.code.toLowerCase();
        } else {
          this.$message.error("访问错误！");
        }
      } catch (err) {
        this.$message.error("悄悄告诉你，服务器偷偷开小差去了！");
      }
    },

    //重置表单
    resetForm() {
      this.$refs.formRef.resetFields();
      this.formData.department = "";
      this.formData.role = "";
    },

    // 用户点击遮罩层，应该关闭模态框
    close() {
      this.isShow = false;
    },
  },
};
</script>

<style scoped>
/* 引入特效css */
@import url(../assets/css/register-style.css);

.register {
  overflow: hidden;
  width: 100%;
  height: 100%;
  position: fixed;
  padding-top: calc((100vh - 550px) / 2);
  top: 0;
}


.el-form-item {
  margin: 5px 0;
}

.el-card {
  width: 400px;
  height: 550px;
  background-color: transparent;
  border: none;
}

.form-commit {
  margin-top: 10px;
  display: flex;
  justify-content: center;
}

.el-input__inner {
  border-radius: none;
}

/* 头部链接 */
.topBox {
  position: absolute;
  top: 20px;
  right: 50px;
  z-index: 10;
  transition: 0.8s;
}

.topBox:hover {
  right: 70px;
}

.topBox a {
  transition: 0.4s;
}

.topBox a:hover {
  color: #fff;
}
</style>