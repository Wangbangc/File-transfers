<template>
  <div class="manage-page">
    <Navbar />
    <div class="main">
      <Sidebar />
      <div class="content">
        <el-card style="max-width: 80% ;height: 70%;" >
           <el-form ref="form" :model="form" label-width="120px">
            <el-form-item label="姓名">
              <el-input v-model="form.name" disabled clearable></el-input>
            </el-form-item>
            <el-form-item label="工号">
              <el-input v-model="form.username" disabled clearable></el-input>
            </el-form-item>
            <el-form-item label="部门">
              <el-input v-model="form.name" disabled clearable></el-input>
            </el-form-item>
            <el-form-item label="角色">
              <el-input v-model="form.role" disabled clearable></el-input>
            </el-form-item>
          </el-form>
       <el-button type="primary" @click="update()" style="margin-left: 400px ;">修改密码</el-button>
        <el-dialog title="修改密码" v-model="updatePersonDialogVisible">
                     <el-form ref="form" :model="form" label-width="120px">
        
            <el-form-item label="当前密码">
              <el-input v-model="password"></el-input>
            </el-form-item>
            <el-form-item label="新密码">
             <el-input v-model="password1"></el-input>
           </el-form-item>
        <el-form-item label="确认新密码">
         <el-input v-model="password2"></el-input>
       </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updateUser">提交</el-button>
            </el-form-item>
          </el-form>
                    </el-dialog>
     </el-card>

      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue';
import Sidebar from '@/components/SidebarDepartmentAdmin.vue';
import { updateSelf,selectUser } from '@/api/userApi';

export default {
  name: 'ManagePage',
  components: {
    Navbar,
    Sidebar
  },
  data() {
    return {
        updatePersonDialogVisible:false,
      form: {
      
        username: '',
        password: '',
        name: '',
        departmentid: '',
        password:'',
      
      },
      password:'',
          password1:'',
        password2:''
    };
  },
  methods: {
    update(){
      console.log("ssss")
      this.updatePersonDialogVisible=true;
      console.log(this.updatePersonDialogVisible)
    },
    async updateUser(){
      if(this.password!=this.form.password){
         this.$message.error('密码错误');
      }else if(this.password1==this.form.password){
        this.$message.error('新密码不能与原密码一致');
      }else if(this.password1!=this.password2){
          this.$message.error('新密码不一致');
      }
      else {
        this.form.password=this.password1
        const response = await updateSelf(this.form);
        console.log(response);
              if (response.success) {
                this.$message.success('信息更新成功');
              } else {
                this.$message.error('信息更新失败: ' + response.message);
              }
      }
        
    },
    async selectUser(){   
      const response = await  selectUser();
      if (response.success) {

        this.form=response.data

      } else {
        alert('信息查询失败: ' +response.errorMsg);
      }
},
  
  },
  mounted(){
    this.selectUser()
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