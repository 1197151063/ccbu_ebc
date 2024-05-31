var Main =  {
    data() {
        return {
            currentStageId:1,
            currentStageDesc:'',
            activeIndex: '8',
            domain_data: [1,2,2],
            previousStage1: '1',
            previousStage2: '2',
            assets1:{
                cash:1,
                shortDeposit:1,

            },

        }
    },
    methods: {
        queryInitData() {
            let that = this
            axios({
                method: 'get',
                url: '/parInitialDataAutomation/queryAll',
                responseType: 'json',
            }).then(function (response) {
                that.domain_data = response.data.data
            })
        }
    },
    mounted() {
        this.queryInitData()
    }
}
;const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");