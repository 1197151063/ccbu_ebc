import {quertProjectEndStage} from "../util/teacher_util.js";
import teacher_chart from "../util/teacher_chart.js";
var Main = {
        data () {
            return {
                //头像
                circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
                sizeList: ["large", "medium", "small"],
                //表格
                reportData:{},
                teamData:[],
                tableData: [],
                shichangData:[],
                tufaData:[],
                fubenData:[],
                fengxianData:[],
                messageData:[],
                tybgData:{},
                defaultTeamLogo:'../photo/team_logo1.jpg',
                //导航
                activeIndex: '1',
                //tabs
                tab:{},
                teamtabs: '',
                tybgtabs: 'first',
                zsktabs: 'first',
                //进度选择器
                optionsjindu: [],
                //业务选择器
                optionsyewu: [
                    {
                        value: '短期储蓄存款',
                        label: '短期储蓄存款'
                    },{
                        value: '长期储蓄存款',
                        label: '长期储蓄存款'
                    },{
                        value: '长期存款',
                        label: '长期存款'
                    }, {
                        value: '短期存款',
                        label: '短期存款'
                    }, {
                        value: '贴现',
                        label: '贴现'
                    }, {
                        value: '短期贷款',
                        label: '短期贷款'
                    }, {
                        value: '短期贷款-抵押贷款',
                        label: '短期贷款-抵押贷款'
                    }, {
                        value: '短期贷款-质押贷款',
                        label: '短期贷款-质押贷款'
                    }, {
                        value: '短期贷款-保证贷款',
                        label: '短期贷款-保证贷款'
                    }, {
                        value: '短期贷款-信用贷款',
                        label: '短期贷款-信用贷款'
                    }, {
                        value: '中长期贷款',
                        label: '中长期贷款'
                    }, {
                        value: '长期贷款-抵押贷款',
                        label: '长期贷款-抵押贷款'
                    }, {
                        value: '长期贷款-质押贷款',
                        label: '长期贷款-质押贷款'
                    }, {
                        value: '长期贷款-保证贷款',
                        label: '长期贷款-保证贷款'
                    }, {
                        value: '长期贷款-信用贷款',
                        label: '长期贷款-信用贷款'
                    }],
                value: '',
                value1: '短期储蓄存款',
                value2: '选项23',
                currentStageDesc:'',currentStageDesc2:'',currentStageDesc3:'',currentStageDesc4:'',currentStageDesc5:'',
                currentStageDesc6:'',currentStageDesc7:'',currentStageDesc8:'',currentStageDesc9:'',
                defaultId:'',
                statgeId:'',
                //弹出经济形势
                jjxs: false,
                //在线交流
                zxjl: false
            }
        },
        mounted:function (){
            //查询一个项目的信息
            // this.projectTableData(proid);
            //查询所有项目
            this.selectAllProjecs()
            //市场份额图表
        },
        methods: {
            //查询所有项目
            selectAllProjecs(){
                let that = this
                axios({
                    method:'get',
                    url:'queryAllProject',//相对路径即可，不用加/service
                    responseType:'json',
                }).then(function(resp){
                    let data = resp.data.data
                    that.options = [];
                    data.forEach(function(item,index){
                        let temp = {value:'',label:''}
                        temp.value = item.proId
                        temp.label = item.proName
                        that.options.push(temp)
                    })
                    that.value = data[0].proName
                    that.defaultId = data[0].proId
                    that.changeProject(that.defaultId)
                    that.selectByStageId(that.defaultId)
                    that.queryData(that.defaultId)
                    that.querySceneData()
                    that.queryMessageData()
                })
            },
            //根据项目id获取当前阶段
            selectByStageId(proId){
                let that = this
                axios({
                    method:'get',
                    url:'sysProject/selectByStageId?proId='+proId,//相对路径即可，不用加/service
                    responseType:'json',
                }).then(function(resp){
                    that.statgeId = resp.data.data
                    teacher_chart.queryMarketShareData(that,proId,that.statgeId,that.value1)
                    that.defaultId = proId
                    quertProjectEndStage(that,true)
                    //同业报告
                    that.queryPeerReport(proId,that.statgeId)
                    //资产总额柱状图
                    teacher_chart.totalAssetsData(that,proId,that.statgeId)
                    //利润总额柱状图
                    teacher_chart.teamProfitData(that,proId,that.statgeId)
                    //资产增长率
                    teacher_chart.assetGrouthRate(that,proId,that.statgeId)
                })
            },
            //切换项目
            changeProject(val){
                this.projectTableData(val)
                teacher_chart.queryMarketShareData(this,val,this.statgeId,this.value1)
                this.queryPeerReport(val,this.statgeId)
                this.queryData(val)
                this.querySceneData()
                this.queryMessageData()
            },
            projectTableData(proId){
                let that = this
                let params= new URLSearchParams()
                params.append('currentProjectId', proId)
                axios({
                    method:'get',
                    url:'queryTeam',//相对路径即可，不用加/service
                    params:params,
                }).then(function(resp){
                    if(resp.status==200){
                        that.reportData = resp.data.data
                        console.log(that.reportData)
                        // that.teamtabs =that.reportData.getKey()
                        let form=that.reportData
                        let val=null;//用来记录第一个键值对的值
                        for(let key in form){
                            val=key;
                            break;
                        }
                        that.teamtabs = val
                        that.tybgtabs = val
                    }
                })
                // axios({
                //     method:'get',
                //     url:'queryTeamData',//相对路径即可，不用加/service
                //     params:params,
                // }).then(function(resp){
                //     if(resp.status==200){
                //         let aData = resp.data.data
                //         for(let key in aData){
                //             that.teamData=aData[key]
                //             break
                //         }
                //          console.log(that.teamData.actualName)
                //     }
                // })
                 that.queryTeamData(proId,null)
                that.selectByStageId(proId)
            },
            //查询团队信息
            queryTeamData(proId,name){
                let that = this
                let params= new URLSearchParams()
                params.append('currentProjectId', proId)
                axios({
                    method:'get',
                    url:'queryTeamData',//相对路径即可，不用加/service
                    params:params,
                }).then(function(resp){
                    if(resp.status==200){
                        let aData = resp.data.data
                    console.log(name)
                        for(let key in aData){
                            if(name==null){
                                that.teamData=aData[key]
                                break
                            }
                            if(aData[key].name==name){
                                that.teamData=aData[key]
                                break
                            }
                        }
                        console.log(that.teamData.actualName)
                    }
                })
            },
            //决策查询
            queryData(proId){
                // console.log(this.activeName)
                let that = this
                let form = this.form
                let params= new URLSearchParams()
                params.append('proId', proId)
                axios({
                    method:'get',
                    url:'tDecisionSummary/queryDataIndex',//相对路径即可，不用加/service
                    params:params,
                    responseType:'json',
                }).then(function(resp){
                    if(resp.status==200){
                        let data = resp.data
                        that.form = data.data
                        // console.log("form=", resp)
                        that.tableData = data.data
                          // console.log("form=",that.tableData)
                    }
                })
            },
            //场景查询
            querySceneData(){
                let that = this
                let form = this.form
                let params= new URLSearchParams()
                axios({
                    method:'get',
                    url:'tSceneManagement/queryDataIndex',//相对路径即可，不用加/service
                    params:params,
                    responseType:'json',
                }).then(function(resp){
                    if(resp.status==200){
                        that.shichangData= resp.data.data[0]
                        that.tufaData= resp.data.data[1]
                        that.fubenData= resp.data.data[2]
                        that.fengxianData= resp.data.data[3]
                        // console.log("that.shichangData=", that.shichangData)
                    }
                })
            },
            //新增场景
            release(releaseType){
                window.location.href ='js_xzcj?releaseType='+releaseType
            },
            //系统消息查询
            queryMessageData(){
                let that = this
                let form = this.form
                let params= new URLSearchParams()
                axios({
                    method:'get',
                    url:'tMessage/queryMessageData',//相对路径即可，不用加/service
                    params:params,
                    responseType:'json',
                }).then(function(resp){
                    if(resp.status==200){
                        that.messageData= resp.data.data
                    }
                })
            },
            //市场份额阶段切换
            scfeStageChange(val){
                let type = this.$refs['serviceType'].selected.value
                teacher_chart.queryMarketShareData(this,this.defaultId,val,type);
            },
            //市场份额业务类型切换
            typeChanged(val) {
                this.currentStageId =this.statgeId
                let statgeId = this.statgeId
                // console.log(statgeId)
                // console.log(this.currentStageId)
                // console.log(val)

                var numReg = /^[0-9]*$/
                var numRe = new RegExp(numReg)
                //如果不是数字的话，说明是页面加载时初始化的数据，则statgeId取当前的阶段id
                if (!numRe.test(statgeId)) {
                    statgeId = this.currentStageId
                }
                teacher_chart.queryMarketShareData(this,this.defaultId,statgeId,val);
            },
            //同业报告阶段切换
            tybgStageChange(val){

                this.queryPeerReport(this.defaultId,val);
            },
            //当前项目某阶段所有银行的同业报告
            queryPeerReport(proId,statgeId){
                let that = this
                let params= new URLSearchParams()
                params.append('currentProjectId', proId)
                params.append('currentStageId', statgeId-1)
                axios({
                    method:'get',
                    url:'query/teamPeerReport',//相对路径即可，不用加/service
                    params:params,
                }).then(function(resp){
                    if(resp.status==200){
                        that.tybgData = resp.data.data
                        // console.log(that.reportData)
                    }
                })
            },
            //tab
            formatter(row, column) {
                return row.address;
            },
            handleClick(tab, event) {
                this.queryTeamData(this.defaultId,tab.props.name)
                console.log(tab.props.name);
            },
        }
    }
;const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");

