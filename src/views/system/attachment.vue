<template>
  <div>
    <el-form label-width="150px">
      <el-row>
        <el-row>
          <h3>检索附件</h3>
        </el-row>
        <el-form-item label="搜索">
          <div class="search"
            ><el-input placeholder="搜索图片名" v-model="keyword"></el-input>
            <el-button type="primary" @click="search">搜索</el-button></div
          >
        </el-form-item>
        <el-form-item label="检索结果" v-if="resultUrl">
          <span>{{resultUrl}}</span>
        </el-form-item>
      </el-row>
      <el-row>
        <el-row>
          <h3>上传附件</h3>
        </el-row>
        <el-row>
          <el-form-item label="上传地址">
            <el-cascader
              v-model="url"
              :options="urls"
              :props="{ expandTrigger: 'hover' }"
              @change="handleChange"
            ></el-cascader>
          </el-form-item>
          <el-form-item label="图片">
            <div class="imgUpload">
              <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <el-button type="primary" @click="send">上传</el-button>
            </div>
          </el-form-item>
        </el-row>
      </el-row>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      keyword: "",
      resultUrl: '',
      imageUrl: "",
      file: '',
      url: "",
      urls: [
        {
          label: "file",
          value: 1,
        },
        {
          label: "images",
          value: 2,
          children: [
            {
              label: "bgc",
              value: 21,
            },
          ],
        },
      ],
    };
  },
  methods: {
    search(){
      if(this.keyword){
        this.resultUrl = this.keyword
        this.$message.success('检索成功！')
      }else{
        this.$message.warning('检索内容不能为空！')
      }
      
    },
    async send(){
      if(!this.url){
        return this.$message.warning('你还未选择上传路径！')
      }
      if(!this.file || !this.imageUrl){
        return this.$message.warning('你还未选择图片！')
      }      
      try{
        let res = await this.$http.post("http://222.177.66.230:9898/upload", { file: this.file });
        console.log(res);
        this.$message.success('上传成功！')
      }catch(err){
        this.$message.error('上传失败！失败原因：' + err)
      }    
    },
    handleChange(value) {
      console.log(value);
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      console.log(file);
      this.$message.success("上传成功");
    },

    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      this.imageUrl = URL.createObjectURL(file)
      this.file = file
      return isJPG && isLt2M;
    },

  },
};
</script>

<style scoped lang='less'>
.search{
  display: flex;

  .el-input{
    width: 300px;
  }
}

.imgUpload{
  display: flex;
  align-items: flex-end;

  .el-button{
    margin-left: 20px;
  }
}
.el-row {
  margin: 20px 0;
}
.el-input {
  width: 80%;
  margin-right: 10px;
}

.avatar-uploader
{
  height: 200px;
  width: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  border-radius: 5px;
  border: 2px dashed #ddd;

  img {
    width: 200px;
    height: 200px;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 200px;
    height: 200px;
    line-height: 200px;
    text-align: center;
  }
}

.avatar-uploader .el-upload:hover {
  border: 2px dashed #409eff;
}

.avatar {
  display: block;
}
</style>