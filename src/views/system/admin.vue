<template>
  <div>
    <el-card class="table">
      <div class="table-header">
              <div class="table-header-left">
              <el-button type="primary" class="el-icon-edit" size="mini" @click="handleAdd(scope.row)">添加</el-button>
              <el-button type="danger" class="el-icon-delete" size="mini" @click="handleDeleteMore(scope.$index, scope.row)">删除</el-button>
            </div>
            <div class="table-header-right">
              <el-tooltip class="item" effect="dark" content="刷新" placement="top">
                <el-button class="el-icon-refresh right-btns" size="small"></el-button>
              </el-tooltip>
              <el-tooltip class="item" effect="dark" content="密度" placement="top">
                <el-button class="el-icon-s-operation right-btns" size="small"></el-button>
              </el-tooltip>           
              <el-tooltip class="item" effect="dark" content="设置" placement="top">
                <el-button class="el-icon-setting right-btns" size="small"></el-button>
              </el-tooltip>
              <el-tooltip class="item" effect="dark" content="全屏" placement="top">
                <el-button class="el-icon-rank right-btns" size="small"></el-button>
              </el-tooltip>
            </div> </div>  
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        
          <el-table-column type="selection" width="55" align="center">
          </el-table-column>
          <el-table-column prop="userId" label="ID" width="50" align="center">
          </el-table-column>
          <el-table-column
            prop="userName"
            label="姓名"
            width="80"
            align="center"
          >
          </el-table-column>
          <el-table-column label="头像" align="center" width="80">
            <template slot-scope="scope">
              <img
                :src="scope.row.userImg"
                alt="未上传头像"
                style="width: 30px; height: 30px; border-radius: 50%"
              />
            </template>
          </el-table-column>
          <el-table-column
            prop="userPhone"
            label="手机号"
            width="120"
            align="center"
          >
          </el-table-column>
          <el-table-column label="角色" align="center" width="100">
            <template slot-scope="scope">
              <el-tag size="mini" type="success" v-if="scope.row.role === 0"
                >管理员</el-tag
              >
              <el-tag
                size="mini"
                type="warning"
                v-else-if="scope.row.role === 1"
                >审核员</el-tag
              >
              <el-tag size="mini" type="danger" v-else-if="scope.row.role === 2"
                >超级管理员</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column label="部门" align="center" width="150">
            <template slot-scope="scope">
              <el-tag size="mini" type="primary">{{
                scope.row.department === 0 ? "运营部门" : "审核部门"
              }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="status"
            label="职级状态"
            align="center"
            width="100"
          >
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.status"
                active-color="#13ce66"
                inactive-color="#ff4949"
              >
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" align="center" width="150">
          </el-table-column>
          <el-table-column prop="updateTime" label="更新时间" align="center" width="150">
          </el-table-column>
          <el-table-column label="操作" width="300" align="center" fixed="right">
            <template slot-scope="scope">
              <el-button
                size="mini"
                class="el-icon-edit"
                @click="handleEdit(scope.$index, scope.row)"
                >编辑</el-button
              >
              <el-button
                size="mini"
                class="el-icon-delete"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
                >删除</el-button
              >
              <el-button
                size="mini"
                class="el-icon-setting"
                type="success"
                @click="handleUpDate(scope.$index, scope.row)"
                >重置密码</el-button
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      page: {
        pageSize: 10,
        pageNum: 1,
        total: 400,
      },

      // 被选择
      multipleSelection: [],

      // 数据
      tableData: [
        {
          userId: "1",
          userCount: "admin",
          userName: "李华",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 1,
          userPhone: 18140267749,
          role: 0,
          department: 0,
          status: true,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
        },
        {
          userId: "2",
          userCount: "雾里看花",
          userName: "李花",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 0,
          userPhone: 15154269856,
          role: 1,
          department: 1,
          status: false,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
        },
        {
          userId: "22",
          userCount: "春招",
          userName: "张三",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 0,
          userPhone: 15712568410,
          role: 2,
          department: 2,
          status: false,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
        },
        {
          userId: "42",
          userCount: "攦",
          userName: "李四",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 0,
          userPhone: 15720362395,
          role: 1,
          department: 1,
          status: true,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
        },
      ],
    };
  },
  methods: {
    // 编辑
    handleEdit(index, row) {
      console.log(index, row);
      this.$message.warning('暂未开放功能')
    },
    // 重置密码
    handleUpDate(index, row) {
      console.log(index, row);
      this.$message.warning('暂未开放功能')
    },
    // 删除一个
    handleDelete(index, row) {
      console.log(index, row);
      this.$message.warning('暂未开放功能')
    },
    // 删除多个
    handleDeleteMore(index, row) {
      console.log(index, row);
      this.$message.warning('暂未开放功能')
    },
    // 添加
    handleAdd(scope){
      this.$message.warning('暂未开放功能')
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
.table {
}

.table-header{
  display: flex;
  justify-content: space-between;
  align-items: center;


}


.right-btns{
  font-size: 18px;
}

.pagination {
  margin-top: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
}
</style>