<template>
    <div class="super-admin">
      <Navbar />
      <div class="main">
        <Sidebar />
        <div class="content">
          <el-main>
            <div class="search">
              <el-row>
                <el-col :span="10">
                </el-col>
                <el-col :span="4">
                </el-col>
                <el-col :span="8"></el-col>
                <el-col :span="2">
                  <el-button type="primary" @click="addFiles()">添加文件</el-button>
                </el-col>
              </el-row>
              <el-dialog title="添加文件" v-model="add">
                <el-form @submit.native.prevent="addFile">
          <el-form-item label="选择文件:">
          
    <input id="file" @change="handleFileChange" type="file" required>
  
          </el-form-item>
          <el-form-item label="部门">
  <el-select v-model="departmentId" placeholder="请选择">
    <el-option
      v-for="department in departments"
      :key="department.departmentid"
      :label="department.departmentname"
      :value="department.departmentid">
    </el-option>
  </el-select>
</el-form-item>
          <el-form-item label="角色">
            <el-select v-model="permissionType" placeholder="请选择" required>
             
                <el-option label="部门管理员" value="DepartmentAdmin"></el-option>
              
                <el-option label="中层领导" value="MiddleLevelLeader"></el-option>
                
            </el-select>
          </el-form-item>
  
          <el-form-item>
            <el-button type="primary" native-type="submit">上传文件</el-button>
          </el-form-item>
        </el-form>
              </el-dialog>
            </div>
            <el-card>
              <el-table :data="files" style="width: 100%">
                <el-table-column prop="serialNumber" label="文件编号"></el-table-column>
                <el-table-column prop="filename" label="文件名称"></el-table-column>
                <el-table-column prop="publishtime" label="上传时间">
  <template #default="scope">
    {{ new Date(scope.row.publishtime).toLocaleString('zh-CN') }}
  </template>
</el-table-column>


                <el-table-column label="操作">
                  <template #default="scope">
                    <el-row>
                      <el-col :span="12">
                        <el-button type="primary" @click="updateFile(scope.row)">更新</el-button>
                 
                      </el-col>
                      <el-col :span="12">
                        <el-button type="danger" @click="confirmDelete(scope.row)">删除</el-button>
                      </el-col>
                    </el-row>
                    <el-dialog title="更新文件" v-model="dialogVisible">
                      <el-form @submit.native.prevent="updateFiles">
                       
                        <el-form-item label="选择文件:">
          
          <input id="file" @change="handleFileChange" type="file" required>
        
                </el-form-item>                      
                        <el-form-item>
                          <el-button type="primary" native-type="submit">更新文件</el-button>
                        </el-form-item>
                      </el-form>
                    </el-dialog>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[10, 20, 50]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="files.length">
              </el-pagination>
            </el-card>
          </el-main>
        </div>
      </div>
    </div>
  </template>
  
  
  <script>
    import { ElContainer, ElHeader, ElAside, ElMain, ElTable, ElTableColumn, ElButton, ElMessageBox } from 'element-plus';
  import Navbar from '@/components/Navbar.vue';
  import Sidebar from '@/components/SidebarDepartmentAdmin.vue';
  import {updateFile, getFiles ,deleteFile,uploadFile} from '@/api/fileApi';
  import {  createDepartment,getAllDepartments, deleteDepartment, updateDepartment } from '@/api/departmentsApi';

  export default {
    name: 'ManagePage',
    components: {
      Navbar,
      Sidebar
    },
    data() {
      return {
        files: [],
      dialogVisible: false,
      add: false,
 
      fileId: '',
      fileName: '',
      departments:[],
      displayedFiles: [],
      currentPage: 1,
      pageSize: 10,
      fileList: [],
        publisherId: '',
        departmentId: '',
        permissionType: '',
        SerialNumber: '',    
        };
 
    },
    methods: {
      handleFileChange(event) {
      this.file = event.target.files[0];
      console.log(this.file);
    },
        updateFile(e){
          this.SerialNumber=e.serialNumber
            this.dialogVisible=true
        },

        async updateFiles(){
  if (!this.file) {
    alert('请先选择一个文件');
    return;
  }
  const formData = new FormData();
  formData.append('file', this.file);
  formData.append('serialNumber', this.SerialNumber);
  const response = await updateFile(formData);  
  if (response.success) {
    await this.select();  
    this.updateDisplayedFiles(); 
    this.dialogVisible=false;
    alert('文件版本更新成功');
  } else {
    alert('文件版本更新失败: ' + response.errorMsg);
  }     
},
handleCurrentChange(val) {
      this.currentPage = val;
      this.updateDisplayedFiles();  // 当当前页码改变时，更新显示的数据
    },
    updateDisplayedFiles() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = this.currentPage * this.pageSize;
      this.displayedFiles = this.files.slice(start, end);
    },

      async select(){
        const response = await getFiles();
        console.log(response.data)
    if (response.success) {
        console.log(response.data)
      this.files = response.data;
    } else {
      alert('文件查询失败: ' + response.message);
    }
      },
      async selectDepartmentsManage() {
  const response = await getAllDepartments();
  console.log(response);  

  if (response.success) {
    console.log(response.data)
    this.departments = response.data;
  } else {
    alert('获取部门列表失败: ' + response.errorMsg);
  }},
      confirmDelete(file) {
        ElMessageBox.confirm('确定删除该文件吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteFile(file.serialNumber);
        }).catch(() => {});
      },
      addFiles(){
        this.selectDepartmentsManage()
        this.add=true;
      },
      
async addFile(event){
  if (!this.file) {
    alert('请先选择一个文件');
    return;
  }

  const formData = new FormData();
  formData.append('file', this.file);
  formData.append('publisherId', this.publisherId);
  formData.append('departmentId', this.departmentId);
  formData.append('permissionType', this.permissionType);

  const response = await uploadFile(formData);

  if (response.success) {
    await this.select();  
    this.updateDisplayedFiles(); // 更新显示的数据
    this.add=false;
    alert('文件上传成功');
  } else {
    alert('文件上传失败: ' + response.message);
  }
},
      async deleteFile(SerialNumber) {
        const response = await deleteFile(SerialNumber);
    
        if (response.success) {
          this.updateDisplayedFiles(); 
          alert('文件删除成功');
          this.select();
        } else {
          alert('文件删除失败: ' + response.message);
        }
      },
    },
    async created() {
    await this.select();  // 获取数据
    this.updateDisplayedFiles();  // 更新显示的数据
  },
  mounted() {
    this.select();  // 获取数据
    this.updateDisplayedFiles();  // 更新显示的数据
  },
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
  
  }
  </style>