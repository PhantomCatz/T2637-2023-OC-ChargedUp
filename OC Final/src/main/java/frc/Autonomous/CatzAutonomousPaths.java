package frc.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

/***************************************************************************
    *
    * Autonomous selections
    * 
***************************************************************************/
public class CatzAutonomousPaths
{  
    private final SendableChooser<Boolean> chosenAllianceColor = new SendableChooser<>();
    private final SendableChooser<Integer> chosenPath = new SendableChooser<>();
    // private final SendableChooser<Boolean> chosenBalance = new SendableChooser<>();
    // private final SendableChooser<Boolean> chosenCube = new SendableChooser<>();


    private final double STRAIGHT = 0.0;
    public static double CORRECTED_STRAIGHT = 0.0;
    public static double CORRECTED_RIGHT = -90.0; //RIGHT = negative for reasons
    public static double RIGHT = -90.0; 
    public static double LEFT  = 90.0;

    private final double TEMP_MAX_TIME = 3.0;
    private final double TEMP_DECEL_DIST = 0.1;
    private final double MIN_DIST = 20.0;
    private final double MAX_DIST = 224.0;
    private final double CLR_COMM = 100.0;
    private final double GP_TO_GP = 48.0;

    private final double INDEXER_EJECT_TIME = 0.5;

    public static boolean doBalance = false; // default
    public static boolean score = false;

    public static int Path;
    
    //MAX SPEED
    public static double FAST = 0.35;
    public static double SLOW = 0.25;

     /*  DRIVE STRAIGHT VALUES: 
     * if distance > 70, then FAST, else SLOW
     * 8 second maxTime is an arbitrary number, subject to change upon more testing 
     * only robot backwards movement has negative signs over distance and maxspeed
     * left and right distances and max speed aren't negative
     * TEMP_DECEL_DIST decelDistance is an arbitrary number, subject to change upon more testing
     * 
     *   *note* - autonomous is 15 seconds, meaning that all of this will have to finsih within that time
     *          - THIS CODE IS MADE FOR BLUE SIDE 
     *          - FOR RED, CHANGE LEFTS WITH RIGHTS AND RIGHTS WITH LEFTS (from blue)
     *          - movement similar to code.org level programming
    */

    /* PATH NAME:
     *    /CenterRightTunnel/
     * - CenterRight (Starting Position)
     * - Tunnel (type of movement/movement path)
     */

    /* Distances:          -______-
     * drive.DriveStraight(distance, decelDistance, maxSpeed, wheelPos, maxTime);
     *  - 224 = distance from grid to center pieces
     *                
     */
    // drive.DriveStraight(distance, decelDist, )
    public CatzAutonomousPaths() {}


    public void initializePathOptions()
    {
        chosenAllianceColor.setDefaultOption("Blue Alliance", Robot.constants.BLUE_ALLIANCE);
        chosenAllianceColor.addOption("Red Alliance", Robot.constants.RED_ALLIANCE);
        SmartDashboard.putData(Robot.constants.ALLIANCE_COLOR, chosenAllianceColor);

        chosenPath.setDefaultOption(Robot.constants.POSITION_SELECTOR1, 1);
        chosenPath.addOption(Robot.constants.POSITION_SELECTOR2, 2);
        chosenPath.addOption(Robot.constants.POSITION_SELECTOR3, 3);
        // chosenPath.addOption(Robot.constants.POSITION_SELECTOR5, 5);
        // chosenPath.addOption(Robot.constants.POSITION_SELECTOR6, 6);
        // chosenPath.addOption(Robot.constants.POSITION_SELECTOR7, 7);
        // chosenPath.addOption(Robot.constants.POSITION_SELECTOR8, 8);
        // chosenPath.addOption(Robot.constants.POSITION_SELECTOR9, 9);
        SmartDashboard.putData(Robot.constants.ALLIANCE_POSITION, chosenPath);

        // chosenBalance.setDefaultOption("Don't Balance", Robot.constants.NO_BALANCE);
        // chosenBalance.addOption("Do Balance", Robot.constants.YES_BALANCE);
        // SmartDashboard.putData(Robot.constants.BALANCE, chosenBalance);

        // chosenCube.setDefaultOption("Regular Version", Robot.constants.REGULAR_PATH);
        // chosenCube.addOption("Cube Version", Robot.constants.CUBE_PATH);
        // SmartDashboard.putData(Robot.constants.PATH_TYPE, chosenCube);


    }

