import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
const routes: Array<RouteRecordRaw> = [
 
  {
    path: '/',
    name: 'login',
    component: () => import(/* webpackChunkName: "login" */ '../views/Login/Login.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import(/* webpackChunkName: "register" */ '../views/Login/Register.vue')
  },
  {
    path: '/superadmin',
    name: 'superadmin',
    component: () => import(/* webpackChunkName: "superadmin" */ '../views/SuperAdmin/SuperAdmin.vue')
  },
{
  path: '/PersonnelUpdate3',
  name: 'PersonnelUpdate3',
  component: () => import(/* webpackChunkName: "superadmin" */ '../views/UserManage/PersonnelUpdate3.vue')
},
{
  path: '/FileManage',
  name: 'FileManage',
  component: () => import(/* webpackChunkName: "superadmin" */ '../views/FileManage/index.vue')
},
 

  {
    path: '/UserManageDepartmentAdmin',
    name: 'UserManageDepartmentAdmin',
    component: () => import(/* webpackChunkName: "FileSearch" */ '../views/UserManageDepartmentAdmin/index.vue')
  },
  {
    path: '/PersonnelUpdate2',
    name: 'PersonnelUpdate2',
    component: () => import(/* webpackChunkName: "PersonnelUpdate2" */ '../views/UserManage/PersonnelUpdate2.vue')
  },
  {
    path: '/PersonnelUpdate3',
    name: 'PersonnelUpdate3',
    component: () => import(/* webpackChunkName: "PersonnelUpdate2" */ '../views/UserManage/PersonnelUpdate3.vue')
  },



  {
    path: '/PersonnelUpdateDepartmentAdmin2',
    name: 'PersonnelUpdateDepartmentAdmin2',
    component: () => import(/* webpackChunkName: "PersonnelUpdateDepartmentAdmin2" */ '../views/UserManageDepartmentAdmin/PersonnelUpdate2.vue')
  },
  {
    path: '/PersonnelUpdateDepartmentAdmin3',
    name: 'PersonnelUpdateDepartmentAdmin3',
    component: () => import(/* webpackChunkName: "PersonnelUpdateDepartmentAdmin2" */ '../views/UserManageDepartmentAdmin/PersonnelUpdate3.vue')
  },
 {
    path: '/DepartmentsManage',
    name: 'DepartmentsManage',
    component: () => import(/* webpackChunkName: "DepartmentsManage" */ '../views/DepartmentsManage/index.vue')
  },
  {
    path: '/userManage',
    name: 'userManage',
    component: () =>import('../views/UserManage/index.vue')

  }



 
]

const router = createRouter({
  history: createWebHistory(), // 使用 createWebHistory 替换 createWebHashHistory
  routes
})
//写一个路由导航守卫，如果用户未登录，就跳转到登录页面，确保localStorage.getItem('token')存在是登录状态
router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem('token');

  if (to.name !== 'login' &&to.name!='register'&& !isAuthenticated) {
    next({ name: 'login' });
    ElMessage.error('请先登录');
  } else {
    // 如果用户已经验证身份，或者正在尝试访问登录页面，就正常导航
    next();
  }
});
export default router