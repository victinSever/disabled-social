<template>
  <div>
    <el-row class="top">
      <el-card>
        <div class="top-item">
          <div>
            <el-button
              type="primary"
              icon="el-icon-s-custom"
              circle
              plain
            ></el-button>
          </div>
          <div>78.9 k</div>
          <div>总访问人数</div>
          <div>
            <i class="el-icon-caret-top"></i>
            <span>110.5%</span>
          </div>
        </div>
      </el-card>
      <el-card>
        <div class="top-item">
          <div>
            <el-button
              type="warning"
              icon="el-icon-s-promotion"
              circle
              plain
            ></el-button>
          </div>
          <div>10.9 k</div>
          <div>点击量（近30天）</div>
          <div>
            <i class="el-icon-caret-top"></i>
            <span>110.5%</span>
          </div>
        </div>
      </el-card>
      <el-card>
        <div class="top-item">
          <div>
            <el-button
              type="danger"
              icon="el-icon-s-flag"
              circle
              plain
            ></el-button>
          </div>
          <div>926.0</div>
          <div>到达量（近30天）</div>
          <div>
            <i class="el-icon-caret-bottom"></i>
            <span>15.5%</span>
          </div>
        </div>
      </el-card>
      <el-card>
        <div class="top-item">
          <div>
            <el-button
              type="success"
              icon="el-icon-s-check"
              circle
              plain
            ></el-button>
          </div>
          <div>75.3 %</div>
          <div>转化率（近30天）</div>
          <div>
            <i class="el-icon-caret-top"></i>
            <span>65.8%</span>
          </div>
        </div>
      </el-card>
    </el-row>

    <el-row class="main">
      <el-card class="user">
        <div slot="header" class="clearfix">
          <span>用户分布</span>
        </div>
        <div id="map" style="height: 400px"></div>
      </el-card>
      <div class="main-right">
        <el-card class="onlineNumber-box">
          <div slot="header" class="clearfix">
            <span>在线人数</span>
          </div>
          <div class="onlineNumber">
            <div class="time">{{ onlineNumber.time }}</div>
            <div class="number">
              {{ onlineNumber.number }}
              <span>人</span>
            </div>
            <div class="text">在线总人数</div>
            <div class="tip">
              <span>{{ onlineNumber.countTime }}</span> 秒后更新
            </div>
          </div>
        </el-card>
        <el-card>
          
        </el-card>
      </div>
    </el-row>

    <el-row class="bottom">
      <el-card class="age-box">
        <div slot="header" class="clearfix">
            <span>年龄分布</span>
          </div>
          <div id="ageTable" style="height: 200px">

          </div>
      </el-card>
      <el-card></el-card>
      <el-card></el-card>
    </el-row>
  </div>
</template>

<script>
import jsonp from "jsonp";
import "echarts/map/js/china";
import { formatDateTime } from "@/api/api";

const option = {
  title: {
    text: "用户分布",
    subtext: new Date().toString(),
  },
  tooltip: {},
  series: [
    {
      name: "当前用户人数",
      type: "map", //展示的是地图还是line线形图还是bar柱状
      map: "china",
      data: [],
      label: {
        //鼠标移动到地图显示的文字和样式
        show: true,
        color: "#000",
        fontSize: 10,
      },
      roam: true, //为true就是地图既可以平移也可以缩放
      zoom: 1.2, //控制地图的放大缩小
      itemStyle: {
        areaColor: "#1890ff",
        borderColor: "yellow", //边框颜色
      },
      /*     控制鼠标滑过之后的背景色和字体颜色*/
      emphasis: {
        label: {
          color: "#000",
          fontsize: 12,
        },
        itemStyle: {
          areaColor: "#1890ff",
        },
      },
    },
  ],
  visualMap: {
    type: "piecewise", //设置为分段型
    show: true,
    pieces: [
      { min: 10000 }, // 不指定 max，表示 max 为无限大（Infinity）。
      { min: 1000, max: 9990 },
      { min: 100, max: 999 },
      { min: 10, max: 99 },
      { min: 1, max: 9 },
    ],
    inRange: {
      symbol: "rect",
      color: ["#ebf3ff", "#1890ff"],
    },
    itemWidth: 20,
    itemHeight: 10,
  },

  tooltip: {
    trigger: "item", //数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
  },
};

