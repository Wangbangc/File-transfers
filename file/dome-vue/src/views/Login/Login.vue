<template>
  <div class="container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
				<span style="display: block; text-align: center; font-size: 24px;">河北水利电力学院办公文件管理系统</span>
<br/>
      <span style="display: block; text-align: center; font-size: 20px;">管理端</span>
      <br/>
      </div>
      <el-form :model="form" @submit.native.prevent="handleLogin">
        <el-form-item label="用户名">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密    码">
          <el-input v-model="form.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="验证码">
          <div class="captcha-container">
  <el-input v-model="form.captcha" placeholder="请输入验证码"></el-input>
  <canvas ref="captchaCanvas" width="150" height="50"></canvas>
</div>
       
</el-form-item>
        <el-form-item>
          <el-button type="primary" native-type="submit">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script lang="ts">
import { ref, onMounted, nextTick } from 'vue';
import { login } from '@/api/userApi';
import router from '@/router';

export default {
  setup() {//form响应式对象
  
    const form = ref({
    username: '',
    password: '',
    captcha: '',
  });

  const captchaCanvas = ref<HTMLCanvasElement | null>(null);
    const captcha = ref('');

    const generateCaptcha = () => {
  if (!captchaCanvas.value) {
    return;
  }

  const ctx = captchaCanvas.value.getContext('2d');
  if (!ctx) {
    return;
  }

  // 设置背景颜色
  ctx.fillStyle = 'lightgray';
  ctx.fillRect(0, 0, captchaCanvas.value.width, captchaCanvas.value.height);

  // 添加小点
  for (let i = 0; i < 150; i++) {
    ctx.fillStyle = `rgb(${Math.random() * 255}, ${Math.random() * 255}, ${Math.random() * 255})`;
    ctx.fillRect(Math.random() * captchaCanvas.value.width, Math.random() * captchaCanvas.value.height, 2, 2);
  }

  captcha.value = Math.random().toString().slice(-6);
  ctx.font = '30px Arial';
  ctx.fillStyle = 'red';

  const chars = captcha.value.split('');
  chars.forEach((char, index) => {
    ctx.save();
    ctx.translate(25 * index + 15, 35);
    ctx.rotate((Math.random() - 0.5) * Math.PI / 6);
    ctx.fillText(char, 0, 0);
    ctx.restore();
  });
};
onMounted(async () => {
  await nextTick();
  generateCaptcha();
});
    const handleLogin = async () => {
      if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,12}$/.test(form.value.password)) {
    alert('密码必须是6-12位的字母和数字的组合');
    return;
  }
  if (form.value.captcha !== captcha.value) {
  alert('验证码错误');
  return;
}
      try {
        const data = await login(form.value.username, form.value.password);//首先调用后端接口的login方法进行登录验证
        if (data.success) {
          console.log(data.data[1]);
          if(data.data[1] === 'SuperAdmin'){
            router.push('/superAdmin'); //超级管理员
          }else if(data.data[1] === 'DepartmentAdmin'){
            router.push('/FileManage');}//部门管理员
                  
        } else {
          router.push('/');
        }
      } catch (error) {
        // 网络错误，可以在这里显示错误信息
      }
    };

    return {
      form,
      handleLogin,
      captchaCanvas,
      captcha,
      generateCaptcha,
    };
  }
};
</script>

<style scoped>
.captcha-container {
  display: flex;
}

.captcha-container .el-input {
  flex: 1;
  margin-right: 10px;
}

.captcha-container canvas {
  flex: 0 0 auto;
}
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.box-card {
  width: 450px;
}
</style>