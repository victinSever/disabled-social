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
        <el-table-column type="index" width="50" align="center" fixed>
        </el-table-column>
        <el-table-column
          prop="title"
          label="广告位名称"
          width="150"
          align="center"
          ><template slot-scope="scope">
            <span style="color: blue; font-weight: bold">{{
              scope.row.title
            }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="description"
          label="广告位描述"
          width="500"
          align="center"
        ></el-table-column>

        <el-table-column label="广告位使用状态" width="150" align="center">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.status"
              active-color="#13ce66"
              inactive-color="#ff4949"
              disabled
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          prop="adverName"
          label="已插入广告"
          width="150"
          align="center"
          ><template slot-scope="scope">
            <span v-if="scope.row.status">{{ scope.row.adverName }}</span>
          </template></el-table-column
        >
        <el-table-column
          prop="adverId"
          label="已插入广告ID"
          width="150"
          align="center"
          ><template slot-scope="scope">
            <span v-if="scope.row.status">{{ scope.row.adverId }}</span>
          </template></el-table-column
        >

        <el-table-column
          prop="order"
          label="序号"
          width="80"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="updateTime"
          label="更新时间"
          width="200"
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

    <!-- 修改广告 -->
    <el-dialog
      :title="type === 1 ? '修改广告位' : '添加广告位'"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      width="400px"
    >
      <adSort :adverData="adverData" ref="updateAdver" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import adSort from "@/components/adverSort/index.vue";
import { getAdvertiseplaceList } from "@/api/manage";
export default {
  components: {
    adSort,
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
          id: "1",
          title: "首页全屏弹框",
          description: "首页全屏弹框，用户第一时间可以看到的最优质广告位",
          order: 1,
          status: false,
          adverName: "七夕送暖",
          adverId: "S1226",
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
        {
          id: "2",
          title: "广场精彩多多",
          description: "小按钮广告，通过广场模块的悬浮按钮进入，该广告最持久",
          order: 1,
          status: true,
          adverName: "福利大放送",
          adverId: "S1236",
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
        {
          id: "3",
          title: "动态插入广告",
          description: "在动态列表中插入广告用户，可以出现的频率最多",
          order: 1,
          status: true,
          adverName: "红包节",
          adverId: "S1266",
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
      ],
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    // 查询数据
    async getData() {
      const { data: res } = await getAdvertiseplaceList({
        size: this.page.pageSize,
        page: this.page.pageNum,
      });
      console.log(res);
      this.tableData = res.list;
      this.page.total = res.total;
    },
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
      this.type = 1;
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
      this.type = 2;
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