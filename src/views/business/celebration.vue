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
          prop="festival"
          label="节日名称"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="title"
          label="主题名称"
          width="150"
          align="center"
        >
        </el-table-column>
        <el-table-column
          label="主题色"
          width="150"
          align="center"
        >
        <template slot-scope="scope">
            <el-tag v-if="scope.row.theme === 0" size="mini" type="info"
              >浪漫粉</el-tag
            >
            <el-tag
              v-else-if="scope.row.theme === 1"
              size="mini"
              type="success"
              >炫酷黑</el-tag
            >
            <el-tag
              v-else-if="scope.row.theme === 2"
              size="mini"
              type="warning"
              >优雅紫</el-tag
            >
            <el-tag
              v-else-if="scope.row.theme === 3"
              size="mini"
              type="primary"
              >高贵蓝</el-tag
            >
            <el-tag
              v-else-if="scope.row.theme === 4"
              size="mini"
              type="danger"
              >飘雪白</el-tag
            >
            <el-tag
              v-else-if="scope.row.theme === 5"
              size="mini"
              type="danger"
              >玛瑙红</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column label="主题状态" width="150" align="center">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status === 0" size="mini" type="info"
              >未开始</el-tag
            >
            <el-tag
              v-else-if="scope.row.status === 1"
              size="mini"
              type="success"
              >展示中</el-tag
            >
            <el-tag
              v-else-if="scope.row.status === 2"
              size="mini"
              type="danger"
              >已结束</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column
          prop="isAnimation"
          label="动画"
          width="150"
          align="center"
        >
           <template slot-scope="scope">
            <el-tag v-if="scope.row.isAnimation" size="mini" type="info"
              >无动画播放</el-tag
            >
            <el-tag
              v-else
              size="mini"
              style="success"
              >展示动画</el-tag
            >
          </template>
        </el-table-column>
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
          prop="updateTime"
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
              >更新</el-button
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
      :title="type === 1 ? '更新主题':'添加主题'"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      width="400px"
    >
      <celebration :adverData="adverData" ref="cele" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import celebration from "@/components/celebration/index.vue";
export default {
  components: {
    celebration,
  },
  data() {
    return {
      // 弹窗显示
      dialogVisible: false,
      type: 1, //1表示更新，2表示添加

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
          festival: '七夕节',
          title: '七夕之约',
          theme: 0,//主题
          isAnimation: true,//开启动画
          status: 0,
          startTime: "2022/7/25 14:39",
          endTime: "2022/7/25 14:39",
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
        {
          id: "2",
          festival: '情人节',
          title: '挚爱之约',
          theme: 1,//主题
          isAnimation: false,//开启动画
          status: 1,
          startTime: "2022/7/25 14:39",
          endTime: "2022/7/25 14:39",
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
        {
          id: "1",
          festival: '残疾人节',
          title: '残疾之美',
          theme: 2,//主题
          isAnimation: true,//开启动画
          status: 0,
          startTime: "2022/7/25 14:39",
          endTime: "2022/7/25 14:39",
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
      ],
    };
  },
  mounted(){
    this.getData()
  },
  methods: {
    // 查询数据
    async getData(){
      const { data: res} = await this.$http.get('/business/getFestivalList?size=4&page=1')
      console.log(res);
    },
    // 修改单个数据请求
    saveData() {
      let data = this.$refs.cele.returnData();
      console.log(data);
      if(this.type === 1){
        this.$message.success("修改成功！");
      }else{
        this.$message.success("添加成功！");
      }
      
      this.handleClose(); //关闭窗口
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
      this.type = 1;
      this.adverData = {};
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

.demo-drawer-footer {
  margin: 30px 0;
}
</style>