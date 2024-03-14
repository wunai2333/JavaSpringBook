<template>
  <div>
    <!--    搜索表单-->
    <div style="margin-bottom: 20px" >
      <el-input placeholder="请输入内容" style="width: 20%" v-model="params.name" ></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load"><i class="el-icon-search"></i>查询</el-button>
      <el-button type="warning" style="margin-left: 5px" @click="reset"><i class="el-icon-refresh"></i>重置</el-button>
    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="name" label="名字"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="account" label="账户积分"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>

      <el-table-column label="操作" witch="240">
        <template v-slot="scope">
          <!--scope.row 就是当前行数据-->
          <el-button type="warning" @click="handleAccount(scope.row)">充值</el-button>
          <el-button type="primary" @click="$router.push('/editUser?id=' + scope.row.id)">编辑</el-button>
          <el-popconfirm style="margin-left: 5px" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference">删除</el-button></el-popconfirm>
        </template>
      </el-table-column>

    </el-table>
    <!--    分页-->
    <div style="margin-top: 20px;">
      <el-pagination
          background
          :current-page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog type="success" title="充值" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" label-width="100px">
        <el-form-item label="当前积分" prop="account">
          <el-input disabled v-model="form.account" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="积分" prop="score">
          <el-input v-model="form.score" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAccount">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'User',
  data(){
    return {
      tableData: [],
      total:  0,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: ''
      },
      dialogFormVisible: false,
      form: {},
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      /*
            fetch('/user/list').then(res => res.json()).then(res => {
              console.log(res)
              this.tableData = res
            })
      */
      request.get('/user/page', {
        params: this.params
      }).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 10,
        name: ''
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      //点击分页按钮触发
      this.params.pageNum = pageNum
      this.load()
    },
    del(id) {
      request.delete("/user/delete/" + id).then(res => {
        if (res.code === '200') {
        this.$notify.success("删除成功")
        this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    handleAccount(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    addAccount() {
      request.post('/user/account', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success("充值成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
  }
}
</script>

<style scoped>

</style>