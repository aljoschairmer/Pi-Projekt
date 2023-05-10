package jSerialCommReceive;


import com.fazecast.jSerialComm.*;

public class SerialCommReceive {
    public static void main (String[] Args) {

        int BaudRate = 9600;
        int DataBits = 8;
        int StopBits = SerialPort.ONE_STOP_BIT;
        int Parity   = SerialPort.NO_PARITY;

        //Print all available serial ports
        SerialPort[] AvailablePorts = SerialPort.getCommPorts();
        System.out.print("\n\n Available Ports ");
        for (int i = 0; i<AvailablePorts.length ; i++) {
            System.out.println(i + " - " + AvailablePorts[i].getSystemPortName() + " -> " + AvailablePorts[i].getDescriptivePortName());
        }

        SerialPort MySerialPort = AvailablePorts[2];
        MySerialPort.setComPortParameters(BaudRate,DataBits,StopBits,Parity);//Sets all serial port parameters at one time
        MySerialPort.openPort(); //open the port

        if (MySerialPort.isOpen()) {
            System.out.println("\n" + MySerialPort.getSystemPortName() + "  is open ");
        }
        else {
            System.out.println(" Port not open ");
        }
        //Display the Serial Port parameters
        System.out.println("\n Selected Port               = " + MySerialPort.getSystemPortName());
        System.out.println(" Selected Baud rate          = " + MySerialPort.getBaudRate());
        System.out.println(" Selected Number of DataBits = " + MySerialPort.getNumDataBits());
        System.out.println(" Selected Number of StopBits = " + MySerialPort.getNumStopBits());
        System.out.println(" Selected Parity             = " + MySerialPort.getParity());

        try {
            String text = "Hallo";
            MySerialPort.getOutputStream().write(text.getBytes());
            MySerialPort.getOutputStream().flush();
            MySerialPort.getOutputStream().close();
            System.out.print(" Text Transmitted -> " + text );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        MySerialPort.closePort();
        if (MySerialPort.isOpen()){
            System.out.println(MySerialPort.getSystemPortName() + " is still open ");
        }
        else {
            System.out.println("\n Port closed ");
        }
    }
}