<template>
  <div>

    <el-card class="table">
      <el-row class="top">
        <el-col :span="4">
        <el-input v-model="page.keyword" placeholder="请输入动态关键词" prefix-icon="el-icon-search"></el-input>
      </el-col>
      <el-col :span="4">
        <el-select v-model="page.status" placeholder="请选择动态状态">
          <el-option :label="item.label" :value="item.value" v-for="(item, i) in options.statusOptions" :key="i"></el-option>
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
        
        <el-table-column width="150" align="center" label="ID" prop="id">
        </el-table-column>
        <el-table-column width="300" align="center" label="动态部分文本内容" prop="text">
        </el-table-column>
        <el-table-column width="150" align="center" label="审核状态" prop="status">
          <template slot-scope="scope">
            <div>
              <el-tag type="danger" size="mini" v-if="scope.row.status === 0">草稿</el-tag>
              <el-tag type="warning" size="mini" v-else-if="scope.row.status === 1">审核中</el-tag>
              <el-tag type="success" size="mini" v-else-if="scope.row.status === 2">已通过</el-tag>
              <el-tag type="danger" size="mini" v-else-if="scope.row.status === 3">未通过</el-tag>
              <el-tag type="success" size="mini" v-else-if="scope.row.status === 4">已发布</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column width="150" align="center" label="发布时间" prop="createTime">
        </el-table-column>
        <el-table-column width="150" align="center" label="更新时间" prop="updateTime">
        </el-table-column>

        <el-table-column width="200" align="center" label="操作" fixed="right">
          <template slot-scope="scope">
            <div>
              <el-button type="success" size="mini" circle class="el-icon-search" @click="checkDetail(scope.$index, scope.row)"></el-button>
              <el-button type="primary" size="mini" circle class="el-icon-edit" @click="checkDetail(scope.$index, scope.row)"></el-button>
              <el-button type="danger" size="mini" circle class="el-icon-delete" @click="checkDetail(scope.$index, scope.row)"></el-button>
            </div>
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
  data(){
    const options = {
      columns: [
        { prop: 'actions', label: '操作', scope: 'actions'},
        { prop: 'publish', label: '发布/审核', scope: 'publish'},
        { prop: 'id', label: 'ID'},
        { prop: 'status', label: '状态', scope: 'status'},
        { prop: 'createTime', label: '创建时间', formatter: 'time'},
        { prop: 'updateTime', label: '更新时间', formatter: 'time'},
      ],
      statusOptions: [
        { label: '草稿', value: 0 },
        { label: '审核中', value: 1 },
        { label: '审核通过', value: 2 },
        { label: '审核拒绝', value: 3 },
        { label: '已发布', value: 4 },
      ]
    }
    return {
      options,
      isPass: false,//是否通过
      tableData: [{
        id: 'S74856125151',
        text: '懒懒散散',
        status: '',
        createTime: '2022/7/28 21:46',
        updateTime: '2022/7/28 21:46'
      }],
      //单个动态数据
      activeData: [
        {
        userInfo: {
          imgPath: require('@/assets/images/user.jpeg'),
          userName: '小懒猫'
        },
        activeInfo: {
          publishTime: '2022/7/28 19:35',
          text: '迷迷糊糊的活着',
          imgArray: [
            require('@/assets/images/role-bg.png'),
          ]
        }
      }
      ],
      page: {
        pageSize: 10,
        pageNum: 1,
        total: 20,
        keyword: '',
        status: 0
      },
    }
  },
  methods: {
    checkDetail(index, row) {

    },
    searchActive(){

    }
  }
}
</script>

<style scoped lang='less'>
.top{
  margin-bottom: 20px;
}

.el-col{
  margin-right: 20px;
}

.pagination {
  margin-top: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
}

</style>