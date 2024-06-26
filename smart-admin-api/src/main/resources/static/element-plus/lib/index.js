'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var ElAffix = require('./el-affix');
var ElAlert = require('./el-alert');
var ElAside = require('./el-aside');
var ElAutocomplete = require('./el-autocomplete');
var ElAvatar = require('./el-avatar');
var ElBacktop = require('./el-backtop');
var ElBadge = require('./el-badge');
var ElBreadcrumb = require('./el-breadcrumb');
var ElBreadcrumbItem = require('./el-breadcrumb-item');
var ElButton = require('./el-button');
var ElButtonGroup = require('./el-button-group');
var ElCalendar = require('./el-calendar');
var ElCard = require('./el-card');
var ElCarousel = require('./el-carousel');
var ElCarouselItem = require('./el-carousel-item');
var ElCascader = require('./el-cascader');
var ElCascaderPanel = require('./el-cascader-panel');
var ElCheckbox = require('./el-checkbox');
var ElCheckboxButton = require('./el-checkbox-button');
var ElCheckboxGroup = require('./el-checkbox-group');
var ElCol = require('./el-col');
var ElCollapse = require('./el-collapse');
var ElCollapseItem = require('./el-collapse-item');
var ElCollapseTransition = require('./el-collapse-transition');
var ElColorPicker = require('./el-color-picker');
var ElContainer = require('./el-container');
var ElDatePicker = require('./el-date-picker');
var ElDialog = require('./el-dialog');
var ElDivider = require('./el-divider');
var ElDrawer = require('./el-drawer');
var ElDropdown = require('./el-dropdown');
var ElDropdownItem = require('./el-dropdown-item');
var ElDropdownMenu = require('./el-dropdown-menu');
var ElEmpty = require('./el-empty');
var ElFooter = require('./el-footer');
var ElForm = require('./el-form');
var ElFormItem = require('./el-form-item');
var ElHeader = require('./el-header');
var ElIcon = require('./el-icon');
var ElImage = require('./el-image');
var ElImageViewer = require('./el-image-viewer');
var ElInfiniteScroll = require('./el-infinite-scroll');
var ElInput = require('./el-input');
var ElInputNumber = require('./el-input-number');
var ElLink = require('./el-link');
var ElLoading = require('./el-loading');
var ElMain = require('./el-main');
var ElMenu = require('./el-menu');
var ElMenuItem = require('./el-menu-item');
var ElMenuItemGroup = require('./el-menu-item-group');
var ElMessage = require('./el-message');
var ElMessageBox = require('./el-message-box');
var ElNotification = require('./el-notification');
var ElOption = require('./el-option');
var ElOptionGroup = require('./el-option-group');
var ElPageHeader = require('./el-page-header');
var ElPagination = require('./el-pagination');
var ElPopconfirm = require('./el-popconfirm');
var ElPopover = require('./el-popover');
var ElPopper = require('./el-popper');
var ElProgress = require('./el-progress');
var ElRadio = require('./el-radio');
var ElRadioButton = require('./el-radio-button');
var ElRadioGroup = require('./el-radio-group');
var ElRate = require('./el-rate');
var ElRow = require('./el-row');
var ElScrollbar = require('./el-scrollbar');
var ElSelect = require('./el-select');
var ElSlider = require('./el-slider');
var ElStep = require('./el-step');
var ElSteps = require('./el-steps');
var ElSubmenu = require('./el-submenu');
var ElSwitch = require('./el-switch');
var ElTabPane = require('./el-tab-pane');
var ElTable = require('./el-table');
var ElTableColumn = require('./el-table-column');
var ElTabs = require('./el-tabs');
var ElTag = require('./el-tag');
var ElTimePicker = require('./el-time-picker');
var ElTimeSelect = require('./el-time-select');
var ElTimeline = require('./el-timeline');
var ElTimelineItem = require('./el-timeline-item');
var ElTooltip = require('./el-tooltip');
var ElTransfer = require('./el-transfer');
var ElTree = require('./el-tree');
var ElUpload = require('./el-upload');
var ElSpace = require('./el-space');
var ElSkeleton = require('./el-skeleton');
var ElSkeletonItem = require('./el-skeleton-item');
var ElCheckTag = require('./el-check-tag');
var ElDescriptions = require('./el-descriptions');
var ElDescriptionsItem = require('./el-descriptions-item');
var ElResult = require('./el-result');
var ElSelectV2 = require('./el-select-v2');
var locale$1 = require('./locale');
var config = require('./utils/config');
var isServer = require('./utils/isServer');
var dayjs = require('dayjs');

