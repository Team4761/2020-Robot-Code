package org.robockets.infiniterecharge;

/**
 * PID source type defines what type to encoder value we receive
 * @kdistance: means that the encoder returns how far the motor has moved.
 * @kspeed: means that the encoder returns how fast the motor has moved.
 *
 * PID will be calculated accordingly depending on what type of encoder value is recieved.
 *
 */

public enum PidSourceType {
    kdistance,
    kspeed
}
