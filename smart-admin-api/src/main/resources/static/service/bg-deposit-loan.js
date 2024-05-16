import {loading_open,loading_close,quertProjectEndStage} from "../util/util.js";
var Main = {
    data () {
        //基础非空验证规则
        return {
            //头像
            circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
            squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
            sizeList: ["large", "medium", "small"],
            //导航
            activeIndex: '5',
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
            bgProjectId:"",
            bgTeamId:"",
            bgStageName:"",
            bgStageId:"",
            //进度选择器
            optionsjindu: [],
            //当前阶段所有阈值
            currentLimit: {},
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

            //资产负债表数据
            asset1: {},//阶段
            assetData: {},
            //利润表数据
            profit1: {},//阶段
            profitData: {},
            //流动性报表-现金
            liquiditycash: {interbankDeposit:'',interBank:'',shortDeposit:'',
                longDeposit:'',shortSavingsDeposit:'',longSavingsDeposit:'',
                totalShortDebt:'',cashLiquidityRequirements:'',solvency:'',
                cashSolvency:''},
            liquiditycashData: {},
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
            },
            result0:{bondType:'',couponRate:'',deadline:'',creditRating:'',
                issueAmount:'',underwritingBank:'',underwritingMount:'',subscriptionLimit:'',
                underwritingPrice:'',discount:''},
            result111:{bondType:'',couponRate:'',deadline:'',creditRating:'',
                issueAmount:'',underwritingBank:'',underwritingMount:'',subscriptionLimit:'',
                underwritingPrice:'',discount:''},
            result211:{bondType:'',couponRate:'',deadline:'',creditRating:'',
                issueAmount:'',underwritingBank:'',underwritingMount:'',subscriptionLimit:'',
                underwritingPrice:'',discount:''},
            result033:{},
            result133:{},
            result233:{},
            result333:{},
            result433:{},
            automation:{presentStage:'',cumulativeInvestment:'',exponent:''},
        }

    },
    methods: {
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
        //查询资债数据
        previousDepositLoanData(bgProjectId,bgTeamId,bgStageId){
            //获取上两个阶段的资产负债数据
            let params= new URLSearchParams()
            params.append('bgProjectId', bgProjectId)
            params.append('bgTeamId', bgTeamId)
            params.append('bgStageId', bgStageId)
            let that = this;
            axios({
                method:'get',
                url:'selectBalanceResultBg',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.status==200){
                    let bgData = resp.data.data[0]
                    let pre1 = bgStageId;
                    console.log('资债数据')
                    console.log(bgData[pre1])
                    that.asset1 = bgData[pre1]
                }
            })
        },
        //查询利润数据
        previousProfitData(bgProjectId,bgTeamId,bgStageId){
            let that = this;
            let params= new URLSearchParams()
            params.append('bgProjectId', bgProjectId)
            params.append('bgTeamId', bgTeamId)
            params.append('bgStageId', bgStageId)
            //获取利润表数据
            axios({
                method:'get',
                url:'selectProfitBg',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.status==200){
                    let bgData = resp.data.data[0]
                    let pre1 = bgStageId;
                    console.log('利润表数据')
                    console.log(bgData[pre1])
                    that.profit1 = bgData[pre1]
                }
            })
        },
        //查询上阶段流动性报表数据
        previousMobilityData(bgProjectId,bgTeamId,bgStageId){
            let that = this;
            let params = new URLSearchParams()
            params.append('bgProjectId', bgProjectId)
            params.append('bgTeamId', bgTeamId)
            params.append('bgStageId', bgStageId)
            //获取上个阶段的流动性报表-现金数据
            axios({
                method:'get',
                url:'selectLiquidityCashBg',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.status==200) {
                    let data = resp.data.data
                    console.log('流动性报表')
                    console.log(data)
                    if(null!=data){
                        that.liquiditycash = data
                    }

                }
            })
        },
        //获取股票数据
        previousStockData(bgProjectId,bgTeamId,bgStageId){
            //获取上个阶段的股票数据
            let that = this;
            let params = new URLSearchParams()
            params.append('bgProjectId', bgProjectId)
            params.append('bgTeamId', bgTeamId)
            params.append('bgStageId', bgStageId)
            axios({
                method:'get',
                url:'selectInvestbusOperatDataStockBg',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.status==200){
                    console.log("获取股票数据")
                    let result = resp.data.data;
                    console.log(resp.data)
                    if(null!=result){
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
                    }
                }
            }).catch(function(error){
                console.log(error);
            });
        },
        //获取债券数据
        previousBondData(bgProjectId,bgTeamId,bgStageId){
            let that = this;
            let params = new URLSearchParams()
            params.append('bgProjectId', bgProjectId)
            params.append('bgTeamId', bgTeamId)
            params.append('bgStageId', bgStageId)
            axios({
                method:'get',
                url:'selectInvestbusOperatDataBondBg',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.status==200){
                    console.log("获取债券数据")
                    let result2 = resp.data.data;
                    console.log(resp.data.data)
                    if(null!=result2){
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
                    }
                }
            }).catch(function(error){
                console.log(error);
            });
        },
        //查询上阶段中间业务数据(代理债券发行业务数据表)
        previousMiddleData(bgProjectId,bgTeamId,bgStageId){
            let that = this;
            let params = new URLSearchParams()
            params.append('bgProjectId', bgProjectId)
            params.append('bgTeamId', bgTeamId)
            params.append('bgStageId', bgStageId)
            axios({
                method:'get',
                url:'selectAgencyBondAllBg',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.status==200){
                    let result0 = resp.data.data[0];
                    let result111 = resp.data.data[1];
                    let result211 = resp.data.data[2];
                    console.log('代理债券发行业务')
                    console.log(resp.data.data);
                    if(resp.data.data.len()!=0){
                        that.result0 = result0;
                        that.result111 = result111;
                        that.result211 = result211;
                    }
                }
            }).catch(function(error){
                console.log(error);
            });
        },
        //获取自动化投资数据
        previousAutomationData(bgProjectId,bgTeamId,bgStageId){
            let that = this;
            let params = new URLSearchParams()
            params.append('bgProjectId', bgProjectId)
            params.append('bgTeamId', bgTeamId)
            params.append('bgStageId', bgStageId)
            //获取上个阶段的自动化投资
            axios({
                method:'get',
                url:'selectPerLogDataAutomationBg',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                console.log('获取自动化投资数据')
                let data = resp.data.data
                console.log(data)
                if(null!=data){
                    that.automation = data
                }

            })
        },
        previousPeopleLogisticsData(bgProjectId,bgTeamId,bgStageId){
            let that = this;
            let params = new URLSearchParams()
            params.append('bgProjectId', bgProjectId)
            params.append('bgTeamId', bgTeamId)
            params.append('bgStageId', bgStageId)
            axios({
                method:'get',
                url:'selectPeopleLogisticsBg',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.status==200){
                    let result = resp.data.data;
                    console.log('获取人事后勤数据')
                    that.result033 = result['贷款业务'];
                    that.result133 = result['存款业务'];
                    that.result233 = result['投资业务'];
                    that.result333 = result['中间业务'];
                    that.result444 = result['其他'];
                    console.log(result);
                }
            }).catch(function(error){
                console.log(error);
            });
        }
    },
    // 页面加载时请求数据
    mounted:function () {
        let that = this;
        that.bgProjectId = this.$refs['bgProjectId'].attrs.value
        that.bgTeamId = this.$refs['bgTeamId'].attrs.value
        that.bgStageId = this.$refs['bgStageId'].attrs.value
        that.bgStageName = that.bgStageId -1
        // //获取资债数据
        this.previousDepositLoanData(that.bgProjectId,that.bgTeamId,that.bgStageId);
        // //获取利润数据
        this.previousProfitData(that.bgProjectId,that.bgTeamId,that.bgStageId)
        // //获取流动性报表数据
        this.previousMobilityData(that.bgProjectId,that.bgTeamId,that.bgStageId);
        //获取股票数据
        this.previousStockData(that.bgProjectId,that.bgTeamId,that.bgStageId);
        //获取债券数据
        this.previousBondData(that.bgProjectId,that.bgTeamId,that.bgStageId);
        //中间业务数据
        this.previousMiddleData(that.bgProjectId,that.bgTeamId,that.bgStageId);

        //获取自动化投资数据
        this.previousAutomationData(that.bgProjectId,that.bgTeamId,that.bgStageId);
        //获取人事后勤
        this.previousPeopleLogisticsData(that.bgProjectId,that.bgTeamId,that.bgStageId);
    }
}
const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");