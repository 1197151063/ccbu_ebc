(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-10debfbd"],{"5ae5":function(e,t,a){},e6dd:function(e,t,a){"use strict";var i=a("5ae5");a.n(i).a},f629:function(e,t,a){"use strict";a.r(t);var i=(a("8e6e"),a("ac6a"),a("456d"),a("ade3")),r=(a("96cf"),a("1da1")),n=a("0bb4");function o(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);t&&(i=i.filter(function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable})),a.push.apply(a,i)}return a}function s(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?o(Object(a),!0).forEach(function(t){Object(i.a)(e,t,a[t])}):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):o(Object(a)).forEach(function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))})}return e}var c={name:"PersonNotice",components:{},props:{},data:function(){var e=this;return{searchData:{title:""},modalType:"add",editModal:!1,addModal:!1,loading:!0,saveLoading:!0,updateLoading:!0,pageNum:1,pageSize:10,pageTotal:0,updateItem:{},saveItem:{},formData:{title:"",content:""},columns:[{title:"ID",width:60,key:"id"},{title:"消息标题",key:"title"},{title:"创建时间",key:"receiveTime"},{title:"消息创建人",key:"createUserName"},{title:"状态",key:"createUser",render:function(e,t){return e("span",t.row.readStatus?"已读":"未读")}},{title:"操作",key:"action",align:"center",width:200,className:"action-hide",render:function(t,a){return e.$tableAction(t,[{title:"查看",directives:[{name:"privilege",value:"person-notice-detail"}],action:function(){e.getNoticeDetail(a.row.id)}}])}}],data:[],formValidate:{title:[{required:!0,message:"请输入消息标题",trigger:"blur"}],content:[{required:!0,message:"请输入消息内容",trigger:"blur"}]}}},computed:{},watch:{},filters:{},created:function(){},mounted:function(){this.getPersonNoticeList()},beforeCreate:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){},activated:function(){},methods:{getPersonNoticeList:function(){var e=Object(r.a)(regeneratorRuntime.mark(function e(){var t;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,this.loading=!0,e.next=4,n.a.getPersonNoticeList(s(s({},this.searchData),{},{pageNum:this.pageNum,pageSize:this.pageSize}));case 4:t=e.sent,this.loading=!1,this.data=t.data.list,this.pageTotal=t.data.total,this.pageNum=t.data.pageNum,this.pageSize=t.data.pageSize,e.next=16;break;case 12:e.prev=12,e.t0=e.catch(0),this.loading=!1;case 16:case"end":return e.stop()}},e,this,[[0,12]])}));return function(){return e.apply(this,arguments)}}(),getNoticeDetail:function(){var e=Object(r.a)(regeneratorRuntime.mark(function e(t){var a;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,this.loading=!0,e.next=4,n.a.getNoticeDetail(t);case 4:a=e.sent,this.loading=!1,this.formData=a.data,this.openModal(a.data),e.next=14;break;case 10:e.prev=10,e.t0=e.catch(0),this.loading=!1;case 14:case"end":return e.stop()}},e,this,[[0,10]])}));return function(t){return e.apply(this,arguments)}}(),openModal:function(){var e=Object(r.a)(regeneratorRuntime.mark(function e(t){return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return this.editModal=!0,this.$Spin.show(),e.next=4,n.a.addNoticeRecord(t.id);case 4:e.sent,this.$Spin.hide();case 6:case"end":return e.stop()}},e,this)}));return function(t){return e.apply(this,arguments)}}(),changePage:function(e){this.pageNum=e,this.getPersonNoticeList()},changePageSize:function(e){this.pageNum=1,this.pageSize=e,this.getPersonNoticeList()},refresh:function(){this.pageNum=1,this.searchData.title="",this.getPersonNoticeList()},cancelSave:function(){this.editModal=!1,this.getPersonNoticeList()}}},l=(a("e6dd"),a("2877")),u=Object(l.a)(c,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("Card",{staticClass:"warp-card",attrs:{"dis-hover":""}},[a("Form",{staticClass:"tools",attrs:{inline:""}},[a("FormItem",{directives:[{name:"privilege",rawName:"v-privilege",value:"person-notice-query",expression:"'person-notice-query'"}]},[a("Input",{attrs:{placeholder:"请输入消息标题"},model:{value:e.searchData.title,callback:function(t){e.$set(e.searchData,"title",t)},expression:"searchData.title"}},[a("Button",{attrs:{slot:"append",icon:"ios-search"},on:{click:e.getPersonNoticeList},slot:"append"})],1)],1),a("FormItem",[a("Button",{directives:[{name:"privilege",rawName:"v-privilege",value:"person-notice-query",expression:"'person-notice-query'"}],attrs:{icon:"md-refresh",type:"primary"},on:{click:e.refresh}},[e._v("重置")])],1)],1),a("Table",{attrs:{columns:e.columns,data:e.data,loading:e.loading,border:""}}),a("Page",{staticStyle:{margin:"24px 0","text-align":"right"},attrs:{current:e.pageNum,"page-size":e.pageSize,"page-size-opts":[10,20,30,50,100],total:e.pageTotal,"show-elevator":"","show-sizer":"","show-total":""},on:{"on-change":e.changePage,"on-page-size-change":e.changePageSize}})],1),a("Modal",{staticClass:"detail-modal",attrs:{loading:e.saveLoading,title:e.formData.title},model:{value:e.editModal,callback:function(t){e.editModal=t},expression:"editModal"}},[a("div",{staticClass:"detail"},[e._v(e._s(e.formData.content))]),a("p",{staticClass:"time"},[e._v(e._s(e.formData.updateTime))]),a("div",{attrs:{slot:"footer"},slot:"footer"},[a("Button",{attrs:{size:"large",type:"primary"},on:{click:e.cancelSave}},[e._v("知道了")])],1)])],1)},[],!1,null,"7c11a5d0",null);t.default=u.exports}}]);