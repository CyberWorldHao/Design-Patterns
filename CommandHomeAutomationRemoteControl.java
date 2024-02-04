/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab7.Exercise;

/**
 *
 * @author CWH
 */
interface Command {

    public void execute();

    public void undo();
}

class NoCommand implements Command {

    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }
}

class MacroCommand implements Command {

    Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    /**
     * NOTE: these commands have to be done backwards to ensure proper undo
     * functionality
     */
    @Override
    public void undo() {
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
    }
}

public class CommandHomeAutomationRemoteControl {

    public static void remoteLoader() {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        CeilingFan ceilingFan = new CeilingFan("Living Room");
        GarageDoor garageDoor = new GarageDoor("");
        Stereo stereo = new Stereo("Living Room");

        LightOnCommand livingRoomLightOn
                = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff
                = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOn
                = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff
                = new LightOffCommand(kitchenLight);

        CeilingFanOnCommand ceilingFanOn
                = new CeilingFanOnCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff
                = new CeilingFanOffCommand(ceilingFan);

        GarageDoorUpCommand garageDoorUp
                = new GarageDoorUpCommand(garageDoor);
        GarageDoorDownCommand garageDoorDown
                = new GarageDoorDownCommand(garageDoor);

        StereoOnWithCDCommand stereoOnWithCD
                = new StereoOnWithCDCommand(stereo);
        StereoOffCommand stereoOff
                = new StereoOffCommand(stereo);

        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteControl.setCommand(2, ceilingFanOn, ceilingFanOff);
        remoteControl.setCommand(3, garageDoorUp, garageDoorDown);
        remoteControl.setCommand(4, stereoOnWithCD, stereoOff);

        System.out.println(remoteControl);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.onButtonWasPushed(2);
        remoteControl.offButtonWasPushed(2);
        remoteControl.onButtonWasPushed(3);
        remoteControl.offButtonWasPushed(3);
        remoteControl.onButtonWasPushed(4);
        remoteControl.offButtonWasPushed(4);
    }

    public static void testingCeilingFan() {

        RemoteControl remoteControl = new RemoteControl();
        CeilingFan ceilingFan = new CeilingFan("Living Room");
        CeilingFanHighCommand ceilingFanHigh
                = new CeilingFanHighCommand(ceilingFan);
        CeilingFanMediumCommand ceilingFanMedium
                = new CeilingFanMediumCommand(ceilingFan);
        CeilingFanLowCommand ceilingFanLow
                = new CeilingFanLowCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff
                = new CeilingFanOffCommand(ceilingFan);

        remoteControl.setCommand(0, ceilingFanLow, ceilingFanOff);
        remoteControl.setCommand(1, ceilingFanMedium, ceilingFanOff);
        remoteControl.setCommand(2, ceilingFanHigh, ceilingFanOff);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        System.out.println(remoteControl);
        remoteControl.undoButtonWasPushed();

        remoteControl.onButtonWasPushed(1);
        System.out.println(remoteControl);
        remoteControl.undoButtonWasPushed();

    }

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light light = new Light("Living Room");
        TV tv = new TV("Living Room");
        Stereo stereo = new Stereo("Living Room");
        Hottub hottub = new Hottub();

        // ON
        LightOnCommand lightOn = new LightOnCommand(light);
        StereoOnCommand stereoOn = new StereoOnCommand(stereo);
        TVOnCommand tvOn = new TVOnCommand(tv);
        HottubOnCommand hottubOn = new HottubOnCommand(hottub);

        // OFF
        LightOffCommand lightOff = new LightOffCommand(light);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);
        TVOffCommand tvOff = new TVOffCommand(tv);
        HottubOffCommand hottubOff = new HottubOffCommand(hottub);

        Command[] partyOn = {lightOn, stereoOn, tvOn, hottubOn};
        Command[] partyOff = {lightOff, stereoOff, tvOff, hottubOff};

        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);

        remoteControl.setCommand(0, partyOnMacro, partyOffMacro);

        /*-------- Macro Command --------*/
        System.out.println("\n***-------- Macro Command --------***");
        System.out.println(remoteControl);
        System.out.println("--- Pushing Macro On---");
        remoteControl.onButtonWasPushed(0);
        System.out.println("--- Pushing Macro Off---");
        remoteControl.offButtonWasPushed(0);
        System.out.println("--- Pushing Macro Undo---");
        remoteControl.undoButtonWasPushed();

        /*-------- Remote Loader --------*/
        System.out.println("\n***-------- Remote Loader --------***");
        remoteLoader();

        /*-------- Testing Ceiling Fan --------*/
        System.out.println("\n***-------- Testing Ceiling Fan --------***\n");
        testingCeilingFan();

    }
}
