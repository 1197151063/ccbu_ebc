'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
var ElInput = require('../el-input');
var util = require('../utils/util');
var resizeEvent = require('../utils/resize-event');
var ElTag = require('../el-tag');
var ElPopper = require('../el-popper');
var ElScrollbar = require('../el-scrollbar');
var directives = require('../directives');
var locale = require('../locale');
var constants = require('../utils/constants');
var validators = require('../utils/validators');
var mitt = require('mitt');
var aria = require('../utils/aria');
var isServer = require('../utils/isServer');
var scrollIntoView = require('../utils/scroll-into-view');
var lodashDebounce = require('lodash/debounce');
var isDef = require('../utils/isDef');
var form = require('../el-form');
var isEqual = require('lodash/isEqual');
var hooks = require('../hooks');

function _interopDefaultLegacy (e) { return e && typeof e === 'object' && 'default' in e ? e : { 'default': e }; }

var ElInput__default = /*#__PURE__*/_interopDefaultLegacy(ElInput);
var ElTag__default = /*#__PURE__*/_interopDefaultLegacy(ElTag);
var ElPopper__default = /*#__PURE__*/_interopDefaultLegacy(ElPopper);
var ElScrollbar__default = /*#__PURE__*/_interopDefaultLegacy(ElScrollbar);
var mitt__default = /*#__PURE__*/_interopDefaultLegacy(mitt);
var isServer__default = /*#__PURE__*/_interopDefaultLegacy(isServer);
var scrollIntoView__default = /*#__PURE__*/_interopDefaultLegacy(scrollIntoView);
var lodashDebounce__default = /*#__PURE__*/_interopDefaultLegacy(lodashDebounce);
var isEqual__default = /*#__PURE__*/_interopDefaultLegacy(isEqual);

const selectGroupKey = "ElSelectGroup";
const selectKey = "ElSelect";
const selectEvents = {
  queryChange: "elOptionQueryChange",
  groupQueryChange: "elOptionGroupQueryChange"
};

function useOption(props, states) {
  const select = vue.inject(selectKey);
  const selectGroup = vue.inject(selectGroupKey, { disabled: false });
  const isObject = vue.computed(() => {
    return Object.prototype.toString.call(props.value).toLowerCase() === "[object object]";
  });
  const itemSelected = vue.computed(() => {
    if (!select.props.multiple) {
      return isEqual(props.value, select.props.modelValue);
    } else {
      return contains(select.props.modelValue, props.value);
    }
  });
  const limitReached = vue.computed(() => {
    if (select.props.multiple) {
      const modelValue = select.props.modelValue || [];
      return !itemSelected.value && modelValue.length >= select.props.multipleLimit && select.props.multipleLimit > 0;
    } else {
      return false;
    }
  });
  const currentLabel = vue.computed(() => {
    return props.label || (isObject.value ? "" : props.value);
  });
  const currentValue = vue.computed(() => {
    return props.value || props.label || "";
  });
  const isDisabled = vue.computed(() => {
    return props.disabled || states.groupDisabled || limitReached.value;
  });
  const instance = vue.getCurrentInstance();
  const contains = (arr = [], target) => {
    if (!isObject.value) {
      return arr && arr.indexOf(target) > -1;
    } else {
      const valueKey = select.props.valueKey;
      return arr && arr.some((item) => {
        return util.getValueByPath(item, valueKey) === util.getValueByPath(target, valueKey);
      });
    }
  };
  const isEqual = (a, b) => {
    if (!isObject.value) {
      return a === b;
    } else {
      const { valueKey } = select.props;
      return util.getValueByPath(a, valueKey) === util.getValueByPath(b, valueKey);
    }
  };
  const hoverItem = () => {
    if (!props.disabled && !selectGroup.disabled) {
      select.hoverIndex = select.optionsArray.indexOf(instance);
    }
  };
  const queryChange = (query) => {
    const regexp = new RegExp(util.escapeRegexpString(query), "i");
    states.visible = regexp.test(currentLabel.value) || props.created;
    if (!states.visible) {
      select.filteredOptionsCount--;
    }
  };
  vue.watch(() => currentLabel.value, () => {
    if (!props.created && !select.props.remote)
      select.setSelected();
  });
  vue.watch(() => props.value, (val, oldVal) => {
    const { remote, valueKey } = select.props;
    if (!props.created && !remote) {
      if (valueKey && typeof val === "object" && typeof oldVal === "object" && val[valueKey] === oldVal[valueKey]) {
        return;
      }
      select.setSelected();
    }
  });
  vue.watch(() => selectGroup.disabled, () => {
    states.groupDisabled = selectGroup.disabled;
  }, { immediate: true });
  select.selectEmitter.on(selectEvents.queryChange, queryChange);
  return {
    select,
    currentLabel,
    currentValue,
    itemSelected,
    isDisabled,
    hoverItem
  };
}

var script = vue.defineComponent({
  name: "ElOption",
  componentName: "ElOption",
  props: {
    value: {
      required: true,
      type: [String, Number, Boolean, Object]
    },
    label: [String, Number],
    created: Boolean,
    disabled: {
      type: Boolean,
      default: false
    }
  },
  setup(props) {
    const states = vue.reactive({
      index: -1,
      groupDisabled: false,
      visible: true,
      hitState: false,
      hover: false
    });
    const {
      currentLabel,
      itemSelected,
      isDisabled,
      select,
      hoverItem
    } = useOption(props, states);
    const {
      visible,
      hover
    } = vue.toRefs(states);
    const vm = vue.getCurrentInstance().proxy;
    select.onOptionCreate(vm);
    vue.onBeforeUnmount(() => {
      const { selected } = select;
      let selectedOptions = select.props.multiple ? selected : [selected];
      const doesExist = select.cachedOptions.has(props.value);
      const doesSelected = selectedOptions.some((item) => {
        return item.value === vm.value;
      });
      if (doesExist && !doesSelected) {
        select.cachedOptions.delete(props.value);
      }
      select.onOptionDestroy(props.value);
    });
    function selectOptionClick() {
      if (props.disabled !== true && states.groupDisabled !== true) {
        select.handleOptionSelect(vm, true);
      }
    }
    return {
      currentLabel,
      itemSelected,
      isDisabled,
      select,
      hoverItem,
      visible,
      hover,
      selectOptionClick
    };
  }
});

