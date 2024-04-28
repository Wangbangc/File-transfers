<template>
    <div class="manage-page">
      <Navbar />
      <div class="main">
        <SidebarDepartmentAdmin />
        <div class="content">
        <div>请根据工号修改和人员信息</div>
          <el-form ref="form" :model="form" label-width="120px">
            <el-form-item label="工号">
              <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
          
            <el-form-item>
              <el-button type="primary" @click="submitForm">提交</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import Navbar from '@/components/Navbar.vue';
  import SidebarDepartmentAdmin from '@/components/SidebarDepartmentAdmin.vue';
  import { updateUser } from '@/api/userApi';
  
  export default {
    name: 'ManagePage',
    components: {
      Navbar,
      SidebarDepartmentAdmin
    },
    data() {
      return {
        form: {
          username: '',
          password: '',
          name: '',
          departmentid: ''
        }
      };
    },
    methods: {
      async submitForm() {
        const response = await updateUser(this.form);
  console.log(response);
        if (response.success) {
          this.$message.success('用户信息更新成功');
        } else {
          this.$message.error('用户信息更新失败: ' + response.message);
        }
      }
    }
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