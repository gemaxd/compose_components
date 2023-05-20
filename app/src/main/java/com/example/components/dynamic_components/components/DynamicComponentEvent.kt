package com.example.components.dynamic_components.components

import com.example.components.feature.dynamic_form.domain.model.Attachment
import com.example.components.feature.dynamic_form.domain.model.Component

sealed class DynamicComponentEvent {
    data class LoadAttachments(val attachments: List<Attachment>): DynamicComponentEvent()
    data class RemoveAttachment(val attachmentId: Int): DynamicComponentEvent()
    data class AddAttachment(val attachment: Attachment): DynamicComponentEvent()
    data class OnTextChange(
        val component: Component,
        val text: String,
        val isValid: Boolean
    ): DynamicComponentEvent()
    data class CounterUpdate(val count: Int): DynamicComponentEvent()
}
