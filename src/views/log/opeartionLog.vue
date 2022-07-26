<template>
  <div class="logs">

    <el-card
      class="animate__animated animate__bounceInLeft"
      style="margin-top: 20px"
      v-if="tableData.length !== 0"
    >
      <h1 style="text-align: center; margin-top: 40px">操作日志</h1>
      <el-table :data="tableData" :fit="true" :stripe="true" border>
        <el-table-column
          label="#"
          type="index"
          width="100"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="user_name"
          label="操作员"
          width="180"
        ></el-table-column>

        <el-table-column label="操作名称">
          <template v-slot:default="scope">
            <span>{{
              scope.row.isPut === 1
                ? "入库操作"
                : scope.row.isPut === 2
                ? "出库操作"
                : "修改"
            }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="id" label="操作单号"></el-table-column>
        <el-table-column prop="content" label="操作原因"></el-table-column>
        <el-table-column prop="update_time" label="操作时间"></el-table-column>
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
    </el-card>

    <el-empty
      v-else
      description="暂时没有记录"
      class="animate__animated animate__zoomInDown"
      :image-size="200"
    ></el-empty>
  </div>
</template>

<script>
export default {
  name: "logs",
  data() {
    return {
      total: 0,
      tableData: [],
      pageSize: 10,
      pageNum: 1,
      showPagination: true,
      formLabelWidth: "120px",
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    async getData() {
      const {data: res} = await this.$http.get(`/user/searchUserOperVo/${this.pageNum}/${this.pageSize}`);
      this.tableData = res.data.userOper.records
      this.total = res.data.userOper.total
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