    public void Red() {
        RIGHT *= -1;
        LEFT *= -1;
        System.out.println("RED SIDE");
    }
    
    // public void ScoreCube() 
    // {
    //     //FAST *= -1;
    //     //SLOW *= -1;
    //     score = true;
    //     System.out.println("Cube Only");
    // }

    public void CubeIndexerScore()
    {
        System.out.println("Indexer Score");
        Robot.indexer.indexerFrntEject();
        Timer.delay(INDEXER_EJECT_TIME);
        Robot.indexer.indexerFrntStop();
    }

    // public void Intake() 
    // {
    //     Robot.intake.intakeRollerIn();
    //     Timer.delay(2);
    //     Robot.intake.intakeRollerOff();
    // }

    /**************************************************************************************
     *                                 Drive Foward
     **************************************************************************************/

    public void gridToCenter() 
    {
        //Robot.auton.DriveStraight(MAX_DIST, TEMP_DECEL_DIST, FAST, CORRECTED_STRAIGHT, TEMP_MAX_TIME);
        Robot.auton.DriveStraight(MAX_DIST, TEMP_DECEL_DIST, FAST, CORRECTED_STRAIGHT, TEMP_MAX_TIME);
    }
    public void leaveComunity() 
    {
        //Robot.auton.DriveStraight(MAX_DIST, TEMP_DECEL_DIST, FAST, CORRECTED_STRAIGHT, TEMP_MAX_TIME);
        Robot.auton.DriveStraight(CLR_COMM, TEMP_DECEL_DIST, FAST, CORRECTED_STRAIGHT, TEMP_MAX_TIME);
    }
    
    public void gridToAreaInfrontOfCargo() 
    {
        Robot.auton.DriveStraight(MAX_DIST - MIN_DIST, TEMP_DECEL_DIST, FAST, STRAIGHT, TEMP_MAX_TIME);
    }
    
