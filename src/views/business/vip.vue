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

        <el-table-column
          prop="id"
          label="订单ID"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="userId"
          label="用户ID"
          width="150"
          align="center"
        >
        </el-table-column>

        <el-table-column label="订单状态" width="150" align="center">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status === 0" size="mini" type="warning"
              >待支付</el-tag
            >
            <el-tag
              v-else-if="scope.row.status === 1"
              size="mini"
              type="success"
              >支付成功</el-tag
            >
            <el-tag
              v-else-if="scope.row.status === 2"
              size="mini"
              type="danger"
              >支付失败</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column
          prop="type"
          label="VIP类别"
          width="150"
          align="center"
        >
           <template slot-scope="scope">
            <el-tag v-if="scope.row.type == 0" size="mini" type="success"
              >月包</el-tag
            >
            <el-tag
              v-else-if="scope.row.type === 1"
              size="mini"
              type="primary"
              >季包</el-tag
            >
            <el-tag
              v-else-if="scope.row.type === 2"
              size="mini"
              type="danger"
              >年包</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column
          prop="price"
          label="价格"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="startTime"
          label="特权开始时间"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="endTime"
          label="特权结束时间"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="createTime"
          label="订单创建时间"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="updateTime"
          label="订单更新时间"
          width="150"
          align="center"
        ></el-table-column>

        <el-table-column label="操作" width="200" align="center" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="el-icon-view"
              type="success"
              @click="handleEdit(scope.$index, scope.row)"
              >订单详情</el-button
            >

            <el-button
              type="danger"
              size="mini"
              class="el-icon-delete"
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

    <!-- 更新主题-->
    <el-dialog
      title="订单详情"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      width="400px"
    >
      <order :adverData="adverData"/>
    </el-dialog>
  </div>
</template>

<script>
import order from "@/components/order/index.vue";
export default {
  components: {
    order,
  },
  data() {
    return {
      // 弹窗显示
      dialogVisible: false,

      page: {
        pageSize: 10,
        pageNum: 1,
        total: 20,
      },

      // 将要修改的数据
      adverData: {},

      // 被选择
      multipleSelection: [],

      // 数据
      tableData: [
        {
          id: 's956620200',//订单号
          userId: '96626',//用户id
          status: 0,//0待支付，1支付成功
          price: 95,
          type: 0,//vip套餐类别，三种
          startTime: "2022/7/25 14:39",
          endTime: "2022/7/25 14:39",
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
        {
          id: 's95632400',//订单号
          userId: '96626',//用户id
          status: 1,//0待支付，1支付成功
          price: 18,
          type: 1,//vip套餐类别，三种
          startTime: "2022/7/25 14:39",
          endTime: "2022/7/25 14:39",
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
        {
          id: 's956613200',//订单号
          userId: '31231',//用户id
          status: 2,//0待支付，1支付成功
          price: 25,
          type: 2,//vip套餐类别，三种
          startTime: "2022/7/25 14:39",
          endTime: "2022/7/25 14:39",
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
      ],
    };
  },
  methods: {

    // 刷新页面数据
    refreshPage() {
      location.reload();
    },

    //关闭窗口
    handleClose() {
      this.dialogVisible = false;
    },

    // 打开详情dialog
    handleEdit(index, row) {
      this.adverData = row;
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

.demo-drawer-footer {
  margin: 30px 0;
}
</style>