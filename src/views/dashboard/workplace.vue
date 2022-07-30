<template>
  <div>
    <!-- 欢迎信息 -->
    <el-row class="header">
      <el-card>
        <!-- 左侧盒子 -->
        <div class="header-left-box">
          <img src="@/assets/images/user.jpeg" alt="" />
          <div>
            <h2>早安，管理员，开始您一天的工作吧！</h2>
            <p class="weather">今日阴转小雨，22℃ - 32℃，出门记得带伞哦。</p>
          </div>
        </div>
        <!-- 右侧盒子 -->
        <div class="header-right-box">
          <div class="right-item">
            <div class="text">
              <span class="icon"><i class="el-icon-menu"></i></span>
              <span>项目数</span>
            </div>
            <span class="nums">2</span>
          </div>
          <div class="right-item">
            <div class="text">
              <span class="icon"><i class="el-icon-finished"></i></span>
              <span>代办项</span>
            </div>
            <span class="nums">4/9</span>
          </div>
          <div class="right-item">
            <div class="text">
              <span class="icon"><i class="el-icon-bell"></i></span>
              <span>消息</span>
            </div>
            <span class="nums">541</span>
          </div>
        </div>
      </el-card>
    </el-row>

    <!-- 快捷入口 -->
    <el-row class="entrance">
      <el-card v-for="(item, i) in entrance" :key="i" shadow="hover">
        <div class="entrance-item" @click="$router.push(item.router)">
          <i :class="item.icon" :style="'color:' + item.color"></i>
          <span>{{ item.label }}</span>
        </div>
      </el-card>
    </el-row>

    <!-- 任务和更新 -->
    <el-row class="task">
      <!-- 更新动态 -->
      <el-card class="update">
        <div slot="header" class="clearfix">
          <span>更新动态</span>
        </div>
        <div class="scroll-list">
          <el-timeline>
            <el-timeline-item
              :timestamp="item.time"
              placement="top"
              v-for="(item, i) in updateData"
              :key="i"
            >
              <el-card>
                <h4>{{ item.content }}</h4>
              </el-card>
            </el-timeline-item>
          </el-timeline>
        </div>
      </el-card>

      <!-- 任务 -->
      <el-card class="tasks">
        <div slot="header" class="clearfix">
          <span>我的任务</span>
        </div>
        <div class="task-box">
          <el-table
            :data="taskData"
            style="width: 100%; text-align: center"
            empty-text="暂无任务"
            header-align="center"
          >
            <el-table-column type="index" width="30"> </el-table-column>
            <el-table-column prop="priority" label="优先级" align="center">
              <template v-slot:default="scope">
                <el-tag size="mini" type="info" v-if="scope.row.priority == 0"
                  >普通</el-tag
                >
                <el-tag
                  size="mini"
                  type="success"
                  v-else-if="scope.row.priority == 1"
                  >较重要</el-tag
                >
                <el-tag
                  size="mini"
                  type="warning"
                  v-else-if="scope.row.priority == 2"
                  >重要</el-tag
                >
                <el-tag
                  size="mini"
                  type="danger"
                  v-else-if="scope.row.priority == 3"
                  >紧急</el-tag
                >
              </template>
            </el-table-column>
            <el-table-column prop="task" label="任务名称" width="300">
            </el-table-column>
            <el-table-column prop="status" label="状态" align="center">
              <template v-slot:default="scope">
                <el-tag size="mini" type="warning" v-if="scope.row.status == 0"
                  >未开始</el-tag
                >
                <el-tag
                  size="mini"
                  type="danger"
                  v-else-if="scope.row.status == 1"
                  >进行中</el-tag
                >
                <el-tag
                  size="mini"
                  type="info"
                  v-else-if="scope.row.status == 2"
                  >已完成</el-tag
                >
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-card>

      <!-- 目标 -->
      <el-card class="target">
        <div slot="header" class="clearfix">
          <span>本周目标</span>
        </div>
        <div class="target-box">
          <div id="targetTable"></div>
          <span>恭喜，本周目标已达成！</span>
        </div>
      </el-card>
    </el-row>

    <el-row class="last">
      <!-- 进度 -->
      <el-card class="progress">
        <div slot="header" class="clearfix">
          <span>活动进度</span>
        </div>
        <div class="task-box">
          <el-table
            :data="activityData"
            style="width: 100%; text-align: center"
            empty-text="暂无进行中的活动"
            header-align="center"
          >
            <el-table-column type="index" width="30"> </el-table-column>
            <el-table-column
              prop="name"
              label="活动名称"
            >
            </el-table-column>
            <el-table-column prop="startTime" label="开始时间" align="center" width="100">
            </el-table-column>
            <el-table-column prop="endTime" label="结束时间" align="center" width="100">
            </el-table-column>
            <el-table-column prop="status" label="状态" align="center" width="100">
              <template v-slot:default="scope">
                <el-tag size="mini" type="info" v-if="scope.row.status == 0"
                  >已结束</el-tag
                >
                <el-tag
                  size="mini"
                  type="warning"
                  v-else-if="scope.row.status == 1"
                  >未开始</el-tag
                >
                <el-tag
                  size="mini"
                  type="success"
                  v-else-if="scope.row.status == 2"
                  >进行中</el-tag
                >
                <el-tag
                  size="mini"
                  type="danger"
                  v-else-if="scope.row.status == 3"
                  >已延期</el-tag
                >
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-card>

      <!-- 团队 -->
      <el-card class="team">
        <div slot="header" class="clearfix">
          <span>团队成员</span>
        </div>
        <div class="team-box">
          <div class="team-item" v-for="(item, i) in teamData" :key="i">
            <div class="item-left">
              <img :src="item.userImg" alt="">
              <div>
                <p class="item-username">{{item.userName}}</p>
                <p class="item-role">{{item.role}}</p>
              </div>
            </div>
            <div class="item-right">
              <el-tag
                  size="mini"
                  type="danger"
                  v-if="item.status === 0"
                  >离线</el-tag
                >
                <el-tag
                  size="mini"
                  type="success"
                  v-else-if="item.status === 1"
                  >在线</el-tag
                >
            </div>
          </div>
        </div>
      </el-card>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      
      // 团队成员
      teamData: [{
        userImg: require('@/assets/images/user.jpeg'),
        userName: '陌上微凉',
        role: '管理员，测试员',
        status: 1
      },{
        userImg: require('@/assets/images/user.jpeg'),
        userName: '奥德赛巴莱',
        role: '审核员',
        status: 0
      },{
        userImg: require('@/assets/images/user.jpeg'),
        userName: '出清',
        role: '审核员',
        status: 1
      }],
      // 活动
      activityData: [
        {
          name: "面对面拥抱",
          startTime: "2020-03-01",
          endTime: "2020-06-01",
          status: 2,
        },
        {
          name: "面对面拥抱",
          startTime: "2020-03-01",
          endTime: "2020-06-01",
          status: 0,
        },
        {
          name: "面对面拥抱",
          startTime: "2020-03-01",
          endTime: "2020-06-01",
          status: 1,
        },
        {
          name: "面对面拥抱",
          startTime: "2020-03-01",
          endTime: "2020-06-01",
          status: 1,
        },
        {
          name: "面对面拥抱",
          startTime: "2020-03-01",
          endTime: "2020-06-01",
          status: 3,
        },
      ],
      // 我的任务
      taskData: [
        {
          priority: 3,
          task: "架构设计与技术预研",
          status: 0,
        },
        {
          priority: 2,
          task: "架构设计与技术预研",
          status: 1,
        },
        {
          priority: 1,
          task: "架构设计与技术预研",
          status: 0,
        },
        {
          priority: 1,
          task: "架构设计与技术预研",
          status: 2,
        },
        {
          priority: 0,
          task: "架构设计与技术预研",
          status: 2,
        },
      ],
      // 更新数据
      updateData: [
        {
          time: "2022/7/30",
          content: "完成v1.0.0开发",
        },
        {
          time: "2022/8/5",
          content: "修复一些列bug",
        },
        {
          time: "2022/8/10",
          content: "完成v1.0.2开发",
        },
        {
          time: "2022/8/12",
          content: "完成v1.0.3开发",
        },
        {
          time: "2022/8/15",
          content: "残疾人婚恋平台APP正式上线",
        },
      ],
      // 快捷入口数据
      entrance: [
        {
          icon: "el-icon-user",
          color: "skyblue",
          label: "用户管理",
          router: '/user/commontUser'
        },
        {
          icon: "el-icon-data-line",
          color: "lightgreen",
          label: "广告投放",
          router: '/business/adver'
        },
        {
          icon: "el-icon-menu",
          color: "lightred",
          label: "创建活动",
          router: '/business/activity'
        },
        {
          icon: "el-icon-s-ticket",
          color: "purple",
          label: "节日庆典",
          router: '/business/celebration'
        },
        {
          icon: "el-icon-s-promotion",
          color: "lightgrey",
          label: "特权管理",
          router: '/business/vip'
        },
        {
          icon: "el-icon-data-analysis",
          color: "green",
          label: "监控",
          router: '/dashboard/monitor'
        },
        {
          icon: "el-icon-files",
          color: "pink",
          label: "数据分析",     
          router: '/dashboard/analysis'
        },
        {
          icon: "el-icon-shopping-bag-2",
          color: "orange",
          label: "个人中心",
          router: '/person/personData'
        },
      ],
    };
  },
  mounted() {
    this.loadTargetTable();
  },
  methods: {
    load() {
      this.updateData += 2;
    },
    loadTargetTable() {
      var chartDom = document.getElementById("targetTable");
      var myChart = this.$echarts.init(chartDom);

      var option = {
        series: [
          {
            type: "gauge",
            startAngle: 180,
            endAngle: 0,
            min: 0,
            max: 240,
            splitNumber: 12,
            itemStyle: {
              color: "#58D9F9",
              shadowColor: "rgba(0,138,255,0.45)",
              shadowBlur: 5,
              shadowOffsetX: 2,
              shadowOffsetY: 2,
            },
            progress: {
              show: true,
              roundCap: true,
              width: 8,
            },
            pointer: {
              icon: "path://M2090.36389,615.30999 L2090.36389,615.30999 C2091.48372,615.30999 2092.40383,616.194028 2092.44859,617.312956 L2096.90698,728.755929 C2097.05155,732.369577 2094.2393,735.416212 2090.62566,735.56078 C2090.53845,735.564269 2090.45117,735.566014 2090.36389,735.566014 L2090.36389,735.566014 C2086.74736,735.566014 2083.81557,732.63423 2083.81557,729.017692 C2083.81557,728.930412 2083.81732,728.84314 2083.82081,728.755929 L2088.2792,617.312956 C2088.32396,616.194028 2089.24407,615.30999 2090.36389,615.30999 Z",
              length: "75%",
              width: 10,
              offsetCenter: [0, "5%"],
            },
            axisLine: {
              roundCap: true,
              lineStyle: {
                width: 10,
              },
            },
            axisTick: {
              splitNumber: 2,
              lineStyle: {
                width: 2,
                color: "#999",
              },
            },
            splitLine: {
              length: 5,
              lineStyle: {
                width: 3,
                color: "#999",
              },
            },
            axisLabel: {
              distance: 10,
              color: "#999",
              fontSize: 10,
            },
            title: {
              show: false,
            },
            detail: {
              lineHeight: 20,
              height: 20,
              offsetCenter: [0, "50%"],
              valueAnimation: true,
              formatter: function (value) {
                return "{value|" + value.toFixed(0) + "}{unit|件}";
              },
              rich: {
                value: {
                  fontSize: 30,
                  fontWeight: "bolder",
                  color: "#777",
                },
                unit: {
                  fontSize: 20,
                  color: "#999",
                  padding: [0, 0, 0, 10],
                },
              },
            },
            data: [
              {
                value: 100,
              },
            ],
          },
        ],
      };

      option && myChart.setOption(option);
    },
  },
};
</script>

