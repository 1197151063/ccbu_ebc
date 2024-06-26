'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
var validators = require('../utils/validators');
var DescriptionsItem = require('../el-descriptions-item');
var util = require('../utils/util');

function _interopDefaultLegacy (e) { return e && typeof e === 'object' && 'default' in e ? e : { 'default': e }; }

var DescriptionsItem__default = /*#__PURE__*/_interopDefaultLegacy(DescriptionsItem);

const elDescriptionsKey = "elDescriptions";

var DescriptionsCell = vue.defineComponent({
  name: "ElDescriptionsCell",
  props: {
    cell: {
      type: Object
    },
    tag: {
      type: String
    },
    type: {
      type: String
    }
  },
  setup(props) {
    const descriptions = vue.inject(elDescriptionsKey, {});
    const label = vue.computed(() => {
      var _a, _b, _c, _d, _e;
      return ((_c = (_b = (_a = props.cell) == null ? void 0 : _a.children) == null ? void 0 : _b.label) == null ? void 0 : _c.call(_b)) || ((_e = (_d = props.cell) == null ? void 0 : _d.props) == null ? void 0 : _e.label);
    });
    const content = vue.computed(() => {
      var _a, _b, _c;
      return (_c = (_b = (_a = props.cell) == null ? void 0 : _a.children) == null ? void 0 : _b.default) == null ? void 0 : _c.call(_b);
    });
    const span = vue.computed(() => {
      var _a, _b;
      return ((_b = (_a = props.cell) == null ? void 0 : _a.props) == null ? void 0 : _b.span) || 1;
    });
    return {
      descriptions,
      label,
      content,
      span
    };
  },
  render() {
    switch (this.type) {
      case "label":
        return vue.h(this.tag, {
          class: ["el-descriptions__label", { "is-bordered-label": this.descriptions.border }],
          colSpan: this.descriptions.direction === "vertical" ? this.span : 1
        }, this.label);
      case "content":
        return vue.h(this.tag, {
          class: "el-descriptions__content",
          colSpan: this.descriptions.direction === "vertical" ? this.span : this.span * 2 - 1
        }, this.content);
      default:
        return vue.h("td", {
          colSpan: this.span
        }, [
          vue.h("span", {
            class: ["el-descriptions__label", { "is-bordered-label": this.descriptions.border }]
          }, this.label),
          vue.h("span", {
            class: "el-descriptions__content"
          }, this.content)
        ]);
    }
  }
});

var script = vue.defineComponent({
  name: "ElDescriptionsRow",
  components: {
    [DescriptionsCell.name]: DescriptionsCell
  },
  props: {
    row: {
      type: Array
    }
  },
  setup() {
    const descriptions = vue.inject(elDescriptionsKey, {});
    return {
      descriptions
    };
  }
});

const _hoisted_1 = { key: 1 };
function render(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_el_descriptions_cell = vue.resolveComponent("el-descriptions-cell");
  return _ctx.descriptions.direction === "vertical" ? (vue.openBlock(), vue.createBlock(vue.Fragment, { key: 0 }, [
    vue.createVNode("tr", null, [
      (vue.openBlock(true), vue.createBlock(vue.Fragment, null, vue.renderList(_ctx.row, (cell, index) => {
        return vue.openBlock(), vue.createBlock(_component_el_descriptions_cell, {
          key: `tr1-${index}`,
          cell,
          tag: "th",
          type: "label"
        }, null, 8, ["cell"]);
      }), 128))
    ]),
    vue.createVNode("tr", null, [
      (vue.openBlock(true), vue.createBlock(vue.Fragment, null, vue.renderList(_ctx.row, (cell, index) => {
        return vue.openBlock(), vue.createBlock(_component_el_descriptions_cell, {
          key: `tr2-${index}`,
          cell,
          tag: "td",
          type: "content"
        }, null, 8, ["cell"]);
      }), 128))
    ])
  ], 64)) : (vue.openBlock(), vue.createBlock("tr", _hoisted_1, [
    (vue.openBlock(true), vue.createBlock(vue.Fragment, null, vue.renderList(_ctx.row, (cell, index) => {
      return vue.openBlock(), vue.createBlock(vue.Fragment, {
        key: `tr3-${index}`
      }, [
        _ctx.descriptions.border ? (vue.openBlock(), vue.createBlock(vue.Fragment, { key: 0 }, [
          vue.createVNode(_component_el_descriptions_cell, {
            cell,
            tag: "td",
            type: "label"
          }, null, 8, ["cell"]),
          vue.createVNode(_component_el_descriptions_cell, {
            cell,
            tag: "td",
            type: "content"
          }, null, 8, ["cell"])
        ], 64)) : (vue.openBlock(), vue.createBlock(_component_el_descriptions_cell, {
          key: 1,
          cell,
          tag: "td",
          type: "both"
        }, null, 8, ["cell"]))
      ], 64);
    }), 128))
  ]));
}

