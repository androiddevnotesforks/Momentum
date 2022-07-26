//
//  SignUpView.swift
//  MomentumiOS
//
//  Created by Mwai Banda on 7/22/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SignUpView: View {
    var namespace: Namespace.ID
    var onSignUpCompletion: () -> Void
    @EnvironmentObject var session: Session
    @State private var fullname = ""
    @State private var email = ""
    @State private var password = ""
    @State private var confirmPassword = ""

    var body: some View {
        VStack(alignment: .leading) {
            VStack(alignment: .leading) {
            Text("Sign Up")
                .font(.title3)
                .fontWeight(.heavy)
                .foregroundColor(.white)
                .padding(.horizontal)
                .padding(.top)
            Text("Create An Account To Proceed")
                .font(.subheadline)
                .foregroundColor(.white.opacity(0.3))
                .padding(.horizontal)
                .padding(.bottom, 5)
            Divider()
                .overlay(Color(.white).opacity(0.5))
                .padding(.bottom)
            }.matchedGeometryEffect(id: "title", in: namespace)

            Group {
            TextField("", text: $fullname)
                .padding(.leading, 24)
                .placeholder(when: fullname.isEmpty) {
                    Text("Fullname")
                        .font(.headline)
                        .fontWeight(.regular)
                        .foregroundColor(.white)
                        .padding(.leading, 24)
                    
                }
                .foregroundColor(.white)
                .overlay(
                    HStack{
                        Image(systemName: "person")
                        Spacer()
                    }
                        .foregroundColor(Color.white)
                    
                )
                .ignoresSafeArea(.keyboard, edges: .bottom)
                .padding(.horizontal)
            Divider()
                .overlay(Color(.white).opacity(0.5))
                .padding(.vertical)
            TextField("", text: $email)
                .padding(.leading, 24)
                .placeholder(when: email.isEmpty) {
                    Text("Email")
                        .font(.headline)
                        .fontWeight(.regular)
                        .foregroundColor(.white)
                        .padding(.leading, 24)
                    
                }
                .foregroundColor(.white)
                .overlay(
                    HStack{
                        Image(systemName: "envelope")
                        Spacer()
                    }
                        .foregroundColor(Color.white)
                    
                )
                .ignoresSafeArea(.keyboard, edges: .bottom)
                .padding(.horizontal)
                .matchedGeometryEffect(id: "email", in: namespace)
            Divider()
                .overlay(Color(.white).opacity(0.5))
                .padding(.vertical)
            
            PasswordTextfield(password: $password, placeholder: "Password") {
                
            }
            .padding(.horizontal)
            .matchedGeometryEffect(id: "password", in: namespace)
            Divider()
                .overlay(Color(.white).opacity(0.5))
                .padding(.vertical)
            PasswordTextfield(password: $confirmPassword, placeholder: "Confirm Password") {
                
            }
            .padding(.horizontal)
            }
            Spacer()
            HStack {
                Spacer()
                Button {
                    if !email.isEmpty && !password.isEmpty {
                        session.signUp(email: email, password: password, onCompletion: {
                            onSignUpCompletion()
                        })
                    }
                } label: {
                    Text("Confirm")
                        .fontWeight(.heavy)
                        .frame(width: screenBounds.width - 120, height: 55)
                }
                .buttonStyle(FilledButtonStyle())
                .padding(.bottom, 10)
                Spacer()
            }.matchedGeometryEffect(id: "button", in: namespace)

        }
    }
}


