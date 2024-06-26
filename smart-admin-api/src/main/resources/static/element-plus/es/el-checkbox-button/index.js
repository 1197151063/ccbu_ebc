import { inject, computed, getCurrentInstance, ref, watch, defineComponent, openBlock, createBlock, withDirectives, vModelCheckbox, renderSlot, createTextVNode, toDisplayString, createCommentVNode } from 'vue';
import { UPDATE_MODEL_EVENT } from '../utils/constants';
import { useGlobalConfig } from '../utils/util';
import { elFormKey, elFormItemKey } from '../el-form';

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
const objectToString = Object.prototype.toString;
const toTypeString = (value) => objectToString.call(value);

const useCheckboxGroup = () => {
  const ELEMENT = useGlobalConfig();
  const elForm = inject(elFormKey, {});
  const elFormItem = inject(elFormItemKey, {});
  const checkboxGroup = inject("CheckboxGroup", {});
  const isGroup = computed(() => checkboxGroup && (checkboxGroup == null ? void 0 : checkboxGroup.name) === "ElCheckboxGroup");
  const elFormItemSize = computed(() => {
    return elFormItem.size;
  });
  return {
    isGroup,
    checkboxGroup,
    elForm,
    ELEMENT,
    elFormItemSize,
    elFormItem
  };
};
const useModel = (props) => {
  let selfModel = false;
  const { emit } = getCurrentInstance();
  const { isGroup, checkboxGroup } = useCheckboxGroup();
  const isLimitExceeded = ref(false);
  const store = computed(() => {
    var _a;
    return checkboxGroup ? (_a = checkboxGroup.modelValue) == null ? void 0 : _a.value : props.modelValue;
  });
  const model = computed({
    get() {
      var _a;
      return isGroup.value ? store.value : (_a = props.modelValue) != null ? _a : selfModel;
    },
    set(val) {
      var _a;
      if (isGroup.value && Array.isArray(val)) {
        isLimitExceeded.value = false;
        if (checkboxGroup.min !== void 0 && val.length < checkboxGroup.min.value) {
          isLimitExceeded.value = true;
        }
        if (checkboxGroup.max !== void 0 && val.length > checkboxGroup.max.value) {
          isLimitExceeded.value = true;
        }
        isLimitExceeded.value === false && ((_a = checkboxGroup == null ? void 0 : checkboxGroup.changeEvent) == null ? void 0 : _a.call(checkboxGroup, val));
      } else {
        emit(UPDATE_MODEL_EVENT, val);
        selfModel = val;
      }
    }
  });
  return {
    model,
    isLimitExceeded
  };
};
const useCheckboxStatus = (props, { model }) => {
  const { isGroup, checkboxGroup, elFormItemSize, ELEMENT } = useCheckboxGroup();
  const focus = ref(false);
  const size = computed(() => {
    var _a;
    return ((_a = checkboxGroup == null ? void 0 : checkboxGroup.checkboxGroupSize) == null ? void 0 : _a.value) || elFormItemSize.value || ELEMENT.size;
  });
  const isChecked = computed(() => {
    const value = model.value;
    if (toTypeString(value) === "[object Boolean]") {
      return value;
    } else if (Array.isArray(value)) {
      return value.includes(props.label);
    } else if (value !== null && value !== void 0) {
      return value === props.trueLabel;
    }
  });
  const checkboxSize = computed(() => {
    var _a;
    const temCheckboxSize = props.size || elFormItemSize.value || ELEMENT.size;
    return isGroup.value ? ((_a = checkboxGroup == null ? void 0 : checkboxGroup.checkboxGroupSize) == null ? void 0 : _a.value) || temCheckboxSize : temCheckboxSize;
  });
  return {
    isChecked,
    focus,
    size,
    checkboxSize
  };
};
const useDisabled = (props, { model, isChecked }) => {
  const { elForm, isGroup, checkboxGroup } = useCheckboxGroup();
  const isLimitDisabled = computed(() => {
    var _a, _b;
    const max = (_a = checkboxGroup.max) == null ? void 0 : _a.value;
    const min = (_b = checkboxGroup.min) == null ? void 0 : _b.value;
    return !!(max || min) && (model.value.length >= max && !isChecked.value) || model.value.length <= min && isChecked.value;
  });
  const isDisabled = computed(() => {
    var _a;
    const disabled = props.disabled || elForm.disabled;
    return isGroup.value ? ((_a = checkboxGroup.disabled) == null ? void 0 : _a.value) || disabled || isLimitDisabled.value : props.disabled || elForm.disabled;
  });
  return {
    isDisabled,
    isLimitDisabled
  };
};
const setStoreValue = (props, { model }) => {
  function addToStore() {
    if (Array.isArray(model.value) && !model.value.includes(props.label)) {
      model.value.push(props.label);
    } else {
      model.value = props.trueLabel || true;
    }
  }
  props.checked && addToStore();
};
const useEvent = (props, { isLimitExceeded }) => {
  const { elFormItem } = useCheckboxGroup();
  const { emit } = getCurrentInstance();
  function handleChange(e) {
    var _a, _b;
    if (isLimitExceeded.value)
      return;
    const target = e.target;
    const value = target.checked ? (_a = props.trueLabel) != null ? _a : true : (_b = props.falseLabel) != null ? _b : false;
    emit("change", value, e);
  }
  watch(() => props.modelValue, (val) => {
    var _a;
    (_a = elFormItem.formItemMitt) == null ? void 0 : _a.emit("el.form.change", [val]);
  });
  return {
    handleChange
  };
};
const useCheckbox = (props) => {
  const { model, isLimitExceeded } = useModel(props);
  const { focus, size, isChecked, checkboxSize } = useCheckboxStatus(props, { model });
  const { isDisabled } = useDisabled(props, { model, isChecked });
  const { handleChange } = useEvent(props, { isLimitExceeded });
  setStoreValue(props, { model });
  return {
    isChecked,
    isDisabled,
    checkboxSize,
    model,
    handleChange,
    focus,
    size
  };
};

