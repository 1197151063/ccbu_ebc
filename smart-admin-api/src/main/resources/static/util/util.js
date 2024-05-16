const loading_option = {text:"数据上传中....",background:"rgb(0 0 0 / 12%)"}
let my_loading
let stageOptions = []
let currentProjectId
export const loading_open = obj => {
    my_loading = obj.$loading(loading_option)
}
export const loading_close = function(){
    my_loading.close()
}
export const quertProjectEndStage = (obj,isIndex) => {
    currentProjectId = obj.$refs['projectId'].attrs.value
    axios({
        method:'get',
        url:'sysProject/queryProjectEndStage?proId='+currentProjectId,//相对路径即可，不用加/service
        responseType:'json',
    }).then(function(resp){
        let data = resp.data.data
        stageOptions = []
        data.forEach(function(item,index){
            let temp = {value:'',label:''}
            if(item.stageId > 0){
                temp.value = item.stageId+1
                temp.label = '第'+(item.stageId-1)+'阶段'
                stageOptions.push(temp)
            }
        })
        let projectStatus = obj.$refs['projectStatus'].attrs.value
        let currentStageId = obj.$refs['currentStageId'].attrs.value
        if(projectStatus!='1'){
            currentStageId = currentStageId - 2
        }else{
            currentStageId = currentStageId - 1
        }
        let desc= '第'+(currentStageId)+'阶段'
        //设置默认选中的option
        // if(!isChange){
        obj.currentStageDesc = desc
        // }
        //设置可选择的的options
        obj.optionsjindu = stageOptions
        if(isIndex){//首页多个下阶段下拉框
            obj.currentStageDesc2 = desc
            obj.currentStageDesc3 = desc
            obj.currentStageDesc4 = desc
            obj.currentStageDesc5 = desc
            obj.currentStageDesc6 = desc
            obj.currentStageDesc7 = desc
            obj.currentStageDesc8 = desc
            obj.currentStageDesc9 = desc
        }
    })
    return stageOptions
}