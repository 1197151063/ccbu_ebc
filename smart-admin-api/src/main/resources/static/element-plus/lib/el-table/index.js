'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
var util = require('../utils/util');
var dom = require('../utils/dom');
var core = require('@popperjs/core');
var PopupManager = require('../utils/popup-manager');
var debounce = require('lodash/debounce');
var locale = require('../locale');
var directives = require('../directives');
var scrollbarWidth = require('../utils/scrollbar-width');
var isServer = require('../utils/isServer');
var ElCheckbox = require('../el-checkbox');
var ElPopper = require('../el-popper');
var ElCheckboxGroup = require('../el-checkbox-group');
var ElScrollbar = require('../el-scrollbar');
var resizeEvent = require('../utils/resize-event');
var throttle = require('lodash/throttle');

function _interopDefaultLegacy (e) { return e && typeof e === 'object' && 'default' in e ? e : { 'default': e }; }

var PopupManager__default = /*#__PURE__*/_interopDefaultLegacy(PopupManager);
var debounce__default = /*#__PURE__*/_interopDefaultLegacy(debounce);
var scrollbarWidth__default = /*#__PURE__*/_interopDefaultLegacy(scrollbarWidth);
var isServer__default = /*#__PURE__*/_interopDefaultLegacy(isServer);
var ElCheckbox__default = /*#__PURE__*/_interopDefaultLegacy(ElCheckbox);
var ElPopper__default = /*#__PURE__*/_interopDefaultLegacy(ElPopper);
var ElCheckboxGroup__default = /*#__PURE__*/_interopDefaultLegacy(ElCheckboxGroup);
var ElScrollbar__default = /*#__PURE__*/_interopDefaultLegacy(ElScrollbar);
var throttle__default = /*#__PURE__*/_interopDefaultLegacy(throttle);

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

var __defProp = Object.defineProperty;
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
const getCell = function(event) {
  let cell = event.target;
  while (cell && cell.tagName.toUpperCase() !== "HTML") {
    if (cell.tagName.toUpperCase() === "TD") {
      return cell;
    }
    cell = cell.parentNode;
  }
  return null;
};
const isObject = function(obj) {
  return obj !== null && typeof obj === "object";
};
const orderBy = function(array, sortKey, reverse, sortMethod, sortBy) {
  if (!sortKey && !sortMethod && (!sortBy || Array.isArray(sortBy) && !sortBy.length)) {
    return array;
  }
  if (typeof reverse === "string") {
    reverse = reverse === "descending" ? -1 : 1;
  } else {
    reverse = reverse && reverse < 0 ? -1 : 1;
  }
  const getKey = sortMethod ? null : function(value, index) {
    if (sortBy) {
      if (!Array.isArray(sortBy)) {
        sortBy = [sortBy];
      }
      return sortBy.map(function(by) {
        if (typeof by === "string") {
          return util.getValueByPath(value, by);
        } else {
          return by(value, index, array);
        }
      });
    }
    if (sortKey !== "$key") {
      if (isObject(value) && "$value" in value)
        value = value.$value;
    }
    return [isObject(value) ? util.getValueByPath(value, sortKey) : value];
  };
  const compare = function(a, b) {
    if (sortMethod) {
      return sortMethod(a.value, b.value);
    }
    for (let i = 0, len = a.key.length; i < len; i++) {
      if (a.key[i] < b.key[i]) {
        return -1;
      }
      if (a.key[i] > b.key[i]) {
        return 1;
      }
    }
    return 0;
  };
  return array.map(function(value, index) {
    return {
      value,
      index,
      key: getKey ? getKey(value, index) : null
    };
  }).sort(function(a, b) {
    let order = compare(a, b);
    if (!order) {
      order = a.index - b.index;
    }
    return order * +reverse;
  }).map((item) => item.value);
};
const getColumnById = function(table, columnId) {
  let column = null;
  table.columns.forEach(function(item) {
    if (item.id === columnId) {
      column = item;
    }
  });
  return column;
};
const getColumnByKey = function(table, columnKey) {
  let column = null;
  for (let i = 0; i < table.columns.length; i++) {
    const item = table.columns[i];
    if (item.columnKey === columnKey) {
      column = item;
      break;
    }
  }
  return column;
};
const getColumnByCell = function(table, cell) {
  const matches = (cell.className || "").match(/el-table_[^\s]+/gm);
  if (matches) {
    return getColumnById(table, matches[0]);
  }
  return null;
};
const getRowIdentity = (row, rowKey) => {
  if (!row)
    throw new Error("row is required when get row identity");
  if (typeof rowKey === "string") {
    if (rowKey.indexOf(".") < 0) {
      return row[rowKey] + "";
    }
    const key = rowKey.split(".");
    let current = row;
    for (let i = 0; i < key.length; i++) {
      current = current[key[i]];
    }
    return current + "";
  } else if (typeof rowKey === "function") {
    return rowKey.call(null, row);
  }
};
const getKeysMap = function(array, rowKey) {
  const arrayMap = {};
  (array || []).forEach((row, index) => {
    arrayMap[getRowIdentity(row, rowKey)] = { row, index };
  });
  return arrayMap;
};
function parseHeight(height) {
  if (typeof height === "number") {
    return height;
  }
  if (typeof height === "string") {
    if (/^\d+(?:px)?$/.test(height)) {
      return parseInt(height, 10);
    } else {
      return height;
    }
  }
  return null;
}
function toggleRowStatus(statusArr, row, newVal) {
  let changed = false;
  const index = statusArr.indexOf(row);
  const included = index !== -1;
  const addRow = () => {
    statusArr.push(row);
    changed = true;
  };
  const removeRow = () => {
    statusArr.splice(index, 1);
    changed = true;
  };
  if (typeof newVal === "boolean") {
    if (newVal && !included) {
      addRow();
    } else if (!newVal && included) {
      removeRow();
    }
  } else {
    if (included) {
      removeRow();
    } else {
      addRow();
    }
  }
  return changed;
}
function walkTreeNode(root, cb, childrenKey = "children", lazyKey = "hasChildren") {
  const isNil = (array) => !(Array.isArray(array) && array.length);
  function _walker(parent, children, level) {
    cb(parent, children, level);
    children.forEach((item) => {
      if (item[lazyKey]) {
        cb(item, null, level + 1);
        return;
      }
      const children2 = item[childrenKey];
      if (!isNil(children2)) {
        _walker(item, children2, level + 1);
      }
    });
  }
  root.forEach((item) => {
    if (item[lazyKey]) {
      cb(item, null, 0);
      return;
    }
    const children = item[childrenKey];
    if (!isNil(children)) {
      _walker(item, children, 0);
    }
  });
}
let removePopper;
function createTablePopper(trigger, popperContent, popperOptions, tooltipEffect) {
  function renderContent() {
    const isLight = tooltipEffect === "light";
    const content2 = document.createElement("div");
    content2.className = `el-popper ${isLight ? "is-light" : "is-dark"}`;
    content2.innerHTML = popperContent;
    content2.style.zIndex = String(PopupManager__default['default'].nextZIndex());
    document.body.appendChild(content2);
    return content2;
  }
  function renderArrow() {
    const arrow2 = document.createElement("div");
    arrow2.className = "el-popper__arrow";
    arrow2.style.bottom = "-4px";
    return arrow2;
  }
  function showPopper() {
    popperInstance && popperInstance.update();
  }
  removePopper = function removePopper2() {
    try {
      popperInstance && popperInstance.destroy();
      content && document.body.removeChild(content);
      dom.off(trigger, "mouseenter", showPopper);
      dom.off(trigger, "mouseleave", removePopper2);
    } catch (e) {
    }
  };
  let popperInstance = null;
  const content = renderContent();
  const arrow = renderArrow();
  content.appendChild(arrow);
  popperInstance = core.createPopper(trigger, content, __spreadValues({
    modifiers: [
      {
        name: "offset",
        options: {
          offset: [0, 8]
        }
      },
      {
        name: "arrow",
        options: {
          element: arrow,
          padding: 10
        }
      }
    ]
  }, popperOptions));
  dom.on(trigger, "mouseenter", showPopper);
  dom.on(trigger, "mouseleave", removePopper);
  return popperInstance;
}

function useExpand(watcherData) {
  const instance = vue.getCurrentInstance();
  const defaultExpandAll = vue.ref(false);
  const expandRows = vue.ref([]);
  const updateExpandRows = () => {
    const data = watcherData.data.value || [];
    const rowKey = watcherData.rowKey.value;
    if (defaultExpandAll.value) {
      expandRows.value = data.slice();
    } else if (rowKey) {
      const expandRowsMap = getKeysMap(expandRows.value, rowKey);
      expandRows.value = data.reduce((prev, row) => {
        const rowId = getRowIdentity(row, rowKey);
        const rowInfo = expandRowsMap[rowId];
        if (rowInfo) {
          prev.push(row);
        }
        return prev;
      }, []);
    } else {
      expandRows.value = [];
    }
  };
  const toggleRowExpansion = (row, expanded) => {
    const changed = toggleRowStatus(expandRows.value, row, expanded);
    if (changed) {
      instance.emit("expand-change", row, expandRows.value.slice());
      instance.store.scheduleLayout();
    }
  };
  const setExpandRowKeys = (rowKeys) => {
    instance.store.assertRowKey();
    const data = watcherData.data.value || [];
    const rowKey = watcherData.rowKey.value;
    const keysMap = getKeysMap(data, rowKey);
    expandRows.value = rowKeys.reduce((prev, cur) => {
      const info = keysMap[cur];
      if (info) {
        prev.push(info.row);
      }
      return prev;
    }, []);
  };
  const isRowExpanded = (row) => {
    const rowKey = watcherData.rowKey.value;
    if (rowKey) {
      const expandMap = getKeysMap(expandRows.value, rowKey);
      return !!expandMap[getRowIdentity(row, rowKey)];
    }
    return expandRows.value.indexOf(row) !== -1;
  };
  return {
    updateExpandRows,
    toggleRowExpansion,
    setExpandRowKeys,
    isRowExpanded,
    states: {
      expandRows,
      defaultExpandAll
    }
  };
}

function useCurrent(watcherData) {
  const instance = vue.getCurrentInstance();
  const _currentRowKey = vue.ref(null);
  const currentRow = vue.ref(null);
  const setCurrentRowKey = (key) => {
    instance.store.assertRowKey();
    _currentRowKey.value = key;
    setCurrentRowByKey(key);
  };
  const restoreCurrentRowKey = () => {
    _currentRowKey.value = null;
  };
  const setCurrentRowByKey = (key) => {
    const { data = [], rowKey } = watcherData;
    let _currentRow = null;
    if (rowKey.value) {
      _currentRow = util.arrayFind(vue.unref(data), (item) => getRowIdentity(item, rowKey.value) === key);
    }
    currentRow.value = _currentRow;
  };
  const updateCurrentRow = (_currentRow) => {
    const oldCurrentRow = currentRow.value;
    if (_currentRow && _currentRow !== oldCurrentRow) {
      currentRow.value = _currentRow;
      instance.emit("current-change", currentRow.value, oldCurrentRow);
      return;
    }
    if (!_currentRow && oldCurrentRow) {
      currentRow.value = null;
      instance.emit("current-change", null, oldCurrentRow);
    }
  };
  const updateCurrentRowData = () => {
    const rowKey = watcherData.rowKey.value;
    const data = watcherData.data.value || [];
    const oldCurrentRow = currentRow.value;
    if (data.indexOf(oldCurrentRow) === -1 && oldCurrentRow) {
      if (rowKey) {
        const currentRowKey = getRowIdentity(oldCurrentRow, rowKey);
        setCurrentRowByKey(currentRowKey);
      } else {
        currentRow.value = null;
      }
      if (currentRow.value === null) {
        instance.emit("current-change", null, oldCurrentRow);
      }
    } else if (_currentRowKey.value) {
      setCurrentRowByKey(_currentRowKey.value);
      restoreCurrentRowKey();
    }
  };
  return {
    setCurrentRowKey,
    restoreCurrentRowKey,
    setCurrentRowByKey,
    updateCurrentRow,
    updateCurrentRowData,
    states: {
      _currentRowKey,
      currentRow
    }
  };
}

