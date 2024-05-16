import {loading_open, loading_close, quertProjectEndStage} from "../util/util.js";
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
            if(value < 0.5 || value > 1.5){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules2 = (rule, value, callback) => {
            if(value < 0.25 || value > 0.75){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        //基础非空验证规则
        return {
            //当前阶段
            currentStageId:4,
            //提交警告
            submitAlert: false,
            //当前阶段描述（第i阶段）
            currentStageDesc:'',
            //头像
            circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
            squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
            sizeList: ["large", "medium", "small"],
            //导航
            activeIndex: '4',
            //tabs
            activeName: 'first',
            //滑块
            slider1: 50,
            slider2: 50,
            marks: {
                0: '0',
                100: '400',
                50: {
                    style: {
                        color: '#1989FA'
                    },
                    label: '200'
                }
            },
            //进度选择器
            optionsjindu: [
            {
                value: '0',
                label: '第0阶段'
            }, {
                value: '1',
                label: '第1阶段'
            }, {
                value: '2',
                label: '第2阶段'
            }, {
                value: '3',
                label: '第3阶段'
            }, {
                value: '4',
                label: '第4阶段'
            }, {
                value: '5',
                label: '第5阶段'
            }],
            //当前阶段所有阈值
            currentLimit: {
            },
            //中间业务值
            form:{
                state:'0',custodyBusinessAdd:'',guaranteeBusinessAdd: '',custodyBusinessProcedure: '',guaranteeBusinessProcedure: '',firstUnderwritPriceBond: '',secondUnderwritPriceBond: '',thirdUnderwritPriceBond: '',currentProjectId:'1',currentTeamId:'1',currentStageId:'1'
            },
            //中间业务提示语
            limitMsg:{
                custodyBusiness_msg: '',guaranteeBusiness_msg: ''
            },
            //定义接收预期市场供求的对象
            custodyBusiness_obj:{},guaranteeBusiness_msg :{},
            guaranteeBusiness_obj:{},
            //资产负债表结果接收对象
            // 表单校验规则
            rules: {

                custodyBusinessAdd: [{ required: true, validator: validateRules,trigger: 'blur' }],
                guaranteeBusinessAdd: [{ required: true, validator: validateRules,trigger: 'blur' }],
                custodyBusinessProcedure: [
                    { required: true, validator: validateRules,trigger: 'blur' },
                    { required: true, validator: validateRules1,trigger: 'blur' }
                ],
                guaranteeBusinessProcedure: [
                    { required: true, validator: validateRules,trigger: 'blur' },
                    { required: true, validator: validateRules2,trigger: 'blur' }
                ],
                firstUnderwritPriceBond: [{ required: true, validator: validateRules,trigger: 'blur' }],
                secondUnderwritPriceBond: [{ required: true, validator: validateRules,trigger: 'blur' }],
                thirdUnderwritPriceBond: [{ required: true, validator: validateRules,trigger: 'blur' }],

            },
            result0:{},
            result1:{},
            result2:{},
            resultData:{
            }
        }
    },
    methods: {
        //查询已保存或填写的数据
        queryMiddleData(currentProjectId,currentTeamId,currentStageId){
            let that = this
            let params= new URLSearchParams()
            params.append('currentProjectId', currentProjectId)
            params.append('currentTeamId', currentTeamId)
            params.append('currentStageId', currentStageId)
            axios({
                method:'get',
                url:'decMiddleCustody/queryData',//相对路径即可，不用加/service
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
        formatterMsg(item){
            return "预期市场供求" + item.marketProspect ;
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
            let form = this.form
            console.log(form);
            axios({
                headers: {
                    'Content-Type': 'application/json'
                },
                method:'post',
                url:'decMiddleCustody/add',//相对路径即可，不用加/service
                data:form,
                responseType:'json',
            }).then(function(resp){
                const code = resp.data.code
                loading_close()//关闭动画
                if(resp.status==200) {
                    let message = '中间业务决策数据保存成功'
                    if(that.form.state == '1'){
                        message = '中间业务决策数据提交成功'
                    }
                    let type = "success"
                    that.$message({
                        showClose: true,
                        message: message,
                        type: type
                    });
                    that.queryMiddleData(that.form.currentProjectId,that.form.currentTeamId,that.currentStageId)
                }
            })
        },
        //提交表单前
        preOperation () {
            loading_open(this)//开启加载动画
            let that = this
            let form = this.form
            console.log(form);
            axios({
                headers: {
                    'Content-Type': 'application/json'
                },
                method:'post',
                url:'decMiddleCustody/add',//相对路径即可，不用加/service
                data:form,
                responseType:'json',
            }).then(function(resp){
                const code = resp.data.code
                loading_close()//关闭动画
                if(resp.status==200) {
                    window.open("yjs?currentProjectId=" + that.form.currentProjectId+"&currentTeamId="+that.form.currentTeamId+"&currentStageId="+that.form.currentStageId)
                    that.queryMiddleData(that.form.currentProjectId,that.form.currentTeamId,that.currentStageId)
                }
            })
            //标记动作为提交
            this.form.state = '0'
        },
        //阶段切换
        switchStageMiddle(val){
            this.previousMiddleData(this.form.currentProjectId,val);
        },
        //查询上阶段中间业务数据
        previousMiddleData(projectId,stageId){
            let that = this;
            //console.log("that="+that)
            let params1= new URLSearchParams()
            params1.append('currentProjectId', projectId)
            params1.append('currentStageId', stageId - 1)
            axios({
                method:'get',
                url:'repAgencyBond/selectAgencyBondAll',//相对路径即可，不用加/service
                params:params1,
                responseType:'json',
            }).then(function(resp){
                if(resp.status==200){
                    let result0 = resp.data.data[0];
                    let result1 = resp.data.data[1];
                    let result2 = resp.data.data[2];
                    console.log(result0);
                    console.log(result1);
                    console.log(result2);
                    that.result0 = result0;
                    that.result1 = result1;
                    that.result2 = result2;

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
        //动态生成可选阶段列表
        quertProjectEndStage(this)
        let that = this;
        let teamId = this.$refs['teamId'].attrs.value
        let projectId = this.$refs['projectId'].attrs.value
        this.queryMiddleData(projectId,teamId,this.currentStageId)
        //this.queryMiddleData(projectId,teamId,this.currentStageId)
        //设置formdata的数据
        this.form.currentStageId = this.currentStageId
        this.form.currentProjectId = projectId
        this.form.currentTeamId = teamId
        //获取当前阶段的市场供求预测
        axios({
            method:'get',
            url:'parMarketSupplyDemandForecast/selectParMarketSupply?stageId='+this.currentStageId,//相对路径即可，不用加/service
            responseType:'json',
        }).then(function(resp){
            if(resp.status==200){
                let result = resp.data.data;
                that.limitMsg.custodyBusiness_msg =  that.formatterMsg(result['委托资产托管业务']);
                that.limitMsg.guaranteeBusiness_msg =  that.formatterMsg(result['担保业务']);
                that.custodyBusiness_obj =  result['委托资产托管业务'];
                that.guaranteeBusiness_obj =  result['担保业务'];
            }
        }).catch(function(error){
            console.log(error);
        });
        //获取中间业务数据
        this.previousMiddleData(projectId,this.currentStageId);
    }
}
const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");