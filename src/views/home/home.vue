<template>
  <div class="home">
    <!-- 主体部分 -->
    <el-container>
      <el-aside
        :width="isCollapse ? '64px' : '200px'"
        style="min-height: 100vh; background-color: #272c33"
      >
        <div class="header-title">
          <img src="@/assets/images/space2.jpg" alt="" />
          <h3 v-if="!isCollapse">残疾人婚恋APP管理</h3>
        </div>
        <!--展开/收起-->
        <div class="toggle_box" @click="toggleCollapse">|||</div>

        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          background-color="#001529"
          text-color="#fff"
          active-text-color="#ffd04b"
          :collapse="isCollapse"
          :collapse-transition="false"
          :unique-opened="true"
          :router="true"
        >
          <!--菜单树（组件）-->
          <MenuTree :menuList="menuList"></MenuTree>
        </el-menu>
      </el-aside>

      <el-container class="left-main">
        <!-- 头部 -->
        <el-header class="home-header">

          <!--面包屑导航-->
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item v-for="(item, i) in breadcrumb" :key="i" :to="item.path">{{item.label}}</el-breadcrumb-item>
          </el-breadcrumb>

          <!-- 右侧信息按钮 -->
          <div class="home-header-Info">
            <div
              class="user_avatar_search user_avatar_i"
              @click="$router.push('/products')"
            >
              <i class="el-icon-search"></i>
            </div>
            <div
              class="user_avatar_bell user_avatar_i"
              @click="$router.push('/logs')"
            >
              <el-tooltip
                class="item"
                effect="light"
                transition="el-fade-in-linear"
                placement="bottom"
              >
                <div slot="content">
                  <span v-if="messageNum != 0"
                    >你有{{ messageNum }}则新的消息未查看哦！</span
                  >
                  <span v-else>暂时没有需要查阅的邮件！</span>
                </div>
                <el-badge
                  v-if="messageNum != 0"
                  :value="emailNum"
                  class="item"
                  type="primary"
                >
                  <i class="el-icon-bell"></i>
                </el-badge>
                <el-badge v-else is-dot class="item">
                  <i class="el-icon-bell"></i>
                </el-badge>
              </el-tooltip>
            </div>
            <div
              class="user_avatar_message user_avatar_i"
              @click="$router.push('/loginLog')"
            >
              <el-tooltip
                class="item"
                effect="light"
                transition="el-fade-in-linear"
                placement="bottom"
              >
                <div slot="content">
                  <span v-if="emailNum != 0"
                    >你有{{ emailNum }}封未读邮件！</span
                  >
                  <span v-else>暂时没有需要查阅的邮件！</span>
                </div>
                <el-badge
                  v-if="emailNum != 0"
                  :value="emailNum"
                  class="item"
                  type="primary"
                >
                  <i class="el-icon-message"></i>
                </el-badge>
                <el-badge v-else is-dot class="item" type="primary">
                  <i class="el-icon-message"></i>
                </el-badge>
              </el-tooltip>
            </div>
            <el-dropdown>
              <el-avatar class="user_avatar_img" style="cursor: pointer">
                <img src="@/assets/images/user.jpeg" alt />
              </el-avatar>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <span
                    class="el-icon-s-home"
                    @click="$router.push({ name: 'home' })"
                    >系统首页</span
                  >
                </el-dropdown-item>
                <el-dropdown-item>
                  <span class="el-icon-s-tools" @click="systemSetting"
                    >系统设置</span
                  >
                </el-dropdown-item>
                <el-dropdown-item>
                  <span @click="loginout" class="el-icon-caret-right"
                    >退出登录</span
                  >
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>

        <!-- 主体 -->
        <el-main class="mainRouter">
          <router-view />
        </el-main>

        <!-- 页脚 -->
        <Footer></Footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import MenuTree from "@/components/menuTree";
import Footer from "@/components/footer";
export default {
  name: "home",
  components: {
    MenuTree,Footer
  },
  data() {
    return {
      // 面包屑导航数据
      breadcrumb: [{
        path: '',
        label: '控制面板'
      },{
        path: '/dashboard/workplace',
        label: '工作台'
      }],
      isCollapse: false, //控制左侧导航展开和伸缩
      // tabbar菜单
      // 图标：
      menuList: [], //菜单栏数据
      activepath: "", //点击二级菜单获取的下标
      messageNum: 0, //消息数量
      emailNum: 0, //邮件数量
    };
  },

  mounted() {
    this.getMenuLists();
    this.changeBreadcrumb()
  },
  methods: {
    // 改变面包屑的数据
    changeBreadcrumb(){     
      let that = this
      this.$bus.$on('changeBreadcrumb', breadcrumb => {
        that.breadcrumb = breadcrumb
        console.log(breadcrumb);
      })
      
    },

    //获取左侧菜单栏数据
    async getMenuLists() {
      // const { data: res } = await this.$http.get("user/findMenu");
      // if (res.code !== 200) {
      //   return this.$message.error("获取左侧菜单栏数据失败");
      // } else {
      //   this.menulist = res.data;
      // }
      let menulist = this.$store.state.menuList;
      this.menuList = menulist
    },

    // 左侧导航栏是否坍塌
    toggleCollapse() {
      this.isCollapse = !this.isCollapse;
    },

    //退出登录
    loginout() {
      this.$router.push("/login");
      localStorage.clear();
      sessionStorage.clear(); //清除缓存
    },

    //系统设置
    systemSetting() {
      this.$message.warning("该功能未开放！");
    },
  },
};
</script>

<style scoped lang="less">
// 左侧aside栏
.header-title {
  background-color: #001529;
  width: 100%;
  height: 150px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;

  img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }

  h3 {
    display: inline-block;
    height: 40px;
    line-height: 40px;
    color: #fff;
  }
}

.el-aside {
  transition: 0.3s;
}

.el-menu {
  border-right: none !important;
  background: #272c33 !important;
}

/*展开/收起*/

.toggle_box {
  background-color: #001529;
  font-size: 15px;
  font-weight: bold;
  line-height: 24px;
  color: #ffffff;
  letter-spacing: 0.2em;
  text-align: center;
  cursor: pointer;
}

// 主窗口
.left-main{
  background: #fff url(../../assets/images/role-bg.png) no-repeat;
  background-size: 150% 150%;
}


.home-header {
  width: 100%;
  overflow: hidden;
  border-bottom: 1px solid #eee;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .home-header-Info {
    float: right;
    display: flex;
    align-items: center;
    height: 60px;
    padding-right: 30px;
  }

  .user_avatar_i {
    width: 50px;
    font-size: 23px;
    cursor: pointer;
  }

  .user_avatar_i:hover {
    color: skyblue;
  }
}
</style>