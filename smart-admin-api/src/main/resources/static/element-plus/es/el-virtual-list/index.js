import { isNumber, $, isString } from '../utils/util';
import throwError from '../utils/error';
import { defineComponent, ref, reactive, computed, watch, onMounted, onBeforeUnmount, getCurrentInstance, onUpdated, resolveDynamicComponent, h, nextTick } from 'vue';
import memo from 'lodash/memoize';
import isServer from '../utils/isServer';
import { cAF, rAF } from '../utils/raf';
import { BAR_MAP } from '../el-scrollbar/src/util';
import { on, off } from '../utils/dom';
import getScrollBarWidth from '../utils/scrollbar-width';

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
const hasOwnProperty = Object.prototype.hasOwnProperty;
const hasOwn = (val, key) => hasOwnProperty.call(val, key);
const isFunction = (val) => typeof val === 'function';
const isObject = (val) => val !== null && typeof val === 'object';

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
const DEFAULT_DYNAMIC_LIST_ITEM_SIZE = 50;
const ITEM_RENDER_EVT = "item-rendered";
const SCROLL_EVT = "scroll";
const FORWARD = "forward";
const BACKWARD = "backward";
const AUTO_ALIGNMENT = "auto";
const SMART_ALIGNMENT = "smart";
const START_ALIGNMENT = "start";
const CENTERED_ALIGNMENT = "center";
const END_ALIGNMENT = "end";
const HORIZONTAL = "horizontal";
const VERTICAL = "vertical";
const LTR = "ltr";
const RTL = "rtl";
const RTL_OFFSET_NAG = "negative";
const RTL_OFFSET_POS_ASC = "positive-ascending";
const RTL_OFFSET_POS_DESC = "positive-descending";
const DefaultListProps = {
  cache: {
    type: Number,
    default: 2
  },
  className: {
    type: String,
    default: ""
  },
  containerElement: {
    type: [String, Object],
    default: "div"
  },
  data: {
    type: [Array],
    default: () => []
  },
  direction: {
    type: String,
    default: "ltr",
    validator: (val) => {
      return val === LTR || val === RTL;
    }
  },
  estimatedItemSize: {
    type: [Number]
  },
  height: {
    type: [String, Number],
    required: true
  },
  layout: {
    type: String,
    default: VERTICAL
  },
  initScrollOffset: {
    type: Number,
    default: 0
  },
  innerElement: {
    type: [String, Object],
    default: "div"
  },
  total: {
    type: Number,
    required: true
  },
  itemSize: {
    type: [Number, Function],
    required: true
  },
  style: {
    type: [Object, String, Array],
    default: () => ({})
  },
  useIsScrolling: {
    type: Boolean,
    default: false
  },
  width: {
    type: [Number, String],
    required: true
  }
};
const DefaultGridProps = {
  className: DefaultListProps.className,
  columnCache: DefaultListProps.cache,
  columnWidth: DefaultListProps.itemSize,
  containerElement: DefaultListProps.containerElement,
  data: DefaultListProps.data,
  direction: DefaultListProps.direction,
  estimatedColumnWidth: DefaultListProps.estimatedItemSize,
  estimatedRowHeight: DefaultListProps.estimatedItemSize,
  height: __spreadProps(__spreadValues({}, DefaultListProps.height), {
    validator: (val) => isNumber(val)
  }),
  initScrollLeft: DefaultListProps.initScrollOffset,
  initScrollTop: DefaultListProps.initScrollOffset,
  innerElement: DefaultListProps.innerElement,
  rowCache: DefaultListProps.cache,
  rowHeight: DefaultListProps.itemSize,
  style: DefaultListProps.style,
  useIsScrolling: DefaultListProps.useIsScrolling,
  width: __spreadProps(__spreadValues({}, DefaultListProps.width), {
    validator: (val) => {
      return isNumber(val);
    }
  }),
  totalColumn: DefaultListProps.total,
  totalRow: DefaultListProps.total
};
const DefaultScrollBarProps = {
  layout: DefaultListProps.layout,
  total: Number,
  ratio: Number,
  clientSize: Number,
  scrollFrom: Number,
  visible: Boolean
};
const ScrollbarSizeKey = {
  [HORIZONTAL]: "height",
  [VERTICAL]: "width"
};
const ScrollbarDirKey = {
  [HORIZONTAL]: "left",
  [VERTICAL]: "top"
};
const SCROLLBAR_MIN_SIZE = 20;

const getScrollDir = (prev, cur) => prev < cur ? FORWARD : BACKWARD;
const isHorizontal = (dir) => dir === LTR || dir === RTL || dir === HORIZONTAL;
const isRTL = (dir) => dir === RTL;
let cachedRTLResult = null;
function getRTLOffsetType(recalculate = false) {
  if (cachedRTLResult === null || recalculate) {
    const outerDiv = document.createElement("div");
    const outerStyle = outerDiv.style;
    outerStyle.width = "50px";
    outerStyle.height = "50px";
    outerStyle.overflow = "scroll";
    outerStyle.direction = "rtl";
    const innerDiv = document.createElement("div");
    const innerStyle = innerDiv.style;
    innerStyle.width = "100px";
    innerStyle.height = "100px";
    outerDiv.appendChild(innerDiv);
    document.body.appendChild(outerDiv);
    if (outerDiv.scrollLeft > 0) {
      cachedRTLResult = RTL_OFFSET_POS_DESC;
    } else {
      outerDiv.scrollLeft = 1;
      if (outerDiv.scrollLeft === 0) {
        cachedRTLResult = RTL_OFFSET_NAG;
      } else {
        cachedRTLResult = RTL_OFFSET_POS_ASC;
      }
    }
    document.body.removeChild(outerDiv);
    return cachedRTLResult;
  }
  return cachedRTLResult;
}
function renderThumbStyle({ move, size, bar }, layout) {
  const style = {};
  const translate = `translate${bar.axis}(${move}px)`;
  style[bar.size] = size;
  style.transform = translate;
  style.msTransform = translate;
  style.webkitTransform = translate;
  if (layout === "horizontal") {
    style.height = "100%";
  } else {
    style.width = "100%";
  }
  return style;
}
const isFF = typeof navigator !== "undefined" && isObject(navigator) && /Firefox/i.test(navigator.userAgent);

const LayoutKeys = {
  [HORIZONTAL]: "deltaX",
  [VERTICAL]: "deltaY"
};
const useWheel = ({
  atEndEdge,
  atStartEdge,
  layout
}, onWheelDelta) => {
  let frameHandle = null;
  let offset = 0;
  const hasReachedEdge = (offset2) => {
    const edgeReached = offset2 < 0 && atStartEdge.value || offset2 > 0 && atEndEdge.value;
    return edgeReached;
  };
  const onWheel = (e) => {
    cAF(frameHandle);
    const newOffset = e[LayoutKeys[layout.value]];
    if (hasReachedEdge(offset) && hasReachedEdge(offset + newOffset))
      return;
    offset += newOffset;
    if (!isFF) {
      e.preventDefault();
    }
    frameHandle = rAF(() => {
      onWheelDelta(offset);
      offset = 0;
    });
  };
  return {
    hasReachedEdge,
    onWheel
  };
};