<style lang="less" scoped>
.el-row {
  margin-bottom: 16px;
}

.header {
  .el-card {
    height: 100px;
    width: 100%;
    // display: flex;

    .header-left-box {
      float: left;
      display: flex;
      align-items: center;

      img {
        height: 70px;
        width: 70px;
        border-radius: 50%;
        margin-right: 20px;
      }

      div {
        h2,
        p {
          height: 30px;
          line-height: 30px;
        }
        h2 {
          font-size: 22px;
          font-weight: normal;
        }

        p {
          font-size: 14px;
          color: #777;
        }
      }
    }

    .header-right-box {
      float: right;
      display: flex;
      height: 70px;

      .right-item {
        margin-left: 20px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;

        .text {
          display: flex;
          align-items: center;
          .icon {
            margin-right: 10px;
            background-color: #eee;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            width: 25px;
            height: 25px;

            i {
              font-size: 12px;
              color: #1890ff;
            }
          }

          .icon:first-child {
            background-color: #e6f7ff;
            border: 1px solid #b6d8f8;
          }

          .icon:last-child {
            background-color: lightgreen;
          }
        }

        .nums {
          margin-top: 10px;
          font-size: 22px;
          font-weight: bold;
        }
      }
    }
  }
}

.entrance,
.task,
.last {
  display: flex;

  .el-card {
    flex: 1;
    margin: 0 8px;
  }

  .el-card:first-child {
    margin-left: 0;
  }

  .el-card:last-child {
    margin-right: 0;
  }
}

