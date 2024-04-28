import Axios from './api'

// 文件查询
export const getFiles = async () => {
    const response = await Axios({
      method: 'get',
      url: `/files/getFiles`,
    })
    return response.data;
  };
  // 文件删除
export const deleteFile = async (SerialNumber: string) => {
    const response = await Axios({
      method: 'delete',
      url: `/files/delete?SerialNumber=${SerialNumber}`,
    })
    return response.data;
  };
  // 文件上传
export const uploadFile = async (formData:FormData) => {

  const response = await Axios ({
    method: 'post',
    url: '/files/upload',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
  return response.data;
};
  // 文件更新
  export const updateFile =async (formData:FormData)=>{
    console.log(formData)
    const response = await Axios ({
      method: 'Put',
      url: '/files/update',
      data: formData,
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    return response.data;
  }