var __defProp$1 = Object.defineProperty;
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
function useTree(watcherData) {
  const expandRowKeys = vue.ref([]);
  const treeData = vue.ref({});
  const indent = vue.ref(16);
  const lazy = vue.ref(false);
  const lazyTreeNodeMap = vue.ref({});
  const lazyColumnIdentifier = vue.ref("hasChildren");
  const childrenColumnName = vue.ref("children");
  const instance = vue.getCurrentInstance();
  const normalizedData = vue.computed(() => {
    if (!watcherData.rowKey.value)
      return {};
    const data = watcherData.data.value || [];
    return normalize(data);
  });
  const normalizedLazyNode = vue.computed(() => {
    const rowKey = watcherData.rowKey.value;
    const keys = Object.keys(lazyTreeNodeMap.value);
    const res = {};
    if (!keys.length)
      return res;
    keys.forEach((key) => {
      if (lazyTreeNodeMap.value[key].length) {
        const item = { children: [] };
        lazyTreeNodeMap.value[key].forEach((row) => {
          const currentRowKey = getRowIdentity(row, rowKey);
          item.children.push(currentRowKey);
          if (row[lazyColumnIdentifier.value] && !res[currentRowKey]) {
            res[currentRowKey] = { children: [] };
          }
        });
        res[key] = item;
      }
    });
    return res;
  });
  const normalize = (data) => {
    const rowKey = watcherData.rowKey.value;
    const res = {};
    walkTreeNode(data, (parent, children, level) => {
      const parentId = getRowIdentity(parent, rowKey);
      if (Array.isArray(children)) {
        res[parentId] = {
          children: children.map((row) => getRowIdentity(row, rowKey)),
          level
        };
      } else if (lazy.value) {
        res[parentId] = {
          children: [],
          lazy: true,
          level
        };
      }
    }, childrenColumnName.value, lazyColumnIdentifier.value);
    return res;
  };
  const updateTreeData = () => {
    var _a, _b;
    const nested = normalizedData.value;
    const normalizedLazyNode_ = normalizedLazyNode.value;
    const keys = Object.keys(nested);
    const newTreeData = {};
    if (keys.length) {
      const oldTreeData = vue.unref(treeData);
      const defaultExpandAll = (_a = instance.store) == null ? void 0 : _a.states.defaultExpandAll.value;
      const rootLazyRowKeys = [];
      const getExpanded = (oldValue, key) => {
        const included = defaultExpandAll || expandRowKeys.value && expandRowKeys.value.indexOf(key) !== -1;
        return !!(oldValue && oldValue.expanded || included);
      };
      keys.forEach((key) => {
        const oldValue = oldTreeData[key];
        const newValue = __spreadValues$1({}, nested[key]);
        newValue.expanded = getExpanded(oldValue, key);
        if (newValue.lazy) {
          const { loaded = false, loading = false } = oldValue || {};
          newValue.loaded = !!loaded;
          newValue.loading = !!loading;
          rootLazyRowKeys.push(key);
        }
        newTreeData[key] = newValue;
      });
      const lazyKeys = Object.keys(normalizedLazyNode_);
      if (lazy.value && lazyKeys.length && rootLazyRowKeys.length) {
        lazyKeys.forEach((key) => {
          const oldValue = oldTreeData[key];
          const lazyNodeChildren = normalizedLazyNode_[key].children;
          if (rootLazyRowKeys.indexOf(key) !== -1) {
            if (newTreeData[key].children.length !== 0) {
              throw new Error("[ElTable]children must be an empty array.");
            }
            newTreeData[key].children = lazyNodeChildren;
          } else {
            const { loaded = false, loading = false } = oldValue || {};
            newTreeData[key] = {
              lazy: true,
              loaded: !!loaded,
              loading: !!loading,
              expanded: getExpanded(oldValue, key),
              children: lazyNodeChildren,
              level: ""
            };
          }
        });
      }
    }
    treeData.value = newTreeData;
    (_b = instance.store) == null ? void 0 : _b.updateTableScrollY();
  };
  vue.watch(() => normalizedData.value, updateTreeData);
  vue.watch(() => normalizedLazyNode.value, updateTreeData);
  const updateTreeExpandKeys = (value) => {
    expandRowKeys.value = value;
    updateTreeData();
  };
  const toggleTreeExpansion = (row, expanded) => {
    instance.store.assertRowKey();
    const rowKey = watcherData.rowKey.value;
    const id = getRowIdentity(row, rowKey);
    const data = id && treeData.value[id];
    if (id && data && "expanded" in data) {
      const oldExpanded = data.expanded;
      expanded = typeof expanded === "undefined" ? !data.expanded : expanded;
      treeData.value[id].expanded = expanded;
      if (oldExpanded !== expanded) {
        instance.emit("expand-change", row, expanded);
      }
      instance.store.updateTableScrollY();
    }
  };
  const loadOrToggle = (row) => {
    instance.store.assertRowKey();
    const rowKey = watcherData.rowKey.value;
    const id = getRowIdentity(row, rowKey);
    const data = treeData.value[id];
    if (lazy.value && data && "loaded" in data && !data.loaded) {
      loadData(row, id, data);
    } else {
      toggleTreeExpansion(row, void 0);
    }
  };
  const loadData = (row, key, treeNode) => {
    const { load } = instance.props;
    if (load && !treeData.value[key].loaded) {
      treeData.value[key].loading = true;
      load(row, treeNode, (data) => {
        if (!Array.isArray(data)) {
          throw new Error("[ElTable] data must be an array");
        }
        treeData.value[key].loading = false;
        treeData.value[key].loaded = true;
        treeData.value[key].expanded = true;
        if (data.length) {
          lazyTreeNodeMap.value[key] = data;
        }
        instance.emit("expand-change", row, true);
      });
    }
  };
  return {
    loadData,
    loadOrToggle,
    toggleTreeExpansion,
    updateTreeExpandKeys,
    updateTreeData,
    normalize,
    states: {
      expandRowKeys,
      treeData,
      indent,
      lazy,
      lazyTreeNodeMap,
      lazyColumnIdentifier,
      childrenColumnName
    }
  };
}

var __defProp$2 = Object.defineProperty;
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
const sortData = (data, states) => {
  const sortingColumn = states.sortingColumn;
  if (!sortingColumn || typeof sortingColumn.sortable === "string") {
    return data;
  }
  return orderBy(data, states.sortProp, states.sortOrder, sortingColumn.sortMethod, sortingColumn.sortBy);
};
const doFlattenColumns = (columns) => {
  const result = [];
  columns.forEach((column) => {
    if (column.children) {
      result.push.apply(result, doFlattenColumns(column.children));
    } else {
      result.push(column);
    }
  });
  return result;
};
function useWatcher() {
  const instance = vue.getCurrentInstance();
  const rowKey = vue.ref(null);
  const data = vue.ref([]);
  const _data = vue.ref([]);
  const isComplex = vue.ref(false);
  const _columns = vue.ref([]);
  const originColumns = vue.ref([]);
  const columns = vue.ref([]);
  const fixedColumns = vue.ref([]);
  const rightFixedColumns = vue.ref([]);
  const leafColumns = vue.ref([]);
  const fixedLeafColumns = vue.ref([]);
  const rightFixedLeafColumns = vue.ref([]);
  const leafColumnsLength = vue.ref(0);
  const fixedLeafColumnsLength = vue.ref(0);
  const rightFixedLeafColumnsLength = vue.ref(0);
  const isAllSelected = vue.ref(false);
  const selection = vue.ref([]);
  const reserveSelection = vue.ref(false);
  const selectOnIndeterminate = vue.ref(false);
  const selectable = vue.ref(null);
  const filters = vue.ref({});
  const filteredData = vue.ref(null);
  const sortingColumn = vue.ref(null);
  const sortProp = vue.ref(null);
  const sortOrder = vue.ref(null);
  const hoverRow = vue.ref(null);
  vue.watch(data, () => instance.state && scheduleLayout(false), {
    deep: true
  });
  const assertRowKey = () => {
    if (!rowKey.value)
      throw new Error("[ElTable] prop row-key is required");
  };
  const updateColumns = () => {
    fixedColumns.value = _columns.value.filter((column) => column.fixed === true || column.fixed === "left");
    rightFixedColumns.value = _columns.value.filter((column) => column.fixed === "right");
    if (fixedColumns.value.length > 0 && _columns.value[0] && _columns.value[0].type === "selection" && !_columns.value[0].fixed) {
      _columns.value[0].fixed = true;
      fixedColumns.value.unshift(_columns.value[0]);
    }
    const notFixedColumns = _columns.value.filter((column) => !column.fixed);
    originColumns.value = [].concat(fixedColumns.value).concat(notFixedColumns).concat(rightFixedColumns.value);
    const leafColumns2 = doFlattenColumns(notFixedColumns);
    const fixedLeafColumns2 = doFlattenColumns(fixedColumns.value);
    const rightFixedLeafColumns2 = doFlattenColumns(rightFixedColumns.value);
    leafColumnsLength.value = leafColumns2.length;
    fixedLeafColumnsLength.value = fixedLeafColumns2.length;
    rightFixedLeafColumnsLength.value = rightFixedLeafColumns2.length;
    columns.value = [].concat(fixedLeafColumns2).concat(leafColumns2).concat(rightFixedLeafColumns2);
    isComplex.value = fixedColumns.value.length > 0 || rightFixedColumns.value.length > 0;
  };
  const scheduleLayout = (needUpdateColumns, immediate = false) => {
    if (needUpdateColumns) {
      updateColumns();
    }
    if (immediate) {
      instance.state.doLayout();
    } else {
      instance.state.debouncedUpdateLayout();
    }
  };
  const isSelected = (row) => {
    return selection.value.indexOf(row) > -1;
  };
  const clearSelection = () => {
    isAllSelected.value = false;
    const oldSelection = selection.value;
    if (oldSelection.length) {
      selection.value = [];
      instance.emit("selection-change", []);
    }
  };
  const cleanSelection = () => {
    let deleted;
    if (rowKey.value) {
      deleted = [];
      const selectedMap = getKeysMap(selection.value, rowKey.value);
      const dataMap = getKeysMap(data.value, rowKey.value);
      for (const key in selectedMap) {
        if (hasOwn(selectedMap, key) && !dataMap[key]) {
          deleted.push(selectedMap[key].row);
        }
      }
    } else {
      deleted = selection.value.filter((item) => data.value.indexOf(item) === -1);
    }
    if (deleted.length) {
      const newSelection = selection.value.filter((item) => deleted.indexOf(item) === -1);
      selection.value = newSelection;
      instance.emit("selection-change", newSelection.slice());
    }
  };
  const toggleRowSelection = (row, selected = void 0, emitChange = true) => {
    const changed = toggleRowStatus(selection.value, row, selected);
    if (changed) {
      const newSelection = (selection.value || []).slice();
      if (emitChange) {
        instance.emit("select", newSelection, row);
      }
      instance.emit("selection-change", newSelection);
    }
  };
  const _toggleAllSelection = () => {
    const value = selectOnIndeterminate.value ? !isAllSelected.value : !(isAllSelected.value || selection.value.length);
    isAllSelected.value = value;
    let selectionChanged = false;
    data.value.forEach((row, index) => {
      if (selectable.value) {
        if (selectable.value.call(null, row, index) && toggleRowStatus(selection.value, row, value)) {
          selectionChanged = true;
        }
      } else {
        if (toggleRowStatus(selection.value, row, value)) {
          selectionChanged = true;
        }
      }
    });
    if (selectionChanged) {
      instance.emit("selection-change", selection.value ? selection.value.slice() : []);
    }
    instance.emit("select-all", selection.value);
  };
  const updateSelectionByRowKey = () => {
    const selectedMap = getKeysMap(selection.value, rowKey.value);
    data.value.forEach((row) => {
      const rowId = getRowIdentity(row, rowKey.value);
      const rowInfo = selectedMap[rowId];
      if (rowInfo) {
        selection.value[rowInfo.index] = row;
      }
    });
  };
  const updateAllSelected = () => {
    var _a;
    if (((_a = data.value) == null ? void 0 : _a.length) === 0) {
      isAllSelected.value = false;
      return;
    }
    let selectedMap;
    if (rowKey.value) {
      selectedMap = getKeysMap(selection.value, rowKey.value);
    }
    const isSelected2 = function(row) {
      if (selectedMap) {
        return !!selectedMap[getRowIdentity(row, rowKey.value)];
      } else {
        return selection.value.indexOf(row) !== -1;
      }
    };
    let isAllSelected_ = true;
    let selectedCount = 0;
    for (let i = 0, j = (data.value || []).length; i < j; i++) {
      const item = data.value[i];
      const isRowSelectable = selectable.value && selectable.value.call(null, item, i);
      if (!isSelected2(item)) {
        if (!selectable.value || isRowSelectable) {
          isAllSelected_ = false;
          break;
        }
      } else {
        selectedCount++;
      }
    }
    if (selectedCount === 0)
      isAllSelected_ = false;
    isAllSelected.value = isAllSelected_;
  };
  const updateFilters = (columns2, values) => {
    if (!Array.isArray(columns2)) {
      columns2 = [columns2];
    }
    const filters_ = {};
    columns2.forEach((col) => {
      filters.value[col.id] = values;
      filters_[col.columnKey || col.id] = values;
    });
    return filters_;
  };
  const updateSort = (column, prop, order) => {
    if (sortingColumn.value && sortingColumn.value !== column) {
      sortingColumn.value.order = null;
    }
    sortingColumn.value = column;
    sortProp.value = prop;
    sortOrder.value = order;
  };
  const execFilter = () => {
    let sourceData = vue.unref(_data);
    Object.keys(filters.value).forEach((columnId) => {
      const values = filters.value[columnId];
      if (!values || values.length === 0)
        return;
      const column = getColumnById({
        columns: columns.value
      }, columnId);
      if (column && column.filterMethod) {
        sourceData = sourceData.filter((row) => {
          return values.some((value) => column.filterMethod.call(null, value, row, column));
        });
      }
    });
    filteredData.value = sourceData;
  };
  const execSort = () => {
    data.value = sortData(filteredData.value, {
      sortingColumn: sortingColumn.value,
      sortProp: sortProp.value,
      sortOrder: sortOrder.value
    });
  };
  const execQuery = (ignore = void 0) => {
    if (!(ignore && ignore.filter)) {
      execFilter();
    }
    execSort();
  };
  const clearFilter = (columnKeys) => {
    const {
      tableHeader,
      fixedTableHeader,
      rightFixedTableHeader
    } = instance.refs;
    let panels = {};
    if (tableHeader)
      panels = Object.assign(panels, tableHeader.filterPanels);
    if (fixedTableHeader)
      panels = Object.assign(panels, fixedTableHeader.filterPanels);
    if (rightFixedTableHeader)
      panels = Object.assign(panels, rightFixedTableHeader.filterPanels);
    const keys = Object.keys(panels);
    if (!keys.length)
      return;
    if (typeof columnKeys === "string") {
      columnKeys = [columnKeys];
    }
    if (Array.isArray(columnKeys)) {
      const columns_ = columnKeys.map((key) => getColumnByKey({
        columns: columns.value
      }, key));
      keys.forEach((key) => {
        const column = columns_.find((col) => col.id === key);
        if (column) {
          column.filteredValue = [];
        }
      });
      instance.store.commit("filterChange", {
        column: columns_,
        values: [],
        silent: true,
        multi: true
      });
    } else {
      keys.forEach((key) => {
        const column = columns.value.find((col) => col.id === key);
        if (column) {
          column.filteredValue = [];
        }
      });
      filters.value = {};
      instance.store.commit("filterChange", {
        column: {},
        values: [],
        silent: true
      });
    }
  };
  const clearSort = () => {
    if (!sortingColumn.value)
      return;
    updateSort(null, null, null);
    instance.store.commit("changeSortCondition", {
      silent: true
    });
  };
  const {
    setExpandRowKeys,
    toggleRowExpansion,
    updateExpandRows,
    states: expandStates,
    isRowExpanded
  } = useExpand({
    data,
    rowKey
  });
  const {
    updateTreeExpandKeys,
    toggleTreeExpansion,
    loadOrToggle,
    states: treeStates
  } = useTree({
    data,
    rowKey
  });
  const {
    updateCurrentRowData,
    updateCurrentRow,
    setCurrentRowKey,
    states: currentData
  } = useCurrent({
    data,
    rowKey
  });
  const setExpandRowKeysAdapter = (val) => {
    setExpandRowKeys(val);
    updateTreeExpandKeys(val);
  };
  const toggleRowExpansionAdapter = (row, expanded) => {
    const hasExpandColumn = columns.value.some(({ type }) => type === "expand");
    if (hasExpandColumn) {
      toggleRowExpansion(row, expanded);
    } else {
      toggleTreeExpansion(row, expanded);
    }
  };
  return {
    assertRowKey,
    updateColumns,
    scheduleLayout,
    isSelected,
    clearSelection,
    cleanSelection,
    toggleRowSelection,
    _toggleAllSelection,
    toggleAllSelection: null,
    updateSelectionByRowKey,
    updateAllSelected,
    updateFilters,
    updateCurrentRow,
    updateSort,
    execFilter,
    execSort,
    execQuery,
    clearFilter,
    clearSort,
    toggleRowExpansion,
    setExpandRowKeysAdapter,
    setCurrentRowKey,
    toggleRowExpansionAdapter,
    isRowExpanded,
    updateExpandRows,
    updateCurrentRowData,
    loadOrToggle,
    states: __spreadValues$2(__spreadValues$2(__spreadValues$2({
      rowKey,
      data,
      _data,
      isComplex,
      _columns,
      originColumns,
      columns,
      fixedColumns,
      rightFixedColumns,
      leafColumns,
      fixedLeafColumns,
      rightFixedLeafColumns,
      leafColumnsLength,
      fixedLeafColumnsLength,
      rightFixedLeafColumnsLength,
      isAllSelected,
      selection,
      reserveSelection,
      selectOnIndeterminate,
      selectable,
      filters,
      filteredData,
      sortingColumn,
      sortProp,
      sortOrder,
      hoverRow
    }, expandStates), treeStates), currentData)
  };
}

