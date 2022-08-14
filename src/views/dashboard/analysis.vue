<template>
  <div>
    <!-- 数据卡片 -->
    <el-row class="card">
      <!-- 销售额 -->
      <el-card class="yearSale">
        <div slot="header" class="clearfix">
          <span>年销售额</span>
          <el-tooltip
            class="item"
            effect="dark"
            style="float: right"
            content="指标说明"
            placement="top"
          >
            <i class="el-icon-magic-stick" style="cursor: pointer"></i>
          </el-tooltip>
        </div>
        <div class="yearSale-box">
          <p class="box-top">
            <span>￥{{ yearSaleNum }}</span>
          </p>
          <p class="box-bottom">
            <span
              >周同比{{ yearSale.add }}%
              <i class="el-icon-caret-top"></i>
            </span>
            <span
              >日同比{{ yearSale.sub }}%
              <i class="el-icon-caret-bottom"></i>
            </span>
          </p>
        </div>
      </el-card>

      <!-- 订单数 -->
      <el-card>
        <div slot="header" class="clearfix">
          <span>订单总数</span>
          <el-tag style="float: right" type="danger" size="mini">日</el-tag>
        </div>
      </el-card>

      <!-- 交易总额 -->
      <el-card>
        <div slot="header" class="clearfix">
          <span>交易总额</span>
          <el-tag style="float: right" type="primary" size="mini">月</el-tag>
        </div>
      </el-card>

      <!-- 会员增量 -->
      <el-card>
        <div slot="header" class="clearfix">
          <span>会员增量</span>
          <el-tag style="float: right" type="success" size="mini">周</el-tag>
        </div>
      </el-card>
    </el-row>

    <!-- 销售量 -->
    <el-row class="sale">
      <el-card>
        <div slot="header" class="clearfix">
          <span>VIP充值量</span>
          <div style="float: right">月</div>
        </div>
        <div class="sale-box">
          <div id="saleTable"></div>
        </div>
      </el-card>
    </el-row>

    <!-- 访问量 -->
    <el-row class="visit">
      <el-card class="visit-left">
        <div slot="header" class="clearfix">
          <span>最近一小时访问数据</span>
        </div>
        <div class="visit-left-box">
          <div id="visitTable"></div>
        </div>
      </el-card>
      <el-card class="visit-right">
        <div slot="header" class="clearfix">
          <span>热门关键词</span>
        </div>
      </el-card>
    </el-row>
  </div>
</template>

<script>
import { formBigNum } from "@/api/tools";
export default {
  data() {
    return {
      // 最近一小时访问数据
      recentVisitHourData: {
        timeData: [],
      },
      // 上四个卡片数据
      yearSale: {
        num: 987242541,
        add: 12,
        sub: 11,
      },
      // Vip充值量数据
      vipSaleData: {
        mouths: [
          "1月",
          "2月",
          "3月",
          "4月",
          "5月",
          "6月",
          "7月",
          "8月",
          "9月",
          "10月",
          "11月",
          "12月",
        ],
        vipData: [485, 41, 541, 451, 54, 185, 21, 54, 547, 854, 541, 525],
      },
    };
  },
  computed: {
    // 格式化数字，添加逗号
    yearSaleNum() {
      return formBigNum(this.yearSale.num);
    },
  },
  mounted() {
    this.getHourArray()
    this.loadSaleTable();
    this.loadVisitTable();
  },
  methods: {
    // 获取最近一小时的时间数组
    getHourArray() {
      let array = [];
      let date = new Date();
      let hour = date.getHours() - 1
      let minite = date.getMinutes();
      for (let i = 0; i < 60; i += 5) {
        if(minite + i > 60) {
          hour ++
          minite -= 60
        }
        let str = hour + ":" + (minite + i)
        array.push(str)
      }
      this.recentVisitHourData.timeData = array
    },

    loadSaleTable() {
      var chartDom = document.getElementById("saleTable");
      var myChart = this.$echarts.init(chartDom);
      var option;

      option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            crossStyle: {
              color: "#cc",
            },
          },
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line", "bar"] },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        },
        legend: {
          data: ["销售额", "Temperature"],
        },
        xAxis: [
          {
            type: "category",
            data: this.vipSaleData.mouths,
            axisPointer: {
              type: "shadow",
            },
          },
        ],
        yAxis: [
          {
            type: "value",
            name: "销售额",
            min: 0,
            max: 1200,
            axisLabel: {
              formatter: "{value} ￥",
            },
          },
        ],
        series: [
          {
            name: "销售额",
            type: "bar",
            color: "#3aa1ff",
            tooltip: {
              valueFormatter: function (value) {
                return value + " ￥";
              },
            },
            data: this.vipSaleData.vipData,
          },
        ],
      };

      option && myChart.setOption(option);
    },
    loadVisitTable() {
      var chartDom = document.getElementById("visitTable");
      var myChart = this.$echarts.init(chartDom);
      var option;

      option = {
        color: ["#80FFA5", "#00DDFF"],
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985",
            },
          },
        },
        legend: {
          data: ["浏览量", "访问量"],
        },
        toolbox: {
          feature: {
            saveAsImage: {},
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: [
          {
            type: "category",
            boundaryGap: false,
            data: this.recentVisitHourData.timeData,
          },
        ],
        yAxis: [
          {
            type: "value",
          },
        ],
        series: [
          {
            name: "浏览量",
            type: "line",
            stack: "Total",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(128, 255, 165)",
                },
                {
                  offset: 1,
                  color: "rgb(1, 191, 236)",
                },
              ]),
            },
            emphasis: {
              focus: "series",
            },
            data: [140, 232, 101, 264, 90, 340, 250,200,300,400,300,200],
          },
          {
            name: "访问量",
            type: "line",
            stack: "Total",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(0, 221, 255)",
                },
                {
                  offset: 1,
                  color: "rgb(77, 119, 255)",
                },
              ]),
            },
            emphasis: {
              focus: "series",
            },
            data: [120, 282, 111, 234, 220, 340, 310,222, 250, 300,350,500],
          },
        ],
      };

      option && myChart.setOption(option);
    },
  },
};
</script>

<style lang='less' scoped>
.el-row {
  display: flex;
  margin-bottom: 16px;

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

.card {
  height: 200px;
}

.yearSale {
  .yearSale-box {
    .box-top {
      font-size: 25px;
      color: #777;
      margin-bottom: 20px;
    }

    .box-bottom {
      font-size: 14px;

      span {
        margin: 0 5px;

        .el-icon-caret-top {
          color: green;
        }

        .el-icon-caret-bottom {
          color: red;
        }
      }
    }
  }
}

.sale {
  height: 400px;

  .sale-box {
    width: 100%;
    height: 350px;

    #saleTable {
      width: 70%;
      height: 100%;
    }
  }
}

.visit {
  height: 400px;
  .visit-left {
    flex: 3;

    .visit-left-box {
      #visitTable {
        width: 100%;
        height: 320px;
      }
    }
  }
}
</style>