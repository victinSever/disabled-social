<template>
  <div class="loginLog">

    <el-card
      v-if="tableData.length !== 0"
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
            <el-table-column prop="id" label="ID"></el-table-column>
            <el-table-column prop="nickName" label="登陆用户"></el-table-column>
            <el-table-column prop="userName" label="权限">
              <template slot-scope="scope">
                <el-tag type="success" v-if="scope.row.role === 1">管理员</el-tag>
                <el-tag type="warning" v-else-if="scope.row.role === 2">审核员</el-tag>
              </template>
            </el-table-column>
            <el-table-column
              prop="lastTime"
              label="最近登陆时间"
            ></el-table-column>
          </el-table>

          <!--分页-->
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="page.pageNum"
            :page-sizes="[10, 15, 20, 50]"
            :page-size="page.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="page.total"
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
import { getLoginLogs } from "@/api/manage";
export default {
  name: "LoginLog",
  data() {
    return {
      total: 0,
      tableData: [],
      page: {
        pageSize: 10,
        pageNum: 1,
        total: 20,
      },
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    async getData() {
      const { data: res } = await getLoginLogs({
        size: this.page.pageSize,
        page: this.page.pageNum,
      });
      this.tableData = res.logs;
      this.page.total = res.total;
    },

    handleSizeChange(newSize) {
      this.page.pageSize = newSize;
      this.getData();
    },
    handleCurrentChange(newPage) {
      this.page.pageNum = newPage;
      this.getData();
    },
  },
};
</script>

<style scoped>
</style>