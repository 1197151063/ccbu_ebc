import {loading_open,loading_close,quertProjectEndStage} from "../util/util.js";
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
            if(!this.compilerRise(value,this.shortMortgageLoanRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules2 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.shortHypothecatedLoanRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules3 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.shortGuaranteeLoanRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules4 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.shortLoanCreditRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules5 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.discountRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules6 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.mlongMortgageLoanRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules7 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.mlongHypothecatedLoanRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules8 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.mlongLoanCreditRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules9 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.mlongGuaranteeLoanRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules10 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.shortDepositRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules11 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.shortSavingsDepositRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules12 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.interbankdepositnnnRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules13 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.longDePositRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        const validateRules14 = (rule, value, callback) => {
            if(!this.compilerRise(value,this.longDepositRise_obj)){
                callback(new Error('请输入有效值'));
            }
            callback();
        }
        //基础非空验证规则
        return {
            //当前阶段
            currentStageId: 2,
            //当前阶段描述（第i阶段）
            currentStageDesc:'',
            //提交警告
            submitAlert: false,
            //头像
            circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
            squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
            sizeList: ["large", "medium", "small"],
            //导航
            activeIndex: '2',
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
            optionsjindu: [],
            //当前阶段所有阈值
            currentLimit: {},
            //存储上两个阶段的值
            previousStage1:'',
            previousStage2:'',
            //存贷业务值
            form: {
                state: '0',
                totalAssets: '',
                dueCentralBank: '',
                interbankDeposit: '',
                loanTrade: '',
                shortMortgageLoanAdd: '',
                shortHypothecatedLoanAdd: '',
                shortGuaranteeLoanAdd: '',
                shortLoanCreditRiseAdd: '',
                shortMortgageLoanRise: '',
                shortHypothecatedLoanRise: '',
                shortGuaranteeLoanRise: '',
                shortLoanCreditRise: '',
                discountAdd: '',
                discountRise: '',
                mlongMortgageLoanAdd: '',
                mlongHypothecatedLoanAdd: '',
                mlongGuaranteeLoanAdd: '',
                mlongLoanCreditAdd: '',
                mlongMortgageLoanRise: '',
                mlongHypothecatedLoanRise: '',
                mlongGuaranteeLoanRise: '',
                mlongLoanCreditRise: '',
                interBank: '',
                issueLongBonds: '',
                issueSecondaryBonds: '',
                shortDepositAdd: '',
                shortSavingsDepositAdd: '',
                interbankdepositnnnAdd: '',
                longDePositAdd: '',
                longDepositAdd1: '',
                shortDepositRise: '',
                shortSavingsDepositRise: '',
                interbankdepositnnnRise: '',
                longDePositRise: '',
                longSavingsDepositRise: '',
                currentProjectId: '1',
                currentTeamId: '1',
                currentStageId: '1'
            },
            //存贷业务提示语
            limitMsg: {
                dueCentralBank_msg: '',
                interbankDeposit_msg: '',
                loanTrade_msg: '',
                shortMortgageLoanAdd_msg: '',
                shortHypothecatedLoanAdd_msg: '',
                shortGuaranteeLoanAdd_msg: '',
                shortLoanCreditRiseAdd_msg: '',
                shortMortgageLoanRise_msg: '',
                shortHypothecatedLoanRise_msg: '',
                shortGuaranteeLoanRise_msg: '',
                shortLoanCreditRise_msg: '',
                discountAdd_msg: '',
                discountRise_msg: '',
                mlongMortgageLoanAdd_msg: '',
                mlongHypothecatedLoanAdd_msg: '',
                mlongGuaranteeLoanAdd_msg: '',
                mlongLoanCreditAdd_msg: '',
                mlongMortgageLoanRise_msg: '',
                mlongHypothecatedLoanRise_msg: '',
                mlongGuaranteeLoanRise_msg: '',
                mlongLoanCreditRise_msg: '',
                interBank_msg: '',
                issueLongBonds_msg: '',
                issueSecondaryBonds_msg: '',
                shortDepositAdd_msg: '',
                shortSavingsDepositAdd_msg: '',
                interbankdepositnnnAdd_msg: '',
                longDePositAdd_msg: '',
                longDepositAdd1_msg: '',
                shortDepositRise_msg: '',
                shortSavingsDepositRise_msg: '',
                interbankdepositnnnRise_msg: '',
                longDePositRise_msg: '',
                longDepositRise_msg: ''
            },
            //定义接收利率区间的对象
            shortMortgageLoanRise_obj: {},
            shortHypothecatedLoanRise_obj: {},
            shortLoanCreditRise_obj: {},
            shortGuaranteeLoanRise_obj: {},
            discountRise_obj: {},
            mlongMortgageLoanRise_obj: {},
            mlongHypothecatedLoanRise_obj: {},
            mlongLoanCreditRise_obj: {},
            mlongGuaranteeLoanRise_obj: {},
            shortDepositRise_obj: {},
            shortSavingsDepositRise_obj: {},
            interbankdepositnnnRise_obj: {},
            longDePositRise_obj: {},
            longDepositRise_obj: {},
            //资产负债表结果接收对象
            // 表单校验规则
            rules: {
                dueCentralBank: [{required: true, validator: validateRules, trigger: 'blur'}],
                interbankDeposit: [{required: true, validator: validateRules, trigger: 'blur'}],
                loanTrade: [{required: true, validator: validateRules, trigger: 'blur'}],
                shortMortgageLoanAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                shortHypothecatedLoanAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                shortGuaranteeLoanAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                shortLoanCreditRiseAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                shortMortgageLoanRise: [
                    {required: true, validator: validateRules, trigger: 'blur'},
                    {validator: validateRules1, trigger: 'blur'}
                ],
                shortHypothecatedLoanRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules2, trigger: 'blur'}],
                shortGuaranteeLoanRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules3, trigger: 'blur'}],
                shortLoanCreditRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules4, trigger: 'blur'}],
                discountAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                discountRise: [{required: true, validator: validateRules, trigger: 'blur'}, {
                    validator: validateRules5,
                    trigger: 'blur'
                }],
                mlongMortgageLoanAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                mlongHypothecatedLoanAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                mlongGuaranteeLoanAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                mlongLoanCreditAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                mlongMortgageLoanRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules6, trigger: 'blur'}],
                mlongHypothecatedLoanRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules7, trigger: 'blur'}],
                mlongLoanCreditRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules8, trigger: 'blur'}],
                mlongGuaranteeLoanRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules9, trigger: 'blur'}],
                interBank: [{required: true, validator: validateRules, trigger: 'blur'}],
                issueLongBonds: [{required: true, validator: validateRules, trigger: 'blur'}],
                issueSecondaryBonds: [{required: true, validator: validateRules, trigger: 'blur'}],
                shortDepositAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                shortSavingsDepositAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                interbankdepositnnnAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                longDePositAdd: [{required: true, validator: validateRules, trigger: 'blur'}],
                longDepositAdd1: [{required: true, validator: validateRules, trigger: 'blur'}],
                shortDepositRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules10, trigger: 'blur'}],
                shortSavingsDepositRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules11, trigger: 'blur'}],
                interbankdepositnnnRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules12, trigger: 'blur'}],
                longDePositRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules13, trigger: 'blur'}],
                longSavingsDepositRise: [{
                    required: true,
                    validator: validateRules,
                    trigger: 'blur'
                }, {validator: validateRules14, trigger: 'blur'}],
            },
            //资产负债表数据
            asset1: {},//前1阶段
            asset2: {},//前2阶段
            assetData: {},
            //利润表数据
            profit1: {},//前1阶段
            profit2: {},//前2阶段
            profitData: {},
            //流动性报表-现金
            liquiditycash: {},
            liquiditycashData: {}
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
                url:'decLoanDeposit/query',//相对路径即可，不用加/service
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
            return "最低" + item.interestRateMin + " 平均" + item.interestRateAverage + " 最高" + item.interestRateMax;
        },
        compilerRise(value,item){
            let min = item.interestRateMin
            let max = item.interestRateMax
            if(value < min || value > max){
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
                url:'decLoanDeposit/add',//相对路径即可，不用加/service
                data:form,
                responseType:'json',
            }).then(function(resp){
                const code = resp.data.code
                //关闭动画
                loading_close()
                if(resp.status==200) {
                    let message = '存贷款业务决策数据保存成功'
                    if(that.form.state == '1'){
                        message = '存贷款业务决策数据提交成功'
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
                url:'decLoanDeposit/add',//相对路径即可，不用加/service
                data:form,
                responseType:'json',
            }).then(function(resp){

                const code = resp.data.code
                //关闭动画
                loading_close()
                if(resp.status==200) {
                    window.open("yjs?currentProjectId=" + that.form.currentProjectId+"&currentTeamId="+that.form.currentTeamId+"&currentStageId="+that.form.currentStageId)
                    that.queryDecisionData(that.form.currentProjectId,that.form.currentTeamId,that.currentStageId)
                }
            })
            //标记动作为提交
            this.form.state = '0'
            // console.log(this.form.currentProjectId)
            // alert(formName.currentStageId)

            // this.$refs[form].validate((valid) => {
            //     if (!valid) {
            //         this.submitAlert = true
            //         return false
            //     }else{
                    // this.Operation();
                    // window.location.href="yjs?data=" +formName;
                // }
            // })
        },
        // Operation(){
        //     loading_open(this)//开启加载动画
        //     let form = this.form
        //     axios({
        //         headers: {
        //             'Content-Type': 'application/json'
        //         },
        //         method:'post',
        //         url:'decLoanDeposit/preOperation',//相对路径即可，不用加/service
        //         data:form,
        //         responseType:'json',
        //     }).then(function(resp){
        //         let data = resp.data.data
        //         //关闭动画
        //         loading_close();
        //         that.asset1=data['资产负债表']
        //         window.open("yjs_cdk.html",that.asset1)
        //     })
        // },
        //阶段切换
        switchStage(val){
            //获取资债数据
            this.previousDepositLoanData(this.form.currentTeamId,val);
            //获取利润数据
            this.previousProfitData(this.form.currentTeamId,val)
            //获取流动性报表数据
            this.previousMobilityData(this.form.currentTeamId,val);
            this.previousStage1 = val-2;
            this.previousStage2 = val-3;
        },
        //查询上两个阶段的资债数据
        previousDepositLoanData(teamId,stageId){
            let that = this;
            //获取上两个阶段的资产负债数据
            let params= new URLSearchParams()
            params.append('currentTeamId', teamId)
            params.append('currentStageIds', [stageId-1,stageId-2])
            axios({
                method:'get',
                url:'repBalanceSheet/selectBalanceResult',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                let data = resp.data.data[0]
                // that.assetData = data
                let pre1 = stageId - 1;
                let pre2 = stageId - 2;
                that.asset1 = data[pre1]//上一阶段
                that.asset2 = data[pre2]//上两阶段
            })
        },
        //查询上两个阶段的利润数据
        previousProfitData(teamId,stageId){
            let that = this;
            let params= new URLSearchParams()
            params.append('currentTeamId', teamId)
            params.append('currentStageIds', [stageId-1,stageId-2])
            //获取上两个阶段的利润表数据
            axios({
                method:'get',
                url:'repProfit/selectProfit',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                let data = resp.data.data[0]
                // that.assetData = data
                let pre1 = stageId - 1;
                let pre2 = stageId - 2;
                that.profit1 = data[pre1]//上一阶段
                that.profit2 = data[pre2]//上两阶段
            })
        },
        //查询上阶段流动性报表数据
        previousMobilityData(teamId,stageId){
            let paramc = new URLSearchParams()
            paramc.append('currentTeamId', teamId)
            paramc.append('currentStageId', stageId-1)
            let that = this;
            //获取上个阶段的流动性报表-现金数据
            axios({
                method:'get',
                url:'repLiqudityCash/selectLiquidityCash',//相对路径即可，不用加/service
                params:paramc,
                responseType:'json',
            }).then(function(resp){
                let data = resp.data.data
                // that.liquiditycash = data
                // let pre1 = that.currentStageId - 1;
                that.liquiditycash = data//上一阶段
            })
        }
    },
    // 页面加载时请求数据
    mounted:function () {
        //动态获取当前正在进行阶段
        this.currentStageId = this.$refs['currentStageId'].attrs.value
        //设置数据参考中上两阶段的值
        this.previousStage1 = this.currentStageId-2;
        this.previousStage2 = this.currentStageId-3;
        //动态生成可选阶段列表
        quertProjectEndStage(this)
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
        //获取当前阶段的经济形势
        axios({
            method:'get',
            url:'parPropertyLiabilityRate/selectRate?stageId='+that.currentStageId,//相对路径即可，不用加/service
            responseType:'json',
        }).then(function(resp){
            console.log(resp)
            if(resp.status==200){
                let result = resp.data.data;
                //短期
                that.limitMsg.shortMortgageLoanRise_msg =  that.formatterMsg(result['短期贷款-抵押贷款']);
                that.limitMsg.shortHypothecatedLoanRise_msg =  that.formatterMsg(result['短期贷款-质押贷款']);
                that.limitMsg.shortLoanCreditRise_msg =  that.formatterMsg(result['短期贷款-信用贷款']);
                that.limitMsg.shortGuaranteeLoanRise_msg =  that.formatterMsg(result['短期贷款-保证贷款']);
                that.limitMsg.discountRise_msg =  that.formatterMsg(result['贴现']);
                //中长期
                that.limitMsg.mlongMortgageLoanRise_msg =  that.formatterMsg(result['长期贷款-抵押贷款']);
                that.limitMsg.mlongHypothecatedLoanRise_msg =  that.formatterMsg(result['长期贷款-质押贷款']);
                that.limitMsg.mlongLoanCreditRise_msg = that.formatterMsg(result['长期贷款-信用贷款']);
                that.limitMsg.mlongGuaranteeLoanRise_msg =  that.formatterMsg(result['长期贷款-保证贷款']);
                //负债
                that.limitMsg.shortDepositRise_msg =  that.formatterMsg(result['短期存款']);
                that.limitMsg.shortSavingsDepositRise_msg =  that.formatterMsg(result['短期储蓄存款']);
                that.limitMsg.interbankdepositnnnRise_msg =  that.formatterMsg(result['同业存放款项']);
                that.limitMsg.ongDePositRise_msg =  that.formatterMsg(result['长期存款']);
                that.limitMsg.longDepositRise_msg =  that.formatterMsg(result['长期储蓄存款']);

                that.shortMortgageLoanRise_obj =  result['短期贷款-抵押贷款'];
                that.shortHypothecatedLoanRise_obj =  result['短期贷款-质押贷款'];
                that.shortLoanCreditRise_obj =  result['短期贷款-信用贷款'];
                that.shortGuaranteeLoanRise_obj =  result['短期贷款-保证贷款'];
                that.discountRise_obj =  result['贴现'];
                //中长期
                that.mlongMortgageLoanRise_obj =  result['长期贷款-抵押贷款'];
                that.mlongHypothecatedLoanRise_obj =  result['长期贷款-质押贷款'];
                that.mlongLoanCreditRise_obj = result['长期贷款-信用贷款'];
                that.mlongGuaranteeLoanRise_obj =  result['长期贷款-保证贷款'];
                //负债
                that.shortDepositRise_obj =  result['短期存款'];
                that.shortSavingsDepositRise_obj =  result['短期储蓄存款'];
                that.interbankdepositnnnRise_obj =  result['同业存放款项'];
                that.longDePositRise_obj =  result['长期存款'];
                that.longDepositRise_obj =  result['长期储蓄存款'];
            }
        }).catch(function(error){
            console.log(error);
        });
        //获取资债数据
        this.previousDepositLoanData(teamId,this.currentStageId);
        //获取利润数据
        this.previousProfitData(teamId,this.currentStageId)
        //获取流动性报表数据
        this.previousMobilityData(teamId,this.currentStageId);
    }
}
const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");