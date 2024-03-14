<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px">新增借书记录</div>
    <el-form :inline="true" :model="form" label-width="100px">
      <el-form-item  label="图书编号" prop="description">
        <el-select v-model="form.bookNo" filterable placeholder="请选择" @change="selBook">
          <el-option
              v-for="item in books"
              :key="item.id"
              :label="item.bookNo"
              :value="item.bookNo">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="图书名称" prop="bookName">
        <el-input v-model="form.bookName" disabled></el-input>
      </el-form-item>
      <el-form-item label="使用积分" prop="score">
        <el-input v-model="form.score" disabled></el-input>
      </el-form-item>
      <el-form-item label="数量" prop="nums">
        <el-input v-model="form.nums" disabled></el-input>
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-select v-model="form.userName" filterable placeholder="请选择" @change="selUser">
          <el-option
              v-for="item in users"
              :key="item.id"
              :label="'(' + item.username +  ')' + item.name "
              :value="item.username">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="用户名称" prop="username">
        <el-input v-model="form.username" disabled></el-input>
      </el-form-item>
      <el-form-item label="联系方法" prop="userPhone">
        <el-input v-model="form.userPhone" disabled></el-input>
      </el-form-item>
      <el-form-item label="账户积分" prop="userPhone">
        <el-input v-model="form.account" disabled></el-input>
      </el-form-item>
      <el-form-item label="借出天数" prop="days">
        <el-input-number v-model="form.days" :min="1" :max="5" label="借出的天数"></el-input-number>
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
  name: "AddBorrow",
  data() {
    return {
      form: { days: 3 },
      books: [],
      users: [],
      categories: [],
    }
  },
  created() {
    request.get('/book/list').then(res => {
      this.books = res.data
    })
    request.get('/user/list').then(res => {
      this.users = res.data
    })
  },
  methods: {
    save() {
      request.post('/borrow/save', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('新增成功')

          window.location.reload();
          this.form = {}
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    selBook() {
      const book =this.books.find(v => v.bookNo === this.form.bookNo)
      this.form.bookName = book.name
      this.form.score = book.score
      this.form.nums = book.nums
    },
    selUser() {
      const user =this.users.find(v => v.userName === this.form.username)
      this.form.username = user.name
      this.form.userPhone = user.phone
      this.form.account = user.account
      this.form.userId = user.id
    },
  }
}
</script>

<style scoped>

</style>