function render(_ctx, _cache, $props, $setup, $data, $options) {
  return vue.withDirectives((vue.openBlock(), vue.createBlock("li", {
    class: ["el-select-dropdown__item", {
      "selected": _ctx.itemSelected,
      "is-disabled": _ctx.isDisabled,
      "hover": _ctx.hover
    }],
    onMouseenter: _cache[1] || (_cache[1] = (...args) => _ctx.hoverItem && _ctx.hoverItem(...args)),
    onClick: _cache[2] || (_cache[2] = vue.withModifiers((...args) => _ctx.selectOptionClick && _ctx.selectOptionClick(...args), ["stop"]))
  }, [
    vue.renderSlot(_ctx.$slots, "default", {}, () => [
      vue.createVNode("span", null, vue.toDisplayString(_ctx.currentLabel), 1)
    ])
  ], 34)), [
    [vue.vShow, _ctx.visible]
  ]);
}

script.render = render;
script.__file = "packages/select/src/option.vue";

var script$1 = vue.defineComponent({
  name: "ElSelectDropdown",
  componentName: "ElSelectDropdown",
  setup() {
    const select = vue.inject(selectKey);
    const popperClass = vue.computed(() => select.props.popperClass);
    const isMultiple = vue.computed(() => select.props.multiple);
    const minWidth = vue.ref("");
    function updateMinWidth() {
      var _a;
      minWidth.value = ((_a = select.selectWrapper) == null ? void 0 : _a.getBoundingClientRect().width) + "px";
    }
    vue.onMounted(() => {
      resizeEvent.addResizeListener(select.selectWrapper, updateMinWidth);
    });
    vue.onBeforeUnmount(() => {
      resizeEvent.removeResizeListener(select.selectWrapper, updateMinWidth);
    });
    return {
      minWidth,
      popperClass,
      isMultiple
    };
  }
});

function render$1(_ctx, _cache, $props, $setup, $data, $options) {
  return vue.openBlock(), vue.createBlock("div", {
    class: ["el-select-dropdown", [{ "is-multiple": _ctx.isMultiple }, _ctx.popperClass]],
    style: { minWidth: _ctx.minWidth }
  }, [
    vue.renderSlot(_ctx.$slots, "default")
  ], 6);
}

script$1.render = render$1;
script$1.__file = "packages/select/src/select-dropdown.vue";

/**
 * Make a map and return a function for checking if a key
 * is in that map.
 * IMPORTANT: all calls of this function must be prefixed with
 * \/\*#\_\_PURE\_\_\*\/
 * So that rollup can tree-shake them if necessary.
 */
const EMPTY_OBJ = (process.env.NODE_ENV !== 'production')
    ? Object.freeze({})
    : {};
const EMPTY_ARR = (process.env.NODE_ENV !== 'production') ? Object.freeze([]) : [];
const isObject = (val) => val !== null && typeof val === 'object';
const objectToString = Object.prototype.toString;
const toTypeString = (value) => objectToString.call(value);
const toRawType = (value) => {
    // extract "RawType" from strings like "[object RawType]"
    return toTypeString(value).slice(8, -1);
};

