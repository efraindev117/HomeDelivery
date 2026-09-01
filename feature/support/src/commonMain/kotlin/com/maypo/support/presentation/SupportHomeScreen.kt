package com.maypo.support.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maypo.common.support.SupportContact
import com.maypo.common.support.SupportContent
import com.maypo.common.support.SupportFaq
import com.maypo.designsystem.component.HdButton
import com.maypo.designsystem.component.HdButtonSize
import com.maypo.designsystem.component.HdButtonVariant
import com.maypo.designsystem.component.HdCard
import com.maypo.designsystem.component.HdErrorBanner
import com.maypo.designsystem.theme.HdColors
import com.maypo.designsystem.theme.HdSpacing
import com.maypo.designsystem.theme.HomeDeliveryTheme
import kotlin.coroutines.cancellation.CancellationException
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SupportHomeScreen(
    viewModel: SupportViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    SupportHomeContent(
        uiState = uiState,
        onCallSupport = {
            val phoneUri = uiState.content?.contact?.phoneUri
            if (phoneUri.isNullOrBlank()) {
                viewModel.onCallFailed()
                return@SupportHomeContent
            }
            viewModel.onCallSupport()
            try {
                uriHandler.openUri(phoneUri)
            } catch (error: Exception) {
                if (error is CancellationException) throw error
                viewModel.onCallFailed()
            }
        },
        onToggleFaq = viewModel::toggleFaq,
        onRetry = viewModel::loadSupport,
    )
}

@Composable
internal fun SupportHomeContent(
    uiState: SupportUiState,
    onCallSupport: () -> Unit,
    onToggleFaq: (String) -> Unit,
    onRetry: () -> Unit,
) {
    val content = uiState.content

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HdColors.canvas)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = HdSpacing.xxl, vertical = HdSpacing.huge),
        verticalArrangement = Arrangement.spacedBy(HdSpacing.lg),
    ) {
        Text(
            text = "Soporte",
            color = HdColors.ink,
            style = MaterialTheme.typography.headlineMedium,
        )

        val errorMessage = uiState.errorMessage ?: uiState.callError
        errorMessage?.let { message ->
            HdErrorBanner(message = message)
        }

        if (uiState.isLoading && content == null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = HdSpacing.huge),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = HdColors.navy,
                    strokeWidth = 3.dp,
                )
            }
        }

        if (uiState.errorMessage != null && content == null && !uiState.isLoading) {
            HdButton(
                text = "Reintentar",
                onClick = onRetry,
                modifier = Modifier.fillMaxWidth(),
                variant = HdButtonVariant.Secondary,
                size = HdButtonSize.Medium,
            )
        }

        content?.let { support ->
            HdCard {
                Text(
                    text = support.contact.centerName,
                    color = HdColors.gray,
                    style = MaterialTheme.typography.bodySmall,
                )
                Spacer(Modifier.height(HdSpacing.xxs))
                Text(
                    text = support.contact.phoneDisplay,
                    color = HdColors.ink,
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            HdButton(
                text = "Llamar a soporte",
                onClick = onCallSupport,
                modifier = Modifier.fillMaxWidth(),
                size = HdButtonSize.Medium,
            )

            Text(
                text = "Preguntas frecuentes",
                color = HdColors.muted,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = HdSpacing.xs),
            )

            if (support.faqs.isEmpty()) {
                Text(
                    text = "Aún no hay preguntas frecuentes.",
                    color = HdColors.muted,
                    style = MaterialTheme.typography.bodyMedium,
                )
            } else {
                support.faqs.forEach { faq ->
                    SupportFaqItem(
                        faq = faq,
                        expanded = uiState.expandedFaqId == faq.id,
                        onClick = { onToggleFaq(faq.id) },
                    )
                }
            }
        }
    }
}

@Composable
private fun SupportFaqItem(
    faq: SupportFaq,
    expanded: Boolean,
    onClick: () -> Unit,
) {
    HdCard(onClick = onClick) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(HdSpacing.md),
        ) {
            Text(
                text = faq.question,
                color = HdColors.ink,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = if (expanded) "−" else "+",
                color = HdColors.navy,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            Text(
                text = faq.answer,
                color = HdColors.gray,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = HdSpacing.md),
            )
        }
    }
}

@Preview(
    name = "Soporte",
    showBackground = true,
    widthDp = 390,
    heightDp = 844,
)
@Composable
private fun SupportHomePreview() {
    HomeDeliveryTheme {
        SupportHomeContent(
            uiState = SupportUiState(
                content = PreviewSupportContent,
                expandedFaqId = "patient-not-home",
            ),
            onCallSupport = {},
            onToggleFaq = {},
            onRetry = {},
        )
    }
}

private val PreviewSupportContent = SupportContent(
    contact = SupportContact(
        centerName = "Centro logístico MAYPO",
        phoneDisplay = "55 4000 1234",
        phoneUri = "tel:5540001234",
    ),
    faqs = listOf(
        SupportFaq(
            id = "patient-not-home",
            question = "¿Qué hago si el paciente no está en el domicilio?",
            answer = "Llame al paciente desde el detalle de la parada. Si no responde, registre una devolución con el motivo NO DISPONIBLE.",
        ),
        SupportFaq(
            id = "gps-or-app",
            question = "Mi GPS o la app no funcionan, ¿qué hago?",
            answer = "Reinicie la aplicación. Si el problema continúa, llame al centro logístico.",
        ),
    ),
)
