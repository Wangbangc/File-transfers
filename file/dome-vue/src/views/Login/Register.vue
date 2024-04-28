<template>
    <div class="container">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>注册</span>
        </div>
        <el-form :model="form" @submit.native.prevent="handleRegister">
          <el-form-item label="用户名">
            <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" type="password" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" native-type="submit">注册</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script lang="ts">
  import { ref } from 'vue';
  import { register } from '@/api/userApi';
  import router from '@/router';
  
  export default {
    setup() {
      const form = ref({
        username: '',
        password: ''
      });
  
      const handleRegister = async () => {
        try {
          const data = await register(form.value.username, form.value.password);
          console.log(data);
          if (data.success) {
            router.push('/superAdmin');
          } else {
        //弹出注册失败提示	
        alert('注册失败，请重试！');
          }
        } catch (error) {
          // 网络错误，可以在这里显示错误信息
        }
      };
  
      return {
        form,
        handleRegister
      };
    }
  };
  </script>
  
  <style scoped>
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f0f2f5;
  }
  
  .box-card {
    width: 400px;
  }
  </style>