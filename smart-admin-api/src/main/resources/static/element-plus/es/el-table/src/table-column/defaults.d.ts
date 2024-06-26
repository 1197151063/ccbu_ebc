import { PropType } from 'vue';
import { ComponentInternalInstance, Ref, VNode } from 'vue';
import { Table } from '../table/defaults';
declare type CI<T> = {
    column: TableColumnCtx<T>;
    $index: number;
};
declare type Filters = {
    text: string;
    value: string;
}[];
declare type FilterMethods<T> = (value: any, row: T, column: TableColumnCtx<T>) => void;
interface TableColumnCtx<T> {
    id: string;
    realWidth: number;
    type: string;
    label: string;
    className: string;
    labelClassName: string;
    property: string;
    prop: string;
    width: string | number;
    minWidth: string | number;
    renderHeader: (data: CI<T>) => VNode;
    sortable: boolean | string;
    sortMethod: (a: T, b: T) => number;
    sortBy: string | ((row: T, index: number) => string) | string[];
    resizable: boolean;
    columnKey: string;
    rawColumnKey: string;
    align: string;
    headerAlign: string;
    showTooltipWhenOverflow: boolean;
    showOverflowTooltip: boolean;
    fixed: boolean | string;
    formatter: (row: T, column: TableColumnCtx<T>, cellValue: any, index: number) => VNode;
    selectable: (row: T, index: number) => boolean;
    reserveSelection: boolean;
    filterMethod: FilterMethods<T>;
    filteredValue: string[];
    filters: Filters;
    filterPlacement: string;
    filterMultiple: boolean;
    index: number | ((index: number) => number);
    sortOrders: ('ascending' | 'descending' | null)[];
    renderCell: (data: any) => void;
    colSpan: number;
    rowSpan: number;
    children: TableColumnCtx<T>[];
    level: number;
    filterable: boolean | FilterMethods<T> | Filters;
    order: string;
    isColumnGroup: boolean;
    columns: TableColumnCtx<T>[];
    getColumnIndex: () => number;
    no: number;
}
interface TableColumn<T> extends ComponentInternalInstance {
    vnode: {
        vParent: TableColumn<T> | Table<T>;
    } & VNode;
    vParent: TableColumn<T> | Table<T>;
    columnId: string;
    columnConfig: Ref<Partial<TableColumnCtx<T>>>;
}
export type { Filters, FilterMethods, TableColumnCtx, TableColumn };
declare const _default: {
    type: {
        type: StringConstructor;
        default: string;
    };
    label: StringConstructor;
    className: StringConstructor;
    labelClassName: StringConstructor;
    property: StringConstructor;
    prop: StringConstructor;
    width: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
    };
    minWidth: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
    };
    renderHeader: PropType<(data: CI<any>) => VNode<import("vue").RendererNode, import("vue").RendererElement, {
        [key: string]: any;
    }>>;
    sortable: {
        type: (StringConstructor | BooleanConstructor)[];
        default: boolean;
    };
    sortMethod: PropType<(a: any, b: any) => number>;
    sortBy: PropType<string | string[] | ((row: any, index: number) => string)>;
    resizable: {
        type: BooleanConstructor;
        default: boolean;
    };
    columnKey: StringConstructor;
    align: StringConstructor;
    headerAlign: StringConstructor;
    showTooltipWhenOverflow: BooleanConstructor;
    showOverflowTooltip: BooleanConstructor;
    fixed: (StringConstructor | BooleanConstructor)[];
    formatter: PropType<(row: any, column: TableColumnCtx<any>, cellValue: any, index: number) => VNode<import("vue").RendererNode, import("vue").RendererElement, {
        [key: string]: any;
    }>>;
    selectable: PropType<(row: any, index: number) => boolean>;
    reserveSelection: BooleanConstructor;
    filterMethod: PropType<FilterMethods<any>>;
    filteredValue: PropType<string[]>;
    filters: PropType<Filters>;
    filterPlacement: StringConstructor;
    filterMultiple: {
        type: BooleanConstructor;
        default: boolean;
    };
    index: PropType<number | ((index: number) => number)>;
    sortOrders: {
        type: PropType<("ascending" | "descending")[]>;
        default: () => string[];
        validator: (val: TableColumnCtx<unknown>['sortOrders']) => boolean;
    };
};
export default _default;
