import {loading_open,loading_close} from "../util/util.js";
var Main = {
        data() {
            //表单输入内容验证
            const validateRules = (rule, value, callback) => {
                let reg = /^[+-]?(0|([1-9]\d*))(\.\d+)?$/g;
                if (value === '') {
                    callback(new Error('请输入内容'));
                } else if (!reg.test(value)) {
                    callback(new Error('请输入数字'));
                } else {
                    callback();
                }
                callback();
            }
            //基础非空验证规则
            return {
                //当前阶段
                currentStageId:2,
                //当前阶段描述（第i阶段）
                currentStageDesc:'',
                //提交警告
                submitAlert: false,
                //头像
                circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
                sizeList: ["large", "medium", "small"],
                //导航
                activeIndex: '7',
                //tabs
                activeName: 'first',
                //滑块
                slider1: 50,
                slider2: 50,
                marks1: {
                    0: '0',
                    100: '100',
                    50: {
                        style: {
                            color: '#1989FA'
                        },
                        label: '50'
                    }
                },
                marks2: {
                    0: '0',
                    100: '100',
                    50: {
                        style: {
                            color: '#1989FA'
                        },
                        label: '50'
                    }
                },
                //进度选择器
                optionsjindu: [],
                //当前阶段所有阈值
                currentLimit: {},
                //财务管理业务值
                form:{
                    state:'0',
                    shareBonus:'',
                    synergia:'',
                    currentProjectId:'1',
                    currentTeamId:'1',
                    currentStageId:'1'
                },
                //财务管理提示语
                limitMsg:{
                    shareBonus_msg:'',
                    synergia_msg:'',
                },
                //资产负债表结果接收对象
                // 表单校验规则
                rules: {
                    shareBonus: [{ required: true, validator: validateRules,trigger: 'blur'}],
                    synergia: [{ required: true, validator: validateRules,trigger: 'blur'}]
                },
            }
        },
        methods: {
            //查询已保存或填写的数据
            queryDecisionData(currentProjectId,currentTeamId,currentStageId){
                let that = this
                let params= new URLSearchParams()
                params.append('currentProjectId', currentProjectId)
                params.append('currentTeamId', currentTeamId)
                params.append('currentStageId', currentStageId)
                axios({
                    method:'get',
                    url:'decFinancialManagement/queryFinancial',//相对路径即可，不用加/service
                    params:params,
                    responseType:'json',
                }).then(function(resp){
                    if(resp.data.data!=null){
                        that.form = resp.data.data
                    }
                })
            },
            //tab
            formatter(row, column) {
                return row.address;
            },
            handleClick(tab, event) {
                console.log(tab, event);
            },
            //滑块
            formatTooltip(val) {
                return val / 100;
            },
            open() {
                this.$confirm('提交后您将无法修改当前的决策数据，是否确定?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '再想想',
                    type: 'warning',
                    center: true
                }).then(() => {
                    this.submitForm()
                }).catch(() => {
                    // this.$message({
                    //     type: 'info',
                    //     message: '已取消'
                    // });
                });
            },
            //提交表单前
            submitFormBefore (formName) {
                this.$refs[formName].validate((valid) => {
                    if (!valid) {
                        this.submitAlert = true
                        return false
                    }else{
                        this.open();
                    }
                })
            },
            // 保存数据
            saveForm(){
                this.postFormData()
                //标记动作为提交
                this.form.state = '0'
            },
            submitForm(){
                //关闭弹出框
                this.submitConfirm = false
                //标记动作为提交
                this.form.state = '1'
                this.postFormData()
            },
            postFormData(){
                loading_open(this)//开启加载动画
                let that = this
                console.log(this.form)
                let form = this.form
                axios({
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    method:'post',
                    url:'decFinancialManagement/add',//相对路径即可，不用加/service
                    data:form,
                    responseType:'json',
                }).then(function(resp){
                    const code = resp.data.code
                    loading_close()//关闭动画
                    if(resp.status==200) {
                        let message = '财务管理决策数据保存成功'
                        if(that.form.state == '1'){
                            message = '财务管理决策数据提交成功'
                        }
                        let type = "success"
                        that.$message({
                            showClose: true,
                            message: message,
                            type: type
                        });
                        that.queryDecisionData(that.form.currentProjectId,that.form.currentTeamId,that.currentStageId)
                    }
                })
            },
            //提交表单前
            preOperation () {
                loading_open(this)//开启加载动画
                let that = this
                console.log(this.form)
                let form = this.form
                axios({
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    method:'post',
                    url:'decFinancialManagement/add',//相对路径即可，不用加/service
                    data:form,
                    responseType:'json',
                }).then(function(resp){
                    const code = resp.data.code
                    loading_close()//关闭动画
                    if(resp.status==200) {
                        window.open("yjs?currentProjectId=" + that.form.currentProjectId+"&currentTeamId="+that.form.currentTeamId+"&currentStageId="+that.form.currentStageId)
                        that.queryDecisionData(that.form.currentProjectId,that.form.currentTeamId,that.currentStageId)
                    }
                })
                //标记动作为提交
                this.form.state = '0'
            },
        },
    // 页面加载时请求数据
    mounted:function () {
        //动态获取当前正在进行阶段
        this.currentStageId = this.$refs['currentStageId'].attrs.value
        //设置默认选中的option
        this.currentStageDesc = '第' + (this.currentStageId - 1) + '阶段'
        //动态生成可选阶段列表
        this.optionsjindu = []
        for (var i = 1; i < this.currentStageId; i++) {
            let temp = {value: '', label: ''}
            temp.value = i + 1
            temp.label = "第" + i + "阶段"
            this.optionsjindu.push(temp)
        }
        let that = this;
        //获取已保存数据
        let teamId = this.$refs['teamId'].attrs.value
        let projectId = this.$refs['projectId'].attrs.value
        this.queryDecisionData(projectId, teamId, this.currentStageId)
        //设置formdata的数据
        this.form.currentStageId = this.currentStageId
        this.form.currentProjectId = projectId
        this.form.currentTeamId = teamId
    },
    }
;const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");