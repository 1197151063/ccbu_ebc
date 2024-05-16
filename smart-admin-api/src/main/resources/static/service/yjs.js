import {loading_open, loading_close, quertProjectEndStage} from "../util/util.js";

var Main = {
    data() {
        return {
            //tabs
            activeName: 'first',
        }
    },
    //资产负债表数据
    asset1: {},
    map:{},
    //利润表数据
    profit1: {},
    //流动性报表-现金
    liquiditycash: {},

    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        },
    },
    // 页面加载时请求数据
    mounted: function () {

    }
}
const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#index");