var script = defineComponent({
  name: "ElCheckboxButton",
  props: {
    modelValue: {
      type: [Boolean, Number, String],
      default: () => void 0
    },
    label: {
      type: [Boolean, Number, String]
    },
    indeterminate: Boolean,
    disabled: Boolean,
    checked: Boolean,
    name: {
      type: String,
      default: void 0
    },
    trueLabel: {
      type: [String, Number],
      default: void 0
    },
    falseLabel: {
      type: [String, Number],
      default: void 0
    }
  },
  emits: [UPDATE_MODEL_EVENT, "change"],
  setup(props) {
    const { focus, isChecked, isDisabled, size, model, handleChange } = useCheckbox(props);
    const { checkboxGroup } = useCheckboxGroup();
    const activeStyle = computed(() => {
      var _a, _b, _c, _d;
      const fillValue = (_b = (_a = checkboxGroup == null ? void 0 : checkboxGroup.fill) == null ? void 0 : _a.value) != null ? _b : "";
      return {
        backgroundColor: fillValue,
        borderColor: fillValue,
        color: (_d = (_c = checkboxGroup == null ? void 0 : checkboxGroup.textColor) == null ? void 0 : _c.value) != null ? _d : "",
        boxShadow: fillValue ? `-1px 0 0 0 ${fillValue}` : null
      };
    });
    return {
      focus,
      isChecked,
      isDisabled,
      model,
      handleChange,
      activeStyle,
      size
    };
  }
});

function render(_ctx, _cache, $props, $setup, $data, $options) {
  return openBlock(), createBlock("label", {
    class: ["el-checkbox-button", [
      _ctx.size ? "el-checkbox-button--" + _ctx.size : "",
      { "is-disabled": _ctx.isDisabled },
      { "is-checked": _ctx.isChecked },
      { "is-focus": _ctx.focus }
    ]],
    role: "checkbox",
    "aria-checked": _ctx.isChecked,
    "aria-disabled": _ctx.isDisabled
  }, [
    _ctx.trueLabel || _ctx.falseLabel ? withDirectives((openBlock(), createBlock("input", {
      key: 0,
      "onUpdate:modelValue": _cache[1] || (_cache[1] = ($event) => _ctx.model = $event),
      checked: _ctx.isChecked,
      class: "el-checkbox-button__original",
      type: "checkbox",
      name: _ctx.name,
      disabled: _ctx.isDisabled,
      "true-value": _ctx.trueLabel,
      "false-value": _ctx.falseLabel,
      onChange: _cache[2] || (_cache[2] = (...args) => _ctx.handleChange && _ctx.handleChange(...args)),
      onFocus: _cache[3] || (_cache[3] = ($event) => _ctx.focus = true),
      onBlur: _cache[4] || (_cache[4] = ($event) => _ctx.focus = false)
    }, null, 40, ["checked", "name", "disabled", "true-value", "false-value"])), [
      [vModelCheckbox, _ctx.model]
    ]) : withDirectives((openBlock(), createBlock("input", {
      key: 1,
      "onUpdate:modelValue": _cache[5] || (_cache[5] = ($event) => _ctx.model = $event),
      class: "el-checkbox-button__original",
      type: "checkbox",
      name: _ctx.name,
      disabled: _ctx.isDisabled,
      value: _ctx.label,
      onChange: _cache[6] || (_cache[6] = (...args) => _ctx.handleChange && _ctx.handleChange(...args)),
      onFocus: _cache[7] || (_cache[7] = ($event) => _ctx.focus = true),
      onBlur: _cache[8] || (_cache[8] = ($event) => _ctx.focus = false)
    }, null, 40, ["name", "disabled", "value"])), [
      [vModelCheckbox, _ctx.model]
    ]),
    _ctx.$slots.default || _ctx.label ? (openBlock(), createBlock("span", {
      key: 2,
      class: "el-checkbox-button__inner",
      style: _ctx.isChecked ? _ctx.activeStyle : null
    }, [
      renderSlot(_ctx.$slots, "default", {}, () => [
        createTextVNode(toDisplayString(_ctx.label), 1)
      ])
    ], 4)) : createCommentVNode("v-if", true)
  ], 10, ["aria-checked", "aria-disabled"]);
}

script.render = render;
script.__file = "packages/checkbox/src/checkbox-button.vue";

script.install = (app) => {
  app.component(script.name, script);
};
const _CheckboxButton = script;

export default _CheckboxButton;
