(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-2dcb4da2"],{"1d86":function(e,t,n){},"2f21":function(e,t,n){"use strict";var r=n("79e5");e.exports=function(e,t){return!!e&&r(function(){t?e.call(null,function(){},1):e.call(null)})}},"55dd":function(e,t,n){"use strict";var r=n("5ca1"),i=n("d8e8"),a=n("4bf8"),s=n("79e5"),u=[].sort,o=[1,2,3];r(r.P+r.F*(s(function(){o.sort(void 0)})||!s(function(){o.sort(null)})||!n("2f21")(u)),"Array",{sort:function(e){return void 0===e?u.call(a(this)):u.call(a(this),i(e))}})},"679b":function(e,t,n){"use strict";n.r(t);var r=(n("ac4d"),n("8a81"),n("1c4c"),n("6b54"),n("20d6"),n("55dd"),n("7f7f"),n("96cf"),n("1da1")),i=(n("ac6a"),n("5df3"),n("f400"),n("6f0b")),a=(n("28a5"),{name:"PrivilegeForm",components:{},props:{typeDisabled:{type:Boolean,default:!0,require:!1},show:{type:Boolean,default:!1,require:!0},title:{type:String,require:!0},privilege:{type:Object,require:!0}},data:function(){return{scope:1,urlList:[],privilegeNameTitle:"菜单名称",urlArray:[]}},computed:{},watch:{privilege:function(e){e&&(this.urlArray=e.url.split(","))}},filters:{},created:function(){},mounted:function(){this.getAllUrl()},beforeCreate:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){},activated:function(){},methods:{getAllUrl:function(){var e=Object(r.a)(regeneratorRuntime.mark(function e(){var t,n,r,a;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return this.$Spin.show(),e.next=3,i.a.getAllUrl(this.scope);case 3:t=e.sent,this.$Spin.hide(),n=t.data,r=[],a=[],n.map(function(e){var t=e.name.split(".")[0],n=a.indexOf(t);n<0?(a.push(t),r.push({label:t,data:[e]})):r[n].data.push(e)}),this.urlList=r;case 11:case"end":return e.stop()}},e,this)}));return function(){return e.apply(this,arguments)}}(),cancel:function(){this.$emit("closeModal")},submitForm:function(){var e=Object.assign({},this.privilege);0!==this.urlArray.length?(e.url=this.urlArray.join(","),this.addOrUpdate(e)):this.$Message.error("请选择Url!")},addOrUpdate:function(){var e=Object(r.a)(regeneratorRuntime.mark(function e(t){return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return this.$Spin.show(),e.next=3,i.a.addOrUpdate(t);case 3:e.sent,this.$Message.success("修改成功"),this.$Spin.hide(),this.$emit("updateMenuSuccess",t.menuKey);case 7:case"end":return e.stop()}},e,this)}));return function(t){return e.apply(this,arguments)}}()}}),s=n("2877"),u=Object(s.a)(a,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("Modal",{attrs:{closable:!1,"mask-closable":!1,title:e.privilege.functionName,width:680},on:{"on-ok":function(t){return e.submitForm()}},model:{value:e.show,callback:function(t){e.show=t},expression:"show"}},[n("Form",{ref:"privilegeFormRef",attrs:{"label-width":100,model:e.privilege}},[n("Form-item",{attrs:{label:"菜单名称：",required:""}},[n("Input",{attrs:{disabled:"",placeholder:"请输入菜单名称"},model:{value:e.title,callback:function(t){e.title=t},expression:"title"}})],1),n("Form-item",{attrs:{label:"功能点名称：",prop:"functionName",required:""}},[n("Input",{attrs:{disabled:"",placeholder:"请输入功能点名称"},model:{value:e.privilege.functionName,callback:function(t){e.$set(e.privilege,"functionName",t)},expression:"privilege.functionName"}})],1),n("Form-item",{attrs:{label:"功能Key：",prop:"functionKey",required:""}},[n("Input",{attrs:{disabled:"",placeholder:"请输入功能Key"},model:{value:e.privilege.functionKey,callback:function(t){e.$set(e.privilege,"functionKey",t)},expression:"privilege.functionKey"}})],1),n("Form-item",{attrs:{label:"Url："}},[n("Select",{attrs:{filterable:"",multiple:""},model:{value:e.urlArray,callback:function(t){e.urlArray=t},expression:"urlArray"}},e._l(e.urlList,function(t,r){return n("OptionGroup",{key:r,attrs:{label:t.label}},e._l(t.data,function(t,r){return n("Option",{key:r,attrs:{label:t.url,value:t.name}},[n("span",[e._v(e._s(t.url))]),n("span",{staticStyle:{float:"right",color:"#ccc"}},[e._v(e._s(t.comment))])])}),1)}),1)],1)],1),n("div",{attrs:{slot:"footer"},slot:"footer"},[n("Button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("保存")]),n("Button",{attrs:{type:"default"},on:{click:e.cancel}},[e._v("取消")])],1)],1)},[],!1,null,null,null).exports,o=n("ddb9"),l=n("ad67");function c(e,t){var n;if("undefined"==typeof Symbol||null==e[Symbol.iterator]){if(Array.isArray(e)||(n=function(e,t){if(e){if("string"==typeof e)return m(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?m(e,t):void 0}}(e))||t&&e&&"number"==typeof e.length){n&&(e=n);var r=0,i=function(){};return{s:i,n:function(){return r>=e.length?{done:!0}:{done:!1,value:e[r++]}},e:function(e){throw e},f:i}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var a,s=!0,u=!1;return{s:function(){n=e[Symbol.iterator]()},n:function(){var e=n.next();return s=e.done,e},e:function(e){u=!0,a=e},f:function(){try{s||null==n.return||n.return()}finally{if(u)throw a}}}}function m(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var p={name:"SystemPrivilege",components:{PrivilegeForm:u},data:function(){var e=this;return{typeDisabled:!0,activeName:0,scope:1,formData:{show:!1,title:"功能点菜单",privilege:{functionKey:"",functionName:"",menuKey:"",url:1}},menuTree:[],menusChange:!1,menusChangeNum:0,menuList:[],pointsChangeNum:0,routerMap:new Map,privilegeTableData:[],privilegeTableColumn:[{title:"名称",key:"title"},{title:"routerKey",key:"name"},{title:"url",key:"url"},{title:"操作",width:120,align:"center",className:"action-hide",render:function(t,n){return e.$tableAction(t,[{title:"编辑",directives:[{name:"privilege",value:"privilege-main-update"}],action:function(){e.updatePrivilege(n.row,n.index)}}])}}]}},mounted:function(){this.initRouters()},methods:{closeModal:function(){this.formData.show=!1},initRouters:function(){this.resetMenuChange(),this.buildPrivilegeTree()},buildPrivilegeTree:function(){var e=Object(r.a)(regeneratorRuntime.mark(function e(){var t,n,r,a,s,u,l,m,p,h,d,f;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return this.$Spin.show(),e.next=3,i.a.getMenuList();case 3:t=e.sent,n=t.data,r=new Map,a=c(n);try{for(a.s();!(s=a.n()).done;)u=s.value,r.set(u.menuKey,u)}catch(e){a.e(e)}finally{a.f()}l=[],m=[],p=c(o.a);try{for(p.s();!(h=p.n()).done;)(d=h.value).meta.noValidatePrivilege||(this.routerMap.set(d.name,d),f=this.convert2Menu(d,null),m.push(f),l.push(f),this.hasMenuChange(f,r),d.children&&d.children.length>0&&this.recursion(d.children,f,l,r))}catch(e){p.e(e)}finally{p.f()}l.length<n.length&&(this.menusChange=!0,this.menusChangeNum=this.menusChangeNum+Math.abs(l.length-n.length)),this.menuTree=m,this.menuList=l,this.$Spin.hide();case 17:case"end":return e.stop()}},e,this)}));return function(){return e.apply(this,arguments)}}(),convert2Menu:function(e,t){return{type:l.a.MENU.value,menuName:e.meta.title,menuKey:e.name,parentKey:t,url:e.path,children:[],sort:0,hideInMenu:e.meta.hideInMenu}},recursion:function(e,t,n,r){var i,a=c(e);try{for(a.s();!(i=a.n()).done;){var s=i.value;if(!s.meta.noValidatePrivilege){this.routerMap.set(s.name,s);var u=this.convert2Menu(s,t.menuKey);t.children.push(u),n.push(u),this.hasMenuChange(u,r),s.children&&s.children.length>0&&this.recursion(s.children,u,n,r)}}}catch(e){a.e(e)}finally{a.f()}},resetMenuChange:function(){this.menusChange=!1,this.menusChangeNum=0},hasMenuChange:function(e,t){var n=t.get(e.menuKey);(!n||n.menuName!==e.menuName||n.menuKey!==e.menuKey||n.parentKey!==e.parentKey||n.url!==e.url)&&(this.menusChange=!0,this.menusChangeNum=this.menusChangeNum+1)},addBatchSavePoints:function(){var e=Object(r.a)(regeneratorRuntime.mark(function e(){return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return this.$Spin.show(),e.next=3,i.a.addBatchSavePoints(this.privilegeTableData.map(function(e){return Object.assign({},{functionName:e.title,menuKey:e.parentKey,functionKey:e.name,sort:e.sort})}));case 3:e.sent,this.$Message.success("批量保存成功"),this.$Spin.hide(),this.loadPrivilegeTableData(this.privilegeTableData[0].parentKey);case 7:case"end":return e.stop()}},e,this)}));return function(){return e.apply(this,arguments)}}(),addBatchSaveMenu:function(){var e=Object(r.a)(regeneratorRuntime.mark(function e(){return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return this.$Spin.show(),e.next=3,i.a.addBatchSaveMenu(this.menuList);case 3:e.sent,this.$Message.success("批量保存成功"),this.$Spin.hide(),this.initRouters();case 7:case"end":return e.stop()}},e,this)}));return function(){return e.apply(this,arguments)}}(),updatePrivilege:function(e,t){this.formData.privilege={functionKey:e.name,functionName:e.title,menuKey:e.parentKey,url:e.url,sort:t},this.formData.title=e.parentName,this.formData.show=!0},getPrivilegeList:function(){var e=Object(r.a)(regeneratorRuntime.mark(function e(t,n){var r,a,s,u,o,l,m,p,h,d,f;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return this.$Spin.show(),this.formData.show=!1,e.next=4,i.a.queryPrivilegeFunctionList(t);case 4:r=e.sent,this.$Spin.hide(),a=r.data,s=new Map,a.map(function(e){s.set(e.functionKey,{url:e.url?e.url:""})}),u=0,o=c(n);try{for(o.s();!(l=o.n()).done;)m=l.value,(p=s.get(m.name))?m.url=p.url:(m.url="",u++)}catch(e){o.e(e)}finally{o.f()}if(a.length>n.length){h=c(a);try{for(f=function(){var e=d.value;-1===n.findIndex(function(t){return t.name===e.functionKey})&&u++},h.s();!(d=h.n()).done;)f()}catch(e){h.e(e)}finally{h.f()}}this.pointsChangeNum=u,this.privilegeTableData=n;case 15:case"end":return e.stop()}},e,this)}));return function(t,n){return e.apply(this,arguments)}}(),loadPrivilegeTableData:function(e){var t=this.routerMap.get(e),n=[];if(!_.isUndefined(t)&&t.meta&&t.meta.privilege){var r=0;n=t.meta.privilege.map(function(t){return r++,Object.assign({},t,{parentKey:e},{sort:r})})}this.getPrivilegeList(e,n)}}},h=(n("7f2c"),Object(s.a)(p,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("Row",{attrs:{gutter:10}},[n("Col",{attrs:{lg:6,md:8}},[n("Card",{staticClass:"warp-card",attrs:{"dis-hover":""}},[n("div",{staticClass:"card-title",attrs:{slot:"title"},slot:"title"},[n("Icon",{attrs:{type:"ios-switch"}}),e._v("菜单管理\n      ")],1),n("div",{attrs:{slot:"extra"},slot:"extra"},[e.menusChange?n("Button",{attrs:{icon:"md-add",size:"small",type:"primary"},on:{click:e.addBatchSaveMenu}},[e._v("批量保存")]):e._e()],1),e.menusChange?n("Alert",{attrs:{"show-icon":"",type:"warning"}},[e._v("有 "+e._s(this.menusChangeNum)+" 个更新，请立即批量保存！")]):e._e(),n("Menu",{ref:"defineSelect",attrs:{"active-name":e.activeName,width:"100%"},on:{"on-select":e.loadPrivilegeTableData}},[e._l(e.menuTree,function(t,r){return[n("Submenu",{key:r,attrs:{name:t.menuKey}},[n("template",{slot:"title"},[n("span",[n("Icon",{attrs:{type:"md-menu"}}),e._v(e._s(t.menuName))],1)]),e._l(t.children,function(t,r){return[t.children.length>0?n("Submenu",{key:r,attrs:{name:t.menuKey}},[n("template",{slot:"title"},[t.hideInMenu?[n("Icon",{attrs:{type:"md-open"}}),n("i",{staticStyle:{"font-size":"0.85rem"}},[e._v(" "+e._s(t.menuName))])]:[n("Icon",{attrs:{type:"md-menu"}}),e._v(" "+e._s(t.menuName)+"\n                    ")]],2),e._l(t.children,function(t,r){return[t.children.length>0?n("Submenu",{key:r,attrs:{name:t.menuKey}},[n("template",{slot:"title"},[t.hideInMenu?[n("Icon",{attrs:{type:"md-open"}}),n("i",{staticStyle:{"font-size":"0.85rem"}},[e._v(" "+e._s(t.menuName))])]:[n("Icon",{attrs:{type:"md-menu"}}),e._v(" "+e._s(t.menuName)+"\n                            ")]],2),e._l(t.children,function(t,r){return[t.hideInMenu?n("MenuItem",{key:r,attrs:{name:e.hideInMenu.menuKey}},[n("Icon",{attrs:{type:"md-open"}}),n("i",{staticStyle:{"font-size":"0.85rem"}},[e._v(" "+e._s(t.menuName))])],1):n("MenuItem",{key:r,attrs:{name:t.menuKey}},[n("Icon",{attrs:{type:"md-menu"}}),e._v(" "+e._s(t.menuName)+"\n                        ")],1)]})],2):n("MenuItem",{key:r,attrs:{name:t.menuKey}},[t.hideInMenu?[n("Icon",{attrs:{type:"md-open"}}),n("i",{staticStyle:{"font-size":"0.85rem"}},[e._v(" "+e._s(t.menuName))])]:[n("Icon",{attrs:{type:"md-menu"}}),e._v(" "+e._s(t.menuName)+"\n                    ")]],2)]})],2):n("MenuItem",{key:r,attrs:{name:t.menuKey}},[t.hideInMenu?[n("Icon",{attrs:{type:"md-open"}}),n("i",{staticStyle:{"font-size":"0.85rem"}},[e._v(" "+e._s(t.menuName))])]:[n("Icon",{attrs:{type:"md-menu"}}),e._v(" "+e._s(t.menuName)+"\n                ")]],2)]})],2)]})],2)],1)],1),n("Col",{attrs:{lg:18,md:16}},[n("Card",{staticClass:"warp-card",staticStyle:{"margin-bottom":"100px"},attrs:{"dis-hover":""}},[n("div",{staticClass:"card-title",attrs:{slot:"title"},slot:"title"},[n("Icon",{attrs:{type:"ios-cog"}}),e._v("功能点\n      ")],1),n("div",{attrs:{slot:"extra"},slot:"extra"},[e.pointsChangeNum>0?n("Button",{directives:[{name:"privilege",rawName:"v-privilege",value:"privilege-batch-save-points",expression:"'privilege-batch-save-points'"}],attrs:{icon:"ios-albums-outline",type:"primary",size:"small"},on:{click:e.addBatchSavePoints}},[e._v("批量保存功能点")]):e._e()],1),e.pointsChangeNum>0?n("Alert",{attrs:{"show-icon":"",type:"warning"}},[e._v("有 "+e._s(this.pointsChangeNum)+" 个功能点更新，请立即批量保存！")]):e._e(),n("Row",[n("Table",{attrs:{columns:e.privilegeTableColumn,data:e.privilegeTableData,border:""}})],1)],1)],1),n("Col",{attrs:{span:"24"}},[n("privilege-form",{attrs:{privilege:e.formData.privilege,show:e.formData.show,title:e.formData.title,typeDisabled:e.typeDisabled},on:{closeModal:e.closeModal,updateMenuSuccess:e.loadPrivilegeTableData}})],1)],1)},[],!1,null,"4043b9e8",null));t.default=h.exports},"6f0b":function(e,t,n){"use strict";n.d(t,"a",function(){return i});var r=n("1e04"),i={getAllUrl:function(e){return Object(r.a)("/privilege/getAllUrl")},getMenuList:function(e){return Object(r.c)("/privilege/menu/queryAll")},addBatchSaveMenu:function(e){return Object(r.c)("/privilege/menu/batchSaveMenu",e)},addBatchSavePoints:function(e){return Object(r.c)("/privilege/function/batchSave",e)},queryPrivilegeFunctionList:function(e){return Object(r.c)("/privilege/function/query/"+e)},addOrUpdate:function(e){return Object(r.c)("/privilege/function/saveOrUpdate",e)},getRolePower:function(e){return Object(r.c)("/privilege/updateRolePrivilege",e)},getListPrivilegeByRoleId:function(e){return Object(r.a)("/privilege/listPrivilegeByRoleId/"+e)}}},"7f2c":function(e,t,n){"use strict";var r=n("1d86");n.n(r).a}}]);