export default {
  data() {
    return {
      onlineNumber: {
        time: new Date().toString(),
        number: 0,
        countTime: 5,
      }, //实时在线人数
      info: [],
      tableData: [], //底下第三个窗口的数据
      top10: [], //前10个地区数据
    };
  },
  mounted() {
    this.loadMap();
    this.loadAgeTable()
    this.countTime(); //启动计时器
  },
  methods: {
    // 获取当前时刻的在线用户数
    getOnlineUserNumber() {
      this.onlineNumber.number = Math.floor(Math.random() * 1000 + 500);
    },
    // 计时器
    countTime() {
      let that = this;
      let date = new Date();
      // 每秒计时
      setInterval(function () {
        that.onlineNumber.time = formatDateTime();
        that.onlineNumber.countTime--;
        if (that.onlineNumber.countTime === 0) {
          that.getOnlineUserNumber();
          that.onlineNumber.countTime = 5;
        }
      }, 1000);
    },
    // 获取用户的分布数据和前10数据
    getData() {
      jsonp(
        "https://interface.sina.cn/news/wap/fymap2020_data.d.json?_=1580892522427", //接口
        {},
        (err, data) => {
          if (!err) {
            let list = data.data.list.map((item) => ({
              name: item.name,
              value: item.value,
            }));
            this.top10 = data.data.jwsrTop;
            option.series[0].data = list; //将list数据移到echart中的data当中
            this.tableData = data.data.list;

            this.myChart.setOption(option);

            console.log("全部分布:", list);
            console.log("前10：", this.top10);
          }
        }
      );
    },
    // 加载地图
    loadMap() {
      this.getData();
      this.myChart = this.$echarts.init(document.getElementById("map"));

      this.myChart.setOption(option);
    },
    // 加载年龄分布环状图
    loadAgeTable() {
      let myChart = this.$echarts.init(document.getElementById("ageTable"));

      let option = {
        tooltip: {
          trigger: "item",
        },
        legend: {
          left: "5%",
        },
        series: [
          {
            name: "用户年龄分布",
            type: "pie",
            radius: ["40%", "70%"],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: "center",
            },
            emphasis: {
              label: {
                show: true,
                fontSize: "12",
                fontWeight: "bold",
              },
            },
            labelLine: {
              show: false,
            },
            data: [
              { value: 1048, name: "18 - 25" },
              { value: 735, name: "26 - 35" },
              { value: 580, name: "36 - 45" },
              { value: 484, name: "46 - 60" },
              { value: 300, name: "60以上" },
            ],
          },
        ],
      };

      myChart.setOption(option);
    },
  },
};
</script>

<style scoped lang='less'>
.el-row {
  display: flex;
  margin-bottom: 16px;

  & > .el-card {
    flex: 1;
    margin: 0 8px;
  }

  & > .el-card:first-child {
    margin-left: 0;
  }

  & > .el-card:last-child {
    margin-right: 0;
  }
}

.top {
  height: 200px;

  .top-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-around;
    width: 100%;
    height: 160px;

    & > div:nth-child(2) {
      font-size: 30px;
    }

    & > div:nth-child(3) {
      font-size: 12px;
      color: #777;
    }

    & > div:nth-child(4) {
      font-size: 14px;
      color: #777;
    }

    div {
      .el-icon-caret-top {
        color: green;
      }

      .el-icon-caret-bottom {
        color: red;
      }
    }
  }
}

.main {
  height: 500px;

  .user {
    flex: 3;
  }

  .main-right {
    flex: 1;
    margin-left: 5px;

    .el-card {
      height: 242px;
    }

    .el-card:first-child {
      margin-bottom: 16px;
    }

    .onlineNumber-box {
      .onlineNumber {
        height: 160px;
        display: flex;
        justify-content: space-around;
        align-items: center;
        flex-direction: column;
        font-size: 14px;

        .number {
          font-size: 30px;
          span {
            font-size: 12px;
            color: #777;
          }
        }

        .tip {
          span {
            color: red;
          }
        }
      }
    }

    .age-box{
      background-color: #ccc;
    }
  }
}

.bottom {
  height: 300px;
}
</style>