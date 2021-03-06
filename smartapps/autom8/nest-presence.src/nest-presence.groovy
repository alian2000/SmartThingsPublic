/**
 *  Nest Thermostat SmartApp
 */
 
definition(
    name: "Nest Presence",
    namespace: "autom8",
    author: "autom8",
    description: "Set your Nest thermostat to away or off when you leave home.",
    category: "Green Living",
    iconUrl: "https://raw.githubusercontent.com/autom8/NestSmartApp/master/nest_icon.png",
    iconX2Url: "https://raw.githubusercontent.com/autom8/NestSmartApp/master/nest_icon.2x.png"
)

preferences {
	page(name: "Home", title: "Nest SmartApp", install: true, uninstall: true) {
        section("Choose Nest themostat... "){
            input "myThermostat", "capability.Thermostat", multiple: true, title: "Thermostat"
        }
        section("Switch Nest to away when mode is...") {
            input "myAwayMode", "mode", multiple: true, required: false
        }
        section("Switch Nest to home when mode is...") {
            input "myHomeMode", "mode", multiple: true, required: false
        }
        section("Switch Nest to off when mode is...") {
            input "myOffMode", "mode", multiple: true, required: false
        }
		section("Send push notification when mode changes?") {
			input "sendPush", "bool", required: true, title: "On = Yes, Off = No", defaultValue: true
		}
    }
}

def installed() {
    log.debug "Installed with settings: ${settings}"
    initialize()
}

def updated() {
    log.debug "Updated with settings: ${settings}"
    unsubscribe()
    initialize()
}

def initialize() {
	subscribe (location, "mode", modeChangeHandler)
}

def modeChangeHandler(evt) {
	log.debug "modeChangeHandler called: $evt.value"
	if (myAwayMode.contains(evt.value)) {
        log.debug "Set Nest Thermostat to Away"
		sendMessage("Away")
		myThermostat?.heat()
		myThermostat?.away()
	}
    else if (myHomeMode.contains(evt.value)) {
        log.debug "Set Nest Thermostat to Home"
		sendMessage("Home")
		myThermostat?.heat()
		myThermostat?.present()
	}
    else if (myOffMode.contains(evt.value)) {
        log.debug "Set Nest Thermostat to Off"
		sendMessage("Off")
		myThermostat?.off()
	}
}

private sendMessage(msg) {
	if (sendPush) {
		sendPush("Heating mode set to ${msg}")
	}
}