function useSelectStates(props) {
  const selectEmitter = mitt__default['default']();
  return vue.reactive({
    options: new Map(),
    cachedOptions: new Map(),
    createdLabel: null,
    createdSelected: false,
    selected: props.multiple ? [] : {},
    inputLength: 20,
    inputWidth: 0,
    initialInputHeight: 0,
    optionsCount: 0,
    filteredOptionsCount: 0,
    visible: false,
    softFocus: false,
    selectedLabel: "",
    hoverIndex: -1,
    query: "",
    previousQuery: null,
    inputHovering: false,
    cachedPlaceHolder: "",
    currentPlaceholder: locale.t("el.select.placeholder"),
    menuVisibleOnFocus: false,
    isOnComposition: false,
    isSilentBlur: false,
    selectEmitter,
    prefixWidth: null,
    tagInMultiLine: false
  });
}
const useSelect = (props, states, ctx) => {
  const ELEMENT = util.useGlobalConfig();
  const reference = vue.ref(null);
  const input = vue.ref(null);
  const popper = vue.ref(null);
  const tags = vue.ref(null);
  const selectWrapper = vue.ref(null);
  const scrollbar = vue.ref(null);
  const hoverOption = vue.ref(-1);
  const elForm = vue.inject(form.elFormKey, {});
  const elFormItem = vue.inject(form.elFormItemKey, {});
  const readonly = vue.computed(() => !props.filterable || props.multiple || !util.isIE() && !util.isEdge() && !states.visible);
  const selectDisabled = vue.computed(() => props.disabled || elForm.disabled);
  const showClose = vue.computed(() => {
    const hasValue = props.multiple ? Array.isArray(props.modelValue) && props.modelValue.length > 0 : props.modelValue !== void 0 && props.modelValue !== null && props.modelValue !== "";
    const criteria = props.clearable && !selectDisabled.value && states.inputHovering && hasValue;
    return criteria;
  });
  const iconClass = vue.computed(() => props.remote && props.filterable ? "" : states.visible ? "arrow-up is-reverse" : "arrow-up");
  const debounce = vue.computed(() => props.remote ? 300 : 0);
  const emptyText = vue.computed(() => {
    if (props.loading) {
      return props.loadingText || locale.t("el.select.loading");
    } else {
      if (props.remote && states.query === "" && states.options.size === 0)
        return false;
      if (props.filterable && states.query && states.options.size > 0 && states.filteredOptionsCount === 0) {
        return props.noMatchText || locale.t("el.select.noMatch");
      }
      if (states.options.size === 0) {
        return props.noDataText || locale.t("el.select.noData");
      }
    }
    return null;
  });
  const optionsArray = vue.computed(() => Array.from(states.options.values()));
  const cachedOptionsArray = vue.computed(() => Array.from(states.cachedOptions.values()));
  const showNewOption = vue.computed(() => {
    const hasExistingOption = optionsArray.value.filter((option) => {
      return !option.created;
    }).some((option) => {
      return option.currentLabel === states.query;
    });
    return props.filterable && props.allowCreate && states.query !== "" && !hasExistingOption;
  });
  const selectSize = vue.computed(() => props.size || elFormItem.size || ELEMENT.size);
  const collapseTagSize = vue.computed(() => ["small", "mini"].indexOf(selectSize.value) > -1 ? "mini" : "small");
  const dropMenuVisible = vue.computed(() => states.visible && emptyText.value !== false);
  vue.watch(() => selectDisabled.value, () => {
    vue.nextTick(() => {
      resetInputHeight();
    });
  });
  vue.watch(() => props.placeholder, (val) => {
    states.cachedPlaceHolder = states.currentPlaceholder = val;
  });
  vue.watch(() => props.modelValue, (val, oldVal) => {
    var _a;
    if (props.multiple) {
      resetInputHeight();
      if (val && val.length > 0 || input.value && states.query !== "") {
        states.currentPlaceholder = "";
      } else {
        states.currentPlaceholder = states.cachedPlaceHolder;
      }
      if (props.filterable && !props.reserveKeyword) {
        states.query = "";
        handleQueryChange(states.query);
      }
    }
    setSelected();
    if (props.filterable && !props.multiple) {
      states.inputLength = 20;
    }
    if (!isEqual__default['default'](val, oldVal)) {
      (_a = elFormItem.formItemMitt) == null ? void 0 : _a.emit("el.form.change", val);
    }
  }, {
    flush: "post",
    deep: true
  });
  vue.watch(() => states.visible, (val) => {
    var _a, _b;
    if (!val) {
      input.value && input.value.blur();
      states.query = "";
      states.previousQuery = null;
      states.selectedLabel = "";
      states.inputLength = 20;
      states.menuVisibleOnFocus = false;
      resetHoverIndex();
      vue.nextTick(() => {
        if (input.value && input.value.value === "" && states.selected.length === 0) {
          states.currentPlaceholder = states.cachedPlaceHolder;
        }
      });
      if (!props.multiple) {
        if (states.selected) {
          if (props.filterable && props.allowCreate && states.createdSelected && states.createdLabel) {
            states.selectedLabel = states.createdLabel;
          } else {
            states.selectedLabel = states.selected.currentLabel;
          }
          if (props.filterable)
            states.query = states.selectedLabel;
        }
        if (props.filterable) {
          states.currentPlaceholder = states.cachedPlaceHolder;
        }
      }
    } else {
      (_b = (_a = popper.value) == null ? void 0 : _a.update) == null ? void 0 : _b.call(_a);
      if (props.filterable) {
        states.filteredOptionsCount = states.optionsCount;
        states.query = props.remote ? "" : states.selectedLabel;
        if (props.multiple) {
          input.value.focus();
        } else {
          if (states.selectedLabel) {
            states.currentPlaceholder = states.selectedLabel;
            states.selectedLabel = "";
          }
        }
        handleQueryChange(states.query);
        if (!props.multiple && !props.remote) {
          states.selectEmitter.emit("elOptionQueryChange", "");
          states.selectEmitter.emit("elOptionGroupQueryChange");
        }
      }
    }
    ctx.emit("visible-change", val);
  });
  vue.watch(() => states.options.entries(), () => {
    var _a, _b, _c;
    if (isServer__default['default'])
      return;
    (_b = (_a = popper.value) == null ? void 0 : _a.update) == null ? void 0 : _b.call(_a);
    if (props.multiple) {
      resetInputHeight();
    }
    const inputs = ((_c = selectWrapper.value) == null ? void 0 : _c.querySelectorAll("input")) || [];
    if ([].indexOf.call(inputs, document.activeElement) === -1) {
      setSelected();
    }
    if (props.defaultFirstOption && (props.filterable || props.remote) && states.filteredOptionsCount) {
      checkDefaultFirstOption();
    }
  }, {
    flush: "post"
  });
  vue.watch(() => states.hoverIndex, (val) => {
    if (typeof val === "number" && val > -1) {
      hoverOption.value = optionsArray.value[val] || {};
    }
    optionsArray.value.forEach((option) => {
      option.hover = hoverOption.value === option;
    });
  });
  const resetInputHeight = () => {
    if (props.collapseTags && !props.filterable)
      return;
    vue.nextTick(() => {
      var _a, _b;
      if (!reference.value)
        return;
      const inputChildNodes = reference.value.$el.childNodes;
      const input2 = [].filter.call(inputChildNodes, (item) => item.tagName === "INPUT")[0];
      const _tags = tags.value;
      const sizeInMap = states.initialInputHeight || 40;
      input2.style.height = states.selected.length === 0 ? sizeInMap + "px" : Math.max(_tags ? _tags.clientHeight + (_tags.clientHeight > sizeInMap ? 6 : 0) : 0, sizeInMap) + "px";
      states.tagInMultiLine = parseFloat(input2.style.height) > sizeInMap;
      if (states.visible && emptyText.value !== false) {
        (_b = (_a = popper.value) == null ? void 0 : _a.update) == null ? void 0 : _b.call(_a);
      }
    });
  };
  const handleQueryChange = (val) => {
    if (states.previousQuery === val || states.isOnComposition)
      return;
    if (states.previousQuery === null && (typeof props.filterMethod === "function" || typeof props.remoteMethod === "function")) {
      states.previousQuery = val;
      return;
    }
    states.previousQuery = val;
    vue.nextTick(() => {
      var _a, _b;
      if (states.visible)
        (_b = (_a = popper.value) == null ? void 0 : _a.update) == null ? void 0 : _b.call(_a);
    });
    states.hoverIndex = -1;
    if (props.multiple && props.filterable) {
      vue.nextTick(() => {
        const length = input.value.length * 15 + 20;
        states.inputLength = props.collapseTags ? Math.min(50, length) : length;
        managePlaceholder();
        resetInputHeight();
      });
    }
    if (props.remote && typeof props.remoteMethod === "function") {
      states.hoverIndex = -1;
      props.remoteMethod(val);
    } else if (typeof props.filterMethod === "function") {
      props.filterMethod(val);
      states.selectEmitter.emit("elOptionGroupQueryChange");
    } else {
      states.filteredOptionsCount = states.optionsCount;
      states.selectEmitter.emit("elOptionQueryChange", val);
      states.selectEmitter.emit("elOptionGroupQueryChange");
    }
    if (props.defaultFirstOption && (props.filterable || props.remote) && states.filteredOptionsCount) {
      checkDefaultFirstOption();
    }
  };
  const managePlaceholder = () => {
    if (states.currentPlaceholder !== "") {
      states.currentPlaceholder = input.value.value ? "" : states.cachedPlaceHolder;
    }
  };
  const checkDefaultFirstOption = () => {
    states.hoverIndex = -1;
    let hasCreated = false;
    for (let i = states.options.size - 1; i >= 0; i--) {
      if (optionsArray.value[i].created) {
        hasCreated = true;
        states.hoverIndex = i;
        break;
      }
    }
    if (hasCreated)
      return;
    for (let i = 0; i !== states.options.size; ++i) {
      const option = optionsArray.value[i];
      if (states.query) {
        if (!option.disabled && !option.groupDisabled && option.visible) {
          states.hoverIndex = i;
          break;
        }
      } else {
        if (option.itemSelected) {
          states.hoverIndex = i;
          break;
        }
      }
    }
  };
  const setSelected = () => {
    var _a;
    if (!props.multiple) {
      const option = getOption(props.modelValue);
      if ((_a = option.props) == null ? void 0 : _a.created) {
        states.createdLabel = option.props.value;
        states.createdSelected = true;
      } else {
        states.createdSelected = false;
      }
      states.selectedLabel = option.currentLabel;
      states.selected = option;
      if (props.filterable)
        states.query = states.selectedLabel;
      return;
    }
    const result = [];
    if (Array.isArray(props.modelValue)) {
      props.modelValue.forEach((value) => {
        result.push(getOption(value));
      });
    }
    states.selected = result;
    vue.nextTick(() => {
      resetInputHeight();
    });
  };
  const getOption = (value) => {
    let option;
    const isObjectValue = toRawType(value).toLowerCase() === "object";
    const isNull = toRawType(value).toLowerCase() === "null";
    const isUndefined = toRawType(value).toLowerCase() === "undefined";
    for (let i = states.cachedOptions.size - 1; i >= 0; i--) {
      const cachedOption = cachedOptionsArray.value[i];
      const isEqualValue = isObjectValue ? util.getValueByPath(cachedOption.value, props.valueKey) === util.getValueByPath(value, props.valueKey) : cachedOption.value === value;
      if (isEqualValue) {
        option = {
          value,
          currentLabel: cachedOption.currentLabel,
          isDisabled: cachedOption.isDisabled
        };
        break;
      }
    }
    if (option)
      return option;
    const label = !isObjectValue && !isNull && !isUndefined ? value : "";
    const newOption = {
      value,
      currentLabel: label
    };
    if (props.multiple) {
      newOption.hitState = false;
    }
    return newOption;
  };
  const resetHoverIndex = () => {
    setTimeout(() => {
      if (!props.multiple) {
        states.hoverIndex = optionsArray.value.indexOf(states.selected);
      } else {
        if (states.selected.length > 0) {
          states.hoverIndex = Math.min.apply(null, states.selected.map((item) => optionsArray.value.indexOf(item)));
        } else {
          states.hoverIndex = -1;
        }
      }
    }, 300);
  };
  const handleResize = () => {
    var _a, _b;
    resetInputWidth();
    (_b = (_a = popper.value) == null ? void 0 : _a.update) == null ? void 0 : _b.call(_a);
    if (props.multiple)
      resetInputHeight();
  };
  const resetInputWidth = () => {
    var _a;
    states.inputWidth = (_a = reference.value) == null ? void 0 : _a.$el.getBoundingClientRect().width;
  };
  const onInputChange = () => {
    if (props.filterable && states.query !== states.selectedLabel) {
      states.query = states.selectedLabel;
      handleQueryChange(states.query);
    }
  };
  const debouncedOnInputChange = lodashDebounce__default['default'](() => {
    onInputChange();
  }, debounce.value);
  const debouncedQueryChange = lodashDebounce__default['default']((e) => {
    handleQueryChange(e.target.value);
  }, debounce.value);
  const emitChange = (val) => {
    if (!isEqual__default['default'](props.modelValue, val)) {
      ctx.emit(constants.CHANGE_EVENT, val);
    }
  };
  const deletePrevTag = (e) => {
    if (e.target.value.length <= 0 && !toggleLastOptionHitState()) {
      const value = props.modelValue.slice();
      value.pop();
      ctx.emit(constants.UPDATE_MODEL_EVENT, value);
      emitChange(value);
    }
    if (e.target.value.length === 1 && props.modelValue.length === 0) {
      states.currentPlaceholder = states.cachedPlaceHolder;
    }
  };
  const deleteTag = (event, tag) => {
    const index = states.selected.indexOf(tag);
    if (index > -1 && !selectDisabled.value) {
      const value = props.modelValue.slice();
      value.splice(index, 1);
      ctx.emit(constants.UPDATE_MODEL_EVENT, value);
      emitChange(value);
      ctx.emit("remove-tag", tag.value);
    }
    event.stopPropagation();
  };
  const deleteSelected = (event) => {
    event.stopPropagation();
    const value = props.multiple ? [] : "";
    if (typeof value !== "string") {
      for (const item of states.selected) {
        if (item.isDisabled)
          value.push(item.value);
      }
    }
    ctx.emit(constants.UPDATE_MODEL_EVENT, value);
    emitChange(value);
    states.visible = false;
    ctx.emit("clear");
  };
  const handleOptionSelect = (option, byClick) => {
    if (props.multiple) {
      const value = (props.modelValue || []).slice();
      const optionIndex = getValueIndex(value, option.value);
      if (optionIndex > -1) {
        value.splice(optionIndex, 1);
      } else if (props.multipleLimit <= 0 || value.length < props.multipleLimit) {
        value.push(option.value);
      }
      ctx.emit(constants.UPDATE_MODEL_EVENT, value);
      emitChange(value);
      if (option.created) {
        states.query = "";
        handleQueryChange("");
        states.inputLength = 20;
      }
      if (props.filterable)
        input.value.focus();
    } else {
      ctx.emit(constants.UPDATE_MODEL_EVENT, option.value);
      emitChange(option.value);
      states.visible = false;
    }
    states.isSilentBlur = byClick;
    setSoftFocus();
    if (states.visible)
      return;
    vue.nextTick(() => {
      scrollToOption(option);
    });
  };
  const getValueIndex = (arr = [], value) => {
    if (!isObject(value))
      return arr.indexOf(value);
    const valueKey = props.valueKey;
    let index = -1;
    arr.some((item, i) => {
      if (util.getValueByPath(item, valueKey) === util.getValueByPath(value, valueKey)) {
        index = i;
        return true;
      }
      return false;
    });
    return index;
  };
  const setSoftFocus = () => {
    states.softFocus = true;
    const _input = input.value || reference.value;
    if (_input) {
      _input.focus();
    }
  };
  const scrollToOption = (option) => {
    var _a, _b, _c, _d;
    const targetOption = Array.isArray(option) ? option[0] : option;
    let target = null;
    if (targetOption == null ? void 0 : targetOption.value) {
      const options = optionsArray.value.filter((item) => item.value === targetOption.value);
      if (options.length > 0) {
        target = options[0].$el;
      }
    }
    if (popper.value && target) {
      const menu = (_c = (_b = (_a = popper.value) == null ? void 0 : _a.popperRef) == null ? void 0 : _b.querySelector) == null ? void 0 : _c.call(_b, ".el-select-dropdown__wrap");
      if (menu) {
        scrollIntoView__default['default'](menu, target);
      }
    }
    (_d = scrollbar.value) == null ? void 0 : _d.handleScroll();
  };
  const onOptionCreate = (vm) => {
    states.optionsCount++;
    states.filteredOptionsCount++;
    states.options.set(vm.value, vm);
    states.cachedOptions.set(vm.value, vm);
  };
  const onOptionDestroy = (key) => {
    states.optionsCount--;
    states.filteredOptionsCount--;
    states.options.delete(key);
  };
  const resetInputState = (e) => {
    if (e.code !== aria.EVENT_CODE.backspace)
      toggleLastOptionHitState(false);
    states.inputLength = input.value.length * 15 + 20;
    resetInputHeight();
  };
  const toggleLastOptionHitState = (hit) => {
    if (!Array.isArray(states.selected))
      return;
    const option = states.selected[states.selected.length - 1];
    if (!option)
      return;
    if (hit === true || hit === false) {
      option.hitState = hit;
      return hit;
    }
    option.hitState = !option.hitState;
    return option.hitState;
  };
  const handleComposition = (event) => {
    const text = event.target.value;
    if (event.type === "compositionend") {
      states.isOnComposition = false;
      vue.nextTick(() => handleQueryChange(text));
    } else {
      const lastCharacter = text[text.length - 1] || "";
      states.isOnComposition = !isDef.isKorean(lastCharacter);
    }
  };
  const handleMenuEnter = () => {
    vue.nextTick(() => scrollToOption(states.selected));
  };
  const handleFocus = (event) => {
    if (!states.softFocus) {
      if (props.automaticDropdown || props.filterable) {
        states.visible = true;
        if (props.filterable) {
          states.menuVisibleOnFocus = true;
        }
      }
      ctx.emit("focus", event);
    } else {
      states.softFocus = false;
    }
  };
  const blur = () => {
    states.visible = false;
    reference.value.blur();
  };
  const handleBlur = (event) => {
    vue.nextTick(() => {
      if (states.isSilentBlur) {
        states.isSilentBlur = false;
      } else {
        ctx.emit("blur", event);
      }
    });
    states.softFocus = false;
  };
  const handleClearClick = (event) => {
    deleteSelected(event);
  };
  const handleClose = () => {
    states.visible = false;
  };
  const toggleMenu = () => {
    if (props.automaticDropdown)
      return;
    if (!selectDisabled.value) {
      if (states.menuVisibleOnFocus) {
        states.menuVisibleOnFocus = false;
      } else {
        states.visible = !states.visible;
      }
      if (states.visible) {
        (input.value || reference.value).focus();
      }
    }
  };
  const selectOption = () => {
    if (!states.visible) {
      toggleMenu();
    } else {
      if (optionsArray.value[states.hoverIndex]) {
        handleOptionSelect(optionsArray.value[states.hoverIndex], void 0);
      }
    }
  };
  const getValueKey = (item) => {
    return isObject(item.value) ? util.getValueByPath(item.value, props.valueKey) : item.value;
  };
  const optionsAllDisabled = vue.computed(() => optionsArray.value.filter((option) => option.visible).every((option) => option.disabled));
  const navigateOptions = (direction) => {
    if (!states.visible) {
      states.visible = true;
      return;
    }
    if (states.options.size === 0 || states.filteredOptionsCount === 0)
      return;
    if (!optionsAllDisabled.value) {
      if (direction === "next") {
        states.hoverIndex++;
        if (states.hoverIndex === states.options.size) {
          states.hoverIndex = 0;
        }
      } else if (direction === "prev") {
        states.hoverIndex--;
        if (states.hoverIndex < 0) {
          states.hoverIndex = states.options.size - 1;
        }
      }
      const option = optionsArray.value[states.hoverIndex];
      if (option.disabled === true || option.groupDisabled === true || !option.visible) {
        navigateOptions(direction);
      }
      vue.nextTick(() => scrollToOption(hoverOption.value));
    }
  };
  return {
    optionsArray,
    selectSize,
    handleResize,
    debouncedOnInputChange,
    debouncedQueryChange,
    deletePrevTag,
    deleteTag,
    deleteSelected,
    handleOptionSelect,
    scrollToOption,
    readonly,
    resetInputHeight,
    showClose,
    iconClass,
    showNewOption,
    collapseTagSize,
    setSelected,
    managePlaceholder,
    selectDisabled,
    emptyText,
    toggleLastOptionHitState,
    resetInputState,
    handleComposition,
    onOptionCreate,
    onOptionDestroy,
    handleMenuEnter,
    handleFocus,
    blur,
    handleBlur,
    handleClearClick,
    handleClose,
    toggleMenu,
    selectOption,
    getValueKey,
    navigateOptions,
    dropMenuVisible,
    reference,
    input,
    popper,
    tags,
    selectWrapper,
    scrollbar
  };
};

