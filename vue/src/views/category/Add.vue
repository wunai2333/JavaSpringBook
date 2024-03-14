<template>
  <div style="width: 80%">
    <h2 style="margin-bottom: 30px">新增分类</h2>
    <el-form :inline="true" :model="form" label-width="100px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.remark" placeholder="请输入备注"></el-input>
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
  name: "AddCategory",
  data() {
    return {
      form: {}
    }
  },
  methods: {
    save() {
      request.post('/category/save', this.form).then(res => {
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