const ScrollBar = defineComponent({
  name: "ElVirtualScrollBar",
  props: DefaultScrollBarProps,
  emits: ["scroll", "start-move", "stop-move"],
  setup(props, { emit }) {
    const trackRef = ref(null);
    const thumbRef = ref(null);
    let frameHandle = null;
    let onselectstartStore = null;
    const state = reactive({
      isDragging: false,
      traveled: 0
    });
    const bar = computed(() => BAR_MAP[props.layout]);
    const trackStyle = computed(() => ({
      display: props.visible ? null : "none",
      position: "absolute",
      [ScrollbarSizeKey[props.layout]]: "6px",
      [ScrollbarDirKey[props.layout]]: "2px",
      right: "2px",
      bottom: "2px",
      height: "100%",
      borderRadius: "4px"
    }));
    const thumbSize = computed(() => {
      if (props.ratio >= 100) {
        return Number.POSITIVE_INFINITY;
      }
      if (props.ratio >= 50) {
        return props.ratio * props.clientSize / 100;
      }
      const SCROLLBAR_MAX_SIZE = props.clientSize / 3;
      return Math.floor(Math.min(Math.max(props.ratio * props.clientSize, SCROLLBAR_MIN_SIZE), SCROLLBAR_MAX_SIZE));
    });
    const thumbStyle = computed(() => {
      if (!Number.isFinite(thumbSize.value)) {
        return {
          display: "none"
        };
      }
      const thumb = `${thumbSize.value}px`;
      const style = renderThumbStyle({
        bar: bar.value,
        size: thumb,
        move: state.traveled
      }, props.layout);
      return style;
    });
    const totalSteps = computed(() => Math.floor(props.clientSize - thumbSize.value - 4));
    const attachEvents = () => {
      on(window, "mousemove", onMouseMove);
      on(window, "mouseup", onMouseUp);
      const thumbEl = thumbRef.value;
      onselectstartStore = document.onselectstart;
      document.onselectstart = () => false;
      on(thumbEl, "touchmove", onMouseMove);
      on(thumbEl, "touchend", onMouseUp);
    };
    const detachEvents = () => {
      off(window, "mousemove", onMouseMove);
      off(window, "mouseup", onMouseUp);
      document.onselectstart = onselectstartStore;
      onselectstartStore = null;
      const thumbEl = thumbRef.value;
      off(thumbEl, "touchmove", onMouseMove);
      off(thumbEl, "touchend", onMouseUp);
    };
    const onThumbMouseDown = (e) => {
      e.stopImmediatePropagation();
      if (e.ctrlKey || [1, 2].includes(e.button)) {
        return;
      }
      state.isDragging = true;
      state[bar.value.axis] = e.currentTarget[bar.value.offset] - (e[bar.value.client] - e.currentTarget.getBoundingClientRect()[bar.value.direction]);
      emit("start-move");
      attachEvents();
    };
    const onMouseUp = () => {
      state.isDragging = false;
      state[bar.value.axis] = 0;
      emit("stop-move");
      detachEvents();
    };
    const onMouseMove = (e) => {
      const { isDragging } = state;
      if (!isDragging)
        return;
      const prevPage = state[bar.value.axis];
      if (!prevPage)
        return;
      cAF(frameHandle);
      const offset = (trackRef.value.getBoundingClientRect()[bar.value.direction] - e[bar.value.client]) * -1;
      const thumbClickPosition = thumbRef.value[bar.value.offset] - prevPage;
      const distance = offset - thumbClickPosition;
      frameHandle = rAF(() => {
        state.traveled = Math.max(2, Math.min(distance, totalSteps.value));
        emit("scroll", distance, totalSteps.value);
      });
    };
    const onScrollbarTouchStart = (e) => e.preventDefault();
    watch(() => props.scrollFrom, (v) => {
      if (state.isDragging)
        return;
      state.traveled = Math.ceil(v * props.clientSize / (props.clientSize / totalSteps.value));
    });
    onMounted(() => {
      if (isServer)
        return;
      on(trackRef.value, "touchstart", onScrollbarTouchStart);
      on(thumbRef.value, "touchstart", onThumbMouseDown);
    });
    onBeforeUnmount(() => {
      off(trackRef.value, "touchstart", onScrollbarTouchStart);
      detachEvents();
    });
    return {
      state,
      trackRef,
      trackStyle,
      thumbRef,
      thumbStyle,
      onThumbMouseDown,
      onMouseUp
    };
  },
  template: `
    <div
      role="presentation"
      ref="trackRef"
      class="el-virtual-scrollbar"
      :style="trackStyle"
      @mousedown.stop.prevent=""
    >
      <div
        ref="thumbRef"
        class="el-scrollbar__thumb"
        :style="thumbStyle"
        @mousedown="onThumbMouseDown"
      >
      </div>
    </div>
  `
});

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
const createList = ({
  name,
  getOffset,
  getItemSize,
  getItemOffset,
  getEstimatedTotalSize,
  getStartIndexForOffset,
  getStopIndexForStartIndex,
  initCache,
  clearCache,
  validateProps
}) => {
  return defineComponent({
    name: name != null ? name : "ElVirtualList",
    props: DefaultListProps,
    emits: [ITEM_RENDER_EVT, SCROLL_EVT],
    setup(props, { emit, expose }) {
      validateProps(props);
      const instance = getCurrentInstance();
      const dynamicSizeCache = ref(initCache(props, instance));
      const windowRef = ref(null);
      const innerRef = ref(null);
      const scrollbarRef = ref(null);
      const states = ref({
        isScrolling: false,
        scrollDir: "forward",
        scrollOffset: isNumber(props.initScrollOffset) ? props.initScrollOffset : 0,
        updateRequested: false,
        isScrollbarDragging: false
      });
      const itemsToRender = computed(() => {
        const { total, cache } = props;
        const { isScrolling, scrollDir, scrollOffset } = $(states);
        if (total === 0) {
          return [0, 0, 0, 0];
        }
        const startIndex = getStartIndexForOffset(props, scrollOffset, $(dynamicSizeCache));
        const stopIndex = getStopIndexForStartIndex(props, startIndex, scrollOffset, $(dynamicSizeCache));
        const cacheBackward = !isScrolling || scrollDir === BACKWARD ? Math.max(1, cache) : 1;
        const cacheForward = !isScrolling || scrollDir === FORWARD ? Math.max(1, cache) : 1;
        return [
          Math.max(0, startIndex - cacheBackward),
          Math.max(0, Math.min(total - 1, stopIndex + cacheForward)),
          startIndex,
          stopIndex
        ];
      });
      const estimatedTotalSize = computed(() => getEstimatedTotalSize(props, $(dynamicSizeCache)));
      const _isHorizontal = computed(() => isHorizontal(props.layout));
      const windowStyle = computed(() => [
        {
          position: "relative",
          overflow: "hidden",
          WebkitOverflowScrolling: "touch",
          willChange: "transform"
        },
        __spreadValues$1({
          direction: props.direction,
          height: isNumber(props.height) ? `${props.height}px` : props.height,
          width: isNumber(props.width) ? `${props.width}px` : props.width
        }, props.style)
      ]);
      const innerStyle = computed(() => {
        const size = $(estimatedTotalSize);
        const horizontal = $(_isHorizontal);
        return {
          height: horizontal ? "100%" : `${size}px`,
          pointerEvents: $(states).isScrolling ? "none" : void 0,
          width: horizontal ? `${size}px` : "100%"
        };
      });
      const clientSize = computed(() => _isHorizontal.value ? props.width : props.height);
      const {
        onWheel
      } = useWheel({
        atStartEdge: computed(() => states.value.scrollOffset <= 0),
        atEndEdge: computed(() => states.value.scrollOffset >= estimatedTotalSize.value),
        layout: computed(() => props.layout)
      }, (offset) => {
        var _a, _b;
        (_b = (_a = scrollbarRef.value).onMouseUp) == null ? void 0 : _b.call(_a);
        scrollTo(Math.min(states.value.scrollOffset + offset, estimatedTotalSize.value - clientSize.value));
      });
      const emitEvents = () => {
        const { total } = props;
        if (total > 0) {
          const [cacheStart, cacheEnd, visibleStart, visibleEnd] = $(itemsToRender);
          emit(ITEM_RENDER_EVT, cacheStart, cacheEnd, visibleStart, visibleEnd);
        }
        const { scrollDir, scrollOffset, updateRequested } = $(states);
        emit(SCROLL_EVT, scrollDir, scrollOffset, updateRequested);
      };
      const scrollVertically = (e) => {
        const { clientHeight, scrollHeight, scrollTop } = e.currentTarget;
        const _states = $(states);
        if (_states.scrollOffset === scrollTop) {
          return;
        }
        const scrollOffset = Math.max(0, Math.min(scrollTop, scrollHeight - clientHeight));
        states.value = __spreadProps$1(__spreadValues$1({}, _states), {
          isScrolling: true,
          scrollDir: getScrollDir(_states.scrollOffset, scrollOffset),
          scrollOffset,
          updateRequested: false
        });
        nextTick(resetIsScrolling);
      };
      const scrollHorizontally = (e) => {
        const { clientWidth, scrollLeft, scrollWidth } = e.currentTarget;
        const _states = $(states);
        if (_states.scrollOffset === scrollLeft) {
          return;
        }
        const { direction } = props;
        let scrollOffset = scrollLeft;
        if (direction === RTL) {
          switch (getRTLOffsetType()) {
            case RTL_OFFSET_NAG: {
              scrollOffset = -scrollLeft;
              break;
            }
            case RTL_OFFSET_POS_DESC: {
              scrollOffset = scrollWidth - clientWidth - scrollLeft;
              break;
            }
          }
        }
        scrollOffset = Math.max(0, Math.min(scrollOffset, scrollWidth - clientWidth));
        states.value = __spreadProps$1(__spreadValues$1({}, _states), {
          isScrolling: true,
          scrollDir: getScrollDir(_states.scrollOffset, scrollOffset),
          scrollOffset,
          updateRequested: false
        });
        nextTick(resetIsScrolling);
      };
      const onScroll = (e) => {
        $(_isHorizontal) ? scrollHorizontally(e) : scrollVertically(e);
        emitEvents();
      };
      const onScrollbarScroll = (distanceToGo, totalSteps) => {
        const offset = (estimatedTotalSize.value - clientSize.value) / totalSteps * distanceToGo;
        scrollTo(Math.min(estimatedTotalSize.value - clientSize.value, offset));
      };
      const getItemStyleCache = memo((_, __, ___) => ({}));
      const scrollTo = (offset) => {
        offset = Math.max(offset, 0);
        if (offset === $(states).scrollOffset) {
          return;
        }
        states.value = __spreadProps$1(__spreadValues$1({}, $(states)), {
          scrollOffset: offset,
          scrollDir: getScrollDir($(states).scrollOffset, offset),
          updateRequested: true
        });
        nextTick(resetIsScrolling);
      };
      const scrollToItem = (idx, alignment = AUTO_ALIGNMENT) => {
        const { scrollOffset } = $(states);
        idx = Math.max(0, Math.min(idx, props.total - 1));
        scrollTo(getOffset(props, idx, alignment, scrollOffset, $(dynamicSizeCache)));
      };
      const getItemStyle = (idx) => {
        const { direction, itemSize, layout } = props;
        const itemStyleCache = getItemStyleCache(clearCache && itemSize, clearCache && layout, clearCache && direction);
        let style;
        if (hasOwn(itemStyleCache, String(idx))) {
          style = itemStyleCache[idx];
        } else {
          const offset = getItemOffset(props, idx, $(dynamicSizeCache));
          const size = getItemSize(props, idx, $(dynamicSizeCache));
          const horizontal = $(_isHorizontal);
          const isRtl = direction === RTL;
          const offsetHorizontal = horizontal ? offset : 0;
          itemStyleCache[idx] = style = {
            position: "absolute",
            left: isRtl ? void 0 : `${offsetHorizontal}px`,
            right: isRtl ? `${offsetHorizontal}px` : void 0,
            top: !horizontal ? `${offset}px` : 0,
            height: !horizontal ? `${size}px` : "100%",
            width: horizontal ? `${size}px` : "100%"
          };
        }
        return style;
      };
      const resetIsScrolling = () => {
        states.value.isScrolling = false;
        nextTick(() => {
          getItemStyleCache(-1, null, null);
        });
      };
      onMounted(() => {
        if (isServer)
          return;
        const { initScrollOffset } = props;
        const windowElement = $(windowRef);
        if (isNumber(initScrollOffset) && windowElement !== null) {
          if ($(_isHorizontal)) {
            windowElement.scrollLeft = initScrollOffset;
          } else {
            windowElement.scrollTop = initScrollOffset;
          }
        }
        emitEvents();
      });
      onUpdated(() => {
        const { direction, layout } = props;
        const { scrollOffset, updateRequested } = $(states);
        if (updateRequested && $(windowRef) !== null) {
          const windowElement = $(windowRef);
          if (layout === HORIZONTAL) {
            if (direction === RTL) {
              switch (getRTLOffsetType()) {
                case "negative": {
                  windowElement.scrollLeft = -scrollOffset;
                  break;
                }
                case "positive-ascending": {
                  windowElement.scrollLeft = scrollOffset;
                  break;
                }
                default: {
                  const { clientWidth, scrollWidth } = windowElement;
                  windowElement.scrollLeft = scrollWidth - clientWidth - scrollOffset;
                  break;
                }
              }
            } else {
              windowElement.scrollLeft = scrollOffset;
            }
          } else {
            windowElement.scrollTop = scrollOffset;
          }
        }
      });
      const api = {
        clientSize,
        estimatedTotalSize,
        windowStyle,
        windowRef,
        innerRef,
        innerStyle,
        itemsToRender,
        scrollbarRef,
        states,
        getItemStyle,
        onScroll,
        onScrollbarScroll,
        onWheel,
        scrollTo,
        scrollToItem
      };
      expose({
        windowRef,
        innerRef,
        getItemStyleCache,
        scrollTo,
        scrollToItem,
        states
      });
      return api;
    },
    render(ctx) {
      var _a;
      const {
        $slots,
        className,
        clientSize,
        containerElement,
        data,
        getItemStyle,
        innerElement,
        itemsToRender,
        innerStyle,
        layout,
        total,
        onScroll,
        onScrollbarScroll,
        onWheel,
        states,
        useIsScrolling,
        windowStyle
      } = ctx;
      const [start, end] = itemsToRender;
      const Container = resolveDynamicComponent(containerElement);
      const Inner = resolveDynamicComponent(innerElement);
      const children = [];
      if (total > 0) {
        for (let i = start; i <= end; i++) {
          children.push((_a = $slots.default) == null ? void 0 : _a.call($slots, {
            data,
            key: i,
            index: i,
            isScrolling: useIsScrolling ? states.isScrolling : void 0,
            style: getItemStyle(i)
          }));
        }
      }
      const InnerNode = [h(Inner, {
        style: innerStyle,
        ref: "innerRef"
      }, !isString(Inner) ? {
        default: () => children
      } : children)];
      const scrollbar = h(ScrollBar, {
        ref: "scrollbarRef",
        clientSize,
        layout,
        onScroll: onScrollbarScroll,
        ratio: clientSize * 100 / this.estimatedTotalSize,
        scrollFrom: states.scrollOffset / (this.estimatedTotalSize - clientSize),
        total,
        visible: true
      });
      const listContainer = h(Container, {
        class: className,
        style: windowStyle,
        onScroll,
        onWheel,
        ref: "windowRef",
        key: 0
      }, !isString(Container) ? { default: () => [InnerNode] } : [InnerNode]);
      return h("div", {
        key: 0,
        class: "el-vl__wrapper"
      }, [
        listContainer,
        scrollbar
      ]);
    }
  });
};

