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
          <el-select v-model="page.type" placeholder="请选择动态类型">
            <el-option
              :label="item.label"
              :value="item.value"
              v-for="(item, i) in options.typeOptions"
              :key="i"
            ></el-option>
          </el-select>
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
        <el-col :span="10">
          <el-button type="primary" @click="searchActive">搜索</el-button>
          <el-button type="danger" @click="handleDeleteMore">删除</el-button>
        </el-col>
      </el-row>
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
        <el-table-column type="index" width="55" align="center" fixed>
        </el-table-column>

        <el-table-column width="150" align="center" label="ID" prop="id">
        </el-table-column>
        <el-table-column
          width="150"
          align="center"
          label="审核类型"
          prop="type"
        >
          <template slot-scope="scope">
            <div>
              <el-tag type="primary" size="mini" v-if="scope.row.type === 0"
                >动态审核</el-tag
              >
              <el-tag
                type="primary"
                size="mini"
                v-else-if="scope.row.type === 1"
                >图片审核</el-tag
              >
              <el-tag
                type="primary"
                size="mini"
                v-else-if="scope.row.type === 2"
                >视频审核</el-tag
              >
              <el-tag
                type="primary"
                size="mini"
                v-else-if="scope.row.type === 3"
                >举报审核</el-tag
              >

            </div>
          </template>
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
          label="受理时间"
          prop="updateTime"
        >
        </el-table-column>
        <el-table-column
          width="150"
          align="center"
          label="审核员"
          prop="admin"
        >
        </el-table-column>
        <el-table-column
          width="150"
          align="center"
          label="审核员ID"
          prop="adminId"
        >
        </el-table-column>

        <el-table-column width="200" align="center" label="操作" fixed="right">
          <template slot-scope="scope">
            <div class="operation">

              <el-popconfirm
                confirm-button-text="确认"
                cancel-button-text="取消"
                icon="el-icon-info"
                icon-color="red"
                title="确认删除该条记录？"
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
        { label: "草稿", value: 0, type: 'info' },
        { label: "审核中", value: 1, type: 'warning' },
        { label: "审核通过", value: 2, type: 'success' },
        { label: "审核拒绝", value: 3, type: 'danger' },
        { label: "已发布", value: 4, type: 'success' },
      ],
      typeOptions: [
        { label: "动态审核", value: 0, type: 'primary' },
        { label: "图片审核", value: 1, type: 'primary' },
        { label: "视屏审核", value: 2, type: 'primary' },
        { label: "举报审核", value: 3, type: 'primary' },
      ]
    };
    return {
      options,
      isPass: false, //是否通过
      drawerVisible: false,
      dialogVisible: false,
      faileResult: "", //不通过原因
      multipleSelection: [],
      tableData: [
        {
          id: "S74856125151",
          status: 1,
          type: 0,
          admin: '下雪',
          adminId: 'S61662',
          createTime: "2022/7/28 21:46",
          updateTime: "2022/7/28 21:46",
          
        },
        {
          id: "S74856125151",
          status: 2,
          type: 1,
          admin: '下雪',
          adminId: 'S61662',
          createTime: "2022/7/28 21:46",
          updateTime: "2022/7/28 21:46",
          
        },
        {
          id: "S74856125151",
          status: 4,
          type: 3,
          admin: '下雪',
          adminId: 'S61662',
          createTime: "2022/7/28 21:46",
          updateTime: "2022/7/28 21:46",
          
        },
        {
          id: "S74856125151",
          status: 3,
          type: 0,
          admin: '下雪',
          adminId: 'S61662',
          createTime: "2022/7/28 21:46",
          updateTime: "2022/7/28 21:46",
          
        },
      ],
      page: {
        pageSize: 10,
        pageNum: 1,
        total: 20,
        keyword: "",
        status: '',
        type: ''
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

    // 删除该动态
    handelDelete(index, row) {
      if(this.isRight() == 1) {
        this.tableData.splice(index, 1);
      this.$message.success("删除该动态成功！");
      }
   
    },

    // 批量删除
    handleDeleteMore() {
      // 验证是否存在选择
      if (this.multipleSelection.length === 0)
        return this.$message.warning("您还未选择删除的用户！");
      // 验证是否具有权限
      this.isRight()
      // 批量删除
      console.log(this.multipleSelection);
      this.$message.warning("暂未开放功能");
    },
    // 验证权限
    isRight(){
      let admin = this.$store.state.userInfo;
      if (admin.role !== 1) 
        this.$message.warning("您的权限不足！");
      return admin.role
    },
    // 多选
    handleSelectionChange(val) {
      this.multipleSelection = val;
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