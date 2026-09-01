package com.maypo.data.datasource

import com.maypo.common.AppLogger
import com.maypo.common.NetworkResult
import com.maypo.common.support.SupportContact
import com.maypo.common.support.SupportContent
import com.maypo.common.support.SupportFaq

class LocalSupportDataSource : SupportDataSource {

    override suspend fun getSupportContent(): NetworkResult<SupportContent> {
        AppLogger.debug(TAG, "Serving local support content faqs=${LocalSupportContent.faqs.size}")
        return NetworkResult.Success(LocalSupportContent)
    }

    private companion object {
        const val TAG = "SupportData"
    }
}

internal val LocalSupportContent = SupportContent(
    contact = SupportContact(
        centerName = "Centro logístico MAYPO",
        phoneDisplay = "55 4000 1234",
        phoneUri = "tel:5540001234",
    ),
    faqs = listOf(
        SupportFaq(
            id = "patient-not-home",
            question = "¿Qué hago si el paciente no está en el domicilio?",
            answer = "Llame al paciente desde el detalle de la parada. Si no responde, registre una devolución con el motivo NO DISPONIBLE; el medicamento se reasigna a la siguiente entrega.",
        ),
        SupportFaq(
            id = "vials-mismatch",
            question = "¿Qué pasa si los frascos no coinciden con el kárdex?",
            answer = "Responda \"No, hay un problema\" en el paso de foto de frascos. El sistema abre la Carta de hechos para documentar la diferencia antes de continuar.",
        ),
        SupportFaq(
            id = "authorized-receiver",
            question = "¿Quién puede recibir la entrega además del titular?",
            answer = "Solo las personas autorizadas registradas en el sistema. Si quien recibe no está en la lista, tome foto de su INE y autorícela indicando el parentesco y la justificación.",
        ),
        SupportFaq(
            id = "report-incident",
            question = "¿Cómo reporto una incidencia en el trayecto?",
            answer = "Use el botón naranja de alerta desde Mis rutas o el detalle de ruta. La incidencia queda registrada en la línea de paradas con GPS y hora automáticos.",
        ),
        SupportFaq(
            id = "required-documents",
            question = "¿Qué documentos debo escanear en cada entrega?",
            answer = "Remisión, vigencia de derechos y registro de infusión. Los tres son obligatorios para poder finalizar la entrega.",
        ),
        SupportFaq(
            id = "new-patient",
            question = "¿Qué hago si el paciente es nuevo?",
            answer = "Aparece la etiqueta \"Paciente nuevo\" y se agrega un paso para escanear la Carta compromiso firmada antes de la verificación de frascos.",
        ),
        SupportFaq(
            id = "gps-or-app",
            question = "Mi GPS o la app no funcionan, ¿qué hago?",
            answer = "Reinicie la aplicación. Si el problema continúa, llame al centro logístico; puede seguir registrando entregas y sincronizarán al recuperar señal.",
        ),
    ),
)