script.render = render;
script.__file = "packages/descriptions/src/descriptions-row.vue";

var script$1 = vue.defineComponent({
  name: "ElDescriptions",
  components: {
    [DescriptionsItem__default['default'].name]: DescriptionsItem__default['default'],
    [script.name]: script
  },
  props: {
    border: {
      type: Boolean,
      default: false
    },
    column: {
      type: Number,
      default: 3
    },
    direction: {
      type: String,
      default: "horizontal"
    },
    size: {
      type: String,
      validator: validators.isValidComponentSize
    },
    title: {
      type: String,
      default: ""
    },
    extra: {
      type: String,
      default: ""
    }
  },
  setup(props, { slots }) {
    vue.provide(elDescriptionsKey, props);
    const $ELEMENT = util.useGlobalConfig();
    const descriptionsSize = vue.computed(() => {
      return props.size || $ELEMENT.size;
    });
    const flattedChildren = (children) => {
      const temp = Array.isArray(children) ? children : [children];
      const res = [];
      temp.forEach((child) => {
        if (Array.isArray(child.children)) {
          res.push(...flattedChildren(child.children));
        } else {
          res.push(child);
        }
      });
      return res;
    };
    const filledNode = (node, span, count, isLast = false) => {
      if (!node.props) {
        node.props = {};
      }
      if (span > count) {
        node.props.span = count;
      }
      if (isLast) {
        node.props.span = props.column;
      }
      return node;
    };
    const rows = vue.computed(() => {
      var _a;
      const children = flattedChildren((_a = slots.default) == null ? void 0 : _a.call(slots)).filter((node) => {
        var _a2;
        return ((_a2 = node == null ? void 0 : node.type) == null ? void 0 : _a2.name) === "ElDescriptionsItem";
      });
      const rows2 = [];
      let temp = [];
      let count = props.column;
      children.forEach((node, index) => {
        var _a2;
        const span = ((_a2 = node.props) == null ? void 0 : _a2.span) || 1;
        if (index === children.length - 1) {
          temp.push(filledNode(node, span, count, true));
          rows2.push(temp);
          return;
        }
        if (span < count) {
          count -= span;
          temp.push(node);
        } else {
          temp.push(filledNode(node, span, count));
          rows2.push(temp);
          count = props.column;
          temp = [];
        }
      });
      return rows2;
    });
    return {
      descriptionsSize,
      rows
    };
  }
});

const _hoisted_1$1 = { class: "el-descriptions" };
const _hoisted_2 = {
  key: 0,
  class: "el-descriptions__header"
};
const _hoisted_3 = { class: "el-descriptions__title" };
const _hoisted_4 = { class: "el-descriptions__extra" };
const _hoisted_5 = { class: "el-descriptions__body" };
function render$1(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_el_descriptions_row = vue.resolveComponent("el-descriptions-row");
  return vue.openBlock(), vue.createBlock("div", _hoisted_1$1, [
    _ctx.title || _ctx.extra || _ctx.$slots.title || _ctx.$slots.extra ? (vue.openBlock(), vue.createBlock("div", _hoisted_2, [
      vue.createVNode("div", _hoisted_3, [
        vue.renderSlot(_ctx.$slots, "title", {}, () => [
          vue.createTextVNode(vue.toDisplayString(_ctx.title), 1)
        ])
      ]),
      vue.createVNode("div", _hoisted_4, [
        vue.renderSlot(_ctx.$slots, "extra", {}, () => [
          vue.createTextVNode(vue.toDisplayString(_ctx.extra), 1)
        ])
      ])
    ])) : vue.createCommentVNode("v-if", true),
    vue.createVNode("div", _hoisted_5, [
      vue.createVNode("table", {
        class: [{ "is-bordered": _ctx.border }, _ctx.descriptionsSize ? `el-descriptions--${_ctx.descriptionsSize}` : ""]
      }, [
        vue.createVNode("tbody", null, [
          (vue.openBlock(true), vue.createBlock(vue.Fragment, null, vue.renderList(_ctx.rows, (row, index) => {
            return vue.openBlock(), vue.createBlock(_component_el_descriptions_row, {
              key: index,
              row
            }, null, 8, ["row"]);
          }), 128))
        ])
      ], 2)
    ])
  ]);
}

script$1.render = render$1;
script$1.__file = "packages/descriptions/src/index.vue";

script$1.install = (app) => {
  app.component(script$1.name, script$1);
};
const _Descriptions = script$1;

exports.default = _Descriptions;
