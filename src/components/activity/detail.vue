<template>
  <div>
    <el-card class="table">
      <!-- 表格 -->
      <el-table
        ref="table"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        border
      >

        <el-table-column type="index" width="55" align="center" fixed>
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
          prop="userCount"
          label="用户名"
          width="150"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="userName" label="姓名" width="80" align="center">
        </el-table-column>
        <el-table-column
          label="性别"
          width="80"
          align="center"
        >
        <template slot-scope="scope">
            <el-tag type="primary" size="mini" v-if="scope.row.userSex == 0">男</el-tag>
            <el-tag type="success" size="mini" v-else-if="scope.row.userSex == 1">女</el-tag>
        </template>
        </el-table-column>
        <el-table-column
          prop="userPhone"
          label="手机号"
          width="150"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="isRight"
          label="是否通过"
          width="150"
          align="center"
        ><template slot-scope="scope">
            <el-tag type="danger" size="mini" v-if="scope.row.isRight == 0">未通过</el-tag>
            <el-tag type="success" size="mini" v-else-if="scope.row.isRight == 1">通过</el-tag>
        </template>
        </el-table-column>



        <el-table-column
          prop="createTime"
          label="报名时间"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column
          prop="updateTime"
          label="更新时间"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column label="操作" width="300" align="center" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="el-icon-edit"
              type="success"
              @click="handlePass(scope.$index, scope.row)"
              >通过</el-button
            >
            <el-button
              size="mini"
              class="el-icon-view"
              type="primary"
              @click="openDetail(scope.$index, scope.row)"
              >详细信息</el-button
            >
            <el-button
              size="mini"
              class="el-icon-delete"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>

  </div>
</template>

<script>
export default {
  data() {
    return {

      // 数据
      tableData: [
        {
          userId: "1",
          userCount: "admin",
          userName: "李华",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 1,
          userPhone: 18140267749,         
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          isRight: 1,//是否同意
        },
        {
          userId: "2",
          userCount: "雾里看花",
          userName: "李花",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 0,
          userPhone: 15154269856,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          isRight: 1,
        },
        {
          userId: "22",
          userCount: "春招",
          userName: "张三",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 0,
          userPhone: 15712568410,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          isRight: 1,
        },
        {
          userId: "42",
          userCount: "攦",
          userName: "李四",
          userImg: require("@/assets/images/user.jpeg"),
          userSex: 0,
          userPhone: 15720362395,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          isRight: 0,
        },
      ],
    };
  },
  methods: {
    handlePass(index, row){      
        location.reload()
        this.$message.success("已通过该报名请求！");
    },
    // 删除一个
    handleDelete(index, row) {
      console.log(index, row);
      this.$message.warning("暂未开放功能");
    },
    // 打开详情diaglo
    openDetail(index, row) {  
      this.peronData = row;
    },
  },
};
</script>

<style lang='less' scoped>


</style>