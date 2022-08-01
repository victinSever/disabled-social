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
          label="活动名称"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="imgPath"
          label="活动封面"
          width="100"
          align="center"
        >
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
        <el-table-column
          prop="position"
          label="活动投放位置"
          width="150"
          align="center"
        >
        </el-table-column>
        <el-table-column label="活动状态" width="150" align="center">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status === 0" size="mini" type="info"
              >未开始</el-tag
            >
            <el-tag
              v-else-if="scope.row.status === 1"
              size="mini"
              style="primary"
              >报名中</el-tag
            >
            <el-tag
              v-else-if="scope.row.status === 2"
              size="mini"
              style="success"
              >进行中</el-tag
            >
            <el-tag
              v-else-if="scope.row.status === 3"
              size="mini"
              style="danger"
              >已结束</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column label="发布状态" width="150" align="center">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.publishStatus"
              disabled
              active-color="#13ce66"
              inactive-color="#ff4949"
            >
            </el-switch>
          </template>
        </el-table-column>
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
          prop="updateTime"
          label="更新时间"
          width="150"
          align="center"
        ></el-table-column>

        <el-table-column label="操作" width="300" align="center" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="el-icon-view"
              type="success"
              @click="handleDetail(scope.$index, scope.row)"
              >报名情况</el-button
            >

            <el-button
              size="mini"
              class="el-icon-edit"
              type="primary"
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

    <!-- 更新活动-->
    <el-drawer
      :title="type === 1 ? '更新活动' : '新建活动'"
      :visible.sync="dialogVisible"
      direction="rtl"
      :before-close="handleClose"
      size="90%"
    >
      <el-col :span="20" :offset="2">
        <activity :adverData="adverData" ref="activity" />
        <div  class="demo-drawer-footer">
          <el-button @click="handleClose">取 消</el-button>
          <el-button type="primary" @click="saveData(false)">确 定</el-button>
        </div>
      </el-col>
    </el-drawer>

    <el-drawer
      title="报名详情"
      :visible.sync="dialogVisible2"
      direction="rtl"
      :before-close="handleClose"
      size="90%"
    >
        <activityDetail :adverData="adverData"/>
    </el-drawer>
  </div>
</template>

<script>
import activity from "@/components/activity/index.vue";
import activityDetail from "@/components/activity/detail.vue";
import { getActivityList } from '@/api/manage'
export default {
  components: {
    activity,activityDetail
  },
  data() {
    return {
      // 弹窗显示
      dialogVisible: false,
      dialogVisible2: false,
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
          title: "全国聋哑人线上交友会", //活动名称
          imgPath: require("@/assets/images/user.jpeg"),
          position: "约吧", //活动弹出层位置
          order: 1,
          status: 1,//活动状态
          publishStatus: false,//发布状态
          views: 234, //关注度
          signUpNum: 20, //报名人数
          isSignUp: false, //是否可报名
          startTime: "2022/7/25 14:39",
          endTime: "2022/7/25 14:39",
          createTime: "2022/7/25 14:39",
          updateTime: "2022/7/25 14:39",

          // 文章内容
          content: {},
        },
      ],
    };
  },
  mounted(){
    this.getData()
  },
  methods: {
    // 查询数据
    getData(){
      // const { data: res } = getActivityList({
      //   size: 4,
      //   page: 1
      // })
      // console.log(res);
    },
    // 修改单个数据请求
    saveData() {
      let data = this.$refs.activity.returnData();
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

    //报名详情
    handleDetail(index, row) {
      this.dialogVisible2 = true;
    },

    //关闭窗口
    handleClose() {
      this.dialogVisible = false;
      this.dialogVisible2 = false;
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