var __defProp$3 = Object.defineProperty;
var __defProps = Object.defineProperties;
var __getOwnPropDescs = Object.getOwnPropertyDescriptors;
var __getOwnPropSymbols$3 = Object.getOwnPropertySymbols;
var __hasOwnProp$3 = Object.prototype.hasOwnProperty;
var __propIsEnum$3 = Object.prototype.propertyIsEnumerable;
var __defNormalProp$3 = (obj, key, value) => key in obj ? __defProp$3(obj, key, { enumerable: true, configurable: true, writable: true, value }) : obj[key] = value;
var __spreadValues$3 = (a, b) => {
  for (var prop in b || (b = {}))
    if (__hasOwnProp$3.call(b, prop))
      __defNormalProp$3(a, prop, b[prop]);
  if (__getOwnPropSymbols$3)
    for (var prop of __getOwnPropSymbols$3(b)) {
      if (__propIsEnum$3.call(b, prop))
        __defNormalProp$3(a, prop, b[prop]);
    }
  return a;
};
var __spreadProps = (a, b) => __defProps(a, __getOwnPropDescs(b));
function replaceColumn(array, column) {
  return array.map((item) => {
    var _a;
    if (item.id === column.id) {
      return column;
    } else if ((_a = item.children) == null ? void 0 : _a.length) {
      item.children = replaceColumn(item.children, column);
    }
    return item;
  });
}
function sortColumn(array) {
  array.forEach((item) => {
    var _a, _b;
    item.no = (_a = item.getColumnIndex) == null ? void 0 : _a.call(item);
    if ((_b = item.children) == null ? void 0 : _b.length) {
      sortColumn(item.children);
    }
  });
  array.sort((cur, pre) => cur.no - pre.no);
}
function useStore() {
  const instance = vue.getCurrentInstance();
  const watcher = useWatcher();
  const mutations = {
    setData(states, data) {
      const dataInstanceChanged = vue.unref(states.data) !== data;
      states.data.value = data;
      states._data.value = data;
      instance.store.execQuery();
      instance.store.updateCurrentRowData();
      instance.store.updateExpandRows();
      if (vue.unref(states.reserveSelection)) {
        instance.store.assertRowKey();
        instance.store.updateSelectionByRowKey();
      } else {
        if (dataInstanceChanged) {
          instance.store.clearSelection();
        } else {
          instance.store.cleanSelection();
        }
      }
      instance.store.updateAllSelected();
      if (instance.$ready) {
        instance.store.scheduleLayout();
      }
    },
    insertColumn(states, column, parent) {
      const array = vue.unref(states._columns);
      let newColumns = [];
      if (!parent) {
        array.push(column);
        newColumns = array;
      } else {
        if (parent && !parent.children) {
          parent.children = [];
        }
        parent.children.push(column);
        newColumns = replaceColumn(array, parent);
      }
      sortColumn(newColumns);
      states._columns.value = newColumns;
      if (column.type === "selection") {
        states.selectable.value = column.selectable;
        states.reserveSelection.value = column.reserveSelection;
      }
      if (instance.$ready) {
        instance.store.updateColumns();
        instance.store.scheduleLayout();
      }
    },
    removeColumn(states, column, parent) {
      const array = vue.unref(states._columns) || [];
      if (parent) {
        parent.children.splice(parent.children.findIndex((item) => item.id === column.id), 1);
        if (parent.children.length === 0) {
          delete parent.children;
        }
        states._columns.value = replaceColumn(array, parent);
      } else {
        const index = array.indexOf(column);
        if (index > -1) {
          array.splice(index, 1);
          states._columns.value = array;
        }
      }
      if (instance.$ready) {
        instance.store.updateColumns();
        instance.store.scheduleLayout();
      }
    },
    sort(states, options) {
      const { prop, order, init } = options;
      if (prop) {
        const column = util.arrayFind(vue.unref(states.columns), (column2) => column2.property === prop);
        if (column) {
          column.order = order;
          instance.store.updateSort(column, prop, order);
          instance.store.commit("changeSortCondition", { init });
        }
      }
    },
    changeSortCondition(states, options) {
      const { sortingColumn: column, sortProp: prop, sortOrder: order } = states;
      if (vue.unref(order) === null) {
        states.sortingColumn.value = null;
        states.sortProp.value = null;
      }
      const ingore = { filter: true };
      instance.store.execQuery(ingore);
      if (!options || !(options.silent || options.init)) {
        instance.emit("sort-change", {
          column: vue.unref(column),
          prop: vue.unref(prop),
          order: vue.unref(order)
        });
      }
      instance.store.updateTableScrollY();
    },
    filterChange(_states, options) {
      const { column, values, silent } = options;
      const newFilters = instance.store.updateFilters(column, values);
      instance.store.execQuery();
      if (!silent) {
        instance.emit("filter-change", newFilters);
      }
      instance.store.updateTableScrollY();
    },
    toggleAllSelection() {
      instance.store.toggleAllSelection();
    },
    rowSelectedChanged(_states, row) {
      instance.store.toggleRowSelection(row);
      instance.store.updateAllSelected();
    },
    setHoverRow(states, row) {
      states.hoverRow.value = row;
    },
    setCurrentRow(_states, row) {
      instance.store.updateCurrentRow(row);
    }
  };
  const commit = function(name, ...args) {
    const mutations2 = instance.store.mutations;
    if (mutations2[name]) {
      mutations2[name].apply(instance, [instance.store.states].concat(args));
    } else {
      throw new Error(`Action not found: ${name}`);
    }
  };
  const updateTableScrollY = function() {
    vue.nextTick(() => instance.layout.updateScrollY.apply(instance.layout));
  };
  return __spreadProps(__spreadValues$3({}, watcher), {
    mutations,
    commit,
    updateTableScrollY
  });
}

const InitialStateMap = {
  rowKey: "rowKey",
  defaultExpandAll: "defaultExpandAll",
  selectOnIndeterminate: "selectOnIndeterminate",
  indent: "indent",
  lazy: "lazy",
  data: "data",
  ["treeProps.hasChildren"]: {
    key: "lazyColumnIdentifier",
    default: "hasChildren"
  },
  ["treeProps.children"]: {
    key: "childrenColumnName",
    default: "children"
  }
};
function createStore(table, props) {
  if (!table) {
    throw new Error("Table is required.");
  }
  const store = useStore();
  store.toggleAllSelection = debounce__default['default'](store._toggleAllSelection, 10);
  Object.keys(InitialStateMap).forEach((key) => {
    handleValue(getArrKeysValue(props, key), key, store);
  });
  proxyTableProps(store, props);
  return store;
}
function proxyTableProps(store, props) {
  Object.keys(InitialStateMap).forEach((key) => {
    vue.watch(() => getArrKeysValue(props, key), (value) => {
      handleValue(value, key, store);
    });
  });
}
function handleValue(value, propsKey, store) {
  let newVal = value;
  let storeKey = InitialStateMap[propsKey];
  if (typeof InitialStateMap[propsKey] === "object") {
    storeKey = storeKey.key;
    newVal = newVal || InitialStateMap[propsKey].default;
  }
  store.states[storeKey].value = newVal;
}
function getArrKeysValue(props, keys) {
  if (keys.includes(".")) {
    const keyList = keys.split(".");
    let value = props;
    keyList.forEach((key) => {
      value = value[key];
    });
    return value;
  } else {
    return props[keys];
  }
}

class TableLayout {
  constructor(options) {
    this.observers = [];
    this.table = null;
    this.store = null;
    this.columns = [];
    this.fit = true;
    this.showHeader = true;
    this.height = vue.ref(null);
    this.scrollX = vue.ref(false);
    this.scrollY = vue.ref(false);
    this.bodyWidth = vue.ref(null);
    this.fixedWidth = vue.ref(null);
    this.rightFixedWidth = vue.ref(null);
    this.tableHeight = vue.ref(null);
    this.headerHeight = vue.ref(44);
    this.appendHeight = vue.ref(0);
    this.footerHeight = vue.ref(44);
    this.viewportHeight = vue.ref(null);
    this.bodyHeight = vue.ref(null);
    this.fixedBodyHeight = vue.ref(null);
    this.gutterWidth = scrollbarWidth__default['default']();
    for (const name in options) {
      if (hasOwn(options, name)) {
        if (vue.isRef(this[name])) {
          this[name].value = options[name];
        } else {
          this[name] = options[name];
        }
      }
    }
    if (!this.table) {
      throw new Error("table is required for Table Layout");
    }
    if (!this.store) {
      throw new Error("store is required for Table Layout");
    }
  }
  updateScrollY() {
    const height = this.height.value;
    if (height === null)
      return false;
    const bodyWrapper = this.table.refs.bodyWrapper;
    if (this.table.vnode.el && bodyWrapper) {
      let scrollY = true;
      const prevScrollY = this.scrollY.value;
      if (this.bodyHeight.value === null) {
        scrollY = false;
      } else {
        const body = bodyWrapper.querySelector(".el-table__body");
        scrollY = body.offsetHeight > this.bodyHeight.value;
      }
      this.scrollY.value = scrollY;
      return prevScrollY !== scrollY;
    }
    return false;
  }
  setHeight(value, prop = "height") {
    if (isServer__default['default'])
      return;
    const el = this.table.vnode.el;
    value = parseHeight(value);
    this.height.value = Number(value);
    if (!el && (value || value === 0))
      return vue.nextTick(() => this.setHeight(value, prop));
    if (typeof value === "number") {
      el.style[prop] = value + "px";
      this.updateElsHeight();
    } else if (typeof value === "string") {
      el.style[prop] = value;
      this.updateElsHeight();
    }
  }
  setMaxHeight(value) {
    this.setHeight(value, "max-height");
  }
  getFlattenColumns() {
    const flattenColumns = [];
    const columns = this.table.store.states.columns.value;
    columns.forEach((column) => {
      if (column.isColumnGroup) {
        flattenColumns.push.apply(flattenColumns, column.columns);
      } else {
        flattenColumns.push(column);
      }
    });
    return flattenColumns;
  }
  updateElsHeight() {
    if (!this.table.$ready)
      return vue.nextTick(() => this.updateElsHeight());
    const { headerWrapper, appendWrapper, footerWrapper } = this.table.refs;
    this.appendHeight.value = appendWrapper ? appendWrapper.offsetHeight : 0;
    if (this.showHeader && !headerWrapper)
      return;
    const headerTrElm = headerWrapper ? headerWrapper.querySelector(".el-table__header tr") : null;
    const noneHeader = this.headerDisplayNone(headerTrElm);
    const headerHeight = this.headerHeight.value = !this.showHeader ? 0 : headerWrapper.offsetHeight;
    if (this.showHeader && !noneHeader && headerWrapper.offsetWidth > 0 && (this.table.store.states.columns.value || []).length > 0 && headerHeight < 2) {
      return vue.nextTick(() => this.updateElsHeight());
    }
    const tableHeight = this.tableHeight.value = this.table.vnode.el.clientHeight;
    const footerHeight = this.footerHeight.value = footerWrapper ? footerWrapper.offsetHeight : 0;
    if (this.height.value !== null) {
      this.bodyHeight.value = tableHeight - headerHeight - footerHeight + (footerWrapper ? 1 : 0);
    }
    this.fixedBodyHeight.value = this.scrollX.value ? this.bodyHeight.value - this.gutterWidth : this.bodyHeight.value;
    this.viewportHeight.value = this.scrollX.value ? tableHeight - this.gutterWidth : tableHeight;
    this.updateScrollY();
    this.notifyObservers("scrollable");
  }
  headerDisplayNone(elm) {
    if (!elm)
      return true;
    let headerChild = elm;
    while (headerChild.tagName !== "DIV") {
      if (getComputedStyle(headerChild).display === "none") {
        return true;
      }
      headerChild = headerChild.parentElement;
    }
    return false;
  }
  updateColumnsWidth() {
    if (isServer__default['default'])
      return;
    const fit = this.fit;
    const bodyWidth = this.table.vnode.el.clientWidth;
    let bodyMinWidth = 0;
    const flattenColumns = this.getFlattenColumns();
    const flexColumns = flattenColumns.filter((column) => typeof column.width !== "number");
    flattenColumns.forEach((column) => {
      if (typeof column.width === "number" && column.realWidth)
        column.realWidth = null;
    });
    if (flexColumns.length > 0 && fit) {
      flattenColumns.forEach((column) => {
        bodyMinWidth += Number(column.width || column.minWidth || 80);
      });
      const scrollYWidth = this.scrollY.value ? this.gutterWidth : 0;
      if (bodyMinWidth <= bodyWidth - scrollYWidth) {
        this.scrollX.value = false;
        const totalFlexWidth = bodyWidth - scrollYWidth - bodyMinWidth;
        if (flexColumns.length === 1) {
          flexColumns[0].realWidth = Number(flexColumns[0].minWidth || 80) + totalFlexWidth;
        } else {
          const allColumnsWidth = flexColumns.reduce((prev, column) => prev + Number(column.minWidth || 80), 0);
          const flexWidthPerPixel = totalFlexWidth / allColumnsWidth;
          let noneFirstWidth = 0;
          flexColumns.forEach((column, index) => {
            if (index === 0)
              return;
            const flexWidth = Math.floor(Number(column.minWidth || 80) * flexWidthPerPixel);
            noneFirstWidth += flexWidth;
            column.realWidth = Number(column.minWidth || 80) + flexWidth;
          });
          flexColumns[0].realWidth = Number(flexColumns[0].minWidth || 80) + totalFlexWidth - noneFirstWidth;
        }
      } else {
        this.scrollX.value = true;
        flexColumns.forEach(function(column) {
          column.realWidth = Number(column.minWidth);
        });
      }
      this.bodyWidth.value = Math.max(bodyMinWidth, bodyWidth);
      this.table.state.resizeState.value.width = this.bodyWidth.value;
    } else {
      flattenColumns.forEach((column) => {
        if (!column.width && !column.minWidth) {
          column.realWidth = 80;
        } else {
          column.realWidth = Number(column.width || column.minWidth);
        }
        bodyMinWidth += column.realWidth;
      });
      this.scrollX.value = bodyMinWidth > bodyWidth;
      this.bodyWidth.value = bodyMinWidth;
    }
    const fixedColumns = this.store.states.fixedColumns.value;
    if (fixedColumns.length > 0) {
      let fixedWidth = 0;
      fixedColumns.forEach(function(column) {
        fixedWidth += Number(column.realWidth || column.width);
      });
      this.fixedWidth.value = fixedWidth;
    }
    const rightFixedColumns = this.store.states.rightFixedColumns.value;
    if (rightFixedColumns.length > 0) {
      let rightFixedWidth = 0;
      rightFixedColumns.forEach(function(column) {
        rightFixedWidth += Number(column.realWidth || column.width);
      });
      this.rightFixedWidth.value = rightFixedWidth;
    }
    this.notifyObservers("columns");
  }
  addObserver(observer) {
    this.observers.push(observer);
  }
  removeObserver(observer) {
    const index = this.observers.indexOf(observer);
    if (index !== -1) {
      this.observers.splice(index, 1);
    }
  }
  notifyObservers(event) {
    const observers = this.observers;
    observers.forEach((observer) => {
      var _a, _b;
      switch (event) {
        case "columns":
          (_a = observer.state) == null ? void 0 : _a.onColumnsChange(this);
          break;
        case "scrollable":
          (_b = observer.state) == null ? void 0 : _b.onScrollableChange(this);
          break;
        default:
          throw new Error(`Table Layout don't have event ${event}.`);
      }
    });
  }
}