function _interopDefaultLegacy (e) { return e && typeof e === 'object' && 'default' in e ? e : { 'default': e }; }

var ElAffix__default = /*#__PURE__*/_interopDefaultLegacy(ElAffix);
var ElAlert__default = /*#__PURE__*/_interopDefaultLegacy(ElAlert);
var ElAside__default = /*#__PURE__*/_interopDefaultLegacy(ElAside);
var ElAutocomplete__default = /*#__PURE__*/_interopDefaultLegacy(ElAutocomplete);
var ElAvatar__default = /*#__PURE__*/_interopDefaultLegacy(ElAvatar);
var ElBacktop__default = /*#__PURE__*/_interopDefaultLegacy(ElBacktop);
var ElBadge__default = /*#__PURE__*/_interopDefaultLegacy(ElBadge);
var ElBreadcrumb__default = /*#__PURE__*/_interopDefaultLegacy(ElBreadcrumb);
var ElBreadcrumbItem__default = /*#__PURE__*/_interopDefaultLegacy(ElBreadcrumbItem);
var ElButton__default = /*#__PURE__*/_interopDefaultLegacy(ElButton);
var ElButtonGroup__default = /*#__PURE__*/_interopDefaultLegacy(ElButtonGroup);
var ElCalendar__default = /*#__PURE__*/_interopDefaultLegacy(ElCalendar);
var ElCard__default = /*#__PURE__*/_interopDefaultLegacy(ElCard);
var ElCarousel__default = /*#__PURE__*/_interopDefaultLegacy(ElCarousel);
var ElCarouselItem__default = /*#__PURE__*/_interopDefaultLegacy(ElCarouselItem);
var ElCascader__default = /*#__PURE__*/_interopDefaultLegacy(ElCascader);
var ElCascaderPanel__default = /*#__PURE__*/_interopDefaultLegacy(ElCascaderPanel);
var ElCheckbox__default = /*#__PURE__*/_interopDefaultLegacy(ElCheckbox);
var ElCheckboxButton__default = /*#__PURE__*/_interopDefaultLegacy(ElCheckboxButton);
var ElCheckboxGroup__default = /*#__PURE__*/_interopDefaultLegacy(ElCheckboxGroup);
var ElCol__default = /*#__PURE__*/_interopDefaultLegacy(ElCol);
var ElCollapse__default = /*#__PURE__*/_interopDefaultLegacy(ElCollapse);
var ElCollapseItem__default = /*#__PURE__*/_interopDefaultLegacy(ElCollapseItem);
var ElCollapseTransition__default = /*#__PURE__*/_interopDefaultLegacy(ElCollapseTransition);
var ElColorPicker__default = /*#__PURE__*/_interopDefaultLegacy(ElColorPicker);
var ElContainer__default = /*#__PURE__*/_interopDefaultLegacy(ElContainer);
var ElDatePicker__default = /*#__PURE__*/_interopDefaultLegacy(ElDatePicker);
var ElDialog__default = /*#__PURE__*/_interopDefaultLegacy(ElDialog);
var ElDivider__default = /*#__PURE__*/_interopDefaultLegacy(ElDivider);
var ElDrawer__default = /*#__PURE__*/_interopDefaultLegacy(ElDrawer);
var ElDropdown__default = /*#__PURE__*/_interopDefaultLegacy(ElDropdown);
var ElDropdownItem__default = /*#__PURE__*/_interopDefaultLegacy(ElDropdownItem);
var ElDropdownMenu__default = /*#__PURE__*/_interopDefaultLegacy(ElDropdownMenu);
var ElEmpty__default = /*#__PURE__*/_interopDefaultLegacy(ElEmpty);
var ElFooter__default = /*#__PURE__*/_interopDefaultLegacy(ElFooter);
var ElForm__default = /*#__PURE__*/_interopDefaultLegacy(ElForm);
var ElFormItem__default = /*#__PURE__*/_interopDefaultLegacy(ElFormItem);
var ElHeader__default = /*#__PURE__*/_interopDefaultLegacy(ElHeader);
var ElIcon__default = /*#__PURE__*/_interopDefaultLegacy(ElIcon);
var ElImage__default = /*#__PURE__*/_interopDefaultLegacy(ElImage);
var ElImageViewer__default = /*#__PURE__*/_interopDefaultLegacy(ElImageViewer);
var ElInfiniteScroll__default = /*#__PURE__*/_interopDefaultLegacy(ElInfiniteScroll);
var ElInput__default = /*#__PURE__*/_interopDefaultLegacy(ElInput);
var ElInputNumber__default = /*#__PURE__*/_interopDefaultLegacy(ElInputNumber);
var ElLink__default = /*#__PURE__*/_interopDefaultLegacy(ElLink);
var ElLoading__default = /*#__PURE__*/_interopDefaultLegacy(ElLoading);
var ElMain__default = /*#__PURE__*/_interopDefaultLegacy(ElMain);
var ElMenu__default = /*#__PURE__*/_interopDefaultLegacy(ElMenu);
var ElMenuItem__default = /*#__PURE__*/_interopDefaultLegacy(ElMenuItem);
var ElMenuItemGroup__default = /*#__PURE__*/_interopDefaultLegacy(ElMenuItemGroup);
var ElMessage__default = /*#__PURE__*/_interopDefaultLegacy(ElMessage);
var ElMessageBox__default = /*#__PURE__*/_interopDefaultLegacy(ElMessageBox);
var ElNotification__default = /*#__PURE__*/_interopDefaultLegacy(ElNotification);
var ElOption__default = /*#__PURE__*/_interopDefaultLegacy(ElOption);
var ElOptionGroup__default = /*#__PURE__*/_interopDefaultLegacy(ElOptionGroup);
var ElPageHeader__default = /*#__PURE__*/_interopDefaultLegacy(ElPageHeader);
var ElPagination__default = /*#__PURE__*/_interopDefaultLegacy(ElPagination);
var ElPopconfirm__default = /*#__PURE__*/_interopDefaultLegacy(ElPopconfirm);
var ElPopover__default = /*#__PURE__*/_interopDefaultLegacy(ElPopover);
var ElPopper__default = /*#__PURE__*/_interopDefaultLegacy(ElPopper);
var ElProgress__default = /*#__PURE__*/_interopDefaultLegacy(ElProgress);
var ElRadio__default = /*#__PURE__*/_interopDefaultLegacy(ElRadio);
var ElRadioButton__default = /*#__PURE__*/_interopDefaultLegacy(ElRadioButton);
var ElRadioGroup__default = /*#__PURE__*/_interopDefaultLegacy(ElRadioGroup);
var ElRate__default = /*#__PURE__*/_interopDefaultLegacy(ElRate);
var ElRow__default = /*#__PURE__*/_interopDefaultLegacy(ElRow);
var ElScrollbar__default = /*#__PURE__*/_interopDefaultLegacy(ElScrollbar);
var ElSelect__default = /*#__PURE__*/_interopDefaultLegacy(ElSelect);
var ElSlider__default = /*#__PURE__*/_interopDefaultLegacy(ElSlider);
var ElStep__default = /*#__PURE__*/_interopDefaultLegacy(ElStep);
var ElSteps__default = /*#__PURE__*/_interopDefaultLegacy(ElSteps);
var ElSubmenu__default = /*#__PURE__*/_interopDefaultLegacy(ElSubmenu);
var ElSwitch__default = /*#__PURE__*/_interopDefaultLegacy(ElSwitch);
var ElTabPane__default = /*#__PURE__*/_interopDefaultLegacy(ElTabPane);
var ElTable__default = /*#__PURE__*/_interopDefaultLegacy(ElTable);
var ElTableColumn__default = /*#__PURE__*/_interopDefaultLegacy(ElTableColumn);
var ElTabs__default = /*#__PURE__*/_interopDefaultLegacy(ElTabs);
var ElTag__default = /*#__PURE__*/_interopDefaultLegacy(ElTag);
var ElTimePicker__default = /*#__PURE__*/_interopDefaultLegacy(ElTimePicker);
var ElTimeSelect__default = /*#__PURE__*/_interopDefaultLegacy(ElTimeSelect);
var ElTimeline__default = /*#__PURE__*/_interopDefaultLegacy(ElTimeline);
var ElTimelineItem__default = /*#__PURE__*/_interopDefaultLegacy(ElTimelineItem);
var ElTooltip__default = /*#__PURE__*/_interopDefaultLegacy(ElTooltip);
var ElTransfer__default = /*#__PURE__*/_interopDefaultLegacy(ElTransfer);
var ElTree__default = /*#__PURE__*/_interopDefaultLegacy(ElTree);
var ElUpload__default = /*#__PURE__*/_interopDefaultLegacy(ElUpload);
var ElSpace__default = /*#__PURE__*/_interopDefaultLegacy(ElSpace);
var ElSkeleton__default = /*#__PURE__*/_interopDefaultLegacy(ElSkeleton);
var ElSkeletonItem__default = /*#__PURE__*/_interopDefaultLegacy(ElSkeletonItem);
var ElCheckTag__default = /*#__PURE__*/_interopDefaultLegacy(ElCheckTag);
var ElDescriptions__default = /*#__PURE__*/_interopDefaultLegacy(ElDescriptions);
var ElDescriptionsItem__default = /*#__PURE__*/_interopDefaultLegacy(ElDescriptionsItem);
var ElResult__default = /*#__PURE__*/_interopDefaultLegacy(ElResult);
var ElSelectV2__default = /*#__PURE__*/_interopDefaultLegacy(ElSelectV2);
var isServer__default = /*#__PURE__*/_interopDefaultLegacy(isServer);
var dayjs__default = /*#__PURE__*/_interopDefaultLegacy(dayjs);

