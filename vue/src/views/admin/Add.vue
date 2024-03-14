<template>
  <div style="width: 80%">
    <h2 style="margin-bottom: 30px">新增用户</h2>
    <el-form :inline="true" :model="form" label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
    </el-form>


    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" @click="save">提交</el-button>
      <el-button type="danger">取消</el-button>
    </div>
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: "AddAdmin",
  data() {
    return {
      form: {}
    }
  },
  methods: {
    save() {
      request.post('/admin/save', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('新增成功')
          this.form = {}
        } else {
          this.$notify.error(res.msg)
        }
      })
    }

  }
}
</script>

<style scoped>

</style>