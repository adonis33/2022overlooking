package frc.robot.component;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class SuckBall {
    public static Compressor com;
    public static DoubleSolenoid sol;
    public static WPI_VictorSPX suck;
    private static int ksuck = 17;

    public static void init() {
        com = new Compressor(PneumaticsModuleType.CTREPCM);
        sol = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
        suck = new WPI_VictorSPX(ksuck);
        com.enableDigital();
    }

    private static boolean a = false;
    private static boolean b = false;

    public static void teleop() {

        boolean com_switch = SmartDashboard.getBoolean("Compressor", true);

        if (com_switch) {
            com.enableDigital();
        } else if (!com_switch) {
            com.disable();
        }

        if (Robot.maincontrol.getYButton()) {
            a = true;
        }
        else if(Robot.maincontrol.getRawButtonPressed(9)){
            b = !b;
        }
        else{
            a=false;
        }

        if (a == true&&b == false) {
            sol.set(Value.kForward);
            suck.set(0.7);
        }
        else if(b&&a == false){
            sol.set(Value.kForward);
        }
        else if(b&&Robot.maincontrol.getRawButton(10)&&a == false){
            suck.set(0.7);
        }
        else {
            sol.set(Value.kReverse);
            suck.set(0);
        }


    }

    public static void autoSuck(double v){
        suck.set(v);
    }
}