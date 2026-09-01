import SwiftUI
import shared

@main
struct iOSApp: App {

    init(){
        #if DEBUG
        AppLogger.shared.configure(enabled: true)
        #endif
        KoinModuleKt.doInitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
