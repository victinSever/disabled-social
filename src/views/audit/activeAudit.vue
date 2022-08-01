<template>
  <div>
    <el-card class="table">
      <el-row class="top">
        <el-col :span="4">
          <el-input
            v-model="page.keyword"
            placeholder="请输入动态关键词"
            prefix-icon="el-icon-search"
          ></el-input>
        </el-col>
        <el-col :span="4">
          <el-select v-model="page.status" placeholder="请选择动态状态">
            <el-option
              :label="item.label"
              :value="item.value"
              v-for="(item, i) in options.statusOptions"
              :key="i"
            ></el-option>
          </el-select>
        </el-col>
        <el-col :span="1">
          <el-button type="danger" @click="searchActive">搜索</el-button>
        </el-col>
      </el-row>
      <!-- 表格 -->
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        border
      >
        <el-table-column type="index" width="55" align="center" fixed>
        </el-table-column>

        <el-table-column width="150" align="center" label="ID" prop="id">
        </el-table-column>
        <el-table-column
          width="500"
          align="center"
          label="动态部分文本内容"
          prop="text"
        >
        </el-table-column>
        <el-table-column
          width="150"
          align="center"
          label="审核状态"
          prop="status"
        >
          <template slot-scope="scope">
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
          width="200"
          align="center"
          label="发布时间"
          prop="createTime"
        >
        </el-table-column>
        <el-table-column
          width="200"
          align="center"
          label="更新时间"
          prop="updateTime"
        >
        </el-table-column>

        <el-table-column width="200" align="center" label="操作" fixed="right">
          <template slot-scope="scope">
            <div class="operation">
              <el-button
                type="success"
                size="mini"
                class="el-icon-search"
                @click="openDrawer(scope.$index, scope.row)"
                >查看</el-button
              >
              <el-popover placement="bottom" width="200" trigger="hover">
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
            </div>
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

      <el-drawer
        title="动态详情"
        :visible.sync="drawerVisible"
        direction="rtl"
        :before-close="handleClose"
        size="90%"
      >
        <activeDetail :itemData="itemData" @sendAuditResult="sendAuditResult" />
      </el-drawer>

      <!-- 审核原因 -->
      <el-dialog
        :visible.sync="dialogVisible"
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
    </el-card>
  </div>
</template>

<script>
import activeDetail from "@/components/audit/activeDetail.vue";
export default {
  components: { activeDetail },
  data() {
    const options = {
      statusOptions: [
        { label: "草稿", value: 0 },
        { label: "审核中", value: 1 },
        { label: "审核通过", value: 2 },
        { label: "审核拒绝", value: 3 },
        { label: "已发布", value: 4 },
      ],
    };
    return {
      options,
      isPass: false, //是否通过
      drawerVisible: false,
      dialogVisible: false,
      faileResult: "", //不通过原因
      tableData: [
        {
          id: "S74856125151",
          text: "懒懒散散",
          status: 1,
          createTime: "2022/7/28 21:46",
          updateTime: "2022/7/28 21:46",
          activeData: {
            userInfo: {
              imgPath: require("@/assets/images/user.jpeg"),
              userName: "小懒猫",
            },
            activeInfo: {
              publishTime: "2022/7/28 19:35",
              text: "迷迷糊糊的活着",
              imgArray: [
                require("@/assets/images/role-bg.png"),
                require("@/assets/images/space2.jpg"),
              ],
            },
          },
        },
        {
          id: "S744241",
          text: "只愿得一人心",
          status: 3,
          createTime: "2022/7/28 21:46",
          updateTime: "2022/7/28 21:46",
          activeData: {
            userInfo: {
              imgPath: require("@/assets/images/user.jpeg"),
              userName: "主",
            },
            activeInfo: {
              publishTime: "2022/7/28 19:35",
              text: "只愿得一人心，白首不分离",
              imgArray: [require("@/assets/images/role-bg.png")],
            },
          },
        },
      ],
      //单个动态数据
      itemData: {},
      index: 0,
      page: {
        pageSize: 10,
        pageNum: 1,
        total: 20,
        keyword: "",
        status: '',
      },
    };
  },

  methods: {
    // 查看详情抽屉
    openDrawer(index, row) {
      this.itemData = row;
      this.drawerVisible = true;
    },

    // 关闭详情抽屉
    handleClose() {
      this.drawerVisible = false;
    },

    handleCloseDialog() {
      this.dialogVisible = false;
    },

    // 按条件搜索动态
    searchActive() {},

    // 发送审核结果
    sendAuditResult(judge, index) {
      this.index = index;
      if (judge) {
        this.tableData[index].status = 2;
        this.$message.success("审核通过！");
      } else {
        this.dialogVisible = true;
      }
    },

    sendAuditResultFaile() {
      this.dialogVisible = false;
      this.tableData[this.index].status = 3;
      this.$message.error("审核未通过！原因：" + this.faileResult);
    },

    // 删除该动态
    handelDelete(index, row) {
      this.tableData.splice(index, 1);
      this.$message.success("删除该动态成功！");
    },

    // 改变页大小
    handleSizeChange(val) {
      this.page.pageSize = val;
    },
    // 改变页码
    handleCurrentChange(val) {
      this.page.pageNum = val;
    },
  },
};
</script>

<style scoped lang='less'>
.top {
  margin-bottom: 20px;
}

.el-col {
  margin-right: 20px;
}

.pagination {
  margin-top: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
}

.operation {
  .el-button {
    margin-right: 10px;
  }
}
</style>