var script = vue.defineComponent({
  name: "ElTableFilterPanel",
  components: {
    ElCheckbox: ElCheckbox__default['default'],
    ElCheckboxGroup: ElCheckboxGroup__default['default'],
    ElScrollbar: ElScrollbar__default['default'],
    ElPopper: ElPopper__default['default']
  },
  directives: { ClickOutside: directives.ClickOutside },
  props: {
    placement: {
      type: String,
      default: "bottom-start"
    },
    store: {
      type: Object
    },
    column: {
      type: Object
    },
    upDataColumn: {
      type: Function
    }
  },
  setup(props) {
    const instance = vue.getCurrentInstance();
    const parent = instance.parent;
    if (!parent.filterPanels.value[props.column.id]) {
      parent.filterPanels.value[props.column.id] = instance;
    }
    const tooltipVisible = vue.ref(false);
    const tooltip = vue.ref(null);
    const filters = vue.computed(() => {
      return props.column && props.column.filters;
    });
    const filterValue = vue.computed({
      get: () => (props.column.filteredValue || [])[0],
      set: (value) => {
        if (filteredValue.value) {
          if (typeof value !== "undefined" && value !== null) {
            filteredValue.value.splice(0, 1, value);
          } else {
            filteredValue.value.splice(0, 1);
          }
        }
      }
    });
    const filteredValue = vue.computed({
      get() {
        if (props.column) {
          return props.column.filteredValue || [];
        }
        return [];
      },
      set(value) {
        if (props.column) {
          props.upDataColumn("filteredValue", value);
        }
      }
    });
    const multiple = vue.computed(() => {
      if (props.column) {
        return props.column.filterMultiple;
      }
      return true;
    });
    const isActive = (filter) => {
      return filter.value === filterValue.value;
    };
    const hidden = () => {
      tooltipVisible.value = false;
    };
    const showFilterPanel = (e) => {
      e.stopPropagation();
      tooltipVisible.value = !tooltipVisible.value;
    };
    const hideFilterPanel = () => {
      tooltipVisible.value = false;
    };
    const handleConfirm = () => {
      confirmFilter(filteredValue.value);
      hidden();
    };
    const handleReset = () => {
      filteredValue.value = [];
      confirmFilter(filteredValue.value);
      hidden();
    };
    const handleSelect = (_filterValue) => {
      filterValue.value = _filterValue;
      if (typeof _filterValue !== "undefined" && _filterValue !== null) {
        confirmFilter(filteredValue.value);
      } else {
        confirmFilter([]);
      }
      hidden();
    };
    const confirmFilter = (filteredValue2) => {
      props.store.commit("filterChange", {
        column: props.column,
        values: filteredValue2
      });
      props.store.updateAllSelected();
    };
    vue.watch(tooltipVisible, (value) => {
      if (props.column) {
        props.upDataColumn("filterOpened", value);
      }
    }, {
      immediate: true
    });
    const popperPaneRef = vue.computed(() => {
      var _a;
      return (_a = tooltip.value) == null ? void 0 : _a.popperRef;
    });
    return {
      tooltipVisible,
      multiple,
      filteredValue,
      filterValue,
      filters,
      handleConfirm,
      handleReset,
      handleSelect,
      isActive,
      t: locale.t,
      showFilterPanel,
      hideFilterPanel,
      popperPaneRef,
      tooltip
    };
  }
});

const _hoisted_1 = { key: 0 };
const _hoisted_2 = { class: "el-table-filter__content" };
const _hoisted_3 = { class: "el-table-filter__bottom" };
const _hoisted_4 = {
  key: 1,
  class: "el-table-filter__list"
};
function render(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_el_checkbox = vue.resolveComponent("el-checkbox");
  const _component_el_checkbox_group = vue.resolveComponent("el-checkbox-group");
  const _component_el_scrollbar = vue.resolveComponent("el-scrollbar");
  const _component_el_popper = vue.resolveComponent("el-popper");
  const _directive_click_outside = vue.resolveDirective("click-outside");
  return vue.openBlock(), vue.createBlock(_component_el_popper, {
    ref: "tooltip",
    visible: _ctx.tooltipVisible,
    "onUpdate:visible": _cache[6] || (_cache[6] = ($event) => _ctx.tooltipVisible = $event),
    offset: 0,
    placement: _ctx.placement,
    "show-arrow": false,
    "stop-popper-mouse-event": false,
    effect: "light",
    pure: "",
    "manual-mode": "",
    "popper-class": "el-table-filter",
    "append-to-body": ""
  }, {
    default: vue.withCtx(() => [
      _ctx.multiple ? (vue.openBlock(), vue.createBlock("div", _hoisted_1, [
        vue.createVNode("div", _hoisted_2, [
          vue.createVNode(_component_el_scrollbar, { "wrap-class": "el-table-filter__wrap" }, {
            default: vue.withCtx(() => [
              vue.createVNode(_component_el_checkbox_group, {
                modelValue: _ctx.filteredValue,
                "onUpdate:modelValue": _cache[1] || (_cache[1] = ($event) => _ctx.filteredValue = $event),
                class: "el-table-filter__checkbox-group"
              }, {
                default: vue.withCtx(() => [
                  (vue.openBlock(true), vue.createBlock(vue.Fragment, null, vue.renderList(_ctx.filters, (filter) => {
                    return vue.openBlock(), vue.createBlock(_component_el_checkbox, {
                      key: filter.value,
                      label: filter.value
                    }, {
                      default: vue.withCtx(() => [
                        vue.createTextVNode(vue.toDisplayString(filter.text), 1)
                      ]),
                      _: 2
                    }, 1032, ["label"]);
                  }), 128))
                ]),
                _: 1
              }, 8, ["modelValue"])
            ]),
            _: 1
          })
        ]),
        vue.createVNode("div", _hoisted_3, [
          vue.createVNode("button", {
            class: { "is-disabled": _ctx.filteredValue.length === 0 },
            disabled: _ctx.filteredValue.length === 0,
            type: "",
            onClick: _cache[2] || (_cache[2] = (...args) => _ctx.handleConfirm && _ctx.handleConfirm(...args))
          }, vue.toDisplayString(_ctx.t("el.table.confirmFilter")), 11, ["disabled"]),
          vue.createVNode("button", {
            type: "",
            onClick: _cache[3] || (_cache[3] = (...args) => _ctx.handleReset && _ctx.handleReset(...args))
          }, vue.toDisplayString(_ctx.t("el.table.resetFilter")), 1)
        ])
      ])) : (vue.openBlock(), vue.createBlock("ul", _hoisted_4, [
        vue.createVNode("li", {
          class: [{
            "is-active": _ctx.filterValue === void 0 || _ctx.filterValue === null
          }, "el-table-filter__list-item"],
          onClick: _cache[4] || (_cache[4] = ($event) => _ctx.handleSelect(null))
        }, vue.toDisplayString(_ctx.t("el.table.clearFilter")), 3),
        (vue.openBlock(true), vue.createBlock(vue.Fragment, null, vue.renderList(_ctx.filters, (filter) => {
          return vue.openBlock(), vue.createBlock("li", {
            key: filter.value,
            class: [{ "is-active": _ctx.isActive(filter) }, "el-table-filter__list-item"],
            label: filter.value,
            onClick: ($event) => _ctx.handleSelect(filter.value)
          }, vue.toDisplayString(filter.text), 11, ["label", "onClick"]);
        }), 128))
      ]))
    ]),
    trigger: vue.withCtx(() => [
      vue.withDirectives(vue.createVNode("span", {
        class: "el-table__column-filter-trigger el-none-outline",
        onClick: _cache[5] || (_cache[5] = (...args) => _ctx.showFilterPanel && _ctx.showFilterPanel(...args))
      }, [
        vue.createVNode("i", {
          class: [
            "el-icon-arrow-down",
            _ctx.column.filterOpened ? "el-icon-arrow-up" : ""
          ]
        }, null, 2)
      ], 512), [
        [_directive_click_outside, _ctx.hideFilterPanel, _ctx.popperPaneRef]
      ])
    ]),
    _: 1
  }, 8, ["visible", "placement"]);
}

script.render = render;
script.__file = "packages/table/src/filter-panel.vue";

function useLayoutObserver(root) {
  const instance = vue.getCurrentInstance();
  vue.onBeforeMount(() => {
    tableLayout.value.addObserver(instance);
  });
  vue.onMounted(() => {
    onColumnsChange(tableLayout.value);
    onScrollableChange(tableLayout.value);
  });
  vue.onUpdated(() => {
    onColumnsChange(tableLayout.value);
    onScrollableChange(tableLayout.value);
  });
  vue.onUnmounted(() => {
    tableLayout.value.removeObserver(instance);
  });
  const tableLayout = vue.computed(() => {
    const layout = root.layout;
    if (!layout) {
      throw new Error("Can not find table layout.");
    }
    return layout;
  });
  const onColumnsChange = (layout) => {
    var _a;
    const cols = ((_a = root.vnode.el) == null ? void 0 : _a.querySelectorAll("colgroup > col")) || [];
    if (!cols.length)
      return;
    const flattenColumns = layout.getFlattenColumns();
    const columnsMap = {};
    flattenColumns.forEach((column) => {
      columnsMap[column.id] = column;
    });
    for (let i = 0, j = cols.length; i < j; i++) {
      const col = cols[i];
      const name = col.getAttribute("name");
      const column = columnsMap[name];
      if (column) {
        col.setAttribute("width", column.realWidth || column.width);
      }
    }
  };
  const onScrollableChange = (layout) => {
    const cols = root.vnode.el.querySelectorAll("colgroup > col[name=gutter]");
    for (let i = 0, j = cols.length; i < j; i++) {
      const col = cols[i];
      col.setAttribute("width", layout.scrollY.value ? layout.gutterWidth : "0");
    }
    const ths = root.vnode.el.querySelectorAll("th.gutter");
    for (let i = 0, j = ths.length; i < j; i++) {
      const th = ths[i];
      th.style.width = layout.scrollY.value ? layout.gutterWidth + "px" : "0";
      th.style.display = layout.scrollY.value ? "" : "none";
    }
  };
  return {
    tableLayout: tableLayout.value,
    onColumnsChange,
    onScrollableChange
  };
}

