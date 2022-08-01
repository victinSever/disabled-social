<template>
  <div>
    <el-card class="table">
      <!-- 顶部按钮 -->
      <div class="table-header">
        <div class="table-header-left">
          <el-button
            type="success"
            class="el-icon-edit"
            size="mini"
            @click="handleAdd()"
            >添加</el-button
          >
          <el-button
            type="primary"
            class="el-icon-edit"
            size="mini"
            @click="handleAddMore()"
            >批量添加</el-button
          >
          <el-button
            type="danger"
            class="el-icon-delete"
            size="mini"
            @click="handleDeleteMore()"
            >删除</el-button
          >
        </div>
        <div class="table-header-right">
          <el-tooltip class="item" effect="dark" content="刷新" placement="top">
            <el-button
              class="el-icon-refresh right-btns"
              size="small"
              @click="refreshPage"
            ></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="密度" placement="top">
            <el-button
              class="el-icon-s-operation right-btns"
              size="small"
            ></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="设置" placement="top">
            <el-button
              class="el-icon-setting right-btns"
              size="small"
            ></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="全屏" placement="top">
            <el-button class="el-icon-rank right-btns" size="small"></el-button>
          </el-tooltip>
        </div>
      </div>

      <!-- 表格 -->
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        border
        @selection-change="handleSelectionChange"
      >

        <el-table-column type="selection" width="55" align="center" fixed>
        </el-table-column>
        <el-table-column
          prop="userId"
          label="ID"
          width="50"
          align="center"
          fixed
        >
        </el-table-column>

        <el-table-column prop="userName" label="姓名" width="80" align="center">
        </el-table-column>
        <el-table-column
          prop="userCount"
          label="用户名"
          width="120"
          align="center"
        >
        </el-table-column>
        <el-table-column label="头像" align="center" width="80">
          <template slot-scope="scope">
            <img
              :src="scope.row.userImg"
              alt="未上传头像"
              style="width: 30px; height: 30px; border-radius: 50%"
            />
          </template>
        </el-table-column>
        <el-table-column
          prop="userPhone"
          label="手机号"
          width="120"
          align="center"
        >
        </el-table-column>
        <el-table-column label="上网时长" align="center" width="150">
          <template slot-scope="scope">
            <span>{{ scope.row.onlineTime + scope.row.onlineTimeUnit }}</span>
          </template>
        </el-table-column>
        <el-table-column label="角色" align="center" width="100">
          <template slot-scope="scope">
            <el-tag size="mini" type="primary" v-if="scope.row.role === 0"
              >普通用户</el-tag
            >
            <el-tag size="mini" type="danger" v-else-if="scope.row.role === 1"
              >管理员</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column label="特权" align="center" width="150">
          <template slot-scope="scope">
            <el-tag size="mini" type="primary" v-if="scope.row.isVip === 0"
              >VIP1</el-tag
            >
            <el-tag size="mini" type="danger" v-else-if="scope.row.isVip === 1"
              >无特权</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column
          prop="status"
          label="认证状态"
          align="center"
          width="100"
        >
          <template slot-scope="scope">
            <i
              class="el-icon-close"
              v-if="scope.row.isRight === 0"
              style="font-size: 20px; color: red"
            ></i>
            <i
              class="el-icon-check"
              v-if="scope.row.isRight === 1"
              style="font-size: 20px; color: green"
            ></i>
          </template>
        </el-table-column>

        <el-table-column
          prop="createTime"
          label="创建时间"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column
          prop="updateTime"
          label="更新时间"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column
          prop="destoryTime"
          label="注销时间"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column label="操作" width="200" align="center" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="el-icon-view"
              @click="openDetail(scope.$index, scope.row)"
              >详细信息</el-button
            >
            <el-button
              size="mini"
              class="el-icon-delete"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="page.pageNum"
          :page-sizes="[
            page.total / 4,
            (page.total / 4) * 2,
            (page.total / 4) * 3,
            page.total,
          ]"
          :page-size="page.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="page.total"
        >
        </el-pagination>
      </div>
    </el-card>

    <!-- 详细信息弹窗 -->
    <el-dialog
      title="用户信息"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
    >
      <UserDetail :personData="personData" />
    </el-dialog>

    <!-- 添加单个用户 -->
    <el-dialog
      title="添加用户"
      :visible.sync="dialogVisible2"
      :before-close="handleClose"
    >
      <userBase ref="userBase"/>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="addUserData()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import UserDetail from "@/components/user/userDetail.vue";
