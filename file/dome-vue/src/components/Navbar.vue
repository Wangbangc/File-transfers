<template>
  <el-header class="header">
   
    <el-row class="nav-row">
      <el-col class="ss" :span="2" >
        <el-image style="width: 40px; height: 40px" :src="url"  />
          </el-col>
    
      <el-col  :span="14" class="ss">

    <el-row >
      <el-col :span="4" class="title">
        水利电力学院办公文具管理系统
      </el-col>
      <el-col :span="20" class="title">
   
      </el-col>
    </el-row>
  </el-col>
  <el-col class="ss" :span="4" >
  
   
        </el-col>
        <el-col class="ss" :span="4" >
          <el-button
      key="plain"
      type=""
      link
      @click="UserInfo()"
      >用户中心</el-button
    >
            <el-text class="mx-1" size="large">{{name}}</el-text>
   
        </el-col>
      </el-row>
  </el-header>
</template>

<script  >
import {selectUser}from '@/api/userApi.ts';
import router from '@/router';
export default {
  name: 'Navbar',
  data() {
    return {
      name: '',
      url: require('@/assets/xh.jpg'),
      s:'',
    };
  },
  mounted() {
    this.selectUser();
  },
  methods: {
    async selectUser() {
      const response = await selectUser();
      if (response.success) {
        console.log(response.data.name);
        this.name = response.data.name;
        this.s=response.data.role;
        console.log(this.name);
      } else {
        alert('信息查询失败: ' + response.errorMsg);
      }
    },
    UserInfo() {
      if (this.s == 'SuperAdmin') {
        router.push('/PersonnelUpdate3');
      } else {
    router.push('/PersonnelUpdateDepartmentAdmin3'); 
      }
   
  }
  },

};
</script>

<style scoped>
.ss{
  margin-top: 15px;
}
.nav-row{
  margin-top: 0px;
}
.title {
  font-weight: bold; /* 设置为黑体 */
  font-size: 20px; /* 设置为三号字体，你可能需要根据你的需求调整这个值 */
  color: black; /* 设置颜色为黑色 */
  white-space: nowrap; 

}
.mx-1{
  color: black; /* 设置颜色为黑色 */
}
.header {
  width: 100%vh;
  background-color: #e6e5e5;
  color: #fff;
  border-bottom: 1px solid #d3d3d3;  /* 添加这一行 */
}

.logo {
  width: 50rpx;
  height: 50rpx;
}
</style>