
import Axios from 'axios'

const initAxios = Axios.create({
    timeout: 1800000 ,//数据响应过期时间
    baseURL: 'http://localhost:9000',
//    headers:{
//     Authorization: "Bearer localStorage.getItem('token')"
//    }
})

//请求拦截器
initAxios.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');

        if (token) {
          config.headers.Authorization = `${token}`;
        }
        // 如果token不存在，就不设置Authorization头，然后正常发送请求
        return config;
    },
    (error) => {
        //对请求错误做点什么
        return error
    }
)
//响应拦截器

initAxios.interceptors.response.use(
    (response) => {
        if (response.status === 401) {
            // 在这里添加处理401状态码的逻辑
          }
        return response;
    },
    (error) => {
        console.log(error, 'error');
        if (error.response.status === 401) {
            console.log(error.response, 'error.response');
            // 在这里添加处理401状态码的逻辑
        }
        return Promise.reject(error);
    }
);

export default initAxios
