import SwiftUI
import shared

@main
struct iOSApp: App {

    init(){
        KoinModuleKt.doInitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