const FixedSizeList = createList({
  name: "ElFixedSizeList",
  getItemOffset: ({ itemSize }, index) => index * itemSize,
  getItemSize: ({ itemSize }) => itemSize,
  getEstimatedTotalSize: ({ total, itemSize }) => itemSize * total,
  getOffset: ({ height, total, itemSize, layout, width }, index, alignment, scrollOffset) => {
    const size = isHorizontal(layout) ? width : height;
    if (process.env.ENV !== "production" && isString(size)) {
      throwError("[ElVirtualList]", `
        You should set
          width/height
        to number when your layout is
          horizontal/vertical
      `);
    }
    const lastItemOffset = Math.max(0, total * itemSize - size);
    const maxOffset = Math.min(lastItemOffset, index * itemSize);
    const minOffset = Math.max(0, (index + 1) * itemSize - size);
    if (alignment === SMART_ALIGNMENT) {
      if (scrollOffset >= minOffset - size && scrollOffset <= maxOffset + size) {
        alignment = AUTO_ALIGNMENT;
      } else {
        alignment = CENTERED_ALIGNMENT;
      }
    }
    switch (alignment) {
      case START_ALIGNMENT: {
        return maxOffset;
      }
      case END_ALIGNMENT: {
        return minOffset;
      }
      case CENTERED_ALIGNMENT: {
        const middleOffset = Math.round(minOffset + (maxOffset - minOffset) / 2);
        if (middleOffset < Math.ceil(size / 2)) {
          return 0;
        } else if (middleOffset > lastItemOffset + Math.floor(size / 2)) {
          return lastItemOffset;
        } else {
          return middleOffset;
        }
      }
      case AUTO_ALIGNMENT:
      default: {
        if (scrollOffset >= minOffset && scrollOffset <= maxOffset) {
          return scrollOffset;
        } else if (scrollOffset < minOffset) {
          return minOffset;
        } else {
          return maxOffset;
        }
      }
    }
  },
  getStartIndexForOffset: ({ total, itemSize }, offset) => Math.max(0, Math.min(total - 1, Math.floor(offset / itemSize))),
  getStopIndexForStartIndex: ({ height, total, itemSize, layout, width }, startIndex, scrollOffset) => {
    const offset = startIndex * itemSize;
    const size = isHorizontal(layout) ? width : height;
    const numVisibleItems = Math.ceil((size + scrollOffset - offset) / itemSize);
    return Math.max(0, Math.min(total - 1, startIndex + numVisibleItems - 1));
  },
  initCache() {
    return void 0;
  },
  clearCache: true,
  validateProps() {
  }
});

