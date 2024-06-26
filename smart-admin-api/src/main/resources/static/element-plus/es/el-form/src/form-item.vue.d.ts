import { RuleItem } from 'async-validator';
import type { PropType } from 'vue';
import type { ElFormContext } from './token';
declare const _default: import("vue").DefineComponent<{
    label: StringConstructor;
    labelWidth: StringConstructor;
    prop: StringConstructor;
    required: {
        type: BooleanConstructor;
        default: any;
    };
    rules: PropType<RuleItem | RuleItem[]>;
    error: StringConstructor;
    validateStatus: StringConstructor;
    for: StringConstructor;
    inlineMessage: {
        type: (BooleanConstructor | StringConstructor)[];
        default: string;
    };
    showMessage: {
        type: BooleanConstructor;
        default: boolean;
    };
    size: {
        types: PropType<ComponentSize>;
        validator: (val: string) => boolean;
    };
}, {
    formItemClass: import("vue").ComputedRef<(string | {
        'el-form-item--feedback': boolean;
        'is-error': boolean;
        'is-validating': boolean;
        'is-success': boolean;
        'is-required': boolean;
        'is-no-asterisk': boolean;
    })[]>;
    shouldShowError: import("vue").ComputedRef<boolean>;
    elForm: ElFormContext;
    labelStyle: import("vue").ComputedRef<{
        width?: undefined;
    } | {
        width: unknown;
    }>;
    contentStyle: import("vue").ComputedRef<Partial<CSSStyleDeclaration>>;
    validateMessage: import("vue").Ref<string>;
    labelFor: import("vue").ComputedRef<unknown>;
    resetField: () => void;
    clearValidate: () => void;
}, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, Record<string, any>, string, import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<{
    inlineMessage: unknown;
    showMessage: boolean;
} & {
    label?: unknown;
    labelWidth?: unknown;
    prop?: unknown;
    required?: boolean;
    rules?: unknown;
    error?: unknown;
    validateStatus?: unknown;
    for?: unknown;
    size?: unknown;
}>, {
    required: boolean;
    inlineMessage: unknown;
    showMessage: boolean;
}>;
export default _default;
