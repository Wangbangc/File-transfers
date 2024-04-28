<template>

    <div class="super-admin">
      <Navbar />
      <div class="main">
        <Sidebar />
        <div class="content">
      <el-main>
        <div class="search">
                  <el-row>
    <el-col :span="10">
       <el-input
              placeholder="搜索部门"
              v-model="search"
            ></el-input>
          </el-col>
    <el-col :span="4">
      <el-button type="primary" @click="searchDepartment">搜索</el-button>
    </el-col>
    <el-col :span="8"></el-col>
    <el-col :span="2">  <el-button type="primary" @click="addDepartments()">添加部门</el-button></el-col>
 
<el-dialog title="添加部门" v-model="addDepartment">
 
    <el-form @submit.native.prevent="AddDepartment">
            <el-form-item label="部门名称:">
              <el-input id="departmentName" v-model="departmentName" type="text" required></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" native-type="submit">创建部门</el-button>
            </el-form-item>
          </el-form>

</el-dialog>
  </el-row>    

            </div>
        <el-card >
   
            <el-table :data="displayedDepartments">
          <el-table-column prop="departmentid" label="部门ID"></el-table-column>
          <el-table-column prop="departmentname" label="部门名称"></el-table-column>
          <el-table-column label="操作">
            <template #default="scope" >
                <el-row>
                    <el-col :span="12">
                        <el-button type="primary" @click="updateDepartment(scope.row)">更新</el-button>
                    </el-col>
                    <el-col :span="12">
                        <el-button type="danger" @click="deleteDepartment(scope.row.departmentname)">删除</el-button>
                    </el-col>
             </el-row>     
             <el-dialog title="更新部门"     v-model="dialogVisible" >
      <el-form @submit.native.prevent="submitForm">

          <el-form-item label="部门ID:">
            <el-input id="departmentId" :disabled="true" v-model="departmentId" type="text" required></el-input>
          </el-form-item>
          <el-form-item label="部门名称:">
            <el-input id="departmentName" v-model="departmentName" type="text" required></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" native-type="submit">更新部门</el-button>
          </el-form-item>
        
      </el-form>
    </el-dialog>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
  @size-change="handleSizeChange"
  @current-change="handleCurrentChange"
  :current-page="currentPage"
  :page-sizes="[ 10, 20, 50]"
  :page-size="pageSize"
  layout="total, sizes, prev, pager, next, jumper"
  :total="departments.length">
</el-pagination>
  </el-card>
       
      </el-main>
       </div>
    </div>
    </div>
</template>
  
  <script>
  import Navbar from '@/components/Navbar.vue';
  import Sidebar from '@/components/Sidebar.vue';
  import { searchDepartment, createDepartment,getAllDepartments, deleteDepartment, updateDepartment } from '@/api/departmentsApi';

  
  export default {
    name: 'ManagePage',
    components: {
      Navbar,
      Sidebar
    },
    data() {
      return {
        search:'',
        departments: [],
        dialogVisible: false,
        addDepartment:false,
        departmentId: '',
    departmentName: '',
  
    departments: [],
    displayedDepartments: [],
    currentPage: 1,
    pageSize: 10,
      };
    },
    methods: {
      addDepartments(){
        console.log("sss")
        this.addDepartment=true;
      },
      handleSizeChange(val) {
    this.pageSize = val;
    this.updateDisplayedDepartments();
  },
  handleCurrentChange(val) {
    this.currentPage = val;
    this.updateDisplayedDepartments();
  },
  updateDisplayedDepartments() {
    const start = (this.currentPage - 1) * this.pageSize;
    const end = start + this.pageSize;
    this.displayedDepartments = this.departments.slice(start, end);
  },
      async deleteDepartment(departmentName) {
        console.log(departmentName);
        const response = await deleteDepartment(departmentName);
        if (response.success) {
          await this.select(); 
          this.updateDisplayedDepartments();
          alert('部门删除成功');
        } else {
          alert('部门删除失败: ' +response.errorMsg);
        }
      },
      async submitForm() {
      const  Department= {
    departmentid: this.departmentId,
    departmentname: this.departmentName,
  }

  const response = await updateDepartment(Department);

  if (response.success) {
    alert('部门名称更新成功');
    await this.select(); 
    this.updateDisplayedDepartments();
    this.dialogVisible = false;
  } else {
    alert('部门名称更新失败: ' + response.errorMsg);
  }
},
   updateDepartment(department) {
  this.departmentId = department.departmentid;
  this.departmentName = department.departmentname;
  this.dialogVisible = true;
},
      async select() {
  const response = await getAllDepartments();
  console.log(response);  

  if (response.success) {
    this.departments = response.data;
  } else {
    alert('获取部门列表失败: ' + response.errorMsg);
  }
},
    async AddDepartment(){
  console.log(this.departmentName)
  const Department = {
        departmentname: this.departmentName,
        };
        console.log(Department);
      const response = await createDepartment( Department );

      if (response.success) {
        this.addDepartment=false;
        await this.select(); 
        this.updateDisplayedDepartments();
        alert('部门创建成功');
      } else {
        alert('部门创建失败: ' + response.errorMsg);
      }
    },
    async searchDepartment() {
    const response = await searchDepartment(this.search);

    if (response.success) {

      this.displayedDepartments = response.data;
    } else {
      alert('搜索部门失败: ' + response.errorMsg);
    }
  },
} ,
async created() {
    await this.select();  // 假设 fetchData 是你获取数据的方法
    this. updateDisplayedDepartments();
  
  },
    mounted() {
      this.select();
      this.updateDisplayedDepartments();

    },
  };
  </script>
  
  <style scoped>
  .manage-page {
    display: flex;
    flex-direction: column;
    height: 100vh;
  }
  
  .main {
    display: flex;
    flex: 1;
    background-color: #f0f2f5;
  }
  
  .content {
    flex: 1;

  }
  </style>