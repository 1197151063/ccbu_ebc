import {quertProjectEndStage} from "../util/teacher_util.js";
import teacher_chart from "../util/teacher_chart.js";

var Main = {
        data() {
            return {
                //头像
                circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
                sizeList: ["large", "medium", "small"],
                //导航
                activeIndex: '4',
                //tabs
                activeName: 'first',
                //tabs
                // teamtabs: '',
                // tybgtabs: 'first',
                // zsktabs: 'first',
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
                dialogTableVisible: false
            }
        },
        mounted: function () {
            //查询所有项目
            this.selectAllProjecs()
        },
        methods: {
            //查询所有项目
            selectAllProjecs() {
                let that = this
                axios({
                    method: 'get',
                    url: 'queryAllProject',//相对路径即可，不用加/service
                    responseType: 'json',
                }).then(function (resp) {
                    let data = resp.data.data
                    that.options = [];
                    data.forEach(function (item, index) {
                        let temp = {value: '', label: ''}
                        temp.value = item.proId
                        temp.label = item.proName
                        that.options.push(temp)
                    })
                    that.value = data[0].proName
                    that.defaultId = data[0].proId
                    // that.changeProject(that.defaultId)
                    that.selectByStageId(that.defaultId)
                })
            },
            //根据项目id获取当前阶段
            selectByStageId(proId) {
                let that = this
                axios({
                    method: 'get',
                    url: 'sysProject/selectByStageId?proId=' + proId,//相对路径即可，不用加/service
                    responseType: 'json',
                }).then(function (resp) {
                    that.statgeId = resp.data.data
                    teacher_chart.queryMarketShareData(that, proId, that.statgeId, that.value1)
                    that.defaultId = proId
                    quertProjectEndStage(that, true)
                    //资产总额柱状图
                    teacher_chart.totalAssetsData(that, proId, that.statgeId)
                    //利润总额柱状图
                    teacher_chart.teamProfitData(that, proId, that.statgeId)
                    //资产增长率
                    teacher_chart.assetGrouthRate(that, proId, that.statgeId)
                    //股价图
                    teacher_chart.stockPriceChart(that,proId,that.statgeId)
                    //员工满意程度
                    teacher_chart.degreeOfSatisfied(that,proId,that.statgeId)
                    //市场营销
                    teacher_chart.marketingManagement(that,proId,that.statgeId)
                })
            },
            //切换项目
            changeProject(val) {
                this.projectTableData(val)
                teacher_chart.queryMarketShareData(this, val, this.statgeId, this.value1)
                console.log(this)
            },
            projectTableData(proId) {
                let that = this
                let params = new URLSearchParams()
                params.append('currentProjectId', proId)
                axios({
                    method: 'get',
                    url: 'queryTeam',//相对路径即可，不用加/service
                    params: params,
                }).then(function (resp) {
                    if (resp.status == 200) {
                        that.reportData = resp.data.data
                        // console.log(that.reportData)
                        // that.teamtabs =that.reportData.getKey()
                        let form = that.reportData
                        let val = null;//用来记录第一个键值对的值
                        for (let key in form) {
                            val = key;
                            break;
                        }
                        that.teamtabs = val
                        that.tybgtabs = val
                    }
                })
                that.selectByStageId(proId)
            },
            //市场份额阶段切换
            scfeStageChange(val) {
                let type = this.$refs['serviceType'].selected.value
                teacher_chart.queryMarketShareData(this, this.defaultId, val, type);
            },
            //市场份额业务类型切换
            typeChanged(val) {
                this.currentStageId = this.statgeId
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
                teacher_chart.queryMarketShareData(this, this.defaultId, statgeId, val);
            },
            //人员满意度阶段切换
            mycdStageChange(val){
                let teamId = this.$refs['teamId'].attrs.value
                teacher_chart.degreeOfSatisfied(this,teamId,val)
            },
            //市场营销阶段切换
            scyxStageChange(val){
                let teamId = this.$refs['teamId'].attrs.value
                teacher_chart.marketingManagement(this,teamId,val)
            },
            //tab
            formatter(row, column) {
                return row.address;
            },
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }
    }
