import SwiftUI
import shared
import GoogleMaps

@main
struct iOSApp: App {

    init(){
        KoinModuleKt.doInitKoinIos()
        GMSServices.provideAPIKey("AIzaSyAKyCCfvcRZvzlt9qNoUWSLps0NEOy6_v8")
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
