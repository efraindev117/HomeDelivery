//
//  NativeAuthConfiguration.swift
//  iosApp
//
//  Created by Aldo Efrain Arreola Martinez on 26/08/26.
//

enum NativeAuthConfiguration {
    static let clientId = ""
    static let tenantSubdomain = ""
    static let apiScopes: [String] = []
    static var isConfigured: Bool {
        !clientId.isEmpty &&
        !tenantSubdomain.isEmpty
    }
}
