import Axios from './api'
interface Department {
  departmentname: string;
  departmentid: number;
}
// 创建部门
export const createDepartment = async (department:Department) => {
console.log(department);
  const response = await Axios({
    method: 'post',
    url: '/departments/create',
    data: department
  })
  return response.data; 
};
// 更新部门名称
export const updateDepartment = async (department:Department) => {
  const response = await Axios({
    method: 'post',
    url: '/departments/update',
    data: department
  })
  return response.data;
};
// 删除部门
export const deleteDepartment = async (departmentName: string) => {
  const response = await Axios({
    method: 'delete',
    url: `/departments/delete?departmentName=${departmentName}`,
  })
  return response.data;
};
//部门查询
export const getAllDepartments=async()=> {
  const response = await Axios({
    method: 'get',
    url: '/departments/getAll',
  })
  console.log(response.data);
  return response.data;
}
// 模糊查询部门
export const searchDepartment = async (departmentName: string) => {
  const response = await Axios({
    method: 'get',
    url: `/departments/getDepartmentsByName?departmentName=${departmentName}`,
   
  })
  console.log(response.data);
  return response.data;
};