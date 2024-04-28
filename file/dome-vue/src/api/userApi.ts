
import Axios from './api'
//登入
export const login = async (username: string, password: string) => {

  const response = await Axios({
    method: 'post',
    url: '/user/login',
    data: {
      username,
      password
    }
  })
console.log(response.data.data[0]);
  localStorage.setItem('token', response.data.data[0]);

  return response.data; 
};
// 超级管理员注册
export async function register(username: string, password: string) {
  const response = await Axios.post('/user/register', {
    username,
    password
  });
  console.log(response.data);
  return response.data;
}
export const selectUserAll = async ()=>{
  const response =await Axios({
    method:'get',
    url:'/user/getAllUsers',
    
  })
  return response.data;
}
//获取个人信息
export const selectUser =async()=>{
  const response =await Axios({
    method:'get',
    url:'/user/user',
    
  })
  return response.data;
}
// 人员录入
interface User {
  username: string;
  password: string;
  name: string;
  departmentid: string;
  role: string;
}
export const insertUser = async (user:User) => {

  const response = await Axios({
    method: 'post',
    url: '/user/insertUser',
    data: user
  })
  return response.data;
};
// 删除用户
export const deleteUser = async (username: string) => {
  const response = await Axios({
    method: 'delete',
    url: `/user/deleteUserByUsername?username=${username}`,
  })
  return response.data;
};
// 更新用户权限
export const updateUserRole = async (username: string, role: string) => {
  
  const response = await Axios({
    method: 'post',
    url: '/user/updateUserRole',
    data: {
      username,
      role
    }
  })
  return response.data;
};
// 查询用户
export const getUserByUsername = async (username:string) => {
  const response = await Axios({
    method: 'get',
    url: `/user/getUserByUsername`,
    params: {
      username: username
    }
  })
  return response.data;
};
// 更新用户信息
export const updateUser = async (user:User) => {
  const response = await Axios({
    method: 'put',
    url: `/user/update`,
    data: user
  })
  return response.data;
};
// 更新自己信息
export const updateSelf = async (user:User) => {
  const response = await Axios({
    method: 'put',
    url: `/user/update2`,
    data: user
  })
  return response.data;
};
// 模糊查询人员
export const searchUser = async (keyword: string) => {
  const response = await Axios({
    method: 'get',
    url: `/user/getUsersByKeyword?keyword=${keyword}`,
   
  })
  return response.data;
};
