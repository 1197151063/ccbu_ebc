import {loading_close, loading_open, quertProjectEndStage} from "../util/util.js";
var Main = {
    data () {
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
            const validateRules1 = (rule, value, callback) => {
                if(!this.compilerRise(value,this.additionalPersonnelCost_obj)){
                    callback(new Error('请输入有效值'));
                }
                callback();
            }
            return {
                //当前阶段
                currentStageId:2,
                //当前阶段描述（第i阶段）
                currentStageDesc:'',
                //提交警告
                submitAlert: false,
                activeName:'first',
                //头像
                circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
                sizeList: ["large", "medium", "small"],
                //导航
                activeIndex: '5',
                //进度选择器
                optionsjindu: [],
                //当前阶段所有阈值
                currentLimit: {},
                //人事后勤业务值
                form:{
                    state:'0',hireFireLoans:'',trainLoans:'',businessDevelopmentLoans:'',customerCareLoans:'',
                    hireFireDeposit:'',trainDeposit:'',businessDevelopmentDeposit:'',customerCareDeposit:'',
                    hireFireInvest:'',trainInvest:'',businessDevelopmentInvest:'',customerCareInvest:'',
                    hireFireMiddle:'',trainMiddle:'',businessDevelopmentMiddle:'',customerCareMiddle:'',
                    additionalPersonnelCost:'',automationInvestment:'',
                    currentProjectId:'1',currentTeamId:'1',currentStageId:'1'
                },
                //业务提示语
                limitMsg:{
                    additionalPersonnelCost_msg:''
                },
                //定义接收附加人员成本对象
                additionalPersonnelCost_obj:{},
                // 表单校验规则
                rules: {
                    hireFireLoans: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    trainLoans: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    businessDevelopmentLoans: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    customerCareLoans: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    hireFireDeposit: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    trainDeposit: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    businessDevelopmentDeposit: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    customerCareDeposit: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    hireFireInvest: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    trainInvest: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    businessDevelopmentInvest: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    customerCareInvest: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    hireFireMiddle: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    trainMiddle: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    businessDevelopmentMiddle: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    customerCareMiddle: [{ required: true, validator: validateRules,trigger: 'blur' }],
                    additionalPersonnelCost: [
                        { required: true, validator: validateRules,trigger: 'blur' }
                        ,{ validator: validateRules1,trigger: 'blur' }
                    ],
                    automationInvestment: [{ required: true, validator: validateRules,trigger: 'blur' }]
                },
                //自动化投资
                automation: {},
                automationData: {},
                //人事后勤数据
                result0:{},
                result1:{},
                result2:{},
                result3:{},
                result4:{},
                result0Data:{}
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
                url:'decPersonnel/queryPeople',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.data.data!=null) {
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

        formatterMsg(item){
            return "最低" + item.loanBusiness;
        },
        compilerRise(value,item){
            let min = item.loanBusiness
            console.log("loanBusiness="+item.loanBusiness)
            //alert(min)
            if(value < min){
                return false
            }
            return true
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
                    //return false
                }else{
                    let messge = ''
                    if((parseInt(this.form.businessDevelopmentLoans)+parseInt(this.form.customerCareLoans))>20){
                        console.log(this.form.businessDevelopmentLoans+this.form.customerCareLoans)
                        messge = '贷款业务市场拓展 + 客户营销总计不能超过20%'
                        this.$message({
                            showClose: true,
                            message: messge,
                            type: 'error'
                        });
                        return
                    }
                    if((parseInt(this.form.businessDevelopmentDeposit)+parseInt(this.form.customerCareDeposit))>20){
                        messge = '存款业务市场拓展 + 客户营销总计不能超过20%'
                        this.$message({
                            showClose: true,
                            message: messge,
                            type: 'error'
                        });
                        return
                    }
                    if((parseInt(this.form.businessDevelopmentInvest)+parseInt(this.form.customerCareInvest))>20){
                        messge = '投资业务市场拓展 + 客户营销总计不能超过20%'
                        this.$message({
                            showClose: true,
                            message: messge,
                            type: 'error'
                        });
                        return
                    }
                    if((parseInt(this.form.customerCareMiddle)+parseInt(this.form.businessDevelopmentMiddle))>20){
                        messge = '中间业务市场拓展 + 客户营销总计不能超过20%'
                        this.$message({
                            showClose: true,
                            message: messge,
                            type: 'error'
                        });
                        return
                    }
                    this.open();
                }
            })
            // this.open();
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
            loading_open(this)//开启动画
            let that = this
            let form = this.form
            console.log(form);
            axios({
                headers: {
                    'Content-Type': 'application/json'
                },
                method:'post',
                url:'decPersonnel/add',//相对路径即可，不用加/service
                data:form,
                responseType:'json',
            }).then(function(resp){
                const code = resp.data.code
                if(resp.status==200) {
                    //关闭动画
                    loading_close()
                    let message = '人事后勤业务决策数据保存成功'
                    if(that.form.state == '1'){
                        message = '人事后勤业务决策数据提交成功'
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
            loading_open(this)//开启动画
            let that = this
            let form = this.form
            console.log(form);
            axios({
                headers: {
                    'Content-Type': 'application/json'
                },
                method:'post',
                url:'decPersonnel/add',//相对路径即可，不用加/service
                data:form,
                responseType:'json',
            }).then(function(resp){
                const code = resp.data.code
                if(resp.status==200) {
                    //关闭动画
                    loading_close()
                    window.open("yjs?currentProjectId=" + that.form.currentProjectId+"&currentTeamId="+that.form.currentTeamId+"&currentStageId="+that.form.currentStageId)
                    that.queryDecisionData(that.form.currentProjectId,that.form.currentTeamId,that.currentStageId)
                }
            })
            //标记动作为提交
            this.form.state = '0'
        },
        //阶段切换
        switchStagePeople(val){
            //获取自动化投资数据
            this.previousAutomationData(this.form.currentTeamId,val);
            //获取人事后勤数据
            this.previousPeopleLogisticsData(this.form.currentTeamId,val);
        },
        previousAutomationData(teamId,stageId){
            let that = this;
            let params = new URLSearchParams()
            params.append('currentTeamId', teamId)
            params.append('currentStageId', stageId - 1)
            //获取上个阶段的自动化投资
            axios({
                method:'get',
                url:'repPersonnelLogisticsDataAutomation/selectPerLogDataAutomation',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                let data = resp.data.data
                that.automation = data//上一阶段
            })
        },
        previousPeopleLogisticsData(teamId,stageId){
            let that = this;
            let params = new URLSearchParams()
            params.append('currentTeamId', teamId)
            params.append('currentStageId', stageId - 1)
            axios({
                method:'get',
                url:'repPeopleLogistics/selectPeopleLogistics',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.status==200){
                    let result = resp.data.data;
                    that.result0 = result['贷款业务'];
                    that.result1 = result['存款业务'];
                    that.result2 = result['投资业务'];
                    that.result3 = result['中间业务'];
                    that.result4 = result['其他'];
                    // console.log(result);
                    // console.log(that.result0);
                    // console.log(that.result0.startEmployeeEuantity);
                }
            }).catch(function(error){
                console.log(error);
            });
        }

    },
    // 页面加载时请求数据
    mounted:function () {
        //动态获取当前正在进行阶段
        this.currentStageId = this.$refs['currentStageId'].attrs.value
        //设置默认选中的option
        this.currentStageDesc = '第' + (this.currentStageId - 1) + '阶段'
        //动态生成可选阶段列表
        quertProjectEndStage(this)
        let that = this;
        //获取已保存数据
        let teamId = this.$refs['teamId'].attrs.value
        let projectId = this.$refs['projectId'].attrs.value
        //查询当前阶段已保存数据
        this.queryDecisionData(projectId, teamId, this.currentStageId)
        //设置formdata的数据
        this.form.currentStageId = this.currentStageId
        this.form.currentProjectId = projectId
        this.form.currentTeamId = teamId
            //获取当前阶段的参数
            axios({
                method:'get',
                url:'parPersonnelCost/queryAdditional',//相对路径即可，不用加/service
                responseType:'json',
            }).then(function(resp){
                if(resp.status==200){
                    let result = resp.data.data;
                    that.limitMsg.additionalPersonnelCost_msg =  that.formatterMsg(result['附加人员成本']);
                    that.additionalPersonnelCost_obj =  result['附加人员成本'];
                }
            }).catch(function(error){
                console.log(error);
            });
            //获取自动化投资数据
            this.previousAutomationData(teamId,this.currentStageId);
            //获取人事后勤
            this.previousPeopleLogisticsData(teamId,this.currentStageId);
        }

    }
;const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");