const SCOPE = "ElDynamicSizeList";
const getItemFromCache = (props, index, listCache) => {
  const { itemSize } = props;
  const { items, lastVisitedIndex } = listCache;
  if (index > lastVisitedIndex) {
    let offset = 0;
    if (lastVisitedIndex >= 0) {
      const item = items[lastVisitedIndex];
      offset = item.offset + item.size;
    }
    for (let i = lastVisitedIndex + 1; i <= index; i++) {
      const size = itemSize(i);
      items[i] = {
        offset,
        size
      };
      offset += size;
    }
    listCache.lastVisitedIndex = index;
  }
  return items[index];
};
const findItem = (props, listCache, offset) => {
  const { items, lastVisitedIndex } = listCache;
  const lastVisitedOffset = lastVisitedIndex > 0 ? items[lastVisitedIndex].offset : 0;
  if (lastVisitedOffset >= offset) {
    return bs(props, listCache, 0, lastVisitedIndex, offset);
  }
  return es(props, listCache, Math.max(0, lastVisitedIndex), offset);
};
const bs = (props, listCache, low, high, offset) => {
  while (low <= high) {
    const mid = low + Math.floor((high - low) / 2);
    const currentOffset = getItemFromCache(props, mid, listCache).offset;
    if (currentOffset === offset) {
      return mid;
    } else if (currentOffset < offset) {
      low = mid + 1;
    } else if (currentOffset > offset) {
      high = mid - 1;
    }
  }
  return Math.max(0, low - 1);
};
const es = (props, listCache, index, offset) => {
  const { total } = props;
  let exponent = 1;
  while (index < total && getItemFromCache(props, index, listCache).offset < offset) {
    index += exponent;
    exponent *= 2;
  }
  return bs(props, listCache, Math.floor(index / 2), Math.min(index, total - 1), offset);
};
const getEstimatedTotalSize = ({ total }, { items, estimatedItemSize, lastVisitedIndex }) => {
  let totalSizeOfMeasuredItems = 0;
  if (lastVisitedIndex >= total) {
    lastVisitedIndex = total - 1;
  }
  if (lastVisitedIndex >= 0) {
    const item = items[lastVisitedIndex];
    totalSizeOfMeasuredItems = item.offset + item.size;
  }
  const numUnmeasuredItems = total - lastVisitedIndex - 1;
  const totalSizeOfUnmeasuredItems = numUnmeasuredItems * estimatedItemSize;
  return totalSizeOfMeasuredItems + totalSizeOfUnmeasuredItems;
};
const DynamicSizeList = createList({
  name: "ElDynamicSizeList",
  getItemOffset: (props, index, listCache) => getItemFromCache(props, index, listCache).offset,
  getItemSize: (_, index, { items }) => items[index].size,
  getEstimatedTotalSize,
  getOffset: (props, index, alignment, scrollOffset, listCache) => {
    const { height, layout, width } = props;
    const size = isHorizontal(layout) ? width : height;
    const item = getItemFromCache(props, index, listCache);
    const estimatedTotalSize = getEstimatedTotalSize(props, listCache);
    const maxOffset = Math.max(0, Math.min(estimatedTotalSize - size, item.offset));
    const minOffset = Math.max(0, item.offset - size + item.size);
    if (alignment === SMART_ALIGNMENT) {
      if (scrollOffset >= minOffset - size && scrollOffset <= maxOffset + size) {
        alignment = AUTO_ALIGNMENT;
      } else {
        alignment = CENTERED_ALIGNMENT;
      }
    }
    switch (alignment) {
      case START_ALIGNMENT: {
        return maxOffset;
      }
      case END_ALIGNMENT: {
        return minOffset;
      }
      case CENTERED_ALIGNMENT: {
        return Math.round(minOffset + (maxOffset - minOffset) / 2);
      }
      case AUTO_ALIGNMENT:
      default: {
        if (scrollOffset >= minOffset && scrollOffset <= maxOffset) {
          return scrollOffset;
        } else if (scrollOffset < minOffset) {
          return minOffset;
        } else {
          return maxOffset;
        }
      }
    }
  },
  getStartIndexForOffset: (props, offset, listCache) => findItem(props, listCache, offset),
  getStopIndexForStartIndex: (props, startIndex, scrollOffset, listCache) => {
    const { height, total, layout, width } = props;
    const size = isHorizontal(layout) ? width : height;
    const item = getItemFromCache(props, startIndex, listCache);
    const maxOffset = scrollOffset + size;
    let offset = item.offset + item.size;
    let stopIndex = startIndex;
    while (stopIndex < total - 1 && offset < maxOffset) {
      stopIndex++;
      offset += getItemFromCache(props, stopIndex, listCache).size;
    }
    return stopIndex;
  },
  initCache({ estimatedItemSize = DEFAULT_DYNAMIC_LIST_ITEM_SIZE }, instance) {
    const cache = {
      items: {},
      estimatedItemSize,
      lastVisitedIndex: -1
    };
    cache.clearCacheAfterIndex = (index, forceUpdate = true) => {
      cache.lastVisitedIndex = Math.min(cache.lastVisitedIndex, index - 1);
      instance.exposed.getItemStyleCache(-1);
      if (forceUpdate) {
        instance.proxy.$forceUpdate();
      }
    };
    return cache;
  },
  clearCache: false,
  validateProps: ({ itemSize }) => {
    if (process.env.NODE_ENV !== "production") {
      if (typeof itemSize !== "function") {
        throwError(SCOPE, `
          itemSize is required as function, but the given value was ${typeof itemSize}
        `);
      }
    }
  }
});

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
const createGrid = ({
  name,
  clearCache,
  getColumnPosition,
  getColumnStartIndexForOffset,
  getColumnStopIndexForStartIndex,
  getEstimatedTotalHeight,
  getEstimatedTotalWidth,
  getColumnOffset,
  getRowOffset,
  getRowPosition,
  getRowStartIndexForOffset,
  getRowStopIndexForStartIndex,
  initCache,
  validateProps
}) => {
  return defineComponent({
    name: name != null ? name : "ElVirtualList",
    props: DefaultGridProps,
    emits: [ITEM_RENDER_EVT, SCROLL_EVT],
    setup(props, { emit, expose }) {
      validateProps(props);
      const instance = getCurrentInstance();
      const cache = ref(initCache(props, instance));
      const windowRef = ref(null);
      const innerRef = ref(null);
      const states = ref({
        isScrolling: false,
        scrollLeft: isNumber(props.initScrollLeft) ? props.initScrollLeft : 0,
        scrollTop: isNumber(props.initScrollTop) ? props.initScrollTop : 0,
        updateRequested: false,
        xAxisScrollDir: FORWARD,
        yAxisScrollDir: FORWARD
      });
      const columnsToRender = computed(() => {
        const { totalColumn, totalRow, columnCache } = props;
        const { isScrolling, xAxisScrollDir, scrollLeft } = $(states);
        if (totalColumn === 0 || totalRow === 0) {
          return [0, 0, 0, 0];
        }
        const startIndex = getColumnStartIndexForOffset(props, scrollLeft, $(cache));
        const stopIndex = getColumnStopIndexForStartIndex(props, startIndex, scrollLeft, $(cache));
        const cacheBackward = !isScrolling || xAxisScrollDir === BACKWARD ? Math.max(1, columnCache) : 1;
        const cacheForward = !isScrolling || xAxisScrollDir === FORWARD ? Math.max(1, columnCache) : 1;
        return [
          Math.max(0, startIndex - cacheBackward),
          Math.max(0, Math.min(totalColumn - 1, stopIndex + cacheForward)),
          startIndex,
          stopIndex
        ];
      });
      const rowsToRender = computed(() => {
        const { totalColumn, totalRow, rowCache } = props;
        const { isScrolling, yAxisScrollDir, scrollTop } = $(states);
        if (totalColumn === 0 || totalRow === 0) {
          return [0, 0, 0, 0];
        }
        const startIndex = getRowStartIndexForOffset(props, scrollTop, $(cache));
        const stopIndex = getRowStopIndexForStartIndex(props, startIndex, scrollTop, $(cache));
        const cacheBackward = !isScrolling || yAxisScrollDir === BACKWARD ? Math.max(1, rowCache) : 1;
        const cacheForward = !isScrolling || yAxisScrollDir === FORWARD ? Math.max(1, rowCache) : 1;
        return [
          Math.max(0, startIndex - cacheBackward),
          Math.max(0, Math.min(totalRow - 1, stopIndex + cacheForward)),
          startIndex,
          stopIndex
        ];
      });
      const estimatedTotalHeight = computed(() => getEstimatedTotalHeight(props, $(cache)));
      const estimatedTotalWidth = computed(() => getEstimatedTotalWidth(props, $(cache)));
      const windowStyle = computed(() => [
        {
          position: "relative",
          overflow: "auto",
          WebkitOverflowScrolling: "touch",
          willChange: "transform"
        },
        __spreadValues$2({
          direction: props.direction,
          height: isNumber(props.height) ? `${props.height}px` : props.height,
          width: isNumber(props.width) ? `${props.width}px` : props.width
        }, props.style)
      ]);
      const innerStyle = computed(() => {
        const width = `${$(estimatedTotalWidth)}px`;
        const height = `${$(estimatedTotalHeight)}px`;
        return {
          height,
          pointerEvents: $(states).isScrolling ? "none" : void 0,
          width
        };
      });
      const emitEvents = () => {
        const { totalColumn, totalRow } = props;
        if (totalColumn > 0 && totalRow > 0) {
          const [columnCacheStart, columnCacheEnd, columnVisibleStart, columnVisibleEnd] = $(columnsToRender);
          const [rowCacheStart, rowCacheEnd, rowVisibleStart, rowVisibleEnd] = $(rowsToRender);
          emit(ITEM_RENDER_EVT, columnCacheStart, columnCacheEnd, rowCacheStart, rowCacheEnd, columnVisibleStart, columnVisibleEnd, rowVisibleStart, rowVisibleEnd);
        }
        const { scrollLeft, scrollTop, updateRequested, xAxisScrollDir, yAxisScrollDir } = $(states);
        emit(SCROLL_EVT, xAxisScrollDir, scrollLeft, yAxisScrollDir, scrollTop, updateRequested);
      };
      const onScroll = (e) => {
        const {
          clientHeight,
          clientWidth,
          scrollHeight,
          scrollLeft,
          scrollTop,
          scrollWidth
        } = e.currentTarget;
        const _states = $(states);
        if (_states.scrollTop === scrollTop && _states.scrollLeft === scrollLeft) {
          return;
        }
        let _scrollLeft = scrollLeft;
        if (isRTL(props.direction)) {
          switch (getRTLOffsetType()) {
            case RTL_OFFSET_NAG:
              _scrollLeft = -scrollLeft;
              break;
            case RTL_OFFSET_POS_DESC:
              _scrollLeft = scrollWidth - clientWidth - scrollLeft;
              break;
          }
        }
        states.value = __spreadProps$2(__spreadValues$2({}, _states), {
          isScrolling: true,
          scrollLeft: _scrollLeft,
          scrollTop: Math.max(0, Math.min(scrollTop, scrollHeight - clientHeight)),
          updateRequested: false,
          xAxisScrollDir: getScrollDir(_states.scrollLeft, _scrollLeft),
          yAxisScrollDir: getScrollDir(_states.scrollTop, scrollTop)
        });
        nextTick(resetIsScrolling);
        emitEvents();
      };
      const getItemStyleCache = memo((_, __, ___) => ({}));
      const scrollTo = ({
        scrollLeft,
        scrollTop
      }) => {
        scrollLeft = Math.max(scrollLeft, 0);
        scrollTop = Math.max(scrollTop, 0);
        const _states = $(states);
        if (scrollTop === _states.scrollTop && scrollLeft === _states.scrollLeft) {
          return;
        }
        states.value = __spreadProps$2(__spreadValues$2({}, _states), {
          xAxisScrollDir: getScrollDir(_states.scrollLeft, scrollLeft),
          yAxisScrollDir: getScrollDir(_states.scrollTop, scrollTop),
          scrollLeft,
          scrollTop,
          updateRequested: true
        });
        nextTick(resetIsScrolling);
      };
      const scrollToItem = (rowIndex = 0, columnIdx = 0, alignment = AUTO_ALIGNMENT) => {
        const _states = $(states);
        columnIdx = Math.max(0, Math.min(columnIdx, props.totalColumn - 1));
        rowIndex = Math.max(0, Math.min(rowIndex, props.totalRow - 1));
        const scrollBarWidth = getScrollBarWidth();
        const _cache = $(cache);
        const estimatedHeight = getEstimatedTotalHeight(props, _cache);
        const estimatedWidth = getEstimatedTotalWidth(props, _cache);
        scrollTo({
          scrollLeft: getColumnOffset(props, columnIdx, alignment, _states.scrollLeft, _cache, estimatedWidth > props.width ? scrollBarWidth : 0),
          scrollTop: getRowOffset(props, rowIndex, alignment, _states.scrollTop, _cache, estimatedHeight > props.height ? scrollBarWidth : 0)
        });
      };
      const getItemStyle = (rowIndex, columnIndex) => {
        const { columnWidth, direction, rowHeight } = props;
        const itemStyleCache = getItemStyleCache(clearCache && columnWidth, clearCache && rowHeight, clearCache && direction);
        const key = `${rowIndex},${columnIndex}`;
        if (hasOwn(itemStyleCache, key)) {
          return itemStyleCache[key];
        } else {
          const [, left] = getColumnPosition(props, columnIndex, $(cache));
          const _cache = $(cache);
          const rtl = isRTL(direction);
          const [height, top] = getRowPosition(props, rowIndex, _cache);
          const [width] = getColumnPosition(props, columnIndex, _cache);
          itemStyleCache[key] = {
            position: "absolute",
            left: rtl ? void 0 : `${left}px`,
            right: rtl ? `${left}px` : void 0,
            top: `${top}px`,
            height: `${height}px`,
            width: `${width}px`
          };
          return itemStyleCache[key];
        }
      };
      const resetIsScrolling = () => {
        states.value.isScrolling = false;
        nextTick(() => {
          getItemStyleCache(-1, null, null);
        });
      };
      onMounted(() => {
        if (isServer)
          return;
        const { initScrollLeft, initScrollTop } = props;
        const windowElement = $(windowRef);
        if (windowElement !== null) {
          if (isNumber(initScrollLeft)) {
            windowElement.scrollLeft = initScrollLeft;
          }
          if (isNumber(initScrollTop)) {
            windowElement.scrollTop = initScrollTop;
          }
        }
        emitEvents();
      });
      onUpdated(() => {
        const { direction } = props;
        const { scrollLeft, scrollTop, updateRequested } = $(states);
        if (updateRequested && $(windowRef) !== null) {
          const windowElement = $(windowRef);
          if (direction === RTL) {
            switch (getRTLOffsetType()) {
              case RTL_OFFSET_NAG: {
                windowElement.scrollLeft = -scrollLeft;
                break;
              }
              case RTL_OFFSET_POS_ASC: {
                windowElement.scrollLeft = scrollLeft;
                break;
              }
              default: {
                const { clientWidth, scrollWidth } = windowElement;
                windowElement.scrollLeft = scrollWidth - clientWidth - scrollLeft;
                break;
              }
            }
          } else {
            windowElement.scrollLeft = Math.max(0, scrollLeft);
          }
          windowElement.scrollTop = Math.max(0, scrollTop);
        }
      });
      const api = {
        windowStyle,
        windowRef,
        columnsToRender,
        innerRef,
        innerStyle,
        states,
        rowsToRender,
        getItemStyle,
        onScroll,
        scrollTo,
        scrollToItem
      };
      expose({
        windowRef,
        innerRef,
        getItemStyleCache,
        scrollTo,
        scrollToItem,
        states
      });
      return api;
    },
    render(ctx) {
      var _a;
      const {
        $slots,
        className,
        containerElement,
        columnsToRender,
        data,
        getItemStyle,
        innerElement,
        innerStyle,
        rowsToRender,
        onScroll,
        states,
        useIsScrolling,
        windowStyle,
        totalColumn,
        totalRow
      } = ctx;
      const [columnStart, columnEnd] = columnsToRender;
      const [rowStart, rowEnd] = rowsToRender;
      const Container = resolveDynamicComponent(containerElement);
      const Inner = resolveDynamicComponent(innerElement);
      const children = [];
      if (totalRow > 0 && totalColumn > 0) {
        for (let row = rowStart; row <= rowEnd; row++) {
          for (let column = columnStart; column <= columnEnd; column++) {
            children.push((_a = $slots.default) == null ? void 0 : _a.call($slots, {
              columnIndex: column,
              data,
              key: column,
              isScrolling: useIsScrolling ? states.isScrolling : void 0,
              style: getItemStyle(row, column),
              rowIndex: row
            }));
          }
        }
      }
      const InnerNode = [h(Inner, {
        style: innerStyle,
        ref: "innerRef"
      }, !isString(Inner) ? {
        default: () => children
      } : children)];
      return h(Container, {
        class: className,
        style: windowStyle,
        onScroll,
        ref: "windowRef"
      }, !isString(Container) ? { default: () => InnerNode } : InnerNode);
    }
  });
};

