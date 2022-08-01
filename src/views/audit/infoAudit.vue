<template>
  <div>
    <el-card class="table">
      <!-- 顶部按钮 -->
      <div class="table-header">
        <div class="table-header-left">
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
        <el-table-column type="index" width="50" align="center" fixed>
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
        <el-table-column label="图片秀" align="center" width="400">
          <template slot-scope="scope">
            <el-avatar
              shape="square"
              :size="30"
              :src="item"
              v-for="(item, i) in scope.row.activeImgs"
              :key="i"
              style="margin: 0 5px"
            ></el-avatar>
          </template>
        </el-table-column>
        <el-table-column
          prop="status"
          label="审核状态"
          width="120"
          align="center"
          ><template slot-scope="scope">
            <div>
              <el-tag type="danger" size="mini" v-if="scope.row.status === 0"
                >草稿</el-tag
              >
              <el-tag
                type="warning"
                size="mini"
                v-else-if="scope.row.status === 1"
                >审核中</el-tag
              >
              <el-tag
                type="success"
                size="mini"
                v-else-if="scope.row.status === 2"
                >已通过</el-tag
              >
              <el-tag
                type="danger"
                size="mini"
                v-else-if="scope.row.status === 3"
                >未通过</el-tag
              >
              <el-tag
                type="success"
                size="mini"
                v-else-if="scope.row.status === 4"
                >已发布</el-tag
              >
            </div>
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
        <el-table-column label="操作" width="250" align="center" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="el-icon-view"
              type="success"
              @click="openDetail(scope.$index, scope.row)"
              >详细信息</el-button
            >
            <el-popover placement="top" width="200" trigger="hover">
              <div class="operation-box">
                <el-button
                  type="primary"
                  size="mini"
                  @click="sendAuditResult(true, scope.$index)"
                  >Pass</el-button
                >
                <el-button
                  type="danger"
                  size="mini"
                  @click="sendAuditResult(false, scope.$index)"
                  >Faile</el-button
                >
              </div>
              <el-button
                type="primary"
                size="mini"
                class="el-icon-edit"
                slot="reference"
                v-if="scope.row.status < 2"
                >审核</el-button
              >
            </el-popover>

            <el-popconfirm
              confirm-button-text="确认"
              cancel-button-text="取消"
              icon="el-icon-info"
              icon-color="red"
              title="确认删除该条动态审核？"
              v-if="scope.row.status >= 2"
              @confirm="handelDelete(scope.$index, scope.row)"
            >
              <el-button
                type="danger"
                size="mini"
                class="el-icon-delete"
                slot="reference"
                >删除</el-button
              >
            </el-popconfirm>
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

    <!-- 审核原因 -->
    <el-dialog
      :visible.sync="dialogVisible2"
      width="300px"
      :before-close="handleCloseDialog"
    >
      <el-input
        type="textarea"
        placeholder="填写不予通过原因"
        focus
        v-model="faileResult"
      ></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="sendAuditResultFaile"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import UserDetail from "@/components/user/userDetail.vue";
import nProgress from "nprogress";
export default {
  components: {
    UserDetail,
  },
  data() {
    return {
      // 弹窗显示
      dialogVisible: false, //详细信息
      dialogVisible2: false,
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

          personId: "1",
          personName: "花",
          sex: 1,
          age: 26,
          phone: "18412385469",
          imagePath: require("@/assets/images/user.jpeg"),
          disableNumber: "G500226201599999999",
          workAddr: "理工大学",
          householdAddr: "重庆南岸",
          maritalStatus: "未婚",
          height: 178,
          weight: 120,
          degree: "中专",
          income: 20000,
          occupation: "无业",
          housingStatus: "两套别墅",
          carStatus: "无",
          expectedMarryTime: "半年内",
          personIntro: "我是一个开朗的人",
          personSign: "在天愿作比翼鸟，在地愿为连理枝",
          longitude: 42.5,
          latitude: 85.93,
          wechat: "951616262",
          wechatCodeImagesPath: require("@/assets/images/user.jpeg"),
          qq: "1255115515",
          email: "dsadahfdsn@qq.com",
        },
        detailData: {},
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
          activeImgs: [
            require("@/assets/images/user.jpeg"),
            require("@/assets/images/user.jpeg"),
            require("@/assets/images/user.jpeg"),
          ],
          status: 0, //是否未注销
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          destoryTime: "",
        },
      ],
    };
  },
  methods: {
    addUserData() {
      let data = this.$refs.userBase.returnData();
      console.log(data);
      if (data !== false) {
        nProgress.start();
        this.$message.success("添加成功");
        this.handleClose();
        nProgress.done();
      }
    },

    // 发送审核结果
    sendAuditResult(judge, index) {
      this.index = index;
      if (judge) {
        this.tableData[index].status = 2;
        this.$message.success("审核通过！");
      } else {
        this.dialogVisible2 = true;
      }
    },

    sendAuditResultFaile() {
      if (this.faileResult) {
        this.dialogVisible2 = false;
        this.tableData[this.index].status = 3;
        this.$message.error("审核未通过！原因：" + this.faileResult);
      } else {
        this.$message.error("请填写原因");
      }
    },

    // 刷新页面数据
    refreshPage() {
      location.reload();
    },

    //关闭窗口
    handleClose() {
      this.dialogVisible = false;
      this.dialogVisible2 = false;
    },

    // 打开详情diaglo
    openDetail(index, row) {
      this.peronData = row;
      this.dialogVisible = true;
    },

    // 删除一个
    handleDelete(index, row) {
      console.log(index, row);
      this.$message.warning("暂未开放功能");
    },
    // 批量删除
    handleDeleteMore() {
      // 验证是否存在选择
      if (this.multipleSelection.length === 0)
        return this.$message.warning("您还未选择删除的用户！");
      // 验证是否具有权限
      let admin = this.$store.state.userInfo;
      if (admin.role !== 1) return this.$message.warning("您未有该权限！");
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
.el-button {
  margin: 0 5px;
}
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