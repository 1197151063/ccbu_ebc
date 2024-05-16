export default {
    optionxj:{},
    serials:[
        {
        type: 'bar',
        itemStyle:{
            color:'rgba(253,115,103,0.8)',
            borderColor:'rgba(253,115,103,1)',
            borderType:'solid',
            borderWidth:1
        }
    },
    {
        type: 'bar',
        barGap:'5%',
        itemStyle:{
            color:'rgba(248,190,74,0.8)',
            borderColor:'rgba(248,190,74,1)',
            borderType:'solid',
            borderWidth:1
        }
    },
    {
        type: 'bar',
        barGap:'5%',
        itemStyle:{
            color:'rgba(90,216,166,0.8)',
            borderColor:'rgba(90,216,166,1)',
            borderType:'solid',
            borderWidth:1
        }
    },
    {
        type: 'bar',
        barGap:'5%',
        itemStyle:{
            color:'rgba(81,156,232,0.8)',
            borderColor:'rgba(81,156,232,1)',
            borderType:'solid',
            borderWidth:1
        }
    },
    {
        type: 'bar',
            barGap:'5%',
        itemStyle:{
        color:'rgba(90,216,166,0.8)',
            borderColor:'rgba(90,216,166,1)',
            borderType:'solid',
            borderWidth:1
        }
    }],
    color:['rgba(253,115,103,1)','rgba(248,190,74,1)','rgba(90,216,166,1)','rgba(81,156,232,1)','rgba(181,256,232,1)','rgba(281,156,132,1)'],
    // serials:[this.serial1,this.serial2,this.serial3,this.serial4,this.serial5],
    //市场份额
    queryMarketShareData:function (obj,proId,stageId,type){
        let that = this
        var optionxj = {}
        let currentProjectId = proId
        //默认查询最新阶段的，需要判断当前阶段是否已结束
        let currentStageId = stageId
        //如果阶段Id不是6，直接取上一阶段，如果是6，看项目状态是否已结束
        let projectStatus = obj.$refs['projectStatus'].attrs.value
        if(projectStatus!='1'){
            currentStageId = currentStageId -1
        }
        let params= new URLSearchParams()
        params.append('proId', currentProjectId)
        params.append('stageId', currentStageId)
        params.append('type', type)
        axios({
            method:'get',
            url:'query/marketPercentData',//相对路径即可，不用加/service
            params:params,
            responseType:'json',
        }).then(function(resp){
            console.log(resp)
            var data = resp.data.data
            var echartdata = []
            var percent = 100 / data.length
            data.forEach(function(item,index){
                let temp = {value:'',name:''}
                if(item.marketShare === null){
                    temp.value = percent
                }else{
                    temp.value = item.marketShare
                }
                temp.name = item.name
                echartdata.push(temp)
            })
            optionxj = {
                // tooltip : {
                //     trigger: 'item'
                // },
                //标签
                label:{
                },
                //提示框
                tooltip: {
                    trigger: 'item',
                    backgroundColor:'rgba(255,255,255,0.9)',
                    borderColor:'rgba(0,0,0,0.1)',
                    borderWidth:0,
                    padding:[
                        15,
                        20,
                        15,
                        20,
                    ],
                    textStyle:{
                        color:'#333',
                        fontSize:13
                    },
                    extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);'
                },
                //颜色
                color:['rgba(253,115,103,0.8)','rgba(248,190,74,0.8)','rgba(90,216,166,0.8)','rgba(81,156,232,0.8)'],
                series : [
                    {
                        name: type,
                        type: 'pie',
                        radius : '60%',
                        center: ['50%', '50%'],
                        data:echartdata,
                        itemStyle: {
                            borderType:'solid',
                            borderWidth:1
                        }
                    }
                ]
            }
            var chartDom = document.getElementById('sczb');
            // console.log(optionxj)
            var myChart = echarts.init(chartDom);
            optionxj && myChart.setOption(optionxj);
        })
    },
    //员工满意度
    degreeOfSatisfied:function (obj,teamId,stageId){
        //query/employeeDegreeOfSatisfied
        //如果阶段Id不是6，直接取上一阶段，如果是6，看项目状态是否已结束
        let projectStatus = obj.$refs['projectStatus'].attrs.value
        if(projectStatus!='1'){
            stageId = stageId -1
        }
        let params= new URLSearchParams()
        params.append('teamId', teamId)
        params.append('stageId', stageId)
        axios({
            method:'get',
            url:'query/employeeDegreeOfSatisfied',//相对路径即可，不用加/service
            params:params,
            responseType:'json',
        }).then(function(resp){
            //人员满意度  雷达图
            let data = resp.data.data
            var option = {
                //提示框
                tooltip: {
                    trigger: 'axis'
                },
                //图例
                legend: {
                    orient: 'vertical',
                    x: '400',
                    y: 'center',
                    align: 'left',
                    itemWidth:12,
                    itemHeight:12,
                    data: ['贷款业务', '存款业务', '投资业务', '中间业务']
                },
                //颜色
                color:['rgba(253,115,103,1)','rgba(248,190,74,1)','rgba(90,216,166,1)','rgba(81,156,232,1)'],
                //坐标系
                radar: [
                    {
                        indicator: [
                            { name: '激励', max: 10},
                            { name: '满意', max: 10},
                            { name: '不满意', max: 10},
                            { name: '痛苦', max: 10}
                        ],
                        radius : '60%',
                        center: ['35%', '50%'],
                        axisLine:{
                            lineStyle:{
                                color:['rgba(0,0,0,0.1)']
                            }
                        },
                        name:{
                            show:true,
                            color:['rgba(0,0,0,0.7)']
                        },
                        //分隔线
                        splitLine:{
                            lineStyle:{
                                color:['rgba(0,0,0,0.1)']
                            }
                        },
                        //分隔区域
                        splitArea:{
                            show:false
                        }
                    }
                ],
                series: [
                    {
                        type: 'radar',
                        areaStyle: {
                            color:['rgba(255,255,255,0.3)']
                        },
                        data: [
                            {
                                name: '贷款业务',
                                value: data['贷款业务'],
                                symbolSize:2
                            },
                            {
                                name: '存款业务',
                                value: data['存款业务'],
                                symbolSize:2
                            },
                            {
                                name: '投资业务',
                                value: data['投资业务'],
                                symbolSize:2
                            },
                            {
                                name: '中间业务',
                                value: data['中间业务'],
                                symbolSize:2
                            }
                        ]
                    }
                ]
            };
            var chartDom = document.getElementById('rymyd');
            var myChart = echarts.init(chartDom);
            option && myChart.setOption(option);
        })
    },
    //市场营销
    marketingManagement:function (obj,teamId,stageId){
        //query/employeeDegreeOfSatisfied
        //如果阶段Id不是6，直接取上一阶段，如果是6，看项目状态是否已结束
        let projectStatus = obj.$refs['projectStatus'].attrs.value
        if(projectStatus!='1'){
            stageId = stageId -1
        }
        let params= new URLSearchParams()
        params.append('teamId', teamId)
        params.append('stageId', stageId)
        axios({
            method:'get',
            url:'query/marketingManagement',//相对路径即可，不用加/service
            params:params,
            responseType:'json',
        }).then(function(resp){
            //市场营销  雷达图
            let data = resp.data.data
            var option = {
                //提示框
                tooltip: {
                    trigger: 'axis'
                },
                //图例
                legend: {
                    orient: 'vertical',
                    x: '400',
                    y: 'center',
                    align: 'left',
                    itemWidth:12,
                    itemHeight:12,
                    data: ['短期贷款', '中长期贷款', '存款', '托管业务', '担保业务']
                },
                //颜色
                color:['rgba(253,115,103,1)','rgba(248,190,74,1)','rgba(90,216,166,1)','rgba(81,156,232,1)','rgba(191,128,255,1)'],
                //雷达坐标系
                radar: [
                    {
                        indicator: [
                            { name: '<35岁', max: 4},
                            { name: '35-55岁', max: 4},
                            { name: '>55岁', max: 4},
                            { name: '小型企业', max: 4},
                            { name: '中型企业', max: 4},
                            { name: '大型企业', max: 4},
                            { name: '公共机构', max: 4}
                        ],
                        radius : '60%',
                        center: ['35%', '50%'],
                        axisLine:{
                            lineStyle:{
                                color:['rgba(0,0,0,0.1)']
                            }
                        },
                        name:{
                            show:true,
                            color:['rgba(0,0,0,0.7)']
                        },
                        //分隔线
                        splitLine:{
                            lineStyle:{
                                color:['rgba(0,0,0,0.1)']
                            }
                        },
                        //分隔区域
                        splitArea:{
                            show:false
                        }
                    }
                ],
                series: [
                    {
                        type: 'radar',
                        areaStyle: {
                            color:['rgba(255,255,255,0.3)']
                        },
                        data: [
                            {
                                name: '短期贷款',
                                value: data['短期贷款'],
                                symbolSize:2
                            },
                            {
                                name: '中长期贷款',
                                value:  data['中长期贷款'],
                                symbolSize:2
                            },
                            {
                                name: '存款',
                                value:  data['存款'],
                                symbolSize:2
                            },
                            {
                                name: '托管业务',
                                value:  data['托管业务'],
                                symbolSize:2
                            },
                            {
                                name: '担保业务',
                                value:  data['担保业务'],
                                symbolSize:2
                            }
                        ]
                    }
                ]
            };
            var chartDom = document.getElementById('scyx');
            var myChart = echarts.init(chartDom);
            option && myChart.setOption(option);
        })
    },
    //资产总额
    totalAssetsData:function(obj,proId,stageId){
        let that = this
        let params= new URLSearchParams()
        params.append('proId', proId)
        params.append('currentStageId', stageId)
        axios({
            method:'get',
            url:'query/assestTotal',//相对路径即可，不用加/service
            params:params,
            responseType:'json',
        }).then(function(resp){
            let data = resp.data.data
            var length = data[0].length
            let series = [];
            for(var i=0;i<length-1;i++){
                series.push(that.serials[i])
            }
            //资产总额   柱状图
            var option = {
                //图例
                legend: {
                    orient: 'horizontal',
                    x: 'center',
                    y: 'bottom',
                    align: 'left',
                    padding:[
                        0,
                        0,
                        25,
                        0,
                    ],
                    itemWidth:12,
                    itemHeight:12
                },

                //坐标系网格
                grid:{
                    top:'40px',
                    right:'50px',
                    bottom:'80px',
                    left:'50px'
                },
                //提示框
                tooltip: {
                    trigger: 'axis',
                    backgroundColor:'rgba(255,255,255,0.9)',
                    padding:[
                        15,
                        20,
                        15,
                        20,
                    ],
                    textStyle:{
                        color:'#333',
                        fontSize:13
                    },
                    extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);'
                },
                //数值
                dataset: {
                    source: resp.data.data
                },
                //x轴
                xAxis: {
                    type: 'category',
                    //轴线
                    axisLine:{
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    //刻度
                    axisTick:{
                        show:false,
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    //刻度标签
                    axisLabel:{
                        show:true,
                        interval:0,
                        color:'#555',
                        rotate:0
                    }
                },
                //y轴
                yAxis: {
                    type: 'value',
                    //轴线
                    axisLine:{
                        show:false,
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    //刻度
                    axisTick:{
                        show:false,
                        lineStyle:{
                            color:'#e6e6e6'
                        }
                    },
                    //刻度标签
                    axisLabel:{
                        color:'#555'
                    },
                    splitLine:{
                        lineStyle:{
                            color:['#e6e6e6','#f2f2f2'],
                            type:'dotted'
                        }
                    }
                },
                //图表
                series:series
            };
            var chartDom = document.getElementById('zcze');
            var myChart = echarts.init(chartDom);
            option && myChart.setOption(option);
        })
    },
    teamProfitData:function(obj,proId,stageId){
        let that = this
        let params= new URLSearchParams()
        params.append('proId', proId)
        params.append('currentStageId', stageId)
        axios({
            method:'get',
            url:'query/profitData',//相对路径即可，不用加/service
            params:params,
            responseType:'json',
        }).then(function(resp) {
            let data = resp.data.data
            var length = data[0].length
            let series = [];
            for (var i = 0; i < length - 1; i++) {
                series.push(that.serials[i])
            }
            var option = {
                //图例
                legend: {
                    orient: 'horizontal',
                    x: 'center',
                    y: 'bottom',
                    align: 'left',
                    padding:[
                        0,
                        0,
                        25,
                        0,
                    ],
                    itemWidth:12,
                    itemHeight:12
                },
                //坐标系网格
                grid:{
                    top:'40px',
                    right:'50px',
                    bottom:'80px',
                    left:'50px'
                },
                //提示框
                tooltip: {
                    trigger: 'axis',
                    backgroundColor:'rgba(255,255,255,0.9)',
                    padding:[
                        15,
                        20,
                        15,
                        20,
                    ],
                    textStyle:{
                        color:'#333',
                        fontSize:13
                    },
                    extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);'
                },
                //数值
                dataset: {
                    source: data
                },
                //x轴
                xAxis: {
                    type: 'category',
                    //轴线
                    axisLine:{
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    //刻度
                    axisTick:{
                        show:false,
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    //刻度标签
                    axisLabel:{
                        show:true,
                        interval:0,
                        color:'#555',
                        rotate:0
                    }
                },
                //y轴
                yAxis: {
                    type: 'value',
                    //轴线
                    axisLine:{
                        show:false,
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    //刻度
                    axisTick:{
                        show:false,
                        lineStyle:{
                            color:'#e6e6e6'
                        }
                    },
                    //刻度标签
                    axisLabel:{
                        color:'#555'
                    },
                    splitLine:{
                        lineStyle:{
                            color:['#e6e6e6','#f2f2f2'],
                            type:'dotted'
                        }
                    }
                },
                //图表
                series: series
            };
            var chartDom = document.getElementById('lrze');
            var myChart = echarts.init(chartDom);
            option && myChart.setOption(option);
        })
    },
    assetGrouthRate:function(obj,proId,stageId){
        //资产增长率  拆线图
        let that = this
        let params= new URLSearchParams()
        params.append('proId', proId)
        params.append('currentStageId', stageId)
        axios({
            method:'get',
            url:'query/assetGrouthRate',//相对路径即可，不用加/service
            params:params,
            responseType:'json',
        }).then(function(resp) {
            let data = resp.data.data
            // console.log(data)
            let colors = []
            for(var i=0;i<data['stages'].length;i++){
                colors.push(that.color[i])
            }
            let serails = []
            for(let key in data){
                if(key!='stages'){
                    var temp = {
                        name:key,
                        type:'line',
                        data:data[key],
                        symbol:'circle',
                        symbolSize:2
                    }
                    serails.push(temp)
                }
            }
            var option = {
                //图例
                legend: {
                    orient: 'horizontal',
                    x: 'center',
                    y: 'bottom',
                    align: 'left',
                    padding:[
                        0,
                        0,
                        30,
                        0,
                    ],
                    itemWidth:12,
                    itemHeight:12
                },
                //提示框
                tooltip: {
                    trigger: 'axis',
                    backgroundColor:'rgba(255,255,255,0.9)',
                    padding:[
                        15,
                        20,
                        15,
                        20,
                    ],
                    textStyle:{
                        color:'#333',
                        fontSize:13
                    },
                    extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);'
                },
                //坐标系网格
                grid:{
                    top:'40px',
                    right:'30px',
                    bottom:'100px',
                    left:'60px'
                },
                //颜色
                color:colors,
                //x轴
                xAxis: {
                    type: 'category',
                    //轴线
                    axisLine:{
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    boundaryGap:true,
                    //刻度
                    axisTick:{
                        show:true,
                        alignWithLabel:true,
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    //刻度标签
                    axisLabel:{
                        show:true,
                        interval:0,
                        color:'#555',
                        rotate:0
                    },
                    data: data['stages']
                },
                //y轴
                yAxis: {
                    type: 'value',
                    //轴线
                    axisLine:{
                        show:false,
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    //刻度
                    axisTick:{
                        show:false,
                        lineStyle:{
                            color:'#e6e6e6'
                        }
                    },
                    //刻度标签
                    axisLabel:{
                        color:'#555'
                    },
                    splitLine:{
                        lineStyle:{
                            color:['#e6e6e6','#f2f2f2'],
                            type:'dotted'
                        }
                    }
                },
                //图表
                series:serails
            };
            var chartDom = document.getElementById('zczzl');
            var myChart = echarts.init(chartDom);
            option && myChart.setOption(option);
        })
    },
    stockPriceChart(obj,proId,stageId){
        //各阶段银行股份  拆线图
        let that = this
        let params= new URLSearchParams()
        params.append('proId', proId)
        params.append('currentStageId', stageId)
        axios({
            method:'get',
            url:'query/assetGrouthRate',//相对路径即可，不用加/service
            params:params,
            responseType:'json',
        }).then(function(resp) {
            let data = resp.data.data
            // console.log(data)
            let colors = []
            for (var i = 0; i < data['stages'].length; i++) {
                colors.push(that.color[i])
            }
            let serails = []
            for (let key in data) {
                if (key != 'stages') {
                    var temp = {
                        name: key,
                        type: 'line',
                        data: data[key],
                        symbol: 'circle',
                        symbolSize: 2
                    }
                    serails.push(temp)
                }
            }
            var option = {
                //图例
                legend: {
                    orient: 'horizontal',
                    x: 'center',
                    y: 'bottom',
                    align: 'left',
                    padding:[
                        0,
                        0,
                        30,
                        0,
                    ],
                    itemWidth:12,
                    itemHeight:12
                },
                //提示框
                tooltip: {
                    trigger: 'axis',
                    backgroundColor:'rgba(255,255,255,0.9)',
                    padding:[
                        15,
                        20,
                        15,
                        20,
                    ],
                    textStyle:{
                        color:'#333',
                        fontSize:13
                    },
                    extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);'
                },
                //坐标系网格
                grid:{
                    top:'40px',
                    right:'30px',
                    bottom:'100px',
                    left:'60px'
                },
                //颜色
                color:colors,
                //x轴
                xAxis: {
                    type: 'category',
                    //轴线
                    axisLine:{
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    boundaryGap:true,
                    //刻度
                    axisTick:{
                        show:true,
                        alignWithLabel:true,
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    //刻度标签
                    axisLabel:{
                        show:true,
                        interval:0,
                        color:'#555',
                        rotate:0
                    },
                    data: data['stages']
                },
                //y轴
                yAxis: {
                    type: 'value',
                    //轴线
                    axisLine:{
                        show:false,
                        lineStyle:{
                            color:'#ccc'
                        }
                    },
                    //刻度
                    axisTick:{
                        show:false,
                        lineStyle:{
                            color:'#e6e6e6'
                        }
                    },
                    //刻度标签
                    axisLabel:{
                        color:'#555'
                    },
                    splitLine:{
                        lineStyle:{
                            color:['#e6e6e6','#f2f2f2'],
                            type:'dotted'
                        }
                    }
                },
                //图表
                series: serails
            };
            var chartDom = document.getElementById('yhgj');
            var myChart = echarts.init(chartDom);
            option && myChart.setOption(option);
        })
    }
}

