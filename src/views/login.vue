<template>
  <div class="body">
    <!-- top -->
    <div class="topBox">
      <span>没有账号？ <router-link to="/register">去注册</router-link></span>
    </div>

    <!-- 背景 -->
    <section class="sky">
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
      <span class="start"></span>
    </section>

    <!-- 盒子 -->
    <div class="form-box">
      <el-card>
        <marquee
          loop="2"
          width="360px"
          scrollAmount="2"
          onMouseOver="this.stop()"
          onMouseOut="this.start()"
        >
          没有账号？请点击右上角注册
        </marquee>
        <el-form
          ref="formRef"
          :rules="formRules"
          status-icon
          :model="formData"
          @submit.native.prevent
        >
          <!-- 标题 -->
          <h1 class="title">残疾人婚恋平台</h1>
          <!-- 用户名 -->
          <el-form-item prop="account">
            <el-input
              prefix-icon="el-icon-user"
              v-model="formData.userName"
              placeholder="账号"
              @keyup.enter.native="submitFormData"
              maxlength="20"
            ></el-input>
          </el-form-item>
          <!-- 密码 -->
          <el-form-item prop="password">
            <el-input
              show-password
              prefix-icon="el-icon-suitcase"
              v-model="formData.password"
              placeholder="密码"
              maxlength="20"
              @keyup.enter.native="submitFormData"
            ></el-input>
          </el-form-item>

          <el-form-item>
            <div class="check">
              <span @click="gotoSearchPassward">忘记密码</span>
              <!-- 记住密码 -->
              <el-checkbox
                v-model="checked"
                style="margin-left: 100px; color: #fefefe"
                >记住密码</el-checkbox
              >
            </div>
          </el-form-item>

          
          <!-- 登录按钮 -->
          <el-form-item class="form-commit">
            <el-button
              type="primary"
              icon="el-icon-finished"
              @click="submitFormData"
              >登录</el-button
            >
            <el-button icon="el-icon-refresh" type="primary" @click="resetForm"
              >重置</el-button
            >
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "login",
  data() {
    return {
      checked: false, //是否记住密码
      formData: {
        userName: "admin",
        password: "123456",
      },
      formRules: {
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 1,
            max: 11,
            message: "长度在 1 到 11 个字符",
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
      },

      //管理员菜单栏数据
      menuList: [
        {
          id: 1,
          parentId: 0,
          menuName: "控制面板",
          parentName: "",
          url: "",
          icon: "el-icon-house",
          orderNum: 1,
          open: 1,
          disabled: false,
          perms: null,
          type: 0,
          children: [
            {
              id: 11,
              parentId: 1,
              menuName: "工作台",
              parentName: "控制面板",
              url: "/dashboard/workplace",
              icon: "el-icon-monitor",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "welcome:view",
              type: 0,
              children: [],
            },
            {
              id: 12,
              parentId: 1,
              menuName: "分析页",
              parentName: "控制面板",
              url: "/dashboard/analysis",
              icon: "el-icon-data-analysis",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "welcome:view",
              type: 0,
              children: [],
            },
            {
              id: 13,
              parentId: 1,
              menuName: "监控",
              parentName: "控制面板",
              url: "/dashboard/monitor",
              icon: "el-icon-monitor",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "welcome:view",
              type: 0,
              children: [],
            },
          ],
        },
        {
          id: 2,
          parentId: 0,
          menuName: "系统管理",
          parentName: "",
          url: "/system",
          icon: "el-icon-user",
          orderNum: 2,
          open: 0,
          disabled: false,
          perms: "users",
          type: 0,

          children: [
            {
              id: 21,
              parentId: 2,
              menuName: "管理员管理",
              parentName: "系统管理",
              url: "/system/admin",
              icon: "el-icon-date",
              orderNum: 1,
              open: 1,
              disabled: false,
              perms: "el-icon-date",
              type: 0,
              children: [],
            },

            {
              id: 23,
              parentId: 2,
              menuName: "菜单管理",
              parentName: "系统管理",
              url: "/system/menu",
              icon: "el-icon-date",
              orderNum: 1,
              open: 1,
              disabled: false,
              perms: "el-icon-date",
              type: 0,
              children: [],
            },
          ],
        },
        {
          id: 3,
          parentId: 0,
          menuName: "业务管理",
          parentName: "",
          url: "/business",
          icon: "el-icon-platform-eleme",
          orderNum: 3,
          open: 0,
          disabled: false,
          perms: "",
          type: 0,
          children: [
            {
              id: 31,
              parentId: 3,
              menuName: "广告管理",
              parentName: "业务管理",
              url: "/business/adver",
              icon: "el-icon-s-opportunity",
              orderNum: 1,
              open: 1,
              disabled: false,
              perms: "",
              type: 0,
              children: [],
            },
            {
              id: 32,
              parentId: 3,
              menuName: "广告位管理",
              parentName: "业务管理",
              url: "/business/adSort",
              icon: "el-icon-s-opportunity",
              orderNum: 1,
              open: 1,
              disabled: false,
              perms: "",
              type: 0,
              children: [],
            },
            {
              id: 33,
              parentId: 3,
              menuName: "活动管理",
              parentName: "业务管理",
              url: "/business/activity",
              icon: "el-icon-dessert",
              orderNum: 2,
              open: 1,
              disabled: false,
              perms: null,
              type: 0,
              children: [],
            },
            {
              id: 35,
              parentId: 3,
              menuName: "节日庆典",
              parentName: "业务管理",
              url: "/business/celebration",
              icon: "el-icon-sugar",
              orderNum: 2,
              open: 1,
              disabled: false,
              perms: null,
              type: 0,
              children: [],
            },
            {
              id: 36,
              parentId: 3,
              menuName: "VIP充值",
              parentName: "业务管理",
              url: "/business/vip",
              icon: "el-icon-star-on",
              orderNum: 2,
              open: 1,
              disabled: false,
              perms: null,
              type: 0,
              children: [],
            },
          ],
        },
        {
          id: 7,
          parentId: 0,
          menuName: "用户管理",
          parentName: "",
          url: "",
          icon: "el-icon-user",
          orderNum: 5,
          open: 0,
          disabled: false,
          perms: "",
          type: 0,
          children: [
            {
              id: 71,
              parentId: 7,
              menuName: "用户信息",
              parentName: "用户管理",
              url: "/user/commontUser",
              icon: "el-icon-user",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "",
              type: 0,
              children: [],
            },
          ],
        },
        {
          id: 8,
          parentId: 0,
          menuName: "个人中心",
          parentName: "",
          url: "",
          icon: "el-icon-user",
          orderNum: 8,
          open: 0,
          disabled: false,
          perms: null,
          type: 0,
          children: [
            {
              id: 81,
              parentId: 8,
              menuName: "个人中心",
              parentName: "个人中心",
              url: "/person/personData",
              icon: "el-icon-user",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "login:log",
              type: 0,
              children: [],
            },

          ],
        },
        {
          id: 5,
          parentId: 0,
          menuName: "日志管理",
          parentName: "",
          url: "",
          icon: "el-icon-camera",
          orderNum: 6,
          open: 0,
          disabled: false,
          perms: null,
          type: 0,
          children: [
            {
              id: 51,
              parentId: 5,
              menuName: "登入日志",
              parentName: "日志管理",
              url: "/log/loginLog",
              icon: "el-icon-date",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "login:log",
              type: 0,
              children: [],
            },
            {
              id: 52,
              parentId: 5,
              menuName: "操作日志",
              parentName: "日志管理",
              url: "/log/opeartionLog",
              icon: "el-icon-edit",
              orderNum: 1,
              open: 1,
              disabled: false,
              perms: "",
              type: 0,
              children: [],
            },
          ],
        },
      ],
      // 审核员菜单栏数据
      menuList2: [
        {
          id: 1,
          parentId: 0,
          menuName: "控制面板",
          parentName: "",
          url: "",
          icon: "el-icon-house",
          orderNum: 1,
          open: 1,
          disabled: false,
          perms: null,
          type: 0,
          children: [
            {
              id: 11,
              parentId: 1,
              menuName: "工作台",
              parentName: "控制面板",
              url: "/dashboard/workplace",
              icon: "el-icon-monitor",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "welcome:view",
              type: 0,
              children: [],
            },
            {
              id: 12,
              parentId: 1,
              menuName: "分析页",
              parentName: "控制面板",
              url: "/dashboard/analysis",
              icon: "el-icon-data-analysis",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "welcome:view",
              type: 0,
              children: [],
            },
            {
              id: 13,
              parentId: 1,
              menuName: "监控",
              parentName: "控制面板",
              url: "/dashboard/monitor",
              icon: "el-icon-monitor",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "welcome:view",
              type: 0,
              children: [],
            },
          ],
        },
        {
          id: 2,
          parentId: 0,
          menuName: "审核模块",
          parentName: "",
          url: "",
          icon: "el-icon-house",
          orderNum: 1,
          open: 1,
          disabled: false,
          perms: null,
          type: 0,
          children: [
            {
              id: 21,
              parentId: 2,
              menuName: "动态审核",
              parentName: "审核模块",
              url: "/audit/activeAudit",
              icon: "el-icon-monitor",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "welcome:view",
              type: 0,
              children: [],
            },
            {
              id: 22,
              parentId: 2,
              menuName: "信息审核",
              parentName: "审核模块",
              url: "/audit/infoAudit",
              icon: "el-icon-data-analysis",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "welcome:view",
              type: 0,
              children: [],
            },
            {
              id: 23,
              parentId: 2,
              menuName: "视频审核",
              parentName: "审核模块",
              url: "/audit/vedioAudit",
              icon: "el-icon-monitor",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "welcome:view",
              type: 0,
              children: [],
            },
          ],

        },
        {
          id: 8,
          parentId: 0,
          menuName: "个人中心",
          parentName: "",
          url: "",
          icon: "el-icon-setting",
          orderNum: 8,
          open: 0,
          disabled: false,
          perms: null,
          type: 0,
          children: [
            {
              id: 81,
              parentId: 8,
              menuName: "个人资料",
              parentName: "个人中心",
              url: "/person/personData",
              icon: "el-icon-user",
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: "login:log",
              type: 0,
              children: [],
            },

          ],
        },
      ],
    };
  },
  mounted() {},
  methods: {
    ...mapMutations(["setUserInfo", "setMenuList"]),

    // 找回密码
    gotoSearchPassward(){
      this.$message.warning('暂未开放找回密码通道！')
    },

    //提交表单
    submitFormData() {
      this.$refs.formRef.validate((valid) => {
        if (!valid) {
          return this.$message.warning("输入数据不合法！");
        } else {
          // 数据验证合法，发送请求
          this.sendData(this.formData);
        }
      });
    },

    // 发送请求获取管理员信息
    sendData(data) {
      // 发送请求

      // 获取管理员基本信息
      this.formData = {
        ...this.formData,
        userName: "李华",
        userImg: require("@/assets/images/user.jpeg"),
        userSex: 1,
        userPhone: 18140267749,
        role: 1, //角色信息
        department: 1, //部门信息
        status: true,
        createTime: "2022-7-23 18:30:55",
        updateTime: "2022-7-23 18:30:55",
      };
      if (data.userName != "admin") {
        this.formData.role = 2;
      }

      // 向vuex中存储个人信息和菜单栏信息
      this.setUserInfo(this.formData);
      this.setMenuList(this.menuList);

      if (data.userName != "admin") {
        this.setMenuList(this.menuList2);
      }

      // 存储token
      sessionStorage.setItem("token", "sfdgsbfjhsFBDSAHJFSDFL");
      // 跳转至主页
      this.$router.push("/dashboard/workplace");
      this.$message.success(
        (this.formData.role === 1 ? "管理员" : "审核员") +
          this.formData.userName +
          "，欢迎登录"
      );
    },

    //重置表单
    resetForm() {
      this.$refs.formRef.resetFields();
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
@import url(../assets/css/login-style.css);

.check{
  display:flex;
  justify-content: center;
}

.check span{
  color: skyblue;
  cursor: pointer;
}

.check span:hover{
  color: #fefefe;
}

.body {
}

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

.el-card {
  width: 400px;
  height: 380px;
  background-color: rgba(255, 255, 255, 0.1);
  border: none;
  border-radius: 15px;
}

.form-commit {
  margin-top: 15px;
  display: flex;
  justify-content: center;
}

.el-input__inner {
  border-radius: none;
}

marquee {
  position: absolute;
  top: 10px;
  background: transparent;
  color: rgba(170, 169, 169, 0.8);
  font-size: 12px;
}
</style>