import userBase from "@/components/user/userBase.vue";
import nProgress from 'nprogress'
export default {
  components: {
    UserDetail,userBase
  },
  data() {
    return {
      // 弹窗显示
      dialogVisible: false,//详细信息
      dialogVisible2: false,//添加单个用户
      personData: {
        baseData: {
          role: 0, //0为用户，1为管理员
          isVip: 1, //是否vip
          isRight: 0, //是否认证
          onlineTime: 68, //上网时长
          onlineTimeUnit: "时",
          status: 0, //是否未注销
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          destoryTime: "",

          concern: 12, //关注数
          beConcern: 511, //被关注数
          dianzan: 4, //点赞数
          sorts: 4884, //积分

          personId: '1',
          personName: '花',
          sex: 1,
          age: 26,
          phone: '18412385469',
          imagePath: require("@/assets/images/user.jpeg"),
          disableNumber: 'G500226201599999999',
          workAddr: '理工大学',
          householdAddr: '重庆南岸',
          maritalStatus: '未婚',
          height: 178,
          weight: 120,
          degree: '中专',
          income: 20000,
          occupation: '无业',
          housingStatus: '两套别墅',
          carStatus: '无',
          expectedMarryTime: '半年内',
          personIntro: '我是一个开朗的人',
          personSign: '在天愿作比翼鸟，在地愿为连理枝',
          longitude: 42.5,
          latitude: 85.93,
          wechat: '951616262',
          wechatCodeImagesPath: require("@/assets/images/user.jpeg"),
          qq: '1255115515',
          email: 'dsadahfdsn@qq.com'
        },
        detailData: {
          
        },
        marrayData: {},
      }, //个人信息

      page: {
        pageSize: 10,
        pageNum: 1,
        total: 20,
      },

      // 被选择
      multipleSelection: [],

      // 数据
      tableData: [
        {
          userId: "1",
          userCount: "admin",
          userName: "李华",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 1,
          userPhone: 18140267749,
          role: 0, //0为用户，1为管理员
          isVip: 0, //是否vip
          isRight: 0, //是否认证
          onlineTime: 68, //上网时长
          onlineTimeUnit: "时",
          status: 0, //是否未注销
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          destoryTime: "",
        },
        {
          userId: "2",
          userCount: "雾里看花",
          userName: "李花",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 0,
          userPhone: 15154269856,
          role: 0, //0为用户，1为管理员
          isVip: 0, //是否vip
          isRight: 1, //是否认证
          onlineTime: 152, //上网时长
          onlineTimeUnit: "时",
          status: 1, //是否未注销
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          destoryTime: "",
        },
        {
          userId: "22",
          userCount: "春招",
          userName: "张三",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 0,
          userPhone: 15712568410,
          role: 0, //0为用户，1为管理员
          isVip: 0, //是否vip
          isRight: 1, //是否认证
          onlineTime: 62, //上网时长
          onlineTimeUnit: "时",
          status: 0, //是否未注销
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          destoryTime: "",
        },
        {
          userId: "42",
          userCount: "攦",
          userName: "李四",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 0,
          userPhone: 15720362395,
          role: 1, //0为用户，1为管理员
          isVip: 1, //是否vip
          isRight: 0, //是否认证
          onlineTime: 12, //上网时长
          onlineTimeUnit: "时",
          status: 0, //是否未注销
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          destoryTime: "",
        },
      ],
    };
  },
  methods: {
    addUserData(){    
      let data = this.$refs.userBase.returnData()
      console.log(data);
      if(data !== false){
        nProgress.start()
        this.$message.success('添加成功')
        this.handleClose()
        nProgress.done()
      }         
      
    },

    // 刷新页面数据
    refreshPage(){
      location.reload()
    },

    //关闭窗口
    handleClose() {
      this.dialogVisible = false
      this.dialogVisible2 = false
    },

    // 打开详情diaglo
    openDetail(index, row) {  
      this.peronData = row;
      this.dialogVisible = true;
    },
    // 添加单个
    handleAdd(){
      this.dialogVisible2 = true
    },
    // 批量添加
    handleAddMore(scope) {
      this.$message.warning("该功能未完成");
    },

    // 删除一个
    handleDelete(index, row) {
      console.log(index, row);
      this.$message.warning("暂未开放功能");
    },
    // 批量删除
    handleDeleteMore() {
      // 验证是否存在选择
      if(this.multipleSelection.length === 0) return this.$message.warning('您还未选择删除的用户！')
      // 验证是否具有权限
      let admin = this.$store.state.userInfo
      if(admin.role !== 1) return this.$message.warning('您未有该权限！')
      // 批量删除
      console.log(this.multipleSelection);
      this.$message.warning("暂未开放功能");
    },
    
    // 多选
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    // 改变页大小
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    // 改变页码
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
  },
};
</script>

<style lang='less' scoped>
.table-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.right-btns {
  font-size: 18px;
}

.pagination {
  margin-top: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
}
</style>