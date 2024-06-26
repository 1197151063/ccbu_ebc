import { defineComponent, inject, computed, h, resolveComponent, openBlock, createBlock, Fragment, createVNode, renderList, provide, renderSlot, createTextVNode, toDisplayString, createCommentVNode } from 'vue';
import { isValidComponentSize } from '../utils/validators';
import DescriptionsItem from '../el-descriptions-item';
import { useGlobalConfig } from '../utils/util';

const elDescriptionsKey = "elDescriptions";

var DescriptionsCell = defineComponent({
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
    const descriptions = inject(elDescriptionsKey, {});
    const label = computed(() => {
      var _a, _b, _c, _d, _e;
      return ((_c = (_b = (_a = props.cell) == null ? void 0 : _a.children) == null ? void 0 : _b.label) == null ? void 0 : _c.call(_b)) || ((_e = (_d = props.cell) == null ? void 0 : _d.props) == null ? void 0 : _e.label);
    });
    const content = computed(() => {
      var _a, _b, _c;
      return (_c = (_b = (_a = props.cell) == null ? void 0 : _a.children) == null ? void 0 : _b.default) == null ? void 0 : _c.call(_b);
    });
    const span = computed(() => {
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
        return h(this.tag, {
          class: ["el-descriptions__label", { "is-bordered-label": this.descriptions.border }],
          colSpan: this.descriptions.direction === "vertical" ? this.span : 1
        }, this.label);
      case "content":
        return h(this.tag, {
          class: "el-descriptions__content",
          colSpan: this.descriptions.direction === "vertical" ? this.span : this.span * 2 - 1
        }, this.content);
      default:
        return h("td", {
          colSpan: this.span
        }, [
          h("span", {
            class: ["el-descriptions__label", { "is-bordered-label": this.descriptions.border }]
          }, this.label),
          h("span", {
            class: "el-descriptions__content"
          }, this.content)
        ]);
    }
  }
});

var script = defineComponent({
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
    const descriptions = inject(elDescriptionsKey, {});
    return {
      descriptions
    };
  }
});

const _hoisted_1 = { key: 1 };
function render(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_el_descriptions_cell = resolveComponent("el-descriptions-cell");
  return _ctx.descriptions.direction === "vertical" ? (openBlock(), createBlock(Fragment, { key: 0 }, [
    createVNode("tr", null, [
      (openBlock(true), createBlock(Fragment, null, renderList(_ctx.row, (cell, index) => {
        return openBlock(), createBlock(_component_el_descriptions_cell, {
          key: `tr1-${index}`,
          cell,
          tag: "th",
          type: "label"
        }, null, 8, ["cell"]);
      }), 128))
    ]),
    createVNode("tr", null, [
      (openBlock(true), createBlock(Fragment, null, renderList(_ctx.row, (cell, index) => {
        return openBlock(), createBlock(_component_el_descriptions_cell, {
          key: `tr2-${index}`,
          cell,
          tag: "td",
          type: "content"
        }, null, 8, ["cell"]);
      }), 128))
    ])
  ], 64)) : (openBlock(), createBlock("tr", _hoisted_1, [
    (openBlock(true), createBlock(Fragment, null, renderList(_ctx.row, (cell, index) => {
      return openBlock(), createBlock(Fragment, {
        key: `tr3-${index}`
      }, [
        _ctx.descriptions.border ? (openBlock(), createBlock(Fragment, { key: 0 }, [
          createVNode(_component_el_descriptions_cell, {
            cell,
            tag: "td",
            type: "label"
          }, null, 8, ["cell"]),
          createVNode(_component_el_descriptions_cell, {
            cell,
            tag: "td",
            type: "content"
          }, null, 8, ["cell"])
        ], 64)) : (openBlock(), createBlock(_component_el_descriptions_cell, {
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

var script$1 = defineComponent({
  name: "ElDescriptions",
  components: {
    [DescriptionsItem.name]: DescriptionsItem,
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
      validator: isValidComponentSize
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
    provide(elDescriptionsKey, props);
    const $ELEMENT = useGlobalConfig();
    const descriptionsSize = computed(() => {
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
    const rows = computed(() => {
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
  const _component_el_descriptions_row = resolveComponent("el-descriptions-row");
  return openBlock(), createBlock("div", _hoisted_1$1, [
    _ctx.title || _ctx.extra || _ctx.$slots.title || _ctx.$slots.extra ? (openBlock(), createBlock("div", _hoisted_2, [
      createVNode("div", _hoisted_3, [
        renderSlot(_ctx.$slots, "title", {}, () => [
          createTextVNode(toDisplayString(_ctx.title), 1)
        ])
      ]),
      createVNode("div", _hoisted_4, [
        renderSlot(_ctx.$slots, "extra", {}, () => [
          createTextVNode(toDisplayString(_ctx.extra), 1)
        ])
      ])
    ])) : createCommentVNode("v-if", true),
    createVNode("div", _hoisted_5, [
      createVNode("table", {
        class: [{ "is-bordered": _ctx.border }, _ctx.descriptionsSize ? `el-descriptions--${_ctx.descriptionsSize}` : ""]
      }, [
        createVNode("tbody", null, [
          (openBlock(true), createBlock(Fragment, null, renderList(_ctx.rows, (row, index) => {
            return openBlock(), createBlock(_component_el_descriptions_row, {
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

export default _Descriptions;
