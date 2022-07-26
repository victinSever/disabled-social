<template>
  <div class="loginLog">

    <el-card
      v-if="tableData.length"
      class="animate__animated animate__bounceInRight"
      style="margin-top: 20px"
    >
      <el-row>
          <h1 style="text-align: center; margin-top: 10px">登录日志</h1>
          <el-table
            :data="tableData"
            style="width: 100%; margin-top: 40px"
            border
          >
            <el-table-column
              label="序号"
              type="index"
              align="center"
              width="80px"
            ></el-table-column>
            <el-table-column prop="userName" label="登陆用户"></el-table-column>
            <el-table-column prop="userName" label="登陆用户">
              <template slot-scope="scope">
                <el-tag type="success" v-if="scope.row.role === 1">管理员</el-tag>
                <el-tag type="warning" v-else-if="scope.row.role === 2">审核员</el-tag>
              </template>
            </el-table-column>
            <el-table-column
              prop="loginTime"
              label="最近登陆时间"
            ></el-table-column>
          </el-table>

          <!--分页-->
          <el-pagination
            v-if="showPagination"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="this.pageNum"
            :page-sizes="[10, 15, 20, 50]"
            :page-size="this.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="this.total"
            style="margin-top: 20px"
          ></el-pagination>
      </el-row>
    </el-card>

    <el-empty
      v-else
      description="暂时没有记录"
      class="animate__animated animate__rollIn"
      :image-size="200"
    ></el-empty>
  </div>
</template>

<script>
export default {
  name: "LoginLog",
  data() {
    return {
      total: 0,
      tableData: [{
          userName: '雾里看花',
          role: 1,
          loginTime: '2022/7/24 23:17:25'
      },{
          userName: '梦',
          role: 1,
          loginTime: '2022/7/24 23:17:25'
      },{
          userName: '潜水员',
          role: 2,
          loginTime: '2022/7/24 23:17:25'
      },{
          userName: '来生',
          role: 2,
          loginTime: '2022/7/24 23:17:25'
      }],
      pageSize: 10,
      pageNum: 1,
      showPagination: true,
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    async getData() {
      // const { data: res } = await this.$http.get(
      //   `/user/searchUserLogVo/${this.pageNum}/${this.pageSize}`
      // );
      // this.tableData = res.data.userLog.records;
      // this.total = res.data.userLog.total;
    },
    handleSizeChange(newSize) {
      this.pageSize = newSize;
      this.getData();
    },
    handleCurrentChange(newPage) {
      this.pageNum = newPage;
      this.getData();
    },
  },
};
</script>

<style scoped>
</style>