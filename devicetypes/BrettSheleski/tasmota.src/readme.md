# Tasmota
Set of SmartThings device-handlers for devices running the [Sonoff-Tasmota](https://github.com/arendst/Sonoff-Tasmota) firmware

## How to use
You will need to install the 'Tasmota' device handler along with other device handlers for module-specific device handlers.

## Device Handlers
### Tasmota
This is device handler is required for all module types.  This device handler determines your Tasmota-device's module and spawns child SmartThings devices accordingly.

For Sonoff RF Bride modules, sixteen Button child devices will be created.

### Tasmota-Power
This device handler is used to define the devices that get spawned for switch-like devices.  It is used for the following device types (possibly/probably more):

For Sonoff Basic modules, a single Switch child devices will be created.

For Sonoff Dual modules, two Switch child devices will be created.

For Sonoff 4ch modules, four Switch child devices will be created

### Tasmota-RF-Bridge Button
This device handler is used to define the deives that get spawned for the Sonoff RF Bridge device.  Each instance of this device handler corresponds to one of the 16 commands that can be captured/sent by the Sonoff RF Bridge.

### Example installation for Sonoff Basic
In addition to the Tasmota device handler, the Tasmota-Power device handler will also need to be installed.

(This process is likely the same for many module types, but is not yet tested.)

#### Install the Tasmota Device Handler.
This device handler is necessary for all module types.  This device handler will make an HTTP call to the Tasmota device to determine the module type.  Depending on the module type defined, it will create child devices.
1. Log in to the SmartThings IDE (https://graph.api.smartthings.com/)
2. Go to `My Device Handlers`
3. Click `Create New Device Handler`
4. In the `From Code` tab paste in the code from https://github.com/BrettSheleski/SmartThingsPublic/blob/master/devicetypes/BrettSheleski/tasmota.src/tasmota.groovy
5. Click `Create`
6. Click `Publish` --> `For Me`

#### Install the Tasmota-Power Device Handler
For Sonoff-Basic, Sonoff-4CH, and Sonoff Dual devices this is required
1. Log in to the SmartThings IDE (https://graph.api.smartthings.com/)
2. Go to `My Device Handlers`
3. Click `Create New Device Handler`
4. In the `From Code` tab paste in the code from https://github.com/BrettSheleski/SmartThingsPublic/blob/master/devicetypes/BrettSheleski/tasmota.src/tasmota-power.groovy
5. Click `Create`
6. Click `Publish` --> `For Me`


#### Add your Device
1.  Log in to the SmartThings IDE (https://graph.api.smartthings.com/)
2.  Go to `My Devices`
3.  Click `New Device`
4.  Give your device a `Name`
5.  Enter a unique `Device Network ID` for your device
6.  In the `Type` dropdown, scroll to the bottom and select the `Tasmota` device.
7.  Choose your `Location`
8.  Choose your `Hub`
9.  Click `Create`
10. In the Edit Device page, click the Edit link in the Preferences area
11. Enter the `IP Address` of your Tasmota device (Required)
12.  Enter the `Username` your device uses (Optional)
13.  Enter the `Password` for your device (Optional)
14.  Click save

After adding and configuring the Tasmota device to your SmartThings account, you should be able to open the SmartThings app and you will see the device you created, as well as a device with the same name followed by the `Switch` suffix.  The `Switch` device will be the device you would normally interact with.