const SCOPE$1 = "ElFixedSizeGrid";
const FixedSizeGrid = createGrid({
  name: "ElFixedSizeGrid",
  getColumnPosition: ({ columnWidth }, index) => [
    columnWidth,
    index * columnWidth
  ],
  getRowPosition: ({ rowHeight }, index) => [
    rowHeight,
    index * rowHeight
  ],
  getEstimatedTotalHeight: ({ totalRow, rowHeight }) => rowHeight * totalRow,
  getEstimatedTotalWidth: ({ totalColumn, columnWidth }) => columnWidth * totalColumn,
  getColumnOffset: ({ totalColumn, columnWidth, width }, columnIndex, alignment, scrollLeft, _, scrollBarWidth) => {
    width = Number(width);
    const lastColumnOffset = Math.max(0, totalColumn * columnWidth - width);
    const maxOffset = Math.min(lastColumnOffset, columnIndex * columnWidth);
    const minOffset = Math.max(0, columnIndex * columnWidth - width + scrollBarWidth + columnWidth);
    if (alignment === "smart") {
      if (scrollLeft >= minOffset - width && scrollLeft <= maxOffset + width) {
        alignment = AUTO_ALIGNMENT;
      } else {
        alignment = CENTERED_ALIGNMENT;
      }
    }
    switch (alignment) {
      case START_ALIGNMENT:
        return maxOffset;
      case END_ALIGNMENT:
        return minOffset;
      case CENTERED_ALIGNMENT:
        const middleOffset = Math.round(minOffset + (maxOffset - minOffset) / 2);
        if (middleOffset < Math.ceil(width / 2)) {
          return 0;
        } else if (middleOffset > lastColumnOffset + Math.floor(width / 2)) {
          return lastColumnOffset;
        } else {
          return middleOffset;
        }
      case AUTO_ALIGNMENT:
      default:
        if (scrollLeft >= minOffset && scrollLeft <= maxOffset) {
          return scrollLeft;
        } else if (minOffset > maxOffset) {
          return minOffset;
        } else if (scrollLeft < minOffset) {
          return minOffset;
        } else {
          return maxOffset;
        }
    }
  },
  getRowOffset: ({ rowHeight, height, totalRow }, rowIndex, align, scrollTop, _, scrollBarWidth) => {
    height = Number(height);
    const lastRowOffset = Math.max(0, totalRow * rowHeight - height);
    const maxOffset = Math.min(lastRowOffset, rowIndex * rowHeight);
    const minOffset = Math.max(0, rowIndex * rowHeight - height + scrollBarWidth + rowHeight);
    if (align === SMART_ALIGNMENT) {
      if (scrollTop >= minOffset - height && scrollTop <= maxOffset + height) {
        align = AUTO_ALIGNMENT;
      } else {
        align = CENTERED_ALIGNMENT;
      }
    }
    switch (align) {
      case START_ALIGNMENT:
        return maxOffset;
      case END_ALIGNMENT:
        return minOffset;
      case CENTERED_ALIGNMENT:
        const middleOffset = Math.round(minOffset + (maxOffset - minOffset) / 2);
        if (middleOffset < Math.ceil(height / 2)) {
          return 0;
        } else if (middleOffset > lastRowOffset + Math.floor(height / 2)) {
          return lastRowOffset;
        } else {
          return middleOffset;
        }
      case AUTO_ALIGNMENT:
      default:
        if (scrollTop >= minOffset && scrollTop <= maxOffset) {
          return scrollTop;
        } else if (minOffset > maxOffset) {
          return minOffset;
        } else if (scrollTop < minOffset) {
          return minOffset;
        } else {
          return maxOffset;
        }
    }
  },
  getColumnStartIndexForOffset: ({ columnWidth, totalColumn }, scrollLeft) => Math.max(0, Math.min(totalColumn - 1, Math.floor(scrollLeft / columnWidth))),
  getColumnStopIndexForStartIndex: ({ columnWidth, totalColumn, width }, startIndex, scrollLeft) => {
    const left = startIndex * columnWidth;
    const visibleColumnsCount = Math.ceil((width + scrollLeft - left) / columnWidth);
    return Math.max(0, Math.min(totalColumn - 1, startIndex + visibleColumnsCount - 1));
  },
  getRowStartIndexForOffset: ({ rowHeight, totalRow }, scrollTop) => Math.max(0, Math.min(totalRow - 1, Math.floor(scrollTop / rowHeight))),
  getRowStopIndexForStartIndex: ({ rowHeight, totalRow, height }, startIndex, scrollTop) => {
    const top = startIndex * rowHeight;
    const numVisibleRows = Math.ceil((height + scrollTop - top) / rowHeight);
    return Math.max(0, Math.min(totalRow - 1, startIndex + numVisibleRows - 1));
  },
  initCache: () => void 0,
  clearCache: true,
  validateProps: ({ columnWidth, rowHeight }) => {
    if (process.env.NODE_ENV !== "production") {
      if (process.env.NODE_ENV !== "production") {
        if (!isNumber(columnWidth)) {
          throwError(SCOPE$1, `
            "columnWidth" must be passed as number,
              instead ${typeof columnWidth} was given.
          `);
        }
        if (!isNumber(rowHeight)) {
          throwError(SCOPE$1, `
            "columnWidth" must be passed as number,
              instead ${typeof rowHeight} was given.
          `);
        }
      }
    }
  }
});