.entrance {
  height: 100px;

  .el-card {
    cursor: pointer;

    .entrance-item {
      width: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;

      i {
        margin-bottom: 10px;
        font-size: 30px;
      }
    }
  }
}

.task {
  height: 400px;

  .update {
    .scroll-list {
      width: 100%;
      height: auto !important;
      max-height: 300px;
      overflow: scroll;
      overflow-x: hidden;
    }
  }

  .tasks {
    // .task-box{
    //   height: auto !important;
    //   max-height: 300px;
    //   overflow: scroll;
    //   overflow-x: hidden;
    // }
  }

  .target {
    .target-box {
      display: flex;
      flex-direction: column;
      align-items: center;

      #targetTable {
        width: 100%;
        height: 300px;
      }

      span{
        margin-top: -50px;
      }
    }
  }
}

.last {
  height: 400px;

  .progress {
    flex: 2;
  }

  .team {
    .team-box{
      .team-item{
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 80px;
        border-bottom: 1px solid #ccc;

        .item-left{
          display: flex;
          align-items: center;

          img{
            height: 50px;
            width: 50px;
            border-radius: 50%;
            margin-right: 10px;
          }

          div{
            p{
              height: 30px;
              line-height: 30px;
            }

            .item-role{
              font-size: 14px;
              color: #777;
            }
          }
        }
      }

      .team-item:last-child{
        border-bottom: none;
      }
    }
  }
}
</style>