const version = '1.0.2-beta.48';

if (!isServer__default['default']) {
    const _window = window;
    if (!_window.dayjs) {
        _window.dayjs = dayjs__default['default'];
    }
}
const version$1 = version;
const locale = locale$1.use;
const defaultInstallOpt = {
    size: '',
    zIndex: 2000,
};
const components = [
    ElAffix__default['default'],
    ElAlert__default['default'],
    ElAside__default['default'],
    ElAutocomplete__default['default'],
    ElAvatar__default['default'],
    ElBacktop__default['default'],
    ElBadge__default['default'],
    ElBreadcrumb__default['default'],
    ElBreadcrumbItem__default['default'],
    ElButton__default['default'],
    ElButtonGroup__default['default'],
    ElCalendar__default['default'],
    ElCard__default['default'],
    ElCarousel__default['default'],
    ElCarouselItem__default['default'],
    ElCascader__default['default'],
    ElCascaderPanel__default['default'],
    ElCheckbox__default['default'],
    ElCheckboxButton__default['default'],
    ElCheckboxGroup__default['default'],
    ElCheckTag__default['default'],
    ElCol__default['default'],
    ElCollapse__default['default'],
    ElCollapseItem__default['default'],
    ElCollapseTransition__default['default'],
    ElColorPicker__default['default'],
    ElContainer__default['default'],
    ElDatePicker__default['default'],
    ElDialog__default['default'],
    ElDivider__default['default'],
    ElDrawer__default['default'],
    ElDropdown__default['default'],
    ElDropdownItem__default['default'],
    ElDropdownMenu__default['default'],
    ElEmpty__default['default'],
    ElFooter__default['default'],
    ElForm__default['default'],
    ElFormItem__default['default'],
    ElHeader__default['default'],
    ElIcon__default['default'],
    ElImage__default['default'],
    ElImageViewer__default['default'],
    ElInput__default['default'],
    ElInputNumber__default['default'],
    ElLink__default['default'],
    ElMain__default['default'],
    ElMenu__default['default'],
    ElMenuItem__default['default'],
    ElMenuItemGroup__default['default'],
    ElOption__default['default'],
    ElOptionGroup__default['default'],
    ElPageHeader__default['default'],
    ElPagination__default['default'],
    ElPopconfirm__default['default'],
    ElPopover__default['default'],
    ElPopper__default['default'],
    ElProgress__default['default'],
    ElRadio__default['default'],
    ElRadioButton__default['default'],
    ElRadioGroup__default['default'],
    ElRate__default['default'],
    ElRow__default['default'],
    ElScrollbar__default['default'],
    ElSelect__default['default'],
    ElSlider__default['default'],
    ElStep__default['default'],
    ElSteps__default['default'],
    ElSubmenu__default['default'],
    ElSwitch__default['default'],
    ElTabPane__default['default'],
    ElTable__default['default'],
    ElTableColumn__default['default'],
    ElTabs__default['default'],
    ElTag__default['default'],
    ElTimePicker__default['default'],
    ElTimeSelect__default['default'],
    ElTimeline__default['default'],
    ElTimelineItem__default['default'],
    ElTooltip__default['default'],
    ElTransfer__default['default'],
    ElTree__default['default'],
    ElUpload__default['default'],
    ElSpace__default['default'],
    ElSkeleton__default['default'],
    ElSkeletonItem__default['default'],
    ElDescriptions__default['default'],
    ElDescriptionsItem__default['default'],
    ElResult__default['default'],
    ElSelectV2__default['default'],
];
const plugins = [
    ElInfiniteScroll__default['default'],
    ElLoading__default['default'],
    ElMessage__default['default'],
    ElMessageBox__default['default'],
    ElNotification__default['default'],
];
const install = (app, opt) => {
    const option = Object.assign(defaultInstallOpt, opt);
    locale(option.locale);
    if (option.i18n) {
        locale$1.i18n(option.i18n);
    }
    app.config.globalProperties.$ELEMENT = option;
    config.setConfig(option);
    components.forEach(component => {
        app.component(component.name, component);
    });
    plugins.forEach(plugin => {
        app.use(plugin);
    });
};
var index = {
    version: version$1,
    install,
};

