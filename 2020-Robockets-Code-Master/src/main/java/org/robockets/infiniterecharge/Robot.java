/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robockets.infiniterecharge;

import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.robockets.infiniterecharge.LED.LEDPatterns;
import org.robockets.infiniterecharge.LED.LedStates;
import org.robockets.infiniterecharge.autonomous.AutoChoices;
import org.robockets.infiniterecharge.autonomous.commandgroups.*;
import org.robockets.infiniterecharge.climber.ClimberSubsystem;
import org.robockets.infiniterecharge.drivetrain.DrivetrainSubsystem;
import org.robockets.infiniterecharge.shooter.IndexBallsCommand;
import org.robockets.infiniterecharge.shooter.ShooterSubsystem;
import org.robockets.infiniterecharge.wheel.WheelSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final SendableChooser<AutoChoices> AutoChooser = new SendableChooser<>();
  private final SendableChooser<LedStates> LedState = new SendableChooser<>();

  //subsystems
  public static DrivetrainSubsystem Drivetrain;
  public static WheelSubsystem Wheel;
  public static ClimberSubsystem Climber;
  public static ShooterSubsystem Shooter;
  public static OI m_oi;

  //scheduler.
  private Scheduler scheduler;

  private void configMotors() {
    RobotMap.PolyCordControllerBottom.getAllConfigs(new VictorSPXConfiguration());
    RobotMap.PolyCordControllerTop.getAllConfigs(new VictorSPXConfiguration());
    RobotMap.Intake.getAllConfigs(new VictorSPXConfiguration());
  }

  private void autoSetup() {
    SmartDashboard.putData("Auto choices", AutoChooser);

    AutoChooser.setDefaultOption("No auto",AutoChoices.NoAuto);
    AutoChooser.addOption("Auto 0", AutoChoices.Auto0);
    //Autochooser.addOption("Center Shot", AutoChoices.CenterShot);
    AutoChooser.addOption("Left to Low", AutoChoices.LefttoLow);
    AutoChooser.addOption("Right to Low", AutoChoices.RighttoLow);
    AutoChooser.addOption("Middle to Low", AutoChoices.MiddletoLow);
    AutoChooser.addOption("The jerk",AutoChoices.TheJerk);
    AutoChooser.addOption("Shoot And Go",AutoChoices.ShootAndGo);
    AutoChooser.addOption("Shoot From Right",AutoChoices.ShootFromRight);
    AutoChooser.addOption("Test Auto",AutoChoices.TestAuto);
  }

  private void ledSetup() {
    RobotMap.led.setLength(RobotMap.ledBuffer.getLength());

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

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture();
    configMotors();
    autoSetup();
    ledSetup();
    scheduler = Scheduler.getInstance();

    //subsystems
    Drivetrain = DrivetrainSubsystem.getInstance();
    Wheel = WheelSubsystem.getInstance();
    Shooter = ShooterSubsystem.getInstance();
    Climber = ClimberSubsystem.getInstance();
    m_oi = OI.getInstance();

    //non-default & endless commands
    IndexBallsCommand index = new IndexBallsCommand();

    //adding commands to scheduler
    scheduler.add(index);
    index.start();
  }

  private void ledLoop() {
      LedStates m_selectedLED = LedState.getSelected();

      switch(m_selectedLED) {
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

  private void updateData() {
    double RobotSpeed = (RobotMap.BackLeftEncoder.getVelocity() + RobotMap.BackRightEncoder.getVelocity() + RobotMap.FrontLeftEncoder.getVelocity() + RobotMap.FrontRightEncoder.getVelocity()) / 4.0;
    SmartDashboard.putNumber("Robot Speed", RobotSpeed);
    SmartDashboard.putNumber("Gyro Rotation", RobotMap.Gyro.getAngle()); //this might be reset (b/c of rot auto code)
    SmartDashboard.putString("Current Color",Wheel.color);
    SmartDashboard.putNumber("Balls in Chamber",Shooter.getBallCount());
    SmartDashboard.putNumber("Lift Position",RobotMap.FrontLeftEncoder.getPosition());
    SmartDashboard.putString("Go To Color",DriverStation.getInstance().getGameSpecificMessage());
    SmartDashboard.putData("Wheel PID Controller",RobotMap.wheelPIDController);
    SmartDashboard.putData("Gyro PID Controller", RobotMap.gyroPIDController);
    SmartDashboard.putNumber("Shooter RPM",RobotMap.FlyWheelBottomEncoder.getVelocity());
    SmartDashboard.putBoolean("Shooter Break beam",RobotMap.flywheelBreakBeam.get());
    SmartDashboard.putBoolean("Intake break beam: " , RobotMap.intakeBreakBeam.get());
  }

  private void testThing() { //for testing out random shit. It's a completely misc task
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
      scheduler.run();
    //Misc functions that need to be ran (non-mechanical)
    Wheel.setColor();
    m_oi.periodic();
    ledLoop();
    updateData();
    testThing();
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
      AutoChoices m_autoSelected = AutoChooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);

    switch (m_autoSelected) {
      case Auto0: new InitLineCommandGroup(); break;
      case TheJerk: new TheJerkCommandGroup(); break;
      case LefttoLow: new LefttoLowCommandGroup();  break;
      case CenterShot: new CenterShotCommandGroup(); break;
      case RighttoLow: new RighttoLowCommandGroup(); break;
      case MiddletoLow: new MiddletoLowCommandGroup(); break;
      case ShootAndGo: new ShootAndGoCommandGroup(); break;
      case ShootFromRight: new ShootFromRightCommandGroup(); break;
      case TestAuto: new TestAutoCommandGroup(); break;
      default: break;
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

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
    //indexballs.close();
    moveballs.close();
    movearm.close();
    spinwheel.close();
    detectcolor.close();
    Telescope.close();
    Reel.close();
  }*/
}