function useEvent(props, emit) {
  const instance = vue.getCurrentInstance();
  const parent = instance.parent;
  const handleFilterClick = (event) => {
    event.stopPropagation();
    return;
  };
  const handleHeaderClick = (event, column) => {
    if (!column.filters && column.sortable) {
      handleSortClick(event, column, false);
    } else if (column.filterable && !column.sortable) {
      handleFilterClick(event);
    }
    parent.emit("header-click", column, event);
  };
  const handleHeaderContextMenu = (event, column) => {
    parent.emit("header-contextmenu", column, event);
  };
  const draggingColumn = vue.ref(null);
  const dragging = vue.ref(false);
  const dragState = vue.ref({});
  const handleMouseDown = (event, column) => {
    if (isServer__default['default'])
      return;
    if (column.children && column.children.length > 0)
      return;
    if (draggingColumn.value && props.border) {
      dragging.value = true;
      const table = parent;
      emit("set-drag-visible", true);
      const tableEl = table.vnode.el;
      const tableLeft = tableEl.getBoundingClientRect().left;
      const columnEl = instance.vnode.el.querySelector(`th.${column.id}`);
      const columnRect = columnEl.getBoundingClientRect();
      const minLeft = columnRect.left - tableLeft + 30;
      dom.addClass(columnEl, "noclick");
      dragState.value = {
        startMouseLeft: event.clientX,
        startLeft: columnRect.right - tableLeft,
        startColumnLeft: columnRect.left - tableLeft,
        tableLeft
      };
      const resizeProxy = table.refs.resizeProxy;
      resizeProxy.style.left = dragState.value.startLeft + "px";
      document.onselectstart = function() {
        return false;
      };
      document.ondragstart = function() {
        return false;
      };
      const handleMouseMove2 = (event2) => {
        const deltaLeft = event2.clientX - dragState.value.startMouseLeft;
        const proxyLeft = dragState.value.startLeft + deltaLeft;
        resizeProxy.style.left = Math.max(minLeft, proxyLeft) + "px";
      };
      const handleMouseUp = () => {
        if (dragging.value) {
          const { startColumnLeft, startLeft } = dragState.value;
          const finalLeft = parseInt(resizeProxy.style.left, 10);
          const columnWidth = finalLeft - startColumnLeft;
          column.width = column.realWidth = columnWidth;
          table.emit("header-dragend", column.width, startLeft - startColumnLeft, column, event);
          props.store.scheduleLayout(false, true);
          document.body.style.cursor = "";
          dragging.value = false;
          draggingColumn.value = null;
          dragState.value = {};
          emit("set-drag-visible", false);
        }
        document.removeEventListener("mousemove", handleMouseMove2);
        document.removeEventListener("mouseup", handleMouseUp);
        document.onselectstart = null;
        document.ondragstart = null;
        setTimeout(function() {
          dom.removeClass(columnEl, "noclick");
        }, 0);
      };
      document.addEventListener("mousemove", handleMouseMove2);
      document.addEventListener("mouseup", handleMouseUp);
    }
  };
  const handleMouseMove = (event, column) => {
    if (column.children && column.children.length > 0)
      return;
    let target = event.target;
    while (target && target.tagName !== "TH") {
      target = target.parentNode;
    }
    if (!column || !column.resizable)
      return;
    if (!dragging.value && props.border) {
      const rect = target.getBoundingClientRect();
      const bodyStyle = document.body.style;
      if (rect.width > 12 && rect.right - event.pageX < 8) {
        bodyStyle.cursor = "col-resize";
        if (dom.hasClass(target, "is-sortable")) {
          target.style.cursor = "col-resize";
        }
        draggingColumn.value = column;
      } else if (!dragging.value) {
        bodyStyle.cursor = "";
        if (dom.hasClass(target, "is-sortable")) {
          target.style.cursor = "pointer";
        }
        draggingColumn.value = null;
      }
    }
  };
  const handleMouseOut = () => {
    if (isServer__default['default'])
      return;
    document.body.style.cursor = "";
  };
  const toggleOrder = ({ order, sortOrders }) => {
    if (order === "")
      return sortOrders[0];
    const index = sortOrders.indexOf(order || null);
    return sortOrders[index > sortOrders.length - 2 ? 0 : index + 1];
  };
  const handleSortClick = (event, column, givenOrder) => {
    event.stopPropagation();
    const order = column.order === givenOrder ? null : givenOrder || toggleOrder(column);
    let target = event.target;
    while (target && target.tagName !== "TH") {
      target = target.parentNode;
    }
    if (target && target.tagName === "TH") {
      if (dom.hasClass(target, "noclick")) {
        dom.removeClass(target, "noclick");
        return;
      }
    }
    if (!column.sortable)
      return;
    const states = props.store.states;
    let sortProp = states.sortProp.value;
    let sortOrder;
    const sortingColumn = states.sortingColumn.value;
    if (sortingColumn !== column || sortingColumn === column && sortingColumn.order === null) {
      if (sortingColumn) {
        sortingColumn.order = null;
      }
      states.sortingColumn.value = column;
      sortProp = column.property;
    }
    if (!order) {
      sortOrder = column.order = null;
    } else {
      sortOrder = column.order = order;
    }
    states.sortProp.value = sortProp;
    states.sortOrder.value = sortOrder;
    parent.store.commit("changeSortCondition");
  };
  return {
    handleHeaderClick,
    handleHeaderContextMenu,
    handleMouseDown,
    handleMouseMove,
    handleMouseOut,
    handleSortClick,
    handleFilterClick
  };
}

function useStyle(props) {
  const instance = vue.getCurrentInstance();
  const parent = instance.parent;
  const storeData = parent.store.states;
  const isCellHidden = (index, columns) => {
    let start = 0;
    for (let i = 0; i < index; i++) {
      start += columns[i].colSpan;
    }
    const after = start + columns[index].colSpan - 1;
    if (props.fixed === "left") {
      return after >= storeData.fixedLeafColumnsLength.value;
    } else if (props.fixed === "right") {
      return start < storeData.columns.value.length - storeData.rightFixedLeafColumnsLength.value;
    } else {
      return after < storeData.fixedLeafColumnsLength.value || start >= storeData.columns.value.length - storeData.rightFixedLeafColumnsLength.value;
    }
  };
  const getHeaderRowStyle = (rowIndex) => {
    const headerRowStyle = parent.props.headerRowStyle;
    if (typeof headerRowStyle === "function") {
      return headerRowStyle.call(null, { rowIndex });
    }
    return headerRowStyle;
  };
  const getHeaderRowClass = (rowIndex) => {
    const classes = [];
    const headerRowClassName = parent.props.headerRowClassName;
    if (typeof headerRowClassName === "string") {
      classes.push(headerRowClassName);
    } else if (typeof headerRowClassName === "function") {
      classes.push(headerRowClassName.call(null, { rowIndex }));
    }
    return classes.join(" ");
  };
  const getHeaderCellStyle = (rowIndex, columnIndex, row, column) => {
    const headerCellStyle = parent.props.headerCellStyle;
    if (typeof headerCellStyle === "function") {
      return headerCellStyle.call(null, {
        rowIndex,
        columnIndex,
        row,
        column
      });
    }
    return headerCellStyle;
  };
  const getHeaderCellClass = (rowIndex, columnIndex, row, column) => {
    const classes = [
      column.id,
      column.order,
      column.headerAlign,
      column.className,
      column.labelClassName
    ];
    if (rowIndex === 0 && isCellHidden(columnIndex, row)) {
      classes.push("is-hidden");
    }
    if (!column.children) {
      classes.push("is-leaf");
    }
    if (column.sortable) {
      classes.push("is-sortable");
    }
    const headerCellClassName = parent.props.headerCellClassName;
    if (typeof headerCellClassName === "string") {
      classes.push(headerCellClassName);
    } else if (typeof headerCellClassName === "function") {
      classes.push(headerCellClassName.call(null, {
        rowIndex,
        columnIndex,
        row,
        column
      }));
    }
    return classes.join(" ");
  };
  return {
    getHeaderRowStyle,
    getHeaderRowClass,
    getHeaderCellStyle,
    getHeaderCellClass
  };
}

const getAllColumns = (columns) => {
  const result = [];
  columns.forEach((column) => {
    if (column.children) {
      result.push(column);
      result.push.apply(result, getAllColumns(column.children));
    } else {
      result.push(column);
    }
  });
  return result;
};
const convertToRows = (originColumns) => {
  let maxLevel = 1;
  const traverse = (column, parent) => {
    if (parent) {
      column.level = parent.level + 1;
      if (maxLevel < column.level) {
        maxLevel = column.level;
      }
    }
    if (column.children) {
      let colSpan = 0;
      column.children.forEach((subColumn) => {
        traverse(subColumn, column);
        colSpan += subColumn.colSpan;
      });
      column.colSpan = colSpan;
    } else {
      column.colSpan = 1;
    }
  };
  originColumns.forEach((column) => {
    column.level = 1;
    traverse(column, void 0);
  });
  const rows = [];
  for (let i = 0; i < maxLevel; i++) {
    rows.push([]);
  }
  const allColumns = getAllColumns(originColumns);
  allColumns.forEach((column) => {
    if (!column.children) {
      column.rowSpan = maxLevel - column.level + 1;
    } else {
      column.rowSpan = 1;
    }
    rows[column.level - 1].push(column);
  });
  return rows;
};
function useUtils(props) {
  const instance = vue.getCurrentInstance();
  const parent = instance.parent;
  const columnRows = vue.computed(() => {
    return convertToRows(props.store.states.originColumns.value);
  });
  const isGroup = vue.computed(() => {
    const result = columnRows.value.length > 1;
    if (result)
      parent.state.isGroup.value = true;
    return result;
  });
  const toggleAllSelection = (event) => {
    event.stopPropagation();
    parent.store.commit("toggleAllSelection");
  };
  return {
    isGroup,
    toggleAllSelection,
    columnRows
  };
}

function hGutter() {
  return vue.h("col", {
    name: "gutter"
  });
}
function hColgroup(columns, hasGutter = false) {
  return vue.h("colgroup", {}, [
    ...columns.map((column) => vue.h("col", {
      name: column.id,
      key: column.id
    })),
    hasGutter && hGutter()
  ]);
}

var TableHeader = vue.defineComponent({
  name: "ElTableHeader",
  components: {
    ElCheckbox: ElCheckbox__default['default']
  },
  props: {
    fixed: {
      type: String,
      default: ""
    },
    store: {
      required: true,
      type: Object
    },
    border: Boolean,
    defaultSort: {
      type: Object,
      default: () => {
        return {
          prop: "",
          order: ""
        };
      }
    }
  },
  setup(props, { emit }) {
    const instance = vue.getCurrentInstance();
    const parent = instance.parent;
    const storeData = parent.store.states;
    const filterPanels = vue.ref({});
    const {
      tableLayout,
      onColumnsChange,
      onScrollableChange
    } = useLayoutObserver(parent);
    const hasGutter = vue.computed(() => {
      return !props.fixed && tableLayout.gutterWidth;
    });
    vue.onMounted(() => {
      vue.nextTick(() => {
        const { prop, order } = props.defaultSort;
        const init = true;
        parent.store.commit("sort", { prop, order, init });
      });
    });
    const {
      handleHeaderClick,
      handleHeaderContextMenu,
      handleMouseDown,
      handleMouseMove,
      handleMouseOut,
      handleSortClick,
      handleFilterClick
    } = useEvent(props, emit);
    const {
      getHeaderRowStyle,
      getHeaderRowClass,
      getHeaderCellStyle,
      getHeaderCellClass
    } = useStyle(props);
    const { isGroup, toggleAllSelection, columnRows } = useUtils(props);
    instance.state = {
      onColumnsChange,
      onScrollableChange
    };
    instance.filterPanels = filterPanels;
    return {
      columns: storeData.columns,
      filterPanels,
      hasGutter,
      onColumnsChange,
      onScrollableChange,
      columnRows,
      getHeaderRowClass,
      getHeaderRowStyle,
      getHeaderCellClass,
      getHeaderCellStyle,
      handleHeaderClick,
      handleHeaderContextMenu,
      handleMouseDown,
      handleMouseMove,
      handleMouseOut,
      handleSortClick,
      handleFilterClick,
      isGroup,
      toggleAllSelection
    };
  },
  render() {
    return vue.h("table", {
      border: "0",
      cellpadding: "0",
      cellspacing: "0",
      class: "el-table__header"
    }, [
      hColgroup(this.columns, this.hasGutter),
      vue.h("thead", {
        class: { "is-group": this.isGroup, "has-gutter": this.hasGutter }
      }, this.columnRows.map((subColumns, rowIndex) => vue.h("tr", {
        class: this.getHeaderRowClass(rowIndex),
        key: rowIndex,
        style: this.getHeaderRowStyle(rowIndex)
      }, subColumns.map((column, cellIndex) => vue.h("th", {
        class: this.getHeaderCellClass(rowIndex, cellIndex, subColumns, column),
        colspan: column.colSpan,
        key: `${column.id}-thead`,
        rowSpan: column.rowSpan,
        style: this.getHeaderCellStyle(rowIndex, cellIndex, subColumns, column),
        onClick: ($event) => this.handleHeaderClick($event, column),
        onContextmenu: ($event) => this.handleHeaderContextMenu($event, column),
        onMousedown: ($event) => this.handleMouseDown($event, column),
        onMousemove: ($event) => this.handleMouseMove($event, column),
        onMouseout: this.handleMouseOut
      }, [
        vue.h("div", {
          class: [
            "cell",
            column.filteredValue && column.filteredValue.length > 0 ? "highlight" : "",
            column.labelClassName
          ]
        }, [
          column.renderHeader ? column.renderHeader({
            column,
            $index: cellIndex,
            store: this.store,
            _self: this.$parent
          }) : column.label,
          column.sortable && vue.h("span", {
            onClick: ($event) => this.handleSortClick($event, column),
            class: "caret-wrapper"
          }, [
            vue.h("i", {
              onClick: ($event) => this.handleSortClick($event, column, "ascending"),
              class: "sort-caret ascending"
            }),
            vue.h("i", {
              onClick: ($event) => this.handleSortClick($event, column, "descending"),
              class: "sort-caret descending"
            })
          ]),
          column.filterable && vue.h(script, {
            store: this.$parent.store,
            placement: column.filterPlacement || "bottom-start",
            column,
            upDataColumn: (key, value) => {
              column[key] = value;
            }
          })
        ])
      ])))))
    ]);
  }
});

function useEvents(props) {
  const instance = vue.getCurrentInstance();
  const parent = instance.parent;
  const tooltipContent = vue.ref("");
  const tooltipTrigger = vue.ref(vue.h("div"));
  const handleEvent = (event, row, name) => {
    const table = parent;
    const cell = getCell(event);
    let column;
    if (cell) {
      column = getColumnByCell({
        columns: props.store.states.columns.value
      }, cell);
      if (column) {
        table.emit(`cell-${name}`, row, column, cell, event);
      }
    }
    table.emit(`row-${name}`, row, column, event);
  };
  const handleDoubleClick = (event, row) => {
    handleEvent(event, row, "dblclick");
  };
  const handleClick = (event, row) => {
    props.store.commit("setCurrentRow", row);
    handleEvent(event, row, "click");
  };
  const handleContextMenu = (event, row) => {
    handleEvent(event, row, "contextmenu");
  };
  const handleMouseEnter = debounce__default['default'](function(index) {
    props.store.commit("setHoverRow", index);
  }, 30);
  const handleMouseLeave = debounce__default['default'](function() {
    props.store.commit("setHoverRow", null);
  }, 30);
  const handleCellMouseEnter = (event, row) => {
    const table = parent;
    const cell = getCell(event);
    if (cell) {
      const column = getColumnByCell({
        columns: props.store.states.columns.value
      }, cell);
      const hoverState = table.hoverState = { cell, column, row };
      table.emit("cell-mouse-enter", hoverState.row, hoverState.column, hoverState.cell, event);
    }
    const cellChild = event.target.querySelector(".cell");
    if (!(dom.hasClass(cellChild, "el-tooltip") && cellChild.childNodes.length)) {
      return;
    }
    const range = document.createRange();
    range.setStart(cellChild, 0);
    range.setEnd(cellChild, cellChild.childNodes.length);
    const rangeWidth = range.getBoundingClientRect().width;
    const padding = (parseInt(dom.getStyle(cellChild, "paddingLeft"), 10) || 0) + (parseInt(dom.getStyle(cellChild, "paddingRight"), 10) || 0);
    if (rangeWidth + padding > cellChild.offsetWidth || cellChild.scrollWidth > cellChild.offsetWidth) {
      createTablePopper(cell, cell.innerText || cell.textContent, {
        placement: "top",
        strategy: "fixed"
      }, row.tooltipEffect);
    }
  };
  const handleCellMouseLeave = (event) => {
    const cell = getCell(event);
    if (!cell)
      return;
    const oldHoverState = parent.hoverState;
    parent.emit("cell-mouse-leave", oldHoverState == null ? void 0 : oldHoverState.row, oldHoverState == null ? void 0 : oldHoverState.column, oldHoverState == null ? void 0 : oldHoverState.cell, event);
  };
  return {
    handleDoubleClick,
    handleClick,
    handleContextMenu,
    handleMouseEnter,
    handleMouseLeave,
    handleCellMouseEnter,
    handleCellMouseLeave,
    tooltipContent,
    tooltipTrigger
  };
}

