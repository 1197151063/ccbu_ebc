'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
var ElTag = require('../el-tag');
var ElPopper = require('../el-popper');
var util = require('../utils/util');
var virtualList = require('../el-virtual-list');
var directives = require('../directives');
var constants = require('../utils/constants');
var isEqual = require('lodash/isEqual');
var lodashDebounce = require('lodash/debounce');
var resizeEvent = require('../utils/resize-event');
var locale = require('../locale');
var form = require('../el-form');
var validators = require('../utils/validators');

function _interopDefaultLegacy (e) { return e && typeof e === 'object' && 'default' in e ? e : { 'default': e }; }

var ElTag__default = /*#__PURE__*/_interopDefaultLegacy(ElTag);
var ElPopper__default = /*#__PURE__*/_interopDefaultLegacy(ElPopper);
var isEqual__default = /*#__PURE__*/_interopDefaultLegacy(isEqual);
var lodashDebounce__default = /*#__PURE__*/_interopDefaultLegacy(lodashDebounce);

var script = vue.defineComponent({
  props: {
    item: {
      type: Object,
      required: true
    },
    style: Object,
    height: Number
  }
});

function render(_ctx, _cache, $props, $setup, $data, $options) {
  return _ctx.item.isTitle ? (vue.openBlock(), vue.createBlock("div", {
    key: 0,
    class: "el-select-group__title",
    style: [_ctx.style, { lineHeight: `${_ctx.height}px` }]
  }, vue.toDisplayString(_ctx.item.label), 5)) : (vue.openBlock(), vue.createBlock("div", {
    key: 1,
    class: "el-select-group__split",
    style: _ctx.style
  }, [
    vue.createVNode("span", {
      class: "el-select-group__split-dash",
      style: { top: `${_ctx.height / 2}px` }
    }, null, 4)
  ], 4));
}

script.render = render;
script.__file = "packages/select-v2/src/group-item.vue";

var script$1 = vue.defineComponent({
  props: {
    data: Array,
    disabled: Boolean,
    hovering: Boolean,
    item: Object,
    index: Number,
    style: Object,
    selected: Boolean
  },
  emits: ["select", "hover"],
  setup(props, { emit }) {
    return {
      hoverItem: () => {
        emit("hover", props.index);
      },
      selectOptionClick: () => {
        if (!props.disabled) {
          emit("select", props.item, props.index);
        }
      }
    };
  }
});

function render$1(_ctx, _cache, $props, $setup, $data, $options) {
  return vue.openBlock(), vue.createBlock("li", {
    "aria-selected": _ctx.selected,
    style: _ctx.style,
    class: {
      "el-select-dropdown__option-item": true,
      "is-selected": _ctx.selected,
      "is-disabled": _ctx.disabled,
      "hover": _ctx.hovering
    },
    onMouseenter: _cache[1] || (_cache[1] = (...args) => _ctx.hoverItem && _ctx.hoverItem(...args)),
    onClick: _cache[2] || (_cache[2] = vue.withModifiers((...args) => _ctx.selectOptionClick && _ctx.selectOptionClick(...args), ["stop"]))
  }, [
    vue.renderSlot(_ctx.$slots, "default", {
      item: _ctx.item,
      index: _ctx.index,
      disabled: _ctx.disabled
    }, () => [
      vue.createVNode("span", null, vue.toDisplayString(_ctx.item.label), 1)
    ])
  ], 46, ["aria-selected"]);
}

script$1.render = render$1;
script$1.__file = "packages/select-v2/src/option-item.vue";

const selectKey = "ElSelect";

