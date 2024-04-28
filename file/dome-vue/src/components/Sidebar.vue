<template>
  <div class="asi" :style="{
      position: 'relative',
      minWidth: isCollapse ? '240px' : '240px',
      height: 'calc(100vh )',
    }" >
    <el-row class="bom">
      <el-col :span="12"> <div>
        <el-button class="logout" @click="logout"  >退出登入</el-button>
      </div></el-col>
      <el-col :span="12">  <div>

      </div></el-col>
    </el-row>
        <el-aside width="240px" class="aside">
          <el-menu
          background-color="#e6e9ee"
              default-active="2"
              class="el-menu-vertical-demo"
            >
              <el-menu-item-group>           
        <el-menu-item index="/DepartmentsManage" @click="handleClick">部门信息管理</el-menu-item>
              </el-menu-item-group>    
              <el-menu-item-group>
        <el-menu-item index="/userManage" @click="handleClick">用户管理</el-menu-item>
        <el-menu-item index="/PersonnelUpdate2" @click="handleClick">人员调配及权限更改</el-menu-item>
              </el-menu-item-group>
         </el-menu>
        </el-aside>

  </div>
</template>
<script  setup>
import {
  Edit,
  View,
 SwitchButton,
  Document,
  TakeawayBox,
  UserFilled,
  Van ,
  Notebook 
} from '@element-plus/icons-vue'
</script>
<script>
export default {
  data(){
    return{
      label:false,
      isCollapse:false,
    }
  },
  methods:{
    handleClick(menuItem) {
      this.$router.push(menuItem.index);
    },
    Coll(){
 

    },
    logout(){
      this.$router.push('/')
    },
bottledManage(){
  this.$router.push('/bottledManage')
},
    userInfo(){
      // 跳转用户信息管理
      this.$router.push('/userManage')
    },
    deliveryManage(){
      // 跳转配送员信息管理
      this.$router.push('/deliveryManage')
    },
    orderManage(){
      this.$router.push('/orderManage')
    },
    DeliveryOrderManage(){
      this.$router.push('/DeliveryOrderManage')
    }
  }
}
</script>

<style>
.asi{
  height: calc(100vh - 100px);
  background-color: #e6e9ee;
}
.bom{

  justify-content: flex-end !important;
  padding: 10px!important;
}
.logout{
  background: transparent !important;
  border: none !important;
}
.aside{
  height: calc(100vh - 64px);
  background-color: #e6e9ee;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 240px;
  min-height: 400px;
  border-right: none;

}
.radio{
  background-color: #e6e9ee;
}
.radiobutton{

  background-color:  rgba(0, 0, 0, 0) ;/* 非选中时的背景色 */
  border: 1px solid  rgba(0, 0, 0, 0); /* 非选中时的边框样式 */
  cursor: pointer;
}
.radiobutton:active{
  background-color: rgba(0, 0, 0, 0); /* 非选中时的背景色 */
  border: 1px solid  rgba(0, 0, 0, 0); /* 非选中时的边框样式 */
}
</style>