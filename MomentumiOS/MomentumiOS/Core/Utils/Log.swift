//
//  Log.swift
//  MomentumiOS
//
//  Created by Mwai Banda on 7/2/22.
//  Copyright © 2022 Mwai Banda. All rights reserved.
//

import Foundation

struct Log {
    static func d(tag: String, message: Any) {
        print("[\(tag)]: \(message)")
    }
}
