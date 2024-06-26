import { PropType } from 'vue';
declare const _default: import("vue").DefineComponent<{
    border: {
        type: BooleanConstructor;
        default: boolean;
    };
    column: {
        type: NumberConstructor;
        default: number;
    };
    direction: {
        type: PropType<"horizontal" | "vertical">;
        default: string;
    };
    size: {
        type: PropType<ComponentSize>;
        validator: (val: string) => boolean;
    };
    title: {
        type: StringConstructor;
        default: string;
    };
    extra: {
        type: StringConstructor;
        default: string;
    };
}, {
    descriptionsSize: import("vue").ComputedRef<any>;
    rows: import("vue").ComputedRef<any[]>;
}, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, Record<string, any>, string, import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<{
    border: boolean;
    column: unknown;
    direction: unknown;
    title: unknown;
    extra: unknown;
} & {
    size?: unknown;
}>, {
    border: boolean;
    column: unknown;
    direction: unknown;
    title: unknown;
    extra: unknown;
}>;
export default _default;
