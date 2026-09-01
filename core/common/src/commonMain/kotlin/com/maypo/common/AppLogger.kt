package com.maypo.common

/**
 * Registro seguro y centralizado para código compartido.
 *
 * Permanece deshabilitado hasta que cada aplicación de plataforma lo active
 * explícitamente para su variante Debug.
 */
expect object AppLogger {
    fun configure(enabled: Boolean)

    fun debug(tag: String, message: String)

    fun error(tag: String, message: String)
}
