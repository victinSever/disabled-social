<template>
  <div>
    <el-card class="table">
      <!-- 顶部按钮 -->
      <div class="table-header">
        <div class="table-header-left">
          <el-button type="primary" class="el-icon-delete" size="mini" @click="handleAdd"
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
          prop="name"
          label="套餐名"
          width="150"
          align="center"
        ><template slot-scope="scope">
            <span style="color: blue; font-weight: bold">{{scope.row.name}}</span>
          </template>
        </el-table-column>


        <el-table-column
          label="时长"
          width="150"
          align="center"
        >
        <template slot-scope="scope">
          <span>{{scope.row.timeNum}}个月</span>
        </template>
        </el-table-column>

        <el-table-column
          label="单月价格"
          width="150"
          align="center"
        >
        <template slot-scope="scope">
          <span>{{scope.row.priceNum}}￥</span>
        </template>
        </el-table-column>

        <el-table-column
          label="价格"
          width="150"
          align="center"
        >
        <template slot-scope="scope">
          <span>{{scope.row.totalPrice}}￥</span>
        </template>
        </el-table-column>

        <el-table-column label="套餐状态" width="150" align="center">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.status"
              disabled
              active-color="#13ce66"
              inactive-color="#ff4949"
            >
            </el-switch>
          </template>
        </el-table-column>

        <el-table-column
          prop="isMain"
          label="是否首推"
          width="150"
          align="center"
          ><template slot-scope="scope">
            <el-button v-if="scope.row.isMain" circle size="mini" type="success" class="el-icon-check" style="font-size: 20px;"
              ></el-button
            >
          </template></el-table-column
        >
        <el-table-column
          prop="createTime"
          label="套餐创建时间"
          width="150"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="updateTime"
          label="套餐更新时间"
          width="150"
          align="center"
        ></el-table-column>

        <el-table-column label="操作" width="200" align="center" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="el-icon-edit"
              type="success"
              @click="handleEdit(scope.$index, scope.row)"
              >更改</el-button
            >

            
            <el-popconfirm
                confirm-button-text="确认"
                cancel-button-text="取消"
                icon="el-icon-info"
                icon-color="red"
                title="确认删除该套餐？"
                style="margin-left: 10px;"
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

    <!-- 更新主题-->
    <el-dialog
      :title="isAdd ? '添加套餐' : '更改套餐'"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      width="400px"
    >
      <packageItem :itemData="itemData" ref="packageItem"/>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveData">保 存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import packageItem from "@/components/package/index.vue";
export default {
  components: {
    packageItem,
  },
  data() {
    return {
      // 弹窗显示
      dialogVisible: false,
      isAdd: false,

      page: {
        pageSize: 10,
        pageNum: 1,
        total: 20,
      },

      // 将要修改的数据
      itemData: {},

      // 被选择
      multipleSelection: [],

      // 数据
      tableData: [
        {
          id: "s956620200", //套餐号
          name: "连续包年",
          timeNum: 12,
          priceNum: 7.92,
          totalPrice: 95,
          isMain: true, //是否首要推荐
          status: true,
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
        {
          id: "s956632432", //套餐号
          name: "连续包季",
          timeNum: 3,
          priceNum: 10,
          totalPrice: 30,
          isMain: false, 
          status: true,
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
        {
          id: "s956632432", //套餐号
          name: "连续包月",
          timeNum: 1,
          priceNum: 12,
          totalPrice: 12,
          isMain: false, 
          status: true,
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",
        },
      ],
    };
  },
  methods: {
    // 保存或者添加数据
    saveData(){
      let data = this.$refs.packageItem.returnData()
      if(data !== false){
        console.log(data);
        this.$message.success('修改套餐成功！')
        this.dialogVisible = false
      }
    },

    //关闭窗口
    handleClose() {
      this.dialogVisible = false;
    },

    // 打开详情dialog
    handleEdit(index, row) {
      this.itemData = row;
      this.isAdd = false
      this.dialogVisible = true;
    },

    handleAdd(){
      this.itemData = {
          id: "", 
          name: "",
          timeNum: 0,
          priceNum: 0,
          totalPrice: 0,
          isMain: false, 
          status: false,
      }
      this.isAdd = true
      this.dialogVisible = true;
    },

    // 删除一个
    handleDelete(index, row) {
      this.tableData.splice(index, 1)
      this.$message.success("删除成功！");
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
      this.page.pageSize = val
    },
    // 改变页码
    handleCurrentChange(val) {
      this.page.pageNum = val
    },
    // 刷新页面数据
    refreshPage() {
      location.reload();
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