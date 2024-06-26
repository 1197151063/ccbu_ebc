import { defineComponent, ref, inject, watch, onMounted, onUpdated, onBeforeUnmount, nextTick, h, Fragment, getCurrentInstance, computed, reactive, toRefs, provide, resolveComponent, openBlock, createBlock, createVNode, withCtx, renderSlot, createTextVNode, toDisplayString, createCommentVNode, Transition } from 'vue';
import AsyncValidator from 'async-validator';
import { addResizeListener, removeResizeListener } from '../utils/resize-event';
import { useGlobalConfig, getPropByPath } from '../utils/util';
import { isValidComponentSize } from '../utils/validators';
import mitt from 'mitt';

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
const NOOP = () => { };

const elFormKey = "elForm";
const elFormItemKey = "elFormItem";
const elFormEvents = {
  addField: "el.form.addField",
  removeField: "el.form.removeField"
};

var LabelWrap = defineComponent({
  name: "ElLabelWrap",
  props: {
    isAutoWidth: Boolean,
    updateAll: Boolean
  },
  setup(props, { slots }) {
    const el = ref(null);
    const elForm = inject(elFormKey);
    const elFormItem = inject(elFormItemKey);
    const computedWidth = ref(0);
    watch(computedWidth, (val, oldVal) => {
      if (props.updateAll) {
        elForm.registerLabelWidth(val, oldVal);
        elFormItem.updateComputedLabelWidth(val);
      }
    });
    const getLabelWidth = () => {
      var _a;
      if ((_a = el.value) == null ? void 0 : _a.firstElementChild) {
        const width = window.getComputedStyle(el.value.firstElementChild).width;
        return Math.ceil(parseFloat(width));
      } else {
        return 0;
      }
    };
    const updateLabelWidth = (action = "update") => {
      nextTick(() => {
        if (slots.default && props.isAutoWidth) {
          if (action === "update") {
            computedWidth.value = getLabelWidth();
          } else if (action === "remove") {
            elForm.deregisterLabelWidth(computedWidth.value);
          }
        }
      });
    };
    const updateLabelWidthFn = () => updateLabelWidth("update");
    onMounted(() => {
      addResizeListener(el.value.firstElementChild, updateLabelWidthFn);
      updateLabelWidthFn();
    });
    onUpdated(updateLabelWidthFn);
    onBeforeUnmount(() => {
      updateLabelWidth("remove");
      removeResizeListener(el.value.firstElementChild, updateLabelWidthFn);
    });
    function render() {
      var _a, _b;
      if (!slots)
        return null;
      if (props.isAutoWidth) {
        const autoLabelWidth = elForm.autoLabelWidth;
        const style = {};
        if (autoLabelWidth && autoLabelWidth !== "auto") {
          const marginLeft = parseInt(autoLabelWidth, 10) - computedWidth.value;
          if (marginLeft) {
            style.marginLeft = marginLeft + "px";
          }
        }
        return h("div", {
          ref: el,
          class: ["el-form-item__label-wrap"],
          style
        }, (_a = slots.default) == null ? void 0 : _a.call(slots));
      } else {
        return h(Fragment, { ref: el }, (_b = slots.default) == null ? void 0 : _b.call(slots));
      }
    }
    return render;
  }
});

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
var script = defineComponent({
  name: "ElFormItem",
  componentName: "ElFormItem",
  components: {
    LabelWrap
  },
  props: {
    label: String,
    labelWidth: String,
    prop: String,
    required: {
      type: Boolean,
      default: void 0
    },
    rules: [Object, Array],
    error: String,
    validateStatus: String,
    for: String,
    inlineMessage: {
      type: [String, Boolean],
      default: ""
    },
    showMessage: {
      type: Boolean,
      default: true
    },
    size: {
      types: String,
      validator: isValidComponentSize
    }
  },
  setup(props) {
    const formItemMitt = mitt();
    const $ELEMENT = useGlobalConfig();
    const elForm = inject(elFormKey, {});
    const validateState = ref("");
    const validateMessage = ref("");
    const validateDisabled = ref(false);
    const computedLabelWidth = ref("");
    const vm = getCurrentInstance();
    const isNested = computed(() => {
      let parent = vm.parent;
      while (parent && parent.type.name !== "ElForm") {
        if (parent.type.name === "ElFormItem") {
          return true;
        }
        parent = parent.parent;
      }
      return false;
    });
    let initialValue = void 0;
    watch(() => props.error, (val) => {
      validateMessage.value = val;
      validateState.value = val ? "error" : "";
    }, {
      immediate: true
    });
    watch(() => props.validateStatus, (val) => {
      validateState.value = val;
    });
    const labelFor = computed(() => props.for || props.prop);
    const labelStyle = computed(() => {
      if (elForm.labelPosition === "top")
        return {};
      const labelWidth = props.labelWidth || elForm.labelWidth;
      if (labelWidth) {
        return {
          width: labelWidth
        };
      }
      return {};
    });
    const contentStyle = computed(() => {
      if (elForm.labelPosition === "top" || elForm.inline) {
        return {};
      }
      if (!props.label && !props.labelWidth && isNested.value) {
        return {};
      }
      const labelWidth = props.labelWidth || elForm.labelWidth;
      const ret = {};
      if (labelWidth === "auto") {
        if (props.labelWidth === "auto") {
          ret.marginLeft = computedLabelWidth.value;
        } else if (elForm.labelWidth === "auto") {
          ret.marginLeft = elForm.autoLabelWidth;
        }
      } else {
        ret.marginLeft = labelWidth;
      }
      return ret;
    });
    const fieldValue = computed(() => {
      const model = elForm.model;
      if (!model || !props.prop) {
        return;
      }
      let path = props.prop;
      if (path.indexOf(":") !== -1) {
        path = path.replace(/:/, ".");
      }
      return getPropByPath(model, path, true).v;
    });
    const isRequired = computed(() => {
      let rules = getRules();
      let required = false;
      if (rules && rules.length) {
        rules.every((rule) => {
          if (rule.required) {
            required = true;
            return false;
          }
          return true;
        });
      }
      return required;
    });
    const elFormItemSize = computed(() => props.size || elForm.size);
    const sizeClass = computed(() => {
      return elFormItemSize.value || $ELEMENT.size;
    });
    const validate = (trigger, callback = NOOP) => {
      validateDisabled.value = false;
      const rules = getFilteredRule(trigger);
      if ((!rules || rules.length === 0) && props.required === void 0) {
        callback();
        return;
      }
      validateState.value = "validating";
      const descriptor = {};
      if (rules && rules.length > 0) {
        rules.forEach((rule) => {
          delete rule.trigger;
        });
      }
      descriptor[props.prop] = rules;
      const validator = new AsyncValidator(descriptor);
      const model = {};
      model[props.prop] = fieldValue.value;
      validator.validate(model, { firstFields: true }, (errors, invalidFields) => {
        var _a;
        validateState.value = !errors ? "success" : "error";
        validateMessage.value = errors ? errors[0].message : "";
        callback(validateMessage.value, invalidFields);
        (_a = elForm.emit) == null ? void 0 : _a.call(elForm, "validate", props.prop, !errors, validateMessage.value || null);
      });
    };
    const clearValidate = () => {
      validateState.value = "";
      validateMessage.value = "";
      validateDisabled.value = false;
    };
    const resetField = () => {
      validateState.value = "";
      validateMessage.value = "";
      let model = elForm.model;
      let value = fieldValue.value;
      let path = props.prop;
      if (path.indexOf(":") !== -1) {
        path = path.replace(/:/, ".");
      }
      let prop = getPropByPath(model, path, true);
      validateDisabled.value = true;
      if (Array.isArray(value)) {
        prop.o[prop.k] = [].concat(initialValue);
      } else {
        prop.o[prop.k] = initialValue;
      }
      nextTick(() => {
        validateDisabled.value = false;
      });
    };
    const getRules = () => {
      const formRules = elForm.rules;
      const selfRules = props.rules;
      const requiredRule = props.required !== void 0 ? { required: !!props.required } : [];
      const prop = getPropByPath(formRules, props.prop || "", false);
      const normalizedRule = formRules ? prop.o[props.prop || ""] || prop.v : [];
      return [].concat(selfRules || normalizedRule || []).concat(requiredRule);
    };
    const getFilteredRule = (trigger) => {
      const rules = getRules();
      return rules.filter((rule) => {
        if (!rule.trigger || trigger === "")
          return true;
        if (Array.isArray(rule.trigger)) {
          return rule.trigger.indexOf(trigger) > -1;
        } else {
          return rule.trigger === trigger;
        }
      }).map((rule) => __spreadValues({}, rule));
    };
    const onFieldBlur = () => {
      validate("blur");
    };
    const onFieldChange = () => {
      if (validateDisabled.value) {
        validateDisabled.value = false;
        return;
      }
      validate("change");
    };
    const updateComputedLabelWidth = (width) => {
      computedLabelWidth.value = width ? `${width}px` : "";
    };
    const addValidateEvents = () => {
      const rules = getRules();
      if (rules.length || props.required !== void 0) {
        formItemMitt.on("el.form.blur", onFieldBlur);
        formItemMitt.on("el.form.change", onFieldChange);
      }
    };
    const removeValidateEvents = () => {
      formItemMitt.off("el.form.blur", onFieldBlur);
      formItemMitt.off("el.form.change", onFieldChange);
    };
    const elFormItem = reactive(__spreadProps(__spreadValues({}, toRefs(props)), {
      size: sizeClass,
      validateState,
      removeValidateEvents,
      addValidateEvents,
      resetField,
      clearValidate,
      validate,
      formItemMitt,
      updateComputedLabelWidth
    }));
    onMounted(() => {
      var _a;
      if (props.prop) {
        (_a = elForm.formMitt) == null ? void 0 : _a.emit(elFormEvents.addField, elFormItem);
        let value = fieldValue.value;
        initialValue = Array.isArray(value) ? [...value] : value;
        addValidateEvents();
      }
    });
    onBeforeUnmount(() => {
      var _a;
      (_a = elForm.formMitt) == null ? void 0 : _a.emit(elFormEvents.removeField, elFormItem);
    });
    provide(elFormItemKey, elFormItem);
    const formItemClass = computed(() => [
      {
        "el-form-item--feedback": elForm.statusIcon,
        "is-error": validateState.value === "error",
        "is-validating": validateState.value === "validating",
        "is-success": validateState.value === "success",
        "is-required": isRequired.value || props.required,
        "is-no-asterisk": elForm.hideRequiredAsterisk
      },
      sizeClass.value ? "el-form-item--" + sizeClass.value : ""
    ]);
    const shouldShowError = computed(() => {
      return validateState.value === "error" && props.showMessage && elForm.showMessage;
    });
    return {
      formItemClass,
      shouldShowError,
      elForm,
      labelStyle,
      contentStyle,
      validateMessage,
      labelFor,
      resetField,
      clearValidate
    };
  }
});

