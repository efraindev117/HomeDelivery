//
//  NativeAuthClient.swift
//  iosApp
//
//  Created by Aldo Efrain Arreola Martinez on 26/08/26.
//

import MSAL

enum NativeAuthClientError: LocalizedError {
    case invalidCredentials
    case noSignedInAccount
    case operationInProgress
    case signInFailed(String)
    case accessTokenFailed(String)

    var errorDescription: String? {
        switch self {
        case .invalidCredentials:
            return "Email and password are required."

        case .noSignedInAccount:
            return "There is no signed-in account."

        case .operationInProgress:
            return "An authentication operation is already in progress."

        case .signInFailed(let message):
            return message

        case .accessTokenFailed(let message):
            return message
        }
    }
}

final class NativeAuthClient: NSObject {

    private let nativeAuth: MSALNativeAuthPublicClientApplication

    private let scopes: [String]

    private var accountResult: MSALNativeAuthUserAccountResult?

    private var signInCompletion:
        ((Result<Void, Error>) -> Void)?

    private var tokenCompletion:
        ((Result<String, Error>) -> Void)?

    init(
        clientId: String,
        tenantSubdomain: String,
        scopes: [String]
    ) throws {
        self.scopes = scopes

        nativeAuth = try MSALNativeAuthPublicClientApplication(
            clientId: clientId,
            tenantSubdomain: tenantSubdomain,
            challengeTypes: [.OOB, .password]
        )

        super.init()

        restoreAccount()
    }

    var isSignedIn: Bool {
        accountResult != nil
    }

    private func restoreAccount() {
        accountResult = nativeAuth.getNativeAuthUserAccount()
    }

    func signIn(
        email: String,
        password: String,
        completion: @escaping (Result<Void, Error>) -> Void
    ) {
        guard !email.isEmpty, !password.isEmpty else {
            completion(
                .failure(
                    NativeAuthClientError.invalidCredentials
                )
            )
            return
        }

        guard signInCompletion == nil else {
            completion(
                .failure(
                    NativeAuthClientError.operationInProgress
                )
            )
            return
        }

        signInCompletion = completion

        let parameters = MSALNativeAuthSignInParameters(
            username: email
        )

        parameters.password = password

        nativeAuth.signIn(
            parameters: parameters,
            delegate: self
        )
    }

    func getAccessToken(
        completion: @escaping (Result<String, Error>) -> Void
    ) {
        guard tokenCompletion == nil else {
            completion(
                .failure(
                    NativeAuthClientError.operationInProgress
                )
            )
            return
        }

        guard let accountResult else {
            completion(
                .failure(
                    NativeAuthClientError.noSignedInAccount
                )
            )
            return
        }

        tokenCompletion = completion

        let parameters =
            MSALNativeAuthGetAccessTokenParameters()

        if !scopes.isEmpty {
            parameters.scopes = scopes
        }

        accountResult.getAccessToken(
            parameters: parameters,
            delegate: self
        )
    }

    func signOut() {
        accountResult?.signOut()
        accountResult = nil
    }
}

extension NativeAuthClient: SignInStartDelegate {

    func onSignInStartError(
        error: MSAL.SignInStartError
    ) {
        let message =
            error.errorDescription
            ?? "Unable to sign in."

        signInCompletion?(
            .failure(
                NativeAuthClientError.signInFailed(
                    message
                )
            )
        )

        signInCompletion = nil
    }

    func onSignInCompleted(
        result: MSAL.MSALNativeAuthUserAccountResult
    ) {
        accountResult = result

        signInCompletion?(
            .success(())
        )

        signInCompletion = nil
    }
}

extension NativeAuthClient: CredentialsDelegate {

    func onAccessTokenRetrieveError(
        error: MSAL.RetrieveAccessTokenError
    ) {
        let message =
            error.errorDescription
            ?? "Unable to retrieve access token."

        tokenCompletion?(
            .failure(
                NativeAuthClientError.accessTokenFailed(
                    message
                )
            )
        )

        tokenCompletion = nil
    }

    func onAccessTokenRetrieveCompleted(
        result: MSALNativeAuthTokenResult
    ) {
        tokenCompletion?(
            .success(result.accessToken)
        )

        tokenCompletion = nil
    }
}
