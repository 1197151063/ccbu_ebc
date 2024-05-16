import{loading_open,loading_close,quertProjectEndStage} from "../util/util.js";
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
                let reg = /^[+-]?(0|([1-9]\d*))(\.\d+)?$/g;
                 if (!reg.test(value)) {
                    callback(new Error('请输入数字'));
                } else {
                    callback();
                }
                callback();
            }
            //必须填正数
            const validateRules2 = (rule, value, callback) => {
                if (value < 0) {
                    callback(new Error('请输入有效值'));
                } else {
                    callback();
                }
                callback();
            }
            //必须填负数
            const validateRules3 = (rule, value, callback) => {
                if (value > 0) {
                    callback(new Error('请输入有效值'));
                } else {
                    callback();
                }
                callback();
            }
            return {
                //当前阶段
                currentStageId: '',
                //当前阶段描述（第i阶段）
                currentStageDesc:'',
                //提交警告
                submitAlert: false,
                //头像
                circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
                sizeList: ["large", "medium", "small"],
                //导航
                activeIndex: '3',
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
                value2: '选项23',
                rules: {
                    investmentStock: [{required: true, validator: validateRules, trigger: 'blur'}],//短期-股票
                    nvestmentBond: [{required: true, validator: validateRules, trigger: 'blur'}]//短期-债券
                },
                rules1:{
                    // shares1: [{required: true, validator: validateRules, trigger: 'blur'}],
                    shares2: [{required: true, validator: validateRules, trigger: 'blur'}],
                    shares3: [{required: true, validator: validateRules, trigger: 'blur'}],
                    shares4: [{required: true, validator: validateRules, trigger: 'blur'}],
                    shares5: [{required: true, validator: validateRules, trigger: 'blur'}],
                    // bond_a_1: [{required: true, validator: validateRules, trigger: 'blur'}],
                    bond_a_2: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_a_3: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_a_4: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_a_5: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    // bond_b_1: [{required: true, validator: validateRules, trigger: 'blur'}],
                    bond_b_2: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_b_3: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_b_4: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_b_5: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_c_2: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_c_3: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_c_4: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_c_5: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_d_3: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_d_4: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_d_5: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_e_4: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_e_5: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}],
                    bond_f_5: [{required: true, validator: validateRules, trigger: 'blur'},{required: true, validator: validateRules2, trigger: 'blur'}]
                },
                form1:{
                    currentStageId:'',
                    currentProjectId:'',
                    id:'',
                    currentTeamId:'',
                    investmentStock:'',
                    nvestmentBond:''
                },
                form2:{
                    currentStageId:'',currentProjectId:'',currentTeamId:'',
                    // shares1: '',
                    shares2: '',shares3: '',shares4: '',shares5: '',
                    // bond_a_1: '',
                    bond_a_2: '',bond_a_3: '',bond_a_4: '', bond_a_5: '',
                    // bond_b_1: ''
                    bond_b_2: '',bond_b_3: '',bond_b_4: '', bond_b_5: '',
                    bond_c_2: '', bond_c_3: '',bond_c_4: '',bond_c_5: '',
                    bond_d_3: '',bond_d_4: '',bond_d_5: '',
                    bond_e_4: '',bond_e_5: '',
                    bond_f_5: ''
                },
                result1:{
                    stockIndex: '',stockType: '',stockNumber: '',stockCost: '',
                    stockDividend: '',stockAccount: ''
                },
                result2:{
                    stockIndex: '',stockType: '',stockNumber: '',stockCost: '',
                    stockDividend: '',stockAccount: ''
                },
                result3:{
                    stockIndex: '',stockType: '',stockNumber: '',stockCost: '',
                    stockDividend: '',stockAccount: ''
                },
                result4:{
                    stockIndex: '',stockType: '',stockNumber: '',stockCost: '',
                    stockDividend: '',stockAccount: ''
                },
                result5:{
                    stockIndex: '',stockType: '',stockNumber: '',stockCost: '',
                    stockDividend: '',stockAccount: ''
                },
                result6:{
                    stockIndex: '',stockType: '',stockNumber: '',stockCost: '',
                    stockDividend: '',stockAccount: ''
                },
                result7:{
                    stockIndex: '',stockType: '',stockNumber: '',stockCost: '',
                    stockDividend: '',stockAccount: ''
                },
                result8:{
                    stockIndex: '',stockType: '',stockNumber: '',stockCost: '',
                    stockDividend: '',stockAccount: ''
                },
                result11:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result12:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result13:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result14:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result15:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result16:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result17:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result18:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result19:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result20:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result21:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result22:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result23:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                },
                result24:{
                    bondPrice: '',bondType: '',realValue: '',nominalValue: '',
                    rateInterest: '',interest: '',accountProfitLoss: ''
                }

            }
        },
        methods: {
            queryLongInvestment(currentProjectId,currentTeamId){
                let that = this
                let params= new URLSearchParams()
                params.append('currentProjectId', currentProjectId)
                params.append('currentTeamId', currentTeamId)
                axios({
                    method:'get',
                    url:'decInvestmentLong/query',//相对路径即可，不用加/service
                    params:params,
                    responseType:'json',
                }).then(function(resp){
                    if(resp.data.data!=null){
                        let data = resp.data.data
                        data.forEach(function(item,key){
                            var type = item.bondType;
                            var value = item.buySell;
                            var stageId = item.currentStageId;
                            if(type == 'G'){
                                if(stageId == 3 ){
                                    that.form2.shares2 = value;
                                }
                                if(stageId == 4){
                                    that.form2.shares3 = value;
                                }
                                if(stageId == 5){
                                    that.form2.shares4 = value;
                                }
                                if(stageId == 6){
                                    that.form2.shares5 = value;
                                }
                            }
                            if(type=='A'){
                                if(stageId == 3){
                                    that.form2.bond_a_2 = value;
                                }
                                if(stageId == 4){
                                    that.form2.bond_a_3 = value;
                                }
                                if(stageId == 5){
                                    that.form2.bond_a_4 = value;
                                }
                                if(stageId == 6){
                                    that.form2.bond_a_5 = value;
                                }
                            }
                            if(type=='B'){
                                if(stageId == 3){
                                    that.form2.bond_b_2 = value;
                                }
                                if(stageId == 4){
                                    that.form2.bond_b_3 = value;
                                }
                                if(stageId == 5){
                                    that.form2.bond_b_4 = value;
                                }
                                if(stageId == 6){
                                    that.form2.bond_b_5 = value;
                                }
                            }
                            if(type=='C'){
                                if(stageId == 3){
                                    that.form2.bond_c_2 = value;
                                }
                                if(stageId == 4){
                                    that.form2.bond_c_3 = value;
                                }
                                if(stageId == 5){
                                    that.form2.bond_c_4 = value;
                                }
                                if(stageId == 6){
                                    that.form2.bond_c_5 = value;
                                }
                            }
                            if(type=='D'){
                                if(stageId == 4){
                                    that.form2.bond_d_3 = value;
                                }
                                if(stageId == 5){
                                    that.form2.bond_d_4 = value;
                                }
                                if(stageId == 6){
                                    that.form2.bond_d_5 = value;
                                }
                            }
                            if(type=='E'){
                                if(stageId == 5){
                                    that.form2.bond_e_4 = value;
                                }
                                if(stageId == 6){
                                    that.form2.bond_e_5 = value;
                                }
                            }
                            if(type=='F'){
                                if(stageId == 6){
                                    that.form2.bond_f_5 = value;
                                }
                            }
                        })
                        that.form2.currentProjectId = that.$refs['projectId'].attrs.value
                        that.form2.currentStageId = that.$refs['currentStageId'].attrs.value
                        that.form2.currentTeamId = that.$refs['teamId'].attrs.value
                        // that.form1 = resp.data.data
                    }
                })
            },
            queryDecisionData(currentProjectId,currentTeamId,currentStageId){
                let that = this
                let params= new URLSearchParams()
                params.append('currentProjectId', currentProjectId)
                params.append('currentTeamId', currentTeamId)
                params.append('currentStageId', currentStageId)
                axios({
                    method:'get',
                    url:'decInvestmentShort/query',//相对路径即可，不用加/service
                    params:params,
                    responseType:'json',
                }).then(function(resp){
                    if(resp.data.data!=null){
                        that.form1 = resp.data.data
                    }
                })
            },
            //tab
            formatter(row, column) {
                return row.address;
            },
            //提交表单前
            submitFormBefore (formName) {
                this.$refs['form1'].validate((valid) => {
                    if (!valid) {
                        this.submitAlert = true
                        return false
                    }else{
                        this.$refs['form2'].validate((valid) => {
                            if (!valid) {
                                this.submitAlert = true
                                return false
                            }else{
                                this.open();
                            }
                        })
                    }
                })

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
            // 保存数据
            saveForm(){
                this.postFormData()
                //标记动作为提交
                this.form1.state = '0'
            },
            submitForm(){
                //关闭弹出框
                this.submitConfirm = false
                //标记动作为提交
                this.form1.state = '1'
                this.postFormData()
            },
            postFormData(){
                loading_open(this)//开启加载动画
                let that = this
                //先提交长期的，然后提交短期时更新状态，触发计算
                let form2 = this.form2
                axios({
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    method:'post',
                    url:'decInvestmentLong/add',//相对路径即可，不用加/service
                    data:form2,
                    responseType:'json',
                }).then(function(resp){
                    console.log(resp)
                })
                //提交短期投资
                let form1 = this.form1
                axios({
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    method:'post',
                    url:'decInvestmentShort/add',//相对路径即可，不用加/service
                    data:form1,
                    responseType:'json',
                }).then(function(resp){
                    const code = resp.data.code
                    loading_close()//关闭动画
                    if(resp.status==200) {
                        let message = '投资业务决策数据保存成功'
                        if(that.form1.state == '1'){
                            message = '投资业务决策数据提交成功'
                        }
                        let type = "success"
                        // if(code != '1'){
                        //     type = "warning"
                        //     message = resp.data.msg
                        // }
                        that.$message({
                            showClose: true,
                            message: message,
                            type: type
                        });
                        //刷新数据
                        that.queryDecisionData(that.form1.currentProjectId,that.form1.currentTeamId,that.form1.currentStageId)
                        this.queryLongInvestment(that.form2.currentProjectId,that.form2.currentTeamId)
                    }
                })
            },
            //提交表单前
            preOperation() {
                loading_open(this)//开启加载动画
                let that = this
                //先提交长期的，然后提交短期时更新状态，触发计算
                let form2 = this.form2
                axios({
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    method:'post',
                    url:'decInvestmentLong/add',//相对路径即可，不用加/service
                    data:form2,
                    responseType:'json',
                }).then(function(resp){
                    window.open("yjs?currentProjectId=" + that.form2.currentProjectId+"&currentTeamId="+that.form2.currentTeamId+"&currentStageId="+that.form2.currentStageId)
                    console.log(resp)
                })
                //提交短期投资
                let form1 = this.form1
                axios({
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    method:'post',
                    url:'decInvestmentShort/add',//相对路径即可，不用加/service
                    data:form1,
                    responseType:'json',
                }).then(function(resp){
                    const code = resp.data.code
                    loading_close()//关闭动画
                    if(resp.status==200) {
                        //刷新数据
                        that.queryDecisionData(that.form1.currentProjectId,that.form1.currentTeamId,that.form1.currentStageId)
                        this.queryLongInvestment(that.form2.currentProjectId,that.form2.currentTeamId)
                    }
                })
                //标记动作为提交
                this.form1.state = '0'
            },
            //阶段切换
            switchStageInvestment(val){
                //获取股票数据
                this.previousStockData(this.form1.currentTeamId,val);
                //获取债券数据
                this.previousBondData(this.form1.currentTeamId,val);
            },
            //获取股票数据
            previousStockData(teamId,stageId){
                //获取上个阶段的股票数据
                let params= new URLSearchParams()
                params.append('currentTeamId', teamId)
                params.append('currentStageId', stageId-1)
                let that = this;
                axios({
                    method:'get',
                    url:'repInvestbusOperatDataStock/selectInvestbusOperatDataStock',//相对路径即可，不用加/service
                    params:params,
                    responseType:'json',
                }).then(function(resp){
                    if(resp.status==200){
                        let result = resp.data.data;
                        that.resultShort = result['短期'];
                        that.result1 = that.resultShort['期初持有'];
                        that.result2 = that.resultShort['最新持有'];
                        that.result3 = that.resultShort['净买入/卖出'];
                        that.result4 = that.resultShort['期末持有'];

                        that.resultLong = result['长期'];
                        that.result5 = that.resultLong['期初持有'];
                        that.result6 = that.resultLong['最新持有'];
                        that.result7 = that.resultLong['净买入/卖出'];
                        that.result8 = that.resultLong['期末持有'];

                        // console.log(that.result0);
                        // console.log(that.result1);
                        // console.log(that.result2);
                        // console.log(that.result3);
                        // console.log(that.result4);
                    }
                }).catch(function(error){
                    console.log(error);
                });
            },
            //获取债券数据
            previousBondData(teamId,stageId){
                //获取上个阶段的债券数据
                let params1= new URLSearchParams()
                params1.append('currentTeamId', teamId)
                params1.append('currentStageId', stageId - 1)
                let that = this;
                axios({
                    method:'get',
                    url:'repInvestbusOperatDataBond/selectInvestbusOperatDataBond',//相对路径即可，不用加/service
                    params:params1,
                    responseType:'json',
                }).then(function(resp){
                    if(resp.status==200){
                        let result2 = resp.data.data;
                        that.resultShort1 = result2['短期'];
                        that.result11 = that.resultShort1['A'];
                        if(that.resultShort1['B']==null){
                            that.result12=[]
                        }else{
                            that.result12 = that.resultShort1['B'];
                        }
                        if(that.resultShort1['C']==null){
                            that.result13=[]
                        }else{
                            that.result13 = that.resultShort1['C'];
                        }
                        if(that.resultShort1['D']==null){
                            that.result14=[]
                        }else{
                            that.result14 = that.resultShort1['D'];
                        }
                        if(that.resultShort1['E']==null){
                            that.result15=[]
                        }else{
                            that.result15 = that.resultShort1['E'];
                        }
                        if(that.resultShort1['F']==null){
                            that.result16=[]
                        }else{
                            that.result16 = that.resultShort1['F'];
                        }
                        that.result17 = that.resultShort1['总计'];

                        that.resultLong1 = result2['长期'];
                        that.result18 = that.resultLong1['A'];
                        if(that.resultLong1['B']==null){
                            that.result19=[]
                        }else{
                            that.result19 = that.resultLong1['B'];
                        }
                        if(that.resultLong1['C']==null){
                            that.result20=[]
                        }else{
                            that.result20 = that.resultLong1['C'];
                        }
                        if(that.resultLong1['D']==null){
                            that.result21=[]
                        }else{
                            that.result21 = that.resultLong1['D'];
                        }
                        if(that.resultLong1['E']==null){
                            that.result22=[]
                        }else{
                            that.result22 = that.resultLong1['E'];
                        }
                        if(that.resultLong1['F']==null){
                            that.result23=[]
                        }else{
                            that.result23 = that.resultLong1['F'];
                        }
                        that.result24 = that.resultLong1['总计'];

                        console.log(that.result11);
                        // console.log(that.result21);
                        // console.log(that.result31);
                        // console.log(that.result41);
                        // console.log(that.result51);

                    }
                }).catch(function(error){
                    console.log(error);
                });
            }
        },
        mounted:function (){
            mounted:{
                //动态获取当前正在进行阶段
                this.currentStageId = this.$refs['currentStageId'].attrs.value
                // this.currentStageId = 3
                //动态生成可选阶段列表
                quertProjectEndStage(this)
                let that = this;
                //获取已保存数据
                let teamId = this.$refs['teamId'].attrs.value
                let projectId = this.$refs['projectId'].attrs.value
                //查询当前阶段已保存数据
                this.queryDecisionData(projectId,teamId,this.currentStageId) //短期投资
                this.queryLongInvestment(projectId,teamId)
                //设置formdata的数据
                this.form1.currentStageId = this.currentStageId
                this.form1.currentProjectId = projectId
                this.form1.currentTeamId = teamId
                this.form2.currentStageId = this.currentStageId
                this.form2.currentProjectId = projectId
                this.form2.currentTeamId = teamId
            //获取资债数据
            this.previousStockData(teamId,this.currentStageId);
            //获取利润数据
            this.previousBondData(teamId,this.currentStageId)
            }
        }
    }

;const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");

