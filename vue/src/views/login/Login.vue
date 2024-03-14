<template>
  <div style="height: 100vh; overflow: hidden">
    <div style="width: 500px; height: 400px; background-color: wheat; border-radius: 10px;
      margin: 150px auto; padding: 60px">
      <div style="margin: 30px; text-align: center; font-size: 30px; font-weight: bold; color: dodgerblue">登 录</div>
      <el-form :model="admin" ref="loginForm">

        <el-form-item prop="username">
          <el-input placeholder="请输入账号" prefix-icon="el-icon-user" size="medium" v-model="admin.username"></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input placeholder="请输入密码" show-password prefix-icon="el-icon-lock" size="medium" v-model="admin.password"></el-input>
        </el-form-item>

        <el-button style="width: 100%" size="medium" type="primary" @click="login">登录</el-button>

      </el-form>

    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie';

export default {
  name: "LOGIN",
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
    }
  },
  methods: {
    login() {
          request.post('/admin/login', this.admin).then(res => {
            if (res.code === '200') {
              this.$notify.success("登录成功")
              if (res.data !== null) {
                Cookies.set('admin', JSON.stringify(res.data))
              }
              this.$router.push('/')
            } else {
            }
          })
        }
  }
}
</script>

<style scoped>

</style>