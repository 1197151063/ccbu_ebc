import {loading_open,loading_close,quertProjectEndStage} from "../util/util.js";
var Main = {
    data () {
        return{
            //提交警告
            gmtybg: false,
            reportData:{
            },
            boxData:{
            },
            projectId:"",
            checkVal:[],
            buyData:[],
            buyAlert:false,
            form:[]
        }
    },
    methods: {
        //当前项目某阶段所有银行的同业报告
        queryPeerReport(flag){
            let proId = this.$refs['projectId'].attrs.value
            this.gmtybg = flag;
            let that = this
            let params= new URLSearchParams()
            params.append('projectId', proId)
            axios({
                method:'get',
                url:'teamsPeerReport',//相对路径即可，不用加/service
                params:params,
            }).then(function(resp){
                if(resp.status==200){
                    that.reportData = resp.data.data.teamStages
                    that.buyData=resp.data.data.buyList
                    for (let i = 0; i < that.buyData.length; i++) {
                        that.form.push(that.buyData[i])
                    }
                    // console.log(that.form);
                }
            })
            that.form=[];
        },
        buy(teamId) {
            if (this.checkVal.length == 0) {
                let message = '您未选中任何报告'
                let type = "success"
                this.$message({
                    showClose: true,
                    message: message,
                    type: type
                });
            } else
                {
                let that = this
                let teamIdVar = teamId + "";
                let proId = this.$refs['projectId'].attrs.value
                    let buyStages = this.checkVal;
                    //let params= new URLSearchParams()
                    //params.append('checkVal', buyStages)
                    let flag = true
                    for (let i = 0; i < that.buyData.length; i++) {
                        for (let a in buyStages) {
                            if (that.buyData[i].teamId == buyStages[a].split("A")[0] && that.buyData[i].stageId == buyStages[a].split("A")[1]) {
                                let name = that.buyData[i].teamName
                                let stage = (that.buyData[i].stageId) - 1
                                let message1 = '您已购买' + name + stage + "阶段,请重新选择"
                                let type = "success"
                                that.$message({
                                    showClose: true,
                                    message: message1,
                                    type: type
                                });
                                flag = false
                            }
                            break
                        }
                        if(flag==false){
                            return
                        }
                    }
                that.$confirm('此操作购买每份同业报告会扣款十亿, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    if (flag) {
                        axios({
                            method: 'post',
                            url: 'saveTeamsPeerReportRecord',//相对路径即可，不用加/service
                            data: {
                                projectId: proId,
                                teamId: teamIdVar,
                                checkVal: buyStages
                            },
                            responseType: 'json',
                        }).then(function (resp) {
                            if (resp.status == 200) {
                                // console.log(resp)
                                let message1 = '购买成功'
                                let type = "success"
                                that.$message({
                                    showClose: true,
                                    message: message1,
                                    type: type
                                });
                                that.queryPeerReport(false)
                            }
                        })
                    }

                }).catch(() => {
                    that.$message({
                        type: 'info',
                        message: '已取消购买'
                    });
                });
            }
        }
    },
    // 页面加载时请求数据
    mounted:function () {
        this.queryPeerReport(false);
    }
}
const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");