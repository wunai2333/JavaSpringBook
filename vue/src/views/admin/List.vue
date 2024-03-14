<template>
  <div>
    <!--    搜索表单-->
    <div style="margin-bottom: 20px" >
      <el-input placeholder="请输入用户名" style="width: 20%" v-model="params.username" ></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load"><i class="el-icon-search"></i>查询</el-button>
      <el-button type="warning" style="margin-left: 5px" @click="reset"><i class="el-icon-refresh"></i>重置</el-button>
    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>

      <el-table-column label="操作" width="230">
        <template v-slot="scope">
          <!--scope.row 就是当前行数据-->
          <el-button type="primary" @click="$router.push('/editAdmin?id=' + scope.row.id)">编辑</el-button>
          <el-popconfirm style="margin-left: 5px" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference">删除</el-button></el-popconfirm>
          <el-button style="margin-left: 5px" type="warning" @click="handleChangePass">修改密码</el-button>
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
    <!-- 弹窗 -->
    <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" label-width="100px">
        <el-form-item label="新密码">
          <el-input v-model="form.newPass" autocomplete="off" show-password></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="savePass">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'AdminList',
  data(){
    return {
      tableData: [],
      total:  0,
      form: {

      },
      dialogFormVisible: false,
      params: {
        pageNum: 1,
        pageSize: 10,
        username: ''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    handleChangePass(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    savePass() {
      request.put('/admin/password', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success("修改成功")
          if (this.form.id === this.admin.id) {
            Cookies.remove('admin')
            this.$router.push('/login')
          } else {
            this.load()
            this.dialogFormVisible = false
          }
        } else {
          this.$notify.error("修改失败")
        }
      })
    },
    load() {
      request.get('/admin/page', {
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
        username: ''
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      //点击分页按钮触发
      this.params.pageNum = pageNum
      this.load()
    },
    del(id) {
      request.delete("/admin/delete/" + id).then(res => {
        if (res.code === '200') {
        this.$notify.success("删除成功")
        this.load()
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