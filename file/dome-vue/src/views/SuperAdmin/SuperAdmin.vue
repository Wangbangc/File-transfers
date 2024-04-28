<template>
    <div class="super-admin">
      <Navbar />
      <div class="main">
        <Sidebar />
        <div class="content">
          <h1>欢迎进入超级管理员页面!</h1>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import Navbar from '@/components/Navbar.vue';
  import Sidebar from '@/components/Sidebar.vue';
  
  export default {
    name: 'SuperAdmin',
    components: {
      Navbar,
      Sidebar
    },
    data() {
      return {
        message: 'Welcome to the Super Admin page!'
      }
    }
  }
  </script>
  
  <style scoped>
  .super-admin {
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