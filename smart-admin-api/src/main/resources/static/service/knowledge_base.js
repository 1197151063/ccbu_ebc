var Main = {
        data () {
            return {
                //头像
                circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
                sizeList: ["large", "medium", "small"],
                //导航
                activeIndex: '8',
                //tabs
                activeName: 'first',
                //选择器
                options: [{
                    value: '选项1',
                    label: 'pptx'
                }, {
                    value: '选项2',
                    label: 'pnf'
                }, {
                    value: '选项3',
                    label: 'exl'
                }, {
                    value: '选项4',
                    label: 'rar'
                }, {
                    value: '选项5',
                    label: 'doc'
                }],
                value: '',
                //分页
                // pageSize:5,
                // currentPage1: 5,
                // currentPage2: 5,
                // currentPage3: 5,
                // currentPage4: 4,
                //上传文件
                fileData:[],

                // pageNum: 1,  //当前页
                pageSize: 5, //每页记录数
                total: 20, //总记录数
                form: {
                    pageNum: '1',
                    pageSize: '5',
                    total:'20'
                },

            }
        },
    mounted:function(){
        //动态获取当前正在进行阶段
        // this.currentStageId = this.$refs['currentStageId'].attrs.value
        // let proId = this.$refs['projectId'].attrs.value
        // let teamId = this.$refs['teamId'].attrs.value
        this.queryFileData()
    },
        methods: {
            //查询上传的文件
            queryFileData(fileName,actualName){
                // console.log("fileName=",fileName)
                let that = this
                let form = this.form
                form.fileName = fileName
                form.actualName = actualName
                    axios({
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    method:'post',
                    url:'api/file/query',//相对路径即可，不用加/service
                    data:form,
                    responseType:'json',
                }).then(function(resp){
                    if(resp.status==200){
                        let data = resp.data
                        // this.total = data.total
                        // console.log("data=",data)
                        that.form = data.data
                        that.fileData = data.data.list
                        // console.log("form=",that.form)
                        // console.log("fileData=",that.fileData)
                    }
                })
            },
            //删除文件
            deleteFile(id){
                let that = this
                that.$confirm('确定要删除吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {

                    let params = new URLSearchParams()
                    params.append('id', id)
                    axios({
                        method: 'get',
                        url: 'api/file/delete',//相对路径即可，不用加/service
                        params: params,
                        responseType: 'json',
                    }).then(function (resp) {
                        if (resp.status == 200) {
                            let message = '删除成功'
                            let type = "success"
                            that.$message({
                                showClose: true,
                                message: message,
                                type: type
                            });
                            that.queryFileData();
                        }
                    })
                }).catch(() => {
                });
            },
            //下载文件
            downLoad(id){
                let that = this
                    let params = new URLSearchParams()
                    params.append('id', id)
                    axios({
                        method: 'get',
                        url: 'api/file/downLoad',//相对路径即可，不用加/service
                        params: params,
                        responseType: "blob",
                        headers: {
                            'Content-Type': 'multipart/x-www-form-urlencoded'
                        }
                    }).then(function (res) {
                        console.log(res);

                        const filename = res.headers["content-disposition"];
                        const blob = new Blob([res.data]);
                        var downloadElement = document.createElement("a");
                        var href = window.URL.createObjectURL(blob);
                        downloadElement.href = href;
                        downloadElement.download = decodeURIComponent(filename.split("filename=")[1]);
                        document.body.appendChild(downloadElement);
                        downloadElement.click();
                        document.body.removeChild(downloadElement);
                        window.URL.revokeObjectURL(href);

                        if (res.status == 200) {
                            let message = '下载成功'
                            let type = "success"
                            that.$message({
                                showClose: true,
                                message: message,
                                type: type
                            });
                            that.queryFileData();
                        }
                    })
            },
            // 页码改变
            changePage(pageNum) {
                this.form.pageNum = pageNum;
                this.queryFileData();
            },
            //tab
            formatter(row, column) {
                return row.address;
            },
            handleClick(tab, event) {
                console.log(tab, event);
            },
        }
    }
;const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");

