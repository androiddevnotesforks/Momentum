//
//  session.swift
//  MomentumiOS
//
//  Created by Mwai Banda on 7/21/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import MomentumSDK
import FirebaseAuth
import Combine

final class Session: ObservableObject {
    @Inject private var auth: Auth
    @Inject private var authController: AuthController
    var didChange = PassthroughSubject<Session, Never>()
    var handle: AuthStateDidChangeListenerHandle?
    @Published var currentUser: User? {
        didSet {
            didChange.send(self)
        }
    }
    func observerAuth() {
        handle = auth.addStateDidChangeListener({ [weak self] auth, user in
            if let user = user {
                self?.currentUser = User(
                    email: user.email ?? "",
                    id: user.uid,
                    isGuest: user.isAnonymous
                )
            }
        })
    }
    
    func signIn(
        email: String,
        password: String,
        onCompletion: @escaping () -> Void
    ){
        authController.signInWithEmail(
            email: email,
            password: password
        ) { [unowned self] res in
            self.currentUser = User(
                email: res.email ?? "",
                id: res.uid,
                isGuest: res.isAnonymous
            )
            onCompletion()
        }
    }
    func signUp(
        email: String,
        password: String,
        onCompletion: @escaping () -> Void
    ){
        authController.signUpWithEmail(
            email: email,
            password: password
        ) { [unowned self] res in
            self.currentUser = User(
                email: res.email ?? "",
                id: res.uid,
                isGuest: res.isAnonymous
            )
            onCompletion()
        }
    }
    func signInAsGuest() {
        authController.signInAsGuest { res in
            self.currentUser = User(
                email: res.email ?? "",
                id: res.uid,
                isGuest: res.isAnonymous
            )
        }
    }
    func checkAndSignInAsGuest() {
        authController.checkAuthAndSignAsGuest(onCompletion: { res in
            self.currentUser = User(
                email: res.email ?? "",
                id: res.uid,
                isGuest: res.isAnonymous
            )
        })
    }
    
    func deleteCurrentUser(onCompletion: @escaping () -> Void){
        authController.deleteUser()
        onCompletion()
    }
    
    func signOut(onCompletion: @escaping () -> Void) {
        authController.signOut()
        onCompletion()
    }
    
    func unbind() {
        if let handle = handle {
            auth.removeStateDidChangeListener(handle)
        }
    }

    struct User {
        var email: String
        var id: String
        var isGuest: Bool
    }
    
    deinit {
        unbind()
    }
}