function useStyles(props) {
  const instance = vue.getCurrentInstance();
  const parent = instance.parent;
  const isColumnHidden = (index) => {
    if (props.fixed === "left") {
      return index >= props.store.states.fixedLeafColumnsLength.value;
    } else if (props.fixed === "right") {
      return index < props.store.states.columns.value.length - props.store.states.rightFixedLeafColumnsLength.value;
    } else {
      return index < props.store.states.fixedLeafColumnsLength.value || index >= props.store.states.columns.value.length - props.store.states.rightFixedLeafColumnsLength.value;
    }
  };
  const getRowStyle = (row, rowIndex) => {
    const rowStyle = parent.props.rowStyle;
    if (typeof rowStyle === "function") {
      return rowStyle.call(null, {
        row,
        rowIndex
      });
    }
    return rowStyle || null;
  };
  const getRowClass = (row, rowIndex) => {
    const classes = ["el-table__row"];
    if (parent.props.highlightCurrentRow && row === props.store.states.currentRow.value) {
      classes.push("current-row");
    }
    if (props.stripe && rowIndex % 2 === 1) {
      classes.push("el-table__row--striped");
    }
    const rowClassName = parent.props.rowClassName;
    if (typeof rowClassName === "string") {
      classes.push(rowClassName);
    } else if (typeof rowClassName === "function") {
      classes.push(rowClassName.call(null, {
        row,
        rowIndex
      }));
    }
    if (props.store.states.expandRows.value.indexOf(row) > -1) {
      classes.push("expanded");
    }
    return classes;
  };
  const getCellStyle = (rowIndex, columnIndex, row, column) => {
    const cellStyle = parent.props.cellStyle;
    if (typeof cellStyle === "function") {
      return cellStyle.call(null, {
        rowIndex,
        columnIndex,
        row,
        column
      });
    }
    return cellStyle;
  };
  const getCellClass = (rowIndex, columnIndex, row, column) => {
    const classes = [column.id, column.align, column.className];
    if (isColumnHidden(columnIndex)) {
      classes.push("is-hidden");
    }
    const cellClassName = parent.props.cellClassName;
    if (typeof cellClassName === "string") {
      classes.push(cellClassName);
    } else if (typeof cellClassName === "function") {
      classes.push(cellClassName.call(null, {
        rowIndex,
        columnIndex,
        row,
        column
      }));
    }
    return classes.join(" ");
  };
  const getSpan = (row, column, rowIndex, columnIndex) => {
    let rowspan = 1;
    let colspan = 1;
    const fn = parent.props.spanMethod;
    if (typeof fn === "function") {
      const result = fn({
        row,
        column,
        rowIndex,
        columnIndex
      });
      if (Array.isArray(result)) {
        rowspan = result[0];
        colspan = result[1];
      } else if (typeof result === "object") {
        rowspan = result.rowspan;
        colspan = result.colspan;
      }
    }
    return { rowspan, colspan };
  };
  const getColspanRealWidth = (columns, colspan, index) => {
    if (colspan < 1) {
      return columns[index].realWidth;
    }
    const widthArr = columns.map(({ realWidth, width }) => realWidth || width).slice(index, index + colspan);
    return Number(widthArr.reduce((acc, width) => Number(acc) + Number(width), -1));
  };
  return {
    getRowStyle,
    getRowClass,
    getCellStyle,
    getCellClass,
    getSpan,
    getColspanRealWidth,
    isColumnHidden
  };
}

var __defProp$4 = Object.defineProperty;
var __defProps$1 = Object.defineProperties;
var __getOwnPropDescs$1 = Object.getOwnPropertyDescriptors;
var __getOwnPropSymbols$4 = Object.getOwnPropertySymbols;
var __hasOwnProp$4 = Object.prototype.hasOwnProperty;
var __propIsEnum$4 = Object.prototype.propertyIsEnumerable;
var __defNormalProp$4 = (obj, key, value) => key in obj ? __defProp$4(obj, key, { enumerable: true, configurable: true, writable: true, value }) : obj[key] = value;
var __spreadValues$4 = (a, b) => {
  for (var prop in b || (b = {}))
    if (__hasOwnProp$4.call(b, prop))
      __defNormalProp$4(a, prop, b[prop]);
  if (__getOwnPropSymbols$4)
    for (var prop of __getOwnPropSymbols$4(b)) {
      if (__propIsEnum$4.call(b, prop))
        __defNormalProp$4(a, prop, b[prop]);
    }
  return a;
};
var __spreadProps$1 = (a, b) => __defProps$1(a, __getOwnPropDescs$1(b));
function useRender(props) {
  const instance = vue.getCurrentInstance();
  const parent = instance.parent;
  const {
    handleDoubleClick,
    handleClick,
    handleContextMenu,
    handleMouseEnter,
    handleMouseLeave,
    handleCellMouseEnter,
    handleCellMouseLeave,
    tooltipContent,
    tooltipTrigger
  } = useEvents(props);
  const {
    getRowStyle,
    getRowClass,
    getCellStyle,
    getCellClass,
    getSpan,
    getColspanRealWidth
  } = useStyles(props);
  const firstDefaultColumnIndex = vue.computed(() => {
    return util.arrayFindIndex(props.store.states.columns.value, ({ type }) => type === "default");
  });
  const getKeyOfRow = (row, index) => {
    const rowKey = parent.props.rowKey;
    if (rowKey) {
      return getRowIdentity(row, rowKey);
    }
    return index;
  };
  const rowRender = (row, $index, treeRowData) => {
    const { tooltipEffect, store } = props;
    const { indent, columns } = store.states;
    const rowClasses = getRowClass(row, $index);
    let display = true;
    if (treeRowData) {
      rowClasses.push("el-table__row--level-" + treeRowData.level);
      display = treeRowData.display;
    }
    const displayStyle = display ? null : {
      display: "none"
    };
    return vue.h("tr", {
      style: [displayStyle, getRowStyle(row, $index)],
      class: rowClasses,
      key: getKeyOfRow(row, $index),
      onDblclick: ($event) => handleDoubleClick($event, row),
      onClick: ($event) => handleClick($event, row),
      onContextmenu: ($event) => handleContextMenu($event, row),
      onMouseenter: () => handleMouseEnter($index),
      onMouseleave: handleMouseLeave
    }, columns.value.map((column, cellIndex) => {
      const { rowspan, colspan } = getSpan(row, column, $index, cellIndex);
      if (!rowspan || !colspan) {
        return null;
      }
      const columnData = __spreadValues$4({}, column);
      columnData.realWidth = getColspanRealWidth(columns.value, colspan, cellIndex);
      const data = {
        store: props.store,
        _self: props.context || parent,
        column: columnData,
        row,
        $index
      };
      if (cellIndex === firstDefaultColumnIndex.value && treeRowData) {
        data.treeNode = {
          indent: treeRowData.level * indent.value,
          level: treeRowData.level
        };
        if (typeof treeRowData.expanded === "boolean") {
          data.treeNode.expanded = treeRowData.expanded;
          if ("loading" in treeRowData) {
            data.treeNode.loading = treeRowData.loading;
          }
          if ("noLazyChildren" in treeRowData) {
            data.treeNode.noLazyChildren = treeRowData.noLazyChildren;
          }
        }
      }
      const baseKey = `${$index},${cellIndex}`;
      const patchKey = columnData.columnKey || columnData.rawColumnKey || "";
      return vue.h("td", {
        style: getCellStyle($index, cellIndex, row, column),
        class: getCellClass($index, cellIndex, row, column),
        key: `${patchKey}${baseKey}`,
        rowspan,
        colspan,
        onMouseenter: ($event) => handleCellMouseEnter($event, __spreadProps$1(__spreadValues$4({}, row), { tooltipEffect })),
        onMouseleave: handleCellMouseLeave
      }, [column.renderCell(data)]);
    }));
  };
  const wrappedRowRender = (row, $index) => {
    const store = props.store;
    const { isRowExpanded, assertRowKey } = store;
    const {
      treeData,
      lazyTreeNodeMap,
      childrenColumnName,
      rowKey
    } = store.states;
    const hasExpandColumn = store.states.columns.value.some(({ type }) => type === "expand");
    if (hasExpandColumn && isRowExpanded(row)) {
      const renderExpanded = parent.renderExpanded;
      const tr = rowRender(row, $index, void 0);
      if (!renderExpanded) {
        console.error("[Element Error]renderExpanded is required.");
        return tr;
      }
      return [
        tr,
        vue.h("tr", {
          key: "expanded-row__" + tr.key
        }, [
          vue.h("td", {
            colspan: store.states.columns.value.length,
            class: "el-table__expanded-cell"
          }, [renderExpanded({ row, $index, store })])
        ])
      ];
    } else if (Object.keys(treeData.value).length) {
      assertRowKey();
      const key = getRowIdentity(row, rowKey.value);
      let cur = treeData.value[key];
      let treeRowData = null;
      if (cur) {
        treeRowData = {
          expanded: cur.expanded,
          level: cur.level,
          display: true
        };
        if (typeof cur.lazy === "boolean") {
          if (typeof cur.loaded === "boolean" && cur.loaded) {
            treeRowData.noLazyChildren = !(cur.children && cur.children.length);
          }
          treeRowData.loading = cur.loading;
        }
      }
      const tmp = [rowRender(row, $index, treeRowData)];
      if (cur) {
        let i = 0;
        const traverse = (children, parent2) => {
          if (!(children && children.length && parent2))
            return;
          children.forEach((node) => {
            const innerTreeRowData = {
              display: parent2.display && parent2.expanded,
              level: parent2.level + 1,
              expanded: false,
              noLazyChildren: false,
              loading: false
            };
            const childKey = getRowIdentity(node, rowKey.value);
            if (childKey === void 0 || childKey === null) {
              throw new Error("for nested data item, row-key is required.");
            }
            cur = __spreadValues$4({}, treeData.value[childKey]);
            if (cur) {
              innerTreeRowData.expanded = cur.expanded;
              cur.level = cur.level || innerTreeRowData.level;
              cur.display = !!(cur.expanded && innerTreeRowData.display);
              if (typeof cur.lazy === "boolean") {
                if (typeof cur.loaded === "boolean" && cur.loaded) {
                  innerTreeRowData.noLazyChildren = !(cur.children && cur.children.length);
                }
                innerTreeRowData.loading = cur.loading;
              }
            }
            i++;
            tmp.push(rowRender(node, $index + i, innerTreeRowData));
            if (cur) {
              const nodes2 = lazyTreeNodeMap.value[childKey] || node[childrenColumnName.value];
              traverse(nodes2, cur);
            }
          });
        };
        cur.display = true;
        const nodes = lazyTreeNodeMap.value[key] || row[childrenColumnName.value];
        traverse(nodes, cur);
      }
      return tmp;
    } else {
      return rowRender(row, $index, void 0);
    }
  };
  return {
    wrappedRowRender,
    tooltipContent,
    tooltipTrigger
  };
}

const defaultProps = {
  store: {
    required: true,
    type: Object
  },
  stripe: Boolean,
  tooltipEffect: String,
  context: {
    default: () => ({}),
    type: Object
  },
  rowClassName: [String, Function],
  rowStyle: [Object, Function],
  fixed: {
    type: String,
    default: ""
  },
  highlight: Boolean
};

var TableBody = vue.defineComponent({
  name: "ElTableBody",
  props: defaultProps,
  setup(props) {
    const instance = vue.getCurrentInstance();
    const parent = instance.parent;
    const { wrappedRowRender, tooltipContent, tooltipTrigger } = useRender(props);
    const { onColumnsChange, onScrollableChange } = useLayoutObserver(parent);
    vue.watch(props.store.states.hoverRow, (newVal, oldVal) => {
      if (!props.store.states.isComplex.value || isServer__default['default'])
        return;
      let raf = window.requestAnimationFrame;
      if (!raf) {
        raf = (fn) => window.setTimeout(fn, 16);
      }
      raf(() => {
        const rows = instance.vnode.el.querySelectorAll(".el-table__row");
        const oldRow = rows[oldVal];
        const newRow = rows[newVal];
        if (oldRow) {
          dom.removeClass(oldRow, "hover-row");
        }
        if (newRow) {
          dom.addClass(newRow, "hover-row");
        }
      });
    });
    vue.onUnmounted(() => {
      var _a;
      (_a = removePopper) == null ? void 0 : _a();
    });
    vue.onUpdated(() => {
      var _a;
      (_a = removePopper) == null ? void 0 : _a();
    });
    return {
      onColumnsChange,
      onScrollableChange,
      wrappedRowRender,
      tooltipContent,
      tooltipTrigger
    };
  },
  render() {
    const data = this.store.states.data.value || [];
    return vue.h("table", {
      class: "el-table__body",
      cellspacing: "0",
      cellpadding: "0",
      border: "0"
    }, [
      hColgroup(this.store.states.columns.value),
      vue.h("tbody", {}, [
        data.reduce((acc, row) => {
          return acc.concat(this.wrappedRowRender(row, acc.filter((item) => typeof item.key === "number").length));
        }, [])
      ])
    ]);
  }
});

function useMapState() {
  const instance = vue.getCurrentInstance();
  const table = instance.parent;
  const store = table.store;
  const leftFixedLeafCount = vue.computed(() => {
    return store.states.fixedLeafColumnsLength.value;
  });
  const rightFixedLeafCount = vue.computed(() => {
    return store.states.rightFixedColumns.value.length;
  });
  const columnsCount = vue.computed(() => {
    return store.states.columns.value.length;
  });
  const leftFixedCount = vue.computed(() => {
    return store.states.fixedColumns.value.length;
  });
  const rightFixedCount = vue.computed(() => {
    return store.states.rightFixedColumns.value.length;
  });
  return {
    leftFixedLeafCount,
    rightFixedLeafCount,
    columnsCount,
    leftFixedCount,
    rightFixedCount,
    columns: store.states.columns
  };
}