function render(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_LabelWrap = resolveComponent("LabelWrap");
  return openBlock(), createBlock("div", {
    class: ["el-form-item", _ctx.formItemClass]
  }, [
    createVNode(_component_LabelWrap, {
      "is-auto-width": _ctx.labelStyle.width === "auto",
      "update-all": _ctx.elForm.labelWidth === "auto"
    }, {
      default: withCtx(() => [
        _ctx.label || _ctx.$slots.label ? (openBlock(), createBlock("label", {
          key: 0,
          for: _ctx.labelFor,
          class: "el-form-item__label",
          style: _ctx.labelStyle
        }, [
          renderSlot(_ctx.$slots, "label", {}, () => [
            createTextVNode(toDisplayString(_ctx.label + _ctx.elForm.labelSuffix), 1)
          ])
        ], 12, ["for"])) : createCommentVNode("v-if", true)
      ]),
      _: 3
    }, 8, ["is-auto-width", "update-all"]),
    createVNode("div", {
      class: "el-form-item__content",
      style: _ctx.contentStyle
    }, [
      renderSlot(_ctx.$slots, "default"),
      createVNode(Transition, { name: "el-zoom-in-top" }, {
        default: withCtx(() => [
          _ctx.shouldShowError ? renderSlot(_ctx.$slots, "error", {
            key: 0,
            error: _ctx.validateMessage
          }, () => [
            createVNode("div", {
              class: ["el-form-item__error", {
                "el-form-item__error--inline": typeof _ctx.inlineMessage === "boolean" ? _ctx.inlineMessage : _ctx.elForm.inlineMessage || false
              }]
            }, toDisplayString(_ctx.validateMessage), 3)
          ]) : createCommentVNode("v-if", true)
        ]),
        _: 3
      })
    ], 4)
  ], 2);
}

script.render = render;
script.__file = "packages/form/src/form-item.vue";

script.install = (app) => {
  app.component(script.name, script);
};
const _FormItem = script;

export default _FormItem;
