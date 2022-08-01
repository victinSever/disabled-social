<template>
  <div>
    <el-row>
    </el-row>
    <el-form
      :model="data"
      size="mini"
      label-width="300px"
      label-position="left"
    >

      <el-form-item label="每天无限制点击喜欢" prop="vip2">
        <el-switch
          v-model="data.vip2"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="滑错随时反悔" prop="vip3">
        <el-switch
          v-model="data.vip3"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>

      <el-divider>约吧中心</el-divider>

      <el-form-item label="活动优先报名权" prop="vip8">
        <el-switch
          v-model="data.vip8"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="情感咨询" prop="vip9">
        <el-switch
          v-model="data.vip9"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>

      <el-divider>广场中心</el-divider>

      <el-form-item label="私聊消息长久保存" prop="vip4">
        <el-switch
          v-model="data.vip4"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="群聊消息长久保存" prop="vip5">
        <el-switch
          v-model="data.vip5"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="私聊语音通话" prop="vip6">
        <el-switch
          v-model="data.vip6"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>

      <el-divider>消息中心</el-divider>

      <el-form-item label="查看被关注者权限" prop="vip1">
        <el-switch
          v-model="data.vip1"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="简历定制" prop="vip7">
        <el-switch
          v-model="data.vip7"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>

      <el-divider>个人中心</el-divider>
    </el-form>
  </div>
</template>

<script>
export default {
  data(){
    return {
      data: {
        vip1: true,
        vip2: true,
        vip3: true,
        vip4: true,
        vip5: true,
        vip6: true,
        vip7: true,
        vip8: true,
        vip9: true,
      },
    }
  },
  watch:{
    data:{
      deep: true,
      handler(val){
        console.log(val);
        this.$message.success('修改成功！')
      }
    }
  }
}
</script>

<style scoped lang='less'>

</style>