function useStyle$1(props) {
  const instance = vue.getCurrentInstance();
  const table = instance.parent;
  const store = table.store;
  const {
    leftFixedLeafCount,
    rightFixedLeafCount,
    columnsCount,
    leftFixedCount,
    rightFixedCount,
    columns
  } = useMapState();
  const hasGutter = vue.computed(() => {
    return !props.fixed && table.layout.gutterWidth;
  });
  const isCellHidden = (index, columns2, column) => {
    if (props.fixed || props.fixed === "left") {
      return index >= leftFixedLeafCount.value;
    } else if (props.fixed === "right") {
      let before = 0;
      for (let i = 0; i < index; i++) {
        before += columns2[i].colSpan;
      }
      return before < columnsCount.value - rightFixedLeafCount.value;
    } else if (!props.fixed && column.fixed) {
      return true;
    } else {
      return index < leftFixedCount.value || index >= columnsCount.value - rightFixedCount.value;
    }
  };
  const getRowClasses = (column, cellIndex) => {
    const classes = [column.id, column.align, column.labelClassName];
    if (column.className) {
      classes.push(column.className);
    }
    if (isCellHidden(cellIndex, store.states.columns.value, column)) {
      classes.push("is-hidden");
    }
    if (!column.children) {
      classes.push("is-leaf");
    }
    return classes;
  };
  return {
    hasGutter,
    getRowClasses,
    columns
  };
}

var TableFooter = vue.defineComponent({
  name: "ElTableFooter",
  props: {
    fixed: {
      type: String,
      default: ""
    },
    store: {
      required: true,
      type: Object
    },
    summaryMethod: Function,
    sumText: String,
    border: Boolean,
    defaultSort: {
      type: Object,
      default: () => {
        return {
          prop: "",
          order: ""
        };
      }
    }
  },
  setup(props) {
    const { hasGutter, getRowClasses, columns } = useStyle$1(props);
    return {
      getRowClasses,
      hasGutter,
      columns
    };
  },
  render() {
    let sums = [];
    if (this.summaryMethod) {
      sums = this.summaryMethod({
        columns: this.columns,
        data: this.store.states.data.value
      });
    } else {
      this.columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = this.sumText;
          return;
        }
        const values = this.store.states.data.value.map((item) => Number(item[column.property]));
        const precisions = [];
        let notNumber = true;
        values.forEach((value) => {
          if (!isNaN(value)) {
            notNumber = false;
            const decimal = ("" + value).split(".")[1];
            precisions.push(decimal ? decimal.length : 0);
          }
        });
        const precision = Math.max.apply(null, precisions);
        if (!notNumber) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return parseFloat((prev + curr).toFixed(Math.min(precision, 20)));
            } else {
              return prev;
            }
          }, 0);
        } else {
          sums[index] = "";
        }
      });
    }
    return vue.h("table", {
      class: "el-table__footer",
      cellspacing: "0",
      cellpadding: "0",
      border: "0"
    }, [
      hColgroup(this.columns, this.hasGutter),
      vue.h("tbody", {
        class: [{ "has-gutter": this.hasGutter }]
      }, [
        vue.h("tr", {}, [
          ...this.columns.map((column, cellIndex) => vue.h("td", {
            key: cellIndex,
            colspan: column.colSpan,
            rowspan: column.rowSpan,
            class: this.getRowClasses(column, cellIndex)
          }, [
            vue.h("div", {
              class: ["cell", column.labelClassName]
            }, [sums[cellIndex]])
          ])),
          this.hasGutter && hGutter()
        ])
      ])
    ]);
  }
});

function useUtils$1(store) {
  const setCurrentRow = (row) => {
    store.commit("setCurrentRow", row);
  };
  const toggleRowSelection = (row, selected) => {
    store.toggleRowSelection(row, selected, false);
    store.updateAllSelected();
  };
  const clearSelection = () => {
    store.clearSelection();
  };
  const clearFilter = (columnKeys) => {
    store.clearFilter(columnKeys);
  };
  const toggleAllSelection = () => {
    store.commit("toggleAllSelection");
  };
  const toggleRowExpansion = (row, expanded) => {
    store.toggleRowExpansionAdapter(row, expanded);
  };
  const clearSort = () => {
    store.clearSort();
  };
  const sort = (prop, order) => {
    store.commit("sort", { prop, order });
  };
  return {
    setCurrentRow,
    toggleRowSelection,
    clearSelection,
    clearFilter,
    toggleAllSelection,
    toggleRowExpansion,
    clearSort,
    sort
  };
}

function useStyle$2(props, layout, store, table) {
  const $ELEMENT = util.useGlobalConfig();
  const isHidden = vue.ref(false);
  const renderExpanded = vue.ref(null);
  const resizeProxyVisible = vue.ref(false);
  const setDragVisible = (visible) => {
    resizeProxyVisible.value = visible;
  };
  const resizeState = vue.ref({
    width: null,
    height: null
  });
  const isGroup = vue.ref(false);
  vue.watchEffect(() => {
    layout.setHeight(props.height);
  });
  vue.watchEffect(() => {
    layout.setMaxHeight(props.maxHeight);
  });
  vue.watch(() => [props.currentRowKey, store.states.rowKey], ([currentRowKey, rowKey]) => {
    if (!vue.unref(rowKey))
      return;
    store.setCurrentRowKey(currentRowKey + "");
  }, {
    immediate: true
  });
  vue.watch(() => props.data, (data) => {
    table.store.commit("setData", data);
  }, {
    immediate: true,
    deep: true
  });
  vue.watchEffect(() => {
    if (props.expandRowKeys) {
      store.setExpandRowKeysAdapter(props.expandRowKeys);
    }
  });
  const handleMouseLeave = () => {
    table.store.commit("setHoverRow", null);
    if (table.hoverState)
      table.hoverState = null;
  };
  const handleHeaderFooterMousewheel = (event, data) => {
    const { pixelX, pixelY } = data;
    if (Math.abs(pixelX) >= Math.abs(pixelY)) {
      table.refs.bodyWrapper.scrollLeft += data.pixelX / 5;
    }
  };
  const shouldUpdateHeight = vue.computed(() => {
    return props.height || props.maxHeight || store.states.fixedColumns.value.length > 0 || store.states.rightFixedColumns.value.length > 0;
  });
  const doLayout = () => {
    if (shouldUpdateHeight.value) {
      layout.updateElsHeight();
    }
    layout.updateColumnsWidth();
    syncPostion();
  };
  vue.onMounted(() => {
    setScrollClass("is-scrolling-left");
    bindEvents();
    store.updateColumns();
    doLayout();
    resizeState.value = {
      width: table.vnode.el.offsetWidth,
      height: table.vnode.el.offsetHeight
    };
    store.states.columns.value.forEach((column) => {
      if (column.filteredValue && column.filteredValue.length) {
        table.store.commit("filterChange", {
          column,
          values: column.filteredValue,
          silent: true
        });
      }
    });
    table.$ready = true;
  });
  const setScrollClassByEl = (el, className) => {
    if (!el)
      return;
    const classList = Array.from(el.classList).filter((item) => !item.startsWith("is-scrolling-"));
    classList.push(layout.scrollX.value ? className : "is-scrolling-none");
    el.className = classList.join(" ");
  };
  const setScrollClass = (className) => {
    const { bodyWrapper } = table.refs;
    setScrollClassByEl(bodyWrapper, className);
  };
  const syncPostion = throttle__default['default'](function() {
    if (!table.refs.bodyWrapper)
      return;
    const {
      scrollLeft,
      scrollTop,
      offsetWidth,
      scrollWidth
    } = table.refs.bodyWrapper;
    const {
      headerWrapper,
      footerWrapper,
      fixedBodyWrapper,
      rightFixedBodyWrapper
    } = table.refs;
    if (headerWrapper)
      headerWrapper.scrollLeft = scrollLeft;
    if (footerWrapper)
      footerWrapper.scrollLeft = scrollLeft;
    if (fixedBodyWrapper)
      fixedBodyWrapper.scrollTop = scrollTop;
    if (rightFixedBodyWrapper)
      rightFixedBodyWrapper.scrollTop = scrollTop;
    const maxScrollLeftPosition = scrollWidth - offsetWidth - 1;
    if (scrollLeft >= maxScrollLeftPosition) {
      setScrollClass("is-scrolling-right");
    } else if (scrollLeft === 0) {
      setScrollClass("is-scrolling-left");
    } else {
      setScrollClass("is-scrolling-middle");
    }
  }, 10);
  const bindEvents = () => {
    window.addEventListener("resize", doLayout);
    table.refs.bodyWrapper.addEventListener("scroll", syncPostion, {
      passive: true
    });
    if (props.fit) {
      resizeEvent.addResizeListener(table.vnode.el, resizeListener);
    }
  };
  vue.onUnmounted(() => {
    unbindEvents();
  });
  const unbindEvents = () => {
    var _a;
    (_a = table.refs.bodyWrapper) == null ? void 0 : _a.removeEventListener("scroll", syncPostion, true);
    window.removeEventListener("resize", doLayout);
    if (props.fit) {
      resizeEvent.removeResizeListener(table.vnode.el, resizeListener);
    }
  };
  const resizeListener = () => {
    if (!table.$ready)
      return;
    let shouldUpdateLayout = false;
    const el = table.vnode.el;
    const { width: oldWidth, height: oldHeight } = resizeState.value;
    const width = el.offsetWidth;
    if (oldWidth !== width) {
      shouldUpdateLayout = true;
    }
    const height = el.offsetHeight;
    if ((props.height || shouldUpdateHeight.value) && oldHeight !== height) {
      shouldUpdateLayout = true;
    }
    if (shouldUpdateLayout) {
      resizeState.value = {
        width,
        height
      };
      doLayout();
    }
  };
  const tableSize = vue.computed(() => {
    return props.size || $ELEMENT.size;
  });
  const bodyWidth = vue.computed(() => {
    const { bodyWidth: bodyWidth_, scrollY, gutterWidth } = layout;
    return bodyWidth_.value ? bodyWidth_.value - (scrollY.value ? gutterWidth : 0) + "px" : "";
  });
  const bodyHeight = vue.computed(() => {
    const headerHeight = layout.headerHeight.value || 0;
    const bodyHeight2 = layout.bodyHeight.value;
    const footerHeight = layout.footerHeight.value || 0;
    if (props.height) {
      return {
        height: bodyHeight2 ? bodyHeight2 + "px" : ""
      };
    } else if (props.maxHeight) {
      const maxHeight = parseHeight(props.maxHeight);
      if (typeof maxHeight === "number") {
        return {
          "max-height": maxHeight - footerHeight - (props.showHeader ? headerHeight : 0) + "px"
        };
      }
    }
    return {};
  });
  const emptyBlockStyle = vue.computed(() => {
    if (props.data && props.data.length)
      return null;
    let height = "100%";
    if (layout.appendHeight.value) {
      height = `calc(100% - ${layout.appendHeight.value}px)`;
    }
    return {
      width: bodyWidth.value,
      height
    };
  });
  const handleFixedMousewheel = (event, data) => {
    const bodyWrapper = table.refs.bodyWrapper;
    if (Math.abs(data.spinY) > 0) {
      const currentScrollTop = bodyWrapper.scrollTop;
      if (data.pixelY < 0 && currentScrollTop !== 0) {
        event.preventDefault();
      }
      if (data.pixelY > 0 && bodyWrapper.scrollHeight - bodyWrapper.clientHeight > currentScrollTop) {
        event.preventDefault();
      }
      bodyWrapper.scrollTop += Math.ceil(data.pixelY / 5);
    } else {
      bodyWrapper.scrollLeft += Math.ceil(data.pixelX / 5);
    }
  };
  const fixedHeight = vue.computed(() => {
    if (props.maxHeight) {
      if (props.showSummary) {
        return {
          bottom: 0
        };
      }
      return {
        bottom: layout.scrollX.value && props.data.length ? layout.gutterWidth + "px" : ""
      };
    } else {
      if (props.showSummary) {
        return {
          height: layout.tableHeight.value ? layout.tableHeight.value + "px" : ""
        };
      }
      return {
        height: layout.viewportHeight.value ? layout.viewportHeight.value + "px" : ""
      };
    }
  });
  const fixedBodyHeight = vue.computed(() => {
    if (props.height) {
      return {
        height: layout.fixedBodyHeight.value ? layout.fixedBodyHeight.value + "px" : ""
      };
    } else if (props.maxHeight) {
      let maxHeight = parseHeight(props.maxHeight);
      if (typeof maxHeight === "number") {
        maxHeight = layout.scrollX.value ? maxHeight - layout.gutterWidth : maxHeight;
        if (props.showHeader) {
          maxHeight -= layout.headerHeight.value;
        }
        maxHeight -= layout.footerHeight.value;
        return {
          "max-height": maxHeight + "px"
        };
      }
    }
    return {};
  });
  return {
    isHidden,
    renderExpanded,
    setDragVisible,
    isGroup,
    handleMouseLeave,
    handleHeaderFooterMousewheel,
    tableSize,
    bodyHeight,
    emptyBlockStyle,
    handleFixedMousewheel,
    fixedHeight,
    fixedBodyHeight,
    resizeProxyVisible,
    bodyWidth,
    resizeState,
    doLayout
  };
}

var defaultProps$1 = {
  data: {
    type: Array,
    default: () => {
      return [];
    }
  },
  size: String,
  width: [String, Number],
  height: [String, Number],
  maxHeight: [String, Number],
  fit: {
    type: Boolean,
    default: true
  },
  stripe: Boolean,
  border: Boolean,
  rowKey: [String, Function],
  showHeader: {
    type: Boolean,
    default: true
  },
  showSummary: Boolean,
  sumText: String,
  summaryMethod: Function,
  rowClassName: [String, Function],
  rowStyle: [Object, Function],
  cellClassName: [String, Function],
  cellStyle: [Object, Function],
  headerRowClassName: [String, Function],
  headerRowStyle: [Object, Function],
  headerCellClassName: [String, Function],
  headerCellStyle: [Object, Function],
  highlightCurrentRow: Boolean,
  currentRowKey: [String, Number],
  emptyText: String,
  expandRowKeys: Array,
  defaultExpandAll: Boolean,
  defaultSort: Object,
  tooltipEffect: String,
  spanMethod: Function,
  selectOnIndeterminate: {
    type: Boolean,
    default: true
  },
  indent: {
    type: Number,
    default: 16
  },
  treeProps: {
    type: Object,
    default: () => {
      return {
        hasChildren: "hasChildren",
        children: "children"
      };
    }
  },
  lazy: Boolean,
  load: Function,
  style: {
    type: Object,
    default: () => ({})
  },
  className: {
    type: String,
    default: ""
  }
};

let tableIdSeed = 1;
var script$1 = vue.defineComponent({
  name: "ElTable",
  directives: {
    Mousewheel: directives.Mousewheel
  },
  components: {
    TableHeader,
    TableBody,
    TableFooter
  },
  props: defaultProps$1,
  emits: [
    "select",
    "select-all",
    "selection-change",
    "cell-mouse-enter",
    "cell-mouse-leave",
    "cell-click",
    "cell-dblclick",
    "row-click",
    "row-contextmenu",
    "row-dblclick",
    "header-click",
    "header-contextmenu",
    "sort-change",
    "filter-change",
    "current-change",
    "header-dragend",
    "expand-change"
  ],
  setup(props) {
    let table = vue.getCurrentInstance();
    const store = createStore(table, props);
    table.store = store;
    const layout = new TableLayout({
      store: table.store,
      table,
      fit: props.fit,
      showHeader: props.showHeader
    });
    table.layout = layout;
    const {
      setCurrentRow,
      toggleRowSelection,
      clearSelection,
      clearFilter,
      toggleAllSelection,
      toggleRowExpansion,
      clearSort,
      sort
    } = useUtils$1(store);
    const {
      isHidden,
      renderExpanded,
      setDragVisible,
      isGroup,
      handleMouseLeave,
      handleHeaderFooterMousewheel,
      tableSize,
      bodyHeight,
      emptyBlockStyle,
      handleFixedMousewheel,
      fixedHeight,
      fixedBodyHeight,
      resizeProxyVisible,
      bodyWidth,
      resizeState,
      doLayout
    } = useStyle$2(props, layout, store, table);
    const debouncedUpdateLayout = debounce__default['default'](doLayout, 50);
    const tableId = "el-table_" + tableIdSeed++;
    table.tableId = tableId;
    table.state = {
      isGroup,
      resizeState,
      doLayout,
      debouncedUpdateLayout
    };
    return {
      layout,
      store,
      handleHeaderFooterMousewheel,
      handleMouseLeave,
      tableId,
      tableSize,
      isHidden,
      renderExpanded,
      resizeProxyVisible,
      resizeState,
      isGroup,
      bodyWidth,
      bodyHeight,
      emptyBlockStyle,
      debouncedUpdateLayout,
      handleFixedMousewheel,
      fixedHeight,
      fixedBodyHeight,
      setCurrentRow,
      toggleRowSelection,
      clearSelection,
      clearFilter,
      toggleAllSelection,
      toggleRowExpansion,
      clearSort,
      doLayout,
      sort,
      t: locale.t,
      setDragVisible,
      context: table
    };
  }
});

const _hoisted_1$1 = {
  ref: "hiddenColumns",
  class: "hidden-columns"
};
const _hoisted_2$1 = {
  key: 0,
  ref: "headerWrapper",
  class: "el-table__header-wrapper"
};
const _hoisted_3$1 = { class: "el-table__empty-text" };
const _hoisted_4$1 = {
  key: 1,
  ref: "appendWrapper",
  class: "el-table__append-wrapper"
};
const _hoisted_5 = {
  key: 1,
  ref: "footerWrapper",
  class: "el-table__footer-wrapper"
};
const _hoisted_6 = {
  key: 0,
  ref: "fixedHeaderWrapper",
  class: "el-table__fixed-header-wrapper"
};
const _hoisted_7 = {
  key: 1,
  ref: "fixedFooterWrapper",
  class: "el-table__fixed-footer-wrapper"
};
const _hoisted_8 = {
  key: 0,
  ref: "rightFixedHeaderWrapper",
  class: "el-table__fixed-header-wrapper"
};
const _hoisted_9 = {
  key: 1,
  ref: "rightFixedFooterWrapper",
  class: "el-table__fixed-footer-wrapper"
};
const _hoisted_10 = {
  ref: "resizeProxy",
  class: "el-table__column-resize-proxy"
};
function render$1(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_table_header = vue.resolveComponent("table-header");
  const _component_table_body = vue.resolveComponent("table-body");
  const _component_table_footer = vue.resolveComponent("table-footer");
  const _directive_mousewheel = vue.resolveDirective("mousewheel");
  return vue.openBlock(), vue.createBlock("div", {
    class: [
      {
        "el-table--fit": _ctx.fit,
        "el-table--striped": _ctx.stripe,
        "el-table--border": _ctx.border || _ctx.isGroup,
        "el-table--hidden": _ctx.isHidden,
        "el-table--group": _ctx.isGroup,
        "el-table--fluid-height": _ctx.maxHeight,
        "el-table--scrollable-x": _ctx.layout.scrollX.value,
        "el-table--scrollable-y": _ctx.layout.scrollY.value,
        "el-table--enable-row-hover": !_ctx.store.states.isComplex.value,
        "el-table--enable-row-transition": (_ctx.store.states.data.value || []).length !== 0 && (_ctx.store.states.data.value || []).length < 100
      },
      _ctx.tableSize ? `el-table--${_ctx.tableSize}` : "",
      _ctx.className,
      "el-table"
    ],
    style: _ctx.style,
    onMouseleave: _cache[1] || (_cache[1] = ($event) => _ctx.handleMouseLeave())
  }, [
    vue.createVNode("div", _hoisted_1$1, [
      vue.renderSlot(_ctx.$slots, "default")
    ], 512),
    _ctx.showHeader ? vue.withDirectives((vue.openBlock(), vue.createBlock("div", _hoisted_2$1, [
      vue.createVNode(_component_table_header, {
        ref: "tableHeader",
        border: _ctx.border,
        "default-sort": _ctx.defaultSort,
        store: _ctx.store,
        style: {
          width: _ctx.layout.bodyWidth.value ? _ctx.layout.bodyWidth.value + "px" : ""
        },
        onSetDragVisible: _ctx.setDragVisible
      }, null, 8, ["border", "default-sort", "store", "style", "onSetDragVisible"])
    ], 512)), [
      [_directive_mousewheel, _ctx.handleHeaderFooterMousewheel]
    ]) : vue.createCommentVNode("v-if", true),
    vue.createVNode("div", {
      ref: "bodyWrapper",
      style: [_ctx.bodyHeight],
      class: "el-table__body-wrapper"
    }, [
      vue.createVNode(_component_table_body, {
        context: _ctx.context,
        highlight: _ctx.highlightCurrentRow,
        "row-class-name": _ctx.rowClassName,
        "tooltip-effect": _ctx.tooltipEffect,
        "row-style": _ctx.rowStyle,
        store: _ctx.store,
        stripe: _ctx.stripe,
        style: {
          width: _ctx.bodyWidth
        }
      }, null, 8, ["context", "highlight", "row-class-name", "tooltip-effect", "row-style", "store", "stripe", "style"]),
      !_ctx.data || _ctx.data.length === 0 ? (vue.openBlock(), vue.createBlock("div", {
        key: 0,
        ref: "emptyBlock",
        style: _ctx.emptyBlockStyle,
        class: "el-table__empty-block"
      }, [
        vue.createVNode("span", _hoisted_3$1, [
          vue.renderSlot(_ctx.$slots, "empty", {}, () => [
            vue.createTextVNode(vue.toDisplayString(_ctx.emptyText || _ctx.t("el.table.emptyText")), 1)
          ])
        ])
      ], 4)) : vue.createCommentVNode("v-if", true),
      _ctx.$slots.append ? (vue.openBlock(), vue.createBlock("div", _hoisted_4$1, [
        vue.renderSlot(_ctx.$slots, "append")
      ], 512)) : vue.createCommentVNode("v-if", true)
    ], 4),
    _ctx.showSummary ? vue.withDirectives((vue.openBlock(), vue.createBlock("div", _hoisted_5, [
      vue.createVNode(_component_table_footer, {
        border: _ctx.border,
        "default-sort": _ctx.defaultSort,
        store: _ctx.store,
        style: {
          width: _ctx.layout.bodyWidth.value ? _ctx.layout.bodyWidth.value + "px" : ""
        },
        "sum-text": _ctx.sumText || _ctx.t("el.table.sumText"),
        "summary-method": _ctx.summaryMethod
      }, null, 8, ["border", "default-sort", "store", "style", "sum-text", "summary-method"])
    ], 512)), [
      [vue.vShow, _ctx.data && _ctx.data.length > 0],
      [_directive_mousewheel, _ctx.handleHeaderFooterMousewheel]
    ]) : vue.createCommentVNode("v-if", true),
    _ctx.store.states.fixedColumns.value.length > 0 ? vue.withDirectives((vue.openBlock(), vue.createBlock("div", {
      key: 2,
      ref: "fixedWrapper",
      style: [
        {
          width: _ctx.layout.fixedWidth.value ? _ctx.layout.fixedWidth.value + "px" : ""
        },
        _ctx.fixedHeight
      ],
      class: "el-table__fixed"
    }, [
      _ctx.showHeader ? (vue.openBlock(), vue.createBlock("div", _hoisted_6, [
        vue.createVNode(_component_table_header, {
          ref: "fixedTableHeader",
          border: _ctx.border,
          store: _ctx.store,
          style: {
            width: _ctx.bodyWidth
          },
          fixed: "left",
          onSetDragVisible: _ctx.setDragVisible
        }, null, 8, ["border", "store", "style", "onSetDragVisible"])
      ], 512)) : vue.createCommentVNode("v-if", true),
      vue.createVNode("div", {
        ref: "fixedBodyWrapper",
        style: [
          {
            top: _ctx.layout.headerHeight.value + "px"
          },
          _ctx.fixedBodyHeight
        ],
        class: "el-table__fixed-body-wrapper"
      }, [
        vue.createVNode(_component_table_body, {
          highlight: _ctx.highlightCurrentRow,
          "row-class-name": _ctx.rowClassName,
          "tooltip-effect": _ctx.tooltipEffect,
          "row-style": _ctx.rowStyle,
          store: _ctx.store,
          stripe: _ctx.stripe,
          style: {
            width: _ctx.bodyWidth
          },
          fixed: "left"
        }, null, 8, ["highlight", "row-class-name", "tooltip-effect", "row-style", "store", "stripe", "style"]),
        _ctx.$slots.append ? (vue.openBlock(), vue.createBlock("div", {
          key: 0,
          style: { height: _ctx.layout.appendHeight.value + "px" },
          class: "el-table__append-gutter"
        }, null, 4)) : vue.createCommentVNode("v-if", true)
      ], 4),
      _ctx.showSummary ? vue.withDirectives((vue.openBlock(), vue.createBlock("div", _hoisted_7, [
        vue.createVNode(_component_table_footer, {
          border: _ctx.border,
          store: _ctx.store,
          style: {
            width: _ctx.bodyWidth
          },
          "sum-text": _ctx.sumText || _ctx.t("el.table.sumText"),
          "summary-method": _ctx.summaryMethod,
          fixed: "left"
        }, null, 8, ["border", "store", "style", "sum-text", "summary-method"])
      ], 512)), [
        [vue.vShow, _ctx.data && _ctx.data.length > 0]
      ]) : vue.createCommentVNode("v-if", true)
    ], 4)), [
      [_directive_mousewheel, _ctx.handleFixedMousewheel]
    ]) : vue.createCommentVNode("v-if", true),
    _ctx.store.states.rightFixedColumns.value.length > 0 ? vue.withDirectives((vue.openBlock(), vue.createBlock("div", {
      key: 3,
      ref: "rightFixedWrapper",
      style: [
        {
          width: _ctx.layout.rightFixedWidth.value ? _ctx.layout.rightFixedWidth.value + "px" : "",
          right: _ctx.layout.scrollY.value ? (_ctx.border ? _ctx.layout.gutterWidth : _ctx.layout.gutterWidth || 0) + "px" : ""
        },
        _ctx.fixedHeight
      ],
      class: "el-table__fixed-right"
    }, [
      _ctx.showHeader ? (vue.openBlock(), vue.createBlock("div", _hoisted_8, [
        vue.createVNode(_component_table_header, {
          ref: "rightFixedTableHeader",
          border: _ctx.border,
          store: _ctx.store,
          style: {
            width: _ctx.bodyWidth
          },
          fixed: "right",
          onSetDragVisible: _ctx.setDragVisible
        }, null, 8, ["border", "store", "style", "onSetDragVisible"])
      ], 512)) : vue.createCommentVNode("v-if", true),
      vue.createVNode("div", {
        ref: "rightFixedBodyWrapper",
        style: [{ top: _ctx.layout.headerHeight.value + "px" }, _ctx.fixedBodyHeight],
        class: "el-table__fixed-body-wrapper"
      }, [
        vue.createVNode(_component_table_body, {
          highlight: _ctx.highlightCurrentRow,
          "row-class-name": _ctx.rowClassName,
          "tooltip-effect": _ctx.tooltipEffect,
          "row-style": _ctx.rowStyle,
          store: _ctx.store,
          stripe: _ctx.stripe,
          style: {
            width: _ctx.bodyWidth
          },
          fixed: "right"
        }, null, 8, ["highlight", "row-class-name", "tooltip-effect", "row-style", "store", "stripe", "style"]),
        _ctx.$slots.append ? (vue.openBlock(), vue.createBlock("div", {
          key: 0,
          style: { height: _ctx.layout.appendHeight.value + "px" },
          class: "el-table__append-gutter"
        }, null, 4)) : vue.createCommentVNode("v-if", true)
      ], 4),
      _ctx.showSummary ? vue.withDirectives((vue.openBlock(), vue.createBlock("div", _hoisted_9, [
        vue.createVNode(_component_table_footer, {
          border: _ctx.border,
          store: _ctx.store,
          style: {
            width: _ctx.bodyWidth
          },
          "sum-text": _ctx.sumText || _ctx.t("el.table.sumText"),
          "summary-method": _ctx.summaryMethod,
          fixed: "right"
        }, null, 8, ["border", "store", "style", "sum-text", "summary-method"])
      ], 512)), [
        [vue.vShow, _ctx.data && _ctx.data.length > 0]
      ]) : vue.createCommentVNode("v-if", true)
    ], 4)), [
      [_directive_mousewheel, _ctx.handleFixedMousewheel]
    ]) : vue.createCommentVNode("v-if", true),
    _ctx.store.states.rightFixedColumns.value.length > 0 ? (vue.openBlock(), vue.createBlock("div", {
      key: 4,
      ref: "rightFixedPatch",
      style: {
        width: _ctx.layout.scrollY.value ? _ctx.layout.gutterWidth + "px" : "0",
        height: _ctx.layout.headerHeight.value + "px"
      },
      class: "el-table__fixed-right-patch"
    }, null, 4)) : vue.createCommentVNode("v-if", true),
    vue.withDirectives(vue.createVNode("div", _hoisted_10, null, 512), [
      [vue.vShow, _ctx.resizeProxyVisible]
    ])
  ], 38);
}

script$1.render = render$1;
script$1.__file = "packages/table/src/table.vue";

script$1.install = (app) => {
  app.component(script$1.name, script$1);
};
const _Table = script$1;

exports.default = _Table;
