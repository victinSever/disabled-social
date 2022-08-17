<template>
  <div>
    <!-- <el-col :span="14" :offset="1">
      <el-card shadow="never">
        <el-form :model="data" size="mini" label-width="100px">
          <el-form-item label="ID" prop="id">{{data.diary.diaryId}}</el-form-item>
          <el-form-item label="发布时间" prop="createTime">{{data.diary.createTime}}</el-form-item>
          <el-form-item label="用户">{{data.diary.diaryUserName}}</el-form-item>
          <el-form-item label="内容" prop="activeData">
            <el-card class="content">
              <p><span>文本：</span>{{data.diary.diaryContent}}</p>
              <p><span>图片：</span></p>
              <div class="imgs" v-if="data.vedio">
                <vedio :src="data.vedio" autoplay controls alt="无"></vedio>
              </div>
              <div class="imgs" v-else>
                <div style="font-size: 12px; text-indent: 3em">该动态无视频!</div>
              </div>
            </el-card>
          </el-form-item>
          <el-form-item label="审核状态">
              <el-tag
                type="warning"
                v-if="status === 1"
                >审核中</el-tag
              >
              <el-tag
                type="success"
                v-else-if="status === 2"
                >已通过</el-tag
              >
              <el-tag
                type="danger"
                v-else-if="status === 3"
                >未通过</el-tag
              >
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
    <el-col :span="5" :offset="1">
      <el-form label-position="top">
        <el-form-item label="审核">
          <div v-if="status < 2">
            <el-button type="success" @click="auditActive(2)">通过</el-button>
          <el-button type="danger" @click="auditActive(3)">不通过</el-button>
          </div>
          <div v-else>
            <el-tag v-if="status == 3" type="danger">未通过</el-tag>
            <el-tag v-else type="success">通过</el-tag>           
          </div>
        </el-form-item>
        <el-form-item label="未通过原因" v-if="status == 3 && !isPass">
          <el-input
          type="textarea"
          placeholder="填写不予通过原因"
          focus
          v-model="faileResult"
        ></el-input>
        </el-form-item>
        <el-form-item label="继续" v-if="status == 2 || (faileResult && status == 3)">
          <el-button type="primary" @click="getNext">审核下一个</el-button>
        </el-form-item>
      </el-form>     
    </el-col> -->
  </div>
</template>

<script>
export default {
  props: ['itemData', 'index', 'result'],
  data(){
    return {
      data: {},
      isPass: false,//是否填写
      faileResult: '',
      status: 0,//当前审核状态
      indexCurr: 0,//正在审核的序号
    }
  },
//   mounted(){
//     this.indexCurr = this.index
//   },
//   watch: {
//     itemData: {
//       immediate: true,
//       handler() { 
//         this.data = this.itemData
//         this.status = this.itemData.diary.diaryStatus
//        }
//     },
//     result: {
//       immediate: true,
//       handler() { this.faileResult = this.result }
//     }
//   },
  methods: {
    // 审核
    auditActive(judge){
      return this.$message.warning('未开放接口')
      this.status = judge;
      if (judge === 2) {      
        this.isPass = true     
        this.$message.success("审核通过！");
      } else if(judge === 3){
        this.$message.error("审核未通过！");
      }
    },
    // 获取下一个动态数据
    getNext(){
      this.$emit('changeIndex', ++this.indexCurr)
    }
  }
}
</script>

<style scoped lang='less'>
.content{
  .imgs{
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
   

    img{
      width: 300px;
      box-shadow: 2px 3px 8px rgba(0,0,0,0.8);
      margin-right: 20px;
      margin-bottom: 20px;
    }
  }

  span{
      font-weight: bold;
    }
}
</style>