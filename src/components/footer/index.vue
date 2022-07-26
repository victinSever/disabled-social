<template>
  <div class="root">
    <div class="link">
        <span>官网</span>
        <span>文档</span>
        <span>授权</span>
    </div>
    <div class="copyright">
        Copyright © 2022 重庆理工大学JavaWeb实验室
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style lang="less" scoped>
.root{
    height: 100px;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: 14px;
    color: #777;

    .link{
      span{
        margin: 0 10px;
        cursor: pointer;
        transition: .4s;
      }

      span:hover{
        color: skyblue;
      }
    }

    .copyright{
      margin-top: 20px;
    }
}
</style>