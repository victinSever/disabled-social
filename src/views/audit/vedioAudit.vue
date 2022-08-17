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

        <el-table-column width="200" align="center" label="动态ID" prop="diary.diaryId">
        </el-table-column>
        <el-table-column
          width="500"
          align="center"
          label="动态部分文本内容"
          prop="diary.diaryContent"
        >
        </el-table-column>
        <el-table-column
          width="120"
          align="center"
          label="审核状态"
        >
          <template slot-scope="scope">
            <div>
              <el-tag
                type="warning"
                size="mini"
                v-if="scope.row.diary.diaryStatus === 1"
                >审核中</el-tag
              >
              <el-tag
                type="success"
                size="mini"
                v-else-if="scope.row.diary.diaryStatus === 2"
                >已通过</el-tag
              >
              <el-tag
                type="danger"
                size="mini"
                v-else-if="scope.row.diary.diaryStatus === 3"
                >未通过</el-tag
              >
            </div>
          </template>
        </el-table-column>
        <el-table-column
          width="220"
          align="center"
          label="发布时间"
          prop="diary.createTime"
        >
        </el-table-column>
        <el-table-column
          width="220"
          align="center"
          label="更新时间"
          prop="diary.updateTime"
        >
        </el-table-column>

        <el-table-column width="300" align="center" label="操作" fixed="right">
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
                  v-if="scope.row.diary.diaryStatus < 2"
                  >审核</el-button
                >
              </el-popover>

              <el-popconfirm
                confirm-button-text="确认"
                cancel-button-text="取消"
                icon="el-icon-info"
                icon-color="red"
                title="确认删除该条动态审核？"
                @confirm="openDialogVisible2(scope.$index)"
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
          :page-sizes="[5, 10, 15, 20]"
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
        <vedioDetail 
        :itemData="itemData" 
        :index="index" 
        @sendAuditResult="sendAuditResult"
        @changeIndex="changeIndex" />
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

      <!-- 删除原因 -->
      <el-dialog
        :visible.sync="dialogVisible2"
        width="300px"
        :before-close="handleCloseDialog2"
      >
        <el-input
          type="textarea"
          placeholder="填写删除原因"
          focus
          v-model="deleteMap.ps"
        ></el-input>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="handelDelete"
            >确 定</el-button
          >
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
import vedioDetail from "@/components/audit/vedioDetail.vue";
import { getVedioList, deleteActive } from '@/api/manage';
export default {
  components: { vedioDetail },
  data() {
    const options = {
      statusOptions: [
        { label: "审核中", value: 1 },
        { label: "审核通过", value: 2 },
        { label: "审核拒绝", value: 3 },
      ],
    };
    return {
      options,
      isPass: false, //是否通过
      drawerVisible: false,
      dialogVisible: false,
      dialogVisible2: false,
      faileResult: "", //不通过原因
      deleteResult: "",//删除原因
      tableData: [],
      //单个动态数据
      itemData: {},
      index: 0,
      page: {
        pageSize: 5,
        pageNum: 1,
        total: 20,
        keyword: "",
        status: '',
      },
      deleteMap: {
        diaryId: 0,
        isDeleted: 1,
        ps: ''
      }
    };
  },
  mounted(){
    this.getData()
  },

  methods: {
    // 关闭删除原因弹框
    handleCloseDialog2(){
      this.drawerVisible2 = false
    },
    // 改变审核序号
    changeIndex(index){
      console.log(index);
      if(index >= this.tableData.length  && index < this.total){
        this.page.pageNum++
        this.getData()
      }
      this.itemData = this.tableData[index]
    },
    //获取动态数据
    async getData() {
      const { data: res } = await getVedioList({
        page: this.page.pageNum,
        size: this.page.pageSize
      });
      console.log(res.data);
      if(res.code === 200){
        this.tableData = res.data
        this.page.total = res.map.total
      }
      
    },
    // 查看详情抽屉
    openDrawer(index, row) {
      this.index = index
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
    searchActive() {
      return this.$message.warning('未开放接口')
    },

    // 发送审核结果
    sendAuditResult(judge, index) {
      return this.$message.warning('未开放接口')
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

    //打开删除原因弹窗
    openDialogVisible2(index){
      this.deleteMap.diaryId = index
      this.dialogVisible2 = true
    },

    // 删除该动态
    async handelDelete() {  
      const { data: res } = await deleteActive(this.deleteMap)
      if(res.code === 200){       
        this.getData()
        this.$message.success("删除该动态成功！");
      }else{
        this.$message.success("删除失败！");
      }
    },

    // 改变页大小
    handleSizeChange(val) {
      this.page.pageSize = val;
      this.getData()
    },
    // 改变页码
    handleCurrentChange(val) {
      this.page.pageNum = val;
      this.getData()
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