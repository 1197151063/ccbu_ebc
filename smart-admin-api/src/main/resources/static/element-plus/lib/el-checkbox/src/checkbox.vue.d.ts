import { PropType } from 'vue';
declare const _default: import("vue").DefineComponent<{
    modelValue: {
        type: (BooleanConstructor | NumberConstructor | StringConstructor)[];
        default: () => any;
    };
    label: {
        type: (BooleanConstructor | NumberConstructor | StringConstructor)[];
    };
    indeterminate: BooleanConstructor;
    disabled: BooleanConstructor;
    checked: BooleanConstructor;
    name: {
        type: StringConstructor;
        default: any;
    };
    trueLabel: {
        type: (NumberConstructor | StringConstructor)[];
        default: any;
    };
    falseLabel: {
        type: (NumberConstructor | StringConstructor)[];
        default: any;
    };
    id: {
        type: StringConstructor;
        default: any;
    };
    controls: {
        type: StringConstructor;
        default: any;
    };
    border: BooleanConstructor;
    size: {
        type: PropType<ComponentSize>;
        validator: (val: string) => boolean;
    };
}, {
    isChecked: import("vue").ComputedRef<any>;
    isDisabled: import("vue").ComputedRef<any>;
    checkboxSize: import("vue").ComputedRef<any>;
    model: import("vue").WritableComputedRef<any>;
    handleChange: (e: InputEvent) => void;
    focus: import("vue").Ref<boolean>;
    size: import("vue").ComputedRef<string>;
}, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, ("update:modelValue" | "change")[], "update:modelValue" | "change", import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<{
    indeterminate: boolean;
    disabled: boolean;
    checked: boolean;
    border: boolean;
} & {
    modelValue?: unknown;
    label?: unknown;
    name?: unknown;
    trueLabel?: unknown;
    falseLabel?: unknown;
    id?: unknown;
    controls?: unknown;
    size?: unknown;
}>, {
    modelValue: unknown;
    indeterminate: boolean;
    disabled: boolean;
    checked: boolean;
    name: unknown;
    trueLabel: unknown;
    falseLabel: unknown;
    id: unknown;
    controls: unknown;
    border: boolean;
}>;
export default _default;
