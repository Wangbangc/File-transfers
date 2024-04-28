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
                <el-input placeholder="请输入部门、工号、姓名搜索" v-model="search"></el-input>
               </el-col>
               <el-col :span="4">
                <el-button type="primary" @click="searchUser()">搜索</el-button> 
               </el-col>
               <el-col :span="8"></el-col>
               <el-col :span="2">
            
               </el-col>
             </el-row>
             
           </div>
           <el-card>
             <el-table :data="displayedPersons">
               <!-- 表格列 -->
               <el-table-column prop="username" label="工号"></el-table-column>
               <el-table-column prop="name" label="姓名"></el-table-column>
               <el-table-column label="部门">
  <template #default="scope">
    {{ getDepartmentName(scope.row.departmentid) }}
  </template>
</el-table-column>
               <el-table-column prop="role" label="权限" :formatter="formatRole"></el-table-column>
        
               <el-table-column label="操作">
                 <template #default="scope">
                   <el-row>
                     <el-col :span="12">
                       <el-button type="primary" @click="updatePerson(scope.row)">修改部门和权限</el-button>
                     </el-col>
               、
                   </el-row>
                   <!-- 更新人员的弹窗 -->
                   <el-dialog title="修改部门和权限" v-model="updatePersonDialogVisible">
                    <el-form ref="form" :model="form" label-width="120px">
            <el-form-item label="工号">
              <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="权限">
              <el-select v-model="role" placeholder="" required>
                <el-option label="超级管理员" value="SuperAdmin"></el-option>
                <el-option label="部门管理员" value="DepartmentAdmin"></el-option>
                <el-option label="高层领导" value="HighLevelLeader"></el-option>
                <el-option label="中层领导" value="MiddleLevelLeader"></el-option>
                <el-option label="教职工" value="OrdinaryStaff"></el-option>
              </el-select>
            </el-form-item >
         
            <el-form-item label="部门">
  <el-select v-model="  departments" placeholder="请选择"  required>
    <el-option
      v-for="department in departments"
      :key="department.departmentid"
      :label="department.departmentname"
      :value="department.departmentid">
    </el-option>
  </el-select>
</el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updateUser">提交</el-button>
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
               :page-sizes="[10, 20, 50]"
               :page-size="pageSize"
               layout="total, sizes, prev, pager, next, jumper"
               :total=" userInfo.length"
             ></el-pagination>
           </el-card>
         </el-main>
     </div>
       </div>
     </div>
     </template>
   
     <script>
     import Navbar from '@/components/Navbar.vue';
     import Sidebar from '@/components/Sidebar.vue';
     import {selectUserAll,deleteUser,insertUser,updateUser} from '@/api/userApi.ts';
     import {  createDepartment,getAllDepartments, deleteDepartment, updateDepartment } from '@/api/departmentsApi';

  
   
     export default {
       name: 'PersonManagePage',
       components: {
         Navbar,
         Sidebar
       },
       data() {
         return {
          search:'',
           persons: [],
           displayedPersons: [],
           currentPage: 1,
           pageSize: 10,
           addPersonDialogVisible: false,
           updatePersonDialogVisible: false,
           userInfo: [],
           username:'',
           password: '',
           departments:[],
         name: '',
         departmentid: '',
         role: '',
         form: {
           username: '',
           role: '',
           departmentid: ''
         }
       
         };
       },
       methods: {
      

  formatRole(row) {
    const roleMap = {
      'SuperAdmin': '超级管理员',
      'DepartmentAdmin': '部门管理员',
      'HighLevelLeader': '高层领导',
      'MiddleLevelLeader': '中层领导',
      'OrdinaryStaff': '教职工',
    };
    return roleMap[row.role] || row.role;
  },

         updatePerson(user){
             this.form=user;
             this.select();
             this.updatePersonDialogVisible=true;
         },
         getDepartmentName(id) {
          console.log(id)
          console.log(this.departments)
    const department = this.departments.find(department => department.departmentid === id);
    return department ? department.departmentname : '未知部门';
  },
         async updateUser(){
             const response = await updateUser(this.form);
   console.log(response);
         if (response.success) {
          this.selectAll();
             this.updatePersonDialogVisible=false,
           this.$message.success('用户信息更新成功');
         } else {
           this.$message.error('用户信息更新失败: ' + response.message);
         }
         },
         async addUser(){
             const user = {
           username: this.username,
           password: this.password,
           name: this.name,
           departmentid: this.departmentid.toString(),
           role: this.role
         };
         const response = await insertUser(user);
         console.log(response);
         if (response.success) {
          this.selectAll();
             this.addPersonDialogVisible=false;
             this.$alert('录入成功', 'success', {
                 confirmButtonText: 'OK',
                 callback: action => {
                     this.$message({
                         type: 'info',
                         message: `action: ${ action }`
                     });
                 }
             });
        
         }  else {
                 // 弹窗录入失败
                 this.$message.error('录入失败');
             } 
         },
         addPerson() {
           // 打开添加人员的弹窗
           this.addPersonDialogVisible = true;
         },
         handleSizeChange(val) {
      this.pageSize = val;
      this.updateDisplayedPersons();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.updateDisplayedPersons();
    },
    updateDisplayedPersons() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      this.displayedPersons = this.userInfo.slice(start, end);
    },
         async selectAll() {
   const response = await selectUserAll();
   console.log(response);  
 
   if (response.success) {
     this.userInfo = response.data;
   } else {
     alert('获取人员列表失败: ' + response.errorMsg);
   }
         },
         async select() {
  const response = await getAllDepartments();
  console.log(response);  

  if (response.success) {
    console.log(response.data)
    this.departments = response.data;
  } else {
    alert('获取部门列表失败: ' + response.errorMsg);
  }},
  async searchUser() {
    const response = await searchUser(this.search);

    if (response.success) {
      this.displayedPersons= response.data;
    } else {
      alert('搜索人员失败: ' + response.errorMsg);
    }
  },
         // 其他方法
 },
 async created() {
  await this.select();
    await this.selectAll();  // 假设 fetchData 是你获取数据的方法
    this.updateDisplayedPersons();
  },
       mounted() {
         this.selectAll();
         // 获取人员数据
         // this.updateDisplayedPersons();
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
       padding: 20px;
     }
     </style>