var script$2 = vue.defineComponent({
  name: "ElSelect",
  componentName: "ElSelect",
  components: {
    ElInput: ElInput__default['default'],
    ElSelectMenu: script$1,
    ElOption: script,
    ElTag: ElTag__default['default'],
    ElScrollbar: ElScrollbar__default['default'],
    ElPopper: ElPopper__default['default']
  },
  directives: { ClickOutside: directives.ClickOutside },
  props: {
    name: String,
    id: String,
    modelValue: [Array, String, Number, Boolean, Object],
    autocomplete: {
      type: String,
      default: "off"
    },
    automaticDropdown: Boolean,
    size: {
      type: String,
      validator: validators.isValidComponentSize
    },
    disabled: Boolean,
    clearable: Boolean,
    filterable: Boolean,
    allowCreate: Boolean,
    loading: Boolean,
    popperClass: {
      type: String,
      default: ""
    },
    remote: Boolean,
    loadingText: String,
    noMatchText: String,
    noDataText: String,
    remoteMethod: Function,
    filterMethod: Function,
    multiple: Boolean,
    multipleLimit: {
      type: Number,
      default: 0
    },
    placeholder: {
      type: String
    },
    defaultFirstOption: Boolean,
    reserveKeyword: Boolean,
    valueKey: {
      type: String,
      default: "value"
    },
    collapseTags: Boolean,
    popperAppendToBody: {
      type: Boolean,
      default: true
    },
    clearIcon: {
      type: String,
      default: "el-icon-circle-close"
    }
  },
  emits: [constants.UPDATE_MODEL_EVENT, constants.CHANGE_EVENT, "remove-tag", "clear", "visible-change", "focus", "blur"],
  setup(props, ctx) {
    const states = useSelectStates(props);
    const {
      optionsArray,
      selectSize,
      readonly,
      handleResize,
      collapseTagSize,
      debouncedOnInputChange,
      debouncedQueryChange,
      deletePrevTag,
      deleteTag,
      deleteSelected,
      handleOptionSelect,
      scrollToOption,
      setSelected,
      resetInputHeight,
      managePlaceholder,
      showClose,
      selectDisabled,
      iconClass,
      showNewOption,
      emptyText,
      toggleLastOptionHitState,
      resetInputState,
      handleComposition,
      onOptionCreate,
      onOptionDestroy,
      handleMenuEnter,
      handleFocus,
      blur,
      handleBlur,
      handleClearClick,
      handleClose,
      toggleMenu,
      selectOption,
      getValueKey,
      navigateOptions,
      dropMenuVisible,
      reference,
      input,
      popper,
      tags,
      selectWrapper,
      scrollbar
    } = useSelect(props, states, ctx);
    const { focus } = hooks.useFocus(reference);
    const {
      inputWidth,
      selected,
      inputLength,
      filteredOptionsCount,
      visible,
      softFocus,
      selectedLabel,
      hoverIndex,
      query,
      inputHovering,
      currentPlaceholder,
      menuVisibleOnFocus,
      isOnComposition,
      isSilentBlur,
      options,
      cachedOptions,
      optionsCount,
      prefixWidth,
      tagInMultiLine
    } = vue.toRefs(states);
    vue.provide(selectKey, vue.reactive({
      props,
      options,
      optionsArray,
      cachedOptions,
      optionsCount,
      filteredOptionsCount,
      hoverIndex,
      handleOptionSelect,
      selectEmitter: states.selectEmitter,
      onOptionCreate,
      onOptionDestroy,
      selectWrapper,
      selected,
      setSelected
    }));
    vue.onMounted(() => {
      states.cachedPlaceHolder = currentPlaceholder.value = props.placeholder || locale.t("el.select.placeholder");
      if (props.multiple && Array.isArray(props.modelValue) && props.modelValue.length > 0) {
        currentPlaceholder.value = "";
      }
      resizeEvent.addResizeListener(selectWrapper.value, handleResize);
      if (reference.value && reference.value.$el) {
        const sizeMap = {
          medium: 36,
          small: 32,
          mini: 28
        };
        const input2 = reference.value.input;
        states.initialInputHeight = input2.getBoundingClientRect().height || sizeMap[selectSize.value];
      }
      if (props.remote && props.multiple) {
        resetInputHeight();
      }
      vue.nextTick(() => {
        if (reference.value.$el) {
          inputWidth.value = reference.value.$el.getBoundingClientRect().width;
        }
        if (ctx.slots.prefix) {
          const inputChildNodes = reference.value.$el.childNodes;
          const input2 = [].filter.call(inputChildNodes, (item) => item.tagName === "INPUT")[0];
          const prefix = reference.value.$el.querySelector(".el-input__prefix");
          prefixWidth.value = Math.max(prefix.getBoundingClientRect().width + 5, 30);
          if (states.prefixWidth) {
            input2.style.paddingLeft = `${Math.max(states.prefixWidth, 30)}px`;
          }
        }
      });
      setSelected();
    });
    vue.onBeforeUnmount(() => {
      resizeEvent.removeResizeListener(selectWrapper.value, handleResize);
    });
    if (props.multiple && !Array.isArray(props.modelValue)) {
      ctx.emit(constants.UPDATE_MODEL_EVENT, []);
    }
    if (!props.multiple && Array.isArray(props.modelValue)) {
      ctx.emit(constants.UPDATE_MODEL_EVENT, "");
    }
    const popperPaneRef = vue.computed(() => {
      var _a;
      return (_a = popper.value) == null ? void 0 : _a.popperRef;
    });
    return {
      tagInMultiLine,
      prefixWidth,
      selectSize,
      readonly,
      handleResize,
      collapseTagSize,
      debouncedOnInputChange,
      debouncedQueryChange,
      deletePrevTag,
      deleteTag,
      deleteSelected,
      handleOptionSelect,
      scrollToOption,
      inputWidth,
      selected,
      inputLength,
      filteredOptionsCount,
      visible,
      softFocus,
      selectedLabel,
      hoverIndex,
      query,
      inputHovering,
      currentPlaceholder,
      menuVisibleOnFocus,
      isOnComposition,
      isSilentBlur,
      options,
      resetInputHeight,
      managePlaceholder,
      showClose,
      selectDisabled,
      iconClass,
      showNewOption,
      emptyText,
      toggleLastOptionHitState,
      resetInputState,
      handleComposition,
      handleMenuEnter,
      handleFocus,
      blur,
      handleBlur,
      handleClearClick,
      handleClose,
      toggleMenu,
      selectOption,
      getValueKey,
      navigateOptions,
      dropMenuVisible,
      focus,
      reference,
      input,
      popper,
      popperPaneRef,
      tags,
      selectWrapper,
      scrollbar
    };
  }
});

