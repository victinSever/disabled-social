<template>
  <div>
    <el-form label-position="right" label-width="100px" :model="data">
      <el-col :span="8">
        <el-form-item label="头像">
          <div class="block">
            <el-avatar
              shape="square"
              :size="80"
              :src="data.imagePath"
            ></el-avatar>
          </div>
        </el-form-item>
        <el-form-item label="关注数">{{ data.concern }}</el-form-item>
        <el-form-item label="被关注数">{{ data.beConcern }}</el-form-item>
        <el-form-item label="点赞数">{{ data.dianzan }}</el-form-item>
        <el-form-item label="积分">{{ data.sorts }}</el-form-item>
        <el-form-item label="角色">{{ data.role }}</el-form-item>
        <el-form-item label="特权用户">
          <el-tag v-if="data.isVip === 1" type="success">VIP1</el-tag>
        </el-form-item>
        <el-form-item label="是否认证">{{ data.isRight }}</el-form-item>
        <el-form-item label="在线时长">{{
          data.onlineTime + data.onlineTimeUnit
        }}</el-form-item>
        <el-form-item label="是否注销">
          <el-tag v-if="data.status === 1" type="danger">是</el-tag>
          <el-tag v-else-if="data.status === 0" type="success">否</el-tag>
        </el-form-item>
        <el-form-item label="注册时间">{{ data.createTime }}</el-form-item>
        <el-form-item label="更新时间">{{ data.updateTime }}</el-form-item>
        <el-form-item label="注销时间">{{ data.destoryTime }}</el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="用户名">{{ data.personName }}</el-form-item>
        <el-form-item label="用户ID">{{ data.personId }}</el-form-item>
        <el-form-item label="性别">{{ data.sex }}</el-form-item>
        <el-form-item label="年龄">{{ data.age }}</el-form-item>
        <el-form-item label="身高">{{ data.height + 'cm' }}</el-form-item>
        <el-form-item label="体重">{{ data.weight + '斤' }}</el-form-item>
        <el-form-item label="手机号码">{{ data.phone }}</el-form-item>
        <el-form-item label="残疾号码">{{ data.disableNumber }}</el-form-item>
        <el-form-item label="工作地区">{{ data.workAddr }}</el-form-item>
        <el-form-item label="户籍地区">{{ data.householdAddr }}</el-form-item>
        <el-form-item label="婚姻情况">{{ data.maritalStatus }}</el-form-item>
        <el-form-item label="年收入">{{ data.income + '元' }}</el-form-item>
        <el-form-item label="职业">{{ data.occupation }}</el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="购房情况">{{ data.housingStatus }}</el-form-item>
        <el-form-item label="购车情况">{{ data.carStatus }}</el-form-item>
        <el-form-item label="期望结婚时间">{{ data.expectedMarryTime }}</el-form-item>
        <el-form-item label="自我介绍">{{ data.personIntro }}</el-form-item>
        <el-form-item label="爱情宣言">{{ data.personSign }}</el-form-item>
        <el-form-item label="经度">{{ data.longitude }}</el-form-item>
        <el-form-item label="纬度">{{ data.latitude }}</el-form-item>
        <el-form-item label="微信">{{ data.wechat }}</el-form-item>
        <el-form-item label="微信二维码">
          <div class="block">
            <el-avatar
              shape="square"
              :size="80"
              :src="data.wechatCodeImagesPath"
            ></el-avatar>
          </div>
        </el-form-item>
        <el-form-item label="QQ账号">{{ data.qq }}</el-form-item>
        <el-form-item label="邮箱">{{ data.email }}</el-form-item>
      </el-col>
    </el-form>
  </div>
</template>

<script>
export default {
  props: ["data"],
};
</script>

<style>
</style>