//
//  session.swift
//  MomentumiOS
//
//  Created by Mwai Banda on 7/21/22.
//  Copyright © 2022 Momentum. All rights reserved.
//

import Foundation
import MomentumSDK
import FirebaseAuth
import Combine
import TinyDi

final class Session: ObservableObject {
    @Inject private var authController: AuthController
    @Inject private var localDefaultsController: LocalDefaultsController
    var didChange = PassthroughSubject<Session, Never>()
    var handle: AuthStateDidChangeListenerHandle?
    @Published var currentUser: User? {
        didSet {
            didChange.send(self)
        }
    }
    
    func resetPassword(
        email: String,
        onCompletion: @escaping () -> Void = {}
    ){
        authController.resetPassword(email: email) { res in
          if let error = res.message {
                Log.d(tag: "Auth", error)
            } else {
                onCompletion()
            }
        }
    }
    
    func signIn(
        email: String,
        password: String,
        onCompletion: @escaping () -> Void = {}
    ){
        authController.signInWithEmail(
            email: email,
            password: password
        ) { [unowned self] res in
            if let user = res.data {
                self.currentUser = User(
                    email: user.email ?? "",
                    id: user.uid,
                    isGuest: user.isAnonymous
                )
                localDefaultsController.setString(
                    key: MultiplatformConstants.shared.EMAIL,
                    value: email
                )
                localDefaultsController.setString(
                    key: MultiplatformConstants.shared.PASSWORD,
                    value: password
                )
                onCompletion()
            } else if let error = res.message {
                Log.d(tag: "Auth", error)
            }
        }
    }
    func signUp(
        email: String,
        password: String,
        onCompletion: @escaping () -> Void = {}
    ){
        authController.signUpWithEmail(
            email: email,
            password: password
        ) { [unowned self] res in
            if let user = res.data {
                self.currentUser = User(
                    email: user.email ?? "",
                    id: user.uid,
                    isGuest: user.isAnonymous
                )
                onCompletion()
            } else if let error = res.message {
                Log.d(tag: "Auth", error)
            }
        }
    }
    func signInAsGuest() {
        authController.signInAsGuest { res in
            if let user = res.data {
                self.currentUser = User(
                    email: user.email ?? "",
                    id: user.uid,
                    isGuest: user.isAnonymous
                )
            } else if let error = res.message {
                Log.d(tag: "Auth", error)
            }
        }
    }
    func checkAndSignInAsGuest() {
        authController.checkAuthAndSignAsGuest(onCompletion: { res in
            if let user = res.data {
                self.currentUser = User(
                    email: user.email ?? "",
                    id: user.uid,
                    isGuest: user.isAnonymous
                )
                Log.d(tag: "Auth", user)
            } else if let error = res.message {
                Log.d(tag: "Auth", error)
            }
        })
    }
    
    func deleteCurrentUser(onCompletion: @escaping () -> Void = {}){
        authController.deleteUser()
        onCompletion()
    }
    
    func signOut(onCompletion: @escaping () -> Void = {}) {
        currentUser = nil
        authController.signOut()
        onCompletion()
    }
    


    struct User {
        var email: String
        var id: String
        var isGuest: Bool
    }
    
  
}
