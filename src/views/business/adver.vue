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
        <el-table-column label="广告封面" width="150" align="center">
          <template slot-scope="scope">
            <div class="block">
              <el-avatar
                shape="square"
                :size="30"
                :src="scope.row.appearencePath"
              ></el-avatar>
            </div>
          </template>
        </el-table-column>
        
        <el-table-column prop="description" label="广告描述" width="400" align="center">
        </el-table-column>
        <el-table-column
          prop="unit"
          label="所属广告位"
          width="150"
          align="center"
        ></el-table-column>
        
        <el-table-column label="广告状态" width="150" align="center">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status === 0" size="mini" type="success"
              >开放</el-tag
            >
            <el-tag v-else-if="scope.row.status === 1" size="mini" style="primary"
              >未开放</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column
          prop="advertisingLinks"
          label="广告链接"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="advertisedModel"
          label="广告型号"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="viewAmount"
          label="浏览量"
          width="80"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="ordinal"
          label="序号"
          width="80"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="startTime"
          label="开始时间"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="endTime"
          label="结束时间"
          width="200"
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
              <el-popconfirm
                confirm-button-text="确认"
                cancel-button-text="取消"
                icon="el-icon-info"
                icon-color="red"
                title="确认删除该条动态？"
                @confirm="handleDelete(scope.$index, scope.row)"
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

    <!-- 修改广告 -->
    <el-dialog
      :title="type === 1 ? '修改广告':'添加广告'"
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
import updateAdver from "@/components/adver/index.vue";
import { getAdvertisingList, addAdvertising, deleteAdvertsing } from '@/api/manage'
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
      tableData: [],
    };
  },
    mounted(){
    this.getData()
  },
  methods: {
    // 查询数据
    async getData(){
      const {data:res} = await getAdvertisingList({
        size: this.page.pageSize,
        page: this.page.pageNum
      })
      this.tableData = res.list
      this.page.total = res.total
    },

    // 修改单个数据请求
    async saveData() {
      let data = this.$refs.updateAdver.returnData();
      console.log(data);
      const res = await addAdvertising(data)
      console.log(res);
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
    async handleDelete(index, row) {
      const res = await deleteAdvertsing({
        id: row.id
      })
      this.getData()
      this.$message.warning("删除成功！");
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
      this.page.pageSize = val
      this.getData()
    },
    // 改变页码
    handleCurrentChange(val) {
      this.page.pageNum = val
      this.getData()
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

.el-button{
  margin-right: 10px;
}
</style>