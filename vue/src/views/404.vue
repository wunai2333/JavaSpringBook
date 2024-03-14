<template>
  <div style="text-align: center">
    <div style="margin-top: 150px; font-size: 100px; ">404</div>
    <div style="font-size: 60px; ">未找到页面</div>
    <div><el-boot type="text" style="margin-top: 20px; font-size: 50px; " @click="$router.push('/')">返回主页</el-boot></div>
  </div>
</template>

<script>
export default {
  name: "404"
}
</script>

<style scoped>

</style>