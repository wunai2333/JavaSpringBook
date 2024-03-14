<template>
  <div>
    <!--    搜索表单-->
    <div style="margin-bottom: 20px" >
      <el-input placeholder="请输入图书名称" style="width: 20%" v-model="params.name" ></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load"><i class="el-icon-search"></i>查询</el-button>
      <el-button type="warning" style="margin-left: 5px" @click="reset"><i class="el-icon-refresh"></i>重置</el-button>
    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="name" label="图书"></el-table-column>
      <el-table-column style="width: 200px" width="200" type="textarea" prop="description" label="描述"></el-table-column>
      <el-table-column prop="publish" label="发表日期"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="publisher" label="出版社"></el-table-column>
      <el-table-column prop="category" label="分类"></el-table-column>
      <el-table-column prop="bookNo" label="编码"></el-table-column>
      <el-table-column prop="score" label="积分"></el-table-column>
      <el-table-column prop="nums" label="数量"></el-table-column>
      <el-table-column prop="cover" label="封面">
        <template v-slot="scope">
          <el-image :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template v-slot="scope">
          <!--scope.row 就是当前行数据-->
          <el-button type="primary" @click="$router.push('/editBook?id=' + scope.row.id)">编辑</el-button>

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
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'BookList',
  data(){
    return {
      tableData: [],
      total:  0,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: ''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/book/page', {
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
      request.delete("/book/delete/" + id).then(res => {
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