var __defProp = Object.defineProperty;
var __defProps = Object.defineProperties;
var __getOwnPropDescs = Object.getOwnPropertyDescriptors;
var __getOwnPropSymbols = Object.getOwnPropertySymbols;
var __hasOwnProp = Object.prototype.hasOwnProperty;
var __propIsEnum = Object.prototype.propertyIsEnumerable;
var __defNormalProp = (obj, key, value) => key in obj ? __defProp(obj, key, { enumerable: true, configurable: true, writable: true, value }) : obj[key] = value;
var __spreadValues = (a, b) => {
  for (var prop in b || (b = {}))
    if (__hasOwnProp.call(b, prop))
      __defNormalProp(a, prop, b[prop]);
  if (__getOwnPropSymbols)
    for (var prop of __getOwnPropSymbols(b)) {
      if (__propIsEnum.call(b, prop))
        __defNormalProp(a, prop, b[prop]);
    }
  return a;
};
var __spreadProps = (a, b) => __defProps(a, __getOwnPropDescs(b));
var script$2 = vue.defineComponent({
  name: "ElSelectDropdown",
  props: {
    data: Array,
    hoveringIndex: Number,
    width: Number
  },
  setup(props) {
    const select = vue.inject(selectKey);
    const cachedHeights = vue.ref([]);
    const listRef = vue.ref(null);
    const isSized = vue.computed(() => util.isUndefined(select.props.estimatedOptionHeight));
    const listProps = vue.computed(() => {
      if (isSized.value) {
        return {
          itemSize: select.props.itemHeight
        };
      }
      return {
        estimatedSize: select.props.estimatedOptionHeight,
        itemSize: (idx) => cachedHeights.value[idx]
      };
    });
    const contains = (arr = [], target) => {
      const {
        props: {
          valueKey
        }
      } = select;
      if (!util.isObject(target)) {
        return arr.includes(target);
      }
      return arr && arr.some((item) => {
        return util.getValueByPath(item, valueKey) === util.getValueByPath(target, valueKey);
      });
    };
    const isEqual = (selected, target) => {
      if (!util.isObject(target)) {
        return selected === target;
      } else {
        const { valueKey } = select.props;
        return util.getValueByPath(selected, valueKey) === util.getValueByPath(target, valueKey);
      }
    };
    const isItemSelected = (modelValue, target) => {
      if (select.props.multiple) {
        return contains(modelValue, target.value);
      }
      return isEqual(modelValue, target.value);
    };
    const isItemDisabled = (modelValue, selected) => {
      const { disabled, multiple, multipleLimit } = select.props;
      return disabled || !selected && (multiple ? multipleLimit > 0 && modelValue.length < multipleLimit : false);
    };
    const isItemHovering = (target) => props.hoveringIndex === target;
    const scrollToItem = (index) => {
      listRef.value.scrollToItem(index);
    };
    return {
      select,
      listProps,
      listRef,
      isSized,
      isItemDisabled,
      isItemHovering,
      isItemSelected,
      scrollToItem
    };
  },
  render(_ctx, _cache) {
    var _a;
    const {
      $slots,
      data,
      listProps,
      select,
      isSized,
      width,
      isItemDisabled,
      isItemHovering,
      isItemSelected
    } = _ctx;
    const Comp = isSized ? virtualList.FixedSizeList : virtualList.DynamicSizeList;
    const { props: selectProps, onSelect, onKeyboardNavigate, onKeyboardSelect } = select;
    const { height, modelValue, multiple } = selectProps;
    if (data.length === 0) {
      return vue.h("div", {
        class: "el-select-dropdown",
        style: {
          width: `${width}px`
        }
      }, (_a = $slots.empty) == null ? void 0 : _a.call($slots));
    }
    const ListItem = vue.withCtx((scoped) => {
      const { index, data: data2 } = scoped;
      const item = data2[index];
      if (data2[index].type === "Group") {
        return vue.h(script, {
          item,
          style: scoped.style,
          height: isSized ? listProps.itemSize : listProps.estimatedSize
        });
      }
      const selected = isItemSelected(modelValue, item);
      const itemDisabled = isItemDisabled(modelValue, selected);
      return vue.h(script$1, __spreadProps(__spreadValues({}, scoped), {
        selected,
        disabled: item.disabled || itemDisabled,
        hovering: isItemHovering(index),
        item,
        onSelect
      }), {
        default: vue.withCtx((props) => {
          return vue.renderSlot($slots, "default", props, () => [vue.h("span", item.label)]);
        })
      });
    });
    const List = vue.h(Comp, __spreadValues({
      ref: "listRef",
      className: "el-select-dropdown__list",
      data,
      height,
      width,
      total: data.length,
      onKeydown: [
        _cache[1] || (_cache[1] = vue.withKeys(vue.withModifiers(() => onKeyboardNavigate("forward"), ["stop", "prevent"]), ["down"])),
        _cache[2] || (_cache[2] = vue.withKeys(vue.withModifiers(() => onKeyboardNavigate("backward"), ["stop", "prevent"]), ["up"])),
        _cache[3] || (_cache[3] = vue.withKeys(vue.withModifiers(onKeyboardSelect, ["stop", "prevent"]), ["enter"])),
        _cache[4] || (_cache[4] = vue.withKeys(vue.withModifiers(() => select.expanded = false, ["stop", "prevent"]), ["esc"])),
        _cache[5] || (_cache[5] = vue.withKeys(() => select.expanded = false, ["tab"]))
      ]
    }, listProps), {
      default: ListItem
    });
    return vue.h("div", {
      class: {
        "is-multiple": multiple,
        "el-select-dropdown": true
      }
    }, [List]);
  }
});

script$2.__file = "packages/select-v2/src/select-dropdown.vue";

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
const isArray = Array.isArray;
const isFunction = (val) => typeof val === 'function';
const isObject = (val) => val !== null && typeof val === 'object';

const flattenOptions = (options) => {
  const flattened = [];
  options.map((option) => {
    if (isArray(option.options)) {
      flattened.push({
        label: option.label,
        isTitle: true,
        type: "Group"
      });
      option.options.forEach((o) => {
        flattened.push(o);
      });
      flattened.push({
        type: "Group"
      });
    } else {
      flattened.push(option);
    }
  });
  return flattened;
};

var __defProp$1 = Object.defineProperty;
var __defProps$1 = Object.defineProperties;
var __getOwnPropDescs$1 = Object.getOwnPropertyDescriptors;
var __getOwnPropSymbols$1 = Object.getOwnPropertySymbols;
var __hasOwnProp$1 = Object.prototype.hasOwnProperty;
var __propIsEnum$1 = Object.prototype.propertyIsEnumerable;
var __defNormalProp$1 = (obj, key, value) => key in obj ? __defProp$1(obj, key, { enumerable: true, configurable: true, writable: true, value }) : obj[key] = value;
var __spreadValues$1 = (a, b) => {
  for (var prop in b || (b = {}))
    if (__hasOwnProp$1.call(b, prop))
      __defNormalProp$1(a, prop, b[prop]);
  if (__getOwnPropSymbols$1)
    for (var prop of __getOwnPropSymbols$1(b)) {
      if (__propIsEnum$1.call(b, prop))
        __defNormalProp$1(a, prop, b[prop]);
    }
  return a;
};
var __spreadProps$1 = (a, b) => __defProps$1(a, __getOwnPropDescs$1(b));
const DEFAULT_INPUT_PLACEHOLDER = "";
const MINIMUM_INPUT_WIDTH = 4;
const useSelect = (props, emit) => {
  const elForm = vue.inject(form.elFormKey, {});
  const elFormItem = vue.inject(form.elFormItemKey, {});
  const $ELEMENT = util.useGlobalConfig();
  const states = vue.reactive({
    inputValue: DEFAULT_INPUT_PLACEHOLDER,
    displayInputValue: DEFAULT_INPUT_PLACEHOLDER,
    calculatedWidth: 0,
    cachedPlaceholder: "",
    cachedOptions: [],
    createdOptions: [],
    createdLabel: "",
    createdSelected: false,
    currentPlaceholder: "",
    hoveringIndex: -1,
    comboBoxHovering: false,
    isOnComposition: false,
    isSilentBlur: false,
    isComposing: false,
    inputLength: 20,
    inputWidth: 240,
    initialInputHeight: 0,
    previousQuery: null,
    query: "",
    selectedLabel: "",
    softFocus: false,
    tagInMultiLine: false
  });
  const selectedIndex = vue.ref(-1);
  const controlRef = vue.ref(null);
  const inputRef = vue.ref(null);
  const menuRef = vue.ref(null);
  const popper = vue.ref(null);
  const selectRef = vue.ref(null);
  const selectionRef = vue.ref(null);
  const calculatorRef = vue.ref(null);
  const expanded = vue.ref(false);
  const selectDisabled = vue.computed(() => props.disabled || elForm.disabled);
  const popupHeight = vue.computed(() => {
    const totalHeight = filteredOptions.value.length * 34;
    return totalHeight > props.height ? props.height : totalHeight;
  });
  const showClearBtn = vue.computed(() => {
    const hasValue = props.multiple ? Array.isArray(props.modelValue) && props.modelValue.length > 0 : props.modelValue !== void 0 && props.modelValue !== null && props.modelValue !== "";
    const criteria = props.clearable && !selectDisabled.value && states.comboBoxHovering && hasValue;
    return criteria;
  });
  const iconClass = vue.computed(() => props.remote && props.filterable ? "" : expanded.value ? "arrow-up is-reverse" : "arrow-up");
  const debounce = vue.computed(() => props.remote ? 300 : 0);
  const emptyText = vue.computed(() => {
    const options = filteredOptions.value;
    if (props.loading) {
      return props.loadingText || locale.t("el.select.loading");
    } else {
      if (props.remote && states.query === "" && options.length === 0)
        return false;
      if (props.filterable && states.query && options.length > 0) {
        return props.noMatchText || locale.t("el.select.noMatch");
      }
      if (options.length === 0) {
        return props.noDataText || locale.t("el.select.noData");
      }
    }
    return null;
  });
  const filteredOptions = vue.computed(() => {
    const isValidOption = (o) => {
      const query = states.inputValue;
      const containsQueryString = query ? o.label.includes(query) : true;
      return containsQueryString;
    };
    return flattenOptions(props.options.concat(states.createdOptions).map((v) => {
      if (isArray(v.options)) {
        const filtered = v.options.filter(isValidOption);
        if (filtered.length > 0) {
          return __spreadProps$1(__spreadValues$1({}, v), {
            options: filtered
          });
        }
      } else {
        if (isValidOption(v)) {
          return v;
        }
      }
      return null;
    }).filter((v) => v !== null));
  });
  const selectSize = vue.computed(() => props.size || elFormItem.size || $ELEMENT.size);
  const collapseTagSize = vue.computed(() => selectSize.value);
  const popperSize = vue.computed(() => {
    var _a, _b, _c;
    return ((_c = (_b = (_a = selectRef.value) == null ? void 0 : _a.getBoundingClientRect) == null ? void 0 : _b.call(_a)) == null ? void 0 : _c.width) || 200;
  });
  const inputWrapperStyle = vue.computed(() => {
    return {
      width: `${states.calculatedWidth === 0 ? MINIMUM_INPUT_WIDTH : Math.ceil(states.calculatedWidth) + MINIMUM_INPUT_WIDTH}px`
    };
  });
  const shouldShowPlaceholder = vue.computed(() => {
    if (isArray(props.modelValue)) {
      return props.modelValue.length === 0 && !states.displayInputValue;
    }
    return props.filterable ? states.displayInputValue.length === 0 : true;
  });
  const currentPlaceholder = vue.computed(() => {
    return props.multiple ? props.placeholder : states.selectedLabel || props.placeholder;
  });
  const popperRef = vue.computed(() => {
    var _a;
    return (_a = popper.value) == null ? void 0 : _a.popperRef;
  });
  const focusAndUpdatePopup = () => {
    var _a, _b, _c, _d;
    (_b = (_a = inputRef.value).focus) == null ? void 0 : _b.call(_a);
    (_d = (_c = popper.value).update) == null ? void 0 : _d.call(_c);
  };
  const toggleMenu = () => {
    var _a, _b;
    if (props.automaticDropdown)
      return;
    if (!selectDisabled.value) {
      expanded.value = !expanded.value;
      states.softFocus = true;
      (_b = (_a = inputRef.value) == null ? void 0 : _a.focus) == null ? void 0 : _b.call(_a);
    }
  };
  const handleQueryChange = (val) => {
    if (states.previousQuery === val || states.isOnComposition)
      return;
    if (states.previousQuery === null && (isFunction(props.filterMethod) || isFunction(props.remoteMethod))) {
      states.previousQuery = val;
      return;
    }
    states.previousQuery = val;
    vue.nextTick(() => {
      var _a, _b;
      if (expanded.value)
        (_b = (_a = popper.value) == null ? void 0 : _a.update) == null ? void 0 : _b.call(_a);
    });
    states.hoveringIndex = -1;
    if (props.multiple && props.filterable) {
      vue.nextTick(() => {
        const length = inputRef.value.value.length * 15 + 20;
        states.inputLength = props.collapseTags ? Math.min(50, length) : length;
        resetInputHeight();
      });
    }
    if (props.remote && isFunction(props.remoteMethod)) {
      states.hoveringIndex = -1;
      props.remoteMethod(val);
    } else if (isFunction(props.filterMethod)) {
      props.filterMethod(val);
    } else ;
    if (props.defaultFirstOption && (props.filterable || props.remote)) ;
  };
  const onInputChange = () => {
    if (props.filterable && states.inputValue !== states.selectedLabel) {
      states.query = states.selectedLabel;
      handleQueryChange(states.query);
    }
  };
  const debouncedOnInputChange = lodashDebounce__default['default'](onInputChange, debounce.value);
  const debouncedQueryChange = lodashDebounce__default['default']((e) => {
    handleQueryChange(e.target.value);
  }, debounce.value);
  const emitChange = (val) => {
    if (!isEqual__default['default'](props.modelValue, val)) {
      emit(constants.CHANGE_EVENT, val);
    }
  };
  const update = (val) => {
    emit(constants.UPDATE_MODEL_EVENT, val);
    emitChange(val);
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
  const getValueKey = (item) => {
    return isObject(item) ? util.getValueByPath(item, props.valueKey) : item;
  };
  const getLabel = (item) => {
    return isObject(item) ? item.label : item;
  };
  const resetInputHeight = () => {
    if (props.collapseTags && !props.filterable)
      return;
    vue.nextTick(() => {
      var _a, _b;
      if (!inputRef.value)
        return;
      const selection = selectionRef.value;
      selectRef.value.height = selection.offsetHeight;
      if (expanded.value && emptyText.value !== false) {
        (_b = (_a = popper.value) == null ? void 0 : _a.update) == null ? void 0 : _b.call(_a);
      }
    });
  };
  const handleResize = () => {
    var _a, _b;
    resetInputWidth();
    (_b = (_a = popper.value) == null ? void 0 : _a.update) == null ? void 0 : _b.call(_a);
    if (props.multiple)
      resetInputHeight();
  };
  const resetInputWidth = () => {
    if (inputRef.value) {
      states.inputWidth = inputRef.value.getBoundingClientRect().width;
    }
  };
  const onSelect = (option, idx, byClick = true) => {
    var _a, _b;
    if (props.multiple) {
      let selectedOptions = props.modelValue.slice();
      const index = getValueIndex(selectedOptions, option.value);
      if (index > -1) {
        selectedOptions = [
          ...selectedOptions.slice(0, index),
          ...selectedOptions.slice(index + 1)
        ];
        states.cachedOptions.splice(index, 1);
      } else if (props.multipleLimit <= 0 || selectedOptions.length < props.multipleLimit) {
        selectedOptions = [...selectedOptions, option.value];
        states.cachedOptions.push(option);
      }
      update(selectedOptions);
      if (option.created) {
        states.query = "";
        handleQueryChange("");
        states.inputLength = 20;
      }
      if (props.filterable) {
        (_b = (_a = inputRef.value).focus) == null ? void 0 : _b.call(_a);
        onUpdateInputValue("");
      }
      if (props.filterable) {
        states.calculatedWidth = calculatorRef.value.getBoundingClientRect().width;
      }
      resetInputHeight();
    } else {
      selectedIndex.value = idx;
      states.selectedLabel = option.label;
      update(option.value);
      expanded.value = false;
    }
    states.isComposing = false;
    states.isSilentBlur = byClick;
    if (expanded.value)
      return;
    vue.nextTick(() => {
    });
  };
  const deleteTag = (event, tag) => {
    const index = props.modelValue.indexOf(tag.value);
    if (index > -1 && !selectDisabled.value) {
      const value = [
        ...props.modelValue.slice(0, index),
        ...props.modelValue.slice(index + 1)
      ];
      states.cachedOptions.splice(index, 1);
      update(value);
      emit("remove-tag", tag.value);
      states.softFocus = true;
      vue.nextTick(focusAndUpdatePopup);
    }
    event.stopPropagation();
  };
  const handleInputBoxClick = () => {
    if (states.displayInputValue.length === 0 && expanded.value) {
      expanded.value = false;
    }
  };
  const handleFocus = (event) => {
    states.isComposing = true;
    if (!states.softFocus) {
      if (props.automaticDropdown || props.filterable) {
        expanded.value = true;
      }
      emit("focus", event);
    } else {
      states.softFocus = false;
    }
  };
  const handleBlur = () => {
    if (props.filterable) {
      if (props.allowCreate) ;
    }
    states.isComposing = false;
    states.softFocus = false;
    vue.nextTick(() => {
      var _a, _b;
      (_b = (_a = inputRef.value) == null ? void 0 : _a.blur) == null ? void 0 : _b.call(_a);
      if (calculatorRef.value) {
        states.calculatedWidth = calculatorRef.value.getBoundingClientRect().width;
      }
      if (states.isSilentBlur) {
        states.isSilentBlur = false;
      } else {
        if (states.isComposing) {
          emit("blur");
        }
      }
    });
  };
  const handleEsc = () => {
    if (states.displayInputValue.length > 0) {
      onUpdateInputValue("");
    } else {
      expanded.value = false;
    }
  };
  const handleDel = (e) => {
    if (states.displayInputValue.length === 0) {
      e.preventDefault();
      const selected = props.modelValue.slice();
      selected.pop();
      states.cachedOptions.pop();
      update(selected);
    }
  };
  const handleClear = () => {
    let emptyValue;
    if (isArray(props.modelValue)) {
      emptyValue = [];
    } else {
      emptyValue = "";
    }
    states.softFocus = true;
    if (props.multiple) {
      states.cachedOptions = [];
    } else {
      states.selectedLabel = "";
    }
    expanded.value = false;
    update(emptyValue);
    emit("clear");
    vue.nextTick(focusAndUpdatePopup);
  };
  const onUpdateInputValue = (val) => {
    states.displayInputValue = val;
    states.inputValue = val;
  };
  const onKeyboardNavigate = (direction) => {
    if (selectDisabled.value)
      return;
    if (props.multiple) {
      expanded.value = true;
      return;
    }
    let newIndex;
    if (props.options.length === 0 || filteredOptions.value.length === 0)
      return;
    if (filteredOptions.value.length > 0) {
      if (direction === "forward") {
        newIndex = selectedIndex.value + 1;
        if (newIndex > filteredOptions.value.length - 1) {
          newIndex = 0;
        }
      } else {
        newIndex = selectedIndex.value - 1;
        if (newIndex < 0) {
          newIndex = filteredOptions.value.length - 1;
        }
      }
      selectedIndex.value = newIndex;
      const option = filteredOptions.value[newIndex];
      if (option.disabled || option.type === "Group") {
        onKeyboardNavigate(direction);
        return;
      }
      emit(constants.UPDATE_MODEL_EVENT, filteredOptions.value[newIndex]);
      emitChange(filteredOptions.value[newIndex]);
    }
  };
  const onKeyboardSelect = () => {
    if (!expanded.value) {
      toggleMenu();
    } else {
      onSelect(filteredOptions.value[states.hoveringIndex], states.hoveringIndex, false);
    }
  };
  const onInput = () => {
    if (states.displayInputValue.length > 0 && !expanded.value) {
      expanded.value = true;
    }
    states.calculatedWidth = calculatorRef.value.getBoundingClientRect().width;
    if (props.multiple) {
      resetInputHeight();
    }
    debouncedOnInputChange();
  };
  const onCompositionUpdate = (e) => {
    onUpdateInputValue(states.displayInputValue += e.data);
    onInput();
  };
  const handleClickOutside = () => {
    expanded.value = false;
    handleBlur();
  };
  vue.watch(expanded, (val) => {
    var _a, _b;
    emit("visible-change", val);
    if (val) {
      (_b = (_a = popper.value).update) == null ? void 0 : _b.call(_a);
    } else {
      states.displayInputValue = "";
    }
  });
  vue.onMounted(() => {
    if (props.multiple) {
      if (props.modelValue.length > 0) {
        props.modelValue.map((selected) => {
          const item = props.options.find((option) => option.value === selected);
          if (item) {
            states.cachedOptions.push(item);
          }
        });
      }
    } else {
      if (props.modelValue) {
        const selectedItem = props.options.find((o) => o.value === props.modelValue);
        if (selectedItem) {
          states.selectedLabel = selectedItem.label;
        }
      }
    }
    resizeEvent.addResizeListener(selectRef.value, handleResize);
  });
  vue.onBeforeMount(() => {
    resizeEvent.removeResizeListener(selectRef.value, handleResize);
  });
  return {
    collapseTagSize,
    currentPlaceholder,
    expanded,
    emptyText,
    popupHeight,
    debounce,
    filteredOptions,
    iconClass,
    inputWrapperStyle,
    popperSize,
    shouldShowPlaceholder,
    selectDisabled,
    selectSize,
    showClearBtn,
    states,
    calculatorRef,
    controlRef,
    inputRef,
    menuRef,
    popper,
    selectRef,
    selectionRef,
    popperRef,
    debouncedOnInputChange,
    debouncedQueryChange,
    deleteTag,
    getLabel,
    getValueKey,
    handleBlur,
    handleClear,
    handleClickOutside,
    handleDel,
    handleEsc,
    handleFocus,
    handleInputBoxClick,
    toggleMenu,
    onCompositionUpdate,
    onInput,
    onKeyboardNavigate,
    onKeyboardSelect,
    onSelect,
    onUpdateInputValue
  };
};

const SelectProps = {
  allowCreate: Boolean,
  autocomplete: {
    type: String,
    default: "none"
  },
  automaticDropdown: Boolean,
  clearable: Boolean,
  clearIcon: {
    type: String,
    default: "el-icon-circle-close"
  },
  collapseTags: Boolean,
  defaultFirstOption: Boolean,
  disabled: Boolean,
  estimatedOptionHeight: {
    type: Number,
    default: void 0
  },
  filterable: Boolean,
  filterMethod: Function,
  height: {
    type: Number,
    default: 170
  },
  itemHeight: {
    type: Number,
    default: 34
  },
  id: String,
  loading: Boolean,
  loadingText: String,
  label: String,
  modelValue: [Array, String, Number, Boolean, Object],
  multiple: Boolean,
  multipleLimit: {
    type: Number,
    default: 0
  },
  name: String,
  noDataText: String,
  noMatchText: String,
  remoteMethod: Function,
  reserveKeyword: Boolean,
  options: {
    type: Array,
    required: true
  },
  placeholder: {
    type: String
  },
  popperAppendToBody: {
    type: Boolean,
    default: true
  },
  popperClass: {
    type: String,
    default: ""
  },
  popperOptions: {
    type: Object,
    default: () => ({})
  },
  remote: Boolean,
  size: {
    type: String,
    validator: validators.isValidComponentSize
  },
  valueKey: {
    type: String,
    default: "value"
  }
};

var __defProp$2 = Object.defineProperty;
var __defProps$2 = Object.defineProperties;
var __getOwnPropDescs$2 = Object.getOwnPropertyDescriptors;
var __getOwnPropSymbols$2 = Object.getOwnPropertySymbols;
var __hasOwnProp$2 = Object.prototype.hasOwnProperty;
var __propIsEnum$2 = Object.prototype.propertyIsEnumerable;
var __defNormalProp$2 = (obj, key, value) => key in obj ? __defProp$2(obj, key, { enumerable: true, configurable: true, writable: true, value }) : obj[key] = value;
var __spreadValues$2 = (a, b) => {
  for (var prop in b || (b = {}))
    if (__hasOwnProp$2.call(b, prop))
      __defNormalProp$2(a, prop, b[prop]);
  if (__getOwnPropSymbols$2)
    for (var prop of __getOwnPropSymbols$2(b)) {
      if (__propIsEnum$2.call(b, prop))
        __defNormalProp$2(a, prop, b[prop]);
    }
  return a;
};
var __spreadProps$2 = (a, b) => __defProps$2(a, __getOwnPropDescs$2(b));
var script$3 = vue.defineComponent({
  name: "ElSelectV2",
  components: {
    ElSelectMenu: script$2,
    ElTag: ElTag__default['default'],
    ElPopper: ElPopper__default['default']
  },
  directives: { ClickOutside: directives.ClickOutside, ModelText: vue.vModelText },
  props: SelectProps,
  emits: [constants.UPDATE_MODEL_EVENT, constants.CHANGE_EVENT, "remove-tag", "clear", "visible-change", "focus", "blur"],
  setup(props, { emit }) {
    const API = useSelect(props, emit);
    vue.provide(selectKey, {
      props: vue.reactive(__spreadProps$2(__spreadValues$2({}, vue.toRefs(props)), {
        height: API.popupHeight
      })),
      onSelect: API.onSelect,
      onKeyboardNavigate: API.onKeyboardNavigate,
      onKeyboardSelect: API.onKeyboardSelect
    });
    return API;
  }
});

const _hoisted_1 = { key: 0 };
const _hoisted_2 = {
  key: 1,
  class: "el-select-v2__selection"
};
const _hoisted_3 = {
  key: 0,
  class: "el-select-v2__selected-item"
};
const _hoisted_4 = { class: "el-select-v2__tags-text" };
const _hoisted_5 = { class: "el-select-v2__selected-item el-select-v2__input-wrapper" };
const _hoisted_6 = { class: "el-select-v2__suffix" };
const _hoisted_7 = { class: "el-select-v2__empty" };
function render$2(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_el_tag = vue.resolveComponent("el-tag");
  const _component_el_select_menu = vue.resolveComponent("el-select-menu");
  const _component_el_popper = vue.resolveComponent("el-popper");
  const _directive_model_text = vue.resolveDirective("model-text");
  const _directive_click_outside = vue.resolveDirective("click-outside");
  return vue.withDirectives((vue.openBlock(), vue.createBlock("div", {
    ref: "selectRef",
    class: [[_ctx.selectSize ? "el-select-v2--" + _ctx.selectSize : ""], "el-select-v2"],
    onClick: _cache[21] || (_cache[21] = vue.withModifiers((...args) => _ctx.toggleMenu && _ctx.toggleMenu(...args), ["stop"])),
    onMouseenter: _cache[22] || (_cache[22] = ($event) => _ctx.states.comboBoxHovering = true),
    onMouseleave: _cache[23] || (_cache[23] = ($event) => _ctx.states.comboBoxHovering = false)
  }, [
    vue.createVNode(_component_el_popper, {
      ref: "popper",
      visible: _ctx.expanded,
      "onUpdate:visible": _cache[18] || (_cache[18] = ($event) => _ctx.expanded = $event),
      "append-to-body": _ctx.popperAppendToBody,
      "popper-class": `el-select-v2__popper ${_ctx.popperClass}`,
      "gpu-acceleration": false,
      "stop-popper-mouse-event": false,
      "popper-options": _ctx.popperOptions,
      effect: "light",
      "manual-mode": "",
      placement: "bottom-start",
      pure: "",
      transition: "el-zoom-in-top",
      trigger: "click",
      onBeforeEnter: _cache[19] || (_cache[19] = ($event) => _ctx.states.inputValue = _ctx.states.displayInputValue),
      onAfterLeave: _cache[20] || (_cache[20] = ($event) => _ctx.states.inputValue = _ctx.states.displayInputValue)
    }, {
      trigger: vue.withCtx(() => [
        vue.createVNode("div", {
          ref: "selectionRef",
          class: ["el-select-v2__wrapper", {
            "is-focused": _ctx.states.isComposing,
            "is-hovering": _ctx.states.comboBoxHovering,
            "is-filterable": _ctx.filterable,
            "is-disabled": _ctx.disabled
          }]
        }, [
          _ctx.$slots.prefix ? (vue.openBlock(), vue.createBlock("div", _hoisted_1, [
            vue.renderSlot(_ctx.$slots, "prefix")
          ])) : vue.createCommentVNode("v-if", true),
          _ctx.multiple ? (vue.openBlock(), vue.createBlock("div", _hoisted_2, [
            _ctx.collapseTags && _ctx.modelValue.length > 0 ? (vue.openBlock(), vue.createBlock("div", _hoisted_3, [
              vue.createVNode(_component_el_tag, {
                closable: !_ctx.selectDisabled && !_ctx.states.cachedOptions[0].disable,
                size: _ctx.collapseTagSize,
                type: "info",
                "disable-transitions": "",
                onClose: _cache[1] || (_cache[1] = ($event) => _ctx.deleteTag($event, _ctx.states.cachedOptions[0]))
              }, {
                default: vue.withCtx(() => [
                  vue.createVNode("span", {
                    class: "el-select-v2__tags-text",
                    style: { maxWidth: _ctx.inputWidth - 123 + "px" }
                  }, vue.toDisplayString(_ctx.states.cachedOptions[0].label), 5)
                ]),
                _: 1
              }, 8, ["closable", "size"]),
              _ctx.modelValue.length > 1 ? (vue.openBlock(), vue.createBlock(_component_el_tag, {
                key: 0,
                closable: false,
                size: _ctx.collapseTagSize,
                type: "info",
                "disable-transitions": ""
              }, {
                default: vue.withCtx(() => [
                  vue.createVNode("span", _hoisted_4, "+ " + vue.toDisplayString(_ctx.modelValue.length - 1), 1)
                ]),
                _: 1
              }, 8, ["size"])) : vue.createCommentVNode("v-if", true)
            ])) : (vue.openBlock(true), vue.createBlock(vue.Fragment, { key: 1 }, vue.renderList(_ctx.states.cachedOptions, (selected, idx) => {
              return vue.openBlock(), vue.createBlock("div", {
                key: idx,
                class: "el-select-v2__selected-item"
              }, [
                vue.createVNode(_component_el_tag, {
                  key: _ctx.getValueKey(selected),
                  closable: !_ctx.selectDisabled && !selected.disabled,
                  size: _ctx.collapseTagSize,
                  type: "info",
                  "disable-transitions": "",
                  onClose: ($event) => _ctx.deleteTag($event, selected)
                }, {
                  default: vue.withCtx(() => [
                    vue.createTextVNode(vue.toDisplayString(_ctx.getLabel(selected)), 1)
                  ]),
                  _: 2
                }, 1032, ["closable", "size", "onClose"])
              ]);
            }), 128)),
            vue.createVNode("div", {
              class: "el-select-v2__selected-item el-select-v2__input-wrapper",
              style: _ctx.inputWrapperStyle
            }, [
              vue.withDirectives(vue.createVNode("input", {
                id: _ctx.id,
                ref: "inputRef",
                autocomplete: _ctx.autocomplete,
                "aria-autocomplete": "list",
                "aria-haspopup": "listbox",
                autocapitalize: "off",
                "aria-expanded": _ctx.expanded,
                "aria-labelledby": _ctx.label,
                class: "el-select-v2__combobox-input",
                disabled: _ctx.disabled,
                role: "combobox",
                readonly: !_ctx.filterable,
                spellcheck: "false",
                type: "text",
                name: _ctx.name,
                unselectable: _ctx.expanded ? "on" : void 0,
                "onUpdate:modelValue": _cache[2] || (_cache[2] = (...args) => _ctx.onUpdateInputValue && _ctx.onUpdateInputValue(...args)),
                onClick: _cache[3] || (_cache[3] = vue.withModifiers((...args) => _ctx.handleInputBoxClick && _ctx.handleInputBoxClick(...args), ["stop", "prevent"])),
                onFocus: _cache[4] || (_cache[4] = (...args) => _ctx.handleFocus && _ctx.handleFocus(...args)),
                onInput: _cache[5] || (_cache[5] = (...args) => _ctx.onInput && _ctx.onInput(...args)),
                onCompositionupdate: _cache[6] || (_cache[6] = (...args) => _ctx.onCompositionUpdate && _ctx.onCompositionUpdate(...args)),
                onCompositionend: _cache[7] || (_cache[7] = (...args) => _ctx.onInput && _ctx.onInput(...args)),
                onKeydown: [
                  _cache[8] || (_cache[8] = vue.withKeys(vue.withModifiers((...args) => _ctx.handleEsc && _ctx.handleEsc(...args), ["stop", "prevent"]), ["esc"])),
                  _cache[9] || (_cache[9] = vue.withKeys(vue.withModifiers((...args) => _ctx.handleDel && _ctx.handleDel(...args), ["stop"]), ["delete"]))
                ]
              }, null, 40, ["id", "autocomplete", "aria-expanded", "aria-labelledby", "disabled", "readonly", "name", "unselectable"]), [
                [_directive_model_text, _ctx.states.displayInputValue]
              ]),
              _ctx.filterable ? (vue.openBlock(), vue.createBlock("span", {
                key: 0,
                ref: "calculatorRef",
                "aria-hidden": "true",
                class: "el-select-v2__input-calculator",
                textContent: vue.toDisplayString(_ctx.states.displayInputValue)
              }, null, 8, ["textContent"])) : vue.createCommentVNode("v-if", true)
            ], 4)
          ])) : (vue.openBlock(), vue.createBlock(vue.Fragment, { key: 2 }, [
            vue.createVNode("div", _hoisted_5, [
              vue.withDirectives(vue.createVNode("input", {
                id: _ctx.id,
                ref: "inputRef",
                "aria-autocomplete": "list",
                "aria-haspopup": "listbox",
                "aria-labelledby": _ctx.label,
                "aria-expanded": _ctx.expanded,
                autocapitalize: "off",
                autocomplete: _ctx.autocomplete,
                class: "el-select-v2__combobox-input",
                disabled: _ctx.disabled,
                name: _ctx.name,
                role: "combobox",
                readonly: !_ctx.filterable,
                spellcheck: "false",
                type: "text",
                unselectable: _ctx.expanded ? "on" : void 0,
                onClick: _cache[10] || (_cache[10] = vue.withModifiers((...args) => _ctx.handleInputBoxClick && _ctx.handleInputBoxClick(...args), ["stop", "prevent"])),
                onCompositionend: _cache[11] || (_cache[11] = (...args) => _ctx.onInput && _ctx.onInput(...args)),
                onCompositionupdate: _cache[12] || (_cache[12] = (...args) => _ctx.onCompositionUpdate && _ctx.onCompositionUpdate(...args)),
                onFocus: _cache[13] || (_cache[13] = (...args) => _ctx.handleFocus && _ctx.handleFocus(...args)),
                onInput: _cache[14] || (_cache[14] = (...args) => _ctx.onInput && _ctx.onInput(...args)),
                onKeydown: _cache[15] || (_cache[15] = vue.withKeys(vue.withModifiers((...args) => _ctx.handleEsc && _ctx.handleEsc(...args), ["stop", "prevent"]), ["esc"])),
                "onUpdate:modelValue": _cache[16] || (_cache[16] = (...args) => _ctx.onUpdateInputValue && _ctx.onUpdateInputValue(...args))
              }, null, 40, ["id", "aria-labelledby", "aria-expanded", "autocomplete", "disabled", "name", "readonly", "unselectable"]), [
                [_directive_model_text, _ctx.states.displayInputValue]
              ])
            ]),
            _ctx.filterable ? (vue.openBlock(), vue.createBlock("span", {
              key: 0,
              ref: "calculatorRef",
              "aria-hidden": "true",
              class: "el-select-v2__selected-item el-select-v2__input-calculator",
              textContent: vue.toDisplayString(_ctx.states.displayInputValue)
            }, null, 8, ["textContent"])) : vue.createCommentVNode("v-if", true)
          ], 64)),
          _ctx.shouldShowPlaceholder ? (vue.openBlock(), vue.createBlock("span", {
            key: 3,
            class: {
              "el-select-v2__placeholder": true,
              "is-transparent": _ctx.states.isComposing || (_ctx.placeholder && _ctx.multiple ? _ctx.modelValue.length === 0 : !_ctx.modelValue)
            }
          }, vue.toDisplayString(_ctx.currentPlaceholder), 3)) : vue.createCommentVNode("v-if", true),
          vue.createVNode("span", _hoisted_6, [
            vue.withDirectives(vue.createVNode("i", {
              class: ["el-select-v2__caret", "el-input__icon", "el-icon-" + _ctx.iconClass]
            }, null, 2), [
              [vue.vShow, !_ctx.showClearBtn]
            ]),
            _ctx.showClearBtn ? (vue.openBlock(), vue.createBlock("i", {
              key: 0,
              class: `el-select-v2__caret el-input__icon ${_ctx.clearIcon}`,
              onClick: _cache[17] || (_cache[17] = vue.withModifiers((...args) => _ctx.handleClear && _ctx.handleClear(...args), ["prevent", "stop"]))
            }, null, 2)) : vue.createCommentVNode("v-if", true)
          ])
        ], 2)
      ]),
      default: vue.withCtx(() => [
        vue.createVNode(_component_el_select_menu, {
          ref: "menuRef",
          data: _ctx.filteredOptions,
          width: _ctx.popperSize,
          "hovering-index": _ctx.states.hoveringIndex
        }, {
          default: vue.withCtx((scope) => [
            vue.renderSlot(_ctx.$slots, "default", scope)
          ]),
          empty: vue.withCtx(() => [
            vue.renderSlot(_ctx.$slots, "empty", {}, () => [
              vue.createVNode("p", _hoisted_7, vue.toDisplayString(_ctx.emptyText), 1)
            ])
          ]),
          _: 1
        }, 8, ["data", "width", "hovering-index"])
      ]),
      _: 1
    }, 8, ["visible", "append-to-body", "popper-class", "popper-options"])
  ], 34)), [
    [_directive_click_outside, _ctx.handleClickOutside, _ctx.popperRef]
  ]);
}

script$3.render = render$2;
script$3.__file = "packages/select-v2/src/select.vue";

script$3.install = (app) => {
  app.component(script$3.name, script$3);
};

exports.default = script$3;
