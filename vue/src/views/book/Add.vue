<template>
  <div style="width: 80%">
    <h2 style="margin-bottom: 30px">新增图书</h2>
    <el-form :inline="true" :model="form" label-width="100px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item  label="描述" prop="description">
        <el-input style="width: 300px" type="textarea"  v-model="form.description" placeholder="请输入描述"></el-input>
      </el-form-item>
      <el-form-item label="发表日期" prop="publish">
          <el-date-picker
              v-model="form.publish"
              type="date"
              placeholder="请选择发表日期">
          </el-date-picker>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="form.author" placeholder="请输入作者"></el-input>
      </el-form-item>
      <el-form-item label="出版社" prop="publisher">
        <el-input v-model="form.publisher" placeholder="请输入出版社"></el-input>
      </el-form-item>
      <el-form-item label="分类" prop="category">
        <el-cascader
            :props="{ value: 'name', label: 'name'}"
            v-model="form.categories"
            :options="categories"></el-cascader>
      </el-form-item>
      <el-form-item label="编码" prop="bookNo">
        <el-input v-model="form.bookNo" placeholder="请输入编码"></el-input>
      </el-form-item>
      <el-form-item label="积分" prop="cover">
        <el-input v-model="form.score" placeholder="请输入所需积分"></el-input>
      </el-form-item>
      <el-form-item label="数量" prop="nums">
        <el-input v-model="form.nums" placeholder="请输入数量"></el-input>
      </el-form-item>
      <el-form-item label="封面" prop="cover">
        <el-input v-model="form.cover" placeholder="请输入封面"></el-input>
      </el-form-item>

    </el-form>


    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" @click="save">提交</el-button>
    </div>
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: "AddBook",
  data() {
    return {
      form: {},
      categories: [],
    }
  },
  created() {
    request.get('/category/tree').then(res => {
      this.categories = res.data
    })
  },
  methods: {
    save() {
      request.post('/book/save', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('新增成功')
          this.form = {}
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    handleChange(val) {
      console.log(val)
    },
  }
}
</script>

<style scoped>

</style>