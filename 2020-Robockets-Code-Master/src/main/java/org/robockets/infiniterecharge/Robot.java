/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robockets.infiniterecharge;

import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.robockets.infiniterecharge.LED.LEDPatterns;
import org.robockets.infiniterecharge.LED.LedStates;
import org.robockets.infiniterecharge.autonomous.ExactMovementCommand;
import org.robockets.infiniterecharge.autonomous.RotateCommand;
import org.robockets.infiniterecharge.autonomous.commandgroups.Auto0CommandGroup;
import org.robockets.infiniterecharge.autonomous.commandgroups.CenterShotCommandGroup;
import org.robockets.infiniterecharge.autonomous.commandgroups.TheJerkCommandGroup;
import org.robockets.infiniterecharge.climber.ClimberSubsystem;
import org.robockets.infiniterecharge.climber.LiftCommand;
import org.robockets.infiniterecharge.drivetrain.DrivetrainSubsystem;
import org.robockets.infiniterecharge.drivetrain.JoyrideCommand;
import org.robockets.infiniterecharge.shooter.IndexBallsCommand;
import org.robockets.infiniterecharge.shooter.MoveBallsCommand;
import org.robockets.infiniterecharge.shooter.ShooterSubsystem;
import org.robockets.infiniterecharge.wheel.SpinWheelCommand;
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

  private final SendableChooser<LedStates> LedState = new SendableChooser<>();
  private LedStates selectedLED;

  private CameraServer camera;

  public static DrivetrainSubsystem Drivetrain;
  public static WheelSubsystem Wheel;
  public static ClimberSubsystem Climber;
  public static ShooterSubsystem Shooter;
  public static OI m_oi;

  //endless commands
  private static JoyrideCommand joyride;
  private static IndexBallsCommand indexballs;
  private static MoveBallsCommand moveballs;
  private static SpinWheelCommand spinwheel;
  private static LiftCommand lift;

  //autocommands
  private static TheJerkCommandGroup thejerk;
  private static CenterShotCommandGroup centershot;
  private static Auto0CommandGroup auto0;
  private static ExactMovementCommand move;
  private static RotateCommand rotate;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    camera = CameraServer.getInstance();
    camera.startAutomaticCapture();

    configMotors();
    ledSetup();

    //subsystems
    Drivetrain = DrivetrainSubsystem.getInstance();
    Wheel = WheelSubsystem.getInstance();
    Shooter = ShooterSubsystem.getInstance();
    Climber = ClimberSubsystem.getInstance();
    m_oi = OI.getInstance();

  }

  private void configMotors() {
    RobotMap.PolyCordControllerBottom.getAllConfigs(new VictorSPXConfiguration());
    RobotMap.PolyCordControllerTop.getAllConfigs(new VictorSPXConfiguration());

    RobotMap.Intake.getAllConfigs(new VictorSPXConfiguration());
  }

  private void putData() {
    double RobotSpeed = (RobotMap.BackLeftEncoder.getVelocity() + RobotMap.BackRightEncoder.getVelocity() + RobotMap.FrontLeftEncoder.getVelocity() + RobotMap.FrontRightEncoder.getVelocity()) / 4.0;
    SmartDashboard.putNumber("Robot Speed", RobotSpeed);
    SmartDashboard.putNumber("Gyro Rotation", RobotMap.Gyro.getAngle()); //this might be reset (b/c of rot auto code)
    SmartDashboard.putString("Current Color",Wheel.color);
    SmartDashboard.putNumber("Balls in Chamber",Shooter.getBallCount());
    SmartDashboard.putNumber("Lift Position",RobotMap.FrontLeftEncoder.getPosition());
    SmartDashboard.putString("Go To Color",DriverStation.getInstance().getGameSpecificMessage());
  }

  private void ledSetup() {
    LedState.addOption("Rainbow", LedStates.Rainbow);
    LedState.addOption("Red",LedStates.Red);
    LedState.addOption("Blue",LedStates.Blue);
    LedState.addOption("Green",LedStates.Green);
    LedState.addOption("Yellow",LedStates.Yellow);
    LedState.addOption("Purple",LedStates.Purple);
    LedState.addOption("Orange",LedStates.Orange);
    LedState.addOption("White",LedStates.White);
    LedState.addOption("Blink Blue",LedStates.BlinkBlue);
    LedState.addOption("Blink Red",LedStates.BlinkRed);
    LedState.addOption("Blink Purple",LedStates.BlinkPurple);
    LedState.addOption("Breathe Blue",LedStates.BreatheBlue);
    LedState.addOption("Breathe Red",LedStates.BreatheRed);
    LedState.addOption("Move Left",LedStates.MoveLeft);
    LedState.addOption("Move Right",LedStates.MoveRight);

    LedState.setDefaultOption("Disabled",LedStates.Disabled);
    LedState.addOption("Robot Decides",LedStates.AutoDecided); //when certain commands trigger usage instead

    SmartDashboard.putData("Led Choices",LedState);
  }

  private void ledLoop() {
      selectedLED = LedState.getSelected();

      switch(selectedLED) {
        case Rainbow: LEDPatterns.rainbow(); break;
        case Red: LEDPatterns.red(1,1); break;
        case Green: LEDPatterns.green(1,1); break;
        case Blue: LEDPatterns.blue(1,1); break;
        case Yellow: LEDPatterns.pattern(255,255,0,1,1); break;
        case Orange: LEDPatterns.pattern(255,128,0,1,1); break;
        case Purple: LEDPatterns.pattern(255,0,255,1,1); break;
        case White: LEDPatterns.pattern(255,255,255,1,1); break;
        case MoveLeft: LEDPatterns.colorflow(0,255,255,8); break;
        case Disabled: LEDPatterns.pattern(0,0,0,0,1); break;
        default: break; //default should do nothing, and leave it to the robot in debug areas to decide
      }
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
    putData();

    //Misc functions that need to be ran (non-mechanical)
    Wheel.setColor();
    //ledLoop();

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

    thejerk.start();

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

  /*@Override
  public void disabledInit() {
    joyride.close();
    //indexballs.start();
    moveballs.close();
    movearm.close();
    spinwheel.close();
    detectcolor.close();
    Telescope.close();
    Reel.close();
  }*/
}