Object.defineProperty(exports, 'ElAffix', {
  enumerable: true,
  get: function () {
    return ElAffix__default['default'];
  }
});
Object.defineProperty(exports, 'ElAlert', {
  enumerable: true,
  get: function () {
    return ElAlert__default['default'];
  }
});
Object.defineProperty(exports, 'ElAside', {
  enumerable: true,
  get: function () {
    return ElAside__default['default'];
  }
});
Object.defineProperty(exports, 'ElAutocomplete', {
  enumerable: true,
  get: function () {
    return ElAutocomplete__default['default'];
  }
});
Object.defineProperty(exports, 'ElAvatar', {
  enumerable: true,
  get: function () {
    return ElAvatar__default['default'];
  }
});
Object.defineProperty(exports, 'ElBacktop', {
  enumerable: true,
  get: function () {
    return ElBacktop__default['default'];
  }
});
Object.defineProperty(exports, 'ElBadge', {
  enumerable: true,
  get: function () {
    return ElBadge__default['default'];
  }
});
Object.defineProperty(exports, 'ElBreadcrumb', {
  enumerable: true,
  get: function () {
    return ElBreadcrumb__default['default'];
  }
});
Object.defineProperty(exports, 'ElBreadcrumbItem', {
  enumerable: true,
  get: function () {
    return ElBreadcrumbItem__default['default'];
  }
});
Object.defineProperty(exports, 'ElButton', {
  enumerable: true,
  get: function () {
    return ElButton__default['default'];
  }
});
Object.defineProperty(exports, 'ElButtonGroup', {
  enumerable: true,
  get: function () {
    return ElButtonGroup__default['default'];
  }
});
Object.defineProperty(exports, 'ElCalendar', {
  enumerable: true,
  get: function () {
    return ElCalendar__default['default'];
  }
});
Object.defineProperty(exports, 'ElCard', {
  enumerable: true,
  get: function () {
    return ElCard__default['default'];
  }
});
Object.defineProperty(exports, 'ElCarousel', {
  enumerable: true,
  get: function () {
    return ElCarousel__default['default'];
  }
});
Object.defineProperty(exports, 'ElCarouselItem', {
  enumerable: true,
  get: function () {
    return ElCarouselItem__default['default'];
  }
});
Object.defineProperty(exports, 'ElCascader', {
  enumerable: true,
  get: function () {
    return ElCascader__default['default'];
  }
});
Object.defineProperty(exports, 'ElCascaderPanel', {
  enumerable: true,
  get: function () {
    return ElCascaderPanel__default['default'];
  }
});
Object.defineProperty(exports, 'ElCheckbox', {
  enumerable: true,
  get: function () {
    return ElCheckbox__default['default'];
  }
});
Object.defineProperty(exports, 'ElCheckboxButton', {
  enumerable: true,
  get: function () {
    return ElCheckboxButton__default['default'];
  }
});
Object.defineProperty(exports, 'ElCheckboxGroup', {
  enumerable: true,
  get: function () {
    return ElCheckboxGroup__default['default'];
  }
});
Object.defineProperty(exports, 'ElCol', {
  enumerable: true,
  get: function () {
    return ElCol__default['default'];
  }
});
Object.defineProperty(exports, 'ElCollapse', {
  enumerable: true,
  get: function () {
    return ElCollapse__default['default'];
  }
});
Object.defineProperty(exports, 'ElCollapseItem', {
  enumerable: true,
  get: function () {
    return ElCollapseItem__default['default'];
  }
});
Object.defineProperty(exports, 'ElCollapseTransition', {
  enumerable: true,
  get: function () {
    return ElCollapseTransition__default['default'];
  }
});
Object.defineProperty(exports, 'ElColorPicker', {
  enumerable: true,
  get: function () {
    return ElColorPicker__default['default'];
  }
});
Object.defineProperty(exports, 'ElContainer', {
  enumerable: true,
  get: function () {
    return ElContainer__default['default'];
  }
});
Object.defineProperty(exports, 'ElDatePicker', {
  enumerable: true,
  get: function () {
    return ElDatePicker__default['default'];
  }
});
Object.defineProperty(exports, 'ElDialog', {
  enumerable: true,
  get: function () {
    return ElDialog__default['default'];
  }
});
Object.defineProperty(exports, 'ElDivider', {
  enumerable: true,
  get: function () {
    return ElDivider__default['default'];
  }
});
Object.defineProperty(exports, 'ElDrawer', {
  enumerable: true,
  get: function () {
    return ElDrawer__default['default'];
  }
});
Object.defineProperty(exports, 'ElDropdown', {
  enumerable: true,
  get: function () {
    return ElDropdown__default['default'];
  }
});
Object.defineProperty(exports, 'ElDropdownItem', {
  enumerable: true,
  get: function () {
    return ElDropdownItem__default['default'];
  }
});
Object.defineProperty(exports, 'ElDropdownMenu', {
  enumerable: true,
  get: function () {
    return ElDropdownMenu__default['default'];
  }
});
Object.defineProperty(exports, 'ElEmpty', {
  enumerable: true,
  get: function () {
    return ElEmpty__default['default'];
  }
});
Object.defineProperty(exports, 'ElFooter', {
  enumerable: true,
  get: function () {
    return ElFooter__default['default'];
  }
});
Object.defineProperty(exports, 'ElForm', {
  enumerable: true,
  get: function () {
    return ElForm__default['default'];
  }
});
Object.defineProperty(exports, 'ElFormItem', {
  enumerable: true,
  get: function () {
    return ElFormItem__default['default'];
  }
});
Object.defineProperty(exports, 'ElHeader', {
  enumerable: true,
  get: function () {
    return ElHeader__default['default'];
  }
});
Object.defineProperty(exports, 'ElIcon', {
  enumerable: true,
  get: function () {
    return ElIcon__default['default'];
  }
});
Object.defineProperty(exports, 'ElImage', {
  enumerable: true,
  get: function () {
    return ElImage__default['default'];
  }
});
Object.defineProperty(exports, 'ElImageViewer', {
  enumerable: true,
  get: function () {
    return ElImageViewer__default['default'];
  }
});
Object.defineProperty(exports, 'ElInfiniteScroll', {
  enumerable: true,
  get: function () {
    return ElInfiniteScroll__default['default'];
  }
});
Object.defineProperty(exports, 'ElInput', {
  enumerable: true,
  get: function () {
    return ElInput__default['default'];
  }
});
Object.defineProperty(exports, 'ElInputNumber', {
  enumerable: true,
  get: function () {
    return ElInputNumber__default['default'];
  }
});
Object.defineProperty(exports, 'ElLink', {
  enumerable: true,
  get: function () {
    return ElLink__default['default'];
  }
});
Object.defineProperty(exports, 'ElLoading', {
  enumerable: true,
  get: function () {
    return ElLoading__default['default'];
  }
});
Object.defineProperty(exports, 'ElMain', {
  enumerable: true,
  get: function () {
    return ElMain__default['default'];
  }
});
Object.defineProperty(exports, 'ElMenu', {
  enumerable: true,
  get: function () {
    return ElMenu__default['default'];
  }
});
Object.defineProperty(exports, 'ElMenuItem', {
  enumerable: true,
  get: function () {
    return ElMenuItem__default['default'];
  }
});
Object.defineProperty(exports, 'ElMenuItemGroup', {
  enumerable: true,
  get: function () {
    return ElMenuItemGroup__default['default'];
  }
});
Object.defineProperty(exports, 'ElMessage', {
  enumerable: true,
  get: function () {
    return ElMessage__default['default'];
  }
});
Object.defineProperty(exports, 'ElMessageBox', {
  enumerable: true,
  get: function () {
    return ElMessageBox__default['default'];
  }
});
Object.defineProperty(exports, 'ElNotification', {
  enumerable: true,
  get: function () {
    return ElNotification__default['default'];
  }
});
Object.defineProperty(exports, 'ElOption', {
  enumerable: true,
  get: function () {
    return ElOption__default['default'];
  }
});
Object.defineProperty(exports, 'ElOptionGroup', {
  enumerable: true,
  get: function () {
    return ElOptionGroup__default['default'];
  }
});
Object.defineProperty(exports, 'ElPageHeader', {
  enumerable: true,
  get: function () {
    return ElPageHeader__default['default'];
  }
});
Object.defineProperty(exports, 'ElPagination', {
  enumerable: true,
  get: function () {
    return ElPagination__default['default'];
  }
});
Object.defineProperty(exports, 'ElPopconfirm', {
  enumerable: true,
  get: function () {
    return ElPopconfirm__default['default'];
  }
});
Object.defineProperty(exports, 'ElPopover', {
  enumerable: true,
  get: function () {
    return ElPopover__default['default'];
  }
});
Object.defineProperty(exports, 'ElPopper', {
  enumerable: true,
  get: function () {
    return ElPopper__default['default'];
  }
});
Object.defineProperty(exports, 'ElProgress', {
  enumerable: true,
  get: function () {
    return ElProgress__default['default'];
  }
});
Object.defineProperty(exports, 'ElRadio', {
  enumerable: true,
  get: function () {
    return ElRadio__default['default'];
  }
});
Object.defineProperty(exports, 'ElRadioButton', {
  enumerable: true,
  get: function () {
    return ElRadioButton__default['default'];
  }
});
Object.defineProperty(exports, 'ElRadioGroup', {
  enumerable: true,
  get: function () {
    return ElRadioGroup__default['default'];
  }
});
Object.defineProperty(exports, 'ElRate', {
  enumerable: true,
  get: function () {
    return ElRate__default['default'];
  }
});
Object.defineProperty(exports, 'ElRow', {
  enumerable: true,
  get: function () {
    return ElRow__default['default'];
  }
});
Object.defineProperty(exports, 'ElScrollbar', {
  enumerable: true,
  get: function () {
    return ElScrollbar__default['default'];
  }
});
Object.defineProperty(exports, 'ElSelect', {
  enumerable: true,
  get: function () {
    return ElSelect__default['default'];
  }
});
Object.defineProperty(exports, 'ElSlider', {
  enumerable: true,
  get: function () {
    return ElSlider__default['default'];
  }
});
Object.defineProperty(exports, 'ElStep', {
  enumerable: true,
  get: function () {
    return ElStep__default['default'];
  }
});
Object.defineProperty(exports, 'ElSteps', {
  enumerable: true,
  get: function () {
    return ElSteps__default['default'];
  }
});
Object.defineProperty(exports, 'ElSubmenu', {
  enumerable: true,
  get: function () {
    return ElSubmenu__default['default'];
  }
});
Object.defineProperty(exports, 'ElSwitch', {
  enumerable: true,
  get: function () {
    return ElSwitch__default['default'];
  }
});
Object.defineProperty(exports, 'ElTabPane', {
  enumerable: true,
  get: function () {
    return ElTabPane__default['default'];
  }
});
Object.defineProperty(exports, 'ElTable', {
  enumerable: true,
  get: function () {
    return ElTable__default['default'];
  }
});
Object.defineProperty(exports, 'ElTableColumn', {
  enumerable: true,
  get: function () {
    return ElTableColumn__default['default'];
  }
});
Object.defineProperty(exports, 'ElTabs', {
  enumerable: true,
  get: function () {
    return ElTabs__default['default'];
  }
});
Object.defineProperty(exports, 'ElTag', {
  enumerable: true,
  get: function () {
    return ElTag__default['default'];
  }
});
Object.defineProperty(exports, 'ElTimePicker', {
  enumerable: true,
  get: function () {
    return ElTimePicker__default['default'];
  }
});
Object.defineProperty(exports, 'ElTimeSelect', {
  enumerable: true,
  get: function () {
    return ElTimeSelect__default['default'];
  }
});
Object.defineProperty(exports, 'ElTimeline', {
  enumerable: true,
  get: function () {
    return ElTimeline__default['default'];
  }
});
Object.defineProperty(exports, 'ElTimelineItem', {
  enumerable: true,
  get: function () {
    return ElTimelineItem__default['default'];
  }
});
Object.defineProperty(exports, 'ElTooltip', {
  enumerable: true,
  get: function () {
    return ElTooltip__default['default'];
  }
});
Object.defineProperty(exports, 'ElTransfer', {
  enumerable: true,
  get: function () {
    return ElTransfer__default['default'];
  }
});
Object.defineProperty(exports, 'ElTree', {
  enumerable: true,
  get: function () {
    return ElTree__default['default'];
  }
});
Object.defineProperty(exports, 'ElUpload', {
  enumerable: true,
  get: function () {
    return ElUpload__default['default'];
  }
});
Object.defineProperty(exports, 'ElSpace', {
  enumerable: true,
  get: function () {
    return ElSpace__default['default'];
  }
});
Object.defineProperty(exports, 'ElSkeleton', {
  enumerable: true,
  get: function () {
    return ElSkeleton__default['default'];
  }
});
Object.defineProperty(exports, 'ElSkeletonItem', {
  enumerable: true,
  get: function () {
    return ElSkeletonItem__default['default'];
  }
});
Object.defineProperty(exports, 'ElCheckTag', {
  enumerable: true,
  get: function () {
    return ElCheckTag__default['default'];
  }
});
Object.defineProperty(exports, 'ElDescriptions', {
  enumerable: true,
  get: function () {
    return ElDescriptions__default['default'];
  }
});
Object.defineProperty(exports, 'ElDescriptionsItem', {
  enumerable: true,
  get: function () {
    return ElDescriptionsItem__default['default'];
  }
});
Object.defineProperty(exports, 'ElResult', {
  enumerable: true,
  get: function () {
    return ElResult__default['default'];
  }
});
Object.defineProperty(exports, 'ElSelectV2', {
  enumerable: true,
  get: function () {
    return ElSelectV2__default['default'];
  }
});
exports.default = index;
exports.install = install;
exports.locale = locale;
exports.version = version$1;