;const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");


// //图表
//
// //各年度银行股份  拆线图
//
// chartDom = document.getElementById('yhgj');
// myChart = echarts.init(chartDom);
// option = {
//     //图例
//     legend: {
//         orient: 'horizontal',
//         x: 'center',
//         y: 'bottom',
//         align: 'left',
//         padding: [
//             0,
//             0,
//             30,
//             0,
//         ]
//     },
//     //提示框
//     tooltip: {
//         trigger: 'axis',
//         backgroundColor: 'rgba(255,255,255,0.9)',
//         padding: [
//             15,
//             20,
//             15,
//             20,
//         ],
//         textStyle: {
//             color: '#333',
//             fontSize: 13
//         },
//         extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);'
//     },
//     //坐标系网格
//     grid: {
//         top: '40px',
//         right: '30px',
//         bottom: '100px',
//         left: '60px'
//     },
//     //颜色
//     color: ['rgba(253,115,103,1)', 'rgba(248,190,74,1)', 'rgba(90,216,166,1)', 'rgba(81,156,232,1)'],
//     //x轴
//     xAxis: {
//         type: 'category',
//         //轴线
//         axisLine: {
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         boundaryGap: true,
//         //刻度
//         axisTick: {
//             show: true,
//             alignWithLabel: true,
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         //刻度标签
//         axisLabel: {
//             show: true,
//             interval: 0,
//             color: '#555',
//             rotate: 0
//         },
//         data: ['2014', '2015', '2016', '2017']
//     },
//     //y轴
//     yAxis: {
//         type: 'value',
//         //轴线
//         axisLine: {
//             show: false,
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         //刻度
//         axisTick: {
//             show: false,
//             lineStyle: {
//                 color: '#e6e6e6'
//             }
//         },
//         //刻度标签
//         axisLabel: {
//             color: '#555'
//         },
//         splitLine: {
//             lineStyle: {
//                 color: ['#e6e6e6', '#f2f2f2'],
//                 type: 'dotted'
//             }
//         }
//     },
//     //图表
//     series: [
//         {
//             name: '中国龙队银行',
//             type: 'line',
//             data: [11.3, 27.8, 25.2, 40.7],
//             symbol: 'circle',
//             symbolSize: 2
//         },
//         {
//             name: '虎队银行',
//             type: 'line',
//             data: [27.2, 17.6, 18.1, 28.6],
//             symbol: 'circle',
//             symbolSize: 2
//         },
//         {
//             name: '全球鹰队银行',
//             type: 'line',
//             data: [14.8, 15.1, 15.3, 25.8],
//             symbol: 'circle',
//             symbolSize: 2
//         },
//         {
//             name: '豹队银行',
//             type: 'line',
//             data: [28.6, 28.8, 19.0, 25.2],
//             symbol: 'circle',
//             symbolSize: 2
//         }
//     ]
// };
// option && myChart.setOption(option);
//
//
// //市场占比 饼状图
// var chartDom = document.getElementById('sczb');
// var myChart = echarts.init(chartDom);
// var option;
// option = {
//     tooltip: {
//         trigger: 'item',
//         formatter: "{a} <br/>{b} : {c} ({d}%)"
//     },
//     //图例
//     legend: {
//         orient: 'vertical',
//         x: '70%',
//         y: '30%',
//         align: 'left',
//         itemWidth: 16,
//         itemHeight: 16,
//         data: ['中国龙队银行', '虎队银行', '全球鹰队银行', '豹队银行']
//     },
//     //标签
//     label: {
//         formatter: '{d}%'
//     },
//     //提示框
//     tooltip: {
//         trigger: 'item',
//         backgroundColor: 'rgba(255,255,255,0.9)',
//         borderColor: 'rgba(0,0,0,0.1)',
//         borderWidth: 0,
//         padding: [
//             15,
//             20,
//             15,
//             20,
//         ],
//         textStyle: {
//             color: '#333',
//             fontSize: 13
//         },
//         extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);'
//     },
//     //颜色
//     color: ['rgba(253,115,103,0.8)', 'rgba(248,190,74,0.8)', 'rgba(90,216,166,0.8)', 'rgba(81,156,232,0.8)'],
//     series: [
//         {
//             name: '资产总值',
//             type: 'pie',
//             radius: '60%',
//             center: ['40%', '45%'],
//             data: [
//                 {value: 17.21, name: '中国龙队银行'},
//                 {value: 26.92, name: '虎队银行'},
//                 {value: 37.03, name: '全球鹰队银行'},
//                 {value: 29.17, name: '豹队银行'}
//             ],
//             itemStyle: {
//                 borderType: 'solid',
//                 borderWidth: 1
//             }
//         }
//     ]
// };
// option && myChart.setOption(option);
//
//
// //资产总额   柱状图
//
// chartDom = document.getElementById('zcze');
// myChart = echarts.init(chartDom);
// option = {
//     //图例
//     legend: {
//         orient: 'horizontal',
//         x: 'center',
//         y: 'bottom',
//         align: 'left',
//         padding: [
//             0,
//             0,
//             25,
//             0,
//         ],
//         itemWidth: 16,
//         itemHeight: 16
//     },
//     tooltip: {},
//     //坐标系网格
//     grid: {
//         top: '40px',
//         right: '60px',
//         bottom: '100px',
//         left: '60px'
//     },
//     //提示框
//     tooltip: {
//         trigger: 'axis',
//         backgroundColor: 'rgba(255,255,255,0.9)',
//         padding: [
//             15,
//             20,
//             15,
//             20,
//         ],
//         textStyle: {
//             color: '#333',
//             fontSize: 13
//         },
//         extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);'
//     },
//     //数值
//     dataset: {
//         source: [
//             ['product', '中国龙队银行', '虎队银行', '全球鹰队银行', '豹队银行',],
//             ['第一年度', 82, 84, 81, 69],
//             ['第二年度', 167, 139, 160, 157],
//             ['第三年度', 248, 231, 241, 220],
//             ['第四年度', 386, 319, 356, 275]
//         ]
//     },
//     //x轴
//     xAxis: {
//         type: 'category',
//         //轴线
//         axisLine: {
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         //刻度
//         axisTick: {
//             show: false,
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         //刻度标签
//         axisLabel: {
//             show: true,
//             interval: 0,
//             color: '#555',
//             rotate: 0
//         }
//     },
//     //y轴
//     yAxis: {
//         type: 'value',
//         //轴线
//         axisLine: {
//             show: false,
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         //刻度
//         axisTick: {
//             show: false,
//             lineStyle: {
//                 color: '#e6e6e6'
//             }
//         },
//         //刻度标签
//         axisLabel: {
//             color: '#555'
//         },
//         splitLine: {
//             lineStyle: {
//                 color: ['#e6e6e6', '#f2f2f2'],
//                 type: 'dotted'
//             }
//         }
//     },
//     //图表
//     series: [
//         {
//             type: 'bar',
//             itemStyle: {
//                 color: 'rgba(253,115,103,0.8)',
//                 borderColor: 'rgba(253,115,103,1)',
//                 borderType: 'solid',
//                 borderWidth: 1
//             }
//         },
//         {
//             type: 'bar',
//             barGap: '5%',
//             itemStyle: {
//                 color: 'rgba(248,190,74,0.8)',
//                 borderColor: 'rgba(248,190,74,1)',
//                 borderType: 'solid',
//                 borderWidth: 1
//             }
//         },
//         {
//             type: 'bar',
//             barGap: '5%',
//             itemStyle: {
//                 color: 'rgba(90,216,166,0.8)',
//                 borderColor: 'rgba(90,216,166,1)',
//                 borderType: 'solid',
//                 borderWidth: 1
//             }
//         },
//         {
//             type: 'bar',
//             barGap: '5%',
//             itemStyle: {
//                 color: 'rgba(81,156,232,0.8)',
//                 borderColor: 'rgba(81,156,232,1)',
//                 borderType: 'solid',
//                 borderWidth: 1
//             }
//         }
//     ]
// };
// option && myChart.setOption(option);
//
//
// //资产增长率  拆线图
//
// chartDom = document.getElementById('zczzl');
// myChart = echarts.init(chartDom);
// option = {
//     //图例
//     legend: {
//         orient: 'horizontal',
//         x: 'center',
//         y: 'bottom',
//         align: 'left',
//         padding: [
//             0,
//             0,
//             30,
//             0,
//         ]
//     },
//     //提示框
//     tooltip: {
//         trigger: 'axis',
//         backgroundColor: 'rgba(255,255,255,0.9)',
//         padding: [
//             15,
//             20,
//             15,
//             20,
//         ],
//         textStyle: {
//             color: '#333',
//             fontSize: 13
//         },
//         extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);'
//     },
//     //坐标系网格
//     grid: {
//         top: '40px',
//         right: '30px',
//         bottom: '100px',
//         left: '60px'
//     },
//     //颜色
//     color: ['rgba(253,115,103,1)', 'rgba(248,190,74,1)', 'rgba(90,216,166,1)', 'rgba(81,156,232,1)'],
//     //x轴
//     xAxis: {
//         type: 'category',
//         //轴线
//         axisLine: {
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         boundaryGap: true,
//         //刻度
//         axisTick: {
//             show: true,
//             alignWithLabel: true,
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         //刻度标签
//         axisLabel: {
//             show: true,
//             interval: 0,
//             color: '#555',
//             rotate: 0
//         },
//         data: ['2014', '2015', '2016', '2017']
//     },
//     //y轴
//     yAxis: {
//         type: 'value',
//         //轴线
//         axisLine: {
//             show: false,
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         //刻度
//         axisTick: {
//             show: false,
//             lineStyle: {
//                 color: '#e6e6e6'
//             }
//         },
//         //刻度标签
//         axisLabel: {
//             color: '#555'
//         },
//         splitLine: {
//             lineStyle: {
//                 color: ['#e6e6e6', '#f2f2f2'],
//                 type: 'dotted'
//             }
//         }
//     },
//     //图表
//     series: [
//         {
//             name: '中国龙队银行',
//             type: 'line',
//             data: [11.3, 27.8, 25.2, 40.7],
//             symbol: 'circle',
//             symbolSize: 2
//         },
//         {
//             name: '虎队银行',
//             type: 'line',
//             data: [27.2, 17.6, 18.1, 28.6],
//             symbol: 'circle',
//             symbolSize: 2
//         },
//         {
//             name: '全球鹰队银行',
//             type: 'line',
//             data: [14.8, 15.1, 15.3, 25.8],
//             symbol: 'circle',
//             symbolSize: 2
//         },
//         {
//             name: '豹队银行',
//             type: 'line',
//             data: [28.6, 28.8, 19.0, 25.2],
//             symbol: 'circle',
//             symbolSize: 2
//         }
//     ]
// };
// option && myChart.setOption(option);
//
//
// //利润总额  柱状图
//
// chartDom = document.getElementById('lrze');
// myChart = echarts.init(chartDom);
// option = {
//     //图例
//     legend: {
//         orient: 'horizontal',
//         x: 'center',
//         y: 'bottom',
//         align: 'left',
//         padding: [
//             0,
//             0,
//             25,
//             0,
//         ],
//         itemWidth: 16,
//         itemHeight: 16
//     },
//     tooltip: {},
//     //坐标系网格
//     grid: {
//         top: '40px',
//         right: '50px',
//         bottom: '80px',
//         left: '50px'
//     },
//     //提示框
//     tooltip: {
//         trigger: 'axis',
//         backgroundColor: 'rgba(255,255,255,0.9)',
//         padding: [
//             15,
//             20,
//             15,
//             20,
//         ],
//         textStyle: {
//             color: '#333',
//             fontSize: 13
//         },
//         extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);'
//     },
//     //数值
//     dataset: {
//         source: [
//             ['product', '中国龙队银行', '虎队银行', '全球鹰队银行', '豹队银行',],
//             ['第一年度', 8.2, 8.4, 8.1, 6.9],
//             ['第二年度', 16.7, 23.9, 26.0, 15.7],
//             ['第三年度', 24.8, 33.1, 34.1, 32.0],
//             ['第四年度', 38.6, 41.9, 45.6, 37.5]
//         ]
//     },
//     //x轴
//     xAxis: {
//         type: 'category',
//         //轴线
//         axisLine: {
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         //刻度
//         axisTick: {
//             show: false,
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         //刻度标签
//         axisLabel: {
//             show: true,
//             interval: 0,
//             color: '#555',
//             rotate: 0
//         }
//     },
//     //y轴
//     yAxis: {
//         type: 'value',
//         //轴线
//         axisLine: {
//             show: false,
//             lineStyle: {
//                 color: '#ccc'
//             }
//         },
//         //刻度
//         axisTick: {
//             show: false,
//             lineStyle: {
//                 color: '#e6e6e6'
//             }
//         },
//         //刻度标签
//         axisLabel: {
//             color: '#555'
//         },
//         splitLine: {
//             lineStyle: {
//                 color: ['#e6e6e6', '#f2f2f2'],
//                 type: 'dotted'
//             }
//         }
//     },
//     //图表
//     series: [
//         {
//             type: 'bar',
//             itemStyle: {
//                 color: 'rgba(253,115,103,0.8)',
//                 borderColor: 'rgba(253,115,103,1)',
//                 borderType: 'solid',
//                 borderWidth: 1
//             }
//         },
//         {
//             type: 'bar',
//             barGap: '5%',
//             itemStyle: {
//                 color: 'rgba(248,190,74,0.8)',
//                 borderColor: 'rgba(248,190,74,1)',
//                 borderType: 'solid',
//                 borderWidth: 1
//             }
//         },
//         {
//             type: 'bar',
//             barGap: '5%',
//             itemStyle: {
//                 color: 'rgba(90,216,166,0.8)',
//                 borderColor: 'rgba(90,216,166,1)',
//                 borderType: 'solid',
//                 borderWidth: 1
//             }
//         },
//         {
//             type: 'bar',
//             barGap: '5%',
//             itemStyle: {
//                 color: 'rgba(81,156,232,0.8)',
//                 borderColor: 'rgba(81,156,232,1)',
//                 borderType: 'solid',
//                 borderWidth: 1
//             }
//         }
//     ]
// };
// option && myChart.setOption(option);
//
//
// //市场营销  雷达图
// chartDom = document.getElementById('scyx');
// myChart = echarts.init(chartDom);
// option = {
//     //提示框
//     tooltip: {
//         trigger: 'axis'
//     },
//     //图例
//     legend: {
//         orient: 'vertical',
//         x: '70%',
//         y: '30%',
//         align: 'left',
//         itemWidth: 16,
//         itemHeight: 16,
//         data: ['短期贷款', '中长期贷款', '存款', '托管业务', '担保业务']
//     },
//     //颜色
//     color: ['rgba(253,115,103,1)', 'rgba(248,190,74,1)', 'rgba(90,216,166,1)', 'rgba(81,156,232,1)', 'rgba(191,128,255,1)'],
//     //雷达坐标系
//     radar: [
//         {
//             indicator: [
//                 {name: '<35岁', max: 10},
//                 {name: '35-55岁', max: 10},
//                 {name: '>55岁', max: 10},
//                 {name: '小型企业', max: 10},
//                 {name: '中型企业', max: 10},
//                 {name: '大型企业', max: 10},
//                 {name: '公共机构', max: 10}
//             ],
//             radius: '60%',
//             center: ['35%', '50%'],
//             axisLine: {
//                 lineStyle: {
//                     color: ['rgba(0,0,0,0.1)']
//                 }
//             },
//             name: {
//                 show: true,
//                 color: ['rgba(0,0,0,0.7)']
//             },
//             //分隔线
//             splitLine: {
//                 lineStyle: {
//                     color: ['rgba(0,0,0,0.1)']
//                 }
//             },
//             //分隔区域
//             splitArea: {
//                 show: false
//             }
//         }
//     ],
//     series: [
//         {
//             type: 'radar',
//             areaStyle: {
//                 color: ['rgba(255,255,255,0.3)']
//             },
//             data: [
//                 {
//                     name: '短期贷款',
//                     value: [2, 4, 5, 7, 6, 4, 6],
//                     symbolSize: 2
//                 },
//                 {
//                     name: '中长期贷款',
//                     value: [9, 10, 7, 5, 6, 7, 4],
//                     symbolSize: 2
//                 },
//                 {
//                     name: '存款',
//                     value: [2, 7, 3, 5, 4, 3, 3],
//                     symbolSize: 2
//                 },
//                 {
//                     name: '托管业务',
//                     value: [3, 5, 4, 3, 6, 8, 3],
//                     symbolSize: 2
//                 },
//                 {
//                     name: '担保业务',
//                     value: [3, 4, 3, 6, 3, 4, 9],
//                     symbolSize: 2
//                 }
//             ]
//         }
//     ]
// };
// option && myChart.setOption(option);
//
//
// //人员满意度  雷达图
// chartDom = document.getElementById('rymyd');
// myChart = echarts.init(chartDom);
// option = {
//     //提示框
//     tooltip: {
//         trigger: 'axis'
//     },
//     //图例
//     legend: {
//         orient: 'vertical',
//         x: '70%',
//         y: '30%',
//         align: 'left',
//         itemWidth: 16,
//         itemHeight: 16,
//         data: ['贷款业务', '存款业务', '投资业务', '中间业务']
//     },
//     //颜色
//     color: ['rgba(253,115,103,1)', 'rgba(248,190,74,1)', 'rgba(90,216,166,1)', 'rgba(81,156,232,1)'],
//     //坐标系
//     radar: [
//         {
//             indicator: [
//                 {name: '非常满意', max: 10},
//                 {name: '满意', max: 10},
//                 {name: '一般', max: 10},
//                 {name: '不满意', max: 10},
//                 {name: '很不满意', max: 10}
//             ],
//             radius: '60%',
//             center: ['35%', '50%'],
//             axisLine: {
//                 lineStyle: {
//                     color: ['rgba(0,0,0,0.1)']
//                 }
//             },
//             name: {
//                 show: true,
//                 color: ['rgba(0,0,0,0.7)']
//             },
//             //分隔线
//             splitLine: {
//                 lineStyle: {
//                     color: ['rgba(0,0,0,0.1)']
//                 }
//             },
//             //分隔区域
//             splitArea: {
//                 show: false
//             }
//         }
//     ],
//     series: [
//         {
//             type: 'radar',
//             areaStyle: {
//                 color: ['rgba(255,255,255,0.3)']
//             },
//             data: [
//                 {
//                     name: '贷款业务',
//                     value: [2, 4, 5, 7, 6],
//                     symbolSize: 2
//                 },
//                 {
//                     name: '存款业务',
//                     value: [9, 10, 7, 5, 6],
//                     symbolSize: 2
//                 },
//                 {
//                     name: '投资业务',
//                     value: [2, 7, 3, 5, 4],
//                     symbolSize: 2
//                 },
//                 {
//                     name: '中间业务',
//                     value: [3, 5, 4, 3, 6],
//                     symbolSize: 2
//                 }
//             ]
//         }
//     ]
// };
// option && myChart.setOption(option);
//
//
// ;const app = Vue.createApp(Main);
// app.use(ElementPlus);
// app.mount("#index");