const _hoisted_1 = { class: "select-trigger" };
const _hoisted_2 = { key: 0 };
const _hoisted_3 = { class: "el-select__tags-text" };
const _hoisted_4 = { style: { "height": "100%", "display": "flex", "justify-content": "center", "align-items": "center" } };
const _hoisted_5 = {
  key: 1,
  class: "el-select-dropdown__empty"
};
function render$2(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_el_tag = vue.resolveComponent("el-tag");
  const _component_el_input = vue.resolveComponent("el-input");
  const _component_el_option = vue.resolveComponent("el-option");
  const _component_el_scrollbar = vue.resolveComponent("el-scrollbar");
  const _component_el_select_menu = vue.resolveComponent("el-select-menu");
  const _component_el_popper = vue.resolveComponent("el-popper");
  const _directive_click_outside = vue.resolveDirective("click-outside");
  return vue.withDirectives((vue.openBlock(), vue.createBlock("div", {
    ref: "selectWrapper",
    class: ["el-select", [_ctx.selectSize ? "el-select--" + _ctx.selectSize : ""]],
    onClick: _cache[26] || (_cache[26] = vue.withModifiers((...args) => _ctx.toggleMenu && _ctx.toggleMenu(...args), ["stop"]))
  }, [
    vue.createVNode(_component_el_popper, {
      ref: "popper",
      visible: _ctx.dropMenuVisible,
      "onUpdate:visible": _cache[25] || (_cache[25] = ($event) => _ctx.dropMenuVisible = $event),
      placement: "bottom-start",
      "append-to-body": _ctx.popperAppendToBody,
      "popper-class": `el-select__popper ${_ctx.popperClass}`,
      "fallback-placements": ["auto"],
      "manual-mode": "",
      effect: "light",
      pure: "",
      trigger: "click",
      transition: "el-zoom-in-top",
      "stop-popper-mouse-event": false,
      "gpu-acceleration": false,
      onBeforeEnter: _ctx.handleMenuEnter
    }, {
      trigger: vue.withCtx(() => [
        vue.createVNode("div", _hoisted_1, [
          _ctx.multiple ? (vue.openBlock(), vue.createBlock("div", {
            key: 0,
            ref: "tags",
            class: "el-select__tags",
            style: { "max-width": _ctx.inputWidth - 32 + "px", width: "100%" }
          }, [
            _ctx.collapseTags && _ctx.selected.length ? (vue.openBlock(), vue.createBlock("span", _hoisted_2, [
              vue.createVNode(_component_el_tag, {
                closable: !_ctx.selectDisabled && !_ctx.selected[0].isDisabled,
                size: _ctx.collapseTagSize,
                hit: _ctx.selected[0].hitState,
                type: "info",
                "disable-transitions": "",
                onClose: _cache[1] || (_cache[1] = ($event) => _ctx.deleteTag($event, _ctx.selected[0]))
              }, {
                default: vue.withCtx(() => [
                  vue.createVNode("span", {
                    class: "el-select__tags-text",
                    style: { "max-width": _ctx.inputWidth - 123 + "px" }
                  }, vue.toDisplayString(_ctx.selected[0].currentLabel), 5)
                ]),
                _: 1
              }, 8, ["closable", "size", "hit"]),
              _ctx.selected.length > 1 ? (vue.openBlock(), vue.createBlock(_component_el_tag, {
                key: 0,
                closable: false,
                size: _ctx.collapseTagSize,
                type: "info",
                "disable-transitions": ""
              }, {
                default: vue.withCtx(() => [
                  vue.createVNode("span", _hoisted_3, "+ " + vue.toDisplayString(_ctx.selected.length - 1), 1)
                ]),
                _: 1
              }, 8, ["size"])) : vue.createCommentVNode("v-if", true)
            ])) : vue.createCommentVNode("v-if", true),
            vue.createCommentVNode(" <div> "),
            !_ctx.collapseTags ? (vue.openBlock(), vue.createBlock(vue.Transition, {
              key: 1,
              onAfterLeave: _ctx.resetInputHeight
            }, {
              default: vue.withCtx(() => [
                vue.createVNode("span", {
                  style: { marginLeft: _ctx.prefixWidth && _ctx.selected.length ? `${_ctx.prefixWidth}px` : null }
                }, [
                  (vue.openBlock(true), vue.createBlock(vue.Fragment, null, vue.renderList(_ctx.selected, (item) => {
                    return vue.openBlock(), vue.createBlock(_component_el_tag, {
                      key: _ctx.getValueKey(item),
                      closable: !_ctx.selectDisabled && !item.isDisabled,
                      size: _ctx.collapseTagSize,
                      hit: item.hitState,
                      type: "info",
                      "disable-transitions": "",
                      onClose: ($event) => _ctx.deleteTag($event, item)
                    }, {
                      default: vue.withCtx(() => [
                        vue.createVNode("span", {
                          class: "el-select__tags-text",
                          style: { "max-width": _ctx.inputWidth - 75 + "px" }
                        }, vue.toDisplayString(item.currentLabel), 5)
                      ]),
                      _: 2
                    }, 1032, ["closable", "size", "hit", "onClose"]);
                  }), 128))
                ], 4)
              ]),
              _: 1
            }, 8, ["onAfterLeave"])) : vue.createCommentVNode("v-if", true),
            vue.createCommentVNode(" </div> "),
            _ctx.filterable ? vue.withDirectives((vue.openBlock(), vue.createBlock("input", {
              key: 2,
              ref: "input",
              "onUpdate:modelValue": _cache[2] || (_cache[2] = ($event) => _ctx.query = $event),
              type: "text",
              class: ["el-select__input", [_ctx.selectSize ? `is-${_ctx.selectSize}` : ""]],
              disabled: _ctx.selectDisabled,
              autocomplete: _ctx.autocomplete,
              style: { marginLeft: _ctx.prefixWidth && !_ctx.selected.length || _ctx.tagInMultiLine ? `${_ctx.prefixWidth}px` : null, flexGrow: "1", width: `${_ctx.inputLength / (_ctx.inputWidth - 32)}%`, maxWidth: `${_ctx.inputWidth - 42}px` },
              onFocus: _cache[3] || (_cache[3] = (...args) => _ctx.handleFocus && _ctx.handleFocus(...args)),
              onBlur: _cache[4] || (_cache[4] = (...args) => _ctx.handleBlur && _ctx.handleBlur(...args)),
              onKeyup: _cache[5] || (_cache[5] = (...args) => _ctx.managePlaceholder && _ctx.managePlaceholder(...args)),
              onKeydown: [
                _cache[6] || (_cache[6] = (...args) => _ctx.resetInputState && _ctx.resetInputState(...args)),
                _cache[7] || (_cache[7] = vue.withKeys(vue.withModifiers(($event) => _ctx.navigateOptions("next"), ["prevent"]), ["down"])),
                _cache[8] || (_cache[8] = vue.withKeys(vue.withModifiers(($event) => _ctx.navigateOptions("prev"), ["prevent"]), ["up"])),
                _cache[9] || (_cache[9] = vue.withKeys(vue.withModifiers(($event) => _ctx.visible = false, ["stop", "prevent"]), ["esc"])),
                _cache[10] || (_cache[10] = vue.withKeys(vue.withModifiers((...args) => _ctx.selectOption && _ctx.selectOption(...args), ["stop", "prevent"]), ["enter"])),
                _cache[11] || (_cache[11] = vue.withKeys((...args) => _ctx.deletePrevTag && _ctx.deletePrevTag(...args), ["delete"])),
                _cache[12] || (_cache[12] = vue.withKeys(($event) => _ctx.visible = false, ["tab"]))
              ],
              onCompositionstart: _cache[13] || (_cache[13] = (...args) => _ctx.handleComposition && _ctx.handleComposition(...args)),
              onCompositionupdate: _cache[14] || (_cache[14] = (...args) => _ctx.handleComposition && _ctx.handleComposition(...args)),
              onCompositionend: _cache[15] || (_cache[15] = (...args) => _ctx.handleComposition && _ctx.handleComposition(...args)),
              onInput: _cache[16] || (_cache[16] = (...args) => _ctx.debouncedQueryChange && _ctx.debouncedQueryChange(...args))
            }, null, 46, ["disabled", "autocomplete"])), [
              [vue.vModelText, _ctx.query]
            ]) : vue.createCommentVNode("v-if", true)
          ], 4)) : vue.createCommentVNode("v-if", true),
          vue.createVNode(_component_el_input, {
            id: _ctx.id,
            ref: "reference",
            modelValue: _ctx.selectedLabel,
            "onUpdate:modelValue": _cache[18] || (_cache[18] = ($event) => _ctx.selectedLabel = $event),
            type: "text",
            placeholder: _ctx.currentPlaceholder,
            name: _ctx.name,
            autocomplete: _ctx.autocomplete,
            size: _ctx.selectSize,
            disabled: _ctx.selectDisabled,
            readonly: _ctx.readonly,
            "validate-event": false,
            class: { "is-focus": _ctx.visible },
            tabindex: _ctx.multiple && _ctx.filterable ? "-1" : null,
            onFocus: _ctx.handleFocus,
            onBlur: _ctx.handleBlur,
            onInput: _ctx.debouncedOnInputChange,
            onPaste: _ctx.debouncedOnInputChange,
            onKeydown: [
              _cache[19] || (_cache[19] = vue.withKeys(vue.withModifiers(($event) => _ctx.navigateOptions("next"), ["stop", "prevent"]), ["down"])),
              _cache[20] || (_cache[20] = vue.withKeys(vue.withModifiers(($event) => _ctx.navigateOptions("prev"), ["stop", "prevent"]), ["up"])),
              vue.withKeys(vue.withModifiers(_ctx.selectOption, ["stop", "prevent"]), ["enter"]),
              _cache[21] || (_cache[21] = vue.withKeys(vue.withModifiers(($event) => _ctx.visible = false, ["stop", "prevent"]), ["esc"])),
              _cache[22] || (_cache[22] = vue.withKeys(($event) => _ctx.visible = false, ["tab"]))
            ],
            onMouseenter: _cache[23] || (_cache[23] = ($event) => _ctx.inputHovering = true),
            onMouseleave: _cache[24] || (_cache[24] = ($event) => _ctx.inputHovering = false)
          }, vue.createSlots({
            suffix: vue.withCtx(() => [
              vue.withDirectives(vue.createVNode("i", {
                class: ["el-select__caret", "el-input__icon", "el-icon-" + _ctx.iconClass]
              }, null, 2), [
                [vue.vShow, !_ctx.showClose]
              ]),
              _ctx.showClose ? (vue.openBlock(), vue.createBlock("i", {
                key: 0,
                class: `el-select__caret el-input__icon ${_ctx.clearIcon}`,
                onClick: _cache[17] || (_cache[17] = (...args) => _ctx.handleClearClick && _ctx.handleClearClick(...args))
              }, null, 2)) : vue.createCommentVNode("v-if", true)
            ]),
            _: 2
          }, [
            _ctx.$slots.prefix ? {
              name: "prefix",
              fn: vue.withCtx(() => [
                vue.createVNode("div", _hoisted_4, [
                  vue.renderSlot(_ctx.$slots, "prefix")
                ])
              ])
            } : void 0
          ]), 1032, ["id", "modelValue", "placeholder", "name", "autocomplete", "size", "disabled", "readonly", "class", "tabindex", "onFocus", "onBlur", "onInput", "onPaste", "onKeydown"])
        ])
      ]),
      default: vue.withCtx(() => [
        vue.createVNode(_component_el_select_menu, null, {
          default: vue.withCtx(() => [
            vue.withDirectives(vue.createVNode(_component_el_scrollbar, {
              ref: "scrollbar",
              tag: "ul",
              "wrap-class": "el-select-dropdown__wrap",
              "view-class": "el-select-dropdown__list",
              class: { "is-empty": !_ctx.allowCreate && _ctx.query && _ctx.filteredOptionsCount === 0 }
            }, {
              default: vue.withCtx(() => [
                _ctx.showNewOption ? (vue.openBlock(), vue.createBlock(_component_el_option, {
                  key: 0,
                  value: _ctx.query,
                  created: true
                }, null, 8, ["value"])) : vue.createCommentVNode("v-if", true),
                vue.renderSlot(_ctx.$slots, "default")
              ]),
              _: 3
            }, 8, ["class"]), [
              [vue.vShow, _ctx.options.size > 0 && !_ctx.loading]
            ]),
            _ctx.emptyText && (!_ctx.allowCreate || _ctx.loading || _ctx.allowCreate && _ctx.options.size === 0) ? (vue.openBlock(), vue.createBlock(vue.Fragment, { key: 0 }, [
              _ctx.$slots.empty ? vue.renderSlot(_ctx.$slots, "empty", { key: 0 }) : (vue.openBlock(), vue.createBlock("p", _hoisted_5, vue.toDisplayString(_ctx.emptyText), 1))
            ], 2112)) : vue.createCommentVNode("v-if", true)
          ]),
          _: 3
        })
      ]),
      _: 1
    }, 8, ["visible", "append-to-body", "popper-class", "onBeforeEnter"])
  ], 2)), [
    [_directive_click_outside, _ctx.handleClose, _ctx.popperPaneRef]
  ]);
}

script$2.render = render$2;
script$2.__file = "packages/select/src/select.vue";

script$2.install = (app) => {
  app.component(script$2.name, script$2);
};
const _Select = script$2;

exports.Option = script;
exports.default = _Select;
