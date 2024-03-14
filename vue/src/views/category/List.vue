<template>
  <div>
    <!--    搜索表单-->
    <div style="margin-bottom: 20px" >
      <el-input placeholder="请输入分类名称" style="width: 20%" v-model="params.name" ></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load"><i class="el-icon-search"></i>查询</el-button>
      <el-button type="warning" style="margin-left: 5px" @click="reset"><i class="el-icon-refresh"></i>重置</el-button>
    </div>

    <el-table :data="tableData" stripe row-key="id" default-expand-all>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>

      <el-table-column label="操作">
        <template v-slot="scope">
          <!--scope.row 就是当前行数据-->
          <el-button type="primary" v-if="!scope.row.pid" @click="handleAdd(scope.row)">添加二级分类</el-button>
          <el-button type="primary" @click="$router.push('/editCategory?id=' + scope.row.id)">编辑</el-button>
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

    <el-dialog type="success" title="添加二级分类" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" label-width="100px">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类备注" prop="ramark">
          <el-input v-model="form.ramark" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'CategoryList',
  data(){
    return {
      tableData: [],
      total:  0,
      dialogFormVisible: false,
      form: {},
      pid: null,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/category/page', {
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
        name: ' '
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      //点击分页按钮触发
      this.params.pageNum = pageNum
      this.load()
    },
    del(id) {
      request.delete("/category/delete/" + id).then(res => {
        if (res.code === '200') {
        this.$notify.success("删除成功")
        this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    handleAdd(row) {
      // 将当前行的id作为二级id
      this.pid = row.id
      this.dialogFormVisible = true
    },
    save() {
      //给二级分类赋值pid
      this.form.pid = this.pid
      request.post('/category/save', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('新增二级分类成功')
          this.form = {}
          this.dialogFormVisible = false
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