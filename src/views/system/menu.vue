<template>
  <div>
    <el-card class="table">
      <div class="table-header">
              <div class="table-header-left">
                <el-button
                  type="primary"
                  class="el-icon-add"
                  size="mini"
                  @click="handleAdd(scope.row)"
                  >添加</el-button
                >
                <el-button
                  size="mini"
                  @click="handleDeleteMore(scope.$index, scope.row)"
                  >展开全部</el-button
                >
                <el-button
                  size="mini"
                  @click="handleDeleteMore(scope.$index, scope.row)"
                  >折叠全部</el-button
                >
              </div>
              <div class="table-header-right">
                <el-tooltip
                  class="item"
                  effect="dark"
                  content="刷新"
                  placement="top"
                >
                  <el-button
                    class="el-icon-refresh right-btns"
                    size="small"
                  ></el-button>
                </el-tooltip>
                <el-tooltip
                  class="item"
                  effect="dark"
                  content="密度"
                  placement="top"
                >
                  <el-button
                    class="el-icon-s-operation right-btns"
                    size="small"
                  ></el-button>
                </el-tooltip>
                <el-tooltip
                  class="item"
                  effect="dark"
                  content="设置"
                  placement="top"
                >
                  <el-button
                    class="el-icon-setting right-btns"
                    size="small"
                  ></el-button>
                </el-tooltip>
                <el-tooltip
                  class="item"
                  effect="dark"
                  content="全屏"
                  placement="top"
                >
                  <el-button
                    class="el-icon-rank right-btns"
                    size="small"
                  ></el-button>
                </el-tooltip>
              </div>
            </div>

      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        max-height="750"
        row-key="id"
        default-expand-all
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
          <el-table-column fixed type="index" width="55" align="center">
          </el-table-column>
          <el-table-column
            prop="name"
            label="菜单名称"
            width="150"
            align="center"
          >
          </el-table-column>
          <el-table-column label="菜单类型" width="80" align="center">
            <template slot-scope="scope">
              <el-tag size="mini" type="info" v-if="scope.row.type === 0"
                >模块</el-tag
              >
              <el-tag size="mini" type="primary" v-else-if="scope.row.type === 1"
                >菜单</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column
            prop="routerUrl"
            label="路由地址"
            align="center"
            width="200"
          >
          </el-table-column>
          <el-table-column
            prop="componentUrl"
            label="组件路径"
            width="200"
            align="center"
          >
          </el-table-column>
          <el-table-column label="权限标识" align="center" width="100">
            <template slot-scope="scope">
              <el-tag
                size="mini"
                type="success"
                v-if="scope.row.permission === 0"
                >add</el-tag
              >
              <el-tag
                size="mini"
                type="warning"
                v-else-if="scope.row.permission === 1"
                >sub</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column prop="id" label="菜单ID" align="center" width="100">
          </el-table-column>
          <el-table-column
            prop="parentId"
            label="父级ID"
            align="center"
            width="100"
          >
          </el-table-column>
          <el-table-column
            prop="status"
            label="状态"
            align="center"
            width="100"
          >
            <template slot-scope="scope">
              <el-tag size="mini" type="success" v-if="scope.row.status === 0"
                >正常</el-tag
              >
              <el-tag
                size="mini"
                type="warning"
                v-else-if="scope.row.status === 1"
                >异常</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" align="center" width="150">
          </el-table-column>
          <el-table-column prop="updateTime" label="更新时间" align="center" width="150">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="280" align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                class="el-icon-edit"
                plain
                @click="handleEdit(scope.$index, scope.row)"
                >编辑</el-button
              >
              <el-button
                size="mini"
                class="el-icon-delete"
                type="danger"
                plain
                @click="handleDelete(scope.$index, scope.row)"
                >删除</el-button
              >
              <el-button
                size="mini"
                class="el-icon-add"
                type="success"
                plain
                @click="handleUpDate(scope.$index, scope.row)"
                >添加</el-button
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
      // 被选择
      multipleSelection: [],

      // 数据
      tableData: [
        {
          name: "控制面板",
          icon: "",
          type: 0,
          routerUrl: "/dashboard",
          componentUrl: "",
          permission: 1,
          id: 1,
          parentId: 0,
          status: 1,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          children: [
            {
              name: "工作台",
              icon: "",
              type: 1,
              routerUrl: "/dashboard/workplace",
              componentUrl: "/views/dashboard/workplace",
              permission: 1,
              id: 11,
              parentId: 0,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
            {
              name: "分析页",
              icon: "",
              type: 1,
              routerUrl: "/dashboard/analysis",
              componentUrl: "/views/dashboard/analysis",
              permission: 1,
              id: 12,
              parentId: 0,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
            {
              name: "监控",
              icon: "",
              type: 1,
              routerUrl: "/dashboard/monitor",
              componentUrl: "/views/dashboard/monitor",
              permission: 0,
              id: 13,
              parentId: 0,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
          ],
        },

        {
          name: "系统管理",
          icon: "",
          type: 0,
          routerUrl: "/system",
          componentUrl: "",
          permission: 0,
          id: 2,
          parentId: 0,
          status: 0,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          children: [
            {
              name: "管理员管理",
              icon: "",
              type: 1,
              routerUrl: "/system/admin",
              componentUrl: "/views/system/admin",
              permission: 0,
              id: 21,
              parentId: 2,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
            {
              name: "部门管理",
              icon: "",
              type: 1,
              routerUrl: "/dashboard/department",
              componentUrl: "/views/system/department",
              permission: 0,
              id: 22,
              parentId: 2,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
            {
              name: "菜单管理",
              icon: "",
              type: 1,
              routerUrl: "/dashboard/menu",
              componentUrl: "/views/system/menu",
              permission: 0,
              id: 23,
              parentId: 2,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
          ],
        },

        {
          name: "业务管理",
          icon: "",
          type: 0,
          routerUrl: "/business",
          componentUrl: "",
          permission: 0,
          id: 3,
          parentId: 0,
          status: 0,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          children: [
            {
              name: "广告业务",
              icon: "",
              type: 1,
              routerUrl: "/business/adver",
              componentUrl: "/views/business/adver",
              permission: 0,
              id: 31,
              parentId: 3,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
            {
              name: "活动业务",
              icon: "",
              type: 1,
              routerUrl: "/business/activity",
              componentUrl: "/views/business/activity",
              permission: 0,
              id: 32,
              parentId: 3,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
            {
              name: "节日庆典",
              icon: "",
              type: 1,
              routerUrl: "/business/celebreation",
              componentUrl: "/views/business/celebreation",
              permission: 0,
              id: 33,
              parentId: 3,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
            {
              name: "VIP充值",
              icon: "",
              type: 1,
              routerUrl: "/business/vip",
              componentUrl: "/views/business/vip",
              permission: 0,
              id: 34,
              parentId: 3,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
          ],
        },

        {
          name: "用户管理",
          icon: "",
          type: 0,
          routerUrl: "/user",
          componentUrl: "",
          permission: 0,
          id: 4,
          parentId: 0,
          status: 0,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          children: [
            {
              name: "用户管理",
              icon: "",
              type: 1,
              routerUrl: "/user/commentUser",
              componentUrl: "/views/user/commentUser",
              permission: 0,
              id: 41,
              parentId: 4,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
            {
              name: "特权管理",
              icon: "",
              type: 1,
              routerUrl: "/user/vipUser",
              componentUrl: "/views/user/vipUser",
              permission: 0,
              id: 42,
              parentId: 4,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
          ],
        },

        {
          name: "日志管理",
          icon: "",
          type: 0,
          routerUrl: "/log",
          componentUrl: "",
          permission: 0,
          id: 5,
          parentId: 0,
          status: 0,
          createTime: "2022-7-23 18:30:55",
          updateTime: "2022-7-23 18:30:55",
          children: [
            {
              name: "登陆日志",
              icon: "",
              type: 1,
              routerUrl: "/log/loginLog",
              componentUrl: "/views/log/loginLog",
              permission: 0,
              id: 51,
              parentId: 4,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
            {
              name: "操作日志",
              icon: "",
              type: 1,
              routerUrl: "/user/opeartionLog",
              componentUrl: "/views/log/opeartionLog",
              permission: 0,
              id: 52,
              parentId: 4,
              status: 0,
              createTime: "2022-7-23 18:30:55",
              updateTime: "2022-7-23 18:30:55",
            },
          ],
        },
      ],
    };
  },
  methods: {
    // 编辑
    handleEdit(index, row) {
      console.log(index, row);
      this.$message.warning("暂未开放功能");
    },
    // 重置密码
    handleUpDate(index, row) {
      console.log(index, row);
      this.$message.warning("暂未开放功能");
    },
    // 删除一个
    handleDelete(index, row) {
      console.log(index, row);
      this.$message.warning("暂未开放功能");
    },
    // 删除多个
    handleDeleteMore(index, row) {
      console.log(index, row);
      this.$message.warning("暂未开放功能");
    },
    // 添加
    handleAdd(scope) {
      this.$message.warning("暂未开放功能");
    },
  },
};
</script>

<style lang='less' scoped>
.table-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}


.right-btns {
  font-size: 18px;
}
</style>