<template>
  <div class="root">
    <el-row>
      <el-col :span="8" :offset="1">
        <el-card class="card">
          <div class="block">
            <el-avatar
              shape="square"
              :size="80"
              :src="personData.imgPath"
            ></el-avatar>
          </div>
          <div class="self">
            <h2>{{ personData.userName }}</h2>
            <p>{{ personData.userSaying }}</p>
          </div>
          <div class="shenfen">
            <p>
              <i class="el-icon-user"></i>
              <span>{{ personData.role }}</span>
            </p>
            <p>
              <i class="el-icon-star-off"></i>
              <span>{{ personData.department }}</span>
            </p>
          </div>
          <div class="tags">
            <h4>标签</h4>
            <el-tag
              type="info"
              size="mini"
              v-for="(item, i) in personData.tags"
              :key="i"
              >{{ item }}</el-tag
            >
          </div>
        </el-card>
      </el-col>
      <el-col class="info" :span="14" :offset="1">
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
        >
          <el-menu-item index="1">基础信息</el-menu-item>
          <el-menu-item index="2">账号绑定</el-menu-item>
        </el-menu>
        <div v-if="activeIndex == '1'">
          <el-form
            ref="formperson"
            :model="personData"
            label-position="right"
            label-width="100px"
            :rules="rules"
          >
            <el-col :span="12">
              <el-form-item label="用户名" prop="userName">
                <el-input
                  v-model="personData.userName"
                  placeholder="输入用户名"
                  clearable
                ></el-input>
              </el-form-item>
              <el-form-item label="角色" prop="role">
                <el-select
                  v-model="personData.role"
                  placeholder="请选择角色权限"
                >
                  <el-option label="" value="0"></el-option>
                  <el-option label="审核员" value="1"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="部门" prop="role">
                <el-select
                  v-model="personData.department"
                  placeholder="请选择部门"
                >
                  <el-option label="运营部门" value="0"></el-option>
                  <el-option label="审核部门" value="1"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="名字" prop="name">
                <el-input
                  v-model="personData.name"
                  placeholder="输入名字"
                  clearable
                ></el-input>
              </el-form-item>
              <el-form-item label="性别" prop="sex">
                <el-select v-model="personData.sex" placeholder="请选择性别">
                  <el-option label="男" value="0"></el-option>
                  <el-option label="女" value="1"></el-option>
                  <el-option label="保密" value="3"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="电话" prop="phone">
                <el-input
                  v-model="personData.phone"
                  placeholder="输入电话"
                  clearable
                ></el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="email">
                <el-input
                  v-model="personData.email"
                  placeholder="输入邮箱"
                  clearable
                ></el-input>
              </el-form-item>
              <el-form-item label="个人简介" prop="introduction">
                <el-input
                  v-model="personData.introduction"
                  type="textarea"
                  rows="5"
                  placeholder="输入个人简介"
                  clearable
                ></el-input>
              </el-form-item>
              <el-form-item>
                <el-divider></el-divider>
                <el-button type="primary" @click="changeAdminInfo"
                  >确定</el-button
                >
              </el-form-item>
            </el-col>
          </el-form>
        </div>
        <div v-else class="binding">
          <div class="section">
            <div class="left-box">
              <p>密保手机</p>
              <p class="text">已绑定手机：{{ personData.phone }}</p>
            </div>
            <div class="right-box">
              <span>去修改</span>
            </div>
          </div>
          <el-divider></el-divider>
          <div class="section">
            <div class="left-box">
              <p>密保邮箱</p>
              <p class="text">已绑定邮箱：{{ personData.email }}</p>
            </div>
            <div class="right-box">
              <span>去修改</span>
            </div>
          </div>
          <el-divider></el-divider>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { changeInfo, getAdministrator } from "@/api/manage";
export default {
  data() {
    return {
      activeIndex: "1",

      personData: {
        imgPath: require("@/assets/images/user.jpeg"),
        userName: "鲲鹏",
        introduction:
          "没有谁的幸运，凭空而来，只有当你足够努力，你才会足够幸运。这世界不会辜负每一份努力和坚持，时光不会怠慢执着而勇敢的每一个人！",
        role: "0",
        department: "审核部门",
        tags: ["爱吃冰激凌", "工作达人", "上班族"],
        name: "张三", 
        sex: "0",
        phone: "184151955552",
        email: "javac@163.com",
        place: "重庆市巴南区理工大学",
      },
      rules: {
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
        userName: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        phone: [{ required: true, message: "请输入电话", trigger: "blur" }],
        introduction: [
          { required: true, message: "请输入个人介绍", trigger: "blur" },
        ],
        tabs: [{ required: true, message: "请输入标签", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.personData = this.$store.state.userInfo;
  },
  computed: {
    changeData() {
      let tab = "";
      this.personData.tags = [];
      this.personData.tags.forEach((item) => {
        tab += item;
      });
      this.personData.tab = tab;
      return this.personData;
    },
  },
  methods: {
    handleSelect(key, keyPath) {
      this.activeIndex = keyPath + "";
    },
    
    //修改管理员信息
    changeAdminInfo() {
      this.$refs["formperson"].validate((valid) => {
        if (valid) {
          this.sendChangeInfo();
        } else {
          this.$message.warning("数据填写有误");
        }
      });
    },
    async sendChangeInfo() {
      this.$nprogress.start()
      const res = await changeInfo(this.changeData);
      this.$nprogress.done()
      if (res.status !== 200) {
        this.$message.error("修改失败！");
      } else {
        this.$message.success("修改成功！");
      }
    },
    // 获取管理员信息
    async getInfo(){
      const res = await getAdministrator({
        id: this.personData.id
      });
    }
  },
};
</script>

<style scoped lang='less'>
.card {
  .self {
    h2 {
      margin-top: 20px;
    }

    p {
      margin-top: 20px;
      font-size: 14px;
      color: #777;
      text-indent: 2rem;
      line-height: 30px;
    }
  }

  .shenfen {
    p {
      margin-top: 20px;
      font-size: 14px;

      i {
        margin-right: 10px;
      }
    }
  }

  .tags {
    margin-top: 20px;

    h4 {
      margin-bottom: 10px;
    }

    .el-tag {
      margin-right: 10px;
    }
  }
}
.info {
  border: 1px solid #eee;
  padding: 20px;

  .el-menu-demo {
    margin-bottom: 20px;
  }

  .binding {
    .section {
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: 14px;
      height: 60px;

      .left-box {
        p {
          line-height: 30px;
        }

        .text {
          color: rgb(160, 158, 158);
        }
      }

      .right-box {
        color: blue;
        cursor: pointer;
      }

      .right-box:hover {
        color: #000;
      }
    }
  }
}
</style>