<template>
  <div style="width: 80%">
    <h2 style="margin-bottom: 30px">编辑图书</h2>
    <el-form :inline="true" :model="form" label-width="100px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入名称"></el-input>
      </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述"></el-input>
        </el-form-item>
      <el-form-item label="发表日期" prop="publish">
        <el-input v-model="form.publish" placeholder="请输入发表日期"></el-input>
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
            :options="categories"
            @click="handleChange">
        </el-cascader>
      </el-form-item>
      <el-form-item label="封面" prop="cover">
      <el-input v-model="form.cover" placeholder="请输入封面"></el-input>
    </el-form-item>

      
    </el-form>


    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" @click="save">提交</el-button>
<!--      <el-button type="danger">取消</el-button>-->
    </div>
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: "EditBook",
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

    const id = this.$route.query.id
    request.get("/book/" + id).then(res => {
      this.form = res.data
      if (this.form.category) {
        this.form.categories = this.form.category.split(' > ')
        console.log(this.form.categories)
      }
    })
  },
  methods: {
    save() {
      request.put('/book/update', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('修改成功')
          this.$router.push("/BookList")
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