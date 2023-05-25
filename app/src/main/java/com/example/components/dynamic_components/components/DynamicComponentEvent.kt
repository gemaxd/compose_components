package com.example.components.dynamic_components.components

import com.example.components.feature.dynamic_form.domain.model.Attachment
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.model.Option

sealed class DynamicComponentEvent {
    data class LoadAttachments(
        val attachments: List<Attachment>
    ) : DynamicComponentEvent()

    data class RemoveAttachment(
        val component: Component, val attachment: Attachment
    ) : DynamicComponentEvent()

    data class AddAttachment(
        val component: Component, val attachment: Attachment
    ) : DynamicComponentEvent()

    data class OnTextChange(
        val component: Component, val text: String, val isValid: Boolean
    ) : DynamicComponentEvent()

    data class OnDropDownOptionSelected(
        val component: Component, val option: Option
    ) : DynamicComponentEvent()

    data class CounterUpdate(
        val count: Int
    ) : DynamicComponentEvent()

    data class OnChipAdd(
        val component: Component, val option: Option
    ) : DynamicComponentEvent()

    data class OnChipRemove(
        val component: Component, val option: Option
    ) : DynamicComponentEvent()

    data class UpdateOptionsValidations(
        val component: Component, val option: Option, val isValid: Boolean
    ) : DynamicComponentEvent()

    data class OnRadioButtonSelection(
        val component: Component, val option: Option
    ): DynamicComponentEvent()

    data class OnCheckBoxToggle(
        val component: Component, val option: Option
    ): DynamicComponentEvent()

}
