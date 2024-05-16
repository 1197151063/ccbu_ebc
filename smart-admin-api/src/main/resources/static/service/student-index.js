import {quertProjectEndStage} from "../util/util.js";
import student_chart from "../util/student_chart.js";
var Main = {
    data () {
    return {
        currentStageId:'',
        currentStageDesc:'',currentStageDesc2:'',currentStageDesc3:'',currentStageDesc4:'',currentStageDesc5:'',
        currentStageDesc6:'',currentStageDesc7:'',currentStageDesc8:'',currentStageDesc9:'',
        teamMembers:[],//团队成员
        teamLeader:{name:'',id:''},//团队负责人
        circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
        squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
        sizeList: ["large", "medium", "small"],
        people: {id:'',actualName:'',phone:'',email:'',loginPwd:''},
        userPhoto: {id:'', userId:'',userPhoto:''},
        teamInfo:{id:'',name:'',shortName:'',logo:''},
        roleList:[],
        marketShareData:[],//市场份额图标
        tableData:[],
        //tabs
        tybgtabs: '大名银行1',
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
            //导航
            activeIndex: '1',
            //个人信息
            people_model: false,
            team_model: false,
            //弹出经济形势
            economic_model: false,
            // //弹出视频
            // tcsp: false,
            // //突发预警
            // tfyj: false,
            // //在线交流
            // zxjl: false,
            //欢迎界面
            hyjm: false,
            defaultImageUrl: '../images/user_photo.png',
            defaultTeamLogo:'../photo/team_logo1.jpg',
            activeName: 'first',
            //同业报告
            reportData:{},
            ranking_model:false,
            rankingData:[],
            //经济形势
            economicData:{}
        }
    },
    mounted:function(){
        //动态获取当前正在进行阶段
        this.currentStageId = this.$refs['currentStageId'].attrs.value
        let proId = this.$refs['projectId'].attrs.value
        let teamId = this.$refs['teamId'].attrs.value
        //动态生成可选阶段列表
        quertProjectEndStage(this,true)
        let that = this;
        //获取团队基本信息
        this.queryTeamInfo()
        this.queryTeamMember()
        //市场份额图表
        student_chart.queryMarketShareData(this,proId,this.currentStageId,this.value1)
        //同业报告
        this.queryPeerReport(proId,this.currentStageId)
        //员工满意程度
        student_chart.degreeOfSatisfied(this,teamId,this.currentStageId)
        //市场营销
        student_chart.marketingManagement(this,teamId,this.currentStageId)
        //员工分配情况
        this.queryPersonnelAllotment(teamId,this.currentStageId)
        //资产总额柱状图
        student_chart.totalAssetsData(this,proId,this.currentStageId)
        //利润总额柱状图
        student_chart.teamProfitData(this,proId,this.currentStageId)
        //资产增长率
        student_chart.assetGrouthRate(this,proId,this.currentStageId)
        //股价图
        student_chart.stockPriceChart(this,proId,this.currentStageId)
    },
    methods: {
        //打开本期排行榜
        openRankingModel(){
            this.ranking_model=true
            let proId = this.$refs['projectId'].attrs.value
            let that = this
            let params= new URLSearchParams()
            params.append('proId', proId)
            params.append('currentStageId', this.currentStageId)
            axios({
                method:'get',
                url:'query/projectRankingList',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                that.rankingData = resp.data.data
                // console.log(that.rankingData)
            })
        },
        //获取当前团队的成员信息
        queryTeamMember(){
            let teamName = this.$refs['teamName'].attrs.value
            // let teamName = this.teamInfo.name
            let that = this
            let params= new URLSearchParams()
            params.append('departmentName', teamName)
            axios({
                method:'get',
                url:'query/listEmployeeByDepartmentName',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.data.data!=null){
                   that.teamMembers = resp.data.data[0].children[0].employees
                    that.teamLeader.name =  resp.data.data[0].children[0].managerName
                    that.teamLeader.id =  resp.data.data[0].children[0].managerId
                }
            })
        },
        //打开团队基本信息对话框
        openTeamDialog(){
            //判断操作人是否是管理者
            let curUser = this.$refs['employeeId'].attrs.value
            if(curUser == this.teamLeader.id){
                this.team_model = true
                this.queryAllRoleList()
            }
        },
        //查询团队基本信息
        queryTeamInfo(){
            let that = this
            let teamId = this.$refs['teamId'].attrs.value
            axios({
                method:'get',
                url:'department/query/'+teamId,//相对路径即可，不用加/service
                responseType:'json',
            }).then(function(resp){
                if(resp.data.data!=null){
                    that.teamInfo = resp.data.data
                    that.hyjm = true
                    that.tybgtabs = that.teamInfo.name
                }
                // that.tybgtabs = '至诚银行'
            })
        },
        //打开用户基本信息对话框，并查询用户基本信息
        openUserDialog(){
            this.people_model = true
            let id = this.$refs['employeeId'].attrs.value
            let that = this
            let params= new URLSearchParams()
            params.append('id', id)
            axios({
                method:'get',
                url:'user/queryById',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.data.data!=null){
                    that.people = resp.data.data
                    // console.log(that.people)
                }
            })
            axios({
                method:'get',
                url:'userPhoto/queryById',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                if(resp.data.data!=null){
                    that.userPhoto = resp.data.data
                    that.imageUrl = "../upload/"+resp.data.data.userPhoto
                }
            })
        },
        //打开市场经济形势
        openEconomicSituation(){
            this.economic_model = true
            this.currentStageId = this.$refs['currentStageId'].attrs.value
            let currentStageId = this.currentStageId
            let params= new URLSearchParams()
            params.append('currentStageId', currentStageId)
            let that = this
            axios({
                method:'get',
                url:'parEconomicSituation/queryCurrentStageEconomy',//相对路径即可，不用加/service
                params:params,
                responseType:'json',
            }).then(function(resp){
                let data = resp.data.data
                that.economicData = data
                // console.log(that.economicData)
            })
        },
        //个人用户头像上传成功
        uploadSuccess(response, file, fileList){
            //返回自定义的图片名称
            let that = this
            let imageUrl = response.data
            imageUrl = "../upload/"+imageUrl
            this.userPhoto.userId = this.people.id;
            this.userPhoto.userPhoto = imageUrl
            axios({
                method:'post',
                url:'userPhoto/add',//相对路径即可，不用加/service
                data:this.userPhoto,
                responseType:'json',
            }).then(function(resp){
                //返回最新的头像信息
                that.userPhoto = resp.data.data
                that.$message({
                    type:'success',
                    message:'头像上传成功'
                })
            })
        },
        //团队logo上传成功
        teamUploadSuccess(response, file, fileList){
            //返回自定义的图片名称
            let that = this
            let imageUrl = response.data
            this.teamInfo.logo = imageUrl;
            this.updateTeamInfo()
        },
        //更新团队信息
        updateTeamInfo(){
            let that = this
            axios({
                method:'post',
                url:'department/update',//相对路径即可，不用加/service
                data:this.teamInfo,
                responseType:'json',
            }).then(function(resp){
                //返回最新的头像信息
                that.$message({
                    type:'success',
                    message:'更新成功'
                })
            })
        },
        //图片上传前
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg'
            const isPNG = file.type === 'image/png'
            const isLt10M = file.size / 1024 / 1024 < 50
            if (!isJPG && !isPNG) {
                this.$message.error('图片格式错误')
            }
            if (!isLt10M) {
                this.$message.error('图片大小不能超过10M!')
            }
            return (isJPG || isPNG) && isLt10M
        },
        //保存用户信息
        saveUserInfo(){
            let id = this.$refs['employeeId'].attrs.value
            let that = this
            axios({
                method:'post',
                url:'user/update',//相对路径即可，不用加/service
                data:this.people,
            }).then(function(resp){
                if(resp.status==200){
                    that.$message({
                        type: 'success',
                        message: '保存成功'
                    })
                }
            })
        },
        //获取所有的角色列表
        queryAllRoleList(){
            let that = this
            axios({
                method:'get',
                url:'role/getAll'
            }).then(function(resp){
                if(resp.status==200){
                    that.roleList = resp.data.data
                }
            })
        },
        //设置用户角色
        setMemberRole(val,row){
            let that = this
            let userId = row.id
            let roleName = val
            let user = {id:userId,remark:roleName,departmentId:row.departmentId}
            axios({
                method:'post',
                url:'user/update',//相对路径即可，不用加/service
                data:user,
            }).then(function(resp){
                if(resp.status==200){
                    that.$message({
                        type: 'success',
                        message: '设置成功'
                    })
                }
            })
        },
        formatter(row, column) {
            return row.address;
        },
        handleClick(tab, event) {
            // console.log(tab, event);
        },
        //市场份额业务类型切换
        typeChanged(val) {
            this.currentStageId = this.$refs['currentStageId'].attrs.value
            let proId = this.$refs['projectId'].attrs.value
            let statgeId = this.$refs['scfeSelect'].selected.value
            var numReg = /^[0-9]*$/
            var numRe = new RegExp(numReg)
            //如果不是数字的话，说明是页面加载时初始化的数据，则statgeId取当前的阶段id
            if (!numRe.test(statgeId)) {
                statgeId = this.currentStageId
            }
            student_chart.queryMarketShareData(this,proId,statgeId,val);
        },
        //同业报告阶段切换
        tybgStageChange(val){
            let proId = this.$refs['projectId'].attrs.value
            let type = this.$refs['serviceType'].selected.value
            this.queryPeerReport(proId,val);
        },
        //市场份额阶段切换
        scfeStageChange(val){
            let proId = this.$refs['projectId'].attrs.value
            let type = this.$refs['serviceType'].selected.value
            student_chart.queryMarketShareData(this,proId,val,type);
        },
        //人员满意度阶段切换
        mycdStageChange(val){
            let teamId = this.$refs['teamId'].attrs.value
            student_chart.degreeOfSatisfied(this,teamId,val)
        },
        //市场营销阶段切换
        scyxStageChange(val){
            let teamId = this.$refs['teamId'].attrs.value
            student_chart.marketingManagement(this,teamId,val)
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
                    that.reportData = resp.data.data
                    // console.log(that.reportData)
                }
            })
        },
        //查询某阶段人力资源分配情况
        queryPersonnelAllotment(teamId,stageId){
            let that = this
            let params= new URLSearchParams()
            params.append('teamId', teamId)
            params.append('stageId', stageId)
            axios({
                method:'get',
                url:'query/personnelAllot',//相对路径即可，不用加/service
                params:params,
            }).then(function(resp){
                if(resp.status==200){
                    that.tableData = resp.data.data
                }
            })
        },
        //人员分配阶段切换
        ryfpStageChange(val){
            let teamId = this.$refs['teamId'].attrs.value
            this.queryPersonnelAllotment(teamId,val)
        }
    }
}
;const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");
//     var optionxj
//     var chartDom
//     var myChart
//
//     //市场营销  雷达图
//     chartDom = document.getElementById('scyx');
//     myChart = echarts.init(chartDom);
//     var option = {
//     //提示框
//     tooltip: {
//     trigger: 'axis'
// },
//     //图例
//     legend: {
//     orient: 'vertical',
//     x: '400',
//     y: 'center',
//     align: 'left',
//     itemWidth:12,
//     itemHeight:12,
//     data: ['短期贷款', '中长期贷款', '存款', '托管业务', '担保业务']
// },
//     //颜色
//     color:['rgba(253,115,103,1)','rgba(248,190,74,1)','rgba(90,216,166,1)','rgba(81,156,232,1)','rgba(191,128,255,1)'],
//     //雷达坐标系
//     radar: [
// {
//     indicator: [
// { name: '<35岁', max: 10},
// { name: '35-55岁', max: 10},
// { name: '>55岁', max: 10},
// { name: '小型企业', max: 10},
// { name: '中型企业', max: 10},
// { name: '大型企业', max: 10},
// { name: '公共机构', max: 10}
//     ],
//     radius : '60%',
//     center: ['35%', '50%'],
//     axisLine:{
//     lineStyle:{
//     color:['rgba(0,0,0,0.1)']
// }
// },
//     name:{
//     show:true,
//     color:['rgba(0,0,0,0.7)']
// },
//     //分隔线
//     splitLine:{
//     lineStyle:{
//     color:['rgba(0,0,0,0.1)']
// }
// },
//     //分隔区域
//     splitArea:{
//     show:false
// }
// }
//     ],
//     series: [
// {
//     type: 'radar',
//     areaStyle: {
//     color:['rgba(255,255,255,0.3)']
// },
//     data: [
// {
//     name: '短期贷款',
//     value: [2, 4, 5, 7, 6, 4, 6],
//     symbolSize:2
// },
// {
//     name: '中长期贷款',
//     value: [9, 10, 7, 5, 6, 7, 4],
//     symbolSize:2
// },
// {
//     name: '存款',
//     value: [2, 7, 3, 5, 4, 3, 3],
//     symbolSize:2
// },
// {
//     name: '托管业务',
//     value: [3, 5, 4, 3, 6, 8, 3],
//     symbolSize:2
// },
// {
//     name: '担保业务',
//     value: [3, 4, 3, 6, 3, 4, 9],
//     symbolSize:2
// }
//     ]
// }
//     ]
// };
//     option && myChart.setOption(option);



