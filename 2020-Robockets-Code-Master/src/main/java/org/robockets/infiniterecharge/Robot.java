/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robockets.infiniterecharge;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.robockets.infiniterecharge.LED.LEDSubsystem;
import org.robockets.infiniterecharge.climber.ClimberSubsystem;
import org.robockets.infiniterecharge.drivetrain.DrivetrainSubsystem;
import org.robockets.infiniterecharge.drivetrain.JoyrideCommand;
import org.robockets.infiniterecharge.shooter.IndexBallsCommand;
import org.robockets.infiniterecharge.shooter.ShooterSubsystem;
import org.robockets.infiniterecharge.wheel.MoveArmCommand;
import org.robockets.infiniterecharge.wheel.WheelSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static DrivetrainSubsystem Drivetrain;
  public static WheelSubsystem Wheel;
  public static ClimberSubsystem Climber;
  public static ShooterSubsystem Shooter;
  private static LEDSubsystem led;
  public static OI m_oi;

  private static JoyrideCommand joyride;
  private static MoveArmCommand movePiston;
  private static IndexBallsCommand indexballs;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    Drivetrain = DrivetrainSubsystem.getInstance();
    Wheel = WheelSubsystem.getInstance();
    Shooter = ShooterSubsystem.getInstance();
    Climber = ClimberSubsystem.getInstance();
    led = LEDSubsystem.getInstance();
    m_oi = OI.getInstance();

    joyride = new JoyrideCommand();
    indexballs = new IndexBallsCommand();
    //TankrideCommand Tankride = new TankrideCommand(Drivetrain);

    //CameraServer.getInstance().startAutomaticCapture();

    SmartDashboard.putNumber("Motor movement", RobotMap.FrontLeft.get());
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
    //joyride.start();
    indexballs.start();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
