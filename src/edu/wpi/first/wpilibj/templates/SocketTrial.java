/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.io.BufferedWriter;
import edu.wpi.first.wpilibj.IterativeRobot;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;

/*
 * Special Notes :
 *   This class establishes a server at port 1180 on the CRIO
 *   This class tests building a server on the robot and 
 */
public class SocketTrial extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    private ServerSocketConnection m_server;
    private SocketConnection m_socket;
    private BufferedWriter out;
    private BufferedReader in;
    private int i;
    
    public void robotInit() {
        try {
            m_server = (ServerSocketConnection)Connector.open("socket://:1180");
            m_socket = (SocketConnection) m_server.acceptAndOpen();
            m_socket.setSocketOption(SocketConnection.DELAY, 0);
            m_socket.setSocketOption(SocketConnection.KEEPALIVE, 0);
            m_socket.setSocketOption(SocketConnection.LINGER, 0);
            m_socket.setSocketOption(SocketConnection.RCVBUF, 128);
            m_socket.setSocketOption(SocketConnection.SNDBUF, 128);
            out = new BufferedWriter(new OutputStreamWriter(m_socket.openOutputStream()));
            in = new BufferedReader(new InputStreamReader(m_socket.openInputStream()));
            i = 0;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        try {
            i++;
            out.write("Hey Listen\t" + i + "\n");
            System.out.println(in.readLine());
            in.reset();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
