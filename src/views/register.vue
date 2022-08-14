<template>
  <div class="register">
    <div class="topBox">
      <span>已有账号？ <router-link to="/login">去登陆</router-link></span>
    </div>

    <div class="form-box2">
      <el-card>
        <el-form ref="formRef" :rules="formRules" status-icon :model="formData">
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
          <el-form-item prop="username">
            <el-input
              prefix-icon="el-icon-user"
              v-model="formData.username"
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
          <!-- 密码 -->
          <el-form-item prop="passwordS">
            <el-input
              show-password
              prefix-icon="el-icon-suitcase"
              v-model="formData.passwordS"
              placeholder="验证密码"
            ></el-input>
          </el-form-item>

          <el-form-item>
            <div class="role-and-depar">
              <!-- 部门 -->
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
            >

            <!-- 管理员等级 -->
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
            </div>
          </el-form-item>
          <!-- 注册按钮 -->
          <el-form-item>
            <div class="form-commit">
              <el-button
              type="primary"
              icon="el-icon-finished"
              @click="submitFormData"
              >注册</el-button
            >
            <el-button icon="el-icon-refresh" type="primary" @click="resetForm"
              >重置</el-button
            >
            </div>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import { register } from '@/api/manage'
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
      formData: {
        account: "",
        username: "",
        password: "",
        passwordS: "",
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
        username: [
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
        { label: "开发部", value: "0", disabled: false },
        { label: "运维部", value: "1", disabled: false },
        { label: "策划部", value: "2", disabled: false },
        { label: "市场部", value: "3", disabled: false },
        { label: "管理部", value: "4", disabled: false },
        { label: "信息技术支持部", value: "5", disabled: false },
      ], //部门数据
      roles: [
        { label: "管理员", value: "1", disabled: false },
        { label: "审核员", value: "2", disabled: false },
        { label: "超级管理员", value: "3", disabled: true },
      ], //部门数据
    };
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
      try {
        const res = await register(this.formData);
        if (res.status === 200) {
          this.resetForm();
          this.$message.success("注册成功！正返回登陆页面");
          let that = this
          setTimeout(function(){
            that.$router.push("/login");
          },2000)         
        }
      } catch (err) {
        this.$message.error("注册失败！错误：" + err);
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

<style scoped lang='less'>


.register {
    /* 页面宽度为百分之百页面大小，几位一个视角长 */
    height: 100vh;
    width: 100vw;
    /* 弹性布局，水平居中 */
    display: flex;
    justify-content: center;
    align-items: center;
    background:linear-gradient(45deg, #777,#fff,#eee,#ddd);
    /* 溢出隐藏 */
    overflow: hidden;
}



.form-box2 {
    background-color: #000;
    border-radius: 15px;
    /* 背景阴影 */
    backdrop-filter: blur(20px);
    /* 阴影 */
    box-shadow: 0 20px 50px rgba(0, 0, 0, 0.5);
}

.form-box2 .title {
    text-align: center;
    letter-spacing: 2px;
    color: #74b9ff;
    margin: 20px 0;
    font-size: 20px;
}

.form-box2 input {
    /* width: 300px; */
    height: 30px;
    /* background: transparent; */
    text-indent: 8px;
    border: none;
    border-radius: none;
    outline: none;
    border-bottom: 1px solid rgba(255, 255, 255, 0.8);
    margin: 15px auto;
}

.form-box2 input::placeholder {
    /* color: rgba(170, 169, 169, 0.8); */
    letter-spacing: 2px;
}

.role-and-depar{
  display: flex;
  justify-content: space-around;
}


.form-commit{
  display: flex;
  justify-content: space-around;
  button{
    width: 150px;
    border-radius: 15px;
    font-weight: bold;
    letter-spacing: 2px;
  }
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