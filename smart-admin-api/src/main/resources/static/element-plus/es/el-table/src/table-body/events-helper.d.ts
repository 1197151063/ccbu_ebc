/// <reference types="lodash" />
import { TableBodyProps } from './defaults';
declare function useEvents<T>(props: Partial<TableBodyProps<T>>): {
    handleDoubleClick: (event: Event, row: T) => void;
    handleClick: (event: Event, row: T) => void;
    handleContextMenu: (event: Event, row: T) => void;
    handleMouseEnter: import("lodash").DebouncedFunc<(index: number) => void>;
    handleMouseLeave: import("lodash").DebouncedFunc<() => void>;
    handleCellMouseEnter: (event: MouseEvent, row: T & {
        tooltipEffect: string;
    }) => void;
    handleCellMouseLeave: (event: any) => void;
    tooltipContent: import("vue").Ref<string>;
    tooltipTrigger: import("vue").Ref<import("vue").VNode<import("vue").RendererNode, import("vue").RendererElement, {
        [key: string]: any;
    }>>;
};
export default useEvents;
