<template>
  <div>
    <el-card class="table">
      <!-- 顶部按钮 -->
      <div class="table-header">
        <div class="table-header-left">
          <el-button
            type="primary"
            class="el-icon-edit"
            size="mini"
            @click="handleAdd()"
            >添加</el-button
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
        <el-table-column prop="id" label="ID" width="50" align="center" fixed>
        </el-table-column>

        <el-table-column
          prop="title"
          label="广告标题"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="position"
          label="所属广告位"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column label="广告类型" width="150" align="center">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.type === 0" size="mini" type="success"
              >图片广告</el-tag
            >
            <el-tag v-else-if="scope.row.type === 1" size="mini" style="primary"
              >链接广告</el-tag
            >
            <el-tag v-else-if="scope.row.type === 2" size="mini" style="warning"
              >图标广告</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column label="广告封面" width="150" align="center">
          <template slot-scope="scope">
            <div class="block">
              <el-avatar
                shape="square"
                :size="30"
                :src="scope.row.imgPath"
              ></el-avatar>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="广告状态" width="150" align="center">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.status"
              active-color="#13ce66"
              inactive-color="#ff4949"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          prop="url"
          label="广告链接"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="size"
          label="广告型号"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="views"
          label="浏览量"
          width="80"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="order"
          label="序号"
          width="80"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="startTime"
          label="开始时间"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="endTime"
          label="结束时间"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="pusblishTime"
          label="更新时间"
          width="150"
          align="center"
        ></el-table-column>

        <el-table-column label="操作" width="200" align="center" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
              >修改</el-button
            >
              <el-button size="mini" class="el-icon-delete" type="danger" @click="handleDelete(scope.$index, scope.row)"
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

    <!-- 修改广告 -->
    <el-dialog
      :title="type === 1 ? '添加广告':'修改广告'"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      width="800px"
    >
      <updateAdver :adverData="adverData" ref="updateAdver" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveData">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import updateAdver from "@/components/adver/updateAdver.vue";
export default {
  components: {
    updateAdver,
  },
  data() {
    return {
      // 弹窗显示
      dialogVisible: false,
      type: 1,

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
          title: "sad",
          id: "1",
          position: "213",
          order: 1,
          type: 0,
          imgPath: require("@/assets/images/user.jpeg"),
          status: true,
          url: "http://localhost:8080",
          size: "50 * 50",
          views: 1000, //浏览量
          startTime: "2022/7/25 14:39", //开始时间
          endTime: "2022/7/25 14:39", //结束时间
          createTime: "2022/7/25 14:39", //创建时间
          pusblishTime: "2022/7/25 14:39", //更新时间
        },
      ],
    };
  },
  methods: {
    // 修改单个数据请求
    saveData() {
      let data = this.$refs.updateAdver.returnData();
      console.log(data);
      this.$message.success("修改成功！");
      this.handleClose(); //关闭窗口
    },

    // 添加数据请求
    addData() {
      let data = this.$refs.addAdver.returnData(); //获取子组件中的数据
      console.log(data);
      this.$message.success("添加成功！");
      this.handleClose();
    },

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
      this.type = 1
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
    // 添加
    handleAdd() {
      this.adverData = {};
      this.type = 2
      this.dialogVisible = true;
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