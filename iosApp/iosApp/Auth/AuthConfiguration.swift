//
//  AuthConfiguration.swift
//  iosApp
//
//  Created by Aldo Efrain Arreola Martinez on 26/08/26.
//

enum AuthConfiguration {
    static let clientId = ""

        static let tenantSubdomain = ""

        static let apiScopes: [String] = [
            // Ejemplo:
            // "api://XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX/access_as_user"
        ]
    static var isConfigured: Bool {
            !clientId.isEmpty && !tenantSubdomain.isEmpty
        }
}