const { max, min, floor } = Math;
const SCOPE$2 = "ElDynamicSizeGrid";
const ACCESS_SIZER_KEY_MAP = {
  column: "columnWidth",
  row: "rowHeight"
};
const ACCESS_LAST_VISITED_KEY_MAP = {
  column: "lastVisitedColumnIndex",
  row: "lastVisitedRowIndex"
};
const getItemFromCache$1 = (props, index, gridCache, type) => {
  const [cachedItems, sizer, lastVisited] = [
    gridCache[type],
    props[ACCESS_SIZER_KEY_MAP[type]],
    gridCache[ACCESS_LAST_VISITED_KEY_MAP[type]]
  ];
  if (index > lastVisited) {
    let offset = 0;
    if (lastVisited >= 0) {
      const item = cachedItems[lastVisited];
      offset = item.offset + item.size;
    }
    for (let i = lastVisited + 1; i <= index; i++) {
      const size = sizer(i);
      cachedItems[i] = {
        offset,
        size
      };
      offset += size;
    }
    gridCache[ACCESS_LAST_VISITED_KEY_MAP[type]] = index;
  }
  return cachedItems[index];
};
const bs$1 = (props, gridCache, low, high, offset, type) => {
  while (low <= high) {
    const mid = low + floor((high - low) / 2);
    const currentOffset = getItemFromCache$1(props, mid, gridCache, type).offset;
    if (currentOffset === offset) {
      return mid;
    } else if (currentOffset < offset) {
      low = mid + 1;
    } else {
      high = mid - 1;
    }
  }
  return max(0, low - 1);
};
const es$1 = (props, gridCache, idx, offset, type) => {
  const total = type === "column" ? props.totalColumn : props.totalRow;
  let exponent = 1;
  while (idx < total && getItemFromCache$1(props, idx, gridCache, type).offset < offset) {
    idx += exponent;
    exponent *= 2;
  }
  return bs$1(props, gridCache, floor(idx / 2), min(idx, total - 1), offset, type);
};
const findItem$1 = (props, gridCache, offset, type) => {
  const [cache, lastVisitedIndex] = [
    gridCache[type],
    gridCache[ACCESS_LAST_VISITED_KEY_MAP[type]]
  ];
  const lastVisitedItemOffset = lastVisitedIndex > 0 ? cache[lastVisitedIndex].offset : 0;
  if (lastVisitedItemOffset >= offset) {
    return bs$1(props, gridCache, 0, lastVisitedIndex, offset, type);
  }
  return es$1(props, gridCache, max(0, lastVisitedIndex), offset, type);
};
const getEstimatedTotalHeight = ({ totalRow }, {
  estimatedRowHeight,
  lastVisitedRowIndex,
  row
}) => {
  let sizeOfVisitedRows = 0;
  if (lastVisitedRowIndex >= totalRow) {
    lastVisitedRowIndex = totalRow - 1;
  }
  if (lastVisitedRowIndex >= 0) {
    const item = row[lastVisitedRowIndex];
    sizeOfVisitedRows = item.offset + item.size;
  }
  const unvisitedItems = totalRow - lastVisitedRowIndex - 1;
  const sizeOfUnvisitedItems = unvisitedItems * estimatedRowHeight;
  return sizeOfVisitedRows + sizeOfUnvisitedItems;
};
const getEstimatedTotalWidth = ({
  totalColumn
}, {
  column,
  estimatedColumnWidth,
  lastVisitedColumnIndex
}) => {
  let sizeOfVisitedColumns = 0;
  if (lastVisitedColumnIndex > totalColumn) {
    lastVisitedColumnIndex = totalColumn - 1;
  }
  if (lastVisitedColumnIndex >= 0) {
    const item = column[lastVisitedColumnIndex];
    sizeOfVisitedColumns = item.offset + item.size;
  }
  const unvisitedItems = totalColumn - lastVisitedColumnIndex - 1;
  const sizeOfUnvisitedItems = unvisitedItems * estimatedColumnWidth;
  return sizeOfVisitedColumns + sizeOfUnvisitedItems;
};
const ACCESS_ESTIMATED_SIZE_KEY_MAP = {
  column: getEstimatedTotalWidth,
  row: getEstimatedTotalHeight
};
const getOffset = (props, index, alignment, scrollOffset, cache, type, scrollBarWidth) => {
  const [
    size,
    estimatedSizeAssociates
  ] = [
    type === "row" ? props.height : props.width,
    ACCESS_ESTIMATED_SIZE_KEY_MAP[type]
  ];
  const item = getItemFromCache$1(props, index, cache, type);
  const estimatedSize = estimatedSizeAssociates(props, cache);
  const maxOffset = max(0, min(estimatedSize - size, item.offset));
  const minOffset = max(0, item.offset - size + scrollBarWidth + item.size);
  if (alignment === SMART_ALIGNMENT) {
    if (scrollOffset >= minOffset - size && scrollOffset <= maxOffset + size) {
      alignment = AUTO_ALIGNMENT;
    } else {
      alignment = CENTERED_ALIGNMENT;
    }
  }
  switch (alignment) {
    case START_ALIGNMENT: {
      return maxOffset;
    }
    case END_ALIGNMENT: {
      return minOffset;
    }
    case CENTERED_ALIGNMENT: {
      return Math.round(minOffset + (maxOffset - minOffset) / 2);
    }
    case AUTO_ALIGNMENT:
    default: {
      if (scrollOffset >= minOffset && scrollOffset <= maxOffset) {
        return scrollOffset;
      } else if (minOffset > maxOffset) {
        return minOffset;
      } else if (scrollOffset < minOffset) {
        return minOffset;
      } else {
        return maxOffset;
      }
    }
  }
};
const FixedSizeGrid$1 = createGrid({
  name: "ElDynamicSizeGrid",
  getColumnPosition: (props, idx, cache) => {
    const item = getItemFromCache$1(props, idx, cache, "column");
    return [item.size, item.offset];
  },
  getRowPosition: (props, idx, cache) => {
    const item = getItemFromCache$1(props, idx, cache, "row");
    return [item.size, item.offset];
  },
  getColumnOffset: (props, columnIndex, alignment, scrollLeft, cache, scrollBarWidth) => getOffset(props, columnIndex, alignment, scrollLeft, cache, "column", scrollBarWidth),
  getRowOffset: (props, rowIndex, alignment, scrollTop, cache, scrollBarWidth) => getOffset(props, rowIndex, alignment, scrollTop, cache, "row", scrollBarWidth),
  getColumnStartIndexForOffset: (props, scrollLeft, cache) => findItem$1(props, cache, scrollLeft, "column"),
  getColumnStopIndexForStartIndex: (props, startIndex, scrollLeft, cache) => {
    const item = getItemFromCache$1(props, startIndex, cache, "column");
    const maxOffset = scrollLeft + props.width;
    let offset = item.offset + item.size;
    let stopIndex = startIndex;
    while (stopIndex < props.totalColumn - 1 && offset < maxOffset) {
      stopIndex++;
      offset += getItemFromCache$1(props, startIndex, cache, "column").size;
    }
    return stopIndex;
  },
  getEstimatedTotalHeight,
  getEstimatedTotalWidth,
  getRowStartIndexForOffset: (props, scrollTop, cache) => findItem$1(props, cache, scrollTop, "row"),
  getRowStopIndexForStartIndex: (props, startIndex, scrollTop, cache) => {
    const { totalRow, height } = props;
    const item = getItemFromCache$1(props, startIndex, cache, "row");
    const maxOffset = scrollTop + height;
    let offset = item.size + item.offset;
    let stopIndex = startIndex;
    while (stopIndex < totalRow - 1 && offset < maxOffset) {
      stopIndex++;
      offset += getItemFromCache$1(props, stopIndex, cache, "row").size;
    }
    return stopIndex;
  },
  initCache: ({
    estimatedColumnWidth = DEFAULT_DYNAMIC_LIST_ITEM_SIZE,
    estimatedRowHeight = DEFAULT_DYNAMIC_LIST_ITEM_SIZE
  }) => {
    const cache = {
      column: {},
      estimatedColumnWidth,
      estimatedRowHeight,
      lastVisitedColumnIndex: -1,
      lastVisitedRowIndex: -1,
      row: {}
    };
    return cache;
  },
  clearCache: true,
  validateProps: ({ columnWidth, rowHeight }) => {
    if (process.env.NODE_ENV !== "production") {
      if (!isFunction(columnWidth)) {
        throwError(SCOPE$2, `
          "columnWidth" must be passed as function,
            instead ${typeof columnWidth} was given.
        `);
      }
      if (!isFunction(rowHeight)) {
        throwError(SCOPE$2, `
          "columnWidth" must be passed as function,
            instead ${typeof rowHeight} was given.
        `);
      }
    }
  }
});

export { FixedSizeGrid$1 as DynamicSizeGrid, DynamicSizeList, FixedSizeGrid, FixedSizeList };