    public void fowardToCargo() 
    {
        Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, SLOW, STRAIGHT, TEMP_MAX_TIME);
    }
    public void gridToChargingStation() 
    {
        Robot.auton.DriveStraight(GP_TO_GP + 24, TEMP_DECEL_DIST, SLOW, STRAIGHT, TEMP_MAX_TIME);
    }


    /**************************************************************************************
     *                                 Drive Backward
     **************************************************************************************/

    public void enterCommunity() 
    {
        Robot.auton.DriveStraight(CLR_COMM, TEMP_DECEL_DIST, -FAST, STRAIGHT, TEMP_MAX_TIME);

    }
     public void centerToGrid() 
    {
        Robot.auton.DriveStraight(MAX_DIST, TEMP_DECEL_DIST, -FAST, STRAIGHT, TEMP_MAX_TIME);
    }
    public void centerToAreaInfrontOfDock() 
    {
        Robot.auton.DriveStraight(MAX_DIST - MIN_DIST, TEMP_DECEL_DIST, -FAST, STRAIGHT, TEMP_MAX_TIME);
    }
    public void dockToGrid() 
    {
        //Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, -SLOW, STRAIGHT, TEMP_MAX_TIME);
        Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, -0.8, STRAIGHT, TEMP_MAX_TIME);
    }
    public void centerToChargingStation() 
    {
        //Robot.auton.DriveStraight(MIN_DIST * 13 / 2, TEMP_DECEL_DIST, -FAST, STRAIGHT, TEMP_MAX_TIME); //dist used to be -128.75
        Robot.auton.DriveStraight(MIN_DIST * 13 / 2, TEMP_DECEL_DIST, -0.8, STRAIGHT, TEMP_MAX_TIME); //dist used to be -128.75
    }

    /**************************************************************************************
     *                                  Drive Right
     **************************************************************************************/
    public void translateRight48() 
    {
        Robot.auton.DriveStraight(GP_TO_GP + 24, TEMP_DECEL_DIST, SLOW, RIGHT, TEMP_MAX_TIME);
    }
    public void translateFowardRight() 
    {
        Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, SLOW, STRAIGHT, TEMP_MAX_TIME);
        Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, SLOW, RIGHT, TEMP_MAX_TIME);
    }
    public void translateBackRight() //Backwards and Right
    {
        Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, SLOW, RIGHT, TEMP_MAX_TIME);
        Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, -SLOW, STRAIGHT, TEMP_MAX_TIME);
    }

    /**************************************************************************************
     *                                 Drive Left
     **************************************************************************************/

    public void translateLeft48() 
    {
        Robot.auton.DriveStraight(GP_TO_GP, TEMP_DECEL_DIST, SLOW, LEFT, TEMP_MAX_TIME);
    }
    public void translateFowardLeft() 
    {
        Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, SLOW, STRAIGHT, TEMP_MAX_TIME);
        Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, SLOW, LEFT, TEMP_MAX_TIME);
    }
    public void translateBackLeft() 
    {
        Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, SLOW, LEFT, TEMP_MAX_TIME);
        Robot.auton.DriveStraight(MIN_DIST, TEMP_DECEL_DIST, -SLOW, STRAIGHT, TEMP_MAX_TIME);
    }    
    public void translateLeft74() 
    {
        Robot.auton.DriveStraight(GP_TO_GP + 24, TEMP_DECEL_DIST, SLOW, LEFT, TEMP_DECEL_DIST);
    }
    public void diagonal()
    {
        Robot.auton.DriveStraight(GP_TO_GP, TEMP_DECEL_DIST, FAST, GP_TO_GP, TEMP_MAX_TIME); //wheelPos used to be 45 (foward left)
        Robot.auton.DriveStraight(GP_TO_GP, TEMP_DECEL_DIST, SLOW, STRAIGHT, TEMP_MAX_TIME);
    }
   
    // private final int CENTER_PRELOAD_TAXI_BALANCE = 1;
    // private final int SIDE_PRELOAD_INTAKE_SCORE = 2;
    // private final int SIDE_PRELOAD_INTAKE_SCORE_BALANCE = 3;
    // private final int DEFENSE_PRELOAD_POSITIONING = 4;
    // private final int CENTER_PRELOAD_SCORE_INTAKE_BALANCE = 5;
    // private final int CENTER_PRELOAD_INTAKE_SCORE_INTAKE_SCORE_BALANCE = 6;
    // private final int SIDE_PRELOAD_INTAKE_SCORE_INTAKE = 7;
    // private final int SIDE_2_PRELOAD_INTAKE_SCORE_INTAKE_SCORE_BALANCE = 8;
    private final int TEST_PATH = 1;
    private final int PATH_1    = 2;
    private final int PATH_2    = 3;
    
    public void determinePath()
    {
        Path = chosenPath.getSelected();

        System.out.println("Path Selected: " + Path);

        if(chosenAllianceColor.getSelected() == Robot.constants.RED_ALLIANCE)
        {
            Red();
        }
        // if(chosenCube.getSelected() == Robot.constants.CUBE_PATH) 
        // {
        //     ScoreCube();
        // }

        // if(chosenBalance.getSelected()){
        //     doBalance = true;
        // }

        // if(Path == CENTER_PRELOAD_TAXI_BALANCE) 
        // {
        //     CenterPreloadTaxiBalance();
        // }
        // if(Path == SIDE_PRELOAD_INTAKE_SCORE) 
        // {
        //     SidePreloadIntakeScore();
        // }
        // if(Path == SIDE_PRELOAD_INTAKE_SCORE_BALANCE)
        // {
        //     SidePreloadIntakeScoreBalance();
        // }
        // if(Path == DEFENSE_PRELOAD_POSITIONING) 
        // {
        //     DefensePreloadPositioning();
        // }
        // if(Path == CENTER_PRELOAD_SCORE_INTAKE_BALANCE) 
        // {
        //     CenterPreloadIntakeScoreBalance();
        // } 
        // if(Path == CENTER_PRELOAD_INTAKE_SCORE_INTAKE_SCORE_BALANCE) 
        // {
        //     CenterPreloadIntakeScoreIntakeScoreBalance();
        // } //SidePreloadIntakeScoreIntake
        // if(Path == SIDE_PRELOAD_INTAKE_SCORE_INTAKE) 
        // {
        //     SidePreloadIntakeScoreIntake();
        // } // Side2PreloadIntakeScoreIntakeScoreBalance
        // if(Path == SIDE_2_PRELOAD_INTAKE_SCORE_INTAKE_SCORE_BALANCE) 
        // {
        //     Side2PreloadIntakeScoreIntakeScoreBalance();
        // }
        if(Path == TEST_PATH) 
        {
            TestPath();
        }

        if(Path == PATH_1)
        {
            Path1();
        }

        if(Path == PATH_2) 
        {
            Path2();
        }

        
    }

    public void TestPath() 
    {
        CubeIndexerScore();
        Robot.auton.DriveStraight(30, TEMP_DECEL_DIST, SLOW, CORRECTED_STRAIGHT, TEMP_MAX_TIME);

        Robot.auton.TurnInPlace(180, 8);
        Robot.auton.DriveStraight(150, TEMP_DECEL_DIST, SLOW, CORRECTED_STRAIGHT, TEMP_MAX_TIME);
        //intake on
        Robot.auton.DriveStraight(50, 0.02, 0.04, CORRECTED_STRAIGHT, TEMP_DECEL_DIST);
        //intake off
        Robot.auton.TurnInPlace(180, 8);
        Robot.auton.DriveStraight(210, TEMP_DECEL_DIST, -SLOW, CORRECTED_STRAIGHT, TEMP_MAX_TIME);
        Robot.auton.DriveStraight(48, TEMP_DECEL_DIST, SLOW, CORRECTED_RIGHT, TEMP_MAX_TIME);
        Robot.auton.DriveStraight(25, TEMP_DECEL_DIST, -SLOW, CORRECTED_STRAIGHT, TEMP_MAX_TIME);
        CubeIndexerScore();

        Robot.auton.StopDriving();
    }

    public void Path1() 
    {
        CubeIndexerScore();
        Robot.auton.DriveStraight(200, 0.02, 0.40, 0.0, 4.0);
        // Robot.auton.DriveStraight(48, 0.02, 0.30, 90, 2.0);
        // Robot.auton.DriveStraight(40, 0.02, 0.30, 0.0, 5.0);
        // Robot.balance.StartBalancing();
        Robot.auton.StopDriving();
    }

    public void Path2() 
    {
        CubeIndexerScore();
        Robot.auton.DriveStraight(170, 0.02, 0.45, 0.0, 4.0);
        Robot.auton.TurnInPlace(180, 2.0);

        Robot.intake.intakeDeploy();
        Robot.intake.intakeRollerIn();
        Robot.auton.DriveStraight(45, 0.02, 0.35, 0.0, 2.0);
        Robot.intake.intakeRollerOff();
        Robot.auton.TurnInPlace(180, 2.0);

        Robot.auton.DriveStraight(200, 0.02, -0.45, 0.0, 4.0);
        Robot.auton.DriveStraight(25, 0.02, 0.35, 90.0, 2.0);
        Robot.intake.intakeRollerOut();
        CubeIndexerScore();
        Robot.intake.intakeRollerOff();
        // Robot.auton.DriveStraight(25, 0.02, 0.035, -90.0, 2);
        // Robot.auton.DriveStraight(200, 0.02, 0.45, 0.0, 4.0);

        Robot.auton.StopDriving();
    }

    // public void CenterPreloadTaxiBalance() 
    // {
    //     // dockToGrid();
    //     CubeIndexerScore();
    //     //score code
    //     // gridToCenter();
    //     //pickup cone;
    //     centerToChargingStation();
    //     Robot.balance.StartBalancing();
    //     Robot.auton.StopDriving();
    // }
    
    // public void SidePreloadIntakeScore()
    // {
    //     CubeIndexerScore();
    //     //score code
    //     gridToCenter();
    //     //pickup cone;
    //     centerToGrid();
    //     CubeIndexerScore();
    //     //score cone
    //     Robot.auton.StopDriving();
    // }

    // public void SidePreloadIntakeScoreBalance() //too long
    // {
    //     // translateBackLeft();
    //     CubeIndexerScore();
    //     // score cone
    //     // translateFowardRight();
    //     // Robot.intake.intakeDeploy();
    //     leaveComunity();
    //     // Intake();
    //     //pickup cube
    //     enterCommunity();
    //     //score cube
    //     Robot.auton.DriveStraight(20, 0.02, 0.25, STRAIGHT, 2);
    //     translateLeft74();
    //     translateRight48();
    //     gridToChargingStation();
    //     Robot.balance.StartBalancing();
    //     Robot.auton.StopDriving();
    // }

    // public void DefensePreloadPositioning() 
    // {
    //     //translateBackLeft();
    //     CubeIndexerScore();
    //     //score cone;
    //     // translateFowardRight();
    //     gridToAreaInfrontOfCargo();
    //     // diagonal();
    //     Robot.auton.StopDriving();
    // }

    // public void CenterPreloadIntakeScoreBalance() //too long
    // {  
    //     CubeIndexerScore();
    //     //score cone;
    //     gridToCenter();
    //     //pickup cone;
    //     centerToAreaInfrontOfDock();
    //     translateBackRight();
    //     CubeIndexerScore();
    //     //score cone;
    //     translateFowardLeft();
    //     gridToChargingStation();
    //     Robot.balance.StartBalancing();
    //     Robot.auton.StopDriving();
    // }

    // public void CenterPreloadIntakeScoreIntakeScoreBalance() //too long
    // {
    //     CubeIndexerScore();
    //     //score cone;
    //     gridToCenter();
    //     //pickup cone;
    //     centerToAreaInfrontOfDock();
    //     translateBackRight();
    //     CubeIndexerScore();
    //     //score cone
    //     translateFowardLeft();
    //     gridToAreaInfrontOfCargo();
    //     //pickup cone
    //     centerToGrid();
    //     CubeIndexerScore();
    //     //score cone
    //     gridToChargingStation();
    //     Robot.balance.StartBalancing();
    //     Robot.auton.StopDriving();
    // }


    // public void SidePreloadIntakeScoreIntake() //too long
    // { 
    //     translateBackLeft();
    //     CubeIndexerScore();
    //     //score cone;
    //     translateFowardRight();
    //     gridToAreaInfrontOfCargo();
    //     //pickup cube
    //     centerToGrid();
    //     CubeIndexerScore();
    //     //score cube
    //     gridToAreaInfrontOfCargo();
    //     translateRight48();
    //     fowardToCargo();
    //     //pickup cone
    //     centerToGrid();
    //     CubeIndexerScore();
    //     //score cone
    //     gridToChargingStation();
    //     Robot.balance.StartBalancing();
    //     Robot.auton.StopDriving();
    // }


    // public void Side2PreloadIntakeScoreIntakeScoreBalance()
    // {
    //     translateBackRight();
    //     CubeIndexerScore();
    //     //score cone;
    //     translateFowardLeft();
    //     gridToAreaInfrontOfCargo();
    //     //pickup cube
    //     centerToGrid();
    //     CubeIndexerScore();
    //     //score cube
    //     gridToAreaInfrontOfCargo();
    //     translateLeft48();
    //     fowardToCargo();
    //     //pickup cone
    //     centerToGrid();
    //     CubeIndexerScore();
    //     //score cone
    //     gridToChargingStation();
    //     Robot.balance.StartBalancing();
    //     Robot.auton.StopDriving();
    // }
}