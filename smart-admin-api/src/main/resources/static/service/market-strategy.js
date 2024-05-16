import {loading_open,loading_close} from "../util/util.js";
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
            return {
                //提交警告
                submitAlert: false,
                //头像
                circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
                sizeList: ["large", "medium", "small"],
                //导航
                activeIndex: '6',
                //滑块
                form:{
                    loansBusineDevelopLess:0,loansBusineDevelopBetween:0,loansBusineDevelopGreater:0,loansBusineDevelopSmall:0,loansBusineDevelopMedium:0,loansBusineDevelopMajor:0,loansBusineDevelopPublic:0,
                    loansCustomerMainLess:0,loansCustomerMainBetween:0,loansCustomerMainGreater:0,loansCustomerMainSmall:0,loansCustomerMainMedium:0,loansCustomerMainMajor:0,loansCustomerMainPublic:0,
                    depositBusineDevelopLess:0,depositBusineDevelopBetween:0,depositBusineDevelopGreater:0,depositBusineDevelopSmall:0,depositBusineDevelopMedium:0,depositBusineDevelopMajor:0,depositBusineDevelopPublic:0,
                    depositCustomerMainLess:0,depositCustomerMainBetween:0,depositCustomerMainGreater:0,depositCustomerMainSmall:0,depositCustomerMainMedium:0,depositCustomerMainMajor:0,depositCustomerMainPublic:0,
                    middleBusineDevelopLess:0,middleBusineDevelopBetween:0,middleBusineDevelopGreater:0,middleBusineDevelopSmall:0,middleBusineDevelopMedium:0,middleBusineDevelopMajor:0,middleBusineDevelopPublic:0,
                    middleCustomerMainLess:0,middleCustomerMainBetween:0,middleCustomerMainGreater:0,middleCustomerMainSmall:0,middleCustomerMainMedium:0,middleCustomerMainMajor:0,middleCustomerMainPublic:0,
                    customerMarketingLessAge:'',customerMarketingBetweenAge:'',customerMarketingGreaterAge:'',customerMarketingSmallEnterprise:'',customerMarketingMediumEnterprise:'',customerMarketingMajorEnterprise:'',customerMarketingPublicInstitution:'',
                    businessMarketingLoanTransaction:'',businessMarketingDepositBloanTransaction:'',businessMarketingMiddleTransaction:'',state:'0',
                },
                marks: {
                    0: '0',
                    4: '4',
                },
                // 表单校验规则
                rules: {
                    customerMarketingLessAge: [{required: true, validator: validateRules, trigger: 'blur'}],
                    customerMarketingBetweenAge: [{required: true, validator: validateRules, trigger: 'blur'}],
                    customerMarketingGreaterAge: [{required: true, validator: validateRules, trigger: 'blur'}],
                    customerMarketingSmallEnterprise: [{required: true, validator: validateRules, trigger: 'blur'}],
                    customerMarketingMediumEnterprise: [{required: true, validator: validateRules, trigger: 'blur'}],
                    customerMarketingMajorEnterprise: [{required: true, validator: validateRules, trigger: 'blur'}],
                    customerMarketingPublicInstitution: [{required: true, validator: validateRules, trigger: 'blur'}],
                    businessMarketingLoanTransaction: [{required: true, validator: validateRules, trigger: 'blur'}],
                    businessMarketingDepositBloanTransaction: [{required: true, validator: validateRules, trigger: 'blur'}],
                    businessMarketingMiddleTransaction: [{required: true, validator: validateRules, trigger: 'blur'}],

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
                    url:'decMarketStrategy/queryMarket',//相对路径即可，不用加/service
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
                        if((parseInt(this.form.customerMarketingLessAge) +
                            parseInt(this.form.customerMarketingBetweenAge) +
                            parseInt(this.form.customerMarketingGreaterAge) +
                            parseInt(this.form.customerMarketingSmallEnterprise) +
                            parseInt(this.form.customerMarketingMediumEnterprise) +
                            parseInt(this.form.customerMarketingMajorEnterprise) +
                            parseInt(this.form.customerMarketingPublicInstitution) +
                            parseInt(this.form.businessMarketingLoanTransaction) +
                            parseInt(this.form.businessMarketingDepositBloanTransaction) +
                            parseInt(this.form.businessMarketingMiddleTransaction))>200){
                            var messge = '客户营销 + 业务营销合计不能超过200（百万元）'
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
                for (var key in form) {
                    console.log(key + ':' + form[key])
                    if (form[key] == null) {
                        this.form[key] = '0';
                    }
                }
                axios({
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    method:'post',
                    url:'decMarketStrategy/add',//相对路径即可，不用加/service
                    data:form,
                    responseType:'json',
                }).then(function(resp){
                    //关闭动画
                    loading_close()
                    const code = resp.data.code
                    if(resp.status==200) {
                        let message = '市场营销决策数据保存成功'
                        let type = "success"
                        if(code != '1'){
                            type = "warning"
                            message = resp.data.msg
                        }
                        that.$message({
                            showClose: true,
                            message: '市场营销决策数据保存成功',
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
                for (var key in form) {
                    console.log(key + ':' + form[key])
                    if (form[key] == null) {
                        this.form[key] = '0';
                    }
                }
                axios({
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    method:'post',
                    url:'decMarketStrategy/add',//相对路径即可，不用加/service
                    data:form,
                    responseType:'json',
                }).then(function(resp){
                    //关闭动画
                    loading_close()
                    const code = resp.data.code
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
        this.currentStageDesc = '第'+(this.currentStageId - 1)+'阶段'
        //动态生成可选阶段列表
        this.optionsjindu = []
        for(var i=1;i<this.currentStageId;i++){
            let temp = {value:'',label: ''}
            temp.value = i+1
            temp.label = "第"+i+"阶段"
            this.optionsjindu.push(temp)
        }
        let that = this;
        //获取已保存数据
        let teamId = this.$refs['teamId'].attrs.value
        let projectId = this.$refs['projectId'].attrs.value
        //查询当前阶段已保存数据
        this.queryDecisionData(projectId,teamId,this.currentStageId)
        //设置formdata的数据
        this.form.currentStageId = this.currentStageId
        this.form.currentProjectId = projectId
        this.form.currentTeamId = teamId
    }
}
;const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");

