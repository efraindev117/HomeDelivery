//
//  IosAuthProvider.swift
//  iosApp
//
//  Created by Aldo Efrain Arreola Martinez on 26/08/26.
//

import Foundation

final class IosAuthProvider {

    private let authClient: NativeAuthClient

    init(
        authClient: NativeAuthClient
    ) {
        self.authClient = authClient
    }

    var isSignedIn: Bool {
        authClient.isSignedIn
    }

    func signIn(
        email: String,
        password: String,
        completion: @escaping (Result<String, Error>) -> Void
    ) {
        authClient.signIn(
            email: email,
            password: password
        ) { [weak self] result in

            switch result {

            case .success:
                self?.getAccessToken(
                    completion: completion
                )

            case .failure(let error):
                completion(
                    .failure(error)
                )
            }
        }
    }

    func getAccessToken(
        completion: @escaping (Result<String, Error>) -> Void
    ) {
        authClient.getAccessToken(
            completion: completion
        )
    }

    func signOut() {
        